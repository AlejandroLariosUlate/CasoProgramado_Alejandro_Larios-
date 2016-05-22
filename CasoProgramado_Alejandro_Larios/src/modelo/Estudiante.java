/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;

/**
 *
 * @author Alejandro Larios Ulate
 */
public class Estudiante implements Serializable {
    
    private String cedula;
    private String nombreCompleto;
    private String direccion;
    
    public Estudiante(String cedula, String nombreCompleto, String direccion)
    {
        this.cedula=cedula;
        this.nombreCompleto=nombreCompleto;
        this.direccion=direccion;
    }

    /**
     * @return the cedula
     */
    public String getCedula() {
        return cedula;
    }

    /**
     * @param cedula the cedula to set
     */
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    /**
     * @return the nombrecompleto
     */
    public String getNombrecompleto() {
        return nombreCompleto;
    }

    /**
     * @param nombrecompleto the nombrecompleto to set
     */
    public void setNombrecompleto(String nombrecompleto) {
        this.nombreCompleto = nombrecompleto;
    }

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    public String getInformacion()
    {
        return "Cédula: "+cedula+" Nombre: "+nombreCompleto+" Dirección: "+direccion;
    }   
}