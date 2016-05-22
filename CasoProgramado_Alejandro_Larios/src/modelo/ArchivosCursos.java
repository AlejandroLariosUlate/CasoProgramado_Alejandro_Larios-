/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author Alejandro Larios Ulate
 */
public class ArchivosCursos {

    ObjectOutputStream archivoSalida;
    ObjectInputStream archivoEntrada;

    public ArchivosCursos() {

    }

    public void crearArchivo() {
        try {
            archivoSalida = new ObjectOutputStream(new FileOutputStream("cursos.dato"));
            System.out.println("Curso creado.");
        } catch (Exception e) {
            System.out.println("Error a crear curso " + e);
        }
    }

    public void escribirInformacionEnElArchivo(Cursos curso) {
        try {
            archivoSalida.writeObject(curso);
            System.out.println("Se escribió la información del curso de forma correcta.");

        } catch (Exception e) {
            System.out.println("Error al escribir el archivo: " + e);
        }
    }

    public String leerInformacion() {
        Cursos curso = null;
        try {

            archivoEntrada = new ObjectInputStream(new FileInputStream("cursos.dato"));
            curso = (Cursos) archivoEntrada.readObject();
            System.out.println("Se leyó la información de forma correcta.");

        } catch (Exception e) {
            System.out.println("Error al leer el archivo: " + e);
        }
        return curso.getInformacion();
    }
    
    public ArrayList<Cursos>leerInformacionCompleta(){
        ArrayList<Cursos> arrayCursos = new ArrayList<Cursos>();
        try{
            archivoEntrada = new ObjectInputStream(new FileInputStream("cursos.dato"));
            while(true){
            arrayCursos.add((Cursos)archivoEntrada.readObject());
            }
        }catch(Exception e){
            System.out.println("Fin del archivo "+e);
        }
        return arrayCursos;   
    }
}