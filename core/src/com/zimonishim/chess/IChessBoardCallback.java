package com.zimonishim.chess;

import com.zimonishim.chess.gameObjects.ChessField;
import com.zimonishim.chess.gameObjects.chessPieces.ChessPiece;

import java.util.ArrayList;

public interface IChessBoardCallback {


    public ChessField getChessField(ChessFieldLetter letter, int number);
    public ChessPiece getChessPiece(ChessFieldLetter letter, int number);

    /**
     * Returns an ArrayList with chessFields.
     * @return  arrayList with chessFields
     */
    public ArrayList<ChessField> getChessFields();
}