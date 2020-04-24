package com.kursjava.gamedev.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.kursjava.gamedev.DrawingTexturesExample;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config =
				new LwjglApplicationConfiguration();

		config.resizable = false;
		config.width = 800;
		config.height = 640;
		config.title = "Wyświetlanie tekstur - https://kursjava.com";

		new LwjglApplication(new DrawingTexturesExample(), config);
	}
}
