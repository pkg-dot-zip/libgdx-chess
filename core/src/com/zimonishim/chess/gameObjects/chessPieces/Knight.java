package com.zimonishim.chess.gameObjects.chessPieces;

import com.zimonishim.chess.IChessBoardCallback;
import com.zimonishim.chess.Players;
import com.zimonishim.chess.gameObjects.ChessField;

import java.util.HashSet;
import java.util.Set;

import static com.zimonishim.chess.util.FilePathHandler.blackKnight;
import static com.zimonishim.chess.util.FilePathHandler.whiteKnight;

public class Knight extends ChessPiece {

    public Knight(ChessField chessField, IChessBoardCallback chessBoardCallback, Players player) {
        super(chessField, chessBoardCallback, player);
    }

    @Override
    protected String getPlayerTexture(Players player) {
        return (player == Players.WHITE) ? whiteKnight : blackKnight;
    }

    @Override
    public Set<int[]> getPossibleMoves() {
        //NOTE: Knights ARE allowed to move over pieces.
        //We don't have to check for pieces blocking the path, -> we only have to check the destination.
        Set<int[]> set = new HashSet<>();

        int x = chessField.getPos()[0];
        int y = chessField.getPos()[1];

        ChessField upLeft = chessField.getChessField(chessFields, y + 2, x - 1);
        if ((upLeft != null && upLeft.getChessPiece() == null) || (upLeft != null && upLeft.getChessPiece() != null && upLeft.getChessPiece().getPlayer() != player)) {
            set.add(new int[]{-1, 2});
        }
        ChessField upRight = chessField.getChessField(chessFields, y + 2, x + 1);
        if ((upRight != null && upRight.getChessPiece() == null) || (upRight != null && upRight.getChessPiece() != null && upRight.getChessPiece().getPlayer() != player)) {
            set.add(new int[]{1, 2});
        }
        ChessField rightUp = chessField.getChessField(chessFields, y + 1, x + 2);
        if ((rightUp != null && rightUp.getChessPiece() == null) || (rightUp != null && rightUp.getChessPiece() != null && rightUp.getChessPiece().getPlayer() != player)) {
            set.add(new int[]{2, 1});
        }
        ChessField rightDown = chessField.getChessField(chessFields, y - 1, x + 2);
        if ((rightDown != null && rightDown.getChessPiece() == null) || (rightDown != null && rightDown.getChessPiece() != null && rightDown.getChessPiece().getPlayer() != player)) {
            set.add(new int[]{2, -1});
        }
        ChessField downRight = chessField.getChessField(chessFields, y - 2, x + 1);
        if ((downRight != null && downRight.getChessPiece() == null) || (downRight != null && downRight.getChessPiece() != null && downRight.getChessPiece().getPlayer() != player)) {
            set.add(new int[]{1, -2});
        }
        ChessField downLeft = chessField.getChessField(chessFields, y - 2, x - 1);
        if ((downLeft != null && downLeft.getChessPiece() == null) || (downLeft != null && downLeft.getChessPiece() != null && downLeft.getChessPiece().getPlayer() != player)) {
            set.add(new int[]{-1, -2});
        }
        ChessField leftDown = chessField.getChessField(chessFields, y - 1, x - 2);
        if ((leftDown != null && leftDown.getChessPiece() == null) || (leftDown != null && leftDown.getChessPiece() != null && leftDown.getChessPiece().getPlayer() != player)) {
            set.add(new int[]{-2, -1});
        }
        ChessField leftUp = chessField.getChessField(chessFields, y + 1, x - 2);
        if ((leftUp != null && leftUp.getChessPiece() == null) || (leftUp != null && leftUp.getChessPiece() != null && leftUp.getChessPiece().getPlayer() != player)) {
            set.add(new int[]{-2, 1});
        }

        return set;
    }
}