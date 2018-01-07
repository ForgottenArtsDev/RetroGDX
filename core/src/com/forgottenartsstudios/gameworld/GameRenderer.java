package com.forgottenartsstudios.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.forgottenartsstudios.data.RenderAndroid;
import com.forgottenartsstudios.data.RenderDesktop;
import com.forgottenartsstudios.helpers.Variables;

/**
 * Created by forgo on 10/6/2017.
 */

public class GameRenderer {
    private GameWorld myWorld;
    public static OrthographicCamera cam, screenCam;
    public static StretchViewport viewport;

    public static SpriteBatch batcher, screenBatcher;
    public static Rectangle Offset;

    private int midPointY;
    private int gameHeight;

    public GameRenderer(GameWorld world, int gameHeight, int midPointY) {
        myWorld = world;

        // The word "this" refers to this instance.
        // We are setting the instance variables' values to be that of the
        // parameters passed in from GameScreen.
        this.gameHeight = gameHeight;
        this.midPointY = midPointY;

        cam = new OrthographicCamera();

        if (Variables.Client_Mode == Variables.Client_Mode_Android) {
            cam.setToOrtho(true, Variables.AndroidScreenWidth, Variables.AndroidScreenHeight);

            screenCam = new OrthographicCamera();
            screenCam.setToOrtho(true, Variables.AndroidScreenWidth, Variables.AndroidScreenHeight);

            viewport = new StretchViewport(Variables.AndroidScreenWidth, Variables.AndroidScreenHeight, cam);
        } else if (Variables.Client_Mode == Variables.Client_Mode_Desktop) {
            cam.setToOrtho(true, Variables.DesktopScreenWidth, Variables.DesktopScreenHeight);

            screenCam = new OrthographicCamera();
            screenCam.setToOrtho(true, Variables.DesktopScreenWidth, Variables.DesktopScreenHeight);

            viewport = new StretchViewport(Variables.DesktopScreenWidth, Variables.DesktopScreenHeight, cam);
        }

        viewport.apply();

        batcher = new SpriteBatch();
        batcher.setProjectionMatrix(cam.combined);

        //screenBatcher = new SpriteBatch();
        //screenBatcher.setProjectionMatrix(screenCam.combined);

        cam.position.set(cam.viewportWidth / 2, cam.viewportHeight / 2, 0);
        //screenCam.position.set(screenCam.viewportWidth / 2, screenCam.viewportHeight / 2, 0);
    }
    public void render(float runTime) {
        // We will move these outside of the loop for performance later.
        //Player player = myWorld.getPlayer()

        // Fill the entire screen with black, to prevent potential flickering.
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batcher.setProjectionMatrix(cam.combined);

        // Begin SpriteBatch
        batcher.begin();

        long tickCount = System.currentTimeMillis();
        Variables.tickCount = tickCount;

        // Render
        switch (Variables.Game_State) {
            case Variables.Game_State_TitleScreen:
                if (Variables.Client_Mode == Variables.Client_Mode_Android) {
                    RenderAndroid.gsTitleScreen(tickCount);
                } else if (Variables.Client_Mode == Variables.Client_Mode_Desktop) {
                    RenderDesktop.gsTitleScreen(tickCount);
                }
                break;
            case Variables.Game_State_Login:
                if (Variables.Client_Mode == Variables.Client_Mode_Android) {
                    RenderAndroid.gsLogin(tickCount);
                } else if (Variables.Client_Mode == Variables.Client_Mode_Desktop) {
                    RenderDesktop.gsLogin(tickCount);
                }
                break;
            case Variables.Game_State_CharSelect:
                if (Variables.Client_Mode == Variables.Client_Mode_Android) {
                    RenderAndroid.gsCharSelect(tickCount);
                } else if (Variables.Client_Mode == Variables.Client_Mode_Desktop) {
                    RenderDesktop.gsCharSelect(tickCount);
                }
                break;
            case Variables.Game_State_CharCreate:
                if (Variables.Client_Mode == Variables.Client_Mode_Android) {
                    RenderAndroid.gsCharCreate(tickCount);
                } else if (Variables.Client_Mode == Variables.Client_Mode_Desktop) {
                    RenderDesktop.gsCharCreate(tickCount);
                }
                break;
            case Variables.Game_State_Loading:
                if (Variables.Client_Mode == Variables.Client_Mode_Android) {
                    RenderAndroid.gsLoading(tickCount);
                } else if (Variables.Client_Mode == Variables.Client_Mode_Desktop) {
                    RenderDesktop.gsLoading(tickCount);
                }
                break;
            case Variables.Game_State_InGame:
                if (Variables.Client_Mode == Variables.Client_Mode_Android) {
                    RenderAndroid.gsInGame(tickCount);
                } else if (Variables.Client_Mode == Variables.Client_Mode_Desktop) {
                    RenderDesktop.gsInGame(tickCount);
                }
                break;
        }

        batcher.end();
    }
}
