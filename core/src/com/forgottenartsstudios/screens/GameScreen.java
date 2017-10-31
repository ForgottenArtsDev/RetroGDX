package com.forgottenartsstudios.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.forgottenartsstudios.gameworld.GameRenderer;
import com.forgottenartsstudios.gameworld.GameWorld;
import com.forgottenartsstudios.helpers.InputHandler;
import com.forgottenartsstudios.helpers.Variables;

/**
 * Created by forgo on 10/6/2017.
 */

public class GameScreen implements Screen {
    private GameWorld world;
    private GameRenderer renderer;
    private float runTime;

    public GameScreen() {

        float screenWidth = Variables.ScreenWidth; //Gdx.graphics.getWidth();
        float screenHeight = Variables.ScreenHeight; //Gdx.graphics.getHeight();
        float gameWidth = Variables.ScreenWidth;
        float gameHeight = screenHeight / (screenWidth / gameWidth);

        int midPointY = (int) (gameHeight / 2);

        world = new GameWorld(midPointY);
        renderer = new GameRenderer(world, (int) gameHeight, midPointY);

        Gdx.input.setInputProcessor(new InputHandler(world));
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        runTime += delta;
        world.update(delta);
        renderer.render(runTime);
    }

    @Override
    public void resize(int width, int height) {
        GameRenderer.viewport.update(width, height);
        GameRenderer.cam.position.set(GameRenderer.cam.viewportWidth / 2, GameRenderer.cam.viewportHeight / 2, 0);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
