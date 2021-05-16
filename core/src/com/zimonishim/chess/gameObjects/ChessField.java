package com.zimonishim.chess.gameObjects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.zimonishim.chess.*;
import com.zimonishim.chess.gameObjects.chessPieces.ChessPiece;

import java.util.Set;

public class ChessField extends Rectangle implements IGameObject {

    private Color color;         //The current color on the screen.
    private Color originalColor; //White or Brown.

    private boolean isSelected = false;
    private boolean isPossibleMove = false;
    private ChessPiece chessPiece;

    private ChessFieldLetter posX;
    private int posY;


    public ChessField(ChessFieldLetter x, int y, float width, float height, Color color) {
        super(x.x * width + ChessBoard.offsetX, y * height + ChessBoard.offsetY, width, height);
        this.color = color;
        this.originalColor = color;
        this.posX = x;
        this.posY = y;
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
        color = originalColor;

        if (isSelected){
            color = Color.CYAN;
        } else if (isPossibleMove){
            color = Color.YELLOW;
        }
    }

    @Override
    public void draw(IDrawCallback drawCallback) {
        drawCallback.getShapeDrawer().filledRectangle(this, color);

        if (this.chessPiece == null){
            return;
        }
        if (this.chessPiece.getTexture() == null){
            return;
        }

        drawCallback.getBatch().draw(
                this.chessPiece.getTexture(),
                this.x,
                this.y
        );
    }

    //TODO: Fix deselecting.
    //TODO: Whenever there is a chessPiece of the opponent here, check if we can attack that piece.
    //TODO: Whenever the field is empty see if we can move the selected piece there.
    //TODO: Only allow input on the players turn.
    public void onClick(IChessBoardCallback chessBoardCallback, ChessField clickedOnField){ //TODO: Isn't clickedOnField always this?! Change this!
        //Reset selection & possible moves.
        for (ChessField chessField : chessBoardCallback.getChessFields()){
            chessField.deselect();
            chessField.setPossibleMove(false);
        }

        //Set all possible moves.
        if (clickedOnField.getChessPiece() != null){
            ChessPiece chessPiece = clickedOnField.getChessPiece();

            Set<int[]> set = chessPiece.getPossibleMoves();

            for (ChessField chessField : chessBoardCallback.getChessFields()){

                if (chessPiece.getPlayer() == Players.WHITE){ //White piece.

                    //TODO: Fix. -> Don't even get a NullPointerException when the returned moves are null.
                    for (int[] ints : set){
                        int x = chessField.getPos()[0] - clickedOnField.getPos()[0];
                        int y = chessField.getPos()[1] - clickedOnField.getPos()[1];

                        if (x == ints[0] && y == ints[1]){
                            chessField.setPossibleMove(true);
                        }
                    }

                } else {                                        //Black piece.
                    //TODO: Fix. -> Don't even get a NullPointerException when the returned moves are null.
                    for (int[] ints : set){
                        int x = clickedOnField.getPos()[0] - chessField.getPos()[0];
                        int y = clickedOnField.getPos()[1] - chessField.getPos()[1];

                        if (x == ints[0] && y == ints[1]){
                            chessField.setPossibleMove(true);
                        }
                    }
                }
            }
        }

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
        this.isSelected = true;
    }

    public void deselect(){
        this.isSelected = false;
    }

    private int[] getPos(){
        return new int[]{posX.x, posY};
    }

    private void setPossibleMove(boolean possibleMove) {
        this.isPossibleMove = possibleMove;
    }
}