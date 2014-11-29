package com.norakomi.template.helpers;

import java.awt.Image;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.norakomi.template.GameScreen;

import kiloboltgame.framework.Animation;


public class AssetLoader {
	
	public Texture image, currentSprite, background, GooniesWorld1,
	GooniesWorld2, GooniesWorld3, titlescreen, gameover, emptyscreen,
	welldone, bottompartofscreenworld1, bottompartofscreenworld2,
	bottompartofscreenworld3, lifebarempty, lifebarfull, diamond,
	playeronminimap, banditonminimap, dirtycrawleronminimap,
	bulletonminimap, devastandright, devastandleft, devarunright1,
	devarunright2, devarunright3, devarunright4, devarunright5,
	devarunright6, devarunleft1, devarunleft2, devarunleft3,
	devarunleft4, devarunleft5, devarunleft6, devajumpright1,
	devajumpright2, devajumpright3, devajumpleft1, devajumpleft2,
	devajumpleft3, devapunchright, devapunchleft, devajumppunchleft,
	devajumppunchright, devaclimb1, devaclimb2, key, opendoor,
	opendoorwithdiamond, door2keys, door1key, finaldooropen,
	finaldoorclosed, emptysprite;

	public Texture dirtycrawlerleft1, dirtycrawlerleft2,
	dirtycrawlerright1, dirtycrawlerright2, dirtycrawlerspawn1,
	dirtycrawlerspawn2, dirtycrawlerdied, banditClimb1, banditClimb2,
	banditDown1, banditDown2, banditLeftRun1, banditLeftRun2,
	banditLeftRun3, banditLeftRun4, banditLeftRun5, banditLeftRun6,
	banditLeftShoot, banditLeftStand, banditRightRun1, banditRightRun2,
	banditRightRun3, banditRightRun4, banditRightRun5, banditRightRun6,
	banditRightShoot, banditRightStand, bullet, drop1, drop2, drop3,
	drop4, tilegrassTop, tilegrassBot, tilegrassLeft, tilegrassRight,
	tiledirt, singlealtarempty, singlealtar1key, doublealtarempty,
	doublealtar1key, doublealtar2keys, logo;
	
	public static Animation devarunrightAnimation, devarunleftAnimation,
	MikeyClimbAnimation, banditRunLeftAnimation,
	banditRunRightAnimation, banditClimbAnimation, banditDownAnimation,
	dirtycrawlerSpawnAnimation, dirtycrawlerLeftAnimation,
	dirtycrawlerRightAnimation;
	
	public Sound Sfx1, Sfx2,Sfx3,Sfx4,Sfx5,Sfx6,Sfx7,Sfx8,Sfx9,Sfx10;
	public Music music1, music2, music3, music4, music5, music6,music7;

