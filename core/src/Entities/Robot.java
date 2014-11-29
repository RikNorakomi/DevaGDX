package Entities;

import java.awt.Rectangle;
import java.util.ArrayList;

public class Robot {

	// Constants are Here
	final int MOVESPEED = 5;

	public static boolean jumped, jumpinterrupted, climbing, attack, PlayerHit,
			PlayerDead, enteringteleport;
	public static int copycenterX, copycenterY, centerX, centerY, direction,
			speedX, speedY, horizontalmovementspeed, horizontalMovement,
			verticalMovement, oldverticalMovement, animateclimbing,
			JumpTablePointer, attacktimer, playerhitcounter, life,
			playerhitflicker, PlayerDeadtimer;
	public static int MiddleOfJumpTable = 18;
	public static Rectangle wallleft = new Rectangle(0, 0, 0, 0);
	public static Rectangle wallright = new Rectangle(0, 0, 0, 0);
	public static Rectangle footleft = new Rectangle(0, 0, 0, 0);
	public static Rectangle footright = new Rectangle(0, 0, 0, 0);
	public static Rectangle floorcheck = new Rectangle(0, 0, 0, 0);
	public static Rectangle ceilingcheck = new Rectangle(0, 0, 0, 0);
	public static Rectangle ropebottomcheck = new Rectangle(0, 0, 0, 0);
	public static Rectangle playerbody = new Rectangle(0, 0, 0, 0);
	public static Rectangle CompleteCollisionPlayerRectangle = new Rectangle(0,
			0, 0, 0);
	public static Rectangle PlayerAttackRectangle = new Rectangle(0, 0, 0, 0);

	// private Background bg1 = StartingClass.getBg1();
	// private Background bg2 = StartingClass.getBg2();

	public void update() {
		// Jumptable Y values that are added to player per frame while jumping
		int[] jumpTableArray = { -5, -4, -4, -3, -3, -3, -2, -3, -2, -2, -1,
				-1, -2, -1, -1, -0, -1, 0, 0, 1, 0, 1, 1, 2, 1, 1, 2, 2, 3, 2,
				3, 3, 3, 4, 4, 4, 4, 5, 4, 4, 5, 4, 5, 5, 5, 6, 100 };

		if (enteringteleport == true) {
			jumped = false;
			attack = false;
		}

		// Handles Jumping
		if (jumped == true && Robot.PlayerDead == false) {
			centerY += jumpTableArray[JumpTablePointer];
			if (jumpTableArray[JumpTablePointer + 1] != 100) {
				JumpTablePointer += 1;
			}
		}

	
		
		
		// set sprites for standing/running/jumping/climbing
		if (horizontalMovement == 0 && direction == -1)
			StartingClass.currentSprite = StartingClass.devastandleft;
		if (horizontalMovement == 0 && direction == 1)
			StartingClass.currentSprite = StartingClass.devastandright;
		if (horizontalMovement == 1)
			StartingClass.currentSprite = StartingClass.devarunrightAnimation
					.getImage();
		if (horizontalMovement == -1)
			StartingClass.currentSprite = StartingClass.devarunleftAnimation
					.getImage();
		if (isJumped() && direction == -1)
			StartingClass.currentSprite = StartingClass.devajumpleft1;
		if (isJumped() && direction == +1)
			StartingClass.currentSprite = StartingClass.devajumpright1;
		if (isJumped() && direction == +1 && jumpinterrupted == true)
			StartingClass.currentSprite = StartingClass.devastandright;
		if (isJumped() && direction == -1 && jumpinterrupted == true)
			StartingClass.currentSprite = StartingClass.devastandleft;
		if (isClimbing())
			if (animateclimbing == 1) {
				StartingClass.currentSprite = StartingClass.MikeyClimbAnimation
						.getImage();
			} else
				StartingClass.currentSprite = StartingClass.devaclimb1;

		// set sprite when attacking
		if (attack == true && direction == -1 && !isJumped()) {
			StartingClass.currentSprite = StartingClass.devapunchleft;
		}
		if (attack == true && direction == +1 && !isJumped()) {
			StartingClass.currentSprite = StartingClass.devapunchright;
		}
		if (attack == true && direction == -1 && isJumped()) {
			StartingClass.currentSprite = StartingClass.devajumppunchleft;
		}
		if (attack == true && direction == +1 && isJumped()) {
			StartingClass.currentSprite = StartingClass.devajumppunchright;
		}

		// set sprite when player is hit
		if (PlayerHit == true) {
			playerhitcounter += 1;
			playerhitflicker += 1;
			if (playerhitflicker == 3) {
				playerhitflicker = 0;
				StartingClass.currentSprite = StartingClass.emptysprite;
			}
			if (playerhitcounter == 50 && PlayerDead == false) {
				PlayerHit = false;
			}
		}

		// end attack after 20 frames
		if (attack == true && Robot.PlayerDead == false) {
			attacktimer += 1;
			if (attacktimer == 20) {
				attack = false;
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
		playerbody.setRect(centerX + 11, centerY + 5, 10, 20);
		CompleteCollisionPlayerRectangle.setRect(centerX + 04, centerY - 2, 22,
				62);
		if (direction == -1)
			PlayerAttackRectangle.setRect(centerX - 7, centerY + 2, 20, 26);
		if (direction == +1)
			PlayerAttackRectangle.setRect(centerX + 18, centerY + 2, 20, 26);

	}

	public static void jump() {
		if (jumped == false) {
			jumped = true;
			JumpTablePointer = 0;
			jumpinterrupted = false;
		}
	}

	public static void beinghit() {
		if (PlayerHit == false && enteringteleport == false) {
			PlayerHit = true;
			playerhitcounter = 0;
			playerhitflicker = 0;
			life -= 1;
			StartingClass.sfxnumber=5;
			if (life == 0) {
				if (PlayerDead == false) {
					PlayerDead = true;
					PlayerDeadtimer = 0;
				}
			}

		}
	}

	public static void attack() {
		if (attack == false && !Robot.isClimbing()) {
			attack = true;
			attacktimer = 0;
			StartingClass.sfxnumber=1;
			// stop horizontal movement when attacking
			if (jumped == false) {
				Robot.horizontalMovement = 0;
			}
		}
	}

	public static void fall() {
		jumped = true;
		JumpTablePointer = MiddleOfJumpTable;
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
