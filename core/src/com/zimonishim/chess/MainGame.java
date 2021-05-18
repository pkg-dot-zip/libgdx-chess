package com.zimonishim.chess;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.ScreenUtils;

public class MainGame implements Screen {

	private final GameHandler gameHandler;

	//Game Attributes.
	private ChessBoard chessBoard;

	public MainGame (GameHandler gameHandler) {
		this.gameHandler = gameHandler;

		//Value init.
		this.chessBoard = new ChessBoard(gameHandler);
	}

	private void update(){
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
		this.gameHandler.getBatch().setTransformMatrix(this.gameHandler.getCamera().view);
		this.gameHandler.getBatch().setProjectionMatrix(this.gameHandler.getCamera().projection);
		this.gameHandler.getBatch().begin();
		draw();
		this.gameHandler.getBatch().end();
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
}