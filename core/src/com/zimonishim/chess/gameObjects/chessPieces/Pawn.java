package com.zimonishim.chess.gameObjects.chessPieces;

import com.zimonishim.chess.Players;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static com.zimonishim.chess.util.FilePathHandler.chessPieceTexturesPath;

public class Pawn extends ChessPiece {

    private boolean isFirstMove = true;

    public Pawn(Players player) {
        super(player);
    }

    @Override
    protected String getPlayerTexture(Players player) {
        if (player == Players.WHITE){
            return chessPieceTexturesPath + "/white_pawn.png";
        } else {
            return chessPieceTexturesPath + "/black_pawn.png";
        }
    }

    @Override
    public Set<int[]> getPossibleMoves() {
        if (isFirstMove){
            return new HashSet<>(Arrays.asList(new int[]{0, 1}, new int[]{0, 2}));
        } else {
            return new HashSet<>(Collections.singletonList(new int[]{0, 1}));
        }
    }

    @Override
    public void onMove() {
        this.isFirstMove = false;
    }
}