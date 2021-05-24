package com.zimonishim.chess.gameObjects.chessPieces;

import com.zimonishim.chess.ChessBoard;
import com.zimonishim.chess.Players;

import java.util.HashSet;
import java.util.Set;

import static com.zimonishim.chess.util.FilePathHandler.chessPieceTexturesPath;

public class Bishop extends ChessPiece {
    public Bishop(Players player) {
        super(player);
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

        for (int i = 1; i < ChessBoard.fieldsAmountX; ++i){
            set.add(new int[]{i, i});
            set.add(new int[]{-i, -i});
            set.add(new int[]{-i, i});
            set.add(new int[]{i, -i});
        }

        return set;
    }
}