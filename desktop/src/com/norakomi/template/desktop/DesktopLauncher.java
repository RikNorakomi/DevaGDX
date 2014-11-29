package com.norakomi.template.desktop;

import static com.norakomi.template.helpers.Variables.*;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.norakomi.template.NorakomiGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		config.width = (int) (V_WIDTH);
		config.height = (int) (V_HEIGHT);
		new LwjglApplication(new NorakomiGame(), config);
	}
}
