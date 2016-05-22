/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.Controlador_FRM_Usuario;
import javax.swing.JOptionPane;

/**
 *
 * @author Alejandro Larios Ulate
 */
public class FRM_MantenimientoUsuarios extends javax.swing.JFrame {

    /**
     * Creates new form FRM_MantenimientoUsuarios
     */
    public Controlador_FRM_Usuario controlador_FRM_Usuario;
    FRM_Login frm_login;

    public FRM_MantenimientoUsuarios(FRM_Login frm_login) {
        initComponents();
        this.frm_login = frm_login;
        controlador_FRM_Usuario = new Controlador_FRM_Usuario(this, frm_login);
        this.btnVolver.addActionListener(controlador_FRM_Usuario);
        this.panel_Botones1.agregarEventosUsuario(controlador_FRM_Usuario);
        cargarTipo();
    }

    public String[] devolverInformacion() {
        String arreglo[] = new String[6];
        arreglo[0] = this.jtCedula.getText();
        arreglo[1] = this.jtNombreCompleto.getText();
        arreglo[2] = this.jtNombreUsuario.getText();
        arreglo[3] = this.jpfContrasena.getText();
        arreglo[4] = this.jpfRepetirContrasena.getText();
        arreglo[5] = "" + this.jcbTipo.getSelectedItem();
        return arreglo;
    }

    public void cargarTipo() {
        this.jcbTipo.removeAllItems();
        this.jcbTipo.addItem("Administrador");
        this.jcbTipo.addItem("Usuario");
    }

    public String devolverCedula() {
        return this.jtCedula.getText();
    }
    
    public void mostrarInformacionBD_AP(String arreglo[]) {
        this.jtNombreCompleto.setText(arreglo[0]);
        this.jtNombreUsuario.setText(arreglo[1]);
        this.jpfContrasena.setText(arreglo[2]);
        this.jpfRepetirContrasena.setText(arreglo[3]);
        this.jcbTipo.setSelectedItem(arreglo[4]);
    }
    
    public void mostrarInformacionXML(String arreglo[]) {
        this.jtNombreCompleto.setText(arreglo[1]);
        this.jtNombreUsuario.setText(arreglo[2]);
        this.jpfContrasena.setText(arreglo[3]);
        this.jpfRepetirContrasena.setText(arreglo[4]);
        this.jcbTipo.setSelectedItem(arreglo[5]);
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }

    public void usuario(boolean estado) {
        this.panel_Botones1.habilitarBotonesUsuario(estado);
    }

    public String devolverNombreUsuario() {
        return this.jtNombreUsuario.getText();
    }

    public void limpiar() {
        this.jtCedula.setText("");
        this.jtNombreCompleto.setText("");
        this.jtNombreUsuario.setText("");
        this.jpfContrasena.setText("");
        this.jpfRepetirContrasena.setText("");
    }

    public void limpiar2() {
        this.jpfRepetirContrasena.setText("");
    }

    public boolean confirmarContrasenna() {
        boolean confirmar = false;
        if (jpfContrasena.getText().equals(jpfRepetirContrasena.getText())) {
            confirmar = true;
        }

        return confirmar;
    }

    

    public void habiltarCampos() {
        this.jtCedula.setEnabled(false);
        this.jtNombreCompleto.setEnabled(true);
        this.jtNombreUsuario.setEnabled(true);
        this.jpfContrasena.setEnabled(true);
        this.jpfRepetirContrasena.setEnabled(true);
    }

    public void inicializarGUI() {
        this.jtCedula.setText("");
        this.jtNombreCompleto.setText("");
        this.jtNombreUsuario.setText("");
        this.jpfContrasena.setText("");
        this.jpfRepetirContrasena.setText("");
    }
    
