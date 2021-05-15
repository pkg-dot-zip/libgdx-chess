package com.zimonishim.chess.gameObjects.chessPieces;

import com.badlogic.gdx.graphics.Texture;
import com.zimonishim.chess.IDrawCallback;
import com.zimonishim.chess.Players;

import java.util.Set;

public abstract class ChessPiece {

    protected IDrawCallback drawCallback;
    protected Texture texture;

    public ChessPiece(IDrawCallback drawCallback, Players player) {
        this.drawCallback = drawCallback;
        this.texture = getPlayerTexture(player);
    }

    protected abstract Texture getPlayerTexture(Players player);
    public abstract boolean isMoveAllowed();
    public abstract Set getPossibleMoves();

    public Texture getTexture() {
        return this.texture;
    }

    public void dispose(){
        this.texture.dispose();
    }
}