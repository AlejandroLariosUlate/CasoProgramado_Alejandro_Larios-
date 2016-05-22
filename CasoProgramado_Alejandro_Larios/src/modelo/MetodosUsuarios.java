/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Alejandro Larios Ulate
 */
public class MetodosUsuarios {

    private ArrayList<Usuario> arrayUsuario;
    String arregloInformacionConsultada[] = new String[6];
    ArchivosUsuario archivosUsuario;

    public MetodosUsuarios() {
        arrayUsuario = new ArrayList<Usuario>();
        archivosUsuario = new ArchivosUsuario();
        arrayUsuario = archivosUsuario.leerInformacionCompleta();
    }

    public void escribirEnArchivo() {
        archivosUsuario.crearArchivo();
        for (int contador = 0; contador < arrayUsuario.size(); contador++) {
            archivosUsuario.escribirInformacionEnElArchivo(arrayUsuario.get(contador));
        }
    }

    public void agregarUsuario(String informacion[]) {
        Usuario temporal = new Usuario(informacion[0], informacion[1], informacion[2], informacion[3], informacion[4], ""+informacion[5]);
        if (informacion[3].equals(informacion[4])) {
            arrayUsuario.add(temporal);
        }
    }
    
    public String existe(){
        String aux = "Usuario Agregado.";
        return aux;
    }
    
    public String noExiste(){
        String aux = "Contraseñas no coinciden";
        return aux;
    }


    public boolean consultarUsuario(String cedula) {
        boolean existe = false;

        for (int contador = 0; contador < arrayUsuario.size(); contador++) {
            if (arrayUsuario.get(contador).getCedula().equals(cedula)) {
                arregloInformacionConsultada[0] = arrayUsuario.get(contador).getCedula();
                arregloInformacionConsultada[1] = arrayUsuario.get(contador).getNombreCompleto();
                arregloInformacionConsultada[2] = arrayUsuario.get(contador).getNombreUsuario();
                arregloInformacionConsultada[3] = arrayUsuario.get(contador).getContrasena();
                arregloInformacionConsultada[4] = arrayUsuario.get(contador).getRepetirContrasena();
                arregloInformacionConsultada[5] = ""+arrayUsuario.get(contador).getTipo();
                existe = true;
            }
        }
        return existe;
    }

    public void modificarUsuario(String arreglo[]) {
        for (int contador = 0; contador < arrayUsuario.size(); contador++) {
            if (arrayUsuario.get(contador).getCedula().equals(arreglo[0])) {
                arrayUsuario.get(contador).setNombreCompleto(arreglo[1]);
                arrayUsuario.get(contador).setNombreUsuario(arreglo[2]);
                arrayUsuario.get(contador).setContrasena(arreglo[3]);
                arrayUsuario.get(contador).setRepetirContrasena(arreglo[4]);
                arrayUsuario.get(contador).setTipo(arreglo[5]);
            }
        }
    }

    public void eliminarUsuario(String arreglo[])/*throws Exception*/{
        //String aux;
        for (int contador = 0; contador < arrayUsuario.size(); contador++) {
            if (arrayUsuario.get(contador).getCedula().equals(arreglo[0])) {
                arrayUsuario.remove(contador);
                //archivosUsuario.eliminar(arrayUsuario.get(contador).getCedula());
            }
        }
    }
    
   

    public String[] getArregloInformacion() {
        return this.arregloInformacionConsultada;
    }
    
    public Usuario getArray(int i){
        return arrayUsuario.get(i);
    }
    
}
