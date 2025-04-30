/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import modelo.Entrenamiento;
import modelo.Ubicacion;
import modelo.Usuario;

/**
 *
 * @author ruben
 */
public class ListaUbicaciones implements ListaUbicacionesInterface{
    
    private String APPLICATION_ID;
    private String REST_API_KEY;
    private static final String API_URL = "https://parseapi.back4app.com/classes/ubicacion";
    
    public ListaUbicaciones(String app_id, String rest_api_key){
        this.APPLICATION_ID = app_id;
        this.REST_API_KEY = rest_api_key;
    }

    @Override
    public String addUbicacion(Ubicacion ubicacion) {
        String objectId="";
        Gson gson = new Gson();
        String jsonEjercicio = gson.toJson(ubicacion);
        try {
            Request request = new Request.Builder()
                .url(API_URL)
                .addHeader("X-Parse-Application-Id", APPLICATION_ID)
                .addHeader("X-Parse-REST-API-Key", REST_API_KEY)
                .post(RequestBody.create(MediaType.get("application/json"),jsonEjercicio))
                .build();
            OkHttpClient client = new OkHttpClient();
            Response response = client.newCall(request).execute();
            if(response.isSuccessful()) {
                Ubicacion newUbicacion = gson.fromJson(response.body().string(), Ubicacion.class);
                objectId = newUbicacion.getId();
               }
        }
        catch (IOException e) {
           System.out.println(e.getMessage());
        }
        return objectId;
    }

    @Override
    public List<Ubicacion> getListaUbicaciones() {
        ArrayList<Ubicacion> listaUbicaciones = new ArrayList<>();

        try {
            Request request = new Request.Builder()
                .url(API_URL)
                .addHeader("X-Parse-Application-Id", APPLICATION_ID)
                .addHeader("X-Parse-REST-API-Key", REST_API_KEY)
                .get()
                .build();

            OkHttpClient client = new OkHttpClient();
            Response response = client.newCall(request).execute();

            if (response.isSuccessful()) {
                String responseJson = response.body().string();
                System.out.println(responseJson);
                JsonObject jsonObject = new Gson().fromJson(responseJson, JsonObject.class);
                JsonArray jsonArray = jsonObject.getAsJsonArray("results");
                Gson gson = new Gson();
                Type listType = new TypeToken<List<Ubicacion>>(){}.getType();
                listaUbicaciones = gson.fromJson(jsonArray, listType);
            } else {
                System.out.println("Error al obtener las ubicaciones: " + response.code());
            }
        } catch (IOException e) {
            System.out.println("Error de conexión: " + e.getMessage());
        }

        return listaUbicaciones;
    }

    @Override
    public void deleteUbicacion(Ubicacion ubicacion) {
        String objectId = ubicacion.getId();
    
        if (objectId == null || objectId.isEmpty()) {
            System.out.println("No se ha proporcionado un objectId válido.");
            return;
        }

        try {
            Request request = new Request.Builder()
                .url(API_URL + "/" + objectId)
                .addHeader("X-Parse-Application-Id", APPLICATION_ID)
                .addHeader("X-Parse-REST-API-Key", REST_API_KEY)
                .delete()
                .build();

            OkHttpClient client = new OkHttpClient();
            Response response = client.newCall(request).execute();

            if (response.isSuccessful()) {
                System.out.println("Ubicacion eliminada con éxito.");
            } else {
                System.out.println("Error al eliminar la ubicacion: " + response.code());
            }
        } catch (IOException e) {
            System.out.println("Error de conexión: " + e.getMessage());
        }
    }

    @Override
    public void updateUbicacion(Ubicacion ubicacion) {
        Gson gson = new Gson();
        String jsonUbicacion = gson.toJson(ubicacion);

        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                .url(API_URL + "/" + ubicacion.getId())
                .addHeader("X-Parse-Application-Id", APPLICATION_ID)
                .addHeader("X-Parse-REST-API-Key", REST_API_KEY)
                .addHeader("Content-Type", "application/json")
                .put(RequestBody.create(MediaType.get("application/json"), jsonUbicacion))
                .build();

            Response response = client.newCall(request).execute();
            String responseJson = response.body().string();
            System.out.println("Response: " + responseJson);

            if (!response.isSuccessful()) {
                throw new IOException("Error en la actualización: " + response.message());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
}
