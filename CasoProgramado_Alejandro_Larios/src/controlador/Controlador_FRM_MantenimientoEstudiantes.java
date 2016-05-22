/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import BaseDatos.ConexionBD;
import XML.Metodos_XML_Estudiantes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.MetodosEstudiantes;
import vista.FRM_Login;
import vista.FRM_MantenimientoEstudiantes;

/**
 *
 * @author Alejandro Larios Ulate
 */
public class Controlador_FRM_MantenimientoEstudiantes implements ActionListener {

    public MetodosEstudiantes metodosEstudiantes;
    FRM_MantenimientoEstudiantes frm_MantenimientoEstudiantes;
    public ConexionBD conexion;
    FRM_Login frm_login;
    Metodos_XML_Estudiantes metodos_xml;

    public Controlador_FRM_MantenimientoEstudiantes(FRM_MantenimientoEstudiantes frm_MantenimientoEstudiantes, FRM_Login frm_login) {

        conexion = new ConexionBD();
        metodosEstudiantes = new MetodosEstudiantes();
        this.frm_MantenimientoEstudiantes = frm_MantenimientoEstudiantes;
        this.frm_login = frm_login;
        metodos_xml = new Metodos_XML_Estudiantes(frm_MantenimientoEstudiantes);

    }

    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("Agregar")) {
            if (frm_login.botones().equals("ArchivosPlanos")) {
                metodosEstudiantes.agregarEstudiante(frm_MantenimientoEstudiantes.devolverInformacion());
                frm_MantenimientoEstudiantes.mostrarMensaje("El estudiante fue registrado de forma correcta");
                metodosEstudiantes.escribirEnArchivo();
            } else if (frm_login.botones().equals("BaseDeDatos")) {
                conexion.registrarEstudiante(frm_MantenimientoEstudiantes.devolverInformacion());
                frm_MantenimientoEstudiantes.mostrarMensaje("El estudiante fue registrado de forma correcta");

            } else {
                metodos_xml.agregarEstudiante(frm_MantenimientoEstudiantes.devolverInformacion());
                frm_MantenimientoEstudiantes.mostrarMensaje("Información agregada al archivo XML de forma correcta.");

            }
        }

        /////////////////////////////////////////////////////////////////////////////////////////////////////////////
        if ((e.getActionCommand().equals("Consultar") || e.getActionCommand().equals("Consulta_Rapida"))) {
            if (frm_login.botones().equals("ArchivosPlanos")) {
                buscarArchivosPlanos();
            } else if (frm_login.botones().equals("BaseDeDatos")) {
                buscarBaseDeDatos();
            } else {
                buscarXML();
            }
        }

        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        if (e.getActionCommand().equals("Modificar")) {
            if (frm_login.botones().equals("ArchivosPlanos")) {
                metodosEstudiantes.modificarEstudiante(frm_MantenimientoEstudiantes.devolverInformacion());
                frm_MantenimientoEstudiantes.mostrarMensaje("El estudiante fue modificado de forma correcta.");
                metodosEstudiantes.escribirEnArchivo();
            } else if (frm_login.botones().equals("BaseDeDatos")) {
                conexion.modificarEstudiante(frm_MantenimientoEstudiantes.devolverInformacion());
                frm_MantenimientoEstudiantes.mostrarMensaje("El estudiante fue modificado de forma correcta.");
            } else {
                metodos_xml.modificarEstudiante(frm_MantenimientoEstudiantes.devolverInformacion());
                frm_MantenimientoEstudiantes.mostrarMensaje("El estudiante fue modificado de forma correcta.");

            }
        }

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        if (e.getActionCommand().equals("Eliminar")) {
            if (frm_login.botones().equals("ArchivosPlanos")) {
                metodosEstudiantes.eliminarEstudiante(frm_MantenimientoEstudiantes.devolverInformacion());
                frm_MantenimientoEstudiantes.mostrarMensaje("El estudiante fue eliminado de forma correcta.");
                metodosEstudiantes.escribirEnArchivo();
            } else if (frm_login.botones().equals("BaseDeDatos")) {
                conexion.eliminarEstudiante(frm_MantenimientoEstudiantes.devolverCedula());
                frm_MantenimientoEstudiantes.mostrarMensaje("El estudiante fue eliminado de forma correcta.");

            } else {
                metodos_xml.eliminarEstudiante(frm_MantenimientoEstudiantes.devolverInformacion());
                frm_MantenimientoEstudiantes.mostrarMensaje("El estudiante fue eliminado de forma correcta.");

            }
        }

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////
        if (e.getActionCommand().equals("Limpiar")) {
            frm_MantenimientoEstudiantes.limpiar();
            //frm_MantenimientoEstudiantes.habilitarAgregar();

        }
    }

    public void buscarArchivosPlanos() {
        if (metodosEstudiantes.consultarEstudiante(frm_MantenimientoEstudiantes.devolverCedula())) {
            frm_MantenimientoEstudiantes.mostrarInformacionBD_AP(metodosEstudiantes.getArregloInformacion());

        } else {
            frm_MantenimientoEstudiantes.mostrarMensaje("La cédula buscada no se encuentra.");

        }
    }

    public void buscarBaseDeDatos() {
        if (conexion.consultarEstudiante(frm_MantenimientoEstudiantes.devolverCedula())) {
            frm_MantenimientoEstudiantes.mostrarInformacionBD_AP(conexion.arregloInformacionEstudiante);

        } else {
            frm_MantenimientoEstudiantes.mostrarMensaje("La cédula buscada no se encuentra.");

        }
    }

    public void buscarXML() {
        if (metodos_xml.consultarEstudiante(frm_MantenimientoEstudiantes.devolverCedula())) {
            frm_MantenimientoEstudiantes.mostrarInformacionXML(metodos_xml.getArregloInformacion());

            frm_MantenimientoEstudiantes.mostrarMensaje("Información encontrada con la cédula : " + frm_MantenimientoEstudiantes.devolverCedula());
        } else {
            frm_MantenimientoEstudiantes.mostrarMensaje("No se encontró información con la cédula: " + frm_MantenimientoEstudiantes.devolverCedula());

        }
        //frm_MantenimientoEstudiantes.deshabilitarCedula();
    }
}
