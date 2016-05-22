/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import BaseDatos.ConexionBD;
import XML.Metodos_XML_Usuarios;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.MetodosUsuarios;
import vista.FRM_Login;
import vista.FRM_MantenimientoUsuarios;

/**
 *
 * @author Alejandro Larios Ulate
 */
public class Controlador_FRM_Usuario implements ActionListener {

    public ConexionBD conexion;
    boolean creado = false;

    MetodosUsuarios metodos;
    Metodos_XML_Usuarios metodos_xml;
    FRM_MantenimientoUsuarios frm_mantenimientoUsuarios;
    FRM_Login frm_login;

    public Controlador_FRM_Usuario(FRM_MantenimientoUsuarios frm_mantenimientoUsuarios, FRM_Login frm_login) {
        metodos = new MetodosUsuarios();
        metodos_xml=new Metodos_XML_Usuarios(frm_mantenimientoUsuarios);
        this.frm_login = frm_login;
        this.frm_mantenimientoUsuarios = frm_mantenimientoUsuarios;

        conexion = new ConexionBD();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Volver")) {
            frm_mantenimientoUsuarios.dispose();
            frm_login.setVisible(true);
        }
        if (e.getActionCommand().equals("Agregar") && frm_login.botones().equals("ArchivosPlanos")) {
            if (frm_mantenimientoUsuarios.confirmarContrasenna()) {
                metodos.agregarUsuario(frm_mantenimientoUsuarios.devolverInformacion());
                frm_mantenimientoUsuarios.mostrarMensaje(metodos.existe());
                metodos.escribirEnArchivo();
                System.out.println("Entró a archivos planos.");
            } else {
                frm_mantenimientoUsuarios.mostrarMensaje(metodos.noExiste());
                frm_mantenimientoUsuarios.limpiar2();
            }
        }
        if (e.getActionCommand().equalsIgnoreCase("Agregar") && frm_login.botones().equals("BaseDeDatos")) {
            if (frm_mantenimientoUsuarios.confirmarContrasenna()) {
                conexion.registrarUsuarios(frm_mantenimientoUsuarios.devolverInformacion());
                conexion.registrarLogin(frm_login.devolverInformacionLogin());
                frm_mantenimientoUsuarios.mostrarMensaje("Usuario agregado");
                System.out.println("Entró a BaseDeDatos.");
            } else {
                frm_mantenimientoUsuarios.mostrarMensaje("Contraseñas no coinciden");
                frm_mantenimientoUsuarios.limpiar2();
            }
        }
        
        if (e.getActionCommand().equalsIgnoreCase("Agregar") && frm_login.botones().equals("XML")) {
             if(frm_mantenimientoUsuarios.confirmarContrasenna())
            {
                metodos_xml.agregarUsuario(frm_mantenimientoUsuarios.devolverInformacion());
                frm_mantenimientoUsuarios.mostrarMensaje("El usuario fue agregado de forma correcta");
                
            }
            else
            {
                frm_mantenimientoUsuarios.mostrarMensaje("Contaseñas distintas\n\nVuelva a repetir la contraseña");
                frm_mantenimientoUsuarios.limpiar2();
            }
        }
        
        
        
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        if (e.getActionCommand().equals("Consultar") && frm_login.botones().equals("ArchivosPlanos")) {
            buscarArchivosPlanos();
            System.out.println("Entró a archivos planos.");
        }
        if (e.getActionCommand().equalsIgnoreCase("Consultar") && frm_login.botones().equals("BaseDeDatos")) {
            buscarBaseDeDatos();
            System.out.println("Entró a BaseDeDatos.");
        }
        if (e.getActionCommand().equalsIgnoreCase("Consultar") && frm_login.botones().equals("XML")) {
            buscarXml();
            System.out.println("Entró a XML.");
        }
        
        /////////////////////////////////////////////////////////////////////////////////////////////////////////

        if (e.getActionCommand().equals("Modificar") && frm_login.botones().equals("ArchivosPlanos")) {
            metodos.modificarUsuario(frm_mantenimientoUsuarios.devolverInformacion());
            frm_mantenimientoUsuarios.mostrarMensaje("El estudiante fue modificado de forma correcta.");
            System.out.println("Entró a archivos planos.");
        }

