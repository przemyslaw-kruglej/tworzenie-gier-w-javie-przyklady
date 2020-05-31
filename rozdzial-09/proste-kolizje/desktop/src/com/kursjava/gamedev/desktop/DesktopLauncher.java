package com.kursjava.gamedev.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.kursjava.gamedev.SimpleCollisionsExample;

public class DesktopLauncher {
  public static void main (String[] arg) {
    LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

    config.resizable = false;
    config.width = 420;
    config.height = 250;
    config.title = "Simple collisions - https://kursjava.com";

    new LwjglApplication(new SimpleCollisionsExample(), config);
  }
}
