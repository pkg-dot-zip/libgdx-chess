package com.zimonishim.chess.gameObjects.chessPieces;

import com.zimonishim.chess.Players;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static com.zimonishim.chess.util.FilePathHandler.chessPieceTexturesPath;

public class King extends ChessPiece {
    public King(Players player) {
        super(player);
    }

    @Override
    protected String getPlayerTexture(Players player) {
        if (player == Players.WHITE){
            return chessPieceTexturesPath + "/white_king.png";
        } else {
            return chessPieceTexturesPath + "/black_king.png";
        }
    }

    //TODO: Don't allow moving there if another piece is there. We can use isMoveAllowed for that.
    @Override
    public Set<int[]> getPossibleMoves() {
        return new HashSet<>(Arrays.asList(
                new int[]{0, 1},
                new int[]{0, -1},
                new int[]{1, 0},
                new int[]{-1, 0}
        ));
    }
}