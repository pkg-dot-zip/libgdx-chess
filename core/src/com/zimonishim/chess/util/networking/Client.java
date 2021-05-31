package com.zimonishim.chess.util.networking;

import com.zimonishim.chess.IChessBoardCallback;
import com.zimonishim.chess.Players;
import com.zimonishim.chess.gameObjects.ChessField;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Client {

    //TODO: Refactor since we also use this in Server.
    public static final String IP = "localhost";
    public static final int PORT = 8000;

    private ObjectOutputStream objectOutputStream;

    private Players player;

    public Client(IChessBoardCallback chessBoardCallback) {
        new Thread(() -> {

            try {
                Socket socket = new Socket(IP, PORT);
                System.out.println("Initialised socket.");

                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                System.out.println("Initialised objectInputStream.");
                objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                System.out.println("Initialised objectOutputStream.");

                player = (objectInputStream.readInt() == 0) ? Players.WHITE : Players.BLACK;

                while (true) {
                    try {
                        List<ChessField> chessFieldArrayList = (ArrayList<ChessField>) objectInputStream.readObject();
                        System.out.println("Received new board state: " + chessFieldArrayList);

                        chessBoardCallback.setChessFields(chessFieldArrayList);
                        chessBoardCallback.switchTurn();

                    } catch (ClassNotFoundException classNotFoundException) {
                        classNotFoundException.printStackTrace();
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }).start();
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

    public Players getPlayer() {
        return this.player;
    }
}