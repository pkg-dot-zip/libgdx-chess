package com.zimonishim.chess;

/**
 * Contains enumerations for the X-axis on chessBoards. This was implemented to avoid silly mistakes; this was implemented with the goal
 * of avoiding mathematical errors, by directly translating chessFields to values used in our software.
 */
public enum ChessFieldLetter {
    A(1),
    B(2),
    C(3),
    D(4),
    E(5),
    F(6),
    G(7),
    H(8);

    public final int x;

    ChessFieldLetter(int x){
        this.x = x;
    }
}