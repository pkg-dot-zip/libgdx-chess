package com.zimonishim.chess;

import com.badlogic.gdx.graphics.g2d.Batch;
import space.earlygrey.shapedrawer.ShapeDrawer;

public interface IDrawCallback {
    public Batch getBatch();
    public ShapeDrawer getShapeDrawer();
}