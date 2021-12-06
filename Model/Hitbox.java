package Model;

import java.util.*;

import Controler.DeplacerPersonnage;
import Vue.Fenetre;

public class Hitbox {
	
	Entite entity;
	
	private boolean isZCollision;
	private boolean isQCollision;
	private boolean isSCollision;
	private boolean isDCollision;
	
    public Hitbox(Entite entity) {
    	this.entity = entity;
    }
    
    public void collisionPlayer(DeplacerPersonnage p) {
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
    
}