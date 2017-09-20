package com.ronglexie.licenseclient;

import javax.swing.*;

/**
 * @author ronglexie
 * @version 2017-8-28
 */
public class LicenceCreatorMain {

    public static void main (String args[]){
        JFrame jFrame = new JFrame("LicenseCreator");
        jFrame.setContentPane(new License().Licence);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setSize(320, 220);
        /*int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        jFrame.setLocation((screenWidth - 200)/2, (screenHeight-200)/2);*/
        jFrame.setLocationRelativeTo(null);
        jFrame.setResizable(false);
        jFrame.setVisible(true);
    }
}
