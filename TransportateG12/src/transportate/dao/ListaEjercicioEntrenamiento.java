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

import transportate.modelo.Ejercicio;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import transportate.modelo.EjercicioEntrenamiento;

/**
 *
 * @author ruben
 */
public class ListaEjercicioEntrenamiento implements ListaEjercicioEntrenamientoInterface{
    
    private static String APPLICATION_ID;
    private static String REST_API_KEY;
    private static final String API_URL = "https://parseapi.back4app.com/classes/ejercicioEntrenamiento";
    
    public ListaEjercicioEntrenamiento(String appID, String restAPIKey) {
        this.APPLICATION_ID = appID;
        this.REST_API_KEY = restAPIKey;
    }
    
    @Override
    public String addEjercicioEntrenamiento(EjercicioEntrenamiento ejercicioEntrenamiento) {
        String objectId="";
        Gson gson = new Gson();
        String jsonEjercicio = gson.toJson(ejercicioEntrenamiento);
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
                EjercicioEntrenamiento newEjercicioEntrenamiento = gson.fromJson(response.body().string(), EjercicioEntrenamiento.class);
                objectId = newEjercicioEntrenamiento.getId();
               }
        }
        catch (IOException e) {
           System.out.println(e.getMessage());
        }
        return objectId;
    }

    @Override
    public void deleteEjercicioEntrenamiento(EjercicioEntrenamiento ejercicioEntrenamiento) {
        String objectId = ejercicioEntrenamiento.getId();
    
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
                System.out.println("EjercicioEntrenamiento eliminado con éxito.");
            } else {
                System.out.println("Error al eliminar el ejercicioEntrenamiento: " + response.code());
            }
        } catch (IOException e) {
            System.out.println("Error de conexión: " + e.getMessage());
        }
    }

    @Override
    public void updatejercicioEntrenamiento(EjercicioEntrenamiento ejercicioEntrenamiento) {
        Gson gson = new Gson();
        String jsonEjercicioEntrenamiento = gson.toJson(ejercicioEntrenamiento);

        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                .url(API_URL + "/" + ejercicioEntrenamiento.getId())
                .addHeader("X-Parse-Application-Id", APPLICATION_ID)
                .addHeader("X-Parse-REST-API-Key", REST_API_KEY)
                .addHeader("Content-Type", "application/json")
                .put(RequestBody.create(MediaType.get("application/json"), jsonEjercicioEntrenamiento))
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

    @Override
    public List<EjercicioEntrenamiento> getAllEjercicioEntrenamiento() {
        ArrayList<EjercicioEntrenamiento> listaEjercicioEntrenamiento = new ArrayList<>();

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
                Type listType = new TypeToken<List<EjercicioEntrenamiento>>(){}.getType();
                listaEjercicioEntrenamiento = gson.fromJson(jsonArray, listType);
            } else {
                System.out.println("Error al obtener los ejerciciosEntrenamientos: " + response.code());
            }
        } catch (IOException e) {
            System.out.println("Error de conexión: " + e.getMessage());
        }

        return listaEjercicioEntrenamiento;
    }
    
    public List<EjercicioEntrenamiento> getEjerciciosPorEntrenamiento(String entrenamientoId) {
        List<EjercicioEntrenamiento> relacionados = new ArrayList<>();

        try {
            HttpUrl.Builder urlBuilder = HttpUrl.parse(API_URL).newBuilder();
            JsonObject where = new JsonObject();
            where.addProperty("entrenamientoId", entrenamientoId);
            urlBuilder.addQueryParameter("where", where.toString());

            Request request = new Request.Builder()
                .url(urlBuilder.build().toString())
                .addHeader("X-Parse-Application-Id", APPLICATION_ID)
                .addHeader("X-Parse-REST-API-Key", REST_API_KEY)
                .get()
                .build();

            OkHttpClient client = new OkHttpClient();
            Response response = client.newCall(request).execute();

            if (response.isSuccessful()) {
                String responseJson = response.body().string();
                JsonObject jsonObject = new Gson().fromJson(responseJson, JsonObject.class);
                JsonArray jsonArray = jsonObject.getAsJsonArray("results");

                Type listType = new TypeToken<List<EjercicioEntrenamiento>>(){}.getType();
                relacionados = new Gson().fromJson(jsonArray, listType);
            } else {
                System.out.println("Error al obtener ejercicios por entrenamiento: " + response.code());
            }
        } catch (IOException e) {
            System.out.println("Error de conexión: " + e.getMessage());
        }

        return relacionados;
    }

    @Override
    public List<Ejercicio> getEjerciciosDeUnEntrenamiento(String entrenamientoId) {
        ListaEjercicioEntrenamiento relacionDao = new ListaEjercicioEntrenamiento(APPLICATION_ID, REST_API_KEY);
        ListaEjercicios ejercicioDao = new ListaEjercicios(APPLICATION_ID, REST_API_KEY);

        List<EjercicioEntrenamiento> relaciones = relacionDao.getEjerciciosPorEntrenamiento(entrenamientoId);
        List<Ejercicio> ejercicios = new ArrayList<>();

        for (EjercicioEntrenamiento rel : relaciones) {
            Ejercicio ejercicio = ejercicioDao.getEjercicioPorId(rel.getEjercicioId());
            if (ejercicio != null) {
                ejercicios.add(ejercicio);
            }
        }

        return ejercicios;
    }
    
}
