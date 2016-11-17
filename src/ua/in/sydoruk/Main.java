package ua.in.sydoruk;


import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame f = new JFrame("Racing_Game!");
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setSize(1040, 680);
        f.add(new Road());
        f.setVisible(true);
    }

}
