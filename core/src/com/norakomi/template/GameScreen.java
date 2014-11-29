package com.norakomi.template;

import static com.norakomi.template.helpers.Variables.DEBUG;
import static com.norakomi.template.helpers.Variables.VIEWPORT_HEIGHT;
import static com.norakomi.template.helpers.Variables.VIEWPORT_WIDTH;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import Entities.Enemies;
import Entities.EnemyTables;
import Entities.Robot;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.files.FileHandleStream;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.norakomi.template.gui.DebugHud;
import com.norakomi.template.gui.GuiButtons;
import com.norakomi.template.helpers.AssetLoader;
import com.norakomi.template.helpers.InputHandler_keyboard;
import com.norakomi.template.helpers.InputHandler_touch;

import kiloboltgame.framework.Animation;

public class GameScreen implements Screen {

	NorakomiGame game;

	private OrthographicCamera cam;

	private InputHandler_keyboard inputHandlerKeyboard;
	private InputHandler_touch inputHandlerTouch;

	private DebugHud debugHud;
	private SpriteBatch sb, sbGui;
	private ShapeRenderer sr;
	private BitmapFont font;
	private GuiButtons guiButtons;

	public static AssetLoader al;
	public static ArrayList<Tile> tilearray = new ArrayList<Tile>();
	public static ArrayList<Enemies> enemies = new ArrayList<Enemies>();
	private static Background bg1;
	public static boolean bulletinuse = false;
	private Texture backgroundTex;

	private static Robot robot;

	public static int screenoffsetX, screenoffsetY, spacePressed,
			oldspacePressed, leftPressed, rightPressed, upPressed,
			OldupPressed, downPressed, keyinPossesion, diamondsinPossesion,
			EnemyNumberBeingUpdated, mapheight, maplenght, Pressed1, Pressed2,
			Pressed3, Pressed4, tilenumber, bulletdirection, bulletx, bullety,
			copyoflife, playeronminimaptimer;

	public int screenwidth = 800;
	public int screenheight = 480;
	public int heightingamemap = 192;
	public static int GameEvent = 3; // 1 = titlescreen 3 = in game
	private int PreviousGameEvent = 0; // reference at switching between events
	private int World = 1;
	private static int oldmusic = 0;
	private static int music = 0;
	private int blackscreenontimer = 0;
	public static int sfxnumber = 0;
	private int worldfinishedvariable = 0;
	private int gamestarttimer = 0;

	public GameScreen(NorakomiGame game) {
		this.game = game;
	}

	@Override
	public void show() {
		// initialize level, setup camera & map
		initLevelVariables();
		// createMap();
		createCamera();

		
		// Load Assets
		al = new AssetLoader();
		al.loadTextures();
		al.loadSfx();
		al.loadMusic();
		al.loadAnimations();

		// create fonts
		font = new BitmapFont();
		font.setScale(0.5f);
		font.setColor(Color.BLUE);

		// create render objects
		sr = new ShapeRenderer();
		sb = new SpriteBatch();
		sbGui = new SpriteBatch();

		guiButtons = new GuiButtons(sbGui, sr);
		debugHud = new DebugHud(font);

		// sb.setProjectionMatrix(cam.combined);

		inputHandlerTouch = new InputHandler_touch(cam);
		inputHandlerKeyboard = new InputHandler_keyboard();

		// Original code start()
		bg1 = new Background(0, 0);
		robot = new Robot();

		EnemyTables.CalculateTableLenght();

	}

