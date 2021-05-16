package com.zimonishim.chess.gameObjects.chessPieces;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.zimonishim.chess.IDrawCallback;
import com.zimonishim.chess.Players;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static com.zimonishim.chess.util.FilePathHandler.chessPieceTexturesPath;

public class Knight extends ChessPiece {
    public Knight(IDrawCallback drawCallback, Players player) {
        super(drawCallback, player);
    }

    @Override
    protected Texture getPlayerTexture(Players player) {
        if (player == Players.WHITE) {
            return new Texture(Gdx.files.internal(chessPieceTexturesPath + "/white_knight.png"));
        } else {
            return new Texture(Gdx.files.internal(chessPieceTexturesPath + "/black_knight.png"));
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