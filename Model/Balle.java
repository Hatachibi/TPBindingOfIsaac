package Model;

import Vue.Render;

public class Balle extends Entite{
	
	private int speed;
	private boolean isShoot;
	private double coolDown;
	private double degats;
	
	public Balle(int width, int heigth, float x, float y) {
		super(width, heigth);
		this.setX(x);
		this.setY(y);
		this.isShoot = false;
	}
	
	public void drawBalle() {
		if(isShoot)
		{
			Render.getInstance().drawPoint(this.getX(), this.getY(), 80);
		}
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public boolean isShoot() {
		return isShoot;
	}

	public void setShoot(boolean isShoot) {
		this.isShoot = isShoot;
	}

	public double getCoolDown() {
		return coolDown;
	}

	public void setCoolDown(double coolDown) {
		this.coolDown = coolDown;
	}

	public double getDegats() {
		return degats;
	}

	public void setDegats(double degats) {
		this.degats = degats;
	}
	
	
	
}
