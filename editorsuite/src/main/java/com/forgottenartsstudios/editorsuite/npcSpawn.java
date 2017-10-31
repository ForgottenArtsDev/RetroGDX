package com.forgottenartsstudios.editorsuite;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * Created by Perfekt on 9/6/2016.
 */
public class npcSpawn {

    public static JTextField npcNum;
    public static JTextField npcDir;
    public static JCheckBox npcMove;
    public static JLabel[] inputLabels;
    public static JButton btnOK, btnCancel;

    public static void create() {
        editorVars.npcSpawn = new JFrame();
        editorVars.npcSpawn.setLayout(null);
        editorVars.npcSpawn.setBounds(0, 0, 200, 180);
        editorVars.npcSpawn.setTitle("NPC Spawn");

        npcNum = new JTextField();
        npcDir = new JTextField();
        npcMove = new JCheckBox();

        btnOK = new JButton();
        btnCancel = new JButton();

        inputLabels = new JLabel[2 + 1];
        for (int i = 0; i <= 2; i ++) {
            inputLabels[i] = new JLabel();
        }

        btnOK.setText("OK");
        btnCancel.setText("Cancel");
        btnOK.setBounds(10, 100, 80, 30);
        btnCancel.setBounds(93, 100, 80, 30);

        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editorVars.selectedTileType = editorVars.TILE_TYPE_NPCSPAWN;
                editorVars.selectedData1 = Integer.parseInt(npcNum.getText()); // NPC Num
                editorVars.selectedData2 = Integer.parseInt(npcDir.getText()); // NPC Dir
                if (npcMove.isSelected()) {
                    editorVars.selectedData3 = 1; // Can Move
                } else {
                    editorVars.selectedData3 = 0; // Can't Move
                }
                editorVars.npcSpawn.setVisible(false);
            }
        });
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editorVars.npcSpawn.setVisible(false);
            }
        });

        inputLabels[0].setText("NPC Num:");
        inputLabels[1].setText("NPC Dir:");
        inputLabels[2].setText("Can Move:");

        for (int i = 0; i <= 2; i++) {
            inputLabels[i].setBounds(10, (i * 30) + 10, 100, 20);
            editorVars.npcSpawn.add(inputLabels[i]);
        }
        npcNum.setBounds(110, 10, 50, 20);
        npcDir.setBounds(110, 40, 50, 20);
        npcMove.setBounds(110, 70, 20, 20);

        editorVars.npcSpawn.add(npcNum);
        editorVars.npcSpawn.add(npcDir);
        editorVars.npcSpawn.add(npcMove);
        editorVars.npcSpawn.add(btnOK);
        editorVars.npcSpawn.add(btnCancel);
    }
}
