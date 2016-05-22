/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import BaseDatos.ConexionBD;
import XML.Metodos_XML_Cursos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.MetodosCursos;
import vista.FRM_Login;
import vista.FRM_MantenimientoCursos;

/**
 *
 * @author Alejandro Larios Ulate
 */
public class Controlador_FRM_MantenimientoCursos implements ActionListener {

    FRM_MantenimientoCursos frm_mantenimientoCursos;
    public MetodosCursos metodosCursos;
    FRM_Login frm_login;
    Metodos_XML_Cursos metodos_xml;
    public ConexionBD conexion;

    public Controlador_FRM_MantenimientoCursos(FRM_MantenimientoCursos frm_mantenimientoCursos, FRM_Login frm_login) {

        conexion = new ConexionBD();
        this.frm_mantenimientoCursos = frm_mantenimientoCursos;
        metodosCursos = new MetodosCursos();
        this.frm_login = frm_login;
        metodos_xml = new Metodos_XML_Cursos(frm_mantenimientoCursos);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Agregar")) {

            if (frm_login.botones().equals("ArchivosPlanos")) {
                metodosCursos.agregarCurso(frm_mantenimientoCursos.devolverInformacion());
                frm_mantenimientoCursos.mostrarMensaje("El curso fue registrado de forma correcta");
                metodosCursos.escribirEnArchivo();
//                frm_mantenimientoCursos.deshabilitarCampos();
//                frm_mantenimientoCursos.habilitarBotonBuscar();
//                frm_mantenimientoCursos.limpiar();
            }

            if (frm_login.botones().equals("BaseDeDatos")) {
                conexion.registrarCurso(frm_mantenimientoCursos.devolverInformacion());
                frm_mantenimientoCursos.mostrarMensaje("El curso fue registrado de forma correcta");
//                frm_mantenimientoCursos.deshabilitarCampos();
//                frm_mantenimientoCursos.habilitarBotonBuscar();
//                frm_mantenimientoCursos.limpiar();
            }
            if (frm_login.botones().equals("XML")) {
                metodos_xml.agregarCurso(frm_mantenimientoCursos.devolverInformacion());
                frm_mantenimientoCursos.mostrarMensaje("El curso fue registrado de forma correcta");
//                frm_mantenimientoCursos.deshabilitarCampos();
//                frm_mantenimientoCursos.habilitarBotonBuscar();
//                frm_mantenimientoCursos.limpiar();
            }

        }

        /////////////////////////////////////////////////////////////////////////////////////////////////////
        if (e.getActionCommand().equals("Consultar") || e.getActionCommand().equals("ConsultaRapida")) {
            System.out.println("impresion para if de consultar");

            if (frm_login.botones().equals("ArchivosPlanos")) {
                buscarArchivosPlanos();
            }

            if (frm_login.botones().equals("BaseDeDatos")) {
                buscarBaseDeDatos();
            }
            if (frm_login.botones().equals("XML")) {
                buscarXml();
            }

        }

        ///////////////////////////////////////////////////////////////////////////////////////////////////////
        if (e.getActionCommand().equals("Modificar")) {
            if (frm_login.botones().equals("ArchivosPlanos")) {
                metodosCursos.modificarCurso(frm_mantenimientoCursos.devolverInformacion());
                frm_mantenimientoCursos.mostrarMensaje("El estudiante fue modificado de forma correcta.");

            } else if (frm_login.botones().equals("BaseDeDatos")) {
                conexion.modificarCurso(frm_mantenimientoCursos.devolverInformacion());
                frm_mantenimientoCursos.mostrarMensaje("El estudiante fue modificado de forma correcta.");

            } else {
                metodos_xml.modificarCurso(frm_mantenimientoCursos.devolverInformacion());
                frm_mantenimientoCursos.mostrarMensaje("El curso fue modificado de forma correcta.");

            }

        }

        //////////////////////////////////////////////////////////////////////////////////////////////////
        if (e.getActionCommand().equals("Eliminar")) {
            if (frm_login.botones().equals("ArchivosPlanos")) {
                metodosCursos.eliminarCurso(frm_mantenimientoCursos.devolverInformacion());
                frm_mantenimientoCursos.mostrarMensaje("El curso fue eliminado de forma correcta.");

            } else if (frm_login.botones().equals("BaseDeDatos")) {
                conexion.eliminarCurso(frm_mantenimientoCursos.devolverSigla());
                frm_mantenimientoCursos.mostrarMensaje("El estudiante fue eliminado de forma correcta.");

            } else {
                metodos_xml.eliminarCurso(frm_mantenimientoCursos.devolverInformacion());
                frm_mantenimientoCursos.mostrarMensaje("El curso fue eliminado de forma correcta.");

            }

        }

        /////////////////////////////////////////////////////////////////////////////////////////////////
        if (e.getActionCommand().equals("Limpiar")) {
            frm_mantenimientoCursos.limpiar();
            //frm_mantenimientoCursos.habilitarBotonBuscar();

        }

    }

    public void buscarArchivosPlanos() {
        if (metodosCursos.consultarCurso(frm_mantenimientoCursos.devolverSigla())) {
            frm_mantenimientoCursos.mostrarInformacionBD_AP(metodosCursos.getArregloInformacion());
//            frm_mantenimientoCursos.habilitarBotones();
//            frm_mantenimientoCursos.habilitarCampos();
        } else {
            frm_mantenimientoCursos.mostrarMensaje("La sigla buscada no se encuentra.");
//            frm_mantenimientoCursos.habilitarAgregar();
//            frm_mantenimientoCursos.habilitarCampos();
        }
    }

    public void buscarBaseDeDatos() {
        if (conexion.consultarCurso(frm_mantenimientoCursos.devolverSigla())) {
            frm_mantenimientoCursos.mostrarInformacionBD_AP(conexion.arregloInformacionCurso);
//            frm_mantenimientoCursos.habilitarBotones();
//            frm_mantenimientoCursos.habilitarCampos();
        } else {
            frm_mantenimientoCursos.mostrarMensaje("La sigla buscada no se encuentra.");
//            frm_mantenimientoCursos.habilitarAgregar();
//            frm_mantenimientoCursos.habilitarCampos();
        }
    }

    public void buscarXml() {
        if (metodos_xml.consultarCurso(frm_mantenimientoCursos.devolverSigla())) {
            frm_mantenimientoCursos.mostrarInformacionXML(metodos_xml.getArregloInformacion());
//            frm_mantenimientoCursos.habilitarBotones();
//            frm_mantenimientoCursos.habilitarCampos();
        } else {
            frm_mantenimientoCursos.mostrarMensaje("La sigla buscada no se encuentra.");
//            frm_mantenimientoCursos.habilitarAgregar();
//            frm_mantenimientoCursos.habilitarCampos();
        }
    }
}
