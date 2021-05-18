package com.zimonishim.chess;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
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

    private final Vector2 mouseInWorld2D = new Vector2();
    private final Vector3 mouseInWorld3D = new Vector3();

    private OrthographicCamera camera;
    private Viewport viewport;

    @Override
    public void create() {
        //Camera
        this.camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
//		this.camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0); //TODO: Does nothing?! Remove?!
        this.viewport = new StretchViewport(camera.viewportWidth, camera.viewportHeight, camera); //TODO: Make it look better.

        //Create the batch.
        this.batch = new SpriteBatch();

        //Creates a BitMapFont with the Arial font and the color black.
        this.font = new BitmapFont(); //Arial.
        this.font.setColor(Color.BLACK);

        //Create the shapeDrawer.
        this.shapeDrawer = new ShapeDrawer(getBatch(), GraphicsHandler.getEmptyTextureRegion());

        //Global init.
        GraphicsHandler.initGraphicSettings();  //Graphics.
        SoundHandler.initSounds();			    //Sounds.

        //Open the mainMenu.
        this.setScreen(new MainMenuScreen(this));
    }

    @Override
    public void render() {
        super.render(); //NOTE: Super important. NEVER remove this.

        //Camera.
        this.camera.update();

        //TODO: Remove if we stop using this.
        //Calculate mouse position in world space.
        mouseInWorld3D.x = Gdx.input.getX();
        mouseInWorld3D.y = Gdx.input.getY();
        mouseInWorld3D.z = 0;
        camera.unproject(mouseInWorld3D);
        mouseInWorld2D.x = mouseInWorld3D.x;
        mouseInWorld2D.y = mouseInWorld3D.y;
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

    @Override
    public int getMouseX(){
        return (int) mouseInWorld3D.x;
    }

    @Override
    public int getMouseY(){
        return (int) mouseInWorld3D.y;
    }

    @Override
    public OrthographicCamera getCamera() {
        return this.camera;
    }

    @Override
    public Viewport getViewPort() {
        return this.viewport;
    }
}