package TeacherSide;


import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.Socket;

public class SendFile extends javax.swing.JFrame {

    private int ClassSelected ;

    // Accessed from within inner class needs to be final or effectively final.
    final File[] fileToSend = new File[1];
    /**
     * Creates new form SendFile
     */
    public SendFile(int id) {
        this.ClassSelected = id ;
        initComponents();

    }

    @SuppressWarnings("unchecked")

    private void initComponents() {
        jLTitle = new javax.swing.JLabel();
        jLfileName = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLTitle.setFont(new Font("Arial", Font.BOLD, 25));
        jLTitle.setText("Send files tou your class");

        jLfileName.setFont(new Font("Arial", Font.BOLD, 25));
        jLfileName.setText("Choose a file to send");

        jButton1.setText("Choose file");
        jButton1.setFont(new Font("Arial", Font.BOLD, 15));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Send file");
        jButton2.setFont(new Font("Arial", Font.BOLD, 15));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });


        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(132, 132, 132)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(132, 132, 132)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLfileName, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(jLTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(jLfileName, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(29, Short.MAX_VALUE)
                                .addGap(32, 32, 32)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(89, Short.MAX_VALUE))
        );
        pack();
    }


    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        // Create a file chooser to open the dialog to choose a file.
        JFileChooser jFileChooser = new JFileChooser();
        // Set the title of the dialog.
        jFileChooser.setDialogTitle("Choose a file to send.");
        // Show the dialog and if a file is chosen from the file chooser execute the following statements.
        if (jFileChooser.showOpenDialog(null)  == JFileChooser.APPROVE_OPTION) {
            // Get the selected file.
            fileToSend[0] = jFileChooser.getSelectedFile();
            // Change the text of the java swing label to have the file name.
            jLfileName.setText("The file you want to send is: " + fileToSend[0].getName());
        }

    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
            // If a file has not yet been selected then display this message.
            if (fileToSend[0] == null) {
                jLfileName.setText("Please choose a file to send first!");
                // If a file has been selected then do the following.
            } else {
                try {
                    // Create an input stream into the file you want to send.
                    FileInputStream fileInputStream = new FileInputStream(fileToSend[0].getAbsolutePath());
                    // Create a socket connection to connect with the server.
                    Socket socket = new Socket("localhost", 1234);
                    // Create an output stream to write to write to the server over the socket connection.
                    DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                    // Get the name of the file you want to send and store it in filename.
                    String fileName = ClassSelected+","+fileToSend[0].getName();
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
        }


    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SendFile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SendFile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SendFile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SendFile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }


        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SendFile(0).setVisible(true);
            }
        });
    }

    private javax.swing.JLabel jLTitle;
    private javax.swing.JLabel jLfileName;

    private javax.swing.JButton jButton1;

    private javax.swing.JButton jButton2;

}