	public void loadTextures() {
		// TS DIRECTORY TEXTURES
		
		background = new Texture ("ts/GooniesWorld1.png");
		
		// GENEREAL TEXTURES
		
		logo = new Texture("img/logoNorakomi.png");
		
				//emptysprite = new Texture("img/data/emptysprite.gif");

		// DEVA TEXTURES
				devaclimb1 = new Texture( "img/devasprite/devaclimb1.gif");
				devaclimb2 = new Texture("img/devasprite/devaclimb2.gif");
				devastandright = new Texture( "img/devasprite/devastandright.gif");
				devastandleft = new Texture("img/devasprite/devastandleft.gif");
				devarunright1 = new Texture("img/devasprite/devarunright1.gif");
				devarunright2 = new Texture("img/devasprite/devarunright2.gif");
				devarunright3 = new Texture("img/devasprite/devarunright3.gif");
				devarunright4 = new Texture("img/devasprite/devarunright4.gif");
				devarunright5 = new Texture("img/devasprite/devarunright5.gif");
				devarunright6 = new Texture("img/devasprite/devarunright6.gif");
				devarunleft1 = new Texture("img/devasprite/devarunleft1.gif");
				devarunleft2 = new Texture("img/devasprite/devarunleft2.gif");
				devarunleft3 = new Texture("img/devasprite/devarunleft3.gif");
				devarunleft4 =new Texture("img/devasprite/devarunleft4.gif");
				devarunleft5 = new Texture("img/devasprite/devarunleft5.gif");
				devarunleft6 = new Texture("img/devasprite/devarunleft6.gif");
				devajumpright1 = new Texture("img/devasprite/devajumpright1.gif");
				devajumpright2 = new Texture("img/devasprite/devajumpright2.gif");
				devajumpright3 = new Texture("img/devasprite/devajumpright3.gif");
				devajumpleft1 = new Texture("img/devasprite/devajumpleft1.gif");
				devajumpleft2 = new Texture("img/devasprite/devajumpleft2.gif");
				devajumpleft3 = new Texture("img/devasprite/devajumpleft3.gif");
				devapunchright = new Texture("img/devasprite/devapunchright.gif");
				devapunchleft = new Texture("img/devasprite/devapunchleft.gif");
				devajumppunchright = new Texture("img/devasprite/devajumppunchright.gif");
				devajumppunchleft = new Texture("img/devasprite/devajumppunchleft.gif");

				
				// ENEMY SPRITE TEXTURES
				dirtycrawlerspawn1 = new Texture("img/enemysprites/dirtycrawlerspawn1.gif");
				dirtycrawlerspawn2 = new Texture("img/enemysprites/dirtycrawlerspawn2.gif");
				dirtycrawlerleft1 = new Texture("img/enemysprites/dirtycrawlerleft1.gif");
				dirtycrawlerleft2 = new Texture("img/enemysprites/dirtycrawlerleft2.gif");
				dirtycrawlerright1 = new Texture("img/enemysprites/dirtycrawlerright1.gif");
				dirtycrawlerright2 = new Texture("img/enemysprites/dirtycrawlerright2.gif");
				dirtycrawlerdied = new Texture("img/enemysprites/dirtycrawlerdied.gif");

				banditClimb1 = new Texture("img/enemysprites/banditClimb1.gif");
				banditClimb2 = new Texture("img/enemysprites/banditClimb2.gif");
				banditDown1 = new Texture("img/enemysprites/banditDown1.gif");
				banditDown2 =new Texture("img/enemysprites/banditDown2.gif");
				banditLeftRun1 = new Texture("img/enemysprites/banditLeftRun1.gif");
				banditLeftRun2 = new Texture("img/enemysprites/banditLeftRun2.gif");
				banditLeftRun3 = new Texture("img/enemysprites/banditLeftRun3.gif");
				banditLeftRun4 = new Texture("img/enemysprites/banditLeftRun4.gif");
				banditLeftRun5 = new Texture("img/enemysprites/banditLeftRun5.gif");
				banditLeftRun6 = new Texture("img/enemysprites/banditLeftRun6.gif");
				banditLeftShoot = new Texture("img/enemysprites/banditLeftShoot.gif");
				banditLeftStand = new Texture("img/enemysprites/banditLeftStand.gif");
				banditRightRun1 = new Texture("img/enemysprites/banditRightRun1.gif");
				banditRightRun2 = new Texture("img/enemysprites/banditRightRun2.gif");
				banditRightRun3 = new Texture("img/enemysprites/banditRightRun3.gif");
				banditRightRun4 = new Texture("img/enemysprites/banditRightRun4.gif");
				banditRightRun5 = new Texture("img/enemysprites/banditRightRun5.gif");
				banditRightRun6 = new Texture("img/enemysprites/banditRightRun6.gif");
				banditRightShoot = new Texture("img/enemysprites/banditRightShoot.gif");
				banditRightStand = new Texture("img/enemysprites/banditRightStand.gif");
								
				drop1 = new Texture("img/enemysprites/drop1.gif");
				drop2 = new Texture("img/enemysprites/drop2.gif");
				drop3 = new Texture("img/enemysprites/drop3.gif");
				drop4 = new Texture("img/enemysprites/drop4.gif");
				
				
				
				// DATA DIRECTORY TEXTURES
				bottompartofscreenworld1 = new Texture("img/data/bottompartofscreenworld1.jpg");
				bottompartofscreenworld2 = new Texture("img/data/bottompartofscreenworld2.jpg");
				bottompartofscreenworld3 = new Texture("img/data/bottompartofscreenworld3.jpg");
				lifebarempty = new Texture("img/data/lifebarempty.jpg");
				lifebarfull =new Texture("img/data/lifebarfull.jpg");
				diamond = new Texture("img/data/diamond.jpg");
				playeronminimap = new Texture("img/data/playeronminimap.jpg");
				banditonminimap = new Texture("img/data/banditonminimap.jpg");
				dirtycrawleronminimap = new Texture("img/enemysprites/dirtycrawleronminimap.jpg");
				bulletonminimap = new Texture("img/data/bulletonminimap.jpg");

				titlescreen = new Texture("img/data/Deva_titlescreen800x480.png");
				gameover = new Texture("img/data/gameover.png");
				emptyscreen = new Texture("img/data/emptyscreen.png");
				welldone = new Texture("img/data/welldone.png");

				tiledirt = new Texture("img/data/tiledirt.png");
				tilegrassTop = new Texture("img/data/tilegrasstop.png");
				tilegrassBot = new Texture("img/data/tilegrassbot.png");
				tilegrassLeft = new Texture("img/data/tilegrassleft.png");
				tilegrassRight = new Texture("img/data/tilegrassright.png");

				key = new Texture("img/data/key.gif");
				door2keys = new Texture("img/data/door2keys.gif");
				door1key = new Texture("img/data/door1key.gif");
				opendoorwithdiamond = new Texture("img/data/opendoorwithdiamond.gif");
				opendoor = new Texture("img/data/opendoor.gif");

				singlealtarempty = new Texture("img/data/singlealtarempty.gif");
				singlealtar1key = new Texture("img/data/singlealtar1key.gif");
				doublealtarempty = new Texture("img/data/doublealtarempty.gif");
				doublealtar1key = new Texture("img/data/doublealtar1key.gif");
				doublealtar2keys = new Texture("img/data/doublealtar2keys.gif");

				finaldooropen = new Texture("img/data/finaldooropen.gif");
				finaldoorclosed = new Texture("img/data/finaldoorclosed.gif");
				
				
				
				
				
				bullet = new Texture("img/data/bullet.gif");
	}

