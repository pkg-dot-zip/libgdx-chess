package com.zimonishim.chess;

import com.zimonishim.chess.gameObjects.ChessField;
import com.zimonishim.chess.gameObjects.chessPieces.ChessPiece;

import java.util.List;

public interface IChessBoardCallback {


    ChessField getChessField(ChessFieldLetter letter, int number);
    ChessPiece getChessPiece(ChessFieldLetter letter, int number);

    List<ChessField> getChessFields();
    void setChessFields(List<ChessField> chessFields);

    Players getTurn();
    void switchTurn();
    void checkGameState();
    void endGame(Players winner);
    void callDraw();
}