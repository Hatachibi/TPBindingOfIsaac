package com.projetpo.bindingofisaac.module.Model;

import java.util.LinkedList;

import com.projetpo.bindingofisaac.module.Shaders.Hitbox;
import com.projetpo.bindingofisaac.module.Shaders.Vector2;
import com.projetpo.bindingofisaac.module.Vue.Render;
import com.projetpo.bindingofisaac.module.Vue.Texture;

public class Balle extends Entite{
		
	/*
	 * Compteur permettant un cooldown entre chaque tire (utile que pour le joueur)
	 */
	private double coolDown;
	
	/*
	 * Position de départ de la balle
	 */
	private Vector2 posOrigin;
	
	/**
	 * Direction dans laquelle part une balle
	 */
	private Vector2 direction;
	
	/*
	 * Vitesse de la balle
	 */
	private double speed;
	
	/*
	 * Constructeur
	 */
	public Balle(int width, int heigth, double x, double y, Vector2 direction, String url, double speed) {
		super(width, heigth, new Vector2(x, y), url);
		this.setDirection(direction);
		this.setPosOrigin(new Vector2(x, y));
		this.setHitbox(new Hitbox(new Vector2(x, y), width, heigth));
		this.direction = direction;
		this.speed = speed;
	}
	
	/**
	 * @return Dessine la balle
	 */
	public void drawBalle() {
		Texture.tears.bind();
		Render.getInstance().drawPicture((float)this.getPosition().getX(), (float)this.getPosition().getY(), 25, 25);
		Texture.tears.unbind(); 
	}
	
	/**
	 * @return Update la position de la balle 
	 */
	public void updateHitbox() {
		this.getHitbox().setPosition(position);
	}
	
	public void animRemove() {
		LinkedList<String> liste = new LinkedList<String>();
		liste.add("src/main/resources/animationBalle2.png");
		Animation anim = new Animation(this, 60, liste ,position, new Vector2(width, heigth));
		anim.animUrl();
	}
		
	/*
	 * Getters & Setters
	 */
	public Vector2 getDirection() {
		return direction;
	}

	public void setDirection(Vector2 direction2) {
		this.direction = direction2;
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

	public Vector2 getPosOrigin() {
		return posOrigin;
	}

	public void setPosOrigin(Vector2 posOrigin) {
		this.posOrigin = posOrigin;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}
	
}