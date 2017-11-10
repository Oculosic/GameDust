package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class DustGame extends ApplicationAdapter {
	SpriteBatch player;
	Texture playerI;
	TextureRegion playerSheetPos;
	SpriteBatch dagger;
	Texture daggerI
	
	@Override
	public void create () {
		player = new SpriteBatch();
		playerI = new Texture("CCsprite.png");
		playerSheetPos = new TextureRegion(playerI, 0, 0, 22, 40);
		dagger = new SpriteBatch();
		daggerI = new Texture("THETRUEKNIFE.png");
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		player.begin();
		player.draw(playerSheetPos, screen.getScreenWidth()/2 - 11, screen.getScreenHeight()/2 - 20);
		player.end();
		dagger.begin();
		dagger.draw();
		dagger.end();
	}
	
	@Override
	public void dispose () {
		player.dispose();
		playerI.dispose();
		dagger.dispose();
		daggerI.dispose();
	}
}
