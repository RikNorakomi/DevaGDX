package Entities;



import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

public class Enemies {
	private int x, y, enemytype, enemysubtype, speedY, enemyRectX, enemyRectY,
			action, timer, CopyYatstart, freevariable1, freevariable2, speedX,
			movementPointer, direction, xofrope, waitclimbframes,
			collisionvariable1, collisionvariable2;
	private boolean visible = true;
	private boolean dropcollision, ropebelowcollision, ropeabovecollision,
			endropetop, endropebottom, floorcollision, wallCollision;
	public Image sprite = StartingClass.emptysprite;
	public Rectangle CollisionRectangle = new Rectangle(0, 0, 0, 0);
	public Rectangle skeletonSpawnRectangle = new Rectangle(0, 0, 0, 0);
	public Rectangle skeletonOutofRangeRectangle = new Rectangle(0, 0, 0, 0);

	private int proximityWidth = 600;
	private int proximityHeight = proximityWidth / 2;
	private int outofrangeWidth = 1600;
	private int outofrangeHeight = outofrangeWidth / 2;
	private int tilenumber;

	public Enemies(int startX, int startY, int type, int subtype) {
		x = startX;
		y = startY;
		enemytype = type;
		enemysubtype = subtype;
		action = 1;
		timer = 0;
		ropebelowcollision = false;
		ropeabovecollision = false;
		endropetop = false;
		endropebottom = false;
		dropcollision = false;
		floorcollision = false;
		wallCollision = false;
		direction = 1;
		freevariable1 = 0;
		freevariable2 = 0;
		waitclimbframes = 0;
	}

	public void update() {
		// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// WATERDROP
		// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		if (enemytype == 1) {

			if (enemysubtype == 1) {
				action = 2;
				enemysubtype = 0;
				timer = 70;
				CopyYatstart = y;
			}

			// dropCollisionRectangle.setRect(centerX +3, centerY, 9, 26);
			enemyRectX = 14;
			enemyRectY = 26;
			CollisionRectangle.setRect(x, y, enemyRectX, enemyRectY);

			// handle drop Action 1 (spawning and falling down)
			if (action == 1) {
				timer += 1;
				if (timer == 1)
					CopyYatstart = y;
				if (timer == 10)
					sprite = StartingClass.drop1;
				if (timer == 20)
					sprite = StartingClass.drop2;
				if (timer == 34)
					dropcollision = false;
				if (timer > 30) {
					// perform tilecheck
					tilenumber = ((2 + y / 16) * StartingClass.maplenght) + x
							/ 16;
					StartingClass.tilearray.get(tilenumber + 0)
							.TileCheckEnemy();

					sprite = StartingClass.drop3;
					y += 3;

					if (CollisionRectangle.intersects(Robot.playerbody)
							|| dropcollision == true) {
						if (timer > 34) {

							if (CollisionRectangle.intersects(Robot.playerbody)) {
								Robot.beinghit();
							}

							action = 2;
							freevariable1 = 0;
							sprite = StartingClass.drop4;
						}
					}
				}
			}
			// handle drop Action 2 (dissapearing and waiting for respawn)
			if (action == 2) {

				timer += 1;
				freevariable1 += 1;
				if (freevariable1 > 10) {
					sprite = StartingClass.emptysprite;

					if (enemysubtype == 2) {
						if (timer > 90) {
							y = CopyYatstart;
							action = 1;
							timer = 0;
						}
					}

					else if (timer > 140) {
						y = CopyYatstart;
						action = 1;
						timer = 0;
					}

				}
			}
		}

		// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// SKELETON
		// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		if (enemytype == 2) {

			enemyRectX = 20;
			enemyRectY = 24;

			CollisionRectangle.setRect(x, y, enemyRectX, enemyRectY);

			skeletonSpawnRectangle.setRect(x + 16 - proximityWidth / 2, y + 16
					- proximityHeight / 2, proximityWidth, proximityHeight);
			skeletonOutofRangeRectangle.setRect(x + 16 - outofrangeWidth / 2, y
					+ 16 - outofrangeHeight / 2, outofrangeWidth,
					outofrangeHeight);

