package com.zimonishim.chess.gameObjects.chessPieces;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.zimonishim.chess.IDrawCallback;
import com.zimonishim.chess.Players;

import java.util.Set;

import static com.zimonishim.chess.util.FilePathHandler.chessPieceTexturesPath;

public class Bishop extends ChessPiece {
    public Bishop(IDrawCallback drawCallback, Players player) {
        super(drawCallback, player);
    }

    @Override
    protected Texture getPlayerTexture(Players player) {
        if (player == Players.WHITE){
            return new Texture(Gdx.files.internal(chessPieceTexturesPath + "/white_bishop.png"));
        } else {
            return new Texture(Gdx.files.internal(chessPieceTexturesPath + "/black_bishop.png"));
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