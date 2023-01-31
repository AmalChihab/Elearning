package StudentSide;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
import java.util.ArrayList;

public class RecieveFile{
    static ArrayList<MyFile> myFiles = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        int ClassSelected = 0;
        Socket socket ;

        // Used to track the file (jpanel that has the file name in it on a label).
        int fileId = 0;
        // Create a server socket that the server will be listening with.
        ServerSocket serverSocket = new ServerSocket(5000);

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
                    String fileName = new String(fileNameBytes);
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
                            FileOutputStream fileOutputStream = new FileOutputStream("C://Users//pc//Desktop//"+fileToDownload);
                            // Write the actual file data to the file.
                            fileOutputStream.write(fileContentBytes);
                            // Close the stream.
                            fileOutputStream.close();

                            JOptionPane.showMessageDialog(null, " file has been downloaded succesfuly");
                            System.out.println("the file recieved : "+fileName);
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
