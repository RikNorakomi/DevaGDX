package com.norakomi.template.helpers;


import static com.norakomi.template.helpers.Variables.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class InputHandler_keyboard {

	public void update() {
		keyRelease();

		if (Gdx.input.isKeyPressed(Keys.LEFT)) {
			MOVINGLEFT = true;
		}
		if (Gdx.input.isKeyPressed(Keys.DOWN)) {
			MOVINGDOWN = true;
		}
		if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
			MOVINGRIGHT = true;
		}
		if (Gdx.input.isKeyPressed(Keys.UP)) {
			System.out.println("up pressed");
			MOVINGUP = true;
		}

	}
	
	public void keyRelease() {
		MOVINGLEFT = false;
		MOVINGRIGHT = false;
		MOVINGUP = false;
		MOVINGDOWN = false;

	}
}
