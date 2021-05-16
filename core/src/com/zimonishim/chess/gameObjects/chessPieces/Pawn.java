package com.zimonishim.chess.gameObjects.chessPieces;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.zimonishim.chess.IDrawCallback;
import com.zimonishim.chess.Players;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static com.zimonishim.chess.util.FilePathHandler.chessPieceTexturesPath;

public class Pawn extends ChessPiece {

    private boolean isFirstMove = true;

    public Pawn(IDrawCallback drawCallback, Players player) {
        super(drawCallback, player);
    }

    @Override
    protected Texture getPlayerTexture(Players player) {
        if (player == Players.WHITE){
            return new Texture(Gdx.files.internal(chessPieceTexturesPath + "/white_pawn.png"));
        } else {
            return new Texture(Gdx.files.internal(chessPieceTexturesPath + "/black_pawn.png"));
        }
    }

    @Override
    public Set<int[]> getPossibleMoves() {
        //TODO: Only allow one square to be moved if this is NOT the first move.
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