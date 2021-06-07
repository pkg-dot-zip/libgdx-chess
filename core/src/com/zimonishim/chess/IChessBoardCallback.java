package com.zimonishim.chess;

import com.zimonishim.chess.gameObjects.ChessField;

import java.util.List;

public interface IChessBoardCallback {


    ChessField getChessField(ChessFieldLetter letter, int number);
    List<ChessField> getChessFields();
    void setChessFields(List<ChessField> chessFields);

    Players getTurn();
    void switchTurn();

    /**
     * Checks whether the kings are present on the board. If none are presents it's a draw,
     * if only one is present the player with the king wins.
     */
    void checkGameState();

    void endGame(Players winner);
    void callDraw();
    GameState getGameState();
    Players getWinner();

    /**
     * Resets all colors and selections.
     */
    void cleanBoard();
}