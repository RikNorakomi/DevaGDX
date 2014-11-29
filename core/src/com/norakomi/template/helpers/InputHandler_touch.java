package com.norakomi.template.helpers;

import static com.norakomi.template.gui.GuiButtons.*;
import static com.norakomi.template.helpers.Variables.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;

public class InputHandler_touch {

	private OrthographicCamera cam;

	private Vector3 screencoordinates;
	private Vector3 viewcoordinates;
	private int max_fingers;
	private int xTouch, yTouch;

	public InputHandler_touch(OrthographicCamera cam) {
		this.cam = cam;
		screencoordinates = new Vector3();
		viewcoordinates = new Vector3();
		max_fingers = 2;
	}

	public void update() {
		touchRelease();

		for (int i = 0; i < max_fingers; i++) {

			 xTouch = Gdx.input.getX(i);
			 yTouch = Gdx.graphics.getHeight() - Gdx.input.getY(i); 
			// convert to Y down coordinates

		


			// ////////// A+B + PAUSE BUTTON TOUCH READ OUT ////////////////
			// A Button
			if (xTouch >= buttonA_X && xTouch <= buttonA_X + buttonWidthBig
					&& yTouch <= buttonA_Y + buttonHeightBig
					&& yTouch >= buttonA_Y) {
				System.out.println("A Touched");
				BUTTON_A_PRESSED = true;
			}

			// B Button
			if (xTouch >= buttonB_X && xTouch <= buttonB_X + buttonWidthBig
					&& yTouch <= buttonB_Y + buttonHeightBig
					&& yTouch >= buttonB_Y) {
				System.out.println("B Touched");
				BUTTON_B_PRESSED = true;
			}

			// Pause Button
			if (xTouch >= buttonPauseX && xTouch <= buttonPauseX + buttonWidth
					&& yTouch <= buttonPauseY + buttonHeight
					&& yTouch >= buttonPauseY) {
				System.out.println("pauseTouched");
				PAUSEBUTTON_PRESSED = true;
			}

			// //////////// MOVEMENT CONTROL BUTTONS TOUCH READ OUT
			// //////////////
			// Left Button
			if (xTouch >= buttonLeftX && xTouch <= buttonLeftX + buttonWidth
					&& yTouch <= buttonLeftY + buttonHeight
					&& yTouch >= buttonLeftY) {
				System.out.println("leftTouched");
				MOVINGLEFT = true;
			}

			// Right Button
			if (xTouch >= buttonRightX && xTouch <= buttonRightX + buttonWidth
					&& yTouch <= buttonRightY + buttonHeight
					&& yTouch >= buttonRightY) {
				System.out.println("rightTouched");
				MOVINGRIGHT = true;
			}

			// Down Button
			if (xTouch >= buttonDownX && xTouch <= buttonDownX + buttonWidth
					&& yTouch <= buttonDownY + buttonHeight
					&& yTouch >= buttonDownY) {
				System.out.println("downTouched");
				MOVINGDOWN = true;
			}

			// Up Button
			if (xTouch >= buttonUpX && xTouch <= buttonUpX + buttonWidth
					&& yTouch <= buttonUpY + buttonHeight
					&& yTouch >= buttonUpY) {
				System.out.println("upTouched");
				MOVINGUP = true;
			}
		
			// if needed: screen/world coordinate conversion
//			screencoordinates.set(xTouch, yTouch, 0);
//			viewcoordinates.set(screencoordinates);
//			cam.unproject(viewcoordinates, 0, 0, VIEWPORT_WIDTH,
//					VIEWPORT_HEIGHT);
			
			
			// // reset touchx/y values
			 xTouch = -1;
			 yTouch = -1;
			 System.out.println("touch x,y = " + xTouch + ", " + yTouch);
			
		}
	
		
	}

	public void touchRelease() {
		
		MOVINGLEFT = false;
		MOVINGRIGHT = false;
		MOVINGUP = false;
		MOVINGDOWN = false;
		BUTTON_A_PRESSED = false;
		BUTTON_B_PRESSED = false;
		PAUSEBUTTON_PRESSED = false;

	}

}
