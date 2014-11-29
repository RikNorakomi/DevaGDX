package com.norakomi.template;

import static com.norakomi.template.helpers.Variables.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class MenuScreen implements Screen {

	NorakomiGame game;

	private SpriteBatch sb;

	private BitmapFont font_white;

	private TextureRegion title;

	private Music music;

	public MenuScreen(NorakomiGame game) {
		this.game = game;
	}

	@Override
	public void show() {
		sb = new SpriteBatch();

		// Set Games "Reset" values here
		SCROLLTRACKER_X = 0;
		LEVEL = 1;

		// load title image
		title = new TextureRegion(
				new Texture("img/Deva_titlescreen800x480.png"));

		// load fonts
		font_white = new BitmapFont(
				Gdx.files.internal("fonts/8bitfontviurs24.fnt"), false);

		// play Title Music
		music = Gdx.audio.newMusic(Gdx.files.internal("sound/titlescreen.wav"));
		music.setLooping(true);
		music.play();

	}

	@Override
	public void render(float delta) {
		// clear pixels every frame
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// draw title screen
		sb.begin();
		sb.draw(title, 0, 0, V_WIDTH, V_HEIGHT);
		font_white.draw(sb, "TOUCH SCREEN", Gdx.graphics.getWidth() / 2,
				V_HEIGHT / 4);
		sb.end();

		
		// switch screen on touch
		if (Gdx.input.isTouched()) {
			System.out.println("screen touched");
			music.stop();
			game.setScreen(game.gameScreen);
		}
	}

	@Override
	public void resize(int width, int height) {

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

}
