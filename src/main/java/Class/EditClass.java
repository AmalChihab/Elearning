package Class;

import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.swing.*;


public class EditClass extends javax.swing.JFrame {

    /**
     * Creates new form EditClass
     */
    public EditClass() {
        initComponents();
    }


    @SuppressWarnings("unchecked")

    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();



        numClass = new javax.swing.JTextField();
        nameClass = new javax.swing.JTextField();

        jButton1 = new javax.swing.JButton();


        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18));
        jLabel1.setText("UPDATE CLASS's NAME");


        jLabel2.setText("NUM CLASS:");

        jLabel3.setText("NAME CLASS:");


        jButton1.setText("Submit");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        getContentPane().setBackground(new Color(255, 255, 255, 178));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 204));
        jLabel4.setText("BACK");
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        /**
         * DESIGN : SET THE ELEMENTS IN THE FRAME
         */

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(90)
                                                .addComponent(jLabel2)
                                                .addGap(38)
                                                .addComponent(numClass, GroupLayout.PREFERRED_SIZE, 201, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(90)
                                                .addComponent(jLabel3)
                                                .addGap(30)
                                                .addComponent(nameClass, GroupLayout.PREFERRED_SIZE, 201, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(355)
                                                .addComponent(jButton1))

                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(185, 185, 185)
                                                .addComponent(jLabel1)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 137, Short.MAX_VALUE)
                                                .addComponent(jLabel4)
                                                .addGap(39, 39, 39)))
                                .addContainerGap(83, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(31)
                                .addComponent(jLabel1)
                                .addGap(11)
                                .addComponent(jLabel4)
                                .addGap(11)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(3)
                                                .addComponent(jLabel2))
                                        .addComponent(numClass, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(11)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(3)
                                                .addComponent(jLabel3))
                                        .addComponent(nameClass, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(11)
                                .addComponent(jButton1)
                                .addContainerGap(112, Short.MAX_VALUE))
        );
        getContentPane().setLayout(layout);

        pack();
    }

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
        ClassDashboard obj=new ClassDashboard();
        obj.setVisible(true);
        dispose();
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        String n  = nameClass.getText();
        int id = Integer.parseInt(numClass.getText()) ;


        try{

            Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/elearningapp","root","");
            String sql = "update `Class` SET `NomClass`='"+n+"' where `idClass`='"+id+"'";
            PreparedStatement ptst= conn.prepareStatement(sql);
            ptst.execute();

            JOptionPane.showMessageDialog(null, "Record has been updated successfully!");
            conn.close();

        }catch (Exception e){
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
            java.util.logging.Logger.getLogger(EditClass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditClass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditClass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditClass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }


        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EditClass().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;

    private javax.swing.JLabel jLabel4;


    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField numClass;
    private javax.swing.JTextField nameClass;


    // End of variables declaration
}

