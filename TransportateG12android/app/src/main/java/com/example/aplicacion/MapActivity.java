package com.example.aplicacion;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.Manifest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.Priority;

import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.MapController;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.Overlay;
import org.osmdroid.views.overlay.Polyline;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import java.util.Arrays;

public class MapActivity extends AppCompatActivity {
    private MapView mapView;
    private FusedLocationProviderClient fusedLocationClient;
    private LocationRequest locationRequest;
    private LocationCallback locationCallback;
    private static final int REQUEST_LOCATION_PERMISSION_CODE = 1001;
    private static final float ARRIVAL_RADIUS_METERS = 10;
    private double lat_destino;
    private double lon_destino;
    private String nombre_destino;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        // Initialize osmdroid configuration
        Configuration.getInstance().load(this, getPreferences(MODE_PRIVATE));

        mapView = findViewById(R.id.mapView);
        mapView.setTileSource(TileSourceFactory.MAPNIK); // Use OSM standard map
        mapView.setBuiltInZoomControls(true);
        mapView.setMultiTouchControls(true);
        lat_destino = getIntent().getDoubleExtra("lat_dest", 42.800431);
        lon_destino = getIntent().getDoubleExtra("lon_dest", -1.636659);
        nombre_destino = getIntent().getStringExtra("nombre_dest");

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        if (checkAndRequestLocationPermissions()) {
            checkLocationEnabled();
        }

        addDestinationMarker();
    }

    private void checkLocationEnabled() {
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        boolean gpsEnabled = false;
        boolean networkEnabled = false;

        try {
            gpsEnabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception ex) {}

        try {
            networkEnabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch (Exception ex) {}

        if (!gpsEnabled && !networkEnabled) {
            showLocationEnableDialog();
        }
        setupLocationTracking();
    }

    private void showLocationEnableDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Activar Ubicación")
                .setMessage("No tienes la ubicación activada")
                .setPositiveButton("Activar", (dialog, which) -> {
                    Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(intent);
                })
                .setNegativeButton("Cancelar", (dialog, which) -> {
                    Toast.makeText(this, "Necesitas la ubicación para desplazarte", Toast.LENGTH_SHORT).show();
                    finish();
                })
                .setCancelable(false)
                .show();
    }

    private boolean checkAndRequestLocationPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION
                    },
                    REQUEST_LOCATION_PERMISSION_CODE);
            return false;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_LOCATION_PERMISSION_CODE) {
            if (grantResults.length > 0 &&
                    Arrays.stream(grantResults).allMatch(result -> result == PackageManager.PERMISSION_GRANTED)) {

                setupLocationTracking();
            } else {
                Toast.makeText(this, "Permisos requeridos para seguir el recorrido", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    private void setupLocationTracking() {
        MyLocationNewOverlay locationOverlay = new MyLocationNewOverlay(
                new GpsMyLocationProvider(this), mapView);
        locationOverlay.enableMyLocation();
        mapView.getOverlays().add(locationOverlay);

        locationRequest = new LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 10000)
                .setMinUpdateIntervalMillis(2000)
                .build();

        createLocationCallback();

        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationClient.requestLocationUpdates(locationRequest,
                    locationCallback,
                    Looper.getMainLooper());
        }
    }

    private void addDestinationMarker() {
        mapView.getOverlays().removeIf(overlay -> overlay instanceof Marker &&
                "dest".equals(((Marker) overlay).getId()));

        Marker destinationMarker = new Marker(mapView);
        destinationMarker.setPosition(new GeoPoint(lat_destino, lon_destino));
        destinationMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        destinationMarker.setTitle("Siguiente Ubicación");
        destinationMarker.setSnippet(nombre_destino);
        destinationMarker.setId("dest");

        destinationMarker.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_destination_pin));

        mapView.getOverlays().add(destinationMarker);
        mapView.invalidate();
    }

    private void centerMapOnLocation(Location location) {
        MapController mapController = (MapController) mapView.getController();
        GeoPoint geoPoint = new GeoPoint(location.getLatitude(), location.getLongitude());

        mapController.animateTo(geoPoint, 19.0, 4000L);
    }

    private void updatePathLine(Location userLocation) {
        mapView.getOverlays().removeIf(overlay -> overlay instanceof Polyline);

        Polyline line = new Polyline();
        line.setPoints(Arrays.asList(
                new GeoPoint(userLocation.getLatitude(), userLocation.getLongitude()),
                new GeoPoint(lat_destino, lon_destino)
        ));
        line.setColor(Color.RED);
        line.setWidth(5f);

        mapView.getOverlays().add(line);
        mapView.invalidate();
    }

    private void createLocationCallback() {
        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) return;

                Location location = locationResult.getLastLocation();
                centerMapOnLocation(location);
                updatePathLine(location);

                if (isAtDestination(location)) {
                    onArrival();
                }
            }
        };
    }

    private boolean isAtDestination(Location userLocation) {
        Location targetLocation = new Location("");
        targetLocation.setLatitude(lat_destino);
        targetLocation.setLongitude(lon_destino);

        return userLocation.distanceTo(targetLocation) <= ARRIVAL_RADIUS_METERS;
    }

    private void onArrival() {
        fusedLocationClient.removeLocationUpdates(locationCallback);

        for (Overlay overlay : mapView.getOverlays()) {
            if (overlay instanceof Marker &&
                    "dest".equals(((Marker) overlay).getId())) {

                Marker destinationMarker = (Marker) overlay;
                destinationMarker.setIcon(ContextCompat.getDrawable(
                        this, R.drawable.ic_destination_reached));
                break;
            }
        }

        mapView.invalidate();
        Toast.makeText(this, "Has llegado!",
                Toast.LENGTH_LONG).show();

        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            Intent returnIntent = new Intent();
            returnIntent.putExtra("result", "llegado");
            setResult(RESULT_OK, returnIntent);
            finish();
        }, 1000);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
        if (checkAndRequestLocationPermissions() && locationCallback != null) {
            if (ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper());
            }
        }
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (fusedLocationClient != null && locationCallback != null) {
            fusedLocationClient.removeLocationUpdates(locationCallback);
        }
        mapView.onPause();
    }
}