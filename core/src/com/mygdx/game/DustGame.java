package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class DustGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture playerI;
	TextureRegion playerSheetPos;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		playerI = new Texture("CCsprite.png");
		playerSheetPos = new TextureRegion(playerI, 0, 0, 22, 40);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(playerSheetPos, screen.getScreenWidth()/2 - 11, screen.getScreenHeight()/2 - 20);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		playerI.dispose();
	}
}
