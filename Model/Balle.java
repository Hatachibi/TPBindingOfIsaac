package Model;

import Shaders.Vector2;
import Vue.Render;
import Vue.Texture;

public class Balle extends Entite{
	
	private int speed;
	private double degats;
	private double coolDown;
	private Hitbox hitbox;
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
		this.setHitbox(new Hitbox(new Vector2(x, y), width, heigth));
	}
	
	public void drawBalle() {
	//	Render.getInstance().drawPoint((float)this.getX(), (float)this.getY(), 25);
		Texture.tears.bind();
		Render.getInstance().drawPicture((float)this.getX(), (float)this.getY(), 25, 25, 200, 200, new float[] {255, 255, 255, 255});
		Texture.tears.unbind();
	}
	
	public void updateHitbox() {
		this.getHitbox().setPosition(new Vector2(x, y));
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

	public Hitbox getHitbox() {
		return hitbox;
	}

	public void setHitbox(Hitbox hitbox) {
		this.hitbox = hitbox;
	}
	
	
	
}
