package Model;

import java.util.*;

import Shaders.Vector2;
import Vue.Fenetre;

public class Hitbox {
	
	private Vector2 position;
	private int width;
	private int heigth;
	
	private boolean isZCollision;
	private boolean isQCollision;
	private boolean isSCollision;
	private boolean isDCollision;
	private Entite entity;
	
    public Hitbox(Vector2 position) {
    	this.position = position;
    } 
    
    public void collisionPlayer(Personnage p) {
    	Jeu.Isaac.updateHitbox();
    	float PI = (float) 3.141592;
    	if(p.getDistance() <= 7) {
    		System.out.println(p.getA() == PI/2);
			if(p.getA() == PI) this.isQCollision = true;
			if(p.getA() == PI/2) this.isZCollision = true;
			if(p.getA() == 3*(PI/2)) this.isSCollision = true;
			if(p.getA() == 0) this.isDCollision = true;
		} else {
			if(p.getA() == PI) this.isQCollision = false;
			if(p.getA() == PI/2) this.isZCollision = false;
			if(p.getA() == 3*(PI/2)) this.isSCollision = false;
			if(p.getA() == 0) this.isDCollision = false;
		}
    }
    public void collisionBalle(Balle b) {
    	float PI = (float) 3.141592;
    	if(b.getDistance() <= 7) {
    		System.out.println(b.getDirection() == PI/2);
			if(b.getDirection() == PI) this.isQCollision = true;
			if(b.getDirection() == PI/2) this.isZCollision = true;
			if(b.getDirection() == 3*(PI/2)) this.isSCollision = true;
			if(b.getDirection() == 0) this.isDCollision = true;
		} else {
			if(b.getDirection() == PI) this.isQCollision = false;
			if(b.getDirection() == PI/2) this.isZCollision = false;
			if(b.getDirection() == 3*(PI/2)) this.isSCollision = false;
			if(b.getDirection() == 0) this.isDCollision = false;
		}
    }

	public Entite getEntity() {
		return entity;
	}

	public void setEntity(Entite entity) {
		this.entity = entity;
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

	public Vector2 getPosition() {
		return position;
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