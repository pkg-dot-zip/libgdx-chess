package com.zimonishim.chess.util.networking;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(Client.PORT);

            new Thread(() -> {

                try {
                    Socket socketGame1 = serverSocket.accept();
                    System.out.println("Accepted socketGame1.");
                    Socket socketChat1 = serverSocket.accept();
                    System.out.println("Accepted socketChat1.");
                    Socket socketGame2 = serverSocket.accept();
                    System.out.println("Accepted socketGame2.");
                    Socket socketChat2 = serverSocket.accept();
                    System.out.println("Accepted socketChat2.");


                    ObjectOutputStream objectOutputStream1 = new ObjectOutputStream(socketGame1.getOutputStream());
                    ObjectInputStream objectInputStream1 = new ObjectInputStream(socketGame1.getInputStream());
                    ObjectOutputStream objectOutputStream2 = new ObjectOutputStream(socketGame2.getOutputStream());
                    ObjectInputStream objectInputStream2 = new ObjectInputStream(socketGame2.getInputStream());

                    //TODO: Fix issue. Sometimes it will block here.

                    objectOutputStream1.writeInt(0);
                    objectOutputStream1.flush();
                    objectOutputStream2.writeInt(1);
                    objectOutputStream2.flush();
                    System.out.println("Initialised input and output streams for OBJECTS.");

                    DataOutputStream dataOutputStream1 = new DataOutputStream(socketChat1.getOutputStream());
                    DataInputStream dataInputStream1 = new DataInputStream(socketChat1.getInputStream());
                    DataOutputStream dataOutputStream2 = new DataOutputStream(socketChat2.getOutputStream());
                    DataInputStream dataInputStream2 = new DataInputStream(socketChat2.getInputStream());
                    System.out.println("Initialised input and output streams for UTF.");

                    new Thread(() -> {
                        while (true) {
                            try {
                                objectOutputStream1.writeObject(objectInputStream2.readObject());
                                System.out.println("Sent object from socket 2 to 1.");
                            } catch (ClassNotFoundException | IOException classNotFoundException) {
                                classNotFoundException.printStackTrace();
                            }
                        }
                    }).start();

                    new Thread(() -> {
                        while (true) {
                            try {
                                dataOutputStream1.writeUTF(dataInputStream2.readUTF());
                                System.out.println("Sent object from socket 2 to 1.");
                            } catch (IOException classNotFoundException) {
                                classNotFoundException.printStackTrace();
                            }
                        }
                    }).start();

                    new Thread(() -> {
                        while (true) {
                            try {
                                objectOutputStream2.writeObject(objectInputStream1.readObject());
                                System.out.println("Sent object from socket 1 to 2.");
                            } catch (ClassNotFoundException | IOException classNotFoundException) {
                                classNotFoundException.printStackTrace();
                            }
                        }
                    }).start();

                    new Thread(() -> {
                        while (true) {
                            try {
                                dataOutputStream2.writeUTF(dataInputStream1.readUTF());
                                System.out.println("Sent UTF from socket 1 to 2.");
                            } catch (IOException classNotFoundException) {
                                classNotFoundException.printStackTrace();
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