	public void loadSfx() {
		// Sound EFFECTS
		 Sfx1 = Gdx.audio.newSound(Gdx.files.internal("sound/devapunch.wav"));
		 Sfx2 = Gdx.audio.newSound(Gdx.files.internal("sound/monsterdies.wav"));
		 Sfx3 = Gdx.audio.newSound(Gdx.files.internal("sound/teleport.wav"));
		 Sfx4 = Gdx.audio.newSound(Gdx.files.internal("sound/key.wav"));
		 Sfx5 = Gdx.audio.newSound(Gdx.files.internal("sound/devahurt.wav"));
		 Sfx6 = Gdx.audio.newSound(Gdx.files.internal("sound/onekeyindoor.wav"));
		 Sfx7 = Gdx.audio.newSound(Gdx.files.internal("sound/dooropens.wav"));
		 Sfx8 = Gdx.audio.newSound(Gdx.files.internal("sound/banditshoots.wav"));
		 Sfx9 = Gdx.audio.newSound(Gdx.files.internal("sound/bandithit.wav"));
		 Sfx10 = Gdx.audio.newSound(Gdx.files.internal("sound/diamondtaken.wav"));
		}
	
	public void playsfx(int sfxnumber){
		if (sfxnumber == 1)	Sfx1.play();
		if (sfxnumber == 2)	Sfx2.play();
		if (sfxnumber == 3)	Sfx3.play();
		if (sfxnumber == 4)	Sfx4.play();
		if (sfxnumber == 5)	Sfx5.play();
		if (sfxnumber == 6)	Sfx6.play();
		if (sfxnumber == 7)	Sfx7.play();
		if (sfxnumber == 8)	Sfx8.play();
		if (sfxnumber == 9) Sfx9.play();
		if (sfxnumber == 10) Sfx10.play();
		sfxnumber = 0;
	}
	
	public void loadMusic(){
		music1 =  Gdx.audio.newMusic(Gdx.files.internal("sound/level1.wav"));
		music1.setLooping(true);
		music2 =  Gdx.audio.newMusic(Gdx.files.internal("sound/level2.wav"));
		music2.setLooping(true);
		music3 =  Gdx.audio.newMusic(Gdx.files.internal("sound/level3.wav"));
		music3.setLooping(true);
		music4 =  Gdx.audio.newMusic(Gdx.files.internal("sound/titlescreen.wav"));
		music4.setLooping(true);
		music5 =  Gdx.audio.newMusic(Gdx.files.internal("sound/gameover.wav"));
		music5.setLooping(false);
		music6 =  Gdx.audio.newMusic(Gdx.files.internal("sound/endlevel.wav"));
		music6.setLooping(false);
		music7 =  Gdx.audio.newMusic(Gdx.files.internal("sound/startgame.wav"));
		music7.setLooping(false);
		
	}
	
