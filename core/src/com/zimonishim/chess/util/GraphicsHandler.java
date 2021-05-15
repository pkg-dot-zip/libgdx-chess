package com.zimonishim.chess.util;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Contains helper methods and global variables for consistency and efficiency.
 */
public class GraphicsHandler {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 480;

    public static final int CENTER_X = WIDTH / 2;
    public static final int CENTER_Y = HEIGHT / 2;

    public static TextureRegion getEmptyTextureRegion(){
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.drawPixel(0, 0);
        Texture texture = new Texture(pixmap); //NOTE: This causes an exception, since it can't be disposed of later. //TODO: Fix.
        pixmap.dispose();
        return new TextureRegion(texture, 0, 0, 1, 1);
    }
}