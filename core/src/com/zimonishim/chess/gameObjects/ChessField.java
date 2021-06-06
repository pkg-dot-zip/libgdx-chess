package com.zimonishim.chess.gameObjects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.zimonishim.chess.*;
import com.zimonishim.chess.gameObjects.chessPieces.ChessPiece;
import com.zimonishim.chess.util.FilePathHandler;
import com.zimonishim.chess.util.SoundHandler;
import com.zimonishim.chess.util.networking.IClientCallback;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

public class ChessField extends Rectangle implements IGameObject, Serializable {

    //Colors.
    private transient Color color;         //The current color on the screen.
    private transient Color originalColor; //White or Brown.

    //Field's piece's properties.
    private boolean isSelected = false;
    private boolean isPossibleMove = false;
    private ChessPiece chessPiece;

    //Position.
    private final ChessFieldLetter posX;
    private final int posY;


    public ChessField(ChessFieldLetter x, int y, float width, float height, Color color) {
        super(x.x * width - width, y * height, width, height);
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
        // This should not be called on. Chessfield should use the other update method instead.
        System.out.println("Wrong update call in chessfield");
    }

    @Override
    public void update(IChessBoardCallback chessBoardCallback) {
        //TODO: Once our software is done, and we notice we don't need to call this as often we NEED to optimize this by not doing so.
        //The reason we can consider this is because we only update variables when pressing the mouse,
        // so this could be once then and ONLY if then.
        color = originalColor;

        if (isSelected) {
            if (this.chessPiece != null) {
                color = Color.CYAN;
            }
        } else if (isPossibleMove) {
            if (this.getChessPiece() == null){
                color = Color.YELLOW;
            } else if (this.chessPiece.getPlayer() != chessBoardCallback.getTurn()){
                color = Color.RED;
            }
        }
    }

    @Override
    public void draw(IDrawCallback drawCallback) {
        //Draw the field ONLY, so without the piece.
        drawCallback.getShapeDrawer().filledRectangle(this, color);

        if (this.chessPiece == null) {
            return;
        }

        //Draw the chessPiece ONLY, so without the field.
        drawCallback.getBatch().draw(this.chessPiece.getTexture(), this.x, this.y, this.chessPiece.getTexture().getWidth() * 2f, this.chessPiece.getTexture().getHeight() * 2f);
    }

    public void onClick(IChessBoardCallback chessBoardCallback, IClientCallback clientCallback, ChessField clickedOnField) {
        if (clientCallback.getPlayer() != chessBoardCallback.getTurn()) {
            return;
        }

        if (this.isPossibleMove) {
            //Check if a previously selected piece can move to this field.
            boolean canMove = false;
            for (ChessField chessField : chessBoardCallback.getChessFields()) {
                if (chessField.isSelected && chessField.getChessPiece() != null && chessField.getChessPiece().getPlayer() == chessBoardCallback.getTurn())
                    canMove = true;
            }

            if (canMove) {
                resetPossibleMoves(chessBoardCallback);
                movePiece(chessBoardCallback);
                resetSelection(chessBoardCallback);
                sendNetworkData(chessBoardCallback, clientCallback);

                chessBoardCallback.checkGameState();
            }
        } else if (this.getChessPiece() == null || (this.chessPiece != null && this.chessPiece.getPlayer() == chessBoardCallback.getTurn())) {
            resetPossibleMoves(chessBoardCallback);             //Reset selection & possible moves.
            setAllMoves(chessBoardCallback, clickedOnField);    //Set all moves.
            setSelection(chessBoardCallback);                   //Update selection.
        }
    }

    private void movePiece(IChessBoardCallback chessBoardCallback) {
        if (this.isPossibleMove) {
            movePieceOnBoard(chessBoardCallback);
            SoundHandler.playSound(FilePathHandler.chessPieceMoveSoundPath);
        }
    }

