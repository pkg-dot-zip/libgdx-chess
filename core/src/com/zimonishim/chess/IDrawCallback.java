package com.zimonishim.chess;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.viewport.Viewport;
import space.earlygrey.shapedrawer.ShapeDrawer;

public interface IDrawCallback {
    //Draw stuff.
    Batch getBatch();
    ShapeDrawer getShapeDrawer();

    //Camera.
    OrthographicCamera getCamera();
    Viewport getViewPort();

    //Mouse pos.
    int getMouseX();
    int getMouseY();
}