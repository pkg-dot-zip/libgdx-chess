package com.zimonishim.chess.gameObjects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.zimonishim.chess.*;
import com.zimonishim.chess.gameObjects.chessPieces.ChessPiece;

import java.util.Set;

public class ChessField extends Rectangle implements IGameObject {

    //Colors.
    private Color color;         //The current color on the screen.
    private Color originalColor; //White or Brown.

    //Field's piece's properties.
    private boolean isSelected = false;
    private boolean isPossibleMove = false;
    private ChessPiece chessPiece;

    //Position.
    private ChessFieldLetter posX;
    private int posY;


    public ChessField(ChessFieldLetter x, int y, float width, float height, Color color) {
        super(x.x * width + ChessBoard.offsetX, y * height + ChessBoard.offsetY, width, height);
        this.color = color;
        this.originalColor = color;
        this.posX = x;
        this.posY = y;
    }

    public ChessPiece getChessPiece() {
        return this.chessPiece;
    }

    public void setChessPiece(ChessPiece chessPiece) {
        this.chessPiece = chessPiece;
    }

    @Override
    public void update() {
        //TODO: Once the game is done, and we notice we don't need to call this as often we NEED to optimize this by not doing so.
        //The reason we can consider this is because we only update variables when pressing the mouse,
        // so this could be once then and ONLY if then.

        color = originalColor;

        if (isSelected){
            if (this.chessPiece != null){
                color = Color.CYAN;
            } else {
                System.out.println("Selected field with no chessPieces. Should we disallow this?");
            }
        } else if (isPossibleMove){
            color = Color.YELLOW;
        }
    }

    @Override
    public void draw(IDrawCallback drawCallback) {
        //Draw the field ONLY, so without the piece.
        drawCallback.getShapeDrawer().filledRectangle(this, color);

        //Return if there is no piece.
        if (this.chessPiece == null){
            return;
        }

        //Return if there is no texture. //TODO: This can be removed once we've decided we'll stick with these textures.
        if (this.chessPiece.getTexture() == null){
            return;
        }

        //Draw the chessPiece.
        drawCallback.getBatch().draw(
                this.chessPiece.getTexture(),
                this.x,
                this.y
        );
    }

    //TODO: Only allow input on the players turn. This is necessary for implementing attacks.
    //TODO: Whenever there is a chessPiece of the opponent here, check if we can attack that piece.
    public void onClick(IChessBoardCallback chessBoardCallback, ChessField clickedOnField){ //TODO: Isn't clickedOnField always this?! Change this!
        //Reset selection & possible moves.
        resetPossibleMoves(chessBoardCallback);

        //Set all moves.
        setAllMoves(chessBoardCallback, clickedOnField);


        //Should this be an else-if with the code in SetAllMoves? ->
        //Move the piece.
        if (this.chessPiece == null){

            //TODO: Refactor into own method for moving, so we can implement sounds and networking functionality more easily.
            if (this.isPossibleMove){
                ChessPiece pieceToMove = null;

                for (ChessField chessField : chessBoardCallback.getChessFields()){
                    if (chessField.isSelected){
                        pieceToMove = chessField.getChessPiece();
                        chessField.setChessPiece(null);
                    }
                }

                this.setChessPiece(pieceToMove);
            }
        }

        //Update selection.
        setSelection(chessBoardCallback);
    }

    private void setSelection(IChessBoardCallback chessBoardCallback){
        if (isSelected){
            deselect();
        } else {
            //Deselect all fields.
            for (ChessField chessField : chessBoardCallback.getChessFields()){
                chessField.deselect();
            }

            //Then select the one we just pressed.
            select();
        }
    }

    private void setAllMoves(IChessBoardCallback chessBoardCallback, ChessField clickedOnField){
        //Set all possible moves.
        if (clickedOnField.getChessPiece() != null){
            ChessPiece chessPiece = clickedOnField.getChessPiece();

            Set<int[]> set = chessPiece.getPossibleMoves();

            for (ChessField chessField : chessBoardCallback.getChessFields()){

                if (chessPiece.getPlayer() == Players.WHITE){ //White piece.

                    //If the piece is WHITE, calculate what fields are eligible
                    // for movement and then set the possibleMove boolean to true.
                    for (int[] ints : set){
                        int x = chessField.getPos()[0] - clickedOnField.getPos()[0];
                        int y = chessField.getPos()[1] - clickedOnField.getPos()[1];

                        if (x == ints[0] && y == ints[1]){
                            chessField.setPossibleMove(true);
                        }
                    }

                } else {                                        //Black piece.

                    //If the piece is BLACK, calculate what fields are eligible
                    // for movement and then set the possibleMove boolean to true.
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
    }

    /**
     * Sets all fields from the chessBoardCallback, defined as a parameter, to <b>not</b> possible to move.
     * @param chessBoardCallback  callback to retrieve list from
     */
    private void resetPossibleMoves(IChessBoardCallback chessBoardCallback){
        for (ChessField chessField : chessBoardCallback.getChessFields()){
            if (chessField != this){
                chessField.setPossibleMove(false);
            }
        }
    }

    /**
     * Sets this.isSelected to true.
     */
    private void select(){
        this.isSelected = true;
    }

    /**
     * Sets this.isSelected to false.
     */
    public void deselect(){
        this.isSelected = false;
    }

    /**
     * Returns an array with two integers, representing the X- and Y-position of this field.
     * @return  coordinates of this field
     */
    private int[] getPos(){
        return new int[]{this.posX.x, this.posY};
    }

    /**
     * Sets this.isPossibleMove to the parameter's value.
     * @param possibleMove  boolean representing the possibility to move
     */
    private void setPossibleMove(boolean possibleMove) {
        this.isPossibleMove = possibleMove;
    }
}