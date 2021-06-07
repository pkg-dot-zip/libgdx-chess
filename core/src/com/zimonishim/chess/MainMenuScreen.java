package com.zimonishim.chess;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MainMenuScreen implements Screen {

    private final GameHandler gameHandler;

    private final Skin skin = new Skin(Gdx.files.internal("skin/uiskin.json"));

    private final Stage stage = new Stage(new ScreenViewport());
    private final Table table = new Table();
    private final Button play = new TextButton("Play", skin);
    private final Button credits = new TextButton("Credits", skin);
    private final Button exit = new TextButton("Exit", skin);

    public MainMenuScreen(final GameHandler gameHandler) {
        this.gameHandler = gameHandler;
        Gdx.input.setInputProcessor(stage);

        setup();
        actionHandlingSetup();
    }

    private void setup() {
        table.setFillParent(true);
        stage.addActor(table);

        table.add(play).fillX().uniformX();
        table.row().pad(10, 0, 10, 0);
        table.add(credits).fillX().uniformX();
        table.row();
        table.add(exit).fillX().uniformX();
    }

    private void actionHandlingSetup() {
        play.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                ((TextButton) actor).setText("Loading...");
                gameHandler.setScreen(new MainGame(gameHandler));
                dispose();
            }
        });

        credits.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                gameHandler.setScreen(new CreditsScreen(gameHandler));
                dispose();
            }
        });

        exit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        draw();
    }

    private void draw() {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
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
        skin.dispose();
        stage.dispose();
    }
}