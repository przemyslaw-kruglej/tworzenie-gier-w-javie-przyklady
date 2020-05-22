package com.kursjava.gamedev.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.kursjava.gamedev.DrawingActorsExample;

public class DesktopLauncher {
  public static void main (String[] arg) {
    LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

    config.resizable = false;
    config.width = 485;
    config.height = 250;
    config.title = "Drawing actors - https://kursjava.com";

    new LwjglApplication(new DrawingActorsExample(), config);
  }
}