	public void playMusic(){
		// first turn off old music
				if (GameScreen.getOldmusic() == 1) music1.stop();
				if (GameScreen.getOldmusic() == 2) music2.stop();
				if (GameScreen.getOldmusic() == 3) music3.stop();
				if (GameScreen.getOldmusic() == 4) music4.stop();

				GameScreen.setMusic(GameScreen.getOldmusic());

				// then play new music
				// switch werkt min of meer zoals een if statement
				switch (GameScreen.getMusic()){
				case 1:
					music1.play();
				case 2:
					music2.play();
				case 3:
					music3.play();
				case 4:
					music4.play();
				case 5:
					music5.play();
				case 6:
					music6.play();
				case 7:
					music7.play();
				default:
					break;
					
				}
					
	}

	public void loadAnimations() {
		banditRunLeftAnimation = new Animation();
		banditRunLeftAnimation.addFrame(banditLeftRun1, 7);
		banditRunLeftAnimation.addFrame(banditLeftRun2, 7);
		banditRunLeftAnimation.addFrame(banditLeftRun3, 7);
		banditRunLeftAnimation.addFrame(banditLeftRun4, 7);
		banditRunLeftAnimation.addFrame(banditLeftRun5, 7);
		banditRunLeftAnimation.addFrame(banditLeftRun6, 7);

		banditRunRightAnimation = new Animation();
		banditRunRightAnimation.addFrame(banditRightRun1, 7);
		banditRunRightAnimation.addFrame(banditRightRun2, 7);
		banditRunRightAnimation.addFrame(banditRightRun3, 7);
		banditRunRightAnimation.addFrame(banditRightRun4, 7);
		banditRunRightAnimation.addFrame(banditRightRun5, 7);
		banditRunRightAnimation.addFrame(banditRightRun6, 7);

		banditClimbAnimation = new Animation();
		banditClimbAnimation.addFrame(banditClimb1, 12);
		banditClimbAnimation.addFrame(banditClimb2, 12);

		banditDownAnimation = new Animation();
		banditDownAnimation.addFrame(banditDown1, 9);
		banditDownAnimation.addFrame(banditDown2, 9);

		dirtycrawlerSpawnAnimation = new Animation();
		dirtycrawlerSpawnAnimation.addFrame(dirtycrawlerspawn1, 10);
		dirtycrawlerSpawnAnimation.addFrame(dirtycrawlerspawn2, 10);

		dirtycrawlerLeftAnimation = new Animation();
		dirtycrawlerLeftAnimation.addFrame(dirtycrawlerleft1, 10);
		dirtycrawlerLeftAnimation.addFrame(dirtycrawlerleft2, 10);

		dirtycrawlerRightAnimation = new Animation();
		dirtycrawlerRightAnimation.addFrame(dirtycrawlerright1, 10);
		dirtycrawlerRightAnimation.addFrame(dirtycrawlerright2, 10);
		
		devarunrightAnimation = new Animation();
		devarunrightAnimation.addFrame(devarunright1, 10);
		devarunrightAnimation.addFrame(devarunright2, 10);
		devarunrightAnimation.addFrame(devarunright3, 10);
		devarunrightAnimation.addFrame(devarunright4, 10);
		devarunrightAnimation.addFrame(devarunright5, 10);
		devarunrightAnimation.addFrame(devarunright6, 10);

		devarunleftAnimation = new Animation();
		devarunleftAnimation.addFrame(devarunleft1, 10);
		devarunleftAnimation.addFrame(devarunleft2, 10);
		devarunleftAnimation.addFrame(devarunleft3, 10);
		devarunleftAnimation.addFrame(devarunleft4, 10);
		devarunleftAnimation.addFrame(devarunleft5, 10);
		devarunleftAnimation.addFrame(devarunleft6, 10);

		MikeyClimbAnimation = new Animation();
		MikeyClimbAnimation.addFrame(devaclimb1, 10);
		MikeyClimbAnimation.addFrame(devaclimb2, 10);

		
	}

}
