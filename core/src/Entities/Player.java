package Entities;

import java.awt.Rectangle;
import java.util.ArrayList;

public class Player {

	// Constants are Here
	final int MOVESPEED = 5;

	public static boolean jumped;
	public static boolean jumpinterrupted;
	public static boolean climbing;
	public static int copycenterX;
	public static int copycenterY;
	public static int centerX;
	public static int centerY;
	public static int direction;
	public static int speedX;
	public static int speedY;
	public static int horizontalmovementspeed;
	public static int horizontalMovement;
	public static int verticalMovement;
	public static int oldverticalMovement;
	public static int animateclimbing;
	public static int MiddleOfJumpTable = 18;
	public static int TablePointer;
	
	public static Rectangle wallleft = new Rectangle(0, 0, 0, 0);
	public static Rectangle wallright = new Rectangle(0, 0, 0, 0);

	public static Rectangle footleft = new Rectangle(0, 0, 0, 0);
	public static Rectangle footright = new Rectangle(0, 0, 0, 0);

	public static Rectangle floorcheck = new Rectangle(0, 0, 0, 0);
	public static Rectangle ceilingcheck = new Rectangle(0, 0, 0, 0);

	public static Rectangle ropebottomcheck = new Rectangle(0, 0, 0, 0);

	public static Rectangle playerbody = new Rectangle(0, 0, 0, 0);

	// private Background bg1 = StartingClass.getBg1();
	// private Background bg2 = StartingClass.getBg2();

	public void update() {
		// Jumptable Y values that are added to player per frame while jumping
		int[] jumpTableArray = { -5, -4, -4, -3, -3, -3, -2, -3, -2, -2, -1,
				-1, -2, -1, -1, -0, -1, 0, 0, 1, 0, 1, 1, 2, 1, 1, 2, 2, 3, 2,
				3, 3, 3, 4, 4, 4, 4, 5, 4, 4, 5, 4, 5, 5, 5, 6, 100 };

		// Handles Jumping
		if (jumped == true) {
			centerY += jumpTableArray[TablePointer];
			if (jumpTableArray[TablePointer + 1] != 100) {
				TablePointer += 1;
			}
		}

		// set all rectagular areas around player that are used for collision
		wallleft.setRect(centerX + 06, centerY + 06, 06, 20);
		wallright.setRect(centerX + 18, centerY + 06, 06, 20);
		floorcheck.setRect(centerX + 10, centerY + 30 + 6, 12, 02);
		ceilingcheck.setRect(centerX + 10, centerY + 00, 12, 02);
		footleft.setRect(centerX + 10, centerY + 34 + 6, 02, 02);
		footright.setRect(centerX + 20, centerY + 34 + 6, 02, 02);
		ropebottomcheck.setRect(centerX + 10, centerY + 50 + 6, 12, 02);
		playerbody.setRect(centerX + 06, centerY + 5, 18, 32);
	}

	public static void jump() {
		if (jumped == false) {
			jumped = true;
			TablePointer = 0;
			jumpinterrupted = false;
		}

	}

	public static void fall() {
		jumped = true;
		TablePointer = MiddleOfJumpTable;
		horizontalMovement = 0;
		jumpinterrupted = true;
	}

	public void shoot() {
	}

	public static int getCenterX() {
		return centerX;
	}

	public static int getCenterY() {
		return centerY;
	}

	public static boolean isJumped() {
		return jumped;
	}

	public static boolean isClimbing() {
		return climbing;
	}

	public void endJump() {
		speedY = 0;
	}

	public int getSpeedX() {
		return speedX;
	}

	public int getSpeedY() {
		return speedY;
	}

	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}

	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}

	public void setJumped(boolean jumped) {
		this.jumped = jumped;
	}

	public void setClimbing(boolean climbing) {
		this.climbing = climbing;
	}

	public void setFall(boolean fall) {
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public void setSpeedY(int speedY) {
		this.speedY = speedY;
	}
}
