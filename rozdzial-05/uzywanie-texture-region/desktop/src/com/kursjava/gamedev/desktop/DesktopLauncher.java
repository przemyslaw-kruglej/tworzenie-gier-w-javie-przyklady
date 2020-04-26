package com.kursjava.gamedev.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.kursjava.gamedev.TextureRegionExample;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config =
				new LwjglApplicationConfiguration();

		config.resizable = false;
		config.width = 500;
		config.height = 300;
		config.title = "Region tekstury - https://kursjava.com";

		new LwjglApplication(new TextureRegionExample(), config);
	}
}
