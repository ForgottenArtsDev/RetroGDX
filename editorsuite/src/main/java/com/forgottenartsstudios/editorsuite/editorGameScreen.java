package com.forgottenartsstudios.editorsuite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

/**
 * Created by Perfekt on 8/31/2016.
 */
public class editorGameScreen implements Screen {

    private editorWorld world;
    private editorRenderer renderer;
    private float runTime;

    // This is the constructor, not the class declaration
    public editorGameScreen() {

        float screenWidth = 1280; //Gdx.graphics.getWidth();
        float screenHeight = 720; //Gdx.graphics.getHeight();
        float gameWidth = 1280;
        float gameHeight = screenHeight / (screenWidth / gameWidth);

        int midPointY = (int) (gameHeight / 2);

        world = new editorWorld(midPointY);
        renderer = new editorRenderer(world, (int) gameHeight, midPointY);

        Gdx.input.setInputProcessor(new editorInputHandler(world));

    }

    @Override
    public void render(float delta) {
        runTime += delta;
        world.update(delta);
        renderer.render(runTime);
    }

    @Override
    public void resize(int width, int height) {
        editorRenderer.viewport.update(width, height);
        editorRenderer.cam.position.set(editorRenderer.cam.viewportWidth / 2,editorRenderer.cam.viewportHeight / 2, 0);
    }

    @Override
    public void show() {
        Gdx.app.log("GameScreen", "show called");
    }

    @Override
    public void hide() {
        Gdx.app.log("GameScreen", "hide called");
    }

    @Override
    public void pause() {
        Gdx.app.log("GameScreen", "pause called");
    }

    @Override
    public void resume() {
        Gdx.app.log("GameScreen", "resume called");
    }

    @Override
    public void dispose() {
        // Leave blank
    }
}
