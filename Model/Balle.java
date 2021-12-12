package Model;

import Vue.Render;

public class Balle extends Entite {
	
	private int speed;
	private double degats;
	private double coolDown;
	private Hitbox hit;
	/**
	 * 1 = gauche
	 * 2 = droite
	 * 3 = haut
	 * 4 = bas
	 */
	private double direction;
	private float distance;
	
	public Balle(int width, int heigth, double x, double y, double direction) {
		super(width, heigth, x, y);
		this.setX(x);
		this.setY(y);
		this.setDirection(direction);
		hit = new Hitbox(this);
	}
	
	public float getDistance() {
		return distance;
	}

	public void setDistance(float distance) {
		this.distance = distance;
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

	public double getDirection() {
		return direction;
	}

	public void setDirection(double direction) {
		this.direction = direction;
	}

	public double getCoolDown() {
		return coolDown;
	}

	public void setCoolDown(double coolDown) {
		this.coolDown = coolDown;
	}

	public Hitbox getHit() {
		return hit;
	}
	
	
	
}