	@Override
	public void render(float delta) {
		// Clear pixel every frame update
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// //// code uit original run()

		if (blackscreenontimer != 0)
			blackscreenontimer -= 1;

		if (sfxnumber != 0) al.playsfx(sfxnumber);
		
		// TITLESCREEN
					if (GameEvent == 1) { // 1 = titlescreen 3 = in game
						if (PreviousGameEvent != 1) {
							PreviousGameEvent = 1;
							music = 4; // 1=level1 2=level2 3=level3 4=titlescreen
							// 5=gameover 6=level finished
							al.playMusic();
						}
						if (Pressed1 == 1) {
							GameEvent = 3; // 1 = titlescreen 3 = in game
							World = 1;
							blackscreenontimer = 30;
						}
						if (Pressed2 == 1) {
							GameEvent = 3;
							World = 2;
							blackscreenontimer = 30;
						}
						if (Pressed3 == 1) {
							GameEvent = 3;
							World = 3;
							blackscreenontimer = 30;
						}

						if (gamestarttimer == 0) {
							if (Pressed4 == 1) {
								gamestarttimer = 1;
								music = 7; // 1=level1 2=level2 3=level3 4=titlescreen
								// 5=gameover 6=level finished 7=start game
								al.playMusic();
							}
						}

						if (gamestarttimer != 0) {
							gamestarttimer += 1;
							if (gamestarttimer == 100) {
								gamestarttimer = 0;
								GameEvent = 3;
								World = 1;
								blackscreenontimer = 30;
							}
						}
					}
					
					// WORLD FINISHED
					if (GameEvent == 2) { // 1 = titlescreen 3 = in game
						if (PreviousGameEvent != 2) {
							PreviousGameEvent = 2;
							music = 6; // 1=level1 2=level2 3=level3 4=titlescreen
							// 5=gameover 6=level finished
							al.playMusic();
						}

						worldfinishedvariable += 1;
						if (worldfinishedvariable == 250) {
							worldfinishedvariable = 0;

							GameEvent = 3;
							World += 1;
							blackscreenontimer = 30;

							if (World == 4)
								GameEvent = 4;
						}
					}
					
					// IN GAME
					if (GameEvent == 3) { // 1 = titlescreen 3 = in game

						if (PreviousGameEvent != 3) {
							PreviousGameEvent = 3;
							// initialise world

							// we try to remove the map, which only works if a map
							// has been loaded, now we dont care which map gets
							// removed, cuz all maps have the same size
							try {
								// LEES: FILEHANDLE IN LIBGDX WIKI
								removeMap("GooniesWorld1.map");
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

							// remove all enemies from array if not empty
							while (enemies.size() != 0) {
								enemies.remove(0);
							}

							if (World == 1) {
								Robot.centerX = 320; // starting coordinates
//								Robot.centerX = 620; // starting coordinates
								Robot.centerY = 140;

								// Robot.centerX = 160 * 16; // starting coordinates
								// Robot.centerY = 10 * 16;

//								background = getImage(base, "ts/GooniesWorld1.png");
								backgroundTex = new Texture("ts/GooniesWorld1.png" );
								
								// is in assetLoader ingeladen
								// Initialize Tiles
								try {
									// LEES: FILEHANDLE IN LIBGDX WIKI
									loadMap("ts/GooniesWorld1.map");
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}

								// load enemies/ add enemies to array
								for (int i = 0; i < EnemyTables.amountofenemiesWorld1; i++) {
									Enemies p = new Enemies(
											EnemyTables.EnemiesWorld1ArrayTable[i
													* EnemyTables.varPerEnem + 0],
											EnemyTables.EnemiesWorld1ArrayTable[i
													* EnemyTables.varPerEnem + 1],
											EnemyTables.EnemiesWorld1ArrayTable[i
													* EnemyTables.varPerEnem + 2],
											EnemyTables.EnemiesWorld1ArrayTable[i
													* EnemyTables.varPerEnem + 3]);
									enemies.add(p);
								}
								music = 1; // 1=level1 2=level2 3=level3 4=titlescreen
								// 5=gameover 6=level finished
							al.playMusic();
							}
							if (World == 2) {
								Robot.centerX = 99; // starting coordinates
								Robot.centerY = 124;

						// !!		background = getImage(base, "ts/GooniesWorld2.png");
								backgroundTex = new Texture("ts/GooniesWorld2.png" );
								
								
								
								// Initialize Tiles
								try {
									
									// LEES: FILEHANDLE IN LIBGDX WIKI
									loadMap("ts/GooniesWorld2.map");
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}

								// load enemies/ add enemies to array
								for (int i = 0; i < EnemyTables.amountofenemiesWorld2; i++) {
									Enemies p = new Enemies(
											EnemyTables.EnemiesWorld2ArrayTable[i
													* EnemyTables.varPerEnem + 0],
											EnemyTables.EnemiesWorld2ArrayTable[i
													* EnemyTables.varPerEnem + 1],
											EnemyTables.EnemiesWorld2ArrayTable[i
													* EnemyTables.varPerEnem + 2],
											EnemyTables.EnemiesWorld2ArrayTable[i
													* EnemyTables.varPerEnem + 3]);
									enemies.add(p);
								}
								music = 2; // 1=level1 2=level2 3=level3 4=titlescreen
								// 5=gameover 6=level finished
								al.playMusic();
							}
							if (World == 3) {
								Robot.centerX = 200; // starting coordinates
								Robot.centerY = 172;

								// Robot.centerX = 143 * 16; // starting coordinates
								// Robot.centerY = 2 * 16;

								//background = getImage(base, "ts/GooniesWorld3.png");
								backgroundTex = new Texture("ts/GooniesWorld3.png" );
								// Initialize Tiles
								try {
									loadMap("ts/GooniesWorld3.map");
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}

								// load enemies/ add enemies to array
								for (int i = 0; i < EnemyTables.amountofenemiesWorld3; i++) {
									Enemies p = new Enemies(
											EnemyTables.EnemiesWorld3ArrayTable[i
													* EnemyTables.varPerEnem + 0],
											EnemyTables.EnemiesWorld3ArrayTable[i
													* EnemyTables.varPerEnem + 1],
											EnemyTables.EnemiesWorld3ArrayTable[i
													* EnemyTables.varPerEnem + 2],
											EnemyTables.EnemiesWorld3ArrayTable[i
													* EnemyTables.varPerEnem + 3]);
									enemies.add(p);
								}
								music = 3; // 1=level1 2=level2 3=level3 4=titlescreen
								// 5=gameover 6=level finished
							al.playMusic();
							}

							Robot.life = 16;
							Robot.jumped = false;
							Robot.PlayerDead = false;
							Robot.jumpinterrupted = false;
							Robot.climbing = false;
							Robot.PlayerHit = false;
							Robot.enteringteleport = false;
							Robot.direction = 1;
							Robot.speedX = 0;
							Robot.speedY = 0;
							Robot.JumpTablePointer = Robot.MiddleOfJumpTable;
							Robot.horizontalMovement = 0;
							Robot.horizontalmovementspeed = 0;
							Robot.verticalMovement = 0;
							Robot.oldverticalMovement = 0;
							Robot.animateclimbing = 0;
							keyinPossesion = 1;
							diamondsinPossesion = 0;
							bulletinuse = false;
						}

						// handle all objects and enemies
						for (int i = 0; i < enemies.size(); i++) {
							Enemies p = (Enemies) enemies.get(i);
							// if (p.isVisible() == true) {
							EnemyNumberBeingUpdated = i;
							if (Robot.PlayerDead == false) {
								p.update();
								// }
							} // else {
								// enemies.remove(i);

							// }
						}

						// check player dead
						if (Robot.PlayerDead == true) {
							Robot.PlayerDeadtimer += 1;
							if (Robot.PlayerDeadtimer == 1) {
								music = 5; // 1=level1 2=level2 3=level3 4=titlescreen
								// 5=gameover 6=level finished
								al.playMusic();
							}
							if (Robot.PlayerDeadtimer == 700) {
								Robot.PlayerDeadtimer = 0;
								GameEvent = 1;
							}

						}

						if (Pressed1 == 1) {
							GameEvent = 1;
						}

						robot.update();

						if (Robot.PlayerDead == true) {
							Robot.horizontalmovementspeed = 0;
							Robot.horizontalMovement = 0;
							upPressed = 0;
							downPressed = 0;
							leftPressed = 0;
							rightPressed = 0;
							spacePressed = 0;
						}

						if (Robot.horizontalmovementspeed == 1)
							Robot.horizontalmovementspeed = 2;
						else
							Robot.horizontalmovementspeed = 1;

						if (Robot.isJumped() && Robot.horizontalmovementspeed == 1)
							Robot.horizontalmovementspeed = 2;

						if (!Robot.isJumped() && Robot.attack == false) {
							Robot.verticalMovement = upPressed + downPressed;
							Robot.horizontalMovement = leftPressed + rightPressed;
						}

						if (Robot.isClimbing() || Robot.jumpinterrupted == true)
							Robot.horizontalMovement = 0;

						if (Robot.horizontalMovement == -1) {
							Robot.direction = -1;
							Robot.centerX -= Robot.horizontalmovementspeed;
						}
						if (Robot.horizontalMovement == 1) {
							Robot.direction = 1;
							Robot.centerX += Robot.horizontalmovementspeed;
						}
						if (Robot.verticalMovement == -1
								&& Robot.oldverticalMovement == 0
								&& !Robot.isClimbing())
							Robot.jump();
						Robot.oldverticalMovement = Robot.verticalMovement;

						if (Robot.verticalMovement == -1 && Robot.isClimbing()) {
							Robot.centerY -= 1;
							Robot.animateclimbing = 1;
						}
						if (Robot.verticalMovement == +1 && Robot.isClimbing()) {
							Robot.centerY += 1;
							Robot.animateclimbing = 1;
						}
						if (Robot.verticalMovement == +0 && Robot.isClimbing()) {
							Robot.animateclimbing = 0;
						}

						if (spacePressed == 1 && oldspacePressed == 0) {
							Robot.attack();
						}
						oldspacePressed = spacePressed;

						// set background starting coordinates (Background.bgX/Y) &
						// inscreen
						// coordinates (screenoffsetX/Y) for characters
						Robot.copycenterX = Robot.centerX;
						Robot.copycenterY = Robot.centerY;
						Background.bgX = 0;
						Background.bgY = 0;
						screenoffsetX = 0;
						screenoffsetY = 0;

						while (Robot.copycenterX > screenwidth - 1 - 16) {
							Robot.copycenterX -= screenwidth;
							Background.bgX -= screenwidth;
							screenoffsetX += screenwidth;
						}

						while (Robot.copycenterY > screenheight - 1 - heightingamemap
								+ 16) {
							Robot.copycenterY -= screenheight - heightingamemap + 32;
							Background.bgY -= screenheight - heightingamemap + 32;
							screenoffsetY += screenheight - heightingamemap + 32;
						}

						TileCollisionCheck();
						animate();
					}
		
					
					

		// //////////////

		handleTouchEvents();
		guiButtons.renderer(sbGui, sr);

		// set debug hud
		if (DEBUG)
			debugHud.render(sb);

		// debug draw routines
		sb.begin();
		sb.draw(al.devaclimb1, 100, 100);
		sb.end();

		cam.update();
	}

	public void playsfx() {
		if (sfxnumber != 0) {

		}
	}

	private void loadMap(String filename) throws IOException {
		ArrayList lines = new ArrayList();
		int width = 0;
		int height = 0;

		BufferedReader reader = new BufferedReader(new FileReader(filename));
		while (true) {
			String line = reader.readLine();
			// no more lines to read
			if (line == null) {
				reader.close();
				break;
			}

			if (!line.startsWith("!")) {
				lines.add(line);
				width = Math.max(width, line.length());
			}
		}
		height = lines.size();
		mapheight = height;
		maplenght = width - 1;

		for (int j = 0; j < height; j++) {
			String line = (String) lines.get(j);
			for (int i = 0; i < width; i++) {

				if (i < line.length()) {
					char ch = line.charAt(i); // charAt(i);
					Tile t = new Tile(i, j, Character.getNumericValue(ch), al);
					tilearray.add(t);
				}
			}
		}
	}
	
	private void TileCollisionCheck() {
		// System.out.println("x = " + Robot.centerX + " " + Robot.centerX/16);
		// System.out.println("y = " + Robot.centerY + " " + Robot.centerY/16);
		// System.out.println("maplenght = " + maplenght);
		// System.out.println("mapheight = " + mapheight);

		tilenumber = (Robot.centerY / 16 * maplenght) + Robot.centerX / 16;

		// System.out.println("tilenumber = " + tilenumber);
		// System.out.println("tilenumber x = " + Robot.centerX/16);
		// System.out.println("tilenumber y = " + Robot.centerY/16);
		// System.out.println("tilenumber = " + (Robot.centerY/16
		// *maplenght+Robot.centerX/16 + maplenght*0 +0));

		// we will check 3 x 5 tiles that surround the player
		tilearray.get(tilenumber + 0).TileCheckPlayer();
		tilearray.get(tilenumber + 1).TileCheckPlayer();
		tilearray.get(tilenumber + 2).TileCheckPlayer();

		tilearray.get(tilenumber + 0 + maplenght).TileCheckPlayer();
		tilearray.get(tilenumber + 1 + maplenght).TileCheckPlayer();
		tilearray.get(tilenumber + 2 + maplenght).TileCheckPlayer();

		tilearray.get(tilenumber + 0 + maplenght + maplenght).TileCheckPlayer();
		tilearray.get(tilenumber + 1 + maplenght + maplenght).TileCheckPlayer();
		tilearray.get(tilenumber + 2 + maplenght + maplenght).TileCheckPlayer();

		tilearray.get(tilenumber + 0 + maplenght + maplenght + maplenght)
				.TileCheckPlayer();
		tilearray.get(tilenumber + 1 + maplenght + maplenght + maplenght)
				.TileCheckPlayer();
		tilearray.get(tilenumber + 2 + maplenght + maplenght + maplenght)
				.TileCheckPlayer();

		tilearray.get(
				tilenumber + 0 + maplenght + maplenght + maplenght + maplenght)
				.TileCheckPlayer();
		tilearray.get(
				tilenumber + 1 + maplenght + maplenght + maplenght + maplenght)
				.TileCheckPlayer();
		tilearray.get(
				tilenumber + 2 + maplenght + maplenght + maplenght + maplenght)
				.TileCheckPlayer();

		// System.out.println("tilearray size = " + tilearray.size());

		// for (int i = 0; i < tilearray.size(); i++) {
		// Tile t = (Tile) tilearray.get(i);
		// tilearray.get(i).TileCheckPlayer();
		// // t.TileCheckPlayer();
		// }
	}

	private void removeMap(String filename) throws IOException {
		ArrayList lines = new ArrayList();
		int width = 1000;

		BufferedReader reader = new BufferedReader(new FileReader(filename));
		while (true) {
			String line = reader.readLine();
			// no more lines to read
			if (line == null) {
				reader.close();
				break;
			}

			if (!line.startsWith("!")) {
				lines.add(line);
			}
		}

		for (int j = 0; j < mapheight; j++) {
			String line = (String) lines.get(j);
			for (int i = 0; i < width; i++) {

				if (i < line.length()) {
					tilearray.remove(0);
				}
			}
		}
	}
	
	public void animate() {
		al.devarunrightAnimation.update(1);
		al.devarunleftAnimation.update(1);
		al.MikeyClimbAnimation.update(1);
		al.dirtycrawlerSpawnAnimation.update(1);
		al.dirtycrawlerLeftAnimation.update(1);
		al.dirtycrawlerRightAnimation.update(1);
		al.banditRunLeftAnimation.update(1);
		al.banditRunRightAnimation.update(1);
		al.banditClimbAnimation.update(1);
		al.	banditDownAnimation.update(1);

	}

	public void handleTouchEvents() {
		System.out.println("handletoucheventss");

		if (Gdx.input.isTouched()) {
			inputHandlerTouch.update();

		} else {
			inputHandlerTouch.touchRelease();
		}
	}

	public void createCamera() {
		cam = new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
		cam.setToOrtho(false, VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
		cam.position.set((VIEWPORT_WIDTH / 2), (VIEWPORT_HEIGHT / 2), 0);

	}

	public void createMap() {
		// code for loading (tmx) tiledmaps go here
	}

	public void initLevelVariables() {
		// enter all level variables for level start here
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	public static Robot getRobot() {
		return robot;
	}

	public static int getOldmusic() {
		return oldmusic;
	}

	public static void setOldmusic(int oldmusic) {
		GameScreen.oldmusic = oldmusic;
	}

	public static int getMusic() {
		return music;
	}

	public static void setMusic(int music) {
		GameScreen.music = music;
	}

}
