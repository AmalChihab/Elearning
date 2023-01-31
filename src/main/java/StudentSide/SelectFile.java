package StudentSide;

import Login.LoginPage;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class SelectFile extends javax.swing.JFrame {
    private int ClassSelected ;

    private String UserSelected ;

    /**
     * Creates new form SelectClass
     */
    public SelectFile (int id , String username) {
        this.ClassSelected = id ;
        this.UserSelected = username ;
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     */
    @SuppressWarnings("unchecked")

    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        classTable = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        sid = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel2.setText("List of the Files");

        classTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                        {null, null},
                        {null, null},
                        {null, null},
                        {null, null}
                },
                new String[]{
                        "ID", "NAME",
                }
        ));
        jScrollPane1.setViewportView(classTable);

        jButton1.setText("REFRECH");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel3.setText("To Select a file, Insert the ID:");

        jButton2.setText("Select");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel4.setText("LOGOUT");
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
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
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 621, Short.MAX_VALUE)
                                .addContainerGap())
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                .addComponent(jLabel2)
                                                                .addGap(103, 103, 103)
                                                                .addComponent(jLabel4)
                                                                .addGap(32, 32, 32))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                .addComponent(jButton1)
                                                                .addGap(263, 263, 263))))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel3)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(sid, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jButton2)))
                                                .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(19, 19, 19)
                                                .addComponent(jLabel2))
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(jLabel4)))
                                .addGap(10, 10, 10)
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(sid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButton2))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }

    /**
     * UPDATE THE TABLE WITH THE INFORMATION FROM DATABASE
     */
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/elearningapp", "root", "");
            Statement st = conn.createStatement();
            String sql = "select idfile , path from files where NumClass="+ClassSelected;
            PreparedStatement ptst = conn.prepareStatement(sql);
            ResultSet rs = ptst.executeQuery();
            DefaultTableModel tm = (DefaultTableModel) classTable.getModel();
            tm.setRowCount(0);
            while (rs.next()) {
                Object o[] = {rs.getInt("idfile"), rs.getString("path")};
                tm.addRow(o);
            }
            conn.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    /**
     * NEW PAGE : MANAGE THE CLASS SELECTED
     */

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:

        int fileSelected = Integer.parseInt(sid.getText());
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/elearningapp", "root", "");
            Statement st = conn.createStatement();
            String sql = "select path from files where idfile="+fileSelected;
            PreparedStatement ptst = conn.prepareStatement(sql);
            ResultSet rs = ptst.executeQuery();
            while(rs.next()) {
                String p = rs.getString("path");
                final File[] fileToSend = new File[1];
                fileToSend[0] = new File(p);
                System.out.println("file : "+fileToSend[0]);
                try {
                    // Create an input stream into the file you want to send.
                    FileInputStream fileInputStream = new FileInputStream(fileToSend[0].getAbsolutePath());
                    // Create a socket connection to connect with the server.
                    Socket socket = new Socket("localhost", 5000);
                    // Create an output stream to write to write to the server over the socket connection.
                    DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                    // Get the name of the file you want to send and store it in filename.
                    String fileName = fileToSend[0].getName();
                    // Convert the name of the file into an array of bytes to be sent to the server.
                    byte[] fileNameBytes = fileName.getBytes();
                    // Create a byte array the size of the file so don't send too little or too much data to the server.
                    byte[] fileBytes = new byte[(int)fileToSend[0].length()];
                    // Put the contents of the file into the array of bytes to be sent so these bytes can be sent to the server.
                    fileInputStream.read(fileBytes);
                    // Send the length of the name of the file so server knows when to stop reading.
                    dataOutputStream.writeInt(fileNameBytes.length);
                    // Send the file name.
                    dataOutputStream.write(fileNameBytes);
                    // Send the length of the byte array so the server knows when to stop reading.
                    dataOutputStream.writeInt(fileBytes.length);
                    // Send the actual file.
                    dataOutputStream.write(fileBytes);


                } catch (IOException ex) {
                    ex.printStackTrace();
                }


            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    /**
     * NEW PAGE : LOGOUT
     */

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
        ManageClass obj = new ManageClass(ClassSelected , UserSelected);
        obj.setVisible(true);
        dispose();
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
            java.util.logging.Logger.getLogger(TeacherSide.SelectClass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TeacherSide.SelectClass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TeacherSide.SelectClass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TeacherSide.SelectClass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }


        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TeacherSide.SelectClass(0).setVisible(true);
            }
        });
    }

    // Variables declaration
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField sid;
    private javax.swing.JTable classTable;
// End of variables declaration

}

