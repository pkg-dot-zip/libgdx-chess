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

                    new Thread(() -> {

                        try {
                            ObjectInputStream objectInputStream = new ObjectInputStream(socket1.getInputStream());
                            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket2.getOutputStream());

                            while (true){

                                try {
                                    objectOutputStream.writeObject(objectInputStream.readObject());
                                    System.out.println("Send object from socket 2 to 1.");
                                } catch (ClassNotFoundException classNotFoundException) {
                                    classNotFoundException.printStackTrace();
                                }

                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }).start();

                    new Thread(() -> {

                        try {
                            ObjectInputStream objectInputStream = new ObjectInputStream(socket2.getInputStream());
                            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket1.getOutputStream());

                            while (true){

                                try {
                                    objectOutputStream.writeObject(objectInputStream.readObject());
                                    System.out.println("Send object from socket 1 to 2.");
                                } catch (ClassNotFoundException classNotFoundException) {
                                    classNotFoundException.printStackTrace();
                                }

                            }

                        } catch (IOException e) {
                            e.printStackTrace();
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