package com.zimonishim.chess;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.zimonishim.chess.gameObjects.ChessField;
import com.zimonishim.chess.gameObjects.chessPieces.*;
import com.zimonishim.chess.util.GraphicsHandler;

import java.util.ArrayList;

import static com.badlogic.gdx.Input.Buttons.LEFT;

public class ChessBoard implements IGameObject {

    private IDrawCallback drawCallback;

    //ChessBoard graphical properties.
    public static final int fieldsAmountX = 8;
    public static final int fieldsAmountY = 8;

    //Individual fields graphical properties.
    public static final int sizeX = 20;
    public static final int sizeY = 20;
    public static final int offsetX = GraphicsHandler.CENTER_X - ((sizeX * fieldsAmountX) / 2);
    public static final int offsetY = GraphicsHandler.CENTER_Y - ((sizeY * fieldsAmountY) / 2);

    private static final int RIGHT_BOUND_X = GraphicsHandler.CENTER_X + ((sizeX * fieldsAmountX) / 2);
    private static final int RIGHT_BOUND_Y = GraphicsHandler.CENTER_Y + ((sizeY * fieldsAmountY) / 2);

    private ArrayList<ChessField> chessFields = new ArrayList<>(64); //Initial capacity should be the max amount of fields.

    public ChessBoard() {
        //Fill chessFields list.
        fillBoard();
    }

    private void fillBoard(){
        boolean colorBool = false;
        Color color;

        //Fill fields.
        for (int y = 0; y < fieldsAmountY; ++y){

            colorBool = !colorBool; //Without this we only draw the Y-axis. So we're doing this the wrong around.

            for (int x = 0; x < fieldsAmountX; ++x){ //TODO: We're doing this the wrong way around. -> Fix this if so. See comment above.

                //Deciding the position of the field.
                ChessFieldLetter posX = ChessFieldLetter.values()[x];
                int posY = y;

                //Deciding the color of the field.
                colorBool = !colorBool;

                if (colorBool){
                    color = Color.BROWN;
                } else {
                    color = Color.WHITE;
                }

                //Adding the field to this ArrayList.
                chessFields.add(new ChessField(
                        posX,   //PosX.
                        posY,   //PosY.
                        sizeX,  //SizeX.
                        sizeY,  //SizeY.
                        color   //Color.
                ));
            }
        }

        //Fill pieces. //TODO: Fix positioning! Row should +1 when drawing.
            //White.
        fillRowWithSpecialPieces(0, Players.WHITE);
        fillRowWithPawns(1, Players.WHITE);
            //Black.
        fillRowWithSpecialPieces(7, Players.BLACK);
        fillRowWithPawns(6, Players.BLACK);
    }
    
    private void fillRowWithSpecialPieces(int row, Players player){
        getChessField(ChessFieldLetter.A, row).setChessPiece(new Rook(drawCallback, player));
        getChessField(ChessFieldLetter.B, row).setChessPiece(new Knight(drawCallback, player));
        getChessField(ChessFieldLetter.C, row).setChessPiece(new Bishop(drawCallback, player));
        getChessField(ChessFieldLetter.D, row).setChessPiece(new Queen(drawCallback, player));
        getChessField(ChessFieldLetter.E, row).setChessPiece(new King(drawCallback, player));
        getChessField(ChessFieldLetter.F, row).setChessPiece(new Bishop(drawCallback, player));
        getChessField(ChessFieldLetter.G, row).setChessPiece(new Knight(drawCallback, player));
        getChessField(ChessFieldLetter.H, row).setChessPiece(new Rook(drawCallback, player));
    }

    private void fillRowWithPawns(int row, Players player){
        for (ChessFieldLetter letter : ChessFieldLetter.values()){
            getChessField(letter, row).setChessPiece(new Pawn(drawCallback, player));
        }
    }

    protected boolean isCheckMate(Players player){
        return false;
    }

    protected ChessField getChessField(ChessFieldLetter letter, int number){
        return this.chessFields.get((letter.x + number * fieldsAmountX) - 1); //Quick maths.
    }

    protected ChessPiece getChessPiece(ChessFieldLetter letter, int number){
        return getChessField(letter, number).getChessPiece();
    }

    public void dispose(){
        for (ChessField chessField : this.chessFields){
            if (chessField.getChessPiece() != null){
                chessField.getChessPiece().dispose();
            }
        }
    }

    public static ChessFieldLetter getLetter(int x){
        switch (x){
            case 1:
                return ChessFieldLetter.A;
            case 2:
                return ChessFieldLetter.B;
            case 3:
                return ChessFieldLetter.C;
            case 4:
                return ChessFieldLetter.D;
            case 5:
                return ChessFieldLetter.E;
            case 6:
                return ChessFieldLetter.F;
            case 7:
                return ChessFieldLetter.G;
            case 8:
                return ChessFieldLetter.H;
            default:
                throw new IllegalArgumentException();
        }
    }

    @Override
    public void update() {
        if (Gdx.input.isButtonJustPressed(LEFT)) { //Only run code if we press our left-Mouse button.

            //Getting mouse positions.
            int mouseX = Gdx.input.getX();
            int mouseY = Gdx.input.getY();

            if (mouseX > offsetX &&
                mouseX < RIGHT_BOUND_X &&
                mouseY > offsetY &&
                mouseY < RIGHT_BOUND_Y
            ){

                //Calculating what field the user selected.
                int selectX = (mouseX - offsetX) / sizeX;

                mouseY = GraphicsHandler.HEIGHT - mouseY; //Calculate mouseY from the bottom of the screen.
                int selectY = (mouseY - offsetY) / sizeY;


                ChessField clickedOnField = getChessField(getLetter(selectX), selectY);

                //Delegate action to clicked ChessField.
                clickedOnField.onClick();

                //Deselect other ChessFields.
                for (ChessField chessField : this.chessFields){
                    if (chessField != clickedOnField){
                        chessField.deselect();
                    }
                }
            }
        }

        //Update all chessFields in this ArrayList.
        for (ChessField chessField : this.chessFields) {
            chessField.update();
        }
    }

    @Override
    public void draw(IDrawCallback drawCallback) {
        for (ChessField chessField : this.chessFields){
            //Draw fields.
            drawCallback.getShapeDrawer().filledRectangle(chessField, chessField.getColor());

            //Draw chessPieces.
            if (chessField.getChessPiece() != null){
                chessField.draw(drawCallback);
            }
        }
    }
}