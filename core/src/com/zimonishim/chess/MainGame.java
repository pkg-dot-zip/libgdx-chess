package com.zimonishim.chess;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.zimonishim.chess.util.GraphicsHandler;
import space.earlygrey.shapedrawer.ShapeDrawer;

public class MainGame extends ApplicationAdapter implements IDrawCallback {

	private SpriteBatch batch;
	private ShapeDrawer shapeDrawer;

	private BitmapFont font;

	//Game Attributes.
	private ChessBoard chessBoard;

	@Override
	public void create () {
		//Value init.
			//LibGdx.
		this.batch = new SpriteBatch();
		this.shapeDrawer = new ShapeDrawer(batch, GraphicsHandler.getEmptyTextureRegion());

		this.font = new BitmapFont();
		this.font.setColor(Color.BLACK);
			//Game.
		this.chessBoard = new ChessBoard(this);

		//TODO: Decide whether we want to utilise this.
		Gdx.graphics.setVSync(true);
	}

	@Override
	public void render () {
		ScreenUtils.clear(Color.WHITE);
		batch.begin();
		update();
		draw();
		batch.end();
	}

	private void update(){
		this.chessBoard.update();
	}

	private void draw(){
		drawUI(this);
		chessBoard.draw(this);
	}

	private void drawUI(IDrawCallback drawCallback){
		drawCallback.getFont().draw(batch, "Turn: " + this.chessBoard.getTurn().name(), 200, 200);
	}

	@Override
	public void dispose () {
		//Dispose Textures.
		this.chessBoard.dispose();
		this.batch.dispose();

		//Dispose Text.
		this.font.dispose();
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