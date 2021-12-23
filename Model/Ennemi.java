package Model;

import java.util.LinkedList;
import java.util.List;

import Shaders.Vector2;

public abstract class Ennemi extends Entite{
	
	private Vector2 direction;
	private double speed;
	private double life;
	private int cooldownDegat;
	private boolean isTouch;
	
	public Ennemi(int width, int heigth, Vector2 position, double speed,String url, int life) {
		super(width, heigth, position, url);
		this.speed = speed;
		this.life = life;
	}
	
	public abstract void drawEnnemi();
	
	public abstract void IAEnnemi(Personnage p);
	
	public boolean doRemove(Ennemi e) {
		return e.getLife()<0;
	}
		
	public boolean collisionEnnemi(Personnage p) {
		return Hitbox.rectangleCollision(p.position, new Vector2(p.getHitbox().getWidth(), p.getHitbox().getHeigth()), position, new Vector2(hitbox.getWidth(), hitbox.getHeigth()));
	}
	
	public boolean collisionBalle(Balle b) {
		return (Hitbox.rectangleCollision(b.position, new Vector2(b.getHitbox().getWidth(), b.getHitbox().getHeigth()), position, new Vector2(hitbox.getWidth(), hitbox.getHeigth())) && !isTouch); 
	}
	
	protected void boucleCooldownEnnemi() {
		if(isTouch()) {
			setCooldownDegat((getCooldownDegat()+1));
			if(getCooldownDegat() == 30) {
				setCooldownDegat(0);
				setTouch(false);
			};
		}
	}
	
	protected void move()
	{
		Vector2 normalizedDirection = getNormalizedDirection();
		Vector2 positionAfterMoving = getPosition().addVector(normalizedDirection);
		setPosition(positionAfterMoving);
		direction = new Vector2();
	}
	
	public Vector2 getNormalizedDirection()
	{
		Vector2 normalizedVector = new Vector2(direction);
		normalizedVector.euclidianNormalize(speed);
		return normalizedVector;
	}
		
	public Vector2 getDirection() {
		return direction;
	}

	public void setDirection(Vector2 direction) {
		this.direction = direction;
	}

	public double getLife() {
		return life;
	}

	public void setLife(double d) {
		this.life = d;
	}

	public int getCooldownDegat() {
		return cooldownDegat;
	}

	public void setCooldownDegat(int cooldownDegat) {
		this.cooldownDegat = cooldownDegat;
	}

	public boolean isTouch() {
		return isTouch;
	}

	public void setTouch(boolean isTouch) {
		this.isTouch = isTouch;
	}
	
	
	
}
