package com.norakomi.template.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import static com.norakomi.template.helpers.Variables.*;

public class GuiButtons {

	// private SpriteBatch sbGui;
	// private ShapeRenderer sr;

	public Texture buttonUp = new Texture(
			"img/controller/button_up_150x119.png");
	public Texture buttonDown = new Texture(
			"img/controller/button_down_150x119.png");
	public Texture buttonLeft = new Texture(
			"img/controller/button_left_150x119.png");
	public Texture buttonRight = new Texture(
			"img/controller/button_right_150x119.png");
	public Texture buttonA = new Texture("img/controller/button_a.png");
	public Texture buttonB = new Texture("img/controller/button_b.png");
	public Texture buttonPause = new Texture("img/controller/button_pause.png");


	
	
	
	// button position x,y member variables
	public static int buttonLeftX, buttonUpX, buttonRightX,buttonDownX;
	public static int buttonLeftY, buttonUpY, buttonRightY,buttonDownY;
	public static int buttonA_X, buttonB_X, buttonPauseX;
	public static int buttonA_Y, buttonB_Y, buttonPauseY;
	
	// button width/height  member variables
	public static float buttonWidth, buttonHeight;
	public static float buttonWidthBig, buttonHeightBig;
	
	// padding variables
	public static int sideOffSet, sideOffSetBig;
	
	public GuiButtons(SpriteBatch sb, ShapeRenderer sr) {
		// super();
		// this.sbGui = sb;
		// this.sr = sr;

		// set relative padding
		sideOffSet = (int) (0.01f * Gdx.graphics.getWidth());
		sideOffSetBig = (int) (0.05f * Gdx.graphics.getWidth());
		
		//set button relative width/height
		buttonWidth = Gdx.graphics.getWidth() / 16;
		buttonHeight = Gdx.graphics.getWidth() / 16;

		buttonWidthBig = Gdx.graphics.getWidth() / 10;
		buttonHeightBig = Gdx.graphics.getWidth() / 10;
		
		
		// set button coordinates
		buttonLeftX = (int) (sideOffSet + 0 * buttonWidth);
		 buttonUpX = (int) (sideOffSet + 1 * buttonWidth);
		 buttonRightX = (int) (sideOffSet + 2 * buttonWidth);
		 buttonDownX = (int) (sideOffSet + 1 * buttonWidth);
		 
		 buttonLeftY = (int) (sideOffSet + 1 * buttonHeight);
		 buttonUpY = (int) (sideOffSet + 2 * buttonHeight);
		 buttonRightY = (int) (sideOffSet + 1 * buttonHeight);
		 buttonDownY = (int) (sideOffSet);
		 
		 buttonA_X =(int) (V_WIDTH - sideOffSetBig - sideOffSet - 2 * buttonWidthBig);
		 buttonA_Y=sideOffSetBig;
		 buttonB_X= (int) (V_WIDTH - sideOffSetBig - buttonWidthBig) ;
		 buttonB_Y= sideOffSetBig;
		 buttonPauseX=(int) (V_WIDTH - sideOffSet - buttonWidth);
		 buttonPauseY= (int) (V_HEIGHT - sideOffSet
					- buttonHeight);
		 
		 
//		public static int buttonLeftY, buttonUpY, buttonRightY,buttonDownY;
//		public static int buttonA_X, buttonB_X, buttonPauseX;
//		public static int buttonA_Y, buttonB_Y, buttonPauseY;
		
		
		


	
	}

	public void renderer(SpriteBatch sbGui, ShapeRenderer sr) {

		sr.begin(ShapeType.Line);
		sr.setColor(Color.GREEN);

		// ////////////////////////
		// Direction Control Buttons
		// ///////////////////////////

		// Left- Up Button
		sr.rect(sideOffSet + 0 * buttonWidth, sideOffSet + 2 * buttonHeight,
				buttonWidth, buttonHeight);
		// Up Button
		sr.rect(buttonUpX, buttonUpY,
				buttonWidth, buttonHeight);
		// Right-Left Button
		sr.rect(sideOffSet + 2 * buttonWidth, sideOffSet + 2 * buttonHeight,
				buttonWidth, buttonHeight);
		// Left Button
		sr.rect(buttonLeftX, buttonLeftY,
				buttonWidth, buttonHeight);
		// Right Button
		sr.rect(buttonRightX, buttonRightY,
				buttonWidth, buttonHeight);
		// Left-Down Button
		sr.rect(sideOffSet, sideOffSet, buttonWidth, buttonHeight);
		// Down Button
		sr.rect(buttonDownX, buttonDownY, buttonWidth,
				buttonHeight);
		// Down- Right Button
		sr.rect(sideOffSet + 2 * buttonWidth, sideOffSet, buttonWidth,
				buttonHeight);

		// ////////////////////////
		// Button A+B
		// ///////////////////////////

		// Button B
		sr.rect(buttonB_X, buttonB_Y,
				buttonWidthBig, buttonHeightBig);
		// Button A
		sr.rect(buttonA_X, buttonA_Y, buttonWidthBig, buttonHeightBig);
		// Button Pause
		sr.rect(buttonPauseX, buttonPauseY, buttonWidth, buttonHeight);

		sr.end();

		sbGui.setColor(1, 1, 1, 0.7f);
		sbGui.begin();

		// Movement Controller Buttons
		sbGui.draw(buttonLeft, sideOffSet, sideOffSet + buttonHeight * 1,
				buttonWidth, buttonHeight);
		sbGui.draw(buttonRight, sideOffSet + 2 * buttonWidth, sideOffSet
				+ buttonHeight * 1, buttonWidth, buttonHeight);
		sbGui.draw(buttonUp, sideOffSet + 1 * buttonWidth, sideOffSet
				+ buttonHeight * 2, buttonWidth, buttonHeight);
		sbGui.draw(buttonDown, sideOffSet + 1 * buttonWidth, sideOffSet
				+ buttonHeight * 0, buttonWidth, buttonHeight);

		// A+B button
		sbGui.draw(buttonB, V_WIDTH - sideOffSetBig - buttonWidthBig,
				sideOffSetBig, buttonWidthBig, buttonHeightBig);
		sbGui.draw(buttonA, V_WIDTH - sideOffSetBig - sideOffSet - 2
				* buttonWidthBig, sideOffSetBig, buttonWidthBig,
				buttonHeightBig);

		// Puase button
		sbGui.draw(buttonPause, V_WIDTH - sideOffSet - buttonWidth, V_HEIGHT
				- sideOffSet - buttonHeight, buttonWidth, buttonHeight);

		sbGui.end();
	}


}
