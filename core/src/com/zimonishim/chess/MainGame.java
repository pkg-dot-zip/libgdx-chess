package com.zimonishim.chess;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MainGame implements Screen {

	private final GameHandler gameHandler;
	private OrthographicCamera camera; //TODO: Use Viewports to make resizing work.
	private Viewport viewport;

	//Game Attributes.
	private ChessBoard chessBoard;

	public MainGame (GameHandler gameHandler) {
		this.gameHandler = gameHandler;

		//Value init.
			//LibGdx.
		this.camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		this.camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
		this.viewport = new FitViewport(camera.viewportWidth, camera.viewportHeight, camera);
			//Game.
		this.chessBoard = new ChessBoard(gameHandler);
	}

	private void update(){
		this.camera.update();
		this.chessBoard.update();
	}

	private void draw(){
		drawUI(gameHandler);
		chessBoard.draw(gameHandler);
	}

	private void drawUI(IDrawCallback drawCallback){
		drawCallback.getFont().draw(drawCallback.getBatch(), "Turn: " + this.chessBoard.getTurn().name(), 200, 200);
	}

	@Override
	public void dispose () {
		//Dispose Textures.
		this.chessBoard.dispose();

		//Dispose the entire game.
		this.gameHandler.dispose();
	}

	@Override
	public void show() {

	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(Color.WHITE);
		update();
		this.gameHandler.getBatch().setTransformMatrix(this.camera.view);
		this.gameHandler.getBatch().setProjectionMatrix(camera.projection);
		this.gameHandler.getBatch().begin();
		draw();
		this.gameHandler.getBatch().end();
	}

	//TODO: Make resizing work. Right now the ratio is good, but we can't interact with anything because the mousePos it not right.
	@Override
	public void resize(int width, int height) {
		this.viewport.update(width, height, true);
		this.camera.update();
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
}