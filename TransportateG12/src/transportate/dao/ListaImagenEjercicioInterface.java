/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package transportate.dao;

import java.io.File;
import transportate.modelo.FileResource;

/**
 *
 * @author ruben
 */
public interface ListaImagenEjercicioInterface {
    public FileResource upload(File imageFile, String nameFileUpload);
}
