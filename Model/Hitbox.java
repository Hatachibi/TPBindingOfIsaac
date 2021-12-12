package Model;

import java.util.*;

import Shaders.Vector2;
import Vue.Fenetre;

public class Hitbox {
	
	/* 2------4
	 * |      |
	 * |      |
	 * 1------3
	 */
	
	private Vector2 position;
	private int width;
	private int heigth;
	
	private boolean isZCollision;
	private boolean isQCollision;
	private boolean isSCollision;
	private boolean isDCollision;
	private Entite entity;
	
    public Hitbox(Vector2 position, int width, int heigth) {
    	this.position = position;
    	this.width = width;
    	this.heigth = heigth;
    } 
    
    public void collisionPlayer(Personnage p) {
    	Jeu.Isaac.updateHitbox();
    	int[] map = new int[]  {
    			1, 1, 1, 1, 1, 1, 1, 1, 1,
				1, 0, 0, 0, 0, 0, 0, 0, 1,
				1, 0, 0, 0, 0, 0, 0, 0, 1,
				1, 0, 0, 0, 1, 0, 0, 0, 1,
				1, 0, 0, 0, 1, 0, 0, 0, 1,
				1, 0, 0, 0, 1, 0, 0, 0, 1,
				1, 0, 0, 0, 0, 0, 0, 0, 1,
				1, 0, 0, 0, 0, 0, 0, 0, 1,
				1, 1, 1, 1, 1, 1, 1, 1, 1};
    	if(this.collisionMur(p.getHitbox().getPositionX().getX() + p.getSpeed(), p.getHitbox().getPositionX().getY(), map) || this.collisionMur(p.getHitbox().getPositionXY().getX() + p.getSpeed(), p.getHitbox().getPositionXY().getY(), map)) {
    		this.isDCollision = true;
    	} else {
    		this.isDCollision = false;
    	}
    	if(this.collisionMur(p.getHitbox().getPosition().getX() - p.getSpeed(), p.getHitbox().getPosition().getY(), map) || this.collisionMur(p.getHitbox().getPositionY().getX() - p.getSpeed(), p.getHitbox().getPositionY().getY(), map)) {
    		this.isQCollision = true;
    	} else {
    		this.isQCollision = false;
    	}
    	if(this.collisionMur(p.getHitbox().getPositionY().getX(), p.getHitbox().getPositionY().getY() + p.getSpeed(), map) || this.collisionMur(p.getHitbox().getPositionXY().getX(), p.getHitbox().getPositionXY().getY() + p.getSpeed(), map)) {
    		this.isZCollision = true;
    	}  else {
    		this.isZCollision = false;
    	}
    	if(this.collisionMur(p.getHitbox().getPosition().getX(), p.getHitbox().getPosition().getY() - p.getSpeed(), map) || this.collisionMur(p.getHitbox().getPositionX().getX(), p.getHitbox().getPositionX().getY() - p.getSpeed(), map)) {
    		this.isSCollision = true;
    	}  else {
    		this.isSCollision = false;
    	}
    }
    
    /**
     * @param pos est la position 1, x la 3, y la 2 et xy la 4 (voir schéma ligne 10)
     * return si 2 Hitbox sont en contact
     */
    public boolean collisionHitbox(Vector2 pos, Vector2 x, Vector2 y, Vector2 xy) {
    	return (this.between(pos) || this.between(x) || this.between(y) || this.between(xy));
    }
    
    public boolean collisionMur(double x, double y, int[] map) {
    	int i = (int) x/65;
    	int j = (int) y/65;
    	return (map[i+9*j] == 1);
    }
    
    /**
     * return si un Vecteur est compris dans la Hitbox
     */
    public boolean between(Vector2 pos) {
    	return (pos.getX() > position.getX() && pos.getX() < getPositionX().getX() && pos.getY() < getPositionY().getY() && pos.getY() > position.getY());
    }
    
    /**
     * return la posision du point 1 (voir schéma ligne 10)
     */
    public Vector2 getPosition() {
  		return position;
  	}
    
    /*
     * return la posision du point 2 (voir schéma ligne 10)
     */
    public Vector2 getPositionY() {
    	return new Vector2(position.getX(), position.getY() + heigth);
    }
    
    /*
     * return la posision du point 3 (voir schéma ligne 10)
     */
    public Vector2 getPositionX() {
    	return new Vector2(position.getX() + width, position.getY());
    }
    
    /*
     * return la posision du point 4 (voir schéma ligne 10)
     */
    public Vector2 getPositionXY() {
    	return new Vector2(position.getX() + width, position.getY() + heigth);
    }

	public boolean isZCollision() {
		return isZCollision;
	}

	public void setZCollision(boolean isZCollision) {
		this.isZCollision = isZCollision;
	}

	public boolean isQCollision() {
		return isQCollision;
	}

	public void setQCollision(boolean isQCollision) {
		this.isQCollision = isQCollision;
	}

	public boolean isSCollision() {
		return isSCollision;
	}

	public void setSCollision(boolean isSCollision) {
		this.isSCollision = isSCollision;
	}

	public boolean isDCollision() {
		return isDCollision;
	}

	public void setDCollision(boolean isDCollision) {
		this.isDCollision = isDCollision;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeigth() {
		return heigth;
	}

	public void setHeigth(int heigth) {
		this.heigth = heigth;
	}
	
	
    
}