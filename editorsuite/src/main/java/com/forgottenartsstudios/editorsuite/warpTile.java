package com.forgottenartsstudios.editorsuite;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * Created by forgo on 2/20/2017.
 */

public class warpTile {
    public static JTextField warpMap, warpX, warpY;
    public static JLabel[] inputLabels;
    public static JButton btnOK, btnCancel;

    public static void create() {
        editorVars.warpTile = new JFrame();
        editorVars.warpTile.setLayout(null);
        editorVars.warpTile.setBounds(0, 0, 200, 180);
        editorVars.warpTile.setTitle("Warp Tile");

        warpMap = new JTextField();
        warpX = new JTextField();
        warpY = new JTextField();

        inputLabels = new JLabel[2 + 1];
        for (int i = 0; i <= 2; i++) {
            inputLabels[i] = new JLabel();
        }

        warpMap.setText("0");
        warpMap.setBounds(60, 5, 40, 30);
        warpX.setText("0");
        warpX.setBounds(60, 35, 40, 30);
        warpY.setText("0");
        warpY.setBounds(60, 65, 40, 30);

        inputLabels[0].setText("Map #:");
        inputLabels[0].setBounds(5, 5, 50, 20);
        inputLabels[1].setText("X:");
        inputLabels[1].setBounds(5, 35, 30, 20);
        inputLabels[2].setText("Y:");
        inputLabels[2].setBounds(5, 65, 30, 20);

        btnOK = new JButton();
        btnCancel = new JButton();

        btnOK.setText("OK");
        btnOK.setBounds(5, 100, 80, 30);
        btnCancel.setText("Cancel");
        btnCancel.setBounds(95, 100, 80, 30);

        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editorVars.selectedTileType = editorVars.TILE_TYPE_WARP;
                editorVars.selectedData1 = Integer.parseInt(warpMap.getText()); // MapNum
                editorVars.selectedData2 = Integer.parseInt(warpX.getText()); // X
                editorVars.selectedData3 = Integer.parseInt(warpY.getText()); // Y
                editorVars.warpTile.setVisible(false);
            }
        });
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editorVars.warpTile.setVisible(false);
            }
        });

        editorVars.warpTile.add(warpMap);
        editorVars.warpTile.add(warpX);
        editorVars.warpTile.add(warpY);

        editorVars.warpTile.add(inputLabels[0]);
        editorVars.warpTile.add(inputLabels[1]);
        editorVars.warpTile.add(inputLabels[2]);

        editorVars.warpTile.add(btnOK);
        editorVars.warpTile.add(btnCancel);
    }
}
