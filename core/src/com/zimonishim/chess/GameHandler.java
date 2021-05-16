package com.zimonishim.chess;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.zimonishim.chess.util.GraphicsHandler;
import com.zimonishim.chess.util.SoundHandler;
import space.earlygrey.shapedrawer.ShapeDrawer;

/**
 * Contains the tools to draw everything on the screen. This also loads the first screen of our gameHandler.
 */
public class GameHandler extends Game implements IDrawCallback {

    private SpriteBatch batch;
    private BitmapFont font;
    private ShapeDrawer shapeDrawer;

    @Override
    public void create() {
        //Create the batch.
        this.batch = new SpriteBatch();

        //Creates a BitMapFont with the Arial font and the color black.
        this.font = new BitmapFont(); //Arial.
        this.font.setColor(Color.BLACK);

        //Create the shapeDrawer.
        this.shapeDrawer = new ShapeDrawer(getBatch(), GraphicsHandler.getEmptyTextureRegion());

        //Global init.
        GraphicsHandler.initGraphicSettings(); //Graphics.
        SoundHandler.initSounds();				//Sounds.

        //Open the mainMenu.
        this.setScreen(new MainMenuScreen(this));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        //Dispose Textures.
        this.batch.dispose();

        //Dispose Text.
        this.font.dispose();

        //Dispose Sounds.
        SoundHandler.dispose();
    }

    @Override
    public Batch getBatch() {
        return this.batch;
    }

    @Override
    public ShapeDrawer getShapeDrawer() {
        return this.shapeDrawer;
    }

    @Override
    public BitmapFont getFont() {
        return this.font;
    }
}