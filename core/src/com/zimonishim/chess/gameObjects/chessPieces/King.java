package com.zimonishim.chess.gameObjects.chessPieces;

import com.zimonishim.chess.IChessBoardCallback;
import com.zimonishim.chess.Players;
import com.zimonishim.chess.gameObjects.ChessField;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static com.zimonishim.chess.util.FilePathHandler.chessPieceTexturesPath;

public class King extends ChessPiece {
    public King(ChessField chessField, IChessBoardCallback chessBoardCallback, Players player) {
        super(chessField, chessBoardCallback, player);
    }

    @Override
    protected String getPlayerTexture(Players player) {
        if (player == Players.WHITE) {
            return chessPieceTexturesPath + "/white_king.png";
        } else {
            return chessPieceTexturesPath + "/black_king.png";
        }
    }

    //TODO: Don't allow moving there if another piece is there. We can use isMoveAllowed for that.
    @Override
    public Set<int[]> getPossibleMoves() {
        Set<int[]> set = new HashSet<>();

        int x = chessField.getPos()[0];
        int y = chessField.getPos()[1];

        ChessField top = chessField.getChessField(chessFields, y + 1, x);
        if ((top != null && top.getChessPiece() == null) || (top != null && top.getChessPiece() != null && top.getChessPiece().getPlayer() != player)) {
            set.add(new int[]{0, 1});
        }
        ChessField bottom = chessField.getChessField(chessFields, y - 1, x);
        if ((bottom != null && bottom.getChessPiece() == null) || (bottom != null && bottom.getChessPiece() != null && bottom.getChessPiece().getPlayer() != player)) {
            set.add(new int[]{0, -1});
        }
        ChessField left = chessField.getChessField(chessFields, y, x + 1);
        if ((left != null && left.getChessPiece() == null) || (left != null && left.getChessPiece() != null && left.getChessPiece().getPlayer() != player)) {
            set.add(new int[]{1, 0});
        }
        ChessField right = chessField.getChessField(chessFields, y, x - 1);
        if ((right != null && right.getChessPiece() == null) || (right != null && right.getChessPiece() != null && right.getChessPiece().getPlayer() != player)) {
            set.add(new int[]{-1, 0});
        }

        return set;
    }
}