package com.norakomi.template;


import java.awt.Image;
import java.awt.Rectangle;

import com.badlogic.gdx.graphics.Texture;
import com.norakomi.template.helpers.AssetLoader;

import Entities.Robot;

public class Tile {

	public int tileX, tileY, speedX, type;
	public Texture tileImage;
	private Robot robot = GameScreen.getRobot();
	private Rectangle r;
	private AssetLoader al;

	public Tile(int x, int y, int typeInt, AssetLoader al) {
		this.al = al;
		tileX = x * 16;
		tileY = y * 16;

		type = typeInt;

		r = new Rectangle();

		if (type == 5) {
			tileImage = al.tiledirt;
		} else if (type == 8) {
			tileImage = al.tilegrassTop;
		} else if (type == 4) {
			tileImage = al.tilegrassLeft;

		} else if (type == 6) {
			tileImage = al.tilegrassRight;

		} else if (type == 2) {
			tileImage = al.tilegrassBot;
		} else {
			type = 0;
		}

	}

	public void TileCheckEnemy() {
		r.setBounds(tileX, tileY, 16, 16);
		// Check collision between Enemies/Objects and background tiles
		if (r.intersects(GameScreen.enemies
				.get(GameScreen.EnemyNumberBeingUpdated).CollisionRectangle)
				&& type == 8)
			GameScreen.enemies.get(GameScreen.EnemyNumberBeingUpdated)
					.setCollision(true);
	}

	public void TileCheckWall() {
		r.setBounds(tileX, tileY, 16, 16);
		// Check collision between Enemies/Objects and foreground (tile 8)
		if (type == 8)
			GameScreen.enemies.get(GameScreen.EnemyNumberBeingUpdated)
					.setwallCollision(true);
	}

	public void TileCheckEnemyFloor() {
		r.setBounds(tileX, tileY, 16, 16);
		// Check collision between Enemies/Objects and foreground (tile 8)
		if (type == 8)
			GameScreen.enemies.get(GameScreen.EnemyNumberBeingUpdated)
					.setfloorCollision(true);
	}

	public void TileCheckRopeBelow() {
		r.setBounds(tileX, tileY, 16, 16);
		// Check collision between Enemies/Objects and foreground (tile 8)
		if (type == 6)
			GameScreen.enemies.get(GameScreen.EnemyNumberBeingUpdated)
					.setropebelowcollision(true);
	}

	public void TileCheckRopeAbove() {
		r.setBounds(tileX, tileY, 16, 16);
		// Check collision between Enemies/Objects and foreground (tile 8)
		if (type == 6)
			GameScreen.enemies.get(GameScreen.EnemyNumberBeingUpdated)
					.setropeabovecollision(true);
	}

	public void TileCheckEnemyEndRopeBelowBandit() {
		r.setBounds(tileX, tileY, 16, 16);
		// Check collision between Enemies/Objects and foreground (tile 8)
		if (type == 8)
			GameScreen.enemies.get(GameScreen.EnemyNumberBeingUpdated)
					.setendropebottom(true);
	}

	public void TileCheckEnemyEndRopeAboveBandit() {
		r.setBounds(tileX, tileY, 16, 16);
		// Check collision between Enemies/Objects and foreground (tile 8)
		if (type == 8)
			GameScreen.enemies.get(GameScreen.EnemyNumberBeingUpdated)
					.setendropetop(true);
	}

	public void TileCheckPlayer() {
		r.setBounds(tileX, tileY, 16, 16);

		// player-tile collision
		// if (r.intersects(Robot.CompleteCollisionPlayerRectangle)) {

		// set player to fall if not jumping nor standing on foreground
		if (Robot.isJumped() == false && r.intersects(Robot.footleft)
				&& r.intersects(Robot.footright) && type == 0) {
			Robot.fall();
		}

		// end fall if player was falling and reaches foreground
		if (Robot.jumped == true
				&& Robot.JumpTablePointer > Robot.MiddleOfJumpTable - 1) {
			if (r.intersects(Robot.floorcheck) && type == 8) {
				robot.setJumped(false);
				robot.setCenterY(tileY - 31 - 5);
				Robot.jumpinterrupted = false;
				Robot.attack = false;
			}
		}

		// set player to climb up if pressed up and reaching a climbing rope
		if (Robot.verticalMovement == -1 && Robot.isClimbing() == false
				&& Robot.JumpTablePointer == 01) {
			if (r.intersects(Robot.floorcheck) && type == 6) {
				robot.setJumped(false);
				robot.setClimbing(true);
				robot.setCenterY(tileY - 21 - 6);
				robot.setCenterX(tileX - 07);
			}
		}

		// set player to climb down if pressed down and reaching a climbing
		// rope
		if (Robot.verticalMovement == +1 && Robot.isClimbing() == false
				&& Robot.isJumped() == false) {
			if (r.intersects(Robot.ropebottomcheck) && type == 6) {
				robot.setJumped(false);
				robot.setClimbing(true);
				robot.setCenterY(tileY - 16);
				robot.setCenterX(tileX - 07);
			}
		}

		// end climbing top of rope
		if (Robot.isClimbing() == true) {
			if (r.intersects(Robot.ceilingcheck) && type == 8) {
				if (Robot.centerY == tileY - 2) {
					Robot.centerY = tileY - 35;
				}
			}
		}

		// end climbing bottom of rope
		if (Robot.isClimbing() == true) {
			if (r.intersects(Robot.floorcheck) && type == 8) {
				robot.setJumped(false);
				robot.setClimbing(false);
			}
		}

		// collision detection left and right side
		if (Robot.isClimbing() == false) {
			if (r.intersects(Robot.wallleft) && type == 8) {
				robot.setCenterX(tileX + 10);
				if (Robot.isJumped() == true)
					Robot.jumpinterrupted = true;
			}
			if (r.intersects(Robot.wallright) && type == 8) {
				robot.setCenterX(tileX - 24);
				if (Robot.isJumped() == true)
					Robot.jumpinterrupted = true;
			}
		}

		// collision detection ceiling
		if (Robot.isClimbing() == false) {
			if (r.intersects(Robot.ceilingcheck) && type == 8) {
				robot.setCenterY(tileY + 14);
			}
		}
	}

	// }

	public int getTileX() {
		return tileX;
	}

	public void setTileX(int tileX) {
		this.tileX = tileX;
	}

	public int getTileY() {
		return tileY;
	}

	public void setTileY(int tileY) {
		this.tileY = tileY;
	}

	public Texture getTileImage() {
		return tileImage;
	}

	public void setTileImage(Texture tileImage) {
		this.tileImage = tileImage;
	}

}