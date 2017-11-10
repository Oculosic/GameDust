package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.DustGame;
import com.mygdx.game.screen;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "DUST";
		config.width = screen.getScreenWidth();
		config.height = screen.getScreenHeight();

		new LwjglApplication(new DustGame(), config);
	}

}
