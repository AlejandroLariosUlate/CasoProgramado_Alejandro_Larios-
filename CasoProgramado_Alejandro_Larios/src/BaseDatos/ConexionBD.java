/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDatos;

/**
 *
 * @author Alejandro Larios Ulate
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import vista.FRM_Matricula;

public class ConexionBD {

    Connection con = null;
    FRM_Matricula frm_Matricula;
    String tipo = "";
    public String arregloInformacionCurso[] = new String[3];
    public String arregloInformacionEstudiante[] = new String[2];
    public String arregloInformacionMatriculaDetallada[] = new String[3];
    public String arregloInformacionMatricula[] = new String[3];
    public String arregloInformacionUsuario[] = new String[6];

    public ConexionBD() {
        realizarConexion();
    }

    public void realizarConexion() {
        try {
            String userName = "root";
            String password = "";
            String url = "jdbc:mysql://localhost:3306/matricula";
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection(url, userName, password);
            System.out.println("Conexión Realizada");
        } catch (Exception e) {
            System.err.println("Cannot connect to database server");
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public boolean registrarEstudiante(String arreglo[]) {
        ResultSet rs = null;
        Statement cmd = null;
        boolean ejecuto;
        try {
            cmd = con.createStatement();
            ejecuto = cmd.execute("INSERT INTO estudiantes(cedula, nombre, direccion) VALUES ('" + arreglo[0] + "','" + arreglo[1] + "','" + arreglo[2] + "')");
            return true;
            // rs.close();
        } catch (Exception e) {
            System.out.println("SQLException ejecutando sentencia: " + e.getMessage());
            return false;
        }

    }

    public boolean consultarEstudiante(String cedula) {
        ResultSet rs = null;
        Statement cmd = null;
        boolean existe = false;
        try {

            cmd = con.createStatement();
            rs = cmd.executeQuery("SELECT * FROM estudiantes where cedula='" + cedula + "'");

            while (rs.next()) {
                arregloInformacionEstudiante[0] = rs.getString("nombre");
                arregloInformacionEstudiante[1] = rs.getString("direccion");

                existe = true;

            }
            rs.close();
        } catch (Exception e) {
            System.out.println("SQLException ejecutando sentencia: " + e.getMessage());
            existe = false;
        }
        return existe;
    }

    public void eliminarEstudiante(String cedula) {
        ResultSet rs = null;
        Statement cmd = null;
        boolean ejecuto;
        try {
            cmd = con.createStatement();
            ejecuto = cmd.execute("DELETE FROM estudiantes WHERE cedula ='" + cedula + "'");
            System.out.println("eliminado con exito");
        } catch (Exception e) {
            System.out.println("SQLException ejecutando sentencia: " + e.getMessage());

        }
    }

    public void modificarEstudiante(String arreglo[]) {
        ResultSet rs = null;
        Statement cmd = null;
        boolean ejecuto;
        try {
            cmd = con.createStatement();
            ejecuto = cmd.execute("UPDATE estudiantes SET nombre='" + arreglo[1] + "',direccion='" + arreglo[2] + "' WHERE cedula='" + arreglo[0] + "'");
        } catch (Exception e) {
            System.out.println("SQLException ejecutando sentencia: " + e.getMessage());

        }
    }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean registrarCurso(String arreglo[]) {
        ResultSet rs = null;
        Statement cmd = null;
        boolean ejecuto;
        boolean existe = false;
        try {
            cmd = con.createStatement();
            ejecuto = cmd.execute("INSERT INTO cursos(sigla, nombre, creditos,horario) VALUES ('" + arreglo[0] + "','" + arreglo[1] + "','" + Integer.parseInt(arreglo[2]) + "','" + arreglo[3] + "')");
            existe = true;
            // rs.close();
        } catch (Exception e) {
            System.out.println("SQLException ejecutando sentencia: " + e.getMessage());
            existe = false;
        }
        return existe;
    }

    public boolean consultarCurso(String sigla) {
        ResultSet rs = null;
        Statement cmd = null;
        boolean existe = false;
        try {
            cmd = con.createStatement();
            rs = cmd.executeQuery("SELECT * FROM cursos where sigla='" + sigla + "'");

            while (rs.next()) {
                arregloInformacionCurso[0] = rs.getString("nombre");
                arregloInformacionCurso[1] = "" + rs.getString("creditos");
                arregloInformacionCurso[2] = rs.getString("horario");
                existe = true;
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("SQLException ejecutando sentencia: " + e.getMessage());
            existe = false;
        }
        return existe;
    }

    public void eliminarCurso(String sigla) {
        ResultSet rs = null;
        Statement cmd = null;
        boolean ejecuto;
        try {
            cmd = con.createStatement();
            ejecuto = cmd.execute("DELETE FROM cursos WHERE sigla ='" + sigla + "'");
            System.out.println("eliminado con exito");

            // rs.close();
        } catch (Exception e) {
            System.out.println("SQLException ejecutando sentencia: " + e.getMessage());

        }
    }

    public void modificarCurso(String arreglo[]) {
        ResultSet rs = null;
        Statement cmd = null;
        boolean ejecuto;
        try {
            cmd = con.createStatement();
            ejecuto = cmd.execute("UPDATE cursos SET nombre='" + arreglo[1] + "',creditos='" + arreglo[2] + "',horario='" + arreglo[3] + "' WHERE sigla='" + arreglo[0] + "'");

            // rs.close();
        } catch (Exception e) {
            System.out.println("SQLException ejecutando sentencia: " + e.getMessage());

        }
    }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean registrarMatriculaDetallada(String arreglo[]) {
        ResultSet rs = null;
        Statement cmd = null;
        boolean ejecuto;
        boolean existe = false;
        try {
            cmd = con.createStatement();
            ejecuto = cmd.execute("INSERT INTO detalle_matricula(codigo, sigla) VALUES ('" + arreglo[0] + "','" + arreglo[1] + "')");
            existe = true;
            // rs.close();
        } catch (Exception e) {
            System.out.println("SQLException ejecutando sentencia: " + e.getMessage());
            existe = false;
        }
        return existe;
    }

    public boolean consultarMatriculaDetallada(String codigo, FRM_Matricula frm_matricula) {
        ResultSet rs = null;
        Statement cmd = null;
        boolean existe = false;

        try {
            cmd = con.createStatement();
            rs = cmd.executeQuery("SELECT * FROM detalle_matricula where codigo='" + codigo + "'");

            String arreglo[] = new String[4];

            while (rs.next()) {

                arreglo[0] = rs.getString("codigo");//me guarda el codigo
                arreglo[1] = consultarMatricula(arreglo[0]);//guarda en el arreglo1 la cedula que encuentro por el codigo
                consultarEstudiante(arreglo[1]);//como ya tengo la cedula consulto el estudiante
                arreglo[2] = arregloInformacionEstudiante[0];//guardo el nombre que se que esta en la posicion cero del arregloEStu
                arreglo[3] = rs.getString("sigla");//nada mas digo que me traiga la sigla y que la guarde
                arregloInformacionMatriculaDetallada[0] = arreglo[1] = consultarMatricula(arreglo[0]);
                arregloInformacionMatriculaDetallada[1] = arreglo[2] = arregloInformacionEstudiante[0];
                arregloInformacionMatriculaDetallada[2] = arreglo[3];

            }
            existe = true;
            rs.close();
        } catch (Exception e) {
            System.out.println("SQLException ejecutando sentencia: " + e.getMessage());
        }
        return existe;
    }

    public void eliminarMatriculaDetallada(String codigo) {
        ResultSet rs = null;
        Statement cmd = null;
        boolean ejecuto;
        try {
            cmd = con.createStatement();
            ejecuto = cmd.execute("DELETE FROM detalle_matricula WHERE codigo ='" + codigo + "'");
            System.out.println("Matrícula detallada eliminada con exito");

            // rs.close();
        } catch (Exception e) {
            System.out.println("SQLException ejecutando sentencia: " + e.getMessage());

        }
    }

    public void modificarMatriculaDetallada(String arreglo[]) {
        ResultSet rs = null;
        Statement cmd = null;
        boolean ejecuto;
        try {
            cmd = con.createStatement();
            ejecuto = cmd.execute("UPDATE detalle_matricula SET sigla='" + arreglo[1] + "'WHERE codigo='" + arreglo[0] + "'");

            // rs.close();
        } catch (Exception e) {
            System.out.println("SQLException ejecutando sentencia: " + e.getMessage());

        }
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean registrarMatricula(String arreglo[]) {
        ResultSet rs = null;
        Statement cmd = null;
        boolean ejecuto;
        boolean existe = false;
        try {
            cmd = con.createStatement();
            ejecuto = cmd.execute("INSERT INTO matricula(codigo, cedula) VALUES ('" + arreglo[0] + "','" + arreglo[1] + "')");
            existe = true;
            // rs.close();
        } catch (Exception e) {
            System.out.println("SQLException ejecutando sentencia: " + e.getMessage());
            existe = false;
        }
        return existe;
    }

    public String consultarMatricula(String codigo) {
        ResultSet rs = null;
        Statement cmd = null;
        String cedulaEstudiante = "";

        boolean existe = false;

        try {
            cmd = con.createStatement();
            rs = cmd.executeQuery("SELECT * FROM matricula where codigo='" + codigo + "'");
            while (rs.next()) {
                cedulaEstudiante = rs.getString("cedula");
            }
            //rs.close();
        } catch (Exception e) {
            System.out.println("SQLException ejecutando sentencia consultar Matricula: " + e.getMessage());
        }
        return cedulaEstudiante;
    }

    public void eliminarMatricula(String codigo) {
        ResultSet rs = null;
        Statement cmd = null;
        boolean ejecuto;
        try {
            cmd = con.createStatement();
            ejecuto = cmd.execute("DELETE FROM matricula WHERE codigo ='" + codigo + "'");
            System.out.println("Matrícula eliminada con exito");

            // rs.close();
        } catch (Exception e) {
            System.out.println("SQLException ejecutando sentencia: " + e.getMessage());

        }
    }

    public void modificarMatricula(String arreglo[]) {
        ResultSet rs = null;
        Statement cmd = null;
        boolean ejecuto;
        try {
            cmd = con.createStatement();
            ejecuto = cmd.execute("UPDATE matricula SET cedula='" + arreglo[1] + "'WHERE codigo='" + arreglo[0] + "'");

            // rs.close();
        } catch (Exception e) {
            System.out.println("SQLException ejecutando sentencia: " + e.getMessage());

        }
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean registrarUsuarios(String arreglo[]) {
        ResultSet rs = null;
        Statement cmd = null;
        boolean ejecuto;
        try {
            cmd = con.createStatement();
            ejecuto = cmd.execute("INSERT INTO `usuarios`(`cedula`, `nombreCompleto`, `nombreUsuario`, `contrasena`, `repetirContrasena`, `tipo`) VALUES ('" + arreglo[0] + "','" + arreglo[1] + "','" + arreglo[2] + "','" + arreglo[3] + "','" + arreglo[4] + "','" + arreglo[5] + "')");

            return true;
            // rs.close();
        } catch (Exception e) {
            System.out.println("SQLException ejecutando sentencia: " + e.getMessage());
            return false;
        }

    }

    public boolean consultarUsuario(String cedula) {
        ResultSet rs = null;
        Statement cmd = null;
        boolean ejecuto = false;
        try {
            cmd = con.createStatement();
            rs = cmd.executeQuery("SELECT * FROM `usuarios` WHERE cedula=" + cedula);
            while (rs.next()) {
                arregloInformacionUsuario[0] = rs.getString("cedula");
                arregloInformacionUsuario[1] = rs.getString("nombreCompleto");
                arregloInformacionUsuario[2] = rs.getString("nombreUsuario");
                arregloInformacionUsuario[3] = rs.getString("contrasena");
                arregloInformacionUsuario[4] = rs.getString("repetirContrasena");
                arregloInformacionUsuario[5] = rs.getString("tipo");
                ejecuto = true;
            }
            rs.close();

        } catch (Exception e) {
            System.out.println("SQLException ejecutando sentencia: " + e.getMessage());
        }
        return ejecuto;
    }//fin del meatodo consultar

    public boolean modificarUsuario(String arreglo[]) {
        ResultSet rs = null;
        Statement cmd = null;
        boolean ejecuto;
        try {
            cmd = con.createStatement();
            ejecuto = cmd.execute("UPDATE usuarios SET cedula= '" + arreglo[0] + "',nombreCompleto='" + arreglo[1] + "',contrasena= '" + arreglo[3] + "',repetirContrasena= '" + arreglo[4] + "',tipo= '" + arreglo[5] + "' WHERE nombreUsuario='" + arreglo[2] + "'");

            return true;
            // rs.close();
        } catch (Exception e) {
            System.out.println("SQLException ejecutando sentencia: " + e.getMessage());
            return false;
        }

    }

    public void eliminarUsuario(String nombreUsuario) {
        ResultSet rs = null;
        Statement cmd = null;
        boolean ejecuto;
        try {
            cmd = con.createStatement();
            ejecuto = cmd.execute("DELETE FROM usuarios where nombreUsuario ='" + nombreUsuario + "'");

        } catch (Exception e) {
            System.out.println("SQLException ejecutando sentencia: " + e.getMessage());

        }
    }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean consultarUsuarios() {
        ResultSet rs = null;
        Statement cmd = null;
        boolean ejecuto = true;
        try {
            cmd = con.createStatement();
            rs = cmd.executeQuery("SELECT * FROM `usuarios`");

            while (rs.next()) {
                ejecuto = false;
            }
            rs.close();

        } catch (Exception e) {
            System.out.println("SQLException ejecutando sentencia: " + e.getMessage());

        }
        return ejecuto;
    }

    public boolean iniciarSesion(String arreglo[]) {
        boolean iniciar = false;
        ResultSet rs = null;
        Statement cmd = null;
        try {
            cmd = con.createStatement();
            rs = cmd.executeQuery("SELECT * FROM `usuarios` WHERE nombreUsuario='" + arreglo[0] + "' AND contrasena='" + arreglo[1] + "' ");
            while (rs.next()) {
                tipo = rs.getString("tipo");
                iniciar = true;
            }
            rs.close();

        } catch (Exception e) {
            System.out.println("SQLException ejecutando sentencia: " + e.getMessage());
        }

        return iniciar;
    }

    public String devolverTipo() {
        return tipo;
    }

    public boolean registrarLogin(String arreglo[]) {
        ResultSet rs = null;
        Statement cmd = null;
        boolean ejecuto;
        try {
            cmd = con.createStatement();
            ejecuto = cmd.execute("INSERT INTO `login`(`nombreUsuario`,`contrasena`) VALUES ('" + arreglo[0] + "','" + arreglo[1] + "')");

            return true;
            // rs.close();
        } catch (Exception e) {
            System.out.println("SQLException ejecutando sentencia: " + e.getMessage());
            return false;
        }

    }

}
