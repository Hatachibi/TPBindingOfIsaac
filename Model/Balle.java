package Model;

import Vue.Render;

public class Balle extends Entite {
	
	private int speed;
	private double degats;
	private double coolDown;
	/**
	 * 1 = gauche
	 * 2 = droite
	 * 3 = haut
	 * 4 = bas
	 */
	private int direction;
	
	public Balle(int width, int heigth, double x, double y, int direction) {
		super(width, heigth, x, y);
		this.setX(x);
		this.setY(y);
		this.setDirection(direction);
	}
	
	public void drawBalle() {
		Render.getInstance().drawPoint((float)this.getX(), (float)this.getY(), 80);
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public double getDegats() {
		return degats;
	}

	public void setDegats(double degats) {
		this.degats = degats;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public double getCoolDown() {
		return coolDown;
	}

	public void setCoolDown(double coolDown) {
		this.coolDown = coolDown;
	}
	
	
	
}
