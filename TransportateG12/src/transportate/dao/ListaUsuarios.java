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

import transportate.modelo.Usuario;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 *
 * @author ruben
 */
public class ListaUsuarios implements ListaUsuariosInterface{
    private String APPLICATION_ID;
    private String REST_API_KEY;
    private static final String API_URL = "https://parseapi.back4app.com/classes/usuario";

    public ListaUsuarios(String app_id, String rest_api_key) {
        this.APPLICATION_ID = app_id;
        this.REST_API_KEY = rest_api_key;
    }
    
    
    @Override
    public String addUsuario(Usuario usuario) {
        String objectId="";
        Gson gson = new Gson();
        String jsonUsuario = gson.toJson(usuario);
        try {
            Request request = new Request.Builder()
                .url(API_URL)
                .addHeader("X-Parse-Application-Id", APPLICATION_ID)
                .addHeader("X-Parse-REST-API-Key", REST_API_KEY)
                .post(RequestBody.create(MediaType.get("application/json"),jsonUsuario))
                .build();
            OkHttpClient client = new OkHttpClient();
            Response response = client.newCall(request).execute();
            if(response.isSuccessful()) {
                Usuario newUsuario = gson.fromJson(response.body().string(), Usuario.class);
                objectId = newUsuario.getId();
            }
        }
        catch (IOException e) {
           System.out.println(e.getMessage());
        }
        return objectId;
    }

    @Override
    public List<Usuario> getListaUsuarios() {
        ArrayList<Usuario> listaUsuarios = new ArrayList<>();

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
                Type listType = new TypeToken<List<Usuario>>(){}.getType();
                listaUsuarios = gson.fromJson(jsonArray, listType);
            } else {
                System.out.println("Error al obtener los usuarios: " + response.code());
            }
        } catch (IOException e) {
            System.out.println("Error de conexión: " + e.getMessage());
        }

        return listaUsuarios;
    }

    @Override
    public void deleteUsuario(Usuario usuario) {
        String objectId = usuario.getId();
    
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
                System.out.println("Usuario eliminado con éxito.");
            } else {
                System.out.println("Error al eliminar el usuario: " + response.code());
            }
        } catch (IOException e) {
            System.out.println("Error de conexión: " + e.getMessage());
        }
    }
    
    @Override
    public void updateUsuario(Usuario usuario) {
        Gson gson = new Gson();
        String jsonUsuario = gson.toJson(usuario);

        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                .url(API_URL + "/" + usuario.getId())
                .addHeader("X-Parse-Application-Id", APPLICATION_ID)
                .addHeader("X-Parse-REST-API-Key", REST_API_KEY)
                .addHeader("Content-Type", "application/json")
                .put(RequestBody.create(MediaType.get("application/json"), jsonUsuario))
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
