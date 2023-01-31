package Login;

import javax.swing.*;
import java.awt.Color;

import RmiChat.ServerSide.ServerRmi;
import StudentSide.Menu;
import TeacherSide.* ;


import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.GroupLayout.Alignment;


public class LoginPage extends javax.swing.JFrame {


    /**
     * Creates new form LoginPage
     */
    public LoginPage() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     */
    @SuppressWarnings("unchecked")

    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        user = new javax.swing.JTextField();
        password = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        getContentPane().setBackground(new Color(31, 235, 255));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24));
        jLabel1.setForeground(new java.awt.Color(213, 18, 234));

        jLabel1.setText("Elearning Appliction");

        jLabel2.setFont(new java.awt.Font("Tempus Sans ITC", 1, 11));
        jLabel2.setText("LOGIN PAGE");

        jLabel3.setText("Username:");

        jLabel4.setText("Password:");

        jButton1.setText("Login");

        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
                layout.createParallelGroup(Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(139)
                                                .addComponent(jLabel3)
                                                .addGap(28)
                                                .addComponent(user, GroupLayout.PREFERRED_SIZE, 201, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(139)
                                                .addComponent(jLabel4)
                                                .addGap(30)
                                                .addComponent(password, GroupLayout.PREFERRED_SIZE, 201, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(355)
                                                .addComponent(jButton1))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(272)
                                                .addComponent(jLabel2))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(149)
                                                .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 232, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(83, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(31)
                                .addComponent(jLabel1)
                                .addGap(11)
                                .addComponent(jLabel2)
                                .addGap(6)
                                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(3)
                                                .addComponent(jLabel3))
                                        .addComponent(user, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(18)
                                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(3)
                                                .addComponent(jLabel4))
                                        .addComponent(password, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(11)
                                .addComponent(jButton1)
                                .addContainerGap(112, Short.MAX_VALUE))
        );
        getContentPane().setLayout(layout);

        pack();
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        String userText= user.getText();
        String passwordText= password.getText();

        try{

            Connection conn= (Connection) DriverManager.getConnection("jdbc:mysql://localhost/elearningapp","root","");
            Statement st= (Statement)conn.createStatement();
            String sql= "select * from User";

             ResultSet rs= st.executeQuery(sql);
            while(rs.next()){
            int id = rs.getInt("idUser");
            String username= rs.getString("username");
            String Password= rs.getString("password");
            String type= rs.getString("type");

            if(userText.equals(username) && passwordText.equals(Password)){
                if(type.equals("admin")){
                  new HelloForm().setVisible(true);
                   dispose();
                } else if (type.equals("student")) {
                    new Menu(username , id).setVisible(true);
                    dispose();
                }else if (type.equals("teacher")) {
                    new TeacherSide.Menu(username , id).setVisible(true);
                    dispose();
                }
            }
            else
            {
                JOptionPane.showMessageDialog(this, "Username or Password is incorrect! Please retype again");
            }
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error while establishing connection");
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
            java.util.logging.Logger.getLogger(LoginPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginPage().setVisible(true);
            }
        });
    }

    // Variables declaration
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;

    private javax.swing.JTextField user;
    private javax.swing.JPasswordField password;


}
