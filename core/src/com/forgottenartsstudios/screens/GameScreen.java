package com.forgottenartsstudios.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.input.GestureDetector;
import com.forgottenartsstudios.gameworld.GameRenderer;
import com.forgottenartsstudios.gameworld.GameWorld;
import com.forgottenartsstudios.helpers.AndroidInputHandler;
import com.forgottenartsstudios.helpers.DesktopInputHandler;
import com.forgottenartsstudios.helpers.Variables;

/**
 * Created by forgo on 10/6/2017.
 */

public class GameScreen implements Screen {
    private GameWorld world;
    private GameRenderer renderer;
    private float runTime;

    public GameScreen() {

        float screenWidth = 0, screenHeight = 0, gameWidth = 0;
        if (Variables.Client_Mode == Variables.Client_Mode_Android) {
            screenWidth = Variables.AndroidScreenWidth; //Gdx.graphics.getWidth();
            screenHeight = Variables.AndroidScreenHeight; //Gdx.graphics.getHeight();
            gameWidth = Variables.AndroidScreenWidth;
        } else if (Variables.Client_Mode == Variables.Client_Mode_Desktop) {
            screenWidth = Variables.DesktopScreenWidth; //Gdx.graphics.getWidth();
            screenHeight = Variables.DesktopScreenHeight; //Gdx.graphics.getHeight();
            gameWidth = Variables.DesktopScreenWidth;
        }
        float gameHeight = screenHeight / (screenWidth / gameWidth);

        int midPointY = (int) (gameHeight / 2);

        world = new GameWorld(midPointY);
        renderer = new GameRenderer(world, (int) gameHeight, midPointY);

        if (Variables.Client_Mode == Variables.Client_Mode_Android) {
            InputMultiplexer im = new InputMultiplexer();
            GestureDetector gd = new GestureDetector(new AndroidInputHandler(world));
            InputProcessor ip = new AndroidInputHandler(world);
            gd.setTapCountInterval(1f);
            im.addProcessor(gd);
            im.addProcessor(ip);
            Gdx.input.setInputProcessor(im);
        } else if (Variables.Client_Mode == Variables.Client_Mode_Desktop) {
            Gdx.input.setInputProcessor(new DesktopInputHandler(world));
        }

        //Gdx.input.setInputProcessor(new InputHandler(world));
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