			// handle Skeleton Action 1 (dead and waiting to become alive)
			if (action == 1) {
				sprite = StartingClass.emptysprite;
				if (skeletonSpawnRectangle.intersects(Robot.playerbody)) {
					action = 2;
					timer = 0;
				}
			}

			// handle Skeleton Action 2 (spawning)
			if (action == 2) {
				sprite = StartingClass.dirtycrawlerSpawnAnimation.getImage();
				timer += 1;
				if (timer == 100) {
					action = 3;
				}

			}

			// handle Skeleton Action 3 (left/right movement)
			if (action == 3) {

				if (!skeletonOutofRangeRectangle.intersects(Robot.playerbody)) {
					action = 1;
				}

				if (speedX == 1)
					sprite = StartingClass.dirtycrawlerRightAnimation
							.getImage();
				else
					sprite = StartingClass.dirtycrawlerLeftAnimation.getImage();

				movementPointer += 1;
				if (movementPointer == 400)
					movementPointer = 0;

				if (movementPointer < 200)
					speedX = 1;
				else
					speedX = -1;

				x += speedX;

				// check if skeleton hits player
				if (CollisionRectangle.intersects(Robot.playerbody)) {
					Robot.beinghit();
				}

				// check if skeleton is hit by player
				if (Robot.attack == true
						&& CollisionRectangle
								.intersects(Robot.PlayerAttackRectangle)) {
					action = 4;
					timer = 0;
					sprite = StartingClass.dirtycrawlerdied;
					StartingClass.sfxnumber = 2;
				}
			}

