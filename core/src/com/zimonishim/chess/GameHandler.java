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

    private OrthographicCamera camera;
    private Viewport viewport;

    private final Vector2 mouseInWorld2D = new Vector2();
    private final Vector3 mouseInWorld3D = new Vector3();

    @Override
    public void create() {
        //Global init.
        GraphicsHandler.initGraphics();
        SoundHandler.initSounds();

        //Camera
        this.camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.viewport = new StretchViewport(camera.viewportWidth, camera.viewportHeight, camera); //TODO: Make it look better.

        this.batch = new SpriteBatch();

        this.font = new BitmapFont(); //Arial font and black color.
        this.font.setColor(Color.BLACK);

        this.shapeDrawer = new ShapeDrawer(getBatch(), GraphicsHandler.getEmptyTextureRegion()); //Empty texture region is initialised in GraphicsHandler.

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
        this.batch.dispose();           //Dispose Textures.
        GraphicsHandler.dispose();      //Dispose Textures.
        this.font.dispose();            //Dispose Text.
        SoundHandler.dispose();         //Dispose Sounds.
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
    public void drawText(String text, int posX, int posY) {
        getFont().draw(getBatch(), text, posX, posY);
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