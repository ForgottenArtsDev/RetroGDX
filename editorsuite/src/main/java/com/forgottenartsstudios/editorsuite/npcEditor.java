package com.forgottenartsstudios.editorsuite;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 * Created by Perfekt on 9/5/2016.
 */
public class npcEditor {

    public static JTextField[] inputFields;
    public static JLabel[] inputLabels;
    public static JList lstNPCs;
    public static JButton btnLoad, btnSave, btnCancel;
    public static JScrollPane scrlList;

    public static void create() {
        editorVars.npcEditor = new JFrame();
        editorVars.npcEditor.setLayout(null);
        editorVars.npcEditor.setBounds(0, 0, 800, 700);
        editorVars.npcEditor.setTitle("NPC Editor");

        inputFields = new JTextField[33 + 1];
        inputLabels = new JLabel[33 + 1];
        for (int i = 0; i <= 33; i++) {
            inputFields[i] = new JTextField();
            inputLabels[i] = new JLabel();
        }

        String[] listData;
        listData = new String[editorVars.maxNPCs + 1];

        for (int i = 1; i <= editorVars.maxNPCs; i++) {
            listData[i] = i + ": " + editorVars.npcs[i].Name;
        }
        lstNPCs = new JList(listData);
        lstNPCs.setBounds(0, 0, 200, 2000);
        scrlList = new JScrollPane();
        scrlList.setBounds(10, 10, 200, 600);
        scrlList.setViewportView(lstNPCs);
        editorVars.npcEditor.add(scrlList);

        inputLabels[0].setText("Name:");
        inputLabels[1].setText("Sprite:");
        inputLabels[2].setText("Range:");
        inputLabels[3].setText("Behaviour:");
        inputLabels[4].setText("SpawnSecs:");
        inputLabels[5].setText("Health:");
        inputLabels[6].setText("Exp:");
        inputLabels[7].setText("STR:");
        inputLabels[8].setText("DEF:");
        inputLabels[9].setText("VIT:");
        inputLabels[10].setText("AGI:");
        inputLabels[11].setText("MAG:");
        inputLabels[12].setText("Shop #:");
        inputLabels[13].setText("Weapon:");
        inputLabels[14].setText("Armor:");
        inputLabels[15].setText("Offhand:");
        inputLabels[16].setText("Helmet:");
        inputLabels[17].setText("Drop 1:");
        inputLabels[18].setText("Drop 2:");
        inputLabels[19].setText("Drop 3:");
        inputLabels[20].setText("Drop 4:");
        inputLabels[21].setText("Drop 5:");
        inputLabels[22].setText("Level:");
        inputLabels[23].setText("Drop Val 1:");
        inputLabels[24].setText("Drop Val 2:");
        inputLabels[25].setText("Drop Val 3:");
        inputLabels[26].setText("Drop Val 4:");
        inputLabels[27].setText("Drop Val 5:");
        inputLabels[28].setText("Drop % 1:");
        inputLabels[29].setText("Drop % 2:");
        inputLabels[30].setText("Drop % 3:");
        inputLabels[31].setText("Drop % 4:");
        inputLabels[32].setText("Drop % 5:");

        for (int i = 0; i <= 32; i++) {
            if (i >= 28 && i <= 32) {
                inputLabels[i].setBounds(540, ((i - 11) * 25) + 5, 100, 20);
                inputFields[i].setBounds(640, ((i - 11) * 25) + 5, 50, 20);
            } else if (i >= 23 && i <= 27) {
                inputLabels[i].setBounds(380, ((i - 6) * 25) + 5, 100, 20);
                inputFields[i].setBounds(480, ((i - 6) * 25) + 5, 50, 20);
            } else if (i >= 17 && i <= 21) {
                inputLabels[i].setBounds(220, (i * 25) + 5, 100, 20);
                inputFields[i].setBounds(320, (i * 25) + 5, 50, 20);
            } else {
                inputLabels[i].setBounds(220, (i * 25) + 5, 100, 20);
                inputFields[i].setBounds(320, (i * 25) + 5, 200, 20);
            }
            editorVars.npcEditor.add(inputLabels[i]);
            editorVars.npcEditor.add(inputFields[i]);
        }

        btnLoad = new JButton();
        btnSave = new JButton();
        btnCancel = new JButton();

        btnLoad.setText("Load NPC");
        btnSave.setText("Save NPC");
        btnCancel.setText("Cancel");

        btnLoad.setBounds(10, 610, 200, 40);
        btnSave.setBounds(553, 610, 100, 40);
        btnCancel.setBounds(673, 610, 100, 40);

        btnLoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editorVars.npcIndex = lstNPCs.getSelectedIndex();

                inputFields[0].setText(editorVars.npcs[editorVars.npcIndex].Name);

                inputFields[1].setText(Integer.toString(editorVars.npcs[editorVars.npcIndex].Sprite));
                inputFields[2].setText(Integer.toString(editorVars.npcs[editorVars.npcIndex].Range));
                inputFields[3].setText(Integer.toString(editorVars.npcs[editorVars.npcIndex].Behaviour));
                inputFields[4].setText(Integer.toString(editorVars.npcs[editorVars.npcIndex].SpawnSecs));
                inputFields[5].setText(Integer.toString(editorVars.npcs[editorVars.npcIndex].Health));
                inputFields[6].setText(Integer.toString(editorVars.npcs[editorVars.npcIndex].Exp));

                inputFields[7].setText(Integer.toString(editorVars.npcs[editorVars.npcIndex].STR));
                inputFields[8].setText(Integer.toString(editorVars.npcs[editorVars.npcIndex].DEF));
                inputFields[9].setText(Integer.toString(editorVars.npcs[editorVars.npcIndex].VIT));
                inputFields[10].setText(Integer.toString(editorVars.npcs[editorVars.npcIndex].AGI));
                inputFields[11].setText(Integer.toString(editorVars.npcs[editorVars.npcIndex].MAG));

                inputFields[12].setText(Integer.toString(editorVars.npcs[editorVars.npcIndex].shopNum));

                inputFields[13].setText(Integer.toString(editorVars.npcs[editorVars.npcIndex].weapon));
                inputFields[14].setText(Integer.toString(editorVars.npcs[editorVars.npcIndex].armor));
                inputFields[15].setText(Integer.toString(editorVars.npcs[editorVars.npcIndex].offhand));
                inputFields[16].setText(Integer.toString(editorVars.npcs[editorVars.npcIndex].helmet));

                inputFields[17].setText(Integer.toString(editorVars.npcs[editorVars.npcIndex].drop1));
                inputFields[18].setText(Integer.toString(editorVars.npcs[editorVars.npcIndex].drop2));
                inputFields[19].setText(Integer.toString(editorVars.npcs[editorVars.npcIndex].drop3));
                inputFields[20].setText(Integer.toString(editorVars.npcs[editorVars.npcIndex].drop4));
                inputFields[21].setText(Integer.toString(editorVars.npcs[editorVars.npcIndex].drop5));

                inputFields[22].setText(Integer.toString(editorVars.npcs[editorVars.npcIndex].Level));

                inputFields[23].setText(Integer.toString(editorVars.npcs[editorVars.npcIndex].dropVal1));
                inputFields[24].setText(Integer.toString(editorVars.npcs[editorVars.npcIndex].dropVal2));
                inputFields[25].setText(Integer.toString(editorVars.npcs[editorVars.npcIndex].dropVal3));
                inputFields[26].setText(Integer.toString(editorVars.npcs[editorVars.npcIndex].dropVal4));
                inputFields[27].setText(Integer.toString(editorVars.npcs[editorVars.npcIndex].dropVal5));

                inputFields[28].setText(Integer.toString(editorVars.npcs[editorVars.npcIndex].dropChance1));
                inputFields[29].setText(Integer.toString(editorVars.npcs[editorVars.npcIndex].dropChance2));
                inputFields[30].setText(Integer.toString(editorVars.npcs[editorVars.npcIndex].dropChance3));
                inputFields[31].setText(Integer.toString(editorVars.npcs[editorVars.npcIndex].dropChance4));
                inputFields[32].setText(Integer.toString(editorVars.npcs[editorVars.npcIndex].dropChance5));
            }
        });
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editorVars.npcs[editorVars.npcIndex].Name = inputFields[0].getText();

                editorVars.npcs[editorVars.npcIndex].Sprite = Integer.parseInt(inputFields[1].getText());
                editorVars.npcs[editorVars.npcIndex].Range = Integer.parseInt(inputFields[2].getText());
                editorVars.npcs[editorVars.npcIndex].Behaviour = Integer.parseInt(inputFields[3].getText());
                editorVars.npcs[editorVars.npcIndex].SpawnSecs = Integer.parseInt(inputFields[4].getText());
                editorVars.npcs[editorVars.npcIndex].Health = Integer.parseInt(inputFields[5].getText());
                editorVars.npcs[editorVars.npcIndex].Exp = Integer.parseInt(inputFields[6].getText());

                editorVars.npcs[editorVars.npcIndex].STR = Integer.parseInt(inputFields[7].getText());
                editorVars.npcs[editorVars.npcIndex].DEF = Integer.parseInt(inputFields[8].getText());
                editorVars.npcs[editorVars.npcIndex].VIT = Integer.parseInt(inputFields[9].getText());
                editorVars.npcs[editorVars.npcIndex].AGI = Integer.parseInt(inputFields[10].getText());
                editorVars.npcs[editorVars.npcIndex].MAG = Integer.parseInt(inputFields[11].getText());

                editorVars.npcs[editorVars.npcIndex].shopNum = Integer.parseInt(inputFields[12].getText());

                editorVars.npcs[editorVars.npcIndex].weapon = Integer.parseInt(inputFields[13].getText());
                editorVars.npcs[editorVars.npcIndex].armor = Integer.parseInt(inputFields[14].getText());
                editorVars.npcs[editorVars.npcIndex].offhand = Integer.parseInt(inputFields[15].getText());
                editorVars.npcs[editorVars.npcIndex].helmet = Integer.parseInt(inputFields[16].getText());

                editorVars.npcs[editorVars.npcIndex].drop1 = Integer.parseInt(inputFields[17].getText());
                editorVars.npcs[editorVars.npcIndex].drop2 = Integer.parseInt(inputFields[18].getText());
                editorVars.npcs[editorVars.npcIndex].drop3 = Integer.parseInt(inputFields[19].getText());
                editorVars.npcs[editorVars.npcIndex].drop4 = Integer.parseInt(inputFields[20].getText());
                editorVars.npcs[editorVars.npcIndex].drop5 = Integer.parseInt(inputFields[21].getText());

                editorVars.npcs[editorVars.npcIndex].Level = Integer.parseInt(inputFields[22].getText());

                editorVars.npcs[editorVars.npcIndex].dropVal1 = Integer.parseInt(inputFields[23].getText());
                editorVars.npcs[editorVars.npcIndex].dropVal2 = Integer.parseInt(inputFields[24].getText());
                editorVars.npcs[editorVars.npcIndex].dropVal3 = Integer.parseInt(inputFields[25].getText());
                editorVars.npcs[editorVars.npcIndex].dropVal4 = Integer.parseInt(inputFields[26].getText());
                editorVars.npcs[editorVars.npcIndex].dropVal5 = Integer.parseInt(inputFields[27].getText());

                editorVars.npcs[editorVars.npcIndex].dropChance1 = Integer.parseInt(inputFields[28].getText());
                editorVars.npcs[editorVars.npcIndex].dropChance2 = Integer.parseInt(inputFields[29].getText());
                editorVars.npcs[editorVars.npcIndex].dropChance3 = Integer.parseInt(inputFields[30].getText());
                editorVars.npcs[editorVars.npcIndex].dropChance4 = Integer.parseInt(inputFields[31].getText());
                editorVars.npcs[editorVars.npcIndex].dropChance5 = Integer.parseInt(inputFields[32].getText());

                editorAssetLoader.saveNPC(editorVars.npcIndex);
            }
        });
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editorVars.npcEditor.setVisible(false);
            }
        });

        editorVars.npcEditor.add(btnLoad);
        editorVars.npcEditor.add(btnSave);
        editorVars.npcEditor.add(btnCancel);
    }
}
