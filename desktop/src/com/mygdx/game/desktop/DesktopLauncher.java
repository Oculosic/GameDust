package com.mygdx.game.desktop;

import java.awt.Toolkit;
import java.awt.Dimension;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.DustGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "DUST";
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		config.width = (int) screenSize.getWidth();
		config.height = (int) screenSize.getHeight();

		new LwjglApplication(new DustGame(), config);
	}
}
