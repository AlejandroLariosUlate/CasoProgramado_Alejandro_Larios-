/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.FRM_Login;
import vista.FRM_MantenimientoCursos;
import vista.FRM_MantenimientoEstudiantes;
import vista.FRM_Matricula;

/**
 *
 * @author Alejandro Larios Ulate
 */


    
public class Controlador_FRM_VentanaPrincipal implements ActionListener{

    FRM_MantenimientoEstudiantes frm_MantenimientoEstufiantes;
    FRM_MantenimientoCursos frm_MantenimientoCursos;
    FRM_Matricula frm_Matricula;
    FRM_Login frm_login;
    
    public Controlador_FRM_VentanaPrincipal(FRM_Login frm_login)
    {
        this.frm_login = frm_login;
        frm_MantenimientoEstufiantes= new FRM_MantenimientoEstudiantes(frm_login);
        frm_MantenimientoCursos= new FRM_MantenimientoCursos(frm_login);
        frm_Matricula =new FRM_Matricula(frm_MantenimientoEstufiantes,frm_MantenimientoCursos, frm_login);
    }
    
    public void actionPerformed(ActionEvent e)
    {
        if(e.getActionCommand().equals("Salir"))
        {
            System.exit(0);
        }
        if(e.getActionCommand().equals("Estudiantes"))
        {
            frm_MantenimientoEstufiantes.setVisible(true);    
        }
        if(e.getActionCommand().equals("Cursos"))
        {
            frm_MantenimientoCursos.setVisible(true);
        }
        if(e.getActionCommand().equals("Matricula"))
        {
            frm_Matricula.setVisible(true);
            frm_Matricula.colocarCodigo();
        }
    
    }    
}