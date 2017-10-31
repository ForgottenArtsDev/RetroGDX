package com.forgottenartsstudios.editorsuite;

import com.badlogic.gdx.Game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneLayout;

/**
 * Created by Perfekt on 8/31/2016.
 */
public class editorSuite extends Game {

    public static JRadioButton rbLayer;
    public static JRadioButton rbAttributes;
    public static JTextField txtUp, txtDown, txtLeft, txtRight, txtX, txtY;
    public static final JScrollPane tileScroll = new JScrollPane();
    public static JCheckBox bucketChk = new JCheckBox();

    @Override
    public void create() {
        editorVars.Player = new editorPlayerData();

        editorVars.Player.setSprite(1);
        editorVars.Player.setDir(editorVars.Dir_Down);
        editorVars.Player.setX(5);
        editorVars.Player.setY(5);
        editorVars.Player.setOffsetX(0);
        editorVars.Player.setOffsetY(0);
        editorVars.Player.setIsMoving(0);
        editorVars.Player.setMap(1);

        editorVars.npcs = new com.forgottenartsstudios.data.NPC_Struct[editorVars.maxNPCs + 1];
        for (int i = 1; i <= editorVars.maxNPCs; i++) {
            editorVars.npcs[i] = new com.forgottenartsstudios.data.NPC_Struct();
            editorLoadData.clearNPC(i);
            editorAssetLoader.loadNPC(i);
        }
        editorVars.items = new com.forgottenartsstudios.data.Item_Struct[editorVars.maxItems + 1];
        for (int i = 1; i <= editorVars.maxItems; i++) {
            editorVars.items[i] = new com.forgottenartsstudios.data.Item_Struct();
            editorLoadData.clearItem(i);
            editorAssetLoader.loadItem(i);
        }
        editorVars.shops = new com.forgottenartsstudios.data.Shop_Struct[editorVars.maxShops + 1];
        for (int i = 1; i <= editorVars.maxShops; i++) {
            editorVars.shops[i] = new com.forgottenartsstudios.data.Shop_Struct();
            editorLoadData.clearShop(i);
            editorAssetLoader.loadShop(i);
        }

        editorVars.toolBox = new JFrame();
        editorVars.toolBox.setLayout(null);
        editorVars.toolBox.setBounds(0, 0, 420, 700);
        editorVars.toolBox.setTitle("Editor Suite - Toolbox");
        editorVars.toolBox.setResizable(false);
        editorVars.toolBox.setAlwaysOnTop(true);

        npcEditor.create();
        npcSpawn.create();
        itemEditor.create();
        itemSpawn.create();
        warpTile.create();
        shopEditor.create();

        JPanel toolBox = new JPanel();
        editorVars.tileBox = new JLabel();
        editorVars.tileSet = new JLabel();
        JButton btnCancel = new JButton();
        JButton btnSaveMap = new JButton();
        JScrollBar scrlTileset = new JScrollBar(JScrollBar.HORIZONTAL, 1, 1, 0, editorVars.MaxTiles + 1);
        JButton btnFill = new JButton();
        JButton btnClear = new JButton();
        JButton btnConvertMap = new JButton();
        final JRadioButton optGround = new JRadioButton("", true);
        final JRadioButton optMask = new JRadioButton();
        final JRadioButton optMask2 = new JRadioButton();
        final JRadioButton optFringe = new JRadioButton();
        final JRadioButton optFringe2 = new JRadioButton();
        rbLayer = new JRadioButton("", true);
        rbAttributes = new JRadioButton();
        final JComboBox<String> tileTypes = new JComboBox<>();
        final JComboBox<String> attributeTypes = new JComboBox<>();
        JButton btnUndo = new JButton();

        // Warp Stuff For Moving Between mapData Easily
        JLabel lblWarp = new JLabel();
        final JTextField txtWarp = new JTextField();
        JButton btnWarp = new JButton();

        // Properties: Setting map size (x/y), and linked maps.
        JLabel lblUp, lblDown, lblLeft, lblRight, lblX, lblY;

        JLabel lblWarpX, lblWarpY;
        JButton btnWarpXY;
        final JTextField txtWarpX, txtWarpY;

        JButton btnSaveProperties;

        lblUp = new JLabel();
        lblDown = new JLabel();
        lblLeft = new JLabel();
        lblRight = new JLabel();
        lblX = new JLabel();
        lblY = new JLabel();

        txtUp = new JTextField();
        txtDown = new JTextField();
        txtLeft = new JTextField();
        txtRight = new JTextField();
        txtX = new JTextField();
        txtY = new JTextField();

        bucketChk.setText("Fill Bucket");
        bucketChk.setBounds(215, 430, 100, 25);

        btnSaveProperties = new JButton();

        lblWarpX = new JLabel();
        lblWarpY = new JLabel();
        btnWarpXY = new JButton();
        txtWarpX = new JTextField();
        txtWarpY = new JTextField();

        lblWarpX.setText("X:");
        lblWarpY.setText("Y:");
        btnWarpXY.setText("Warp XY");
        txtWarpX.setText("0");
        txtWarpY.setText("0");

        lblWarpX.setBounds(129, 605, 20, 20);
        txtWarpX.setBounds(149, 605, 20, 20);
        lblWarpY.setBounds(129, 630, 20, 20);
        txtWarpY.setBounds(149, 630, 20, 20);
        btnWarpXY.setBounds(169, 615, 100, 20);

        btnWarpXY.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editorVars.Player.setX(Integer.parseInt(txtWarpX.getText()));
                editorVars.Player.setY(Integer.parseInt(txtWarpY.getText()));
            }
        });

        btnUndo.setText("Undo");
        btnUndo.setBounds(215, 465, 80, 25);
        btnUndo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("currentUndo: " + editorVars.currentUndo);
                if (editorVars.currentUndo == 1) {
                    editorVars.mapRender = editorVars.undoMapRender[1];
                    editorVars.currentUndo = 0;
                } else if (editorVars.currentUndo == 2) {
                    editorVars.mapRender = editorVars.undoMapRender[2];
                    editorVars.currentUndo = 1;
                } else if (editorVars.currentUndo == 3) {
                    editorVars.mapRender = editorVars.undoMapRender[3];
                    editorVars.currentUndo = 2;
                }
                editorAssetLoader.cacheTiles();
            }
        });

        lblUp.setText("Up:");
        lblDown.setText("Down:");
        lblLeft.setText("Left:");
        lblRight.setText("Right:");
        lblX.setText("Max X:");
        lblY.setText("Max Y:");

        txtUp.setText("0");
        txtDown.setText("0");
        txtLeft.setText("0");
        txtRight.setText("0");
        txtX.setText("50");
        txtY.setText("50");

        lblUp.setBounds(280, 520, 50, 20);
        lblDown.setBounds(280, 545, 50, 20);
        lblLeft.setBounds(280, 570, 50, 20);
        lblRight.setBounds(280, 595, 50, 20);
        lblX.setBounds(170, 520, 50, 20);
        lblY.setBounds(170, 545, 50, 20);

        txtUp.setBounds(340, 520, 50, 20);
        txtDown.setBounds(340, 545, 50, 20);
        txtLeft.setBounds(340, 570, 50, 20);
        txtRight.setBounds(340, 595, 50, 20);
        txtX.setBounds(220, 520, 50, 20);
        txtY.setBounds(220, 545, 50, 20);

        btnSaveProperties.setText("Save Properties");
        btnSaveProperties.setBounds(129, 570, 140, 30);
        btnSaveProperties.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editorVars.reloadingMap = true;
                editorAssetLoader.copyMap();

                editorVars.mapRender.Up = Integer.parseInt(txtUp.getText());
                editorVars.mapRender.Down = Integer.parseInt(txtDown.getText());
                editorVars.mapRender.Left = Integer.parseInt(txtLeft.getText());
                editorVars.mapRender.Right = Integer.parseInt(txtRight.getText());

                editorVars.mapRender.MaxX = Integer.parseInt(txtX.getText());
                editorVars.mapRender.MaxY = Integer.parseInt(txtY.getText());

                // Resize Map
                editorLoadData.clearMap(editorVars.mapRender.MaxX, editorVars.mapRender.MaxY);
                editorAssetLoader.cacheTiles();
                editorAssetLoader.reloadMap();
                editorAssetLoader.cacheTiles();
                editorVars.reloadingMap = false;
            }
        });

        tileTypes.addItem("Normal");
        tileTypes.addItem("Autotile");
        tileTypes.addItem("Fake");
        tileTypes.addItem("Animated");
        tileTypes.addItem("Cliff");
        tileTypes.addItem("Waterfall");

        attributeTypes.addItem("None");
        attributeTypes.addItem("Blocked");
        attributeTypes.addItem("Warp");
        attributeTypes.addItem("Item");
        attributeTypes.addItem("NPC Avoid");
        attributeTypes.addItem("Key");
        attributeTypes.addItem("Key Open");
        attributeTypes.addItem("Resource");
        attributeTypes.addItem("Door");
        attributeTypes.addItem("NPC Spawn");
        attributeTypes.addItem("Shop");
        attributeTypes.addItem("Bank");
        attributeTypes.addItem("Heal");
        attributeTypes.addItem("Trap");
        attributeTypes.addItem("Chat");

        attributeTypes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox combo = (JComboBox)e.getSource();
                if (combo.getSelectedIndex() == editorVars.TILE_TYPE_BLOCKED) {
                    editorVars.selectedTileType = editorVars.TILE_TYPE_BLOCKED;
                    editorVars.selectedData1 = 0;
                    editorVars.selectedData2 = 0;
                    editorVars.selectedData3 = 0;
                } else if (combo.getSelectedIndex() == editorVars.TILE_TYPE_NPCSPAWN) {
                    editorVars.npcSpawn.setVisible(true);
                } else if (combo.getSelectedIndex() == editorVars.TILE_TYPE_ITEM) {
                    editorVars.itemSpawn.setVisible(true);
                } else if (combo.getSelectedIndex() == editorVars.TILE_TYPE_WARP) {
                    editorVars.warpTile.setVisible(true);
                } else if (combo.getSelectedIndex() == editorVars.TILE_TYPE_NPCAVOID) {
                    editorVars.selectedTileType = editorVars.TILE_TYPE_NPCAVOID;
                    editorVars.selectedData1 = 0;
                    editorVars.selectedData2 = 0;
                    editorVars.selectedData3 = 0;
                }
            }
        });

        tileTypes.setBounds(5, 524, 100, 20);

        tileTypes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox combo = (JComboBox)e.getSource();
                editorVars.autoTileIndex = combo.getSelectedIndex();
            }
        });

        attributeTypes.setBounds(420 - 130, 476, 100, 20);

        toolBox.setLayout(null);
        toolBox.setPreferredSize(new Dimension(420, 700));
        toolBox.setBounds(0, 0, 420, 700);
        tileScroll.setLayout(new ScrollPaneLayout());
        tileScroll.setPreferredSize(new Dimension(384, 384));
        tileScroll.setBounds(5, 5, 384, 384);

        String path = "android/assets/data/tiles/" + 1 + ".png";
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        editorVars.tileBox.setIcon(new ImageIcon(image));
        editorVars.tileBox.setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
        editorVars.tileBox.setBounds(0, 0, image.getWidth(), image.getHeight());
        editorVars.tileBox.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                Point Pos = me.getPoint();
                editorVars.multiTileStartX = ((int)Pos.getX() >> 5) * 32;
                editorVars.multiTileStartY = ((int)Pos.getY() >> 5) * 32;
                editorVars.LastScrollHorizontal = tileScroll.getHorizontalScrollBar().getValue();
                editorVars.LastScrollVertical = tileScroll.getVerticalScrollBar().getValue();
                editorVars.LastViewport = tileScroll.getViewport().getViewPosition();
                tileBoxMouseDown(me);
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                super.mousePressed(mouseEvent);
                Point Pos = mouseEvent.getPoint();
                editorVars.multiTileStartX = ((int)Pos.getX() >> 5) * 32;
                editorVars.multiTileStartY = ((int)Pos.getY() >> 5) * 32;
                editorVars.LastScrollHorizontal = tileScroll.getHorizontalScrollBar().getValue();
                editorVars.LastScrollVertical = tileScroll.getVerticalScrollBar().getValue();
                editorVars.LastViewport = tileScroll.getViewport().getViewPosition();
            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {
                super.mouseReleased(mouseEvent);
                //editorVars.multiTileStartX = 0;
                //editorVars.multiTileStartY = 0;
            }
        });
        editorVars.tileBox.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent mouseEvent) {
                super.mouseDragged(mouseEvent);
                //if (mouseEvent.getButton() == MouseEvent.BUTTON1)
                //{
                    if (editorVars.multiTileStartX == 0 && editorVars.multiTileStartY == 0) {
                        Point Pos = mouseEvent.getPoint();
                        editorVars.multiTileStartX = ((int)Pos.getX() >> 5) * 32;
                        editorVars.multiTileStartY = ((int)Pos.getY() >> 5) * 32;
                    }
                    editorInputHandler.mapEditorDrag(mouseEvent, editorVars.multiTileStartX, editorVars.multiTileStartY);
                    //editorVars.tileBox.invalidate();
                /*} else {
                    editorVars.multiTileStartX = 0;
                    editorVars.multiTileStartY = 0;
                }*/
            }
        });

        lblWarp.setText("Map #:");
        lblWarp.setBounds(5, 700 - 74 - 75, 40, 20);

        txtWarp.setText("1");
        txtWarp.setBounds(55, 700 - 74 - 75, 50, 20);

        btnWarp.setText("Warp");
        btnWarp.setBounds(5, 700 - 74 - 45, 80, 30);
        btnWarp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Warp To New Map
                editorVars.reloadingMap = true;
                playerWarp(Integer.parseInt(txtWarp.getText()), editorVars.Player.getX(), editorVars.Player.getY());
                editorVars.reloadingMap = false;
            }
        } );

        btnCancel.setText("Cancel");
        btnCancel.setBounds(420 - 100, 700 - 74, 80, 30);
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editorVars.toolBox.setVisible(false);
            }
        } );
        btnSaveMap.setText("Save");
        btnSaveMap.setBounds(5, 700 - 74, 80, 30);
        btnSaveMap.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editorAssetLoader.saveMap();
            }
        } );

        editorVars.tileSet.setText(Integer.toString(editorVars.currentTileSet));
        editorVars.tileSet.setBounds(350, 400, 100, 25);

        scrlTileset.setMaximum(editorVars.MaxTiles);
        scrlTileset.setBounds(5, 400, 300, 20);
        scrlTileset.setBlockIncrement(0);
        scrlTileset.addAdjustmentListener(new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent adjustmentEvent) {
                editorVars.currentTileSet = adjustmentEvent.getValue();
                if (editorVars.currentTileSet < editorVars.MaxTiles + 9) {
                    editorVars.currentTileSet++;
                    editorVars.tileSet.setText(Integer.toString(editorVars.currentTileSet));

                    if (editorVars.currentTileSet > editorVars.MaxTiles) {
                        return;
                    }

                    String path = "android/assets/data/tiles/" + editorVars.currentTileSet + ".png";
                    BufferedImage image = null;
                    try {
                        image = ImageIO.read(new File(path));
                    } catch (IOException r) {
                        r.printStackTrace();
                    }

                    editorVars.tileBox.setIcon(new ImageIcon(image));
                    editorVars.tileBox.setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
                    editorVars.tileBox.setBounds(0, 0, image.getWidth(), image.getHeight());
                    editorVars.tileBox.repaint();
                }
            }
        });
        btnFill.setText("Fill Layer");
        btnFill.setBounds(105, 430, 100, 25);
        btnFill.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editorVars.reloadingMap = true;
                for (int X = 0; X <= editorVars.mapRender.MaxX - 1; X++)
                {
                    for (int Y = 0; Y <= editorVars.mapRender.MaxY - 1; Y++)
                    {
                        if (editorVars.autoTileIndex == 0) {
                            editorVars.mapRender.Tile[X][Y].Layer[editorVars.layerIndex].Tileset = editorVars.currentTileSet;
                            editorVars.mapRender.Tile[X][Y].Layer[editorVars.layerIndex].X = editorVars.EditorTileX;
                            editorVars.mapRender.Tile[X][Y].Layer[editorVars.layerIndex].Y = editorVars.EditorTileY;
                        } else {
                            editorVars.mapRender.Tile[X][Y].Layer[editorVars.layerIndex].Tileset = editorVars.currentTileSet;
                            editorVars.mapRender.Tile[X][Y].Layer[editorVars.layerIndex].X = editorVars.EditorTileX;
                            editorVars.mapRender.Tile[X][Y].Layer[editorVars.layerIndex].Y = editorVars.EditorTileY;
                            editorVars.mapRender.Tile[X][Y].Autotile[editorVars.layerIndex] = editorVars.autoTileIndex;
                        }
                    }
                }
                editorAssetLoader.cacheTiles();
                editorVars.reloadingMap = false;
            }
        } );

        btnClear.setText("Clear Layer");
        btnClear.setBounds(105, 465, 100, 25);
        btnClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editorVars.reloadingMap = true;
                for (int X = 0; X <= editorVars.mapRender.MaxX - 1; X++)
                {
                    for (int Y = 0; Y <= editorVars.mapRender.MaxY - 1; Y++)
                    {
                        editorVars.mapRender.Tile[X][Y].Layer[editorVars.layerIndex].Tileset = 0;
                        editorVars.mapRender.Tile[X][Y].Layer[editorVars.layerIndex].X = 0;
                        editorVars.mapRender.Tile[X][Y].Layer[editorVars.layerIndex].Y = 0;
                        editorVars.mapRender.Tile[X][Y].Autotile[editorVars.layerIndex] = 0;
                    }
                }
                editorAssetLoader.cacheTiles();
                editorVars.reloadingMap = false;
            }
        } );

        rbLayer.setText("Layers");
        rbLayer.setBounds(420 - 100, 430, 100, 20);
        rbLayer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                attributeTypes.setEnabled(false);
                tileTypes.setEnabled(true);
                optGround.setEnabled(true);
                optMask.setEnabled(true);
                optMask2.setEnabled(true);
                optFringe.setEnabled(true);
                optFringe2.setEnabled(true);
            }
        });
        rbAttributes.setText("Attributes");
        rbAttributes.setBounds(420 - 100, 446, 100, 20);
        rbAttributes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                attributeTypes.setEnabled(true);
                tileTypes.setEnabled(false);
                optGround.setEnabled(false);
                optMask.setEnabled(false);
                optMask2.setEnabled(false);
                optFringe.setEnabled(false);
                optFringe2.setEnabled(false);
            }
        });

        attributeTypes.setEnabled(false);
        tileTypes.setEnabled(true);
        optGround.setEnabled(true);
        optMask.setEnabled(true);
        optMask2.setEnabled(true);
        optFringe.setEnabled(true);
        optFringe2.setEnabled(true);

        optGround.setText("Ground");
        optGround.setBounds(5, 430, 100, 20);
        optGround.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editorVars.layerIndex = 0;
            }
        });
        optMask.setText("Mask");
        optMask.setBounds(5, 446, 100, 20);
        optMask.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editorVars.layerIndex = 1;
            }
        });
        optMask2.setText("Mask 2");
        optMask2.setBounds(5, 462, 100, 20);
        optMask2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editorVars.layerIndex = 2;
            }
        });
        optFringe.setText("Fringe");
        optFringe.setBounds(5, 478, 100, 20);
        optFringe.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editorVars.layerIndex = 3;
            }
        });
        optFringe2.setText("Fringe 2");
        optFringe2.setBounds(5, 494, 100, 20);
        optFringe2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editorVars.layerIndex = 4;
            }
        });

        ButtonGroup layerGroup = new ButtonGroup();
        layerGroup.add(optGround);
        layerGroup.add(optMask);
        layerGroup.add(optMask2);
        layerGroup.add(optFringe);
        layerGroup.add(optFringe2);

        ButtonGroup optGroup = new ButtonGroup();
        optGroup.add(rbLayer);
        optGroup.add(rbAttributes);

        tileScroll.getViewport().add(editorVars.tileBox);

        toolBox.add(tileScroll);
        toolBox.add(btnCancel);
        toolBox.add(btnSaveMap);
        toolBox.add(scrlTileset);
        toolBox.add(btnFill);
        toolBox.add(btnClear);
        toolBox.add(editorVars.tileSet);
        toolBox.add(optGround);
        toolBox.add(optMask);
        toolBox.add(optMask2);
        toolBox.add(optFringe);
        toolBox.add(optFringe2);
        toolBox.add(rbLayer);
        toolBox.add(rbAttributes);
        toolBox.add(tileTypes);
        toolBox.add(attributeTypes);

        toolBox.add(lblWarp);
        toolBox.add(txtWarp);
        toolBox.add(btnWarp);

        toolBox.add(lblUp);
        toolBox.add(lblDown);
        toolBox.add(lblLeft);
        toolBox.add(lblRight);
        toolBox.add(lblX);
        toolBox.add(lblY);

        toolBox.add(lblWarpX);
        toolBox.add(lblWarpY);
        toolBox.add(txtWarpX);
        toolBox.add(txtWarpY);
        toolBox.add(btnWarpXY);

        toolBox.add(bucketChk);
        toolBox.add(btnUndo);

        toolBox.add(txtUp);
        toolBox.add(txtDown);
        toolBox.add(txtLeft);
        toolBox.add(txtRight);
        toolBox.add(txtX);
        toolBox.add(txtY);

        toolBox.add(btnSaveProperties);

        //toolBox.add(btnMapWorld); //FAILED ATTEMPT AT LAZY WAY OUT OF MAPPING THE WORLD MAP. IGNORE. LEFT HERE AS A REMINDER NOT TO BE LAZY. IT WASTES A LOT OF TIME.

        editorVars.toolBox.add(toolBox);
        editorAssetLoader.load();
        editorLoadData.clearMap(14, 14);
        editorAssetLoader.loadMap();
        editorAssetLoader.cacheTiles();
        editorVars.MinX = 0;
        editorVars.MinY = 0;
        editorVars.MaxX = 14;//editorVars.MAX_MAPX - 1;
        editorVars.MaxY = 14;//editorVars.MAX_MAPY - 1;
        //editorRenderer.initFrame();
        txtUp.setText(Integer.toString(editorVars.mapRender.Up));
        txtDown.setText(Integer.toString(editorVars.mapRender.Down));
        txtLeft.setText(Integer.toString(editorVars.mapRender.Left));
        txtRight.setText(Integer.toString(editorVars.mapRender.Right));
        txtX.setText(Integer.toString(editorVars.mapRender.MaxX));
        txtY.setText(Integer.toString(editorVars.mapRender.MaxY));
        setScreen(new editorGameScreen());
    }

    public static void tileBoxMouseDown(MouseEvent e) {
        Point Pos = e.getPoint();

        // Get Selected Tile
        editorVars.EditorTileWidth = 1;
        editorVars.EditorTileHeight = 1;
        /*editorVars.EditorTileX = ((int) Pos.getX() >> 5);
        editorVars.EditorTileY = ((int) Pos.getY() >> 5);

        editorVars.EditorTileX = ((int) Pos.getX() >> 5) * 32;
        editorVars.EditorTileY = ((int) Pos.getY() >> 5) * 32;*/

        if (editorVars.autoTileIndex == 0) {
            editorVars.EditorTileX = ((int) Pos.getX() >> 5) * 32;
            editorVars.EditorTileY = ((int) Pos.getY() >> 5) * 32;
        } else {
            editorVars.EditorTileX = ((int) Pos.getX() >> 5);
            editorVars.EditorTileY = ((int) Pos.getY() >> 5);
        }

        editorVars.SelectedTile = new Rectangle(32, 32);

        String path = "android/assets/data/tiles/" + editorVars.currentTileSet + ".png";
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(path));
        } catch (IOException r) {
            r.printStackTrace();
        }

        // Selected Tile Shape //
        if (editorVars.autoTileIndex > 0)
        {
            switch (editorVars.autoTileIndex)
            {
                case 1: // autotile
                    editorVars.SelectedTile.setSize(64, 96);
                    break;
                case 2: // fake autotile
                    editorVars.SelectedTile.setSize(32, 32);
                    break;
                case 3: // animated
                    editorVars.SelectedTile.setSize(192, 96);
                    break;
                case 4: // cliff
                    editorVars.SelectedTile.setSize(64, 64);
                    break;
                case 5: // waterfall
                    editorVars.SelectedTile.setSize(64, 96);
                    break;
            }
        }

        Graphics2D graphics = image.createGraphics();
        graphics.setColor(Color.RED);
        graphics.drawRect(((int)Pos.getX() >> 5) * 32, ((int)Pos.getY() >> 5) * 32, (int)editorVars.SelectedTile.getWidth(), (int)editorVars.SelectedTile.getHeight());

        editorVars.tileBox.setIcon(new ImageIcon(image));
        editorVars.tileBox.setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
        editorVars.tileBox.setBounds(0, 0, image.getWidth(), image.getHeight());
        editorVars.tileBox.repaint();

        editorVars.tileBox.invalidate();

        tileScroll.getHorizontalScrollBar().setValue(editorVars.LastScrollHorizontal);
        tileScroll.getVerticalScrollBar().setValue(editorVars.LastScrollVertical);
        tileScroll.getViewport().setViewPosition(editorVars.LastViewport);
    }

    public static void tileBoxMouseDownOld(MouseEvent e) {
        Point Pos = e.getPoint();//e.getLocationOnScreen();
        editorVars.SelectedTile = new Rectangle(32, 32);

        // Get Selected Tile
        editorVars.EditorTileWidth = 1;
        editorVars.EditorTileHeight = 1;
        //editorVars.EditorTileX = 0;
        //editorVars.EditorTileY = 0;
        /*editorVars.EditorTileStartX = 0;
        editorVars.EditorTileStartY = 0;*/

        //editorVars.SelectedTile = new Rectangle();

        if (editorVars.autoTileIndex == 0) {
            editorVars.EditorTileX = ((int) Pos.getX() >> 5) * 32;
            editorVars.EditorTileY = ((int) Pos.getY() >> 5) * 32;
        } else {
            editorVars.EditorTileX = ((int) Pos.getX() >> 5);
            editorVars.EditorTileY = ((int) Pos.getY() >> 5);
        }

        editorVars.SelectedTile.setLocation(((int)Pos.getX() >> 5) * 32, ((int)Pos.getY() >> 5) * 32);
        editorVars.SelectedTile.setSize(32, 32);

        String path = "android/assets/data/tiles/" + editorVars.currentTileSet + ".png";
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(path));
        } catch (IOException r) {
            r.printStackTrace();
        }

        // Selected Tile Shape //
        if (editorVars.autoTileIndex > 0)
        {
            switch (editorVars.autoTileIndex)
            {
                case 1: // autotile
                    editorVars.SelectedTile.setSize(64, 96);
                    break;
                case 2: // fake autotile
                    editorVars.SelectedTile.setSize(32, 32);
                    break;
                case 3: // animated
                    editorVars.SelectedTile.setSize(192, 96);
                    break;
                case 4: // cliff
                    editorVars.SelectedTile.setSize(64, 64);
                    break;
                case 5: // waterfall
                    editorVars.SelectedTile.setSize(64, 96);
                    break;
            }
        }

        Graphics2D graphics = image.createGraphics();
        graphics.setColor(Color.RED);
        graphics.drawRect(((int)Pos.getX() >> 5) * 32, ((int)Pos.getY() >> 5) * 32, (int)editorVars.SelectedTile.getWidth(), (int)editorVars.SelectedTile.getHeight());

        editorVars.tileBox.setIcon(new ImageIcon(image));
        editorVars.tileBox.setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
        editorVars.tileBox.setBounds(0, 0, image.getWidth(), image.getHeight());
        editorVars.tileBox.repaint();

        editorVars.tileBox.invalidate();

        tileScroll.getHorizontalScrollBar().setValue(editorVars.LastScrollHorizontal);
        tileScroll.getVerticalScrollBar().setValue(editorVars.LastScrollVertical);
        tileScroll.getViewport().setViewPosition(editorVars.LastViewport);
    }

    public static void playerWarp(int mapNum, int X, int Y)
    {
        int oldMap;

        // Check if you are out of bounds
        if (X > editorVars.mapRender.MaxX) { X = editorVars.mapRender.MaxX; }
        if (Y > editorVars.mapRender.MaxY) { Y = editorVars.mapRender.MaxY; }

        // Save old map to send erase player data to
        oldMap = editorVars.Player.getMap();

        if (oldMap != mapNum)
        {
            // REASON I DONT NEED THIS YET IS BECAUSE WHEN I USE SENDJOINMAP,
            // I SEND TO ALL NOT JUST THE PLAYERS ON THE MAP.
            // THIS UPDATES ALL THE CLIENTS SO NO NEED TO LEAVE MAP.
            // HOW EVER THIS WILL BE ADDED IN.
            //SendLeaveMap(Index, OldMap)
        }

        editorLoadData.clearMap(editorVars.mapRender.MaxX, editorVars.mapRender.MaxY);

        // Set Player Position //
        editorVars.Player.setMap(mapNum);
        editorVars.Player.setX(X);
        editorVars.Player.setY(Y);

        editorAssetLoader.loadMap();
        editorAssetLoader.cacheTiles();

        txtUp.setText(Integer.toString(editorVars.mapRender.Up));
        txtDown.setText(Integer.toString(editorVars.mapRender.Down));
        txtLeft.setText(Integer.toString(editorVars.mapRender.Left));
        txtRight.setText(Integer.toString(editorVars.mapRender.Right));

        txtX.setText(Integer.toString(editorVars.mapRender.MaxX));
        txtY.setText(Integer.toString(editorVars.mapRender.MaxY));

        //sendData.sendCheckMap(Index, mapNum, ServerVars.mapData[mapNum].Revision);
    }
}
