package com.zimonishim.chess.gameObjects.chessPieces;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.zimonishim.chess.IDrawCallback;
import com.zimonishim.chess.Players;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static com.zimonishim.chess.util.FilePathHandler.chessPieceTexturesPath;

public class King extends ChessPiece {
    public King(IDrawCallback drawCallback, Players player) {
        super(drawCallback, player);
    }

    @Override
    protected Texture getPlayerTexture(Players player) {
        if (player == Players.WHITE){
            return new Texture(Gdx.files.internal(chessPieceTexturesPath + "/white_king.png"));
        } else {
            return new Texture(Gdx.files.internal(chessPieceTexturesPath + "/black_king.png"));
        }
    }

    @Override
    public boolean isMoveAllowed() {
        return false;
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