package com.kursjava.gamedev.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.kursjava.gamedev.ActorExample;

public class DesktopLauncher {
  public static void main (String[] arg) {
    LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

    config.resizable = false;
    config.width = 400;
    config.height = 300;
    config.title = "Actor - https://kursjava.com";

    new LwjglApplication(new ActorExample(), config);
  }
}
