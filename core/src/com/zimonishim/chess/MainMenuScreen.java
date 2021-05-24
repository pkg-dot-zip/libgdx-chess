package com.zimonishim.chess;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.ScreenUtils;

public class MainMenuScreen implements Screen {

    private final GameHandler gameHandler;

    public MainMenuScreen(final GameHandler gameHandler) {
        this.gameHandler = gameHandler;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.WHITE);
        update();
        this.gameHandler.getBatch().setTransformMatrix(this.gameHandler.getCamera().view);
        this.gameHandler.getBatch().setProjectionMatrix(this.gameHandler.getCamera().projection);
        gameHandler.getBatch().begin();
        draw();
        gameHandler.getBatch().end();
    }

    private void update(){
        if (Gdx.input.isTouched()) {
            gameHandler.setScreen(new MainGame(gameHandler));
            dispose();
        }
    }

    private void draw(){
        gameHandler.getFont().draw(gameHandler.getBatch(), "Main Menu", 100, 100);
        gameHandler.getFont().draw(gameHandler.getBatch(), "Tap the screen to begin...", 100, 120);
    }

    @Override
    public void resize(int width, int height) {
        this.gameHandler.getViewPort().update(width, height, true);
        this.gameHandler.getCamera().update();
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