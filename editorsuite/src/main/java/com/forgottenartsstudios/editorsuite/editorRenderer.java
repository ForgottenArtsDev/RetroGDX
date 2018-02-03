package com.forgottenartsstudios.editorsuite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.StretchViewport;

/**
 * Created by Perfekt on 8/31/2016.
 */
public class editorRenderer {

    private editorWorld myWorld;
    public static OrthographicCamera cam;
    public static StretchViewport viewport;
    private ShapeRenderer shapeRenderer;

    public static SpriteBatch batcher;

    private int midPointY;
    private int gameHeight;

    private final long UpdateTime_InputTimer = 100;
    private final long UpdateTime_MouseTimer = 10;
    private final long UpdateTime_MapAnim = 250;
    private long LastUpdateTime_InputTimer;
    private long LastUpdateTime_MouseTimer;
    private long LastUpdateTime_MapAnim;

    public editorRenderer(editorWorld world, int gameHeight, int midPointY) {
        myWorld = world;

        // The word "this" refers to this instance.
        // We are setting the instance variables' values to be that of the
        // parameters passed in from GameScreen.
        this.gameHeight = gameHeight;
        this.midPointY = midPointY;

        cam = new OrthographicCamera();
        cam.setToOrtho(true, 448, 448);

        viewport = new StretchViewport(448, 448, cam);
        viewport.apply();

        batcher = new SpriteBatch();
        batcher.setProjectionMatrix(cam.combined);
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(cam.combined);

        cam.position.set(cam.viewportWidth / 2, cam.viewportHeight / 2,0);
    }

