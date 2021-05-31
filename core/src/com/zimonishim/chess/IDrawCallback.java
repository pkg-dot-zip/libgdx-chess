package com.zimonishim.chess;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.viewport.Viewport;
import space.earlygrey.shapedrawer.ShapeDrawer;

public interface IDrawCallback {
    //Draw stuff.
    Batch getBatch();
    ShapeDrawer getShapeDrawer();
    BitmapFont getFont();

    void drawText(String text, int posX, int posY);

    //Camera.
    OrthographicCamera getCamera();
    Viewport getViewPort();

    //Mouse pos.
    int getMouseX();
    int getMouseY();
}