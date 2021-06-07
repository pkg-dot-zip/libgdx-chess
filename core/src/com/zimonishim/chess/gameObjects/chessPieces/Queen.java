package com.zimonishim.chess.gameObjects.chessPieces;

import com.zimonishim.chess.ChessBoard;
import com.zimonishim.chess.IChessBoardCallback;
import com.zimonishim.chess.Players;
import com.zimonishim.chess.gameObjects.ChessField;

import java.util.HashSet;
import java.util.Set;

import static com.zimonishim.chess.util.FilePathHandler.blackQueen;
import static com.zimonishim.chess.util.FilePathHandler.whiteQueen;

public class Queen extends ChessPiece {

    public Queen(ChessField chessField, IChessBoardCallback chessBoardCallback, Players player) {
        super(chessField, chessBoardCallback, player);
    }

    @Override
    protected String getPlayerTexture(Players player) {
        return (player == Players.WHITE) ? whiteQueen : blackQueen;
    }

    @Override
    public Set<int[]> getPossibleMoves() {
        Set<int[]> set = new HashSet<>();

        //Diagonal movement.
        boolean allowTopLeft = true;
        boolean allowTopRight = true;
        boolean allowBottomLeft = true;
        boolean allowBottomRight = true;

        for (int i = 1; i < ChessBoard.fieldsAmountX; ++i){
            int x = chessField.getPos()[0];
            int y = chessField.getPos()[1];

            if (allowTopRight) {
                ChessField topRight = chessField.getChessField(chessFields, y + i, x + i);
                if (topRight != null && topRight.getChessPiece() == null) {
                    set.add(new int[]{i, i});
                } else {
                    allowTopRight = false;
                    if (topRight != null && topRight.getChessPiece() != null && topRight.getChessPiece().getPlayer() != player) set.add(new int[]{i, i});
                }
            }

            if (allowBottomLeft) {
                ChessField bottomLeft = chessField.getChessField(chessFields, y - i, x - i);
                if (bottomLeft != null && bottomLeft.getChessPiece() == null) {
                    set.add(new int[]{-i, -i});
                } else {
                    allowBottomLeft = false;
                    if (bottomLeft != null && bottomLeft.getChessPiece() != null && bottomLeft.getChessPiece().getPlayer() != player) set.add(new int[]{-i, -i});
                }
            }

            if (allowBottomRight) {
                ChessField bottomRight = chessField.getChessField(chessFields, y - i, x + i);
                if (bottomRight != null && bottomRight.getChessPiece() == null) {
                    set.add(new int[]{i, -i});
                } else {
                    allowBottomRight = false;
                    if (bottomRight != null && bottomRight.getChessPiece() != null && bottomRight.getChessPiece().getPlayer() != player) set.add(new int[]{i, -i});
                }
            }

            if (allowTopLeft) {
                ChessField topLeft = chessField.getChessField(chessFields, y + i, x - i);
                if (topLeft != null && topLeft.getChessPiece() == null) {
                    set.add(new int[]{-i, i});
                } else {
                    allowTopLeft = false;
                    if (topLeft != null && topLeft.getChessPiece() != null && topLeft.getChessPiece().getPlayer() != player) set.add(new int[]{-i, i});
                }
            }
        }

        //Horizontal and vertical movement.
        boolean allowTop = true;
        boolean allowRight = true;
        boolean allowBottom = true;
        boolean allowLeft = true;

        for (int i = 1; i < ChessBoard.fieldsAmountX; ++i){
            int x = chessField.getPos()[0];
            int y = chessField.getPos()[1];

            if (allowRight) {
                ChessField right = chessField.getChessField(chessFields, y, x + i);
                if (right != null && right.getChessPiece() == null) {
                    set.add(new int[]{i, 0});
                } else {
                    allowRight = false;
                    if (right != null && right.getChessPiece() != null && right.getChessPiece().getPlayer() != player) set.add(new int[]{i, 0});
                }
            }

            if (allowBottom) {
                ChessField bottom = chessField.getChessField(chessFields, y - i, x);
                if (bottom != null && bottom.getChessPiece() == null) {
                    set.add(new int[]{0, -i});
                } else {
                    allowBottom = false;
                    if (bottom != null && bottom.getChessPiece() != null && bottom.getChessPiece().getPlayer() != player) set.add(new int[]{0, -i});
                }
            }

            if (allowLeft) {
                ChessField left = chessField.getChessField(chessFields, y, x - i);
                if (left != null && left.getChessPiece() == null) {
                    set.add(new int[]{-i, 0});
                } else {
                    allowLeft = false;
                    if (left != null && left.getChessPiece() != null && left.getChessPiece().getPlayer() != player) set.add(new int[]{-i, 0});
                }
            }

            if (allowTop) {
                ChessField top = chessField.getChessField(chessFields, y + i, x);
                if (top != null && top.getChessPiece() == null) {
                    set.add(new int[]{0, i});
                } else {
                    allowTop = false;
                    if (top != null && top.getChessPiece() != null && top.getChessPiece().getPlayer() != player) set.add(new int[]{0, i});
                }
            }
        }

        return set;
    }
}