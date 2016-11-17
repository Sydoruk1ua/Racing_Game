package ua.in.sydoruk;

import javax.swing.*;
import java.awt.*;

class Score {
	Image coinImg = new ImageIcon(getClass().getClassLoader().getResource(
			"res/coin.png")).getImage();
	private int width = coinImg.getWidth(null);
	private int height = coinImg.getHeight(null);
	int x = 0;
	int y = 0;
	private Road road;

	Score(int x, int y, Road road) {

		this.x = x;
		this.y = y;
		this.road = road;

	}

	void move() {
		y = y + road.player.velocity;
	}

	Rectangle getRect() {
		return new Rectangle(x, y, width, height);

	}
}
