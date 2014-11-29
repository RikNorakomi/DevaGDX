package com.norakomi.template;

public class Background {
	
	public static int bgX;
	public static int bgY;
	public int speedX;
	
	public Background(int x, int y){
		bgX = 0; //x;
		bgY = 0; //y;
		speedX = 0;
	}
	
	public void update() {
	}

	public int getBgX() {
		return bgX;
	}

	public int getBgY() {
		return bgY;
	}

	public int getSpeedX() {
		return speedX;
	}

	public void setBgX(int bgX) {
		this.bgX = bgX;
	}

	public void setBgY(int bgY) {
		this.bgY = bgY;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	
	
	
}
