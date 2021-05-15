package com.zimonishim.chess;

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