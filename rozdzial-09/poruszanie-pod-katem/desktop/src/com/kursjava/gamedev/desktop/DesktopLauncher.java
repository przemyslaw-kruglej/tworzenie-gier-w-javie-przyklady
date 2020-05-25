package com.kursjava.gamedev.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.kursjava.gamedev.AngleMovementExample;

public class DesktopLauncher {
  public static void main (String[] arg) {
    LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

    config.resizable = false;
    config.width = 500;
    config.height = 400;
    config.title = "Angle movement - https://kursjava.com";

    new LwjglApplication(new AngleMovementExample(), config);
  }
}
