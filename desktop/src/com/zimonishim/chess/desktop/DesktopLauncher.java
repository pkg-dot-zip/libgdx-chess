package com.zimonishim.chess.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.zimonishim.chess.GameHandler;
import com.zimonishim.chess.util.GraphicsHandler;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		//Set screen width.
		config.width = GraphicsHandler.WIDTH;
		config.height = GraphicsHandler.HEIGHT;

		//Set other configurations.
		config.title = "Chess";

		new LwjglApplication(new GameHandler(), config);
	}
}