    public void resetearGUI()
    {
        this.jtCedula.setText("");
        this.jtCedula.setEnabled(true);
        this.jtNombreCompleto.setText("");
        this.jtNombreCompleto.setEnabled(false);
        this.jtNombreUsuario.setText("");
        this.jtNombreUsuario.setEnabled(false);
        this.jpfContrasena.setText("");
        this.jpfContrasena.setEnabled(false);
        this.jpfRepetirContrasena.setText("");
        this.jpfRepetirContrasena.setEnabled(false);
        this.jcbTipo.setSelectedIndex(0);
        this.jcbTipo.setEnabled(false);
    }

     public void habilitarAgregar() {
         this.panel_Botones1.habilitarAgregar();
     }
     
     public void habilitarBotones() {
         this.panel_Botones1.habilitarBotones();
     }
     
     public void habilitarBotonBuscar() {
         this.panel_Botones1.habilitarBotonBuscar();
     }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        jlCedula = new javax.swing.JLabel();
        jtCedula = new javax.swing.JTextField();
        jlNombreCompleto = new javax.swing.JLabel();
        jtNombreCompleto = new javax.swing.JTextField();
        jlNombreUsuario = new javax.swing.JLabel();
        jtNombreUsuario = new javax.swing.JTextField();
        jlContrasena = new javax.swing.JLabel();
        jlRepetirContrasena = new javax.swing.JLabel();
        jlTipo = new javax.swing.JLabel();
        jcbTipo = new javax.swing.JComboBox();
        panel_Botones1 = new vista.Panel_Botones();
        jpfContrasena = new javax.swing.JPasswordField();
        jpfRepetirContrasena = new javax.swing.JPasswordField();
        btnVolver = new javax.swing.JButton();

        jScrollPane1.setViewportView(jTree1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jlCedula.setText("Cédula:");

        jlNombreCompleto.setText("Nombre Completo:");

        jlNombreUsuario.setText("Nombre Usuario: ");

        jlContrasena.setText("Contraseña: ");

        jlRepetirContrasena.setText("Repetir Contraseña:");

        jlTipo.setText("Tipo:");

        btnVolver.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/volver.png"))); // NOI18N
        btnVolver.setActionCommand("Volver");
        btnVolver.setBorderPainted(false);
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlCedula)
                            .addComponent(jlNombreCompleto)
                            .addComponent(jlNombreUsuario)
                            .addComponent(jlContrasena)
                            .addComponent(jlRepetirContrasena)
                            .addComponent(jlTipo))
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jpfContrasena)
                            .addComponent(jpfRepetirContrasena)
                            .addComponent(jcbTipo, 0, 161, Short.MAX_VALUE)
                            .addComponent(jtCedula)
                            .addComponent(jtNombreCompleto)
                            .addComponent(jtNombreUsuario)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnVolver)
                        .addGap(35, 35, 35)
                        .addComponent(panel_Botones1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlCedula)
                    .addComponent(jtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlNombreCompleto)
                    .addComponent(jtNombreCompleto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlNombreUsuario)
                    .addComponent(jtNombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlContrasena)
                    .addComponent(jpfContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlRepetirContrasena)
                    .addComponent(jpfRepetirContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlTipo)
                    .addComponent(jcbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(panel_Botones1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnVolver)
                        .addGap(32, 32, 32))))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVolverActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnVolver;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTree jTree1;
    private javax.swing.JComboBox jcbTipo;
    private javax.swing.JLabel jlCedula;
    private javax.swing.JLabel jlContrasena;
    private javax.swing.JLabel jlNombreCompleto;
    private javax.swing.JLabel jlNombreUsuario;
    private javax.swing.JLabel jlRepetirContrasena;
    private javax.swing.JLabel jlTipo;
    private javax.swing.JPasswordField jpfContrasena;
    private javax.swing.JPasswordField jpfRepetirContrasena;
    private javax.swing.JTextField jtCedula;
    private javax.swing.JTextField jtNombreCompleto;
    private javax.swing.JTextField jtNombreUsuario;
    private vista.Panel_Botones panel_Botones1;
    // End of variables declaration//GEN-END:variables

}