    public void render(float runTime) {
        if (!editorVars.reloadingMap) {
            //Rectangle Offset;
            /*
            editorVars.ScreenX = 1280;
            editorVars.ScreenY = 720;
            editorVars.MAX_MAPX = 1280 / editorVars.mapZoom - 1;
            editorVars.MAX_MAPY = 720 / editorVars.mapZoom - 1;
            editorVars.HalfX = editorVars.MAX_MAPX / 2 - 1;
            editorVars.HalfY = editorVars.MAX_MAPY / 2 - 1;
            */

            //Offset = nextFrame();

            Gdx.gl.glClearColor(0, 0, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            batcher.setProjectionMatrix(cam.combined);

            long tickCount = System.currentTimeMillis();

            if (LastUpdateTime_InputTimer < tickCount) {
                handleInput();
                LastUpdateTime_InputTimer = tickCount + UpdateTime_InputTimer;
            }
            if (LastUpdateTime_MouseTimer < tickCount) {
                handleMouseInput();
                LastUpdateTime_MouseTimer = tickCount + UpdateTime_MouseTimer;
            }
            // MapAnim
            if (LastUpdateTime_MapAnim < tickCount) {

                // Single Frame
                switch (editorVars.MapAnim) {
                    case 0:
                        editorVars.MapAnim = 1;
                        break;
                    case 1:
                        editorVars.MapAnim = 0;
                        break;
                }

                // Animate Waterfalls
                switch (editorVars.WaterfallAnim) {
                    case 0:
                        editorVars.WaterfallAnim = 1;
                        break;
                    case 1:
                        editorVars.WaterfallAnim = 2;
                        break;
                    case 2:
                        editorVars.WaterfallAnim = 0;
                        break;
                }

                // Animate Autotiles
                switch (editorVars.AutoTileAnim) {
                    case 0:
                        editorVars.AutoTileAnim = 1;
                        break;
                    case 1:
                        editorVars.AutoTileAnim = 2;
                        break;
                    case 2:
                        editorVars.AutoTileAnim = 0;
                        break;
                }

                LastUpdateTime_MapAnim = tickCount + UpdateTime_MapAnim;
            }

            if (!editorVars.reloadingMap) {
                if (batcher.isDrawing()) {
                    batcher.end();
                }
                batcher.begin();

                batcher.enableBlending();


                renderMap_Lower();

                int MovementSpeed = 8;

                switch (editorVars.Player.getDir()) {
                    case editorVars.Dir_Up:
                        editorVars.Player.setOffsetY(editorVars.Player.getOffsetY() - MovementSpeed);
                        if (editorVars.Player.getOffsetY() < 0) editorVars.Player.setOffsetY(0);
                        break;
                    case editorVars.Dir_Down:
                        editorVars.Player.setOffsetY(editorVars.Player.getOffsetY() + MovementSpeed);
                        if (editorVars.Player.getOffsetY() > 0) editorVars.Player.setOffsetY(0);
                        break;
                    case editorVars.Dir_Left:
                        editorVars.Player.setOffsetX(editorVars.Player.getOffsetX() - MovementSpeed);
                        if (editorVars.Player.getOffsetX() < 0) editorVars.Player.setOffsetX(0);
                        break;
                    case editorVars.Dir_Right:
                        editorVars.Player.setOffsetX(editorVars.Player.getOffsetX() + MovementSpeed);
                        if (editorVars.Player.getOffsetX() > 0) editorVars.Player.setOffsetX(0);
                        break;
                }

                int PlayerX = ((editorVars.Player.getX() * 32) + editorVars.Player.getOffsetX());
                int PlayerY = ((editorVars.Player.getY() * 32) + editorVars.Player.getOffsetY());
                renderPlayer(runTime, PlayerX, PlayerY);

                renderMap_Upper();

                if (!editorVars.reloadingMap) {
                    // RenderAndroid Tile Types //
                    for (int X = 0; X <= editorVars.mapRender.MaxX - 1; X++) {
                        for (int Y = 0; Y <= editorVars.mapRender.MaxY - 1; Y++) {
                            //if (X >= 0 && Y >= 0)
                            //{
                            if (editorVars.mapRender == null) { return; }
                            if (editorVars.mapRender.Tile == null) { return; }
                            if (editorVars.mapRender.Tile[X][Y] == null) { return; }
                            switch (editorVars.mapRender.Tile[X][Y].Type) {
                                case editorVars.TILE_TYPE_BLOCKED:
                                    drawText("B", (X * 32) + 10, (Y * 32) + 10, Color.RED);
                                    break;
                                case editorVars.TILE_TYPE_NPCSPAWN:
                                    drawText("NS", (X * 32) + 10, (Y * 32) + 10, Color.CYAN);
                                    break;
                                case editorVars.TILE_TYPE_NPCAVOID:
                                    drawText("NA", (X * 32) + 10, (Y * 32) + 10, Color.WHITE);
                                    break;
                                case editorVars.TILE_TYPE_ITEM:
                                    drawText("I", (X * 32) + 10, (Y * 32) + 10, Color.YELLOW);
                                    break;
                                case editorVars.TILE_TYPE_WARP:
                                    drawText("W", (X * 32) + 10, (Y * 32) + 10, Color.ROYAL);
                                    break;
                                case editorVars.TILE_TYPE_HEAL:
                                    drawText("H", (X * 32) + 10, (Y * 32) + 10, Color.GREEN);
                                    break;
                            }
                            //}
                        }
                    }
                }

                /*editorAssetLoader.font.setColor(Color.BLACK);
                editorAssetLoader.font.draw(batcher, "Map: " + editorVars.Player.getMap(), 10, 9);
                editorAssetLoader.font.draw(batcher, "Map: " + editorVars.Player.getMap(), 9, 10);
                editorAssetLoader.font.draw(batcher, "Map: " + editorVars.Player.getMap(), 11, 10);
                editorAssetLoader.font.draw(batcher, "Map: " + editorVars.Player.getMap(), 10, 11);
                editorAssetLoader.font.setColor(Color.YELLOW);
                editorAssetLoader.font.draw(batcher, "Map: " + editorVars.Player.getMap(), 10, 10);
                editorAssetLoader.font.setColor(Color.WHITE);

                editorAssetLoader.font.setColor(Color.BLACK);
                editorAssetLoader.font.draw(batcher, "X: " + editorVars.Player.getX(), 10, 29);
                editorAssetLoader.font.draw(batcher, "X: " + editorVars.Player.getX(), 9, 30);
                editorAssetLoader.font.draw(batcher, "X: " + editorVars.Player.getX(), 11, 30);
                editorAssetLoader.font.draw(batcher, "X: " + editorVars.Player.getX(), 10, 31);
                editorAssetLoader.font.setColor(Color.YELLOW);
                editorAssetLoader.font.draw(batcher, "X: " + editorVars.Player.getX(), 10, 30);
                editorAssetLoader.font.setColor(Color.WHITE);

                editorAssetLoader.font.setColor(Color.BLACK);
                editorAssetLoader.font.draw(batcher, "Y: " + editorVars.Player.getY(), 10, 49);
                editorAssetLoader.font.draw(batcher, "Y: " + editorVars.Player.getY(), 9, 50);
                editorAssetLoader.font.draw(batcher, "Y: " + editorVars.Player.getY(), 11, 50);
                editorAssetLoader.font.draw(batcher, "Y: " + editorVars.Player.getY(), 10, 51);
                editorAssetLoader.font.setColor(Color.YELLOW);
                editorAssetLoader.font.draw(batcher, "Y: " + editorVars.Player.getY(), 10, 50);
                editorAssetLoader.font.setColor(Color.WHITE);*/

                drawText(Integer.toString(editorVars.disX), 5, 25, Color.WHITE);
                drawText(Integer.toString(editorVars.disY), 5, 45, Color.WHITE);
                drawText(Integer.toString(editorVars.Player.getMap()), 5, 5, Color.WHITE);
                drawText(Integer.toString(editorVars.mapRender.Up), 220, 5, Color.WHITE);
                drawText(Integer.toString(editorVars.mapRender.Down), 220, 420, Color.WHITE);
                drawText(Integer.toString(editorVars.mapRender.Left), 5, 220, Color.WHITE);
                drawText(Integer.toString(editorVars.mapRender.Right), 410, 220, Color.WHITE);

                batcher.disableBlending();
                batcher.end();
            }
        }
    }

    public static GlyphLayout layout = new GlyphLayout();
    public static void drawText(String text, float X, float Y, Color color) {
        if (editorAssetLoader.font.getColor() == null) { return; }
        layout.setText(editorAssetLoader.font, text);
        float width = layout.width;// contains the width of the current set text

        //float nameX = (X - (int)width) - 20;
        //float nameY = Y + 20;

        editorAssetLoader.font.setColor(Color.BLACK);
        editorAssetLoader.font.draw(batcher, text, X - 2, Y);
        editorAssetLoader.font.draw(batcher, text, X + 2, Y);
        editorAssetLoader.font.draw(batcher, text, X, Y - 2);
        editorAssetLoader.font.draw(batcher, text, X, Y + 2);
        editorAssetLoader.font.setColor(color);
        editorAssetLoader.font.draw(batcher, text, X, Y);
    }

    private void handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.W)){
            if (editorVars.Player.getY() > 0) {
                editorVars.Player.setY(editorVars.Player.getY() - 1);
            } else if (editorVars.Player.getY() == 0) {
                if (editorVars.mapRender.Up > 0) {
                    editorAssetLoader.loadTempMap(editorVars.mapRender.Up);
                    editorSuite.playerWarp(editorVars.mapRender.Up, editorVars.Player.getX(), editorVars.tempMap.MaxY - 1);
                }
            }
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.A)){
            if (editorVars.Player.getX() > 0) {
                editorVars.Player.setX(editorVars.Player.getX() - 1);
            } else if (editorVars.Player.getX() == 0) {
                if (editorVars.mapRender.Left > 0) {
                    editorAssetLoader.loadTempMap(editorVars.mapRender.Left);
                    editorSuite.playerWarp(editorVars.mapRender.Left, editorVars.tempMap.MaxX - 1, editorVars.Player.getY());
                }
            }
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.S)){
            if (editorVars.Player.getY() < editorVars.mapRender.MaxY - 1) {
                editorVars.Player.setY(editorVars.Player.getY() + 1);
            } else if (editorVars.Player.getY() == editorVars.mapRender.MaxY - 1) {
                if (editorVars.mapRender.Down > 0) {
                    editorAssetLoader.loadTempMap(editorVars.mapRender.Down);
                    editorSuite.playerWarp(editorVars.mapRender.Down, editorVars.Player.getX(), 0);
                }
            }
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.D)){
            if (editorVars.Player.getX() < editorVars.mapRender.MaxX - 1) {
                editorVars.Player.setX(editorVars.Player.getX() + 1);
            } else if (editorVars.Player.getX() == editorVars.mapRender.MaxX - 1) {
                if (editorVars.mapRender.Right > 0) {
                    editorAssetLoader.loadTempMap(editorVars.mapRender.Right);
                    editorSuite.playerWarp(editorVars.mapRender.Right, 0, editorVars.Player.getY());
                }
            }
        }
    }

    private void handleMouseInput() {
        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            editorInputHandler.handleMouseInput(Gdx.input.getX(), Gdx.input.getY(), Input.Buttons.LEFT, true);
        } else if (Gdx.input.isButtonPressed(Input.Buttons.RIGHT)) {
            editorInputHandler.handleMouseInput(Gdx.input.getX(), Gdx.input.getY(), Input.Buttons.RIGHT, true);
        }
    }

    public static void renderPlayer(float runTime, int x, int y) {

        switch (editorVars.Player.getDir()) {
            case editorVars.Dir_Up:
                batcher.draw(editorAssetLoader.spritesUp2[1], x, y - 3, 32, 36);
                return;
            case editorVars.Dir_Down:
                batcher.draw(editorAssetLoader.spritesDown2[1], x, y - 3, 32, 36);
                return;
            case editorVars.Dir_Left:
                batcher.draw(editorAssetLoader.spritesLeft2[1], x, y - 3, 32, 36);
                return;
            case editorVars.Dir_Right:
                batcher.draw(editorAssetLoader.spritesRight2[1], x, y - 3, 32, 36);
                return;
        }
    }

    public static void renderMap_Lower() {
        if (!editorVars.reloadingMap) {
            int Text = 0;

            for (int x = editorVars.MinX; x <= editorVars.MaxX - 1; x++) {
                for (int y = editorVars.MinY; y <= editorVars.MaxY - 1; y++) {
                    for (int LoopL = 0; LoopL <= 2; LoopL++) {
                        if (editorVars.mapRender == null) { break; }
                        if (editorVars.mapRender.Tile == null) { break; }
                        if (editorVars.mapRender.Tile[x][y] == null) { break; }
                        if (editorVars.mapRender.Tile[x][y].TileNum == null) { break; }
                        Text = editorVars.mapRender.Tile[x][y].TileNum[LoopL];
                        int tileSet = editorVars.mapRender.Tile[x][y].Layer[LoopL].Tileset;
                        int tileX = editorVars.mapRender.Tile[x][y].Layer[LoopL].X;
                        int tileY = editorVars.mapRender.Tile[x][y].Layer[LoopL].Y;
                        if (tileSet > 0) {
                            batcher.draw(editorAssetLoader.tiles[tileSet],
                                    editorVars.TileLayer[LoopL].Tile[Text].PixelPosX,
                                    editorVars.TileLayer[LoopL].Tile[Text].PixelPosY,
                                    32,
                                    32,
                                    tileX,
                                    tileY,
                                    32,
                                    32,
                                    false,
                                    true);
                        }
                    }
                }
            }
        }
    }

    public static void renderMap_Upper() {
        if (!editorVars.reloadingMap) {
            int Text = 0;
            for (int x = editorVars.MinX; x <= editorVars.MaxX - 1; x++) {
                for (int y = editorVars.MinY; y <= editorVars.MaxY - 1; y++) {
                    for (int LoopL = 3; LoopL <= 4; LoopL++) {
                        if (editorVars.mapRender == null) { break; }
                        if (editorVars.mapRender.Tile == null) { break; }
                        if (editorVars.mapRender.Tile[x][y] == null) { break; }
                        if (editorVars.mapRender.Tile[x][y].TileNum == null) { break; }
                        Text = editorVars.mapRender.Tile[x][y].TileNum[LoopL];
                        int tileSet = editorVars.mapRender.Tile[x][y].Layer[LoopL].Tileset;
                        int tileX = editorVars.mapRender.Tile[x][y].Layer[LoopL].X;
                        int tileY = editorVars.mapRender.Tile[x][y].Layer[LoopL].Y;
                        if (tileSet > 0) {
                            batcher.draw(editorAssetLoader.tiles[tileSet],
                                    editorVars.TileLayer[LoopL].Tile[Text].PixelPosX,
                                    editorVars.TileLayer[LoopL].Tile[Text].PixelPosY,
                                    32,
                                    32,
                                    tileX,
                                    tileY,
                                    32,
                                    32,
                                    false,
                                    true);
                        }
                    }
                Text++;
                }
            }
        }
    }
}
