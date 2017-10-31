package com.forgottenartsstudios.editorsuite;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * Created by Perfekt on 9/6/2016.
 */
public class itemSpawn {

    public static JTextField itemNum;
    public static JTextField itemVal;
    public static JLabel[] inputLabels;
    public static JButton btnOK, btnCancel;

    public static void create() {
        editorVars.itemSpawn = new JFrame();
        editorVars.itemSpawn.setLayout(null);
        editorVars.itemSpawn.setBounds(0, 0, 200, 180);
        editorVars.itemSpawn.setTitle("Item Spawn");

        itemNum = new JTextField();
        itemVal = new JTextField();

        btnOK = new JButton();
        btnCancel = new JButton();

        inputLabels = new JLabel[1 + 1];
        for (int i = 0; i <= 1; i ++) {
            inputLabels[i] = new JLabel();
        }

        btnOK.setText("OK");
        btnCancel.setText("Cancel");
        btnOK.setBounds(10, 100, 80, 30);
        btnCancel.setBounds(93, 100, 80, 30);

        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editorVars.selectedTileType = editorVars.TILE_TYPE_ITEM;
                editorVars.selectedData1 = Integer.parseInt(itemNum.getText()); // Item Num
                editorVars.selectedData2 = Integer.parseInt(itemVal.getText()); // Item Value
                editorVars.itemSpawn.setVisible(false);
            }
        });
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editorVars.itemSpawn.setVisible(false);
            }
        });

        inputLabels[0].setText("Item Num:");
        inputLabels[1].setText("Item Val:");

        for (int i = 0; i <= 1; i++) {
            inputLabels[i].setBounds(10, (i * 30) + 10, 100, 20);
            editorVars.itemSpawn.add(inputLabels[i]);
        }
        itemNum.setBounds(110, 10, 50, 20);
        itemVal.setBounds(110, 40, 50, 20);

        editorVars.itemSpawn.add(itemNum);
        editorVars.itemSpawn.add(itemVal);
        editorVars.itemSpawn.add(btnOK);
        editorVars.itemSpawn.add(btnCancel);
    }
}