package com.zimonishim.chess;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.ScreenUtils;
import com.zimonishim.chess.util.networking.Client;
import com.zimonishim.chess.util.networking.IClientCallback;

public class MainGame implements Screen, IClientCallback {

	private final GameHandler gameHandler;
	private ChessBoard chessBoard;
	private Client client;

	public MainGame (GameHandler gameHandler) {
		this.gameHandler = gameHandler;
		this.chessBoard = new ChessBoard(gameHandler, this);

		//Networking.
		this.client = new Client(chessBoard);
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
		drawCallback.getFont().draw(drawCallback.getBatch(), "You are " + getPlayer(), 200, 220);
	}

	@Override
	public void dispose () {
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

	@Override
	public Client getClient() {
		return this.client;
	}

	@Override
	public Players getPlayer() {
		return client.getPlayer();
	}
}