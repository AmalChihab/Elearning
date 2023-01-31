package RmiChat.ClientSide;

import RmiChat.ServerSide.ServerRmi;
import StudentSide.Menu;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.rmi.RemoteException;
import java.util.Vector;

public class WindowChat extends javax.swing.JFrame implements Runnable{
    private ClientRmiImpl client;
    private ServerRmi server;
    private DefaultListModel<String> model = new DefaultListModel<>();
    private Vector<String> listClients;
    private String name;
    private GroupLayout groupLayout;

    private String type ;

    private int UserId ;

    //constructeur
    public WindowChat(String name, ServerRmi server , String type , int UserId) {
        initComponents();

        this.server = server;
        this.name = name;
        this.type = type ;
        this.UserId = UserId ;

        this.setTitle("Chat (" + name + ")");

        groupLayout = new GroupLayout(jPanel1);
        jPanel1.setLayout(new GridLayout(100,1));
        jPanel1.setBorder(new EmptyBorder(5, 10, 10, 10));

        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (JOptionPane.showConfirmDialog(new JFrame(),
                        "Are you sure you want to close the app ?", "Close app?",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
                    try {
                        server.removeClient(name);
                    } catch (RemoteException ex) {
                        System.out.println("Error: " + ex.getMessage());
                    }
                    System.exit(0);
                }else{

                }
            }
        });


        //un placeholder sur le textfield d'envoyer message
        inputMsg.setForeground(Color.GRAY);
        inputMsg.setText("Enter your Message ...");
        inputMsg.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (inputMsg.getText().equals("Enter your Message ...")) {
                    inputMsg.setText("");
                    inputMsg.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (inputMsg.getText().isEmpty()) {
                    inputMsg.setForeground(Color.GRAY);
                    inputMsg.setText("Enter your Message ...");
                }
            }
        });

        //une liste qui contient le nom des clients connectes
        listClients = new Vector<>();
        listConnect.setListData(listClients);

        try{
            client = new ClientRmiImpl(name,server,inputMsg,listMessage,jPanel1);
        } catch (RemoteException ex) {
            System.out.println("Error: " + ex.getMessage());
        }

    }



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jScrollPane1 = new javax.swing.JScrollPane();
        listConnect = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        inputMsg = new javax.swing.JTextArea();
        btnSend = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        listMessage = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();



        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);

        listConnect.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        listConnect.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        listConnect.setToolTipText("");
        jScrollPane1.setViewportView(listConnect);

        inputMsg.setColumns(20);
        inputMsg.setRows(5);
        inputMsg.setToolTipText("Enter your Message ...");
        inputMsg.setMargin(new java.awt.Insets(6, 0, 0, 16));
        jScrollPane2.setViewportView(inputMsg);
        inputMsg.getAccessibleContext().setAccessibleName("Enter your Message ...");

        btnSend.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnSend.setText("Send");
        btnSend.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendActionPerformed(evt);
            }
        });

        listMessage.setEditable(false);
        listMessage.setColumns(20);
        listMessage.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        listMessage.setRows(5);
        listMessage.setRequestFocusEnabled(false);
        jScrollPane3.setViewportView(listMessage);

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        jLabel2.setText("Connected Clients");

        jButton1.setText("Refresh");
        jButton1.setActionCommand("");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Back");
        jButton2.setActionCommand("");
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });


        jPanel1.setFont(new java.awt.Font("Dialog", 0, 14));
        jLabel1.setText("chat");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 113, Short.MAX_VALUE)
        );

        jScrollPane4.setViewportView(jPanel1);


        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addGroup(layout.createSequentialGroup()
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                                .addComponent(jScrollPane4)
                                                                .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE))
                                                        .addGap(27, 27, 27))
                                                .addGroup(layout.createSequentialGroup()
                                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 446, Short.MAX_VALUE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addComponent(jLabel1))
                                .addGap(12, 12, 12)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(8, 8, 8)
                                                .addComponent(jLabel2))
                                        .addComponent(jScrollPane1)
                                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
                                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
                                        .addComponent(btnSend, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap(42, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel2)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jButton1)
                                                .addComponent(jButton2))
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(16, 16, 16)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(btnSend, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    //action sur la bouton "send" button d'envoi le message, verifier si le message est vide ou non avant l'envoyer
    private void btnSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendActionPerformed
        if(!inputMsg.getText().equals("")){
            if(!inputMsg.getText().equals("Enter you Message ...")){
                client.sendMessage(listConnect.getSelectedValuesList());
                inputMsg.setText("");
            }else{
                JOptionPane.showMessageDialog(this,"Please insert something to set your message","Alert",JOptionPane.WARNING_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(this,"Please insert something to send your message","Alert",JOptionPane.WARNING_MESSAGE);
        }
    }

    //action sur la bouton "refresh" button d'actualisation de la liste des clients (utilisation de thread)
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        Thread thread = new Thread(this);
        thread.start();
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {

                     if (type.equals("student")) {
                        new Menu(name , UserId).setVisible(true);
                        dispose();
                    }else if (type.equals("teacher")) {
                        new TeacherSide.Menu(name , UserId).setVisible(true);
                        dispose();
                    }
                }



    // Variables declaration
    private javax.swing.JButton btnSend;
    private javax.swing.JButton jButton2;

    private javax.swing.JTextArea inputMsg;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;

    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JList<String> listConnect;
    private javax.swing.JTextArea listMessage;

    // End of variables declaration

    //la fonction de thread pour le button "actualiser"
    @Override
    public void run() {
        try {
            model.clear();
            listClients = server.getListClientByName(name);
            int i=0;
            while(i<listClients.size()){
                model.addElement(listClients.get(i));
                i++;
            }
            listConnect.setModel(model);
        } catch (RemoteException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
}
