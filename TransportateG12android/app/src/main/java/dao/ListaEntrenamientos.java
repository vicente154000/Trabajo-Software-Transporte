package dao;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import modelo.Entrenamiento;

/**
 *
 * @author ruben
 */
public class ListaEntrenamientos implements ListaEntrenamientosInterface{

    private String APPLICATION_ID;
    private String REST_API_KEY;
    private static final String API_URL = "https://parseapi.back4app.com/classes/entrenamiento";

    public ListaEntrenamientos(String appID, String restAPIKey) {
        this.APPLICATION_ID = appID;
        this.REST_API_KEY = restAPIKey;
    }
    
    @Override
    public String addEntrenamiento(Entrenamiento entrenamiento) {
        String objectId="";
        Gson gson = new Gson();
        String jsonEntrenamiento = gson.toJson(entrenamiento);
        try {
            Request request = new Request.Builder()
                .url(API_URL)
                .addHeader("X-Parse-Application-Id", APPLICATION_ID)
                .addHeader("X-Parse-REST-API-Key", REST_API_KEY)
                .post(RequestBody.create(MediaType.get("application/json"),jsonEntrenamiento))
                .build();
            OkHttpClient client = new OkHttpClient();
            Response response = client.newCall(request).execute();
            if(response.isSuccessful()) {
                Entrenamiento newEntrenamiento = gson.fromJson(response.body().string(), Entrenamiento.class);
                objectId = newEntrenamiento.getId();
               }
        }
        catch (IOException e) {
           System.out.println(e.getMessage());
        }
        return objectId;
    }

    @Override
    public List<Entrenamiento> getListaEntrenamientos() {
        ArrayList<Entrenamiento> listaEntrenamientos = new ArrayList<>();

        try {
            // Realizamos una solicitud GET para obtener los ejercicios
            Request request = new Request.Builder()
                .url(API_URL) // Asegúrate de que la URL es la correcta para obtener la lista de ejercicios
                .addHeader("X-Parse-Application-Id", APPLICATION_ID)
                .addHeader("X-Parse-REST-API-Key", REST_API_KEY)
                .get() // Usamos .get() para realizar una solicitud GET
                .build();

            OkHttpClient client = new OkHttpClient();
            Response response = client.newCall(request).execute();

            if (response.isSuccessful()) {
                String responseJson = response.body().string();
                System.out.println(responseJson);
                JsonObject jsonObject = new Gson().fromJson(responseJson, JsonObject.class);
                JsonArray jsonArray = jsonObject.getAsJsonArray("results");
                Gson gson = new Gson();
                Type listType = new TypeToken<List<Entrenamiento>>(){}.getType();
                listaEntrenamientos = gson.fromJson(jsonArray, listType);
            } else {
                System.out.println("Error al obtener los entrenamientos: " + response.code());
            }
        } catch (IOException e) {
            System.out.println("Error de conexión: " + e.getMessage());
        }

        return listaEntrenamientos;
    }

    @Override
    public List<Entrenamiento> getEntrenamientosPorIntensidad(String tipoIntensidad) {
        ArrayList<Entrenamiento> listaEntrenamientos = new ArrayList<>();
        String filtroJson = "";

        switch (tipoIntensidad.toLowerCase()) {
            case "baja":
                filtroJson = "{\"intensidad\":{\"$lt\":1.5}}";
                break;
            case "media":
                filtroJson = "{\"intensidad\":{\"$gte\":1.5,\"$lt\":3.5}}";
                break;
            case "alta":
                filtroJson = "{\"intensidad\":{\"$gte\":4}}";
                break;
            default:
                return listaEntrenamientos;
        }

        try {
            String encodedFiltroJson = URLEncoder.encode(filtroJson, "UTF-8");
            String url = API_URL + "?where=" + encodedFiltroJson;

            Request request = new Request.Builder()
                    .url(url)
                    .addHeader("X-Parse-Application-Id", APPLICATION_ID)
                    .addHeader("X-Parse-REST-API-Key", REST_API_KEY)
                    .get()
                    .build();

            OkHttpClient client = new OkHttpClient();
            Response response = client.newCall(request).execute();

            if (response.isSuccessful()) {
                String responseJson = response.body().string();
                System.out.println("Response JSON: " + responseJson);

                JsonObject jsonObject = new Gson().fromJson(responseJson, JsonObject.class);
                JsonArray jsonArray = jsonObject.getAsJsonArray("results");

                Type listType = new TypeToken<List<Entrenamiento>>(){}.getType();
                listaEntrenamientos = new Gson().fromJson(jsonArray, listType);

            } else {
                System.out.println("Error en la respuesta: " + response.code());
            }

        } catch (IOException e) {
            System.out.println("Error al filtrar entrenamientos: " + e.getMessage());
        }

        return listaEntrenamientos;
    }


    @Override
    public void deleteEntrenamiento(Entrenamiento entrenamiento) {
        String objectId = entrenamiento.getId();
    
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
                System.out.println("Entrenamiento eliminado con éxito.");
            } else {
                System.out.println("Error al eliminar el entrenamiento: " + response.code());
            }
        } catch (IOException e) {
            System.out.println("Error de conexión: " + e.getMessage());
        }
    }

    @Override
    public void updateEntrenamiento(Entrenamiento entrenamiento) {
        Gson gson = new Gson();
        String jsonEntrenamiento= gson.toJson(entrenamiento);

        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                .url(API_URL + "/" + entrenamiento.getId())
                .addHeader("X-Parse-Application-Id", APPLICATION_ID)
                .addHeader("X-Parse-REST-API-Key", REST_API_KEY)
                .addHeader("Content-Type", "application/json")
                .put(RequestBody.create(MediaType.get("application/json"), jsonEntrenamiento))
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
