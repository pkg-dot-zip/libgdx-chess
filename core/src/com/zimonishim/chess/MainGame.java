package com.zimonishim.chess;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.zimonishim.chess.util.GraphicsHandler;
import space.earlygrey.shapedrawer.ShapeDrawer;

public class MainGame extends ApplicationAdapter implements IDrawCallback {
	private SpriteBatch batch;
	private ShapeDrawer shapeDrawer;

	//Game Attributes.
	private ChessBoard chessBoard;

	@Override
	public void create () {
		//Value init.
			//LibGdx.
		this.batch = new SpriteBatch();
		this.shapeDrawer = new ShapeDrawer(batch, GraphicsHandler.getEmptyTextureRegion());
			//Game.
		this.chessBoard = new ChessBoard(this);
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
		chessBoard.draw(this);
	}
	
	@Override
	public void dispose () {
		//Dispose Textures.
		this.chessBoard.dispose();
	}

	@Override
	public Batch getBatch() {
		return this.batch;
	}

	@Override
	public ShapeDrawer getShapeDrawer() {
		return this.shapeDrawer;
	}
}