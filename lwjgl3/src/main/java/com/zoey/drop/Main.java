package com.zoey.drop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

import java.util.Random;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class Main implements ApplicationListener{
    /*
     * Main class for application 
     */

    // Textures
    Texture backgroundTexture;
    Texture bidoofTexture; 
    Texture dropTexture;

    // Sprites
    Sprite bidoofSprite;
    
    // Rendering
    SpriteBatch spriteBatch;
    FitViewport viewport;

    // Random Number Generators;
    Random rand;

    // Sound + Music
    Sound dropSound;
    Music music;

    @Override
    public void create() {
        /* 
        * Instantiate all previously made objects. 
        */

        backgroundTexture = new Texture("Mondstadt.png"); 
        bidoofTexture = new Texture("bidoof.png");
        dropTexture = new Texture("diamondOre.png");
        dropSound = Gdx.audio.newSound(Gdx.files.internal("tacoBell.mp3")); 
        music = Gdx.audio.newMusic(Gdx.files.internal("echoes.mp3")); 

        bidoofSprite = new Sprite(bidoofTexture);
        bidoofSprite.setSize(1, 1);

        spriteBatch = new SpriteBatch();
        viewport = new FitViewport(8, 5);

        rand = new Random();

        playMusic();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true); 
    }

    @Override
    public void render() {
        /* 
        * Handles new inputs, logic and redrawing of textures every new frame. 
        */
        input();
        logic();
        draw();
    }

    @Override
    public void pause() {} 

    @Override
    public void resume() {}

    @Override
    public void dispose() {}

    private void draw() {
        /*
        * Clears screen then redraws graphics. 
        */
        ScreenUtils.clear(Color.BLACK);
        viewport.apply();
        spriteBatch.setProjectionMatrix(viewport.getCamera().combined);

        // Determine world size
        float worldWidth = viewport.getWorldWidth();
        float worldHeight = viewport.getWorldHeight();

        // Draw Bidoof 
        spriteBatch.begin();
        spriteBatch.draw(backgroundTexture, 0, 0, worldWidth, worldHeight);
        bidoofSprite.draw(spriteBatch);
        spriteBatch.end();
    }

    private void input() {
        /*
         * Handles keyboard input. 
         */

        float speed = 1.5f;
        float delta = Gdx.graphics.getDeltaTime();

        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            bidoofSprite.translateX(speed * delta);
        } else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            bidoofSprite.translateX(-speed * delta);
        } else if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            bidoofSprite.translateY(speed * delta);
        } else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            bidoofSprite.translateY(-speed * delta);
        }

    }

    private void logic() {}

    private void playMusic() {
        music.setLooping(true);
        music.setVolume(.8f);
        music.play();
    }
}