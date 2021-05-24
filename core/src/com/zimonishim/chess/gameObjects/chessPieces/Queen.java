package com.zimonishim.chess.gameObjects.chessPieces;

import com.zimonishim.chess.ChessBoard;
import com.zimonishim.chess.Players;

import java.util.HashSet;
import java.util.Set;

import static com.zimonishim.chess.util.FilePathHandler.chessPieceTexturesPath;

public class Queen extends ChessPiece {
    public Queen(Players player) {
        super(player);
    }

    @Override
    protected String getPlayerTexture(Players player) {
        if (player == Players.WHITE){
            return chessPieceTexturesPath + "/white_queen.png";
        } else {
            return chessPieceTexturesPath + "/black_queen.png";
        }
    }

    @Override
    public Set<int[]> getPossibleMoves() {
        Set<int[]> set = new HashSet<>();

        for (int i = 1; i < ChessBoard.fieldsAmountX; ++i){
            //Diagonal.
            set.add(new int[]{i, i});
            set.add(new int[]{-i, -i});
            set.add(new int[]{-i, i});
            set.add(new int[]{i, -i});

            //Horizontal.
            set.add(new int[]{i, 0});
            set.add(new int[]{-i, 0});

            //Vertical.
            set.add(new int[]{0, i});
            set.add(new int[]{0, -i});
        }

        return set;
    }
}