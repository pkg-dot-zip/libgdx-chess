package com.zimonishim.chess;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MainMenuScreen implements Screen {

    private final GameHandler gameHandler;
    private OrthographicCamera camera;
    private Viewport viewport;

    public MainMenuScreen(final GameHandler gameHandler) {
        this.gameHandler = gameHandler;

        this.camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        this.viewport = new FitViewport(camera.viewportWidth, camera.viewportHeight, camera);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.WHITE);

        update();

        gameHandler.getBatch().setProjectionMatrix(camera.combined);
        gameHandler.getBatch().begin();
        draw();
        gameHandler.getBatch().end();
    }

    private void update(){
        camera.update();
        if (Gdx.input.isTouched()) {
            gameHandler.setScreen(new MainGame(gameHandler));
            dispose();
        }
    }

    private void draw(){
        gameHandler.getFont().draw(gameHandler.getBatch(), "Main Menu", Gdx.graphics.getWidth()  / 2f, Gdx.graphics.getHeight()  / 2f);
        gameHandler.getFont().draw(gameHandler.getBatch(), "Tap the screen to begin...", Gdx.graphics.getWidth()  / 2f, Gdx.graphics.getHeight()  / 2f + 100);
    }

    @Override
    public void resize(int width, int height) {
        this.viewport.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}