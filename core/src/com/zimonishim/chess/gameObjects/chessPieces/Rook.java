package com.zimonishim.chess.gameObjects.chessPieces;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.zimonishim.chess.ChessBoard;
import com.zimonishim.chess.IDrawCallback;
import com.zimonishim.chess.Players;

import java.util.HashSet;
import java.util.Set;

import static com.zimonishim.chess.util.FilePathHandler.chessPieceTexturesPath;

public class Rook extends ChessPiece {
    public Rook(IDrawCallback drawCallback, Players player) {
        super(drawCallback, player);
    }

    @Override
    protected Texture getPlayerTexture(Players player) {
        if (player == Players.WHITE){
            return new Texture(Gdx.files.internal(chessPieceTexturesPath + "/white_rook.png"));
        } else {
            return new Texture(Gdx.files.internal(chessPieceTexturesPath + "/black_rook.png"));
        }
    }

    @Override
    public boolean isMoveAllowed() {
        return false;
    }

    @Override
    public Set<int[]> getPossibleMoves() {
        Set<int[]> set = new HashSet<>();

        for (int i = 1; i < ChessBoard.fieldsAmountX; ++i){
            //Horizontal.
            set.add(new int[]{i, 0});
            set.add(new int[]{-i, 0});

            //Vertical.
            set.add(new int[]{0, i});
            set.add(new int[]{0, -i});
        }

        return set;
    }
}