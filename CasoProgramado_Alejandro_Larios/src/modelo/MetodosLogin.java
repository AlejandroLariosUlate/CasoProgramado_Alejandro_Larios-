/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author Alejandro Larios Ulate
 */
public class MetodosLogin {

    private ArrayList<Usuario> arrayUsuario;
    String arregloInformacionConsultada[] = new String[2];
    ArchivosUsuario archivosUsuario;
    private boolean existe = false;

    public MetodosLogin(ArchivosUsuario archivosUsuario) {
        arrayUsuario = new ArrayList<Usuario>();
        this.archivosUsuario = archivosUsuario;
    }

    public boolean isExiste() {
        return existe;
    }

    public String comparar(String usuario, String contrasena) {
        arrayUsuario = archivosUsuario.leerInformacionCompleta();
        String mensaje = "";
        for (int controlador = 0; controlador < arrayUsuario.size(); controlador++) {
            if (!usuario.isEmpty()) {
                if (arrayUsuario.get(controlador).getNombreUsuario().equals(usuario)) {
                    if (arrayUsuario.get(controlador).getContrasena().equals(contrasena)) {
                        mensaje ="Usuario encontrado.";
                        existe = true;
                    }else{
                        mensaje ="Contraseña incorrecta.";
                        existe = false;
                    }
                }else{
                        mensaje ="Usuario incorrecto.";
                        existe = false;
                    }
            }else{
                mensaje = "No se ha ingresado un usuario.";
                existe = false;
            }
        }
        return mensaje;
    }
    
    public boolean existeArchivo(){
        boolean existe = false;
        File archivo = new File("usuarios.dato");
        if(archivo.exists()){
            System.out.println("Existe archivo usuario");
            existe = true;
        }else{
            System.out.println("No existe archivo usuario");
        }
        return existe;
    }
}