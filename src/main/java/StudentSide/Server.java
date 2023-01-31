package StudentSide;

import javax.swing.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
import java.util.ArrayList;

public class Server {

    // Array list to hold information about the files received.
    static ArrayList<MyFile> myFiles = new ArrayList<>();
    static final int portServer = 1234 ;

     static  Socket socket ;

    static final File[] fileToSend = new File[1];

    static int idClass = 0 ;

    public Server( int id){
        idClass = id ;
    }


    public static void main(String[] args) throws IOException {
        int ClassSelected = 0;

        // Used to track the file (jpanel that has the file name in it on a label).
        int fileId = 0;
        // Create a server socket that the server will be listening with.
        ServerSocket serverSocket = new ServerSocket(portServer);

        // This while loop will run forever so the server will never stop unless the application is closed.
        while (true) {


            try {
                // Wait for a client to connect and when they do create a socket to communicate with them.
                 socket = serverSocket.accept();

                // Stream to receive data from the client through the socket.
                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());

                // Read the size of the file name so know when to stop reading.
                int fileNameLength = dataInputStream.readInt();
                // If the file exists
                if (fileNameLength > 0) {
                    // Byte array to hold name of file.
                    byte[] fileNameBytes = new byte[fileNameLength];
                    // Read from the input stream into the byte array.
                    dataInputStream.readFully(fileNameBytes, 0, fileNameBytes.length);
                    // Create the file name from the byte array.
                    String Name = new String(fileNameBytes);
                    String fileName = "";

                    if (Name.contains(",")) {
                        String[] tab = Name.split(",");
                        fileName = tab[1];
                        ClassSelected = Integer.parseInt(tab[0]);
                    }
                    // Read how much data to expect for the actual content of the file.
                    int fileContentLength = dataInputStream.readInt();
                    // If the file exists.
                    if (fileContentLength > 0) {
                        // Array to hold the file data.
                        byte[] fileContentBytes = new byte[fileContentLength];
                        // Read from the input stream into the fileContentBytes array.
                        dataInputStream.readFully(fileContentBytes, 0, fileContentBytes.length);
                        // Panel to hold the picture and file name.
                        // Add the new file to the array list which holds all our data.
                        myFiles.add(new MyFile(fileId, fileName, fileContentBytes, getFileExtension(fileName)));
                        // Increment the fileId for the next file to be received.
                        fileId++;

                        File fileToDownload = new File(fileName);
                        try {
                            // Create a stream to write data to the file.
                            FileOutputStream fileOutputStream = new FileOutputStream(fileToDownload);
                            // Write the actual file data to the file.
                            fileOutputStream.write(fileContentBytes);
                            // Close the stream.
                            fileOutputStream.close();

                            System.out.println("the path : " + fileToDownload.getAbsolutePath());
                            // Get rid of the jFrame. after the user clicked yes.

                            try{

                                Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/elearningapp","root","");
                                String sql= "insert into files(path , NumClass) values(?,?)";

                                PreparedStatement ptstmt= conn.prepareStatement(sql);
                                ptstmt.setString(1, fileToDownload.getPath());
                                ptstmt.setInt(2, ClassSelected);


                                ptstmt.executeUpdate();
                                System.out.println("enregistrement bd succes");
                                conn.close();

                            }catch (Exception e){
                                JOptionPane.showMessageDialog(null, e);
                            }


                        }catch(IOException e){
                            e.printStackTrace();
                        }
                    }
                }
            }catch(IOException e){
                e.printStackTrace();
            }

            }

    }

    /**
     * @param fileName
     * @return The extension type of the file.
     */
    public static String getFileExtension(String fileName) {
        // Get the file type by using the last occurence of . (for example aboutMe.txt returns txt).
        // Will have issues with files like myFile.tar.gz.
        int i = fileName.lastIndexOf('.');
        // If there is an extension.
        if (i > 0) {
            // Set the extension to the extension of the filename.
            return fileName.substring(i + 1);
        } else {
            return "No extension found.";
        }
    }



}
