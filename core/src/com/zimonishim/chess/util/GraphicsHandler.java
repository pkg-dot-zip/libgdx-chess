package com.zimonishim.chess.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Contains helper methods and global variables for consistency and efficiency.
 */
public class GraphicsHandler {

    //We have to save these since we need to dispose the textures generated by textureRegions.
    private static TextureRegion textureRegion;
    private static Texture texture;

    public static void initGraphics(){
        Gdx.graphics.setVSync(true);
        Gdx.graphics.setResizable(true);
        TextureHandler.initTextures();
        initTextureRegion();
    }

    /**
     * Used by the ShapeDrawer to draw shapes without having to worry about resource-management.
     * @return  textureRegion
     */
    public static TextureRegion getEmptyTextureRegion(){
        return textureRegion;
    }

    private static void initTextureRegion(){
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.drawPixel(0, 0);
        texture = new Texture(pixmap);
        pixmap.dispose();
        textureRegion = new TextureRegion(texture, 0, 0, 1, 1);
    }

    /** Called when this application should release all resources. */
    public static void dispose(){
        TextureHandler.dispose();
        texture.dispose();
    }
}