    private void sendNetworkData(IChessBoardCallback chessBoardCallback, IClientCallback clientCallback) {
        // Before sending data reset all selections and colours.
        chessBoardCallback.cleanBoard();
        clientCallback.getClient().sendChessFields(chessBoardCallback);
    }

    private void movePieceOnBoard(IChessBoardCallback chessBoardCallback) {
        for (ChessField chessField : chessBoardCallback.getChessFields()) {
            if (chessField.isSelected) {
                ChessPiece pieceToMove = chessField.getChessPiece();
                chessField.setChessPiece(null);
                if (this.chessPiece != null) this.chessPiece.setChessField(null);
                this.setChessPiece(pieceToMove);
                pieceToMove.setChessField(this);
                //This means we moved! So let's move it, and then change turns.
                this.getChessPiece().onMove();
                chessBoardCallback.switchTurn();

                break;
            }
        }
    }

    private void setSelection(IChessBoardCallback chessBoardCallback) {
        if (isSelected) {
            resetPossibleMoves(chessBoardCallback); //Make sure deselecting a piece removes all yellow fields.
            deselect();
        } else {
            for (ChessField chessField : chessBoardCallback.getChessFields()) {
                chessField.deselect();
            }

            if (this.chessPiece != null && this.chessPiece.getPlayer() == chessBoardCallback.getTurn()) select();
        }
    }

    private void resetSelection(IChessBoardCallback chessBoardCallback) {
        resetPossibleMoves(chessBoardCallback);
        for (ChessField chessField : chessBoardCallback.getChessFields()) {
            chessField.deselect();
        }
    }

    private void setAllMoves(IChessBoardCallback chessBoardCallback, ChessField clickedOnField) {
        if (clickedOnField.getChessPiece() != null) {
            ChessPiece chessPiece = clickedOnField.getChessPiece();

            Set<int[]> set = chessPiece.getPossibleMoves();
            System.out.println(set);

            List<ChessField> chessFields = chessBoardCallback.getChessFields();
            for (ChessField chessField : chessFields) {
                for (int[] ints : set) {
                    int x = chessField.getPos()[0] - clickedOnField.getPos()[0];
                    int y = chessField.getPos()[1] - clickedOnField.getPos()[1];

                    if (x == ints[0] && y == ints[1]) {
                        chessField.setPossibleMove(true);
                    }
                }
            }
        }
    }

    private void resetPossibleMoves(IChessBoardCallback chessBoardCallback) {
        for (ChessField chessField : chessBoardCallback.getChessFields()) {
            if (chessField != this) {
                chessField.setPossibleMove(false);
            }
        }
    }

    private void select() {
        this.isSelected = true;
    }

    public void deselect() {
        this.isSelected = false;
    }

    public int[] getPos() {
        return new int[]{this.posX.x, this.posY};
    }

    public void setPossibleMove(boolean possibleMove) {
        this.isPossibleMove = possibleMove;
    }

    private void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();
        s.writeFloat(this.color.r);
        s.writeFloat(this.color.g);
        s.writeFloat(this.color.b);
        s.writeFloat(this.color.a);
        s.writeFloat(this.originalColor.r);
        s.writeFloat(this.originalColor.g);
        s.writeFloat(this.originalColor.b);
        s.writeFloat(this.originalColor.a);
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        s.defaultReadObject();
        this.color = new Color(s.readFloat(), s.readFloat(), s.readFloat(), s.readFloat());
        this.originalColor = new Color(s.readFloat(), s.readFloat(), s.readFloat(), s.readFloat());
    }

    public ChessField getChessField(List<ChessField> chessFields, int row, int column) {
        if (row < 0 || row >= ChessBoard.fieldsAmountY || column <= 0 || column > ChessBoard.fieldsAmountX) return null;
        return chessFields.get(row * ChessBoard.fieldsAmountX + column - 1);
    }

    public Color getOriginalColor() {
        return originalColor;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}