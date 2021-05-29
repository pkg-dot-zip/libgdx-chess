package com.zimonishim.chess;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.zimonishim.chess.gameObjects.ChessField;
import com.zimonishim.chess.gameObjects.chessPieces.*;
import com.zimonishim.chess.util.networking.IClientCallback;

import java.util.ArrayList;
import java.util.List;

import static com.badlogic.gdx.Input.Buttons.LEFT;

public class ChessBoard implements IGameObject, IChessBoardCallback {

    private final IDrawCallback drawCallback;
    private final IClientCallback clientCallback;
    private Players turn = Players.WHITE; //In chess WHITE always starts.

    //ChessBoard graphical properties.
    public static final int fieldsAmountX = 8;
    public static final int fieldsAmountY = 8;

    //Individual fields graphical properties.
    public static final int sizeX = 20;
    public static final int sizeY = 20;

    private List<ChessField> chessFields = new ArrayList<>(64); //Initial capacity should be the max amount of fields.

    public ChessBoard(IDrawCallback drawCallback, IClientCallback clientCallback) {
        this.drawCallback = drawCallback;
        this.clientCallback = clientCallback;
        fillBoard();
    }

    private void fillBoard(){
        boolean colorBool = false;
        Color color;

        //Fill fields.
        for (int y = 0; y < fieldsAmountY; ++y){

            colorBool = !colorBool; //Without this we only draw the Y-axis. So we're doing this the wrong around. Right?

            for (int x = 0; x < fieldsAmountX; ++x){ //TODO: We're doing this the wrong way around. -> Fix this if so. See comment above.

                //Deciding the position of the field.
                ChessFieldLetter posX = ChessFieldLetter.values()[x];

                //Deciding the color of the field.
                colorBool = !colorBool;
                color = (colorBool) ? Color.BROWN : Color.WHITE;

                chessFields.add(new ChessField(
                        posX,
                        y,
                        sizeX,
                        sizeY,
                        color
                ));
            }
        }

        //Fill pieces.
            //White.
        fillRowWithSpecialPieces(0, Players.WHITE);
        fillRowWithPawns(1, Players.WHITE);
            //Black.
        fillRowWithSpecialPieces(7, Players.BLACK);
        fillRowWithPawns(6, Players.BLACK);
    }
    
    private void fillRowWithSpecialPieces(int row, Players player){
        ChessField a = getChessField(ChessFieldLetter.A, row);
        a.setChessPiece(new Rook(a, this, player));
        ChessField b = getChessField(ChessFieldLetter.B, row);
        b.setChessPiece(new Knight(b, this, player));
        ChessField c = getChessField(ChessFieldLetter.C, row);
        c.setChessPiece(new Bishop(c, this, player));
        ChessField d = getChessField(ChessFieldLetter.D, row);
        d.setChessPiece(new Queen(d, this, player));
        ChessField e = getChessField(ChessFieldLetter.E, row);
        e.setChessPiece(new King(e, this, player));
        ChessField f = getChessField(ChessFieldLetter.F, row);
        f.setChessPiece(new Bishop(f, this, player));
        ChessField g = getChessField(ChessFieldLetter.G, row);
        g.setChessPiece(new Knight(g, this, player));
        ChessField h = getChessField(ChessFieldLetter.H, row);
        h.setChessPiece(new Rook(h, this, player));
    }

    private void fillRowWithPawns(int row, Players player){
        for (ChessFieldLetter letter : ChessFieldLetter.values()){
            ChessField p = getChessField(letter, row);
            p.setChessPiece(new Pawn(p, this, player));
        }
    }

    @Override
    public ChessField getChessField(ChessFieldLetter letter, int number){
        return this.chessFields.get((letter.x + number * fieldsAmountX) - 1);
    }

    @Override
    public ChessPiece getChessPiece(ChessFieldLetter letter, int number){
        return getChessField(letter, number).getChessPiece();
    }

    @Override
    public void update() {
        if (Gdx.input.isButtonJustPressed(LEFT)) { //Only runs code ONCE when we press our left-Mouse button.

            System.out.println("Mouse = (" + drawCallback.getMouseX() + ", " + drawCallback.getMouseY() + ")");

            //Check whether the mouse was pressed on a field.
            for (ChessField clickedOnField : this.chessFields){
                if (clickedOnField.contains(drawCallback.getMouseX(), drawCallback.getMouseY())){
                    //Just stop doing everything if we press a piece that is NOT the color of turn.
                    if (clickedOnField.getChessPiece() != null){

                        if (clickedOnField.getChessPiece().getPlayer() != this.turn){
                            //TODO: Fix selection. It now stays blue, despite the turn being ended.
                            return;
                        }
                    }

                    //Delegate action to method in field's class.
                    clickedOnField.onClick(this, this.clientCallback, clickedOnField);
                }
            }
        }

        for (ChessField chessField : this.chessFields) {
            chessField.update();
        }
    }

    @Override
    public void draw(IDrawCallback drawCallback) {
        for (ChessField chessField : this.chessFields){
            chessField.draw(drawCallback);
        }
    }

    @Override
    public List<ChessField> getChessFields() {
        return this.chessFields;
    }

    @Override
    public void setChessFields(List<ChessField> chessFields) {
        this.chessFields = chessFields;
    }

    @Override
    public Players getTurn() {
        return this.turn;
    }

    @Override
    public void switchTurn() {
        this.turn = (this.turn == Players.WHITE) ? Players.BLACK : Players.WHITE;
    }
}