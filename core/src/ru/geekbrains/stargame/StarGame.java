package ru.geekbrains.stargame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class StarGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	Texture background;

	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		background = new Texture("background.png");
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
		batch.draw(img, 0, 0);

		drawBackground();
		drawLogo();

		batch.end();
	}


	private void drawBackground() {
		Color bgColor = batch.getColor();
		batch.setColor(bgColor.r, bgColor.g, bgColor.b, 1f);
		batch.draw(background, 0, 0);
	}

	private void drawLogo() {
		Color imgColor = batch.getColor();
		batch.setColor(imgColor.r, imgColor.g, imgColor.b, 0.2f);
		float imgX = (background.getWidth() - img.getWidth()) / 2.0f;
		float imgY = (background.getHeight() - img.getHeight()) / 2.0f;
		batch.draw(img, imgX, imgY);
	}

	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
		background.dispose();
	}
}