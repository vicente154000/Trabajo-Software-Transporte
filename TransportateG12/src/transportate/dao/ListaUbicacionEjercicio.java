/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package transportate.dao;

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
import transportate.modelo.UbicacionEjercicio;

/**
 *
 * @author ruben
 */
public class ListaUbicacionEjercicio implements ListaUbicacionEjercicioInterface{
    
    private String APPLICATION_ID;
    private String REST_API_KEY;
    private static final String API_URL = "https://parseapi.back4app.com/classes/ubicacionEjercicio";
    
    public ListaUbicacionEjercicio(String app_id, String rest_api_key){
        this.APPLICATION_ID = app_id;
        this.REST_API_KEY = rest_api_key;
    }
    
    @Override
    public String addUbicacionEjercicio(UbicacionEjercicio ubicacionEjercicio) {
        String objectId="";
        Gson gson = new Gson();
        String jsonUbicacionEjercicio = gson.toJson(ubicacionEjercicio);
        try {
            Request request = new Request.Builder()
                .url(API_URL)
                .addHeader("X-Parse-Application-Id", APPLICATION_ID)
                .addHeader("X-Parse-REST-API-Key", REST_API_KEY)
                .post(RequestBody.create(MediaType.get("application/json"),jsonUbicacionEjercicio))
                .build();
            OkHttpClient client = new OkHttpClient();
            Response response = client.newCall(request).execute();
            if(response.isSuccessful()) {
                UbicacionEjercicio newUbicacionEjercicio = gson.fromJson(response.body().string(), UbicacionEjercicio.class);
                objectId = newUbicacionEjercicio.getId();
               }
        }
        catch (IOException e) {
           System.out.println(e.getMessage());
        }
        return objectId;
    }

    @Override
    public List<UbicacionEjercicio> getListaUbicacionesEjercicios() {
        ArrayList<UbicacionEjercicio> listaUbicacionesEjercicios = new ArrayList<>();

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
                Type listType = new TypeToken<List<UbicacionEjercicio>>(){}.getType();
                listaUbicacionesEjercicios = gson.fromJson(jsonArray, listType);
            } else {
                System.out.println("Error al obtener las ubicaciones - ejercicios: " + response.code());
            }
        } catch (IOException e) {
            System.out.println("Error de conexión: " + e.getMessage());
        }

        return listaUbicacionesEjercicios;
    }

    @Override
    public void deleteUbicacionEjercicio(UbicacionEjercicio ubicacionEjercicio) {
        String objectId = ubicacionEjercicio.getId();
    
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
                System.out.println("Ubicacion - ejercicio eliminado con éxito.");
            } else {
                System.out.println("Error al eliminar la ubicacion - ejercicio: " + response.code());
            }
        } catch (IOException e) {
            System.out.println("Error de conexión: " + e.getMessage());
        }
    }

    @Override
    public void updateUbicacionEjercicio(UbicacionEjercicio ubicacionEjercicio) {
        Gson gson = new Gson();
        String jsonUbicacionEjercicio= gson.toJson(ubicacionEjercicio);

        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                .url(API_URL + "/" + ubicacionEjercicio.getId())
                .addHeader("X-Parse-Application-Id", APPLICATION_ID)
                .addHeader("X-Parse-REST-API-Key", REST_API_KEY)
                .addHeader("Content-Type", "application/json")
                .put(RequestBody.create(MediaType.get("application/json"), jsonUbicacionEjercicio))
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
