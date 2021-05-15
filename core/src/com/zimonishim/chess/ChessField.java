package com.zimonishim.chess;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.zimonishim.chess.gameObjects.chessPieces.ChessPiece;

public class ChessField extends Rectangle implements IGameObject {

    private Color color;
    private ChessPiece chessPiece;

    public ChessField(ChessFieldLetter x, int y, float width, float height, Color color) {
        super(x.x * width + ChessBoard.offsetX, y * height + ChessBoard.offsetY, width, height);
        this.color = color;
    }

    public ChessField(ChessFieldLetter x, int y, float width, float height, Color color, ChessPiece chessPiece){
        this(x, y, width, height, color);
        this.chessPiece = chessPiece;
    }

    public ChessPiece getChessPiece() {
        return this.chessPiece;
    }

    public void setChessPiece(ChessPiece chessPiece) {
        this.chessPiece = chessPiece;
    }

    public Color getColor() {
        return this.color;
    }

    //TODO: Turn blue whenever selected, and yellow whenever it's a possible move.
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(IDrawCallback drawCallback) {
        if (this.chessPiece.getTexture() == null){
            return;
        }

        drawCallback.getBatch().draw(
                this.chessPiece.getTexture(),
                this.x,
                this.y
        );
    }
}