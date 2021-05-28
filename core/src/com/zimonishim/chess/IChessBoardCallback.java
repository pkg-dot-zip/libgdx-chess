package com.zimonishim.chess;

import com.zimonishim.chess.gameObjects.ChessField;
import com.zimonishim.chess.gameObjects.chessPieces.ChessPiece;

import java.util.List;

public interface IChessBoardCallback {


    public ChessField getChessField(ChessFieldLetter letter, int number);
    public ChessPiece getChessPiece(ChessFieldLetter letter, int number);

    public List<ChessField> getChessFields();
    public void setChessFields(List<ChessField> chessFields);

    public Players getTurn();
    public void switchTurn();
}