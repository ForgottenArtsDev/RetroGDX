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
 * Created by Perfekt on 5/22/2017.
 */

public class shopEditor {

    public static JTextField[] inputFields;
    public static JLabel[] inputLabels;
    public static JList lstShops, lstInShop;
    public static JButton btnLoad, btnSave, btnCancel, btnAddItem;
    public static JScrollPane scrlList, scrlShop;
    public static String[] shopData = new String[20 + 1];

    public static void create() {
        editorVars.shopEditor = new JFrame();
        editorVars.shopEditor.setLayout(null);
        editorVars.shopEditor.setBounds(0, 0, 800, 700);
        editorVars.shopEditor.setTitle("Shop Editor");

        inputFields = new JTextField[6 + 1];
        inputLabels = new JLabel[6 + 1];
        for (int i = 0; i <= 6; i++) {
            inputFields[i] = new JTextField();
            inputLabels[i] = new JLabel();
        }

        String[] listData;
        listData = new String[editorVars.maxShops + 1];

        for (int i = 1; i <= editorVars.maxShops; i++) {
            listData[i] = i + ": " + editorVars.shops[i].Name;
        }
        for (int i = 1; i <= 20; i++) {
            shopData[i] = i + ": Empty Slot";
        }
        lstShops = new JList(listData);
        lstShops.setBounds(0, 0, 200, 2000);
        lstInShop = new JList(shopData);
        lstInShop.setBounds(0, 0, 500, 500);
        scrlList = new JScrollPane();
        scrlList.setBounds(10, 10, 200, 600);
        scrlList.setViewportView(lstShops);
        scrlShop = new JScrollPane();
        scrlShop.setBounds(220, 200, 500, 400);
        scrlShop.setViewportView(lstInShop);
        editorVars.shopEditor.add(scrlList);
        editorVars.shopEditor.add(scrlShop);

        inputLabels[0].setText("Name:");
        inputLabels[1].setText("Welcome Msg:");
        inputLabels[2].setText("Goodbye Msg:");
        inputLabels[3].setText("Sales Tax:");
        inputLabels[4].setText("Can Repair?");
        inputLabels[5].setText("Add Item:");

        for (int i = 0; i <= 5; i++) {
            inputLabels[i].setBounds(220, (i * 30) + 10, 100, 20);
            if (i == 5) {
                inputFields[i].setBounds(320, (i * 30) + 10, 80, 20);
            } else {
                inputFields[i].setBounds(320, (i * 30) + 10, 200, 20);
            }
            editorVars.shopEditor.add(inputLabels[i]);
            editorVars.shopEditor.add(inputFields[i]);
        }

        inputFields[6].setBounds(410, 160, 80, 20);
        editorVars.shopEditor.add(inputFields[6]);

        btnLoad = new JButton();
        btnSave = new JButton();
        btnCancel = new JButton();
        btnAddItem = new JButton();

        btnLoad.setText("Load Shop");
        btnSave.setText("Save Shop");
        btnCancel.setText("Cancel");
        btnAddItem.setText("Add Item");

        btnLoad.setBounds(10, 610, 200, 40);
        btnSave.setBounds(553, 610, 100, 40);
        btnCancel.setBounds(673, 610, 100, 40);
        btnAddItem.setBounds(500, 160, 100, 20);

        editorVars.shopEditor.add(btnCancel);
        editorVars.shopEditor.add(btnLoad);
        editorVars.shopEditor.add(btnSave);
        editorVars.shopEditor.add(btnAddItem);

        btnLoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editorVars.shopIndex = lstShops.getSelectedIndex();

                inputFields[0].setText(editorVars.shops[editorVars.shopIndex].Name);

                inputFields[1].setText(editorVars.shops[editorVars.shopIndex].welcomeMsg);
                inputFields[2].setText(editorVars.shops[editorVars.shopIndex].goodbyeMsg);

                inputFields[3].setText(Integer.toString(editorVars.shops[editorVars.shopIndex].salesTax));

                if (editorVars.shops[editorVars.shopIndex].canRepair) {
                    inputFields[4].setText("1");
                } else {
                    inputFields[4].setText("0");
                }

                for (int i = 1; i <= 20; i++) {
                    if (editorVars.shops[editorVars.shopIndex].itemNum[i] > 0) {
                        shopData[i] = i + ": " + editorVars.items[editorVars.shops[editorVars.shopIndex].itemNum[i]].Name;
                    } else {
                        shopData[i] = i + ": Empty Slot";
                    }
                }

                lstInShop = new JList(shopData);
                editorVars.shopEditor.add(lstInShop);
            }
        });
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editorVars.shops[editorVars.shopIndex].Name = inputFields[0].getText();

                editorVars.shops[editorVars.shopIndex].welcomeMsg = inputFields[1].getText();
                editorVars.shops[editorVars.shopIndex].goodbyeMsg = inputFields[2].getText();

                editorVars.shops[editorVars.shopIndex].salesTax = Integer.parseInt(inputFields[3].getText());

                if (inputFields[4].getText() == "1") {
                    editorVars.shops[editorVars.shopIndex].canRepair = true;
                } else {
                    editorVars.shops[editorVars.shopIndex].canRepair = false;
                }

                editorAssetLoader.saveShop(editorVars.shopIndex);
            }
        });

        btnAddItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 1; i <= 20; i++) {
                    if (editorVars.shops[editorVars.shopIndex].itemNum[i] == 0) {
                        editorVars.shops[editorVars.shopIndex].itemNum[i] = Integer.parseInt(inputFields[5].getText());
                        editorVars.shops[editorVars.shopIndex].itemVal[i] = Integer.parseInt(inputFields[6].getText());
                        shopData[i] = i + ": " + editorVars.items[editorVars.shops[editorVars.shopIndex].itemNum[i]].Name;
                        break;
                    }
                }
                for (int i = 1; i <= 20; i++) {
                    if (editorVars.shops[editorVars.shopIndex].itemNum[i] > 0) {
                        shopData[i] = i + ": " + editorVars.items[editorVars.shops[editorVars.shopIndex].itemNum[i]].Name;
                    } else {
                        shopData[i] = i + ": Empty Slot";
                    }
                }

                lstInShop = new JList(shopData);
                editorVars.shopEditor.add(lstInShop);
            }
        });

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editorVars.shopEditor.setVisible(false);
            }
        });
    }
}