			// handle Skeleton Action 4 (dying)
			if (action == 4) {
				timer += 1;
				if (timer == 20) {
					sprite = StartingClass.emptysprite;
				}
				if (timer == 2000) {
					action = 1;
				}
			}

		}

		// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// BANDIT
		// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		if (enemytype == 3) {

			enemyRectX = 16;
			enemyRectY = 24;

			CollisionRectangle.setRect(x + 8, y, enemyRectX, enemyRectY);

			// check collision between bandit and player
			if (action != 7) {
				if (CollisionRectangle.intersects(Robot.playerbody)) {
					Robot.beinghit();
				}
			}

			// handle bandit Action 1 (Falling/Jumping)
			if (action == 1) {
				if (direction == 1)
					sprite = StartingClass.banditRightRun2;
				if (direction == -1)
					sprite = StartingClass.banditLeftRun2;

				y += 1;

				// check for foregrond tile / floorcheck
				tilenumber = (((y + 37) / 16) * StartingClass.maplenght)
						+ (x / 16);
				StartingClass.tilearray.get(tilenumber).TileCheckEnemyFloor();
				if (floorcollision == true) {
					floorcollision = false; // reset floor collision detection
					action = 2; // running action
					y = (y / 16) * 16 + 11; // snap y
				}
			}

			// check if bandit is hit by player
			if (action == 2 || action == 5 || action == 6) {
				if (Robot.attack == true
						&& CollisionRectangle
								.intersects(Robot.PlayerAttackRectangle)) {
					StartingClass.sfxnumber = 9;
					action = 7;
					timer = 0;
				}
			}

			// handle bandit Action 2 (Running Left/Right)
			if (action == 2) {
				// move x bandit
				timer += 1;
				if (timer == 3) {
					freevariable1 += 1;
					timer = 1;
					if (freevariable1 == 3) {
						timer = 0;
						freevariable1 = 0;
					}
				}
				x += (timer * direction);

				// animate bandit
				if (direction == 1) {
					sprite = StartingClass.banditRunRightAnimation.getImage();
				} else
					sprite = StartingClass.banditRunLeftAnimation.getImage();

				Random rand1 = new Random();
				int n1 = rand1.nextInt(2);
				freevariable2 += n1;
				// if (freevariable2 == 80) {
				if (freevariable2 == 80) {
					freevariable2 = 0;
					action = 5; // action turn around / shoot
				}

				// check for foregrond tile / wallcheck
				tilenumber = (((y + 16) / 16) * StartingClass.maplenght)
						+ (x / 16);
				StartingClass.tilearray.get(tilenumber + 1).TileCheckWall();
				if (wallCollision == true) {
					wallCollision = false;
					direction = direction * -1;
					x = x + (direction * 4);
				}

				// check for foregrond tile / floorcheck
				floorcollision = false; // reset floor collision detection
				tilenumber = (((y + 37) / 16) * StartingClass.maplenght)
						+ ((x + 16) / 16);
				StartingClass.tilearray.get(tilenumber).TileCheckEnemyFloor();
				if (floorcollision == false) {
					direction = direction * -1;
					x += (4 * direction);
				}

				// the action climbing can only be performed if waitclimbing = 0
				if (waitclimbframes != 0)
					waitclimbframes -= 1;
				if (waitclimbframes == 0) {

					// check for rope tile below bandit
					tilenumber = (((y + 64) / 16) * StartingClass.maplenght)
							+ (x / 16);
					StartingClass.tilearray.get(tilenumber + 1)
							.TileCheckRopeBelow();
					if (ropebelowcollision == true) {
						ropebelowcollision = false;
						Random rand2 = new Random();
						int n2 = rand2.nextInt(3);
						if (n2 == 0) {
							x = (x / 16) * 16 + 9;// snap x
							y += 12;
							action = 4; // climbing down
						} else {
							waitclimbframes = 30;
						}
					}

					// check for foregrond tile / rope above
					tilenumber = (((y) / 16) * StartingClass.maplenght)
							+ (x / 16);
					StartingClass.tilearray.get(tilenumber + 1)
							.TileCheckRopeAbove();
					if (ropeabovecollision == true) {
						ropeabovecollision = false;
						Random rand3 = new Random();
						int n3 = rand3.nextInt(3);
						if (n3 == 0) {
							x = (x / 16) * 16 + 9; // snap x
							// y += 12;
							action = 3; // climbing up
						} else {
							waitclimbframes = 30;
						}
					}
				}
			}

			// handle bandit Action 3 (Climbing up)
			if (action == 3) {
				sprite = StartingClass.banditClimbAnimation.getImage();

				y -= 1;

				// check for foregrond tile / end rope above bandit
				tilenumber = (((y + 32) / 16) * StartingClass.maplenght)
						+ (x / 16);
				StartingClass.tilearray.get(tilenumber + 1)
						.TileCheckEnemyEndRopeAboveBandit();
				if (endropetop == true) {
					endropetop = false;
					action = 2; // running action
					y = (y / 16) * 16 - 5; // snap y
					waitclimbframes = 30;
				}
			}

			// handle bandit Action 4 (Climbing down)
			if (action == 4) {
				sprite = StartingClass.banditClimbAnimation.getImage();

				y += 1;

				// check for foregrond tile / end rope below bandit
				tilenumber = (((y + 42) / 16) * StartingClass.maplenght)
						+ (x / 16);
				StartingClass.tilearray.get(tilenumber + 1)
						.TileCheckEnemyEndRopeBelowBandit();
				if (endropebottom == true) {
					endropebottom = false;
					action = 2; // running action
					y = (y / 16) * 16 + 11; // snap y
					waitclimbframes = 30;
				}

			}

			// handle bandit Action 5 (turn around / shoot)
			if (action == 5) {

				// bandit sprite
				if (direction == 1) {
					sprite = StartingClass.banditRightStand;
				} else
					sprite = StartingClass.banditLeftStand;

				freevariable2 += 1;
				if (freevariable2 == 16 || freevariable2 == 32) {
					direction = direction * -1;
				}
				if (freevariable2 == 48) {
					freevariable2 = 0;
					action = 2;
					// if random condition met, then bandit moves towards player
					Random rand4 = new Random();
					int n4 = rand4.nextInt(3);
					if (n4 == 0) {
						if (x > Robot.centerX) {
							direction = -1;
						} else
							direction = 1;
					}

					// check if bandit is close to player, if so, shoot
					CollisionRectangle.setRect(x - 800, y - 40, 1600, 80);
					// check collision between bandit and player
					if (CollisionRectangle.intersects(Robot.playerbody)) {
						if (StartingClass.bulletinuse == false) {
							StartingClass.bulletinuse = true;
							StartingClass.bullety = y + 9;
							if (x > Robot.centerX) {
								StartingClass.bulletdirection = -1;
								StartingClass.bulletx = x - 4;
							} else {
								StartingClass.bulletdirection = 1;
								StartingClass.bulletx = x + 32;
							}
							action = 6; // action shoot
							freevariable2 = 0;
							StartingClass.sfxnumber = 8;
						}
					}

				}

			}

			// handle bandit Action 6 (shoot)
			if (action == 6) {
				freevariable2 += 1;
				if (freevariable2 == 30) {
					freevariable2 = 0;
					action = 2;
				}
				// bandit sprite
				if (x > Robot.centerX) {
					direction = -1;
				} else
					direction = 1;

				if (direction == 1) {
					sprite = StartingClass.banditRightShoot;
				} else
					sprite = StartingClass.banditLeftShoot;

			}
			// handle bandit Action 7 (lying down on the floor)
			if (action == 7) {
				sprite = StartingClass.banditDownAnimation.getImage();
				timer += 1;
				if (timer == 250) {
					timer = 0;
					action = 2;
				}

			}

		}

		// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// TELEPORT GATE
		// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		if (enemytype == 4) {
			enemyRectX = 5;
			enemyRectY = 26;
			CollisionRectangle.setRect(x + 5, y, enemyRectX, enemyRectY);

			if (CollisionRectangle.intersects(Robot.playerbody)
					&& Robot.JumpTablePointer == 1
					&& StartingClass.upPressed == -1 && freevariable1 == 0
					&& Robot.attack == false) {
				freevariable1 = 1;
				Robot.enteringteleport = true;
				StartingClass.sfxnumber = 3;
				Robot.centerX = (Robot.centerX / 16) * 16 + 8;
				Robot.centerY = (Robot.centerY / 16) * 16 + 11;
			}

			if (freevariable1 != 0)
				freevariable1 += 1;

			if (freevariable1 == 50) {
				freevariable1 = 0;
				Robot.enteringteleport = false;
				if (enemysubtype == 0) {
					Robot.centerX = 1945;
					Robot.centerY = 716;
				}
				if (enemysubtype == 1) {
					Robot.centerX = 2536;
					Robot.centerY = 588;
				}
				if (enemysubtype == 2) {
					Robot.centerX = 3849;
					Robot.centerY = 60;
				}
				if (enemysubtype == 3) {
					Robot.centerX = 3031;
					Robot.centerY = 588;
				}
				if (enemysubtype == 4) {
					Robot.centerX = 344;
					Robot.centerY = 700;
				}
				if (enemysubtype == 5) {
					Robot.centerX = 2841;
					Robot.centerY = 812;
				}

				if (enemysubtype == 6) {
					Robot.centerX = 145 * 16 - 7;
					Robot.centerY = 26 * 16;
				}

				if (enemysubtype == 7) {
					Robot.centerX = 23 * 16 - 7;
					Robot.centerY = 8 * 16;
				}

				if (enemysubtype == 8) {
					Robot.centerX = 89 * 16 - 7;
					Robot.centerY = 35 * 16;
				}

				if (enemysubtype == 9) {
					Robot.centerX = 56 * 16 - 7;
					Robot.centerY = 53 * 16;
				}

				if (enemysubtype == 10) {
					Robot.centerX = 175 * 16 - 7;
					Robot.centerY = 30 * 16;
				}

				if (enemysubtype == 11) {
					Robot.centerX = 32 * 16 - 7;
					Robot.centerY = 35 * 16;
				}

				if (enemysubtype == 12) {
					Robot.centerX = 221 * 16 - 7;
					Robot.centerY = 30 * 16;
				}

				if (enemysubtype == 13) {
					Robot.centerX = 164 * 16 - 7;
					Robot.centerY = 58 * 16;
				}

				if (enemysubtype == 14) {
					Robot.centerX = 25 * 16 - 7;
					Robot.centerY = 53 * 16;
				}

				if (enemysubtype == 15) {
					Robot.centerX = 211 * 16 - 7;
					Robot.centerY = 56 * 16;
				}

				if (enemysubtype == 16) {
					Robot.centerX = 243 * 16 - 7;
					Robot.centerY = 6 * 16;
				}

				if (enemysubtype == 17) {
					Robot.centerX = 25 * 16 - 7;
					Robot.centerY = 47 * 16;
				}

				if (enemysubtype == 18) {
					Robot.centerX = 16 * 16 - 7;
					Robot.centerY = 57 * 16;
				}

				if (enemysubtype == 19) {
					Robot.centerX = 41 * 16 - 7;
					Robot.centerY = 52 * 16;
				}

				if (enemysubtype == 20) {
					Robot.centerX = 41 * 16 - 7;
					Robot.centerY = 57 * 16;
				}

				if (enemysubtype == 21) {
					Robot.centerX = 16 * 16 - 7;
					Robot.centerY = 52 * 16;
				}

				if (enemysubtype == 22) {
					Robot.centerX = 182 * 16 - 7;
					Robot.centerY = 7 * 16;
				}

				if (enemysubtype == 23) {
					Robot.centerX = 171 * 16 - 7;
					Robot.centerY = 55 * 16;
				}

				if (enemysubtype == 24) {
					Robot.centerX = 243 * 16 - 7;
					Robot.centerY = 17 * 16;
				}

				if (enemysubtype == 25) {
					Robot.centerX = 145 * 16 - 7;
					Robot.centerY = 26 * 16;
				}

				if (enemysubtype == 26) {
					Robot.centerX = 234 * 16 - 7;
					Robot.centerY = 45 * 16;
				}

				if (enemysubtype == 27) {
					Robot.centerX = 232 * 16 - 7;
					Robot.centerY = 7 * 16;
				}

				if (enemysubtype == 28) {
					Robot.centerX = 223 * 16 - 7;
					Robot.centerY = 26 * 16;
				}

				if (enemysubtype == 29) {
					Robot.centerX = 220 * 16 - 7;
					Robot.centerY = 7 * 16;
				}

				if (enemysubtype == 30) {
					Robot.centerX = 6 * 16 - 7;
					Robot.centerY = 17 * 16;
				}

				if (enemysubtype == 31) {
					Robot.centerX = 209 * 16 - 7;
					Robot.centerY = 45 * 16;
				}

				if (enemysubtype == 32) {
					Robot.centerX = 207 * 16 - 7;
					Robot.centerY = 6 * 16;
				}

				if (enemysubtype == 33) {
					Robot.centerX = 217 * 16 - 7;
					Robot.centerY = 45 * 16;
				}

				if (enemysubtype == 34) {
					Robot.centerX = 9 * 16 - 7;
					Robot.centerY = 6 * 16;
				}

				if (enemysubtype == 35) {
					Robot.centerX = 205 * 16 - 7;
					Robot.centerY = 17 * 16;
				}

			}
		}

		// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// KEY
		// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		if (enemytype == 5) {
			enemyRectX = 14;
			enemyRectY = 26;
			CollisionRectangle.setRect(x, y, enemyRectX, enemyRectY);
			sprite = StartingClass.key;
			sprite = StartingClass.emptysprite;

			if (enemysubtype == 0) {
				sprite = StartingClass.key;
			}
			if (enemysubtype == 1) {
				sprite = StartingClass.emptysprite;
			}

			if (CollisionRectangle.intersects(Robot.playerbody)
					&& enemysubtype == 0 && StartingClass.keyinPossesion == 0) {
				enemysubtype = 1;
				StartingClass.keyinPossesion = 1;
				StartingClass.sfxnumber = 4;
			}
		}

		// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// DOOR
		// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		if (enemytype == 6) {
			enemyRectX = 50;
			enemyRectY = 56;
			CollisionRectangle.setRect(x, y, enemyRectX, enemyRectY);

			if (enemysubtype == 0) {
				sprite = StartingClass.doublealtarempty;
				if (CollisionRectangle.intersects(Robot.playerbody)
						&& StartingClass.keyinPossesion == 1) {
					enemysubtype = 1;
					StartingClass.sfxnumber = 6;
					StartingClass.keyinPossesion = 0;
				}
			}

			if (enemysubtype == 1) {
				sprite = StartingClass.doublealtar1key;
				if (CollisionRectangle.intersects(Robot.playerbody)
						&& StartingClass.keyinPossesion == 1) {
					enemysubtype = 2;
					StartingClass.sfxnumber = 6;
					StartingClass.keyinPossesion = 0;
					StartingClass.diamondsinPossesion += 1;
				}
			}

			if (enemysubtype == 2)
				sprite = StartingClass.doublealtar2keys;

			if (enemysubtype == 3) {
				sprite = StartingClass.singlealtarempty;
				if (CollisionRectangle.intersects(Robot.playerbody)
						&& StartingClass.keyinPossesion == 1) {
					enemysubtype = 4;
					StartingClass.sfxnumber = 6;
					StartingClass.keyinPossesion = 0;
					StartingClass.diamondsinPossesion += 1;
				}
			}

			if (enemysubtype == 4)
				sprite = StartingClass.singlealtar1key;

		}

		// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// FINALDOOR
		// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		if (enemytype == 7) {
			enemyRectX = 14;
			enemyRectY = 56;
			CollisionRectangle.setRect(x + 16, y, enemyRectX, enemyRectY);

			if (enemysubtype == 0) {
				sprite = StartingClass.finaldoorclosed;
				if (StartingClass.diamondsinPossesion == 6) {
					enemysubtype = 1;
				}

			}

			if (enemysubtype == 1) {
				sprite = StartingClass.finaldooropen;
				if (CollisionRectangle.intersects(Robot.playerbody)
						&& Robot.jumped == false) {
					StartingClass.GameEvent = 2;
				}
			}
		}

		// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// BULLET
		// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		if (enemytype == 8) {
			enemyRectX = 4;
			enemyRectY = 4;
			CollisionRectangle.setRect(x, y, enemyRectX, enemyRectY);

			if (StartingClass.bulletinuse == true) {
				sprite = StartingClass.bullet;

				x = StartingClass.bulletx;
				y = StartingClass.bullety;
				StartingClass.bulletx += 3 * StartingClass.bulletdirection;

				// check collision bullet with player
				if (CollisionRectangle.intersects(Robot.playerbody)
						&& Robot.enteringteleport == false) {
					Robot.beinghit();
					StartingClass.bulletinuse = false;
					StartingClass.bulletx = 0;
					x = 0;
					sprite = StartingClass.emptysprite;

				}

				// check for foregrond tile / wallcheck
				if (StartingClass.bulletdirection == -1) {
					tilenumber = (((y + 16) / 16) * StartingClass.maplenght)
							+ (x / 16);
				} else {
					tilenumber = (((y + 16) / 16) * StartingClass.maplenght)
							+ ((x - 26) / 16);
					// System.out.println("x = " + Robot.centerX);
				}

				StartingClass.tilearray.get(tilenumber + 1).TileCheckWall();
				if (wallCollision == true) {
					wallCollision = false;
					StartingClass.bulletinuse = false;
					sprite = StartingClass.emptysprite;
				}

			}
		}
	}

	public int getenemyRectX() {
		return enemyRectX;
	}

	// public void setProjectileRectX(int projectileRectX) {
	// this.projectileRectX = projectileRectX;
	// }

	public int getenemyRectY() {
		return enemyRectY;
	}

	// public void setenemyRectY(int enemyRectY) {
	// this.enemyRectY = enemyRectY;
	// }

	// public void setEnemySprite() {
	// this.thisenemysprite = thisenemysprite;
	// }

	public Image getEnemySprite() {
		return sprite;
	}

	public int getX() {
		return x;
	}

	public int getenemytype() {
		return enemytype;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setCollision(boolean dropcollision) {
		this.dropcollision = dropcollision;
	}

	public void setropebelowcollision(boolean ropebelowcollision) {
		this.ropebelowcollision = ropebelowcollision;
	}

	public void setropeabovecollision(boolean ropeabovecollision) {
		this.ropeabovecollision = ropeabovecollision;
	}

	public void setendropetop(boolean endropetop) {
		this.endropetop = endropetop;
	}

	public void setendropebottom(boolean endropebottom) {
		this.endropebottom = endropebottom;
	}

	public void setfloorCollision(boolean floorcollision) {
		this.floorcollision = floorcollision;
	}

	public void setwallCollision(boolean wallCollision) {
		this.wallCollision = wallCollision;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getSpeedY() {
		return speedY;
	}

	public void setSpeedY(int speedY) {
		this.speedY = speedY;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}
}
