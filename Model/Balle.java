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
	
	public Balle(int width, int heigth, double x, double y, int direction, String url) {
		super(width, heigth, new Vector2(x, y), url);
		this.setDirection(direction);
		this.speed = 10;
		this.setHitbox(new Hitbox(new Vector2(x, y), width, heigth));
	}
	
	public void drawBalle() {
		Texture.tears.bind();
		Render.getInstance().drawPicture((float)this.getPosition().getX(), (float)this.getPosition().getY(), 25, 25, 200, 200, new float[] {255, 255, 255, 255});
		Texture.tears.unbind();
	//	Render.getInstance().drawSquare((float)hitbox.getPosition().getX(), (float)hitbox.getPosition().getY(), (float)hitbox.getPositionX().getX(), (float)hitbox.getPositionX().getY(), (float)hitbox.getPositionXY().getX(), (float)hitbox.getPositionXY().getY(), (float)hitbox.getPositionY().getX(), (float)hitbox.getPositionY().getY()); //Obliger de cast en float car sinon on ne peut pas draw les rectangles
		
	}
		
	public void updateHitbox() {
		this.getHitbox().setPosition(position);
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