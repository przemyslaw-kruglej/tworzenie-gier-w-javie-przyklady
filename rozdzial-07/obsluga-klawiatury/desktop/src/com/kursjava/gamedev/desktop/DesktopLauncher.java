package com.kursjava.gamedev.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.kursjava.gamedev.HandlingKeyboardExample;

public class DesktopLauncher {
  public static void main (String[] arg) {
    LwjglApplicationConfiguration config =
        new LwjglApplicationConfiguration();

    config.resizable = false;
    config.width = 500;
    config.height = 300;
    config.title = "Obsluga klawiatury - https://kursjava.com";

    new LwjglApplication(new HandlingKeyboardExample(), config);
  }
}
