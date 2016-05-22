/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author Alejandro Larios Ulate
 */
public class ArchivosEstudiantes {
    ObjectOutputStream archivoSalida;
    ObjectInputStream archivoEntrada;
    
    public ArchivosEstudiantes(){
        
    }
    
    public void crearArchivo(){
        try{
            archivoSalida = new ObjectOutputStream(new FileOutputStream("estudiantes.dato"));
            System.out.println("Archivo creado.");
        }catch(Exception e){
            System.out.println("Error al crear archivo."+e);
        }
    }
    
    public void escribirInformacionEnElArchivo(Estudiante estudiante){
        try{
            archivoSalida.writeObject(estudiante);
            System.out.println("Se escribió la información de forma correcta.");
        
        }catch(Exception e){
            System.out.println("Error al escribir el archivo: "+e);
        }
    }
    
    public String leerInformacion(){
        Estudiante estudiante=null;
        try{
            
            archivoEntrada = new ObjectInputStream(new FileInputStream("estudiantes.dato"));
            estudiante = (Estudiante)archivoEntrada.readObject();
            System.out.println("Se leyó la información de forma correcta.");
        
        }catch(Exception e){
            System.out.println("Error al leer el archivo: "+e);
        }
        return estudiante.getInformacion();
    }
    
    public ArrayList<Estudiante>leerInformacionCompleta(){
        ArrayList<Estudiante> arrayEstudiante = new ArrayList<Estudiante>();
        try{
            archivoEntrada = new ObjectInputStream(new FileInputStream("estudiantes.dato"));
            while(true){
            arrayEstudiante.add((Estudiante)archivoEntrada.readObject());
            }
        }catch(Exception e){
            System.out.println("Fin del archivo"+e);
        }
        return arrayEstudiante;   
    }
}