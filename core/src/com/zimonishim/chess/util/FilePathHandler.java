package com.zimonishim.chess.util;

/**
 * Contains filePaths for assets. This includes textures and sounds.
 */
public class FilePathHandler {

    //Textures.
    private static final String texturesPath = "textures";
    public static final String chessPieceTexturesPath = texturesPath + "/chessPieces";
    //TODO: Put all paths for chessPieceTextures here.

    //Sounds. //NOTE: These should all be defined the initSounds() method in SoundHandler. That class handles both creation & disposal.
    private static final String soundPath = "sounds";
    public static final String chessPieceMoveSoundPath = soundPath + "/chessPieceMove.mp3";
}