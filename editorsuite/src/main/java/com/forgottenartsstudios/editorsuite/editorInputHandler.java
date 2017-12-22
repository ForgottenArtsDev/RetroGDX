package com.forgottenartsstudios.editorsuite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import static com.forgottenartsstudios.editorsuite.editorSuite.tileScroll;

/**
 * Created by Perfekt on 8/31/2016.
 */
public class editorInputHandler implements InputProcessor {

    private editorWorld myWorld;
    public static boolean leftMouseDown, rightMouseDown;
    private static int CurX, CurY;
    private static int lastClicked = 0;

    public editorInputHandler(editorWorld myWorld) {
        this.myWorld = myWorld;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        CurX = editorVars.MinX + ((screenX + editorVars.ScreenMinX) / 32);
        CurY = editorVars.MinY + ((screenY + editorVars.ScreenMinY) / 32);
        //handleMouseInput(screenX, screenY, button, true);
        return false; // Return true to say we handled the touch.
    }

    @Override
    public boolean keyDown(int keycode) {
        if (Gdx.input.isKeyPressed(Input.Keys.T)){
            if (!editorVars.toolBox.isVisible()) {
                editorVars.toolBox.setVisible(true);
            }
        } else if (Gdx.input.isKeyPressed(Input.Keys.N)) {
            if (!editorVars.npcEditor.isVisible()) {
                editorVars.npcEditor.setVisible(true);
            }
        } else if (Gdx.input.isKeyPressed(Input.Keys.I)) {
            if (!editorVars.itemEditor.isVisible()) {
                editorVars.itemEditor.setVisible(true);
            }
        } else if (Gdx.input.isKeyPressed(Input.Keys.O)) {
            if (!editorVars.shopEditor.isVisible()) {
                editorVars.shopEditor.setVisible(true);
            }
        } else if (Gdx.input.isKeyPressed(Input.Keys.K)) {
            if (!editorVars.spellEditor.isVisible()) {
                editorVars.spellEditor.setVisible(true);
            }
        }

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {

        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {

        editorVars.disX = editorVars.MinX + ((screenX + editorVars.ScreenMinX) / 32);
        editorVars.disY = editorVars.MinY + ((screenY + editorVars.ScreenMinY) / 32);
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    private static void HandleLeftMouseDown()
    {
        //Point Pos = new Point((int)curMouse.X, (int)curMouse.Y);
        //Point Cur = new Point((int)curMouse.X, (int)curMouse.Y);

        // Get Clicked Position (BitWise)
        //Cur.X = (Pos.X >> 5);
        //Cur.Y = (Pos.Y >> 5);
        if (editorSuite.bucketChk.isSelected()) {
            if (CurX < 0 | CurX > (editorVars.mapRender.MaxX - 1) | CurY < 0 | CurY > (editorVars.mapRender.MaxY - 1)) {
                return;
            }
            mapEditorBucket(CurX, CurY, (byte) editorVars.autoTileIndex);
        } else {
            if (CurX < 0 | CurX > (editorVars.mapRender.MaxX - 1) | CurY < 0 | CurY > (editorVars.mapRender.MaxY - 1)) {
                return;
            }

            if (editorSuite.rbLayer.isSelected()) {
                // no autotiling
                if (editorVars.EditorTileWidth == 1 & editorVars.EditorTileHeight == 1) {
                    //single tile
                    if (editorVars.autoTileIndex == 0) {
                        MapEditorSetTile(CurX, CurY, false, (byte) editorVars.autoTileIndex);
                    } else {
                        MapEditorSetTile(CurX, CurY, false, (byte) editorVars.autoTileIndex);
                    }
                } else // multi tile!
                {
                    if (editorVars.autoTileIndex == 0) {
                        MapEditorSetTile(CurX, CurY, true, (byte) 0);
                    } else {
                        MapEditorSetTile(CurX, CurY, false, (byte) editorVars.autoTileIndex);
                    }
                }
            }

            if (editorSuite.rbAttributes.isSelected()) {
                editorVars.mapRender.Tile[CurX][CurY].Type = editorVars.selectedTileType;
                editorVars.mapRender.Tile[CurX][CurY].Data1 = editorVars.selectedData1;
                editorVars.mapRender.Tile[CurX][CurY].Data2 = editorVars.selectedData2;
                editorVars.mapRender.Tile[CurX][CurY].Data3 = editorVars.selectedData3;
            }
        }
    }
    private static void HandleLeftMouseMove()
    {

    }
    private static void HandleLeftMouseUp()
    {

    }

    //////////////////////////////
    // Right Mouse Button Input //
    //////////////////////////////
    private static void HandleRightMouseDown()
    {
        if (CurX < 0 | CurX > (editorVars.mapRender.MaxX - 1) | CurY < 0 | CurY > (editorVars.mapRender.MaxY - 1)) { return; }

        if (editorSuite.rbLayer.isSelected())
        {
            MapEditorRemoveTile(CurX, CurY);
        }

        if (editorSuite.rbAttributes.isSelected())
        {
            editorVars.mapRender.Tile[CurX][CurY].Type = 0;
            editorVars.mapRender.Tile[CurX][CurY].Data1 = 0;
            editorVars.mapRender.Tile[CurX][CurY].Data2 = 0;
            editorVars.mapRender.Tile[CurX][CurY].Data3 = 0;
        }
    }
    private static void HandleRightMouseMove()
    {

    }
    private static void HandleRightMouseUp()
    {

    }

    private static void mapEditorBucket(int x, int y, byte theAutotile) {
        if (x < 0 | x > (editorVars.mapRender.MaxX - 1) | y < 0 | y > (editorVars.mapRender.MaxY - 1)) {
            return;
        }
        int targetTileTS = editorVars.mapRender.Tile[x][y].Layer[editorVars.layerIndex].Tileset;
        int targetTileX = editorVars.mapRender.Tile[x][y].Layer[editorVars.layerIndex].X;
        int targetTileY = editorVars.mapRender.Tile[x][y].Layer[editorVars.layerIndex].Y;
        int[] targetAutotile = editorVars.mapRender.Tile[x][y].Autotile;

        if (targetTileX == editorVars.EditorTileX && targetTileY == editorVars.EditorTileY && targetTileTS == editorVars.currentTileSet) { return; }
        editorVars.mapRender.Tile[x][y].Layer[editorVars.layerIndex].Tileset = editorVars.currentTileSet;
        editorVars.mapRender.Tile[x][y].Layer[editorVars.layerIndex].X = editorVars.EditorTileX;
        editorVars.mapRender.Tile[x][y].Layer[editorVars.layerIndex].Y = editorVars.EditorTileY;
        editorVars.mapRender.Tile[x][y].Autotile[editorVars.layerIndex] = theAutotile;

        mapEditorBucket(x + 1, y, theAutotile);
        mapEditorBucket(x - 1, y, theAutotile);
        mapEditorBucket(x, y + 1, theAutotile);
        mapEditorBucket(x, y - 1, theAutotile);
    }

    public static void mapEditorDrag(MouseEvent e, int startX, int startY)//(int posX, int posY)
    {
        editorVars.EditorTileStartX = startX;
        editorVars.EditorTileStartY = startY;
        if (editorVars.EditorTileX > 0) { editorVars.EditorTileX = 0; }
        if (editorVars.EditorTileY > 0) { editorVars.EditorTileY = 0; }
        //Point Pos = new Point();// = e.getPoint();
        //Pos.setLocation(e.getX(), e.getY());
        int PosX = e.getX();
        int PosY = e.getY();
        editorVars.SelectedTile = new Rectangle(32, 32);
        // convert the pixel number to tile number
        int X = ((PosX - startX) / 32) + 1;
        int Y = ((PosY - startY) / 32) + 1;

        // check it's not out of bounds
        if (X < 0) X = 0;
        if (X > editorVars.tileBox.getWidth() / 32) X = editorVars.tileBox.getWidth() / 32;
        if (Y < 0) Y = 0;
        if (Y > editorVars.tileBox.getHeight() / 32) Y = editorVars.tileBox.getHeight() / 32;

        // find out what to set the width + height of map editor to
        if (X > editorVars.EditorTileX) { // drag right
            editorVars.EditorTileWidth = X - editorVars.EditorTileX;
        } else // drag left
        {
            // TO DO
        }
        if (Y > editorVars.EditorTileY) { // drag down
            editorVars.EditorTileHeight = Y - editorVars.EditorTileY;
        } else // drag up
        {
            // TO DO
        }

        editorVars.SelectedTile.setLocation(startX, startY);
        editorVars.SelectedTile.setSize(editorVars.EditorTileWidth * 32, editorVars.EditorTileHeight * 32);
        //editorVars.SelectedTile = new Rectangle(editorVars.EditorTileWidth * 32, editorVars.EditorTileHeight * 32);

        String path = "android/assets/data/tiles/" + editorVars.currentTileSet + ".png";
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(path));
        } catch (IOException r) {
            r.printStackTrace();
        }

        Graphics2D graphics = image.createGraphics();
        graphics.setColor(Color.RED);
        graphics.drawRect(startX, startY, (int)editorVars.SelectedTile.getWidth(), (int)editorVars.SelectedTile.getHeight());

        editorVars.tileBox.setIcon(new ImageIcon(image));
        editorVars.tileBox.setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
        editorVars.tileBox.setBounds(0, 0, image.getWidth(), image.getHeight());
        editorVars.tileBox.repaint();

        editorVars.tileBox.invalidate();

        tileScroll.getHorizontalScrollBar().setValue(editorVars.LastScrollHorizontal);
        tileScroll.getVerticalScrollBar().setValue(editorVars.LastScrollVertical);
        tileScroll.getViewport().setViewPosition(editorVars.LastViewport);
    }
    public static void MapEditorSetTile(int X, int Y, boolean multitile, byte theAutotile)
    {
        if (editorVars.currentUndo == 0) {
            editorVars.currentUndo = 1;
            editorVars.undoMapRender[editorVars.currentUndo] = editorVars.mapRender;
        } else if (editorVars.currentUndo == 1) {
            editorVars.currentUndo = 2;
            editorVars.undoMapRender[editorVars.currentUndo - 1] = editorVars.undoMapRender[editorVars.currentUndo];
            editorVars.undoMapRender[editorVars.currentUndo] = editorVars.mapRender;
        } else if (editorVars.currentUndo == 2) {
            editorVars.currentUndo = 3;
            editorVars.undoMapRender[editorVars.currentUndo - 2] = editorVars.undoMapRender[editorVars.currentUndo - 1];
            editorVars.undoMapRender[editorVars.currentUndo - 1] = editorVars.undoMapRender[editorVars.currentUndo];
            editorVars.undoMapRender[editorVars.currentUndo] = editorVars.mapRender;
        } else if (editorVars.currentUndo == 3) {
            editorVars.undoMapRender[editorVars.currentUndo - 2] = editorVars.undoMapRender[editorVars.currentUndo - 1];
            editorVars.undoMapRender[editorVars.currentUndo - 1] = editorVars.undoMapRender[editorVars.currentUndo];
            editorVars.undoMapRender[editorVars.currentUndo] = editorVars.mapRender;
        }
        if (theAutotile > 0)
        {
            // set layer
            editorVars.mapRender.Tile[X][Y].Layer[editorVars.layerIndex].X = editorVars.EditorTileX;
            editorVars.mapRender.Tile[X][Y].Layer[editorVars.layerIndex].Y = editorVars.EditorTileY;
            editorVars.mapRender.Tile[X][Y].Layer[editorVars.layerIndex].Tileset = editorVars.currentTileSet;//(byte)Engine.frmEditor.scrlTileSet.Value;
            editorVars.mapRender.Tile[X][Y].Autotile[editorVars.layerIndex] = theAutotile;

            //editorAutotiles.cacheRenderState(X, Y, editorVars.layerIndex);
            return;
        }

        // single
        if (!multitile)
        {
            // set layer
            editorVars.mapRender.Tile[X][Y].Layer[editorVars.layerIndex].X = editorVars.EditorTileX;
            editorVars.mapRender.Tile[X][Y].Layer[editorVars.layerIndex].Y = editorVars.EditorTileY;
            editorVars.mapRender.Tile[X][Y].Layer[editorVars.layerIndex].Tileset = editorVars.currentTileSet;//(byte)Engine.frmEditor.scrlTileSet.Value;
            editorVars.mapRender.Tile[X][Y].Autotile[editorVars.layerIndex] = 0;

            //editorAutotiles.cacheRenderState(X, Y, editorVars.layerIndex);
        }
        else
        {
            int y2 = 0; // starting tile for y axis
            int x2 = 0;

            editorVars.EditorTileX = editorVars.EditorTileStartX;
            editorVars.EditorTileY = editorVars.EditorTileStartY;

            for (int dY = CurY; dY <= CurY + editorVars.EditorTileHeight - 1; dY++)
            {
                x2 = 0; // re-set x count every y loop
                for (int dX = CurX; dX <= CurX + editorVars.EditorTileWidth - 1; dX++)
                {
                    //if (dX >= editorVars.MinX && dX <= editorVars.MaxX - 1)
                    //{
                    //    if (dY >= editorVars.MinY && dY <= editorVars.MaxY - 1)
                    //    {
                            editorVars.mapRender.Tile[dX][dY].Layer[editorVars.layerIndex].X = (editorVars.EditorTileX + (x2 * 32));
                            editorVars.mapRender.Tile[dX][dY].Layer[editorVars.layerIndex].Y = (editorVars.EditorTileY + (y2 * 32));
                            editorVars.mapRender.Tile[dX][dY].Layer[editorVars.layerIndex].Tileset = editorVars.currentTileSet;
                            editorVars.mapRender.Tile[dX][dY].Autotile[editorVars.layerIndex] = 0;
                    //    }
                    //}
                    x2 = x2 + 1;
                }
                y2 = y2 + 1;
            }
            // cache Map Tiles //
            //editorAutotiles.cacheRenderState(X, Y, editorVars.layerIndex);
        }
    }

    private static void MapEditorRemoveTile(int X, int Y)
    {
        editorVars.mapRender.Tile[X][Y].Layer[editorVars.layerIndex].X = 0;
        editorVars.mapRender.Tile[X][Y].Layer[editorVars.layerIndex].Y = 0;
        editorVars.mapRender.Tile[X][Y].Layer[editorVars.layerIndex].Tileset = 0;
        editorVars.mapRender.Tile[X][Y].Autotile[editorVars.layerIndex] = 0;
        //editorAutotiles.cacheRenderState(X, Y, editorVars.layerIndex);
    }

    public static void handleMouseInput(int screenX, int screenY, int button, boolean buttonDown)
    {
        // Get Cursor (Grid)
        CurX = editorVars.MinX + ((screenX + editorVars.ScreenMinX) / 32);
        CurY = editorVars.MinY + ((screenY + editorVars.ScreenMinY) / 32);

        HandleLeftMouseMove();
        HandleRightMouseMove();

        if (buttonDown) {
            if (button == Input.Buttons.LEFT)//Gdx.input.isButtonPressed(Input.Buttons.LEFT))
            {
                leftMouseDown = true;
                HandleLeftMouseDown();
                lastClicked = 1;
            } else if (button == Input.Buttons.RIGHT) {
                rightMouseDown = true;
                HandleRightMouseDown();
                lastClicked = 2;
            }
        } else {
            if (button == Input.Buttons.LEFT) {
                if (lastClicked == 1) {
                    leftMouseDown = false;
                    HandleLeftMouseUp();
                    lastClicked = 0;
                }
            } else if (button == Input.Buttons.RIGHT) {
                if (lastClicked == 2) {
                    rightMouseDown = false;
                    HandleRightMouseUp();
                    lastClicked = 0;
                }
            }
        }
    }
}
