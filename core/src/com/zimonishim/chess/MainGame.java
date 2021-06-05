package com.zimonishim.chess;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.zimonishim.chess.util.networking.ChatLogHandler;
import com.zimonishim.chess.util.networking.Client;
import com.zimonishim.chess.util.networking.IClientCallback;

public class MainGame implements Screen, IClientCallback {

	private final GameHandler gameHandler;
	private ChessBoard chessBoard;
	private Client client;

	private Stage stage;
	private final Skin skin = new Skin(Gdx.files.internal("skin/uiskin.json")); //We keep this here since we only use Scene2D in the mainMenu.
	private final Table chatTable = new Table(skin);
	private final HorizontalGroup horizontalGroup = new HorizontalGroup();
	private final TextField messageTextField = new TextField("", skin);
	private final Button messageSendButton = new TextButton("Send", skin);
	private final List<String> messagesList = new List<String>(skin);
	private final ScrollPane scrollPane = new ScrollPane(messagesList, skin);

	private final VerticalGroup turnInfoGroup = new VerticalGroup();
	private final Label turnLabel = new Label("", skin);
	private final Label playerLabel = new Label("", skin);


	public MainGame (GameHandler gameHandler) {
		this.gameHandler = gameHandler;
		this.chessBoard = new ChessBoard(gameHandler, this);
		this.client = new Client(chessBoard);

		setup();
		actionHandlingSetup();
	}

	private void setup(){
		this.stage = new Stage(new ScreenViewport());

		//ChatTable.
		chatTable.bottom().right(); //Put the chat in the bottom-right corner.
		chatTable.setFillParent(true);

		chatTable.add(scrollPane).fillX().uniformX().prefHeight(700f).prefWidth(400f);
		chatTable.row().pad(10);

		horizontalGroup.addActor(messageTextField);
		horizontalGroup.addActor(messageSendButton);
		chatTable.add(horizontalGroup).width(chatTable.getWidth()).height(30f);

		//TurnInformationGroup.
		turnInfoGroup.top().left();
		turnInfoGroup.setFillParent(true);
		turnInfoGroup.pad(10);

		turnInfoGroup.addActor(turnLabel);
		turnInfoGroup.addActor(playerLabel);


		stage.addActor(chatTable);
		stage.addActor(turnInfoGroup);

		Gdx.input.setInputProcessor(this.stage);
	}

	private void actionHandlingSetup(){
		messageSendButton.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				if (messageTextField.getText().isEmpty()){
					return;
				}

//				ChatLogHandler.sendMessage(chessBoard.getTurn(), messageTextField.getText());
				client.sendChatMessage(getClient().getPlayer(), messageTextField.getText());
				messageTextField.setText("");
			}
		});
	}

	private void update(){
		this.chessBoard.update();
		messagesList.setItems(ChatLogHandler.chatMessages.toArray(new String[0]));
	}

	private void draw(){
		drawUI();
		chessBoard.draw(gameHandler);
	}

	private void drawUI(){
		turnLabel.setText("Turn: " + this.chessBoard.getTurn().name());
		playerLabel.setText("You are " + getPlayer());
		stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		stage.draw();
	}

	@Override
	public void dispose () {
		this.stage.dispose();
		this.skin.dispose();
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
		stage.getViewport().update(width, height, true);
	}

	@Override
	public void pause() {
		System.out.println("Game has been paused.");
	}

	@Override
	public void resume() {
		System.out.println("Game has been resumed.");
	}

	@Override
	public void hide() {
		System.out.println("Game has been hidden.");
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