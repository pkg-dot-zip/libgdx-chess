package com.zimonishim.chess;

public interface IGameObject {

    public void update();
    public void draw(IDrawCallback drawCallback);
}