        if (e.getActionCommand().equalsIgnoreCase("Modificar") && frm_login.botones().equals("BaseDeDatos")) {
            if (frm_mantenimientoUsuarios.confirmarContrasenna()) {
                conexion.modificarUsuario(frm_mantenimientoUsuarios.devolverInformacion());
                frm_mantenimientoUsuarios.mostrarMensaje("El estudiante fue modificado de forma correcta.");
                System.out.println("Entró a BaseDeDatos.");
            } else {
                JOptionPane.showMessageDialog(frm_mantenimientoUsuarios, "Contraseñas no coinciden");
            }
        }
        
         if (e.getActionCommand().equalsIgnoreCase("Modificar") && frm_login.botones().equals("XML")){
              if(frm_mantenimientoUsuarios.confirmarContrasenna())
            {
                metodos_xml.modificarUsuario(frm_mantenimientoUsuarios.devolverInformacion());
                frm_mantenimientoUsuarios.mostrarMensaje("El usuario fue modificado de forma correcta");
                System.out.println("Entró a XML");          
            }
            else
            {
                frm_mantenimientoUsuarios.mostrarMensaje("Contaseñas distintas\n\nVuelva a repetir la contraseña");
                frm_mantenimientoUsuarios.limpiar2();
            }
         }
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////

        if (e.getActionCommand().equals("Eliminar") && frm_login.botones().equals("ArchivosPlanos")) {
            metodos.eliminarUsuario(frm_mantenimientoUsuarios.devolverInformacion());
            frm_mantenimientoUsuarios.mostrarMensaje("El estudiante fue eliminado de forma correcta.");
            System.out.println("Entró a archivos planos.");
        }

        if (e.getActionCommand().equalsIgnoreCase("Eliminar") && frm_login.botones().equals("BaseDeDatos")) {
            if (frm_mantenimientoUsuarios.confirmarContrasenna()) {
                conexion.eliminarUsuario(frm_mantenimientoUsuarios.devolverNombreUsuario());
                frm_mantenimientoUsuarios.mostrarMensaje("El estudiante fue eliminado de forma correcta.");
                System.out.println("Entró a BaseDeDatos.");
            } else {
                JOptionPane.showMessageDialog(frm_mantenimientoUsuarios, "Contraseñas no coinciden");
            }
        }
        
        if (e.getActionCommand().equalsIgnoreCase("Eliminar") && frm_login.botones().equals("XML")) {
             metodos_xml.eliminarUsuario(frm_mantenimientoUsuarios.devolverInformacion());
            frm_mantenimientoUsuarios.mostrarMensaje("El usuario fue eliminado de forma correcta");
            System.out.println("Entró a XML"); 
        }
        
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        if (e.getActionCommand().equals("Limpiar") && frm_login.botones().equals("ArchivosPlanos")) {
            frm_mantenimientoUsuarios.inicializarGUI();
            System.out.println("Entró a ArchivosPlanos.");
        }
        if (e.getActionCommand().equals("Limpiar") && frm_login.botones().equals("BaseDeDatos")) {
            frm_mantenimientoUsuarios.inicializarGUI();
            System.out.println("Entró a BaseDeDatos.");
        }
        
        if (e.getActionCommand().equals("Limpiar") && frm_login.botones().equals("XML")) {
            frm_mantenimientoUsuarios.inicializarGUI();
            System.out.println("Entró a XML.");
        }
    }//FIN DEL ACTION PERFORMED

    public void buscarArchivosPlanos() {
        if (metodos.consultarUsuario(frm_mantenimientoUsuarios.devolverCedula())) {
            frm_mantenimientoUsuarios.mostrarInformacionXML(metodos.getArregloInformacion());
            
            
        } else {
            frm_mantenimientoUsuarios.mostrarMensaje("La cédula buscada no se encuentra.");
            frm_mantenimientoUsuarios.usuario(true);
        }
    }

    public void buscarBaseDeDatos() {
        if (conexion.consultarUsuario(frm_mantenimientoUsuarios.devolverCedula())) {
            frm_mantenimientoUsuarios.mostrarInformacionXML(conexion.arregloInformacionUsuario);
            
        } else {
            frm_mantenimientoUsuarios.mostrarMensaje("La cédula buscada no se encuentra.");
            
            
        }
    }
    
    public void buscarXml()
    {
        if(metodos_xml.consultarUsuario(frm_mantenimientoUsuarios.devolverCedula()))
            {
                frm_mantenimientoUsuarios.mostrarInformacionXML(metodos_xml.arregloInformacion);
                
            }
            else
            {
                frm_mantenimientoUsuarios.mostrarMensaje("La cédula buscada no se encuentra");
                
            }
    }

    public boolean iniciarSesion(String arreglo[]) {
        return conexion.iniciarSesion(arreglo);
    }
}
