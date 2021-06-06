package com.zimonishim.chess;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CreditsScreen implements Screen {

    private final GameHandler gameHandler;

    private final Skin skin = new Skin(Gdx.files.internal("skin/uiskin.json")); //We keep this here since we only use Scene2D in the mainMenu.

    private final Stage stage = new Stage(new ScreenViewport());
    private final VerticalGroup verticalGroup = new VerticalGroup();
    private final TextArea creditsText = new TextArea("", skin);
    private final Button backToMainMenu = new TextButton("Back to Main Menu", skin);

    public CreditsScreen(final GameHandler gameHandler) {
        this.gameHandler = gameHandler;
        Gdx.input.setInputProcessor(stage);

        setup();
        actionHandlingSetup();
    }

    private void setup() {
        Scanner sc;
        try {
            sc = new Scanner(Gdx.files.internal("core/assets/text/credits.txt").file());
            StringBuilder s = new StringBuilder();
            while(sc.hasNextLine()){
                s.append(sc.nextLine()).append("\n");
            }
            creditsText.setText(s.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        creditsText.setDisabled(true);

        verticalGroup.setSize(800, 800);
        stage.addActor(verticalGroup);

        creditsText.setFillParent(true);

        verticalGroup.addActor(creditsText);
        verticalGroup.addActor(backToMainMenu);

        verticalGroup.pad(10);
        verticalGroup.left().bottom();
    }

    private void actionHandlingSetup() {
        backToMainMenu.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                gameHandler.setScreen(new MainMenuScreen(gameHandler));
                dispose();
            }
        });
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float v) {
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