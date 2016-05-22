/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import BaseDatos.ConexionBD;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.MetodosCursos;
import modelo.MetodosEstudiantes;
import modelo.MetodosMatricula;
import vista.FRM_Login;
import vista.FRM_MantenimientoCursos;
import vista.FRM_MantenimientoEstudiantes;
import vista.FRM_Matricula;

/**
 *
 * @author Alejandro Larios Ulate
 */
public class Controlador_FRM_Matricula implements ActionListener {

    MetodosCursos metodosCursos;
    MetodosEstudiantes metodosEstudiantes;
    public MetodosMatricula metodosMatricula;
    FRM_Matricula frm_matricula;
    boolean encontroEstudiante = false;
    boolean encontroCurso = false;
    FRM_Login frm_login;
    ConexionBD conexion;

    public Controlador_FRM_Matricula(FRM_MantenimientoEstudiantes frm_MantenimientoEstufiantes, FRM_MantenimientoCursos frm_MantenimientoCursos, FRM_Matricula frm_matricula, FRM_Login frm_login) {
        this.metodosCursos = frm_MantenimientoCursos.controlador.metodosCursos;
        this.metodosEstudiantes = frm_MantenimientoEstufiantes.controlador_FRM_MantenimientoEstudiantes.metodosEstudiantes;
        this.frm_matricula = frm_matricula;
        metodosMatricula = new MetodosMatricula(metodosEstudiantes, metodosCursos, this);
        this.frm_login = frm_login;
        conexion = new ConexionBD();
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("ConsultaRapidaCedula")) {
            if (frm_login.botones().equals("BaseDeDatos")) {
                if (conexion.consultarEstudiante(frm_matricula.devolverCedula())) {
                    String arreglo[] = conexion.arregloInformacionEstudiante;
                    frm_matricula.colocarNombreEstudiante(arreglo[0]);
                    encontroEstudiante = true;
                } else {
                    frm_matricula.mostrarMensaje("El estudiante no se encuentra, favor dirigirse al módulo de Mantenimiento Estudiantes");
                }
            }

        }

        if (e.getActionCommand().equals("ConsultaRapidaSigla")) {
            if (frm_login.botones().equals("BaseDeDatos")) {
                if (conexion.consultarCurso(frm_matricula.devolverSigla())) {
                    String arreglo[] = conexion.arregloInformacionCurso;
                    frm_matricula.colocarNombreCurso(arreglo[0]);
                    encontroCurso = true;
                } else {
                    frm_matricula.mostrarMensaje("El curso no se encuentra, favor dirigirse al módulo de Mantenimiento Cursos");
                }
            }

        }
        if (e.getActionCommand().equals("Agregar")) {
            frm_matricula.agregarInformacionTabla();
            //frm_matricula.limpiarSigla();
        }

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////
        if (e.getActionCommand().equals("Finalizar")) {
            String arreglo[] = new String[2];
            for (int contador = 0; contador < frm_matricula.getCantidadFilas(); contador++) {
                arreglo[0] = frm_matricula.devolverCodigo();
                arreglo[1] = frm_matricula.devolverDato(contador, 1);
                conexion.registrarMatricula(arreglo);
                guardarMatriculaDetalle();
            }

            //frm_matricula.resetearVentana();
            encontroEstudiante = false;
            encontroCurso = false;
            frm_matricula.mostrarMensaje("Matricula finalizada exitosamente");

        }

        if (e.getActionCommand().equals("Finalizar")) {
            String arreglo[] = new String[3];
            for (int contador = 0; contador < frm_matricula.getCantidadFilas(); contador++) {
                arreglo[0] = frm_matricula.devolverCodigo();
                arreglo[1] = frm_matricula.devolverDato(contador, 1);
                arreglo[2] = frm_matricula.devolverDato(contador, 3);
                metodosMatricula.agregarMatricula(arreglo);
            }
            frm_matricula.colocarCodigo();
            frm_matricula.resetearVentana();
            encontroEstudiante = false;
            encontroCurso = false;
        }

        /////////////////////////////////////////////////////////////////////////////////////////////////////
        if (e.getActionCommand().equals("Consultar")) {
            if (conexion.consultarMatriculaDetallada(frm_matricula.devolverCodigo(), frm_matricula)) {
                frm_matricula.mostrarInformacion(conexion.arregloInformacionMatriculaDetallada);
                //frm_matricula.habilitarOpciones();
            } else {
                frm_matricula.mostrarMensaje("La matricula no se encuentra registrada");
                System.out.println("Buscar");
            }

        }
        if (e.getActionCommand().equals("Eliminar")) {
            System.out.println(frm_matricula.devolverFilaSeleccionada());
        }
        verificarConsultas();
    }

    public void verificarConsultas() {
        if (encontroEstudiante && encontroCurso) {
        }
    }

    public void guardarMatriculaDetalle() {
        String arreglo[] = new String[2];
        for (int contador = 0; contador < frm_matricula.getCantidadFilas(); contador++) {
            arreglo[0] = frm_matricula.devolverCodigo();
            arreglo[1] = frm_matricula.devolverDato(contador, 3);
            conexion.registrarMatriculaDetallada(arreglo);

        }
    }
}
