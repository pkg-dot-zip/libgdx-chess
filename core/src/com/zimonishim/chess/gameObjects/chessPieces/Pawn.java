package com.zimonishim.chess.gameObjects.chessPieces;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.zimonishim.chess.IDrawCallback;
import com.zimonishim.chess.Players;

import java.util.Set;

import static com.zimonishim.chess.util.FilePathHandler.chessPieceTexturesPath;

public class Pawn extends ChessPiece {

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
    public boolean isMoveAllowed() {
        return false;
    }

    @Override
    public Set getPossibleMoves() {
        return null;
    }
}
