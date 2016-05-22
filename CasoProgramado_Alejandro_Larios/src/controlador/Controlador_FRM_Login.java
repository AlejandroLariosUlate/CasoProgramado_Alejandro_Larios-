/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.ArchivosUsuario;
import modelo.MetodosLogin;
import vista.FRM_Login;
import vista.FRM_MantenimientoUsuarios;
import vista.FRM_VentanaPrincipal;

/**
 *
 * @author Alejandro Larios Ulate
 */
public class Controlador_FRM_Login implements ActionListener {

    FRM_Login frm_login;
    MetodosLogin metodosLogin;
    ArchivosUsuario archivosUsuario;
    FRM_VentanaPrincipal frm_ventanaPrincipal;
    FRM_MantenimientoUsuarios frm_mantenimientoUsuarios;

    public Controlador_FRM_Login(FRM_Login frm_login, ArchivosUsuario archivosUsuario, FRM_VentanaPrincipal frm_ventanaPrincipal, FRM_MantenimientoUsuarios frm_mantenimientoUsuarios) {
        this.frm_login = frm_login;
        this.archivosUsuario = archivosUsuario;
        metodosLogin = new MetodosLogin(archivosUsuario);
        metodosLogin.existeArchivo();
        this.frm_ventanaPrincipal = frm_ventanaPrincipal;
        this.frm_mantenimientoUsuarios = frm_mantenimientoUsuarios;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Aceptar") && frm_login.botones().equals("ArchivosPlanos")) {
            frm_login.mensaje(metodosLogin.comparar(frm_login.getJtNombreUsuario(), frm_login.getJpfContrasena()));
            if (metodosLogin.isExiste()) {
                frm_ventanaPrincipal.setVisible(true);
                frm_login.dispose();
                System.out.println("Entró a AP LOG");
            } else {
                frm_login.limpiar();
                frm_login.dispose();
            }
        }
        if (e.getActionCommand().equalsIgnoreCase("Aceptar") && frm_login.botones().equals("BaseDeDatos")) {
            iniciarSesion();
            System.out.println("Entró a BD LOG");
        }

        if (e.getActionCommand().equalsIgnoreCase("Aceptar") && frm_login.botones().equals("XML")) {
            iniciarSesionXML();
            System.out.println("Entró a XML LOG");
        }

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        if (e.getActionCommand().equals("Registro")) {
            frm_mantenimientoUsuarios.setVisible(true);
            frm_login.dispose();
        }
        if (e.getActionCommand().equals("Cancelar")) {
            System.exit(0);
        }
    }

    public void mostrarIniciarSesion() {

        if (frm_mantenimientoUsuarios.controlador_FRM_Usuario.conexion.consultarUsuarios()) {
            frm_mantenimientoUsuarios.mostrarMensaje("No existen ningún Usuario, será redirigido a la ventana Usuarios");
            frm_ventanaPrincipal.setVisible(true);
            frm_mantenimientoUsuarios.setVisible(true);
        } else {
            frm_login.setVisible(true);
        }
    }

    public void iniciarSesion() {
        if (frm_mantenimientoUsuarios.controlador_FRM_Usuario.conexion.iniciarSesion(frm_login.devolverInformacionDeInicioDeSesion())) {
            frm_ventanaPrincipal.setVisible(true);
            if (frm_mantenimientoUsuarios.controlador_FRM_Usuario.conexion.devolverTipo().equalsIgnoreCase("Usuario")) {
                frm_ventanaPrincipal.opcionesUsuario();
            }
            frm_login.hide();
        } else {
            frm_mantenimientoUsuarios.mostrarMensaje("Contraseña o usuario incorrecto");
        }
    }

    public void iniciarSesionXML() {
        if (!frm_mantenimientoUsuarios.controlador_FRM_Usuario.metodos_xml.consultarUsuarioRegistrado(frm_login.devolverInformacionDeInicioDeSesion())) {
            frm_ventanaPrincipal.setVisible(true);
            frm_login.hide();
        } else {
            frm_mantenimientoUsuarios.mostrarMensaje("Contraseña o usuario incorrecto");
        }
    }
}
