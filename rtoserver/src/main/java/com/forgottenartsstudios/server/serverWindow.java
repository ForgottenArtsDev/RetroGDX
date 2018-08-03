package com.forgottenartsstudios.server;

import com.forgottenartsstudios.helpers.ServerVars;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

/**
 * Created by forgo on 10/6/2017.
 */

public class serverWindow {
    public static JTextArea svrMonitor = new JTextArea();
    static JTextField svrInput = new JTextField();

    public static void create() {
        ServerVars.serverWindow = new JFrame();
        ServerVars.serverWindow.setLayout(null);
        ServerVars.serverWindow.setBounds(0, 0, 500, 200);
        ServerVars.serverWindow.setTitle("RetroGDX Demo Server");
        ServerVars.serverWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        svrMonitor.setBounds(0, 0, 350, 100);
        svrMonitor.setLineWrap(true);
        svrMonitor.setWrapStyleWord(true);
        svrMonitor.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        svrMonitor.setEditable(false);
        //ServerVars.serverWindow.add(svrMonitor);
        JScrollPane scrlMonitor = new JScrollPane(svrMonitor);
        scrlMonitor.setBounds(10, 10, 350, 100);
        ServerVars.serverWindow.add(scrlMonitor);

        svrInput.setBounds(10, 120, 350, 30);
        svrInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                svrMonitor.append(svrInput.getText() + "\n");
                svrInput.setText("");
            }
        });
        ServerVars.serverWindow.add(svrInput);
    }
}
