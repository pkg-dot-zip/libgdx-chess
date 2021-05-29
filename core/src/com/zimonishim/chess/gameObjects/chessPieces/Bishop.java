package com.zimonishim.chess.gameObjects.chessPieces;

import com.zimonishim.chess.ChessBoard;
import com.zimonishim.chess.IChessBoardCallback;
import com.zimonishim.chess.Players;
import com.zimonishim.chess.gameObjects.ChessField;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static com.zimonishim.chess.util.FilePathHandler.chessPieceTexturesPath;

public class Bishop extends ChessPiece {
    public Bishop(ChessField chessField, IChessBoardCallback chessBoardCallback, Players player) {
        super(chessField, chessBoardCallback, player);
    }

    @Override
    protected String getPlayerTexture(Players player) {
        if (player == Players.WHITE){
            return chessPieceTexturesPath + "/white_bishop.png";
        } else {
            return chessPieceTexturesPath + "/black_bishop.png";
        }
    }

    @Override
    public Set<int[]> getPossibleMoves() {
        Set<int[]> set = new HashSet<>();

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

        return set;
    }
}