/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package transportate.dao;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.File;
import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import transportate.modelo.FileResource;

/**
 *
 * @author ruben
 */
public class ListaImagenEjercicio implements ListaImagenEjercicioInterface{
    
    private String APPLICATION_ID;
    private String REST_API_KEY;
    private static final String API_URL = "https://parseapi.back4app.com/files/";
    
    private final OkHttpClient client = new OkHttpClient();
    private final Gson gson = new Gson();

    public ListaImagenEjercicio(String appID, String restAPIKey) {
        this.APPLICATION_ID = appID;
        this.REST_API_KEY = restAPIKey;
    }
    
    @Override
    public FileResource upload(File imageFile, String nameFileUpload) {
        FileResource fileResource = null;
        OkHttpClient client = new OkHttpClient();
        Gson gson = new Gson();

        RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpeg"), imageFile);

        // Construir la solicitud POST para cargar el archivo
        Request request = new Request.Builder()
            .url(API_URL + nameFileUpload)
            .addHeader("X-Parse-Application-Id", APPLICATION_ID)
            .addHeader("X-Parse-REST-API-Key", REST_API_KEY)
            .addHeader("Content-Type", "image/jpeg")
            .post(requestBody)
            .build();

        try {
            Response response = client.newCall(request).execute();
            
            // Imprimir la respuesta para depurar
            if (!response.isSuccessful()) {
                // Si la respuesta no es exitosa, imprime el código y el mensaje
                System.err.println("Upload failed: " + response.code() + " - " + response.message());
                String responseBody = response.body() != null ? response.body().string() : "No response body";
                System.err.println("Response Body: " + responseBody);
            } else {
                // Si la carga es exitosa, deserializa el JSON y lo devuelve
                String jsonResponse = response.body().string();
                fileResource = gson.fromJson(jsonResponse, FileResource.class);
                System.out.println("File uploaded successfully: " + fileResource.url);
            }
        } catch (IOException e) {
            System.err.println("IOException: " + e.getMessage());
        }

        return fileResource;
    }
}
