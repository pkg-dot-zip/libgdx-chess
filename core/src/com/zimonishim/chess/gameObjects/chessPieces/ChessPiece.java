package com.zimonishim.chess.gameObjects.chessPieces;

import com.badlogic.gdx.graphics.Texture;
import com.zimonishim.chess.IChessBoardCallback;
import com.zimonishim.chess.Players;
import com.zimonishim.chess.gameObjects.ChessField;
import com.zimonishim.chess.util.TextureHandler;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public abstract class ChessPiece implements Serializable {

    protected String texture;
    protected Players player;
    protected List<ChessField> chessFields;
    protected ChessField chessField;

    public ChessPiece(ChessField chessField, IChessBoardCallback chessBoardCallback, Players player) {
        this.chessField = chessField;
        this.chessFields = chessBoardCallback.getChessFields();
        this.texture = getPlayerTexture(player);
        this.player = player;
    }

    protected abstract String getPlayerTexture(Players player);

    public abstract Set<int[]> getPossibleMoves();

    public Texture getTexture() {
        return TextureHandler.getTexture(texture);
    }

    public Players getPlayer() {
        return this.player;
    }

    //Special method that doesn't need to be overridden.
    public void onMove(){ }

    public void setChessField(ChessField chessField) {
        this.chessField = chessField;
    }
}