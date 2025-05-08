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

import modelo.UsuarioEntrenamiento;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 *
 * @author ruben
 */
public class ListaUsuarioEntrenamiento implements ListaUsuarioEntrenamientoInterface{
    
    private String APPLICATION_ID;
    private String REST_API_KEY;
    private static final String API_URL = "https://parseapi.back4app.com/classes/usuarioEntrenamiento";
    
    public ListaUsuarioEntrenamiento(String api_id, String rest_api_key){
        this.APPLICATION_ID = api_id;
        this.REST_API_KEY = rest_api_key;
    }

    @Override
    public String addUsuarioEntrenamiento(UsuarioEntrenamiento usuarioEntrenamiento) {
        String objectId="";
        Gson gson = new Gson();
        String jsonUsuarioEntrenamiento = gson.toJson(usuarioEntrenamiento);
        try {
            Request request = new Request.Builder()
                .url(API_URL)
                .addHeader("X-Parse-Application-Id", APPLICATION_ID)
                .addHeader("X-Parse-REST-API-Key", REST_API_KEY)
                .post(RequestBody.create(MediaType.get("application/json"),jsonUsuarioEntrenamiento))
                .build();
            OkHttpClient client = new OkHttpClient();
            Response response = client.newCall(request).execute();
            if(response.isSuccessful()) {
                UsuarioEntrenamiento newUsuarioEntrenamiento = gson.fromJson(response.body().string(), UsuarioEntrenamiento.class);
                objectId = newUsuarioEntrenamiento.getId();
            }
        }
        catch (IOException e) {
           System.out.println(e.getMessage());
        }
        return objectId;
    }

    @Override
    public List<UsuarioEntrenamiento> getListaUsuarioEntrenamiento() {
        ArrayList<UsuarioEntrenamiento> listaUsuariosEntrenamientos = new ArrayList<>();

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
                Type listType = new TypeToken<List<UsuarioEntrenamiento>>(){}.getType();
                listaUsuariosEntrenamientos = gson.fromJson(jsonArray, listType);
            } else {
                System.out.println("Error al obtener los usuariosEntrenamientos: " + response.code());
            }
        } catch (IOException e) {
            System.out.println("Error de conexión: " + e.getMessage());
        }

        return listaUsuariosEntrenamientos;
    }

    @Override
    public void deleteUsuarioEntrenamiento(UsuarioEntrenamiento usuarioEntrenamiento) {
        String objectId = usuarioEntrenamiento.getId();
    
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
                System.out.println("UsuarioEntrenamiento eliminado con éxito.");
            } else {
                System.out.println("Error al eliminar el usuarioEntrenamiento: " + response.code());
            }
        } catch (IOException e) {
            System.out.println("Error de conexión: " + e.getMessage());
        }
    }

    @Override
    public void updateUsuarioEntrenamiento(UsuarioEntrenamiento usuarioEntrenamiento) {
        Gson gson = new Gson();
        String jsonUsuarioEntrenamiento = gson.toJson(usuarioEntrenamiento);

        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                .url(API_URL + "/" + usuarioEntrenamiento.getId())
                .addHeader("X-Parse-Application-Id", APPLICATION_ID)
                .addHeader("X-Parse-REST-API-Key", REST_API_KEY)
                .addHeader("Content-Type", "application/json")
                .put(RequestBody.create(MediaType.get("application/json"), jsonUsuarioEntrenamiento))
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
