package com.zimonishim.chess.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

import java.util.HashMap;
import java.util.Map;

public class SoundHandler {

    private static final Map<String, Sound> sounds = new HashMap<>();

    public static void initSounds(){
        addSound(FilePathHandler.chessPieceMoveSoundPath);
    }

    private static void addSound(String filePath){
        sounds.put(filePath, Gdx.audio.newSound(Gdx.files.internal(filePath)));
    }

    public static void playSound(String filePath){
        sounds.get(filePath).play();
    }

    /** Called when this application should release all resources. */
    public static void dispose(){
        for (Sound sound : sounds.values()){
            sound.dispose();
        }
    }
}