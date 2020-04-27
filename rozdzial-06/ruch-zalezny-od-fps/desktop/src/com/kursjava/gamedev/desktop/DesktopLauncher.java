package com.kursjava.gamedev.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.kursjava.gamedev.FPSDependentMovement;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config =
				new LwjglApplicationConfiguration();

		config.resizable = false;
		config.width = 500;
		config.height = 180;
		config.title = "Ruch zalezny od FPS - https://kursjava.com";

		if (arg.length != 0) {
			config.foregroundFPS = Integer.valueOf(arg[0]);
			config.backgroundFPS = config.foregroundFPS;
		}

		new LwjglApplication(new FPSDependentMovement(), config);
	}
}
