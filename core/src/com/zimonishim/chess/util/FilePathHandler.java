package com.zimonishim.chess.util;

/**
 * Contains filePaths for assets. This includes textures and sounds.
 */
public class FilePathHandler {

    //Textures. //NOTE: These should all be defined the initTextures() method in TextureHandler. That class handles both creation & disposal.
    private static final String texturesPath = "textures";
    public static final String chessPieceTexturesPath = texturesPath + "/chessPieces";
        //ChessPieces.
    private static final String whiteChessPiece = chessPieceTexturesPath + "/white_";
    private static final String blackChessPiece = chessPieceTexturesPath + "/black_";
    public static final String whiteBishop = whiteChessPiece + "bishop.png";
    public static final String blackBishop = blackChessPiece + "bishop.png";
    public static final String whiteKing = whiteChessPiece + "king.png";
    public static final String blackKing = blackChessPiece + "king.png";
    public static final String whiteKnight = whiteChessPiece + "knight.png";
    public static final String blackKnight = blackChessPiece + "knight.png";
    public static final String whitePawn = whiteChessPiece + "pawn.png";
    public static final String blackPawn = blackChessPiece + "pawn.png";
    public static final String whiteQueen = whiteChessPiece + "queen.png";
    public static final String blackQueen = blackChessPiece + "queen.png";
    public static final String whiteRook = whiteChessPiece + "rook.png";
    public static final String blackRook = blackChessPiece + "rook.png";

    //Sounds. //NOTE: These should all be defined the initSounds() method in SoundHandler. That class handles both creation & disposal.
    private static final String soundPath = "sounds";
    public static final String chessPieceMoveSoundPath = soundPath + "/chessPieceMove.mp3";
}