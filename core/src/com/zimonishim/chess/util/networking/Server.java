package com.zimonishim.chess.util.networking;

import com.zimonishim.chess.gameObjects.ChessField;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    private static ObjectOutputStream objectOutputStreamSocketOne;
    private static ObjectOutputStream objectOutputStreamSocketTwo;

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(Client.PORT);

            Socket socket1 = serverSocket.accept();
            System.out.println("Accepted socket1.");
            Socket socket2 = serverSocket.accept();
            System.out.println("Accepted socket2.");

            objectOutputStreamSocketOne = new ObjectOutputStream(socket1.getOutputStream());
            ObjectInputStream objectInputStream1 = new ObjectInputStream(socket1.getInputStream());
            objectOutputStreamSocketTwo = new ObjectOutputStream(socket2.getOutputStream());
            ObjectInputStream objectInputStream2 = new ObjectInputStream(socket2.getInputStream());
            System.out.println("Initialised input and output streams.");

            objectOutputStreamSocketOne.writeInt(0);
            objectOutputStreamSocketOne.flush();
            objectOutputStreamSocketTwo.writeInt(1);
            objectOutputStreamSocketTwo.flush();

            new Thread(() -> {
                while (true) {
                    ArrayList<ChessField> chessFields;
                    // Read from socket 2.
                    try {
                        chessFields = (ArrayList<ChessField>) objectInputStream2.readObject();

                        System.out.println("Read input from socket 2");
                    } catch (ClassNotFoundException | IOException classNotFoundException) {
                        // One of the sockets has been closed.
                        System.out.println("Connection with socket 2 broken");
                        connectionBrokenWithSocketTwo();
                        break;
                    }

                    // Write to socket one.
                    try {
                        objectOutputStreamSocketOne.writeObject(chessFields);
                        System.out.println("Sent output to socket 1");
                    } catch (IOException e) {
                        // One of the sockets has been closed.
                        System.out.println("Connection with socket 1 broken");
                        connectionBrokenWithSocketOne();
                        break;
                    }
                }
            }).start();

            new Thread(() -> {
                while (true) {
                    ArrayList<ChessField> chessFields;

                    // Read from socket 1.
                    try {
                        chessFields = (ArrayList<ChessField>) objectInputStream1.readObject();

                        System.out.println("Read input from socket 1");
                    } catch (ClassNotFoundException | IOException classNotFoundException) {
                        // One of the sockets has been closed
                        System.out.println("Connection with socket 1 broken");
                        connectionBrokenWithSocketOne();
                        break;
                    }

                    // Write to socket 2.
                    try {
                        objectOutputStreamSocketTwo.writeObject(chessFields);
                        System.out.println("Sent output to socket 2");
                    } catch (IOException e) {
                        // One of the sockets has been closed.
                        System.out.println("Connection with socket 2 broken");
                        connectionBrokenWithSocketTwo();
                        break;
                    }
                }
            }).start();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static synchronized void connectionBrokenWithSocketOne() {
        // Socket one lost connection, so make socket two win.
        try {
            System.out.println("Trying to make socket 1 win");
            objectOutputStreamSocketTwo.writeObject(new ArrayList<ChessField>());
            objectOutputStreamSocketTwo.flush();
            System.out.println("Succesfully sent message to socket two to win");
        } catch (IOException e) {
            System.out.println("Failed in sending message to socket two to win");
        }
    }

    public static synchronized void connectionBrokenWithSocketTwo() {
        // Socket two lost connection, so make socket one win.
        try {
            System.out.println("Trying to make socket 2 win");
            objectOutputStreamSocketOne.writeObject(new ArrayList<ChessField>());
            objectOutputStreamSocketOne.flush();
            System.out.println("Succesfully sent message to socket one to win");
        } catch (IOException e) {
            System.out.println("Failed in sending message to socket one to win");
        }
    }
}