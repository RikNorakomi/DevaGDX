package com.norakomi.template;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import static com.norakomi.template.helpers.Variables.*;





public class NorakomiGame extends Game {
	
	MenuScreen menuScreen;
	LogoSplashScreen logoSplashScreen;
	GameScreen gameScreen;
	

	@Override
	public void create() {
		menuScreen = new MenuScreen(this);
		logoSplashScreen = new LogoSplashScreen(this);	
		gameScreen = new GameScreen(this);
		
		
		
		switch (Gdx.app.getType()) {
	    case Android:
	    	V_WIDTH = Gdx.graphics.getWidth();
	    	V_HEIGHT = Gdx.graphics.getHeight();
	    	setScreen(logoSplashScreen);
	        break;
	    case Desktop:
	    	setScreen(gameScreen);
	        break;
	     default:
	    	 setScreen(gameScreen);
	}
		
		
	}
	
	

}
