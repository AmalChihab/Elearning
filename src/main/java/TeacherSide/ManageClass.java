package TeacherSide;

import java.awt.*;
import java.sql.*;


import Student.DeleteStudent;
import WhiteBoardRmi.Peer.UI;


import javax.swing.*;


public class ManageClass extends javax.swing.JFrame {
     private int ClassSelected ;
     private int TeacherSelected ;
     private String UserConnected ;

    /**
     * Creates new form ManageClass
     */
    public ManageClass(int id , String UserConnected) {
        this.ClassSelected = id ;
        this.UserConnected = UserConnected ;
        initComponents();
    }

    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24));
        jLabel1.setText("CLASS ");

        jButton1.setText("Tableau");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("VIEW LIST OF STUDENTS OF THIS CLASS");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("TRANSFER FILES TO STUDENTS");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        getContentPane().setBackground(new Color(255, 255, 255, 178));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 204));
        jLabel2.setText("BACK");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        /**
         * DESIGN : SET THE ELEMENTS IN THE FRAME
         */

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(232, 232, 232)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButton1))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(185, 185, 185)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 137, Short.MAX_VALUE)
                                .addComponent(jLabel2)
                                .addGap(39, 39, 39))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1)
                                        .addComponent(jLabel2))
                                .addGap(32, 32, 32)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(89, Short.MAX_VALUE))
        );

        pack();
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        ListStudent obj =new ListStudent(ClassSelected);
        obj.setVisible(true);
        dispose();
    }

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/elearningapp", "root", "");
            Statement st = conn.createStatement();
            String sql = "select idUser from User where type='teacher' and NumClass="+ClassSelected;
            PreparedStatement ptst = conn.prepareStatement(sql);
            ResultSet rs = ptst.executeQuery();
            while (rs.next()) {
                this.TeacherSelected = rs.getInt("idUser");
                SendFile obj =new SendFile(ClassSelected);
                obj.setVisible(true);
                dispose();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        UI ui = new UI(this.UserConnected);
        ui.setVisible(true);
        ui.exportThis();
        dispose();
    }

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/elearningapp", "root", "");
            Statement st = conn.createStatement();
            String sql = "select idUser from User where type='teacher' and NumClass="+ClassSelected;
            PreparedStatement ptst = conn.prepareStatement(sql);
            ResultSet rs = ptst.executeQuery();
            while (rs.next()) {
                this.TeacherSelected = rs.getInt("idUser");
                SelectClass obj =new SelectClass(this.TeacherSelected , this.UserConnected);
                obj.setVisible(true);
                dispose();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }


    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ManageClass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManageClass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManageClass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManageClass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManageClass(0 , null).setVisible(true);
            }
        });
    }

    // Variables declaration
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration
}

