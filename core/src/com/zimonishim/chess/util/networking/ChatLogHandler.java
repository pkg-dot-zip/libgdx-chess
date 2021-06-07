package com.zimonishim.chess.util.networking;

import com.zimonishim.chess.Players;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Contains methods related to saving the ChatLog and/or processing messages and applying the proper formatting.
 */
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

    /**
     * Creates a chatLog file in the user's documents folder and writes all the messages to it.
     * This will always be called upon closing the application window of the MainGame.
     */
    public static void printChatLog() {
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
        System.out.println("Printed chatLog.");
    }

    /**
     * Sends test messages used for debugging. These will be also printed in the ChatLog found in the user's documents folder.
     */
    public static void debugChatTest(){
        ChatLogHandler.sendMessage(Players.WHITE, "Hello fooMan!");
        ChatLogHandler.sendMessage(Players.BLACK, "Hello other fooMan, I am a human!");
        ChatLogHandler.sendMessage(Players.WHITE, "That makes sense, seeing how you sound like one!");
        ChatLogHandler.sendMessage(Players.BLACK, "Affirmative. Beep Boop Beep Boop");
    }
}