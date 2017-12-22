package com.forgottenartsstudios.editorsuite;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 * Created by forgo on 12/16/2017.
 */

public class spellEditor {
    public static JTextField[] inputFields;
    public static JLabel[] inputLabels;
    public static JLabel typeLabel;
    public static JList lstSpells;
    public static JButton btnLoad, btnSave, btnCancel;
    public static JScrollPane scrlList;
    public static JComboBox<String> types = new JComboBox<>();
    public static JCheckBox[] useStat = new JCheckBox[5 + 1];

    public static void create() {
        editorVars.spellEditor = new JFrame();
        editorVars.spellEditor.setLayout(null);
        editorVars.spellEditor.setBounds(0, 0, 800, 700);
        editorVars.spellEditor.setTitle("Spell Editor");

        inputFields = new JTextField[10 + 1];
        inputLabels = new JLabel[10 + 1];
        for (int i = 0; i <= 10; i++) {
            inputFields[i] = new JTextField();
            inputLabels[i] = new JLabel();
        }

        String[] listData;
        listData = new String[editorVars.maxSpells + 1];

        for (int i = 1; i <= editorVars.maxSpells; i++) {
            listData[i] = i + ": " + editorVars.spells[i].Name;
        }
        lstSpells = new JList(listData);
        lstSpells.setBounds(0, 0, 200, 2000);
        scrlList = new JScrollPane();
        scrlList.setBounds(10, 10, 200, 600);
        scrlList.setViewportView(lstSpells);
        editorVars.spellEditor.add(scrlList);

        inputLabels[0].setText("Name:");
        inputLabels[1].setText("Type:");
        inputLabels[2].setText("Icon:");
        inputLabels[3].setText("Level Req:");
        inputLabels[4].setText("Class Req:");
        inputLabels[5].setText("Animation:");
        inputLabels[6].setText("Animation Speed:");
        inputLabels[7].setText("Cast Time:");
        inputLabels[8].setText("Cool Down:");
        inputLabels[9].setText("MP Cost:");
        inputLabels[10].setText("Damage/Heal Amount:");

        for (int i = 0; i <= 10; i++) {
            inputLabels[i].setBounds(220, (i * 25) + 5, 100, 20);
            inputFields[i].setBounds(320, (i * 25) + 5, 200, 20);
            editorVars.spellEditor.add(inputLabels[i]);
            editorVars.spellEditor.add(inputFields[i]);
        }

        btnLoad = new JButton();
        btnSave = new JButton();
        btnCancel = new JButton();

        btnLoad.setText("Load Spell");
        btnSave.setText("Save Spell");
        btnCancel.setText("Cancel");

        btnLoad.setBounds(10, 610, 200, 40);
        btnSave.setBounds(553, 610, 100, 40);
        btnCancel.setBounds(673, 610, 100, 40);

        btnLoad.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               editorVars.spellIndex = lstSpells.getSelectedIndex();

               inputFields[0].setText(editorVars.spells[editorVars.spellIndex].Name);
               inputFields[1].setText(Integer.toString(editorVars.spells[editorVars.spellIndex].Type));
               inputFields[2].setText(Integer.toString(editorVars.spells[editorVars.spellIndex].Icon));

               inputFields[3].setText(Integer.toString(editorVars.spells[editorVars.spellIndex].LevelReq));
               inputFields[4].setText(Integer.toString(editorVars.spells[editorVars.spellIndex].ClassReq));

               inputFields[5].setText(Integer.toString(editorVars.spells[editorVars.spellIndex].Animation));
               inputFields[6].setText(Integer.toString(editorVars.spells[editorVars.spellIndex].AnimSpeed));

               inputFields[7].setText(Integer.toString(editorVars.spells[editorVars.spellIndex].CastTime));
               inputFields[8].setText(Integer.toString(editorVars.spells[editorVars.spellIndex].CoolDown));

               inputFields[9].setText(Integer.toString(editorVars.spells[editorVars.spellIndex].MPCost));
               inputFields[10].setText(Integer.toString(editorVars.spells[editorVars.spellIndex].DmgHealAmt));
           }
        });
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editorVars.spells[editorVars.spellIndex].Name = inputFields[0].getText();
                editorVars.spells[editorVars.spellIndex].Type = Integer.parseInt(inputFields[1].getText());
                editorVars.spells[editorVars.spellIndex].Icon = Integer.parseInt(inputFields[2].getText());

                editorVars.spells[editorVars.spellIndex].LevelReq = Integer.parseInt(inputFields[3].getText());
                editorVars.spells[editorVars.spellIndex].ClassReq = Integer.parseInt(inputFields[4].getText());

                editorVars.spells[editorVars.spellIndex].Animation = Integer.parseInt(inputFields[5].getText());
                editorVars.spells[editorVars.spellIndex].AnimSpeed = Integer.parseInt(inputFields[6].getText());

                editorVars.spells[editorVars.spellIndex].CastTime = Integer.parseInt(inputFields[7].getText());
                editorVars.spells[editorVars.spellIndex].CoolDown = Integer.parseInt(inputFields[8].getText());

                editorVars.spells[editorVars.spellIndex].MPCost = Integer.parseInt(inputFields[9].getText());
                editorVars.spells[editorVars.spellIndex].DmgHealAmt = Integer.parseInt(inputFields[10].getText());

                editorAssetLoader.saveSpell(editorVars.spellIndex);
            }
        });
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editorVars.spellEditor.setVisible(false);
            }
        });

        editorVars.spellEditor.add(btnLoad);
        editorVars.spellEditor.add(btnSave);
        editorVars.spellEditor.add(btnCancel);
    }
}
