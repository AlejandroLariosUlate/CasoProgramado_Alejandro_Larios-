/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author Alejandro Larios Ulate
 */
public class ArchivosUsuario {

    ObjectOutputStream archivoSalida;
    ObjectInputStream archivoEntrada;

    public ArchivosUsuario() {

    }

    public void crearArchivo() {
        try {
            archivoSalida = new ObjectOutputStream(new FileOutputStream("usuarios.dato"));
            System.out.println("Archivo creado.");
        } catch (Exception e) {
            System.out.println("Error al crear archivo." + e);
        }
    }

    public void escribirInformacionEnElArchivo(Usuario usuario) {
        try {
            archivoSalida.writeObject(usuario);
            System.out.println("Se escribió la información de forma correcta.");

        } catch (Exception e) {
            System.out.println("Error al escribir el archivo: " + e);
        }
    }

    public String leerInformacion() {
        Usuario usuario = null;
        try {
            archivoEntrada = new ObjectInputStream(new FileInputStream("usuarios.dato"));
            usuario = (Usuario) archivoEntrada.readObject();
            System.out.println("Se leyó la información de forma correcta.");

        } catch (Exception e) {
            System.out.println("Error al leer el archivo: " + e);
        }
        return usuario.getInformacion();
    }

    public void eliminar(String cedula) throws Exception {
        Usuario usuario = null;
        try {
            archivoEntrada = new ObjectInputStream(new FileInputStream("usuarios.dato"));
            if (usuario.getCedula().equals(cedula)) {
                File archivo = new File(cedula);
                FileReader fr=new FileReader(archivo);
                if(fr!=null){
                    fr.close();
                }
                archivo.delete();
            }
        }catch(Exception e){
            System.out.println("Error al eliminar el archivo: " + e);
        }
    }

    public ArrayList<Usuario> leerInformacionCompleta() {
        ArrayList<Usuario> arrayUsuarios = new ArrayList<Usuario>();
        try {
            archivoEntrada = new ObjectInputStream(new FileInputStream("usuarios.dato"));
            while (true) {
                arrayUsuarios.add((Usuario) archivoEntrada.readObject());
            }
        } catch (Exception e) {
            System.out.println("Fin del archivo" + e);
        }
        return arrayUsuarios;
    }
}
