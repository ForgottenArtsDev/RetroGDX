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
 * Created by forgo on 1/8/2017.
 */

public class itemEditor {
    public static JTextField[] inputFields;
    public static JLabel[] inputLabels;
    public static JList lstItems;
    public static JButton btnLoad, btnSave, btnCancel;
    public static JScrollPane scrlList;

    public static void create() {
        editorVars.itemEditor = new JFrame();
        editorVars.itemEditor.setLayout(null);
        editorVars.itemEditor.setBounds(0, 0, 800, 700);
        editorVars.itemEditor.setTitle("Item Editor");

        inputFields = new JTextField[12 + 1];
        inputLabels = new JLabel[12 + 1];
        for (int i = 0; i <= 12; i++) {
            inputFields[i] = new JTextField();
            inputLabels[i] = new JLabel();
        }

        String[] listData;
        listData = new String[editorVars.maxItems + 1];

        for (int i = 1; i <= editorVars.maxItems; i++) {
            listData[i] = i + ": " + editorVars.items[i].Name;
        }
        lstItems = new JList(listData);
        lstItems.setBounds(0, 0, 200, 2000);
        scrlList = new JScrollPane();
        scrlList.setBounds(10, 10, 200, 600);
        scrlList.setViewportView(lstItems);
        editorVars.itemEditor.add(scrlList);

        inputLabels[0].setText("Name:");
        inputLabels[1].setText("Icon:");
        inputLabels[2].setText("Item Type:");
        inputLabels[3].setText("Stackable?");
        inputLabels[4].setText("Level:");
        inputLabels[5].setText("HP:");
        inputLabels[6].setText("MP:");
        inputLabels[7].setText("STR:");
        inputLabels[8].setText("DEF:");
        inputLabels[9].setText("VIT:");
        inputLabels[10].setText("AGI:");
        inputLabels[11].setText("MAG:");
        inputLabels[12].setText("Cost:");

        for (int i = 0; i <= 12; i++) {
            inputLabels[i].setBounds(220, (i * 30) + 10, 100, 20);
            inputFields[i].setBounds(320, (i * 30) + 10, 200, 20);
            editorVars.itemEditor.add(inputLabels[i]);
            editorVars.itemEditor.add(inputFields[i]);
        }

        btnLoad = new JButton();
        btnSave = new JButton();
        btnCancel = new JButton();

        btnLoad.setText("Load Item");
        btnSave.setText("Save Item");
        btnCancel.setText("Cancel");

        btnLoad.setBounds(10, 610, 200, 40);
        btnSave.setBounds(553, 610, 100, 40);
        btnCancel.setBounds(673, 610, 100, 40);

        editorVars.itemEditor.add(btnCancel);
        editorVars.itemEditor.add(btnLoad);
        editorVars.itemEditor.add(btnSave);

        btnLoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editorVars.itemIndex = lstItems.getSelectedIndex();

                inputFields[0].setText(editorVars.items[editorVars.itemIndex].Name);

                inputFields[1].setText(Integer.toString(editorVars.items[editorVars.itemIndex].Icon));
                inputFields[2].setText(Integer.toString(editorVars.items[editorVars.itemIndex].itemType));
                inputFields[3].setText(Integer.toString(editorVars.items[editorVars.itemIndex].isStackable));
                inputFields[4].setText(Integer.toString(editorVars.items[editorVars.itemIndex].LVL));
                inputFields[5].setText(Integer.toString(editorVars.items[editorVars.itemIndex].HP));
                inputFields[6].setText(Integer.toString(editorVars.items[editorVars.itemIndex].MP));

                inputFields[7].setText(Integer.toString(editorVars.items[editorVars.itemIndex].STR));
                inputFields[8].setText(Integer.toString(editorVars.items[editorVars.itemIndex].DEF));
                inputFields[9].setText(Integer.toString(editorVars.items[editorVars.itemIndex].VIT));
                inputFields[10].setText(Integer.toString(editorVars.items[editorVars.itemIndex].AGI));
                inputFields[11].setText(Integer.toString(editorVars.items[editorVars.itemIndex].MAG));

                inputFields[12].setText(Integer.toString(editorVars.items[editorVars.itemIndex].Cost));
            }
        });
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editorVars.items[editorVars.itemIndex].Name = inputFields[0].getText();

                editorVars.items[editorVars.itemIndex].Icon = Integer.parseInt(inputFields[1].getText());
                editorVars.items[editorVars.itemIndex].itemType = Integer.parseInt(inputFields[2].getText());
                editorVars.items[editorVars.itemIndex].isStackable = Integer.parseInt(inputFields[3].getText());
                editorVars.items[editorVars.itemIndex].LVL = Integer.parseInt(inputFields[4].getText());
                editorVars.items[editorVars.itemIndex].HP = Integer.parseInt(inputFields[5].getText());
                editorVars.items[editorVars.itemIndex].MP = Integer.parseInt(inputFields[6].getText());

                editorVars.items[editorVars.itemIndex].STR = Integer.parseInt(inputFields[7].getText());
                editorVars.items[editorVars.itemIndex].DEF = Integer.parseInt(inputFields[8].getText());
                editorVars.items[editorVars.itemIndex].VIT = Integer.parseInt(inputFields[9].getText());
                editorVars.items[editorVars.itemIndex].AGI = Integer.parseInt(inputFields[10].getText());
                editorVars.items[editorVars.itemIndex].MAG = Integer.parseInt(inputFields[11].getText());

                editorVars.items[editorVars.itemIndex].Cost = Integer.parseInt(inputFields[12].getText());

                editorAssetLoader.saveItem(editorVars.itemIndex);
            }
        });
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editorVars.itemEditor.setVisible(false);
            }
        });
    }
}
