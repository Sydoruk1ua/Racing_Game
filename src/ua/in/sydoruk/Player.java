package ua.in.sydoruk;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

class Player {

	static final int MAX_V = 25;
	private static final int MAX_LEFT = 234;
	private static final int MAX_RIGHT = 750;
	
	private Image img_c = new ImageIcon(getClass().getClassLoader().getResource("res/player.png")).getImage();
	private Image img_l = new ImageIcon(getClass().getClassLoader().getResource("res/player_left.png")).getImage();
	private Image img_r = new ImageIcon(getClass().getClassLoader().getResource("res/player_right.png")).getImage();
	
	Image activePlayerImg = img_c;
	private int width = activePlayerImg.getWidth(null);
	private int height = activePlayerImg.getHeight(null);
	
	int velocity = 0;
	private int acceleration = 0;
	int way = 0;

	int x = 600;
	int y = 520;
	private int dy = 0;

	int layer1 = 0;
	int layer2 = -710;

	Rectangle getRect() {
		return new Rectangle(x, y, width, height);

	}

	void move() {
		way += velocity;
		velocity += acceleration;
		
		if (velocity <= 0) {
			velocity = 0;
		} else if (velocity >= MAX_V) {
			velocity = MAX_V;
		}
		x -= dy;
		if (x <= MAX_LEFT) {
			x = MAX_LEFT;
		} else if (x >= MAX_RIGHT) {
			x = MAX_RIGHT;
		}

		if (layer2 + velocity >= 0) {
			layer1 = 0;
			layer2 = -710;
		} else {
			layer1 += velocity;
			layer2 += velocity;

		}
	}

	void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_RIGHT) {
			
			dy = -10;
			activePlayerImg = img_r;
		} else if (key == KeyEvent.VK_LEFT) {
			
			dy = 10;
			activePlayerImg = img_l;
		} else if (key == KeyEvent.VK_UP) {
			acceleration = 1;
			
		} else if (key == KeyEvent.VK_DOWN) {
			acceleration = -1;
			
		} 
	
	}

	void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_LEFT) {
			dy = 0;
			activePlayerImg = img_c;
		}
		if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_UP) {
			
			acceleration = 0;
			
		}
	}
}
