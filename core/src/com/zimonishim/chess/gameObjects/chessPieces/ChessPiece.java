package com.zimonishim.chess.gameObjects.chessPieces;

import com.badlogic.gdx.graphics.Texture;
import com.zimonishim.chess.IDrawCallback;
import com.zimonishim.chess.Players;

import java.util.Set;

public abstract class ChessPiece {

    protected IDrawCallback drawCallback;
    protected Texture texture;
    protected Players player;

    public ChessPiece(IDrawCallback drawCallback, Players player) {
        this.drawCallback = drawCallback;
        this.texture = getPlayerTexture(player);
        this.player = player;
    }

    protected abstract Texture getPlayerTexture(Players player);

    //TODO: Disallow jumping over pieces (except for the knights).
    public boolean isMoveAllowed(){
        return false;
    }
    public abstract Set<int[]> getPossibleMoves();

    public Texture getTexture() {
        return this.texture;
    }

    public void dispose(){
        this.texture.dispose();
    }

    public Players getPlayer() {
        return this.player;
    }
}