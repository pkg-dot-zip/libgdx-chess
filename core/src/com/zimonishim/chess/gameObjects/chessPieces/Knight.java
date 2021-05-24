package com.zimonishim.chess.gameObjects.chessPieces;

import com.zimonishim.chess.Players;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static com.zimonishim.chess.util.FilePathHandler.chessPieceTexturesPath;

public class Knight extends ChessPiece {
    public Knight(Players player) {
        super(player);
    }

    @Override
    protected String getPlayerTexture(Players player) {
        if (player == Players.WHITE) {
            return chessPieceTexturesPath + "/white_knight.png";
        } else {
            return chessPieceTexturesPath + "/black_knight.png";
        }
    }

    @Override
    public Set<int[]> getPossibleMoves() {
        //NOTE: Knights ARE allowed to move over pieces.
        //We don't have to check for pieces blocking the path, -> we only have to check the destination.
        return new HashSet<>(Arrays.asList(
                new int[]{2, 1},
                new int[]{2, -1},
                new int[]{1, 2},
                new int[]{1, -2},
                new int[]{-2, 1},
                new int[]{-2, -1},
                new int[]{-1, 2},
                new int[]{-1, -2}
        ));
    }
}