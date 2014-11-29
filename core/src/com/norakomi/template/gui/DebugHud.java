package com.norakomi.template.gui;

import static com.norakomi.template.helpers.Variables.*;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class DebugHud {

	private BitmapFont font;
	private SpriteBatch sb;

	public DebugHud(BitmapFont font) {
		this.font = font;
	}

	public void render(SpriteBatch sb) {
		sb.begin();

		if (MOVINGUP)
			font.draw(sb, "up touced", 100, 60);
		if (MOVINGDOWN)
			font.draw(sb, "down touced", 100, 45);
		if (MOVINGRIGHT)
			font.draw(sb, "right touced", 100, 30);
		if (MOVINGLEFT)
			font.draw(sb, "left touced", 100, 15);

		if (BUTTON_A_PRESSED)
			font.draw(sb, "A touced", 180, 15);
		if (BUTTON_B_PRESSED)
			font.draw(sb, "B touced", 180, 15);
		if (PAUSEBUTTON_PRESSED)
			font.draw(sb, "pause touched", 180, 115);

		sb.end();
	}

}
