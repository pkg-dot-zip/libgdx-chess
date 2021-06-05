package com.zimonishim.chess.util.networking;

import com.zimonishim.chess.Players;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ChatLogHandler {

    public static List<String> chatMessages = new ArrayList<>();

    public static void sendMessage(Players player, String message){
        if (player == null){
            return;
        }

        chatMessages.add(
                LocalDateTime.now().toLocalTime().toString() +
                " " + player.name() +
                ": " + message
        );
    }

    public static void printChatLog() { //Is called on the exit of the game. Will always be created.
        String folderToPutFile = FileSystemView.getFileSystemView().getDefaultDirectory().getAbsolutePath() + "/"; //Will point towards the user's desktop directory.
        String logFileName = folderToPutFile + "ChatLog-" + LocalDateTime.now().getYear() + "-" + LocalDateTime.now().getMonth() + "-" + LocalDateTime.now().getDayOfMonth() + ".txt";

        try {
            FileWriter fileWriter = new FileWriter(new File(logFileName));
            fileWriter.write("ChatLog of LibGDX-Chess by ZimonIsHim and Medkam.\n");
            fileWriter.write("ChatLog date: " + LocalDateTime.now().toString() + "\n\n");

            for (String s : chatMessages){
                fileWriter.write(s + "\n");
            }

            fileWriter.write("\n\nEnd of ChatLog.");
            fileWriter.close();
        } catch (IOException e){
            e.printStackTrace();
        }
        System.out.println("Printed chat log");
    }

    public static void debugChatTest(){
        ChatLogHandler.sendMessage(Players.WHITE, "Hello fooMan!");
        ChatLogHandler.sendMessage(Players.BLACK, "Hello other fooMan, I am a human!");
        ChatLogHandler.sendMessage(Players.WHITE, "That makes sense, seeing how you sound like one!");
        ChatLogHandler.sendMessage(Players.BLACK, "Affirmative. Beep Boop Beep Boop");
    }
}