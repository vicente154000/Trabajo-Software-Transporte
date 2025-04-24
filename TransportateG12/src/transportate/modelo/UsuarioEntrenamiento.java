/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package transportate.modelo;

/**
 *
 * @author ruben
 */
public class UsuarioEntrenamiento {
    private String objectId;
    private String idUsuario;
    private String idEntrenamiento;
    
    public UsuarioEntrenamiento(String idUsuario, String idEntrenamiento){
        this.idEntrenamiento = idEntrenamiento;
        this.idUsuario = idUsuario;
    }
    
    public String getId() {
        return objectId;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public String getIdEntrenamiento() {
        return idEntrenamiento;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setIdEntrenamiento(String idEntrenamiento) {
        this.idEntrenamiento = idEntrenamiento;
    }
}
