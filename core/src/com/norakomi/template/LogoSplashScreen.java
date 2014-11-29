package com.norakomi.template;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class LogoSplashScreen implements Screen {
	
	NorakomiGame game;
	
	private SpriteBatch sb;
	private float timer = 1;
	private float fadeTime = 135;
	private float alpha;
	private boolean fadein = true;
	private boolean pause = false;
	private boolean switchScreen;
	private Texture logo;
	

	public LogoSplashScreen (NorakomiGame game) {
	this.game = game;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	
		if (switchScreen) {
			game.setScreen(game.menuScreen);
		}
		// sb.enableBlending();
		sb.begin();
		fadeInLogo();
		sb.end();
		
	}
	public void fadeInLogo() {

		if (fadein == true && pause == false) {
			if (timer < fadeTime) {
				timer += 1;
				alpha = timer / fadeTime;
			} else {
				pause = true;
				timer = 0;
			}
		}

		if (pause == true && fadein == true) {
			timer += 1;
			System.out.println(timer);
			if (timer == 120) {
				fadein = false;
				pause = false;
				timer = 0;
			}
		}

		if (fadein == false && pause == false) {
			if (timer < fadeTime) {
				timer += 1;
				alpha = (fadeTime - timer) / fadeTime;
				// SWITCHSCREEN=true;
			} else {
				pause = true;
				timer = 0;

			}
		}

		if (fadein == false && pause == true) {
			System.out.println("pre switch" + timer);
			timer += 1;
			if (timer == 90) {
				switchScreen = true;
			}

		}

		sb.setColor(1, 1, 1, alpha);
		// sb.draw(BLACK, 0, 0, WIDTH, HEIGHT);
		sb.draw(logo, (Gdx.graphics.getWidth() / 2) - 0.5f * logo.getWidth(),
				Gdx.graphics.getHeight() / 2 - 0.5f * logo.getHeight(),
				logo.getWidth(), logo.getHeight());
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		sb = new SpriteBatch();
		// System.out.println("gameoverscreen show() switchscreen set to false");
		switchScreen = false;
		logo = new Texture("img/logoNorakomi.png");
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


	}

}
