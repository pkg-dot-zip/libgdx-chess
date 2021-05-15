package com.zimonishim.chess.gameObjects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.zimonishim.chess.ChessBoard;
import com.zimonishim.chess.ChessFieldLetter;
import com.zimonishim.chess.IDrawCallback;
import com.zimonishim.chess.IGameObject;
import com.zimonishim.chess.gameObjects.chessPieces.ChessPiece;

public class ChessField extends Rectangle implements IGameObject {

    private Color color;         //The current color on the screen.
    private Color originalColor; //White or Brown.

    private boolean isSelected = false;
    private ChessPiece chessPiece;


    public ChessField(ChessFieldLetter x, int y, float width, float height, Color color) {
        super(x.x * width + ChessBoard.offsetX, y * height + ChessBoard.offsetY, width, height);
        this.color = color;
        this.originalColor = color;
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


    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void update() {

        //Check whether the piece is selected.
        if (isSelected){

            //If it's selected and the color is NOT set to blue, we set it to blue.
            if (this.color != Color.CYAN){
                this.setColor(Color.CYAN);
            }
        } else {

            //If it's not selected and NOT its original color, we sit it to its original color.
            if (this.color != this.originalColor){
                this.color = this.originalColor;
            }
        }
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

    //TODO: Turn yellow whenever it's a possible move.
    //TODO: Whenever there is a chessPiece of the opponent here, check if we can attack that piece.
    //TODO: Whenever the field is empty see if we can move the selected piece there.
    //TODO: Only allow input on the players turn.
    public void onClick(){
        if (this.chessPiece == null){
            //TODO: Make moves etc.
            return;
        }

        if (isSelected){
            deselect();
        } else {
            select();
        }
    }


    private void select(){
        isSelected = true;
    }

    public void deselect(){
        isSelected = false;
    }
}