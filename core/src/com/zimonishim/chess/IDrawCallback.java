package com.zimonishim.chess;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.viewport.Viewport;
import space.earlygrey.shapedrawer.ShapeDrawer;

public interface IDrawCallback {
    //Draw stuff.
    public Batch getBatch();
    public ShapeDrawer getShapeDrawer();
    public BitmapFont getFont();

    //Camera.
    public OrthographicCamera getCamera();
    public Viewport getViewPort();

    //Mouse pos.
    public int getMouseX();
    public int getMouseY();
}