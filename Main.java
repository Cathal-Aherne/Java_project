package project2;

import javax.swing.*;

/**
 * Cathal ahernes project for 4/24/2017
 */
public class Main {

    public static void main(String[] args) {

        JFrame abuse = new JFrame("Abusive Text Detector");
        abuse.setContentPane(new GUI().panelMain);
        abuse.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        abuse.pack();
        abuse.setVisible(true);

    }

}