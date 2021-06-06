package com.zimonishim.chess.util.networking;

import com.zimonishim.chess.IChessBoardCallback;
import com.zimonishim.chess.Players;
import com.zimonishim.chess.gameObjects.ChessField;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Client {
    public static final String IP = "localhost";
    public static final int PORT = 8000;

    private Socket socket;
    private ObjectOutputStream objectOutputStream;
    private DataOutputStream dataOutputStream;

    private Thread pingServer = new Thread(new PingServer());
    private Thread timeoutCheck = new Thread(new TimeoutCheck());

    private Players player;
    private IChessBoardCallback chessBoardCallback;

    public Client(IChessBoardCallback chessBoardCallback) {
        this.chessBoardCallback = chessBoardCallback;
        try {
            socket = new Socket(IP, PORT);
            System.out.println("Initialised socket.");

            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            System.out.println("Initialised objectInputStream.");
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("Initialised objectOutputStream.");

            player = (objectInputStream.readInt() == 0) ? Players.WHITE : Players.BLACK;

            new Thread(() -> {
                while (true) {
                    try {
                        List<ChessField> chessFieldArrayList = (ArrayList<ChessField>) objectInputStream.readObject();
                        lastPingReceived = System.currentTimeMillis();
                        // Null list is a client connection ping.
                        if (chessFieldArrayList != null) {
                            System.out.println("Received new board state: " + chessFieldArrayList);

                            // The list being empty means that the server lost connection with the other client and this client should win.
                            if (chessFieldArrayList.size() == 0) {
                                // This client should win now.
                                chessBoardCallback.endGame(player);
                                // TODO Send a message in the chat window for the statement below?
                                System.out.println("Other client disconnected. YOU WON!");
                                stopConnectionCheck();
                                break;

                            } else {
                                chessBoardCallback.setChessFields(chessFieldArrayList);
                                chessBoardCallback.checkGameState();
                                chessBoardCallback.switchTurn();
                            }
                        } else {
                            System.out.println("Received a ping");
                        }

                    } catch (ClassNotFoundException | IOException exception) {
                        exception.printStackTrace();

                        // If a problem occured in receiving the data then call a draw and stop trying to read new data.
                        System.out.println("try catch resulting in draw");
                        callDraw();
                        break;
                    }
                }
            }).start();

            pingServer.start();
            timeoutCheck.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {

            try {
                Socket socketChat = new Socket(IP, PORT);
                System.out.println("Initialised socketChat.");

                DataInputStream dataInputStream = new DataInputStream(socketChat.getInputStream());
                System.out.println("Initialised dataInputStream.");
                dataOutputStream = new DataOutputStream(socketChat.getOutputStream());
                System.out.println("Initialised dataOutputStream.");

                while (true) {
                    String s = dataInputStream.readUTF();
                    ChatLogHandler.sendMessage((player == Players.WHITE) ? Players.BLACK : Players.WHITE, s);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }).start();
    }

    public void sendChatMessage(Players player, String s) {
        ChatLogHandler.sendMessage(player, s);
        try {
            dataOutputStream.writeUTF(s);
            dataOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendChessFields(IChessBoardCallback chessBoardCallback) {
        try {
            System.out.println("Sent new board state: " + chessBoardCallback.getChessFields());
            objectOutputStream.writeObject(chessBoardCallback.getChessFields());
            objectOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int pingTimeMs = 5000;

    /**
     * Keep on sending a ping of a blank chessfield so the server and other client know that there's a connection.
     */
    private class PingServer implements Runnable {
        @Override
        public void run() {
            long systemTime = System.currentTimeMillis();
            while (true) {
                // A ping every 5 seconds.
                if (System.currentTimeMillis() - systemTime >= pingTimeMs) {
                    systemTime = System.currentTimeMillis();
                    System.out.println("Sending a ping");
                    try {
                        // Null object is the ping.
                        objectOutputStream.writeObject(null);
                        objectOutputStream.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private long lastPingReceived = System.currentTimeMillis();

    /**
     * Constantly check if the timeout time has been crossed.
     */
    private class TimeoutCheck implements Runnable {
        @Override
        public void run() {
            while (true) {
                if (System.currentTimeMillis() - lastPingReceived > 3 * pingTimeMs) {
                    // Server connection broken, so call a draw.
                    System.out.println("Timeout resulting in draw");
                    callDraw();
                }

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void stopConnectionCheck() {
        pingServer.stop();
        timeoutCheck.stop();
    }

    public void callDraw() {
        // Close the socket, networking is no longer needed.
        System.out.println("Lost connection with the server. IT'S A DRAW!");
        chessBoardCallback.callDraw();
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        stopConnectionCheck();
    }

    public Players getPlayer() {
        return this.player;
    }
}