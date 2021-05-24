package com.zimonishim.chess.util.networking;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(Client.PORT);

            new Thread(() -> {

                try {
                    Socket socket1 = serverSocket.accept();
                    System.out.println("Accepted socket1.");
                    Socket socket2 = serverSocket.accept();
                    System.out.println("Accepted socket2.");

                    ObjectOutputStream objectOutputStream1 = new ObjectOutputStream(socket1.getOutputStream());
                    ObjectInputStream objectInputStream1 = new ObjectInputStream(socket1.getInputStream());
                    ObjectOutputStream objectOutputStream2 = new ObjectOutputStream(socket2.getOutputStream());
                    ObjectInputStream objectInputStream2 = new ObjectInputStream(socket2.getInputStream());
                    System.out.println("Initialised input and output streams");


                    new Thread(() -> {
                        while (true) {

                            try {
                                objectOutputStream1.writeObject(objectInputStream2.readObject());
                                System.out.println("Send object from socket 2 to 1.");
                            } catch (ClassNotFoundException classNotFoundException) {
                                classNotFoundException.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }
                    }).start();

                    new Thread(() -> {
                        while (true) {

                            try {
                                objectOutputStream2.writeObject(objectInputStream1.readObject());
                                System.out.println("Send object from socket 1 to 2.");
                            } catch (ClassNotFoundException classNotFoundException) {
                                classNotFoundException.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }
                    }).start();

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}