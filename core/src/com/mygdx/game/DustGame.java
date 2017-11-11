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
	static final int WORLD_WIDTH = 1000;
	static final int WORLD_HEIGHT = 1000;
	OrthographicCamera cam;
	SpriteBatch world;
	Sprite mapSprite;
	int cameraXPos;
	int cameraYPos;
	float w;
	float h;
	float playerXpos;
	float playerYpos;
	int playerXTextOffset;
	int playerYTextOffset;



	@Override
	public void create () {
		mapSprite = new Sprite(new Texture(Gdx.files.internal("REALGROUND.png")));
		mapSprite.setPosition(-500, -500);
		mapSprite.setSize(WORLD_WIDTH, WORLD_HEIGHT);
		w = Gdx.graphics.getWidth();
		h = Gdx.graphics.getHeight();

		cam = new OrthographicCamera(30, 30 * (h / w));
		cam.update();
		world = new SpriteBatch();
		player = new SpriteBatch();
		playerI = new Texture("CCsprite.png");
		playerSheetPos = new TextureRegion(playerI, 66, 0, 22, 38);
		dagger = new SpriteBatch();
		daggerI = new Texture("THETRUEKNIFE.png");
	}

	@Override
	public void resize(int width, int height){
		cam.viewportWidth = 30f;
		cam.viewportHeight = 30f * height/width;
		cam.update();
	}

	@Override
	public void render () {
		handleInput();
		cam.update();
		System.out.println(playerXTextOffset);
		playerSheetPos.setRegion(playerXTextOffset, 0, 22, 38);
		playerXpos = (cameraXPos + cameraYPos + screen.getScreenWidth())/2 -11;
		playerYpos = (cameraYPos + cameraYPos + screen.getScreenHeight())/2 - 19;
		world.setProjectionMatrix(cam.combined);
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		world.begin();
		mapSprite.draw(world);
		world.end();
		player.begin();
		player.draw(playerSheetPos, playerXpos, playerYpos);
		player.end();
	}

	void handleInput(){
		if(Gdx.input.isKeyPressed(Input.Keys.W)||Gdx.input.isKeyPressed(Input.Keys.UP)){
			cameraYPos += 0.3;
			cam.translate( 0, (float) 0.3, 0);
			playerXTextOffset = 44;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.S)||Gdx.input.isKeyPressed(Input.Keys.DOWN)){
			cameraXPos -= 0.3;
			cam.translate(0, (float) -0.3, 0);
			playerXTextOffset = 66;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.D)||Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
			cameraXPos += 0.3;
			cam.translate((float) 0.3, 0, 0);
			playerXTextOffset = 22;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.A)||Gdx.input.isKeyPressed(Input.Keys.LEFT)){
			cameraXPos -= 0.3;
			cam.translate((float) -0.3, 0, 0);
			playerXTextOffset = 0;
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
