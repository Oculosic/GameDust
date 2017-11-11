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
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Iterator;

public class DustGame extends ApplicationAdapter {
	SpriteBatch player;
	Texture playerI;
	TextureRegion playerSheetPos;
	Texture monsterTexture;
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
	public float playerXpos;
	public float playerYpos;
	int playerXTextOffset;
	int playerYTextOffset;
	Array<Rectangle> monsters;
	long lastSpawnTime;



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
		monsterTexture = new Texture("monster.png");
		player = new SpriteBatch();
		playerI = new Texture("CCsprite.png");
		playerSheetPos = new TextureRegion(playerI, 66, 0, 22, 38);
		dagger = new SpriteBatch();
		daggerI = new Texture("THETRUEKNIFE.png");
		monsters = new Array<Rectangle>();
		spawnAttempt();
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
		playerSheetPos.setRegion(playerXTextOffset, playerYTextOffset, 22, 38);
		playerXpos = (cameraXPos + cameraYPos + screen.getScreenWidth())/2 -11;
		playerYpos = (cameraYPos + cameraYPos + screen.getScreenHeight())/2 - 19;
		world.setProjectionMatrix(cam.combined);
		spawnAttempt();
		Iterator<Rectangle> iter = monsters.iterator();
		while(iter.hasNext()){
			Rectangle monster = iter.next();
			monster.x += playerXpos/6;
			monster.y += playerYpos/6;
		}
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		world.begin();
		for(Rectangle monster: monsters){
			world.draw(monsterTexture, monster.x, monster.y);
		}
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

	void spawnAttempt(){
		if(TimeUtils.millis() - lastSpawnTime > 1000000000){
			boolean isSpawnXValid = false;
			boolean isSpawnYValid = false;
			float spawnX = MathUtils.random(playerXpos - 50, playerXpos + 50);
			float spawnY = MathUtils.random(playerYpos - 50, playerYpos + 50);
			while(isSpawnXValid == false && isSpawnYValid == false){
				if(spawnX >= (playerXpos - 30) && (spawnX <= (playerXpos + 30))){
					spawnX = MathUtils.random(playerXpos - 50, playerXpos + 50);
				}
				else{
					isSpawnXValid = true;
				}
				if(spawnY >= (playerYpos - 30) && (spawnY <= (playerYpos + 30))){
					spawnY = MathUtils.random(playerYpos - 50, playerYpos + 50);
				}
				else{
					isSpawnYValid = true;
				}
			}
			spawnMonster(spawnX, spawnY);
		}
	}

	void spawnMonster(float monsterSpawnX, float monsterSpawnY){
		Rectangle monster = new Rectangle();
		monster.x = monsterSpawnX;
		monster.y = monsterSpawnY;
		monster.height = 16;
		monster.width = 9;
		monsters.add(monster);
		lastSpawnTime = TimeUtils.millis();
	}

	@Override
	public void dispose () {
		player.dispose();
		playerI.dispose();
		dagger.dispose();
		daggerI.dispose();
	}
}
