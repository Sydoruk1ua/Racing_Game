package ua.in.sydoruk;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

class Road extends JPanel implements ActionListener, Runnable {

    private static int score = 0;
    private static int life = 30;
    private List<Enemy> enemies = new ArrayList<>();
    private List<Score> coins = new ArrayList<>();
    Player player = new Player();
    private Life lifeImg = new Life();
    private Image roadImg = new ImageIcon(getClass().getClassLoader().getResource("res/road.png")).getImage();

     Road() {
        Timer mainTimer = new Timer(20, this);
        mainTimer.start();
        Thread enemiesFactory = new Thread(this);
        enemiesFactory.start();
        Thread coinsFactory = new Thread(() -> {
            while (true) {
                Random rand = new Random();
                try {
                    Thread.sleep(rand.nextInt(500));
                    coins.add(new Score((234 + rand.nextInt(500 + 1)), -710,
                            Road.this));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        });
        coinsFactory.start();

        addKeyListener(new MyKeyAdapter());
        setFocusable(true);
    }

    public void paint(Graphics graphics) {
        graphics.drawImage(roadImg, 0, player.layer1, null);
        graphics.drawImage(roadImg, 0, player.layer2, null);
        graphics.drawImage(player.activePlayerImg, player.x, player.y, null);
        if (life == 30) {
            graphics.drawImage(lifeImg.heart3Img, 15, 13, null);
        } else if (life >= 15 && life <= 25) {
            graphics.drawImage(lifeImg.heart2Img, 15, 13, null);
        } else if (life >= 1 && life <= 14) {
            graphics.drawImage(lifeImg.heart1Img, 15, 13, null);
        }

        double v = (100 / Player.MAX_V) * player.velocity;
        graphics.setColor(Color.RED);
        Font font = new Font("Arial", Font.BOLD, 20);
        graphics.setFont(font);
        graphics.drawString("Швидкість: " + v + " км/год", 10, 625);
        graphics.drawString("Відстань: " + player.way / 1000 + " км", 830, 625);
        graphics.setColor(Color.YELLOW);
        Font fontForScore = new Font("Arial", Font.BOLD, 30);
        graphics.setFont(fontForScore);
        graphics.drawString("Score: " + score, 830, 40);
        Iterator<Enemy> i = enemies.iterator();
        while (i.hasNext()) {
            Enemy e = i.next();
            if (e.y >= 1300 || e.y <= -1300) {
                i.remove();
            } else {
                e.move();
                graphics.drawImage(e.activeEnemyImg, e.x, e.y, null);

            }

        }
        Iterator<Score> j = coins.iterator();
        while (j.hasNext()) {
            Score e = j.next();
            if (e.y >= 1300 || e.y <= -1300) {
                j.remove();
            } else {
                e.move();
                graphics.drawImage(e.coinImg, e.x, e.y, null);

            }

        }
    }

    public void actionPerformed(ActionEvent e) {
        player.move();
        repaint();
        testCollisionWithEnemies();
        testWin();
        testCollisionWithCoins();
    }

    private void testWin() {
        if (player.way <= 50000 && score >= 500) {
            JOptionPane.showMessageDialog(null, "Вітаємо!!! Ви виграли!");
            System.exit(0);
        } else if (player.way >= 50000 && score < 500) {
            JOptionPane.showMessageDialog(null, "Ви програли!");
            System.exit(0);
        }

    }

    private void testCollisionWithEnemies() {
        enemies.stream().filter(e -> player.getRect().intersects(e.getRect())).forEachOrdered(e -> {

            life--;
            if (life <= 0) {
                JOptionPane.showMessageDialog(null, "Ви програли!");
                System.exit(1);
            }
        });

    }

    private void testCollisionWithCoins() {
        Iterator<Score> i = coins.iterator();
        while (i.hasNext()) {
            Score e = i.next();
            if (player.getRect().intersects(e.getRect())) {
                i.remove();
                score += 5;

            }

        }

    }

    @Override
    public void run() {
        while (true) {
            Random rand = new Random();
            try {
                Thread.sleep(rand.nextInt(2000));
                enemies.add(new Enemy((234 + rand.nextInt(500 + 1)), -710, rand
                        .nextInt(22), this));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private class MyKeyAdapter extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            player.keyPressed(e);

        }

        public void keyReleased(KeyEvent e) {
            player.keyReleased(e);
        }
    }
}
