package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class DustGame extends ApplicationAdapter {
	SpriteBatch player;
	Texture playerI;
	TextureRegion playerSheetPos;
	SpriteBatch dagger;
	Texture daggerI;
	static final int WORLD_WIDTH = 100;
	static final int WORLD_HEIGHT = 100;
	OrthographicCamera cam;
	SpriteBatch world;
	Sprite mapSprite;
	float rotationSpeed;
	
	@Override
	public void create () {
		mapSprite = new Sprite(new Texture(Gdx.files.internal("REALGROUND.png")));
		mapSprite.setPosition(0, 0);
		mapSprite.setSize(WORLD_WIDTH, WORLD_HEIGHT);
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		cam = new OrthographicCamera(30, 30 * (h / w));
		cam.update();
		world = new SpriteBatch();
		player = new SpriteBatch();
		playerI = new Texture("CCsprite.png");
		playerSheetPos = new TextureRegion(playerI, 0, 0, 22, 40);
		dagger = new SpriteBatch();
		daggerI = new Texture("THETRUEKNIFE.png");
	}

	@Override
	public void render () {
		handleInput();
		cam.update();
		world.setProjectionMatrix(cam.combined);
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		world.begin();
		mapSprite.draw(world);
		world.end();
		player.begin();
		player.draw(playerSheetPos, screen.getScreenWidth()/2 - 11, screen.getScreenHeight()/2 - 20);
		player.end();
	}

	void handleInput(){
		if(Gdx.input.isKeyPressed(Input.Keys.D)){
			cam.translate((float) 0.3, 0, 0);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.A)){
			cam.translate((float) -0.3, 0, 0);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.W)){
			cam.translate( 0, (float) 0.3, 0);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.S)){
			cam.translate(0, (float) -0.3, 0);
		}
	}
	
	@Override
	public void dispose () {
		player.dispose();
		playerI.dispose();
		dagger.dispose();
		daggerI.dispose();
	}
}
