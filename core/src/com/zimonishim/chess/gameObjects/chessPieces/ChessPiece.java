package com.zimonishim.chess.gameObjects.chessPieces;

import com.badlogic.gdx.graphics.Texture;
import com.zimonishim.chess.Players;
import com.zimonishim.chess.util.TextureHandler;

import java.io.Serializable;
import java.util.Set;

public abstract class ChessPiece implements Serializable {

    protected String texture;
    protected Players player;

    public ChessPiece(Players player) {
        this.texture = getPlayerTexture(player);
        this.player = player;
    }

    protected abstract String getPlayerTexture(Players player);

    //TODO: Disallow jumping over pieces (except for the knights).
    public boolean isMoveAllowed(){
        return false;
    }
    public abstract Set<int[]> getPossibleMoves();

    public Texture getTexture() {
        return TextureHandler.getTexture(texture);
    }

    public Players getPlayer() {
        return this.player;
    }

    //Special method that doesn't need to be overridden.
    public void onMove(){ }
}