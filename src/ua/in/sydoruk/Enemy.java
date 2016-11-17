package ua.in.sydoruk;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Random;

class Enemy {
    Image activeEnemyImg = null;
    int x;
    int y;
    private Random rand = new Random();
    private int width;
    private int height;
    private int velocity;
    private Road road;

    {
        int randomImage = 1 + rand.nextInt(3);
        URL enemy1 = getClass().getClassLoader().getResource("res/enemy1.png");
        URL enemy2 = getClass().getClassLoader().getResource("res/enemy2.png");
        URL enemy3 = getClass().getClassLoader().getResource("res/enemy3.png");

        if (randomImage == 1) {
            activeEnemyImg = new ImageIcon(enemy1).getImage();
        } else if (randomImage == 2) {
            activeEnemyImg = new ImageIcon(enemy2).getImage();
        } else if (randomImage == 3) {
            activeEnemyImg = new ImageIcon(enemy3).getImage();
        }
    }


    Enemy(int x, int y, int v, Road road) {
        this.width = activeEnemyImg.getWidth(null);
        this.height = activeEnemyImg.getHeight(null);
        this.x = x;
        this.y = y;
        this.velocity = v;
        this.road = road;
    }

    void move() {
        y = y + road.player.velocity - velocity;
    }

    Rectangle getRect() {
        return new Rectangle(x, y, width, height);

    }

}
