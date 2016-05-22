/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;

/**
 *
 * @author Alejandro Larios Ulate
 */
public class MetodosEstudiantes {

    private ArrayList<Estudiante> arrayEstudiantes;
    String arregloInformacionConsultada[] = new String[2];
    ArchivosEstudiantes archivosEstudiantes;

    public MetodosEstudiantes() {
        arrayEstudiantes = new ArrayList<Estudiante>();
        archivosEstudiantes = new ArchivosEstudiantes();
        arrayEstudiantes = archivosEstudiantes.leerInformacionCompleta();
    }

    public void escribirEnArchivo() {
        archivosEstudiantes.crearArchivo();
        for (int contador = 0; contador < arrayEstudiantes.size(); contador++) {
            archivosEstudiantes.escribirInformacionEnElArchivo(arrayEstudiantes.get(contador));
        }
    }

    public void agregarEstudiante(String informacion[]) {
        Estudiante temporal = new Estudiante(informacion[0], informacion[1], informacion[2]);
        arrayEstudiantes.add(temporal);
    }

    public void mostrarInformacion() {
        for (int contador = 0; contador < arrayEstudiantes.size(); contador++) {
            System.out.println(arrayEstudiantes.get(contador).getInformacion());
        }
    }

    public boolean consultarEstudiante(String cedula) {
        boolean existe = false;

        for (int contador = 0; contador < arrayEstudiantes.size(); contador++) {
            if (arrayEstudiantes.get(contador).getCedula().equals(cedula)) {
                arregloInformacionConsultada[0] = arrayEstudiantes.get(contador).getNombrecompleto();
                arregloInformacionConsultada[1] = arrayEstudiantes.get(contador).getDireccion();
                existe = true;
            }
        }
        return existe;
    }

    public void modificarEstudiante(String arreglo[]) {
        for (int contador = 0; contador < arrayEstudiantes.size(); contador++) {
            if (arrayEstudiantes.get(contador).getCedula().equals(arreglo[0])) {
                arrayEstudiantes.get(contador).setNombrecompleto(arreglo[1]);
                arrayEstudiantes.get(contador).setDireccion(arreglo[2]);
            }
        }
    }

    public void eliminarEstudiante(String arreglo[]) {
        for (int contador = 0; contador < arrayEstudiantes.size(); contador++) {
            if (arrayEstudiantes.get(contador).getCedula().equals(arreglo[0])) {
                arrayEstudiantes.remove(contador);
            }
        }
    }

    public String[] getArregloInformacion() {
        return this.arregloInformacionConsultada;
    }
}
