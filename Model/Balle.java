package com.projetpo.bindingofisaac.module.Model;

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
	 * 1 = gauche
	 * 2 = droite
	 * 3 = haut
	 * 4 = bas
	 * 5 =
	 * 6 = 
	 * 7 =
	 * 8 =
	 */
	private int direction;
	
	/*
	 * Constructeur
	 */
	public Balle(int width, int heigth, double x, double y, int direction, String url) {
		super(width, heigth, new Vector2(x, y), url);
		this.setDirection(direction);
		this.setPosOrigin(new Vector2(x, y));
		this.setHitbox(new Hitbox(new Vector2(x, y), width, heigth));
	}
	
	/**
	 * @return Dessine la balle
	 */
	public void drawBalle() {
		Texture.tears.bind();
		Render.getInstance().drawPicture((float)this.getPosition().getX(), (float)this.getPosition().getY(), 25, 25, 200, 200, new float[] {255, 255, 255, 255});
		Texture.tears.unbind();
	//	Render.getInstance().drawSquare((float)hitbox.getPosition().getX(), (float)hitbox.getPosition().getY(), (float)hitbox.getPositionX().getX(), (float)hitbox.getPositionX().getY(), (float)hitbox.getPositionXY().getX(), (float)hitbox.getPositionXY().getY(), (float)hitbox.getPositionY().getX(), (float)hitbox.getPositionY().getY()); //Obliger de cast en float car sinon on ne peut pas draw les rectangles
		
	}
	
	/**
	 * @return Update la position de la balle 
	 */
	public void updateHitbox() {
		this.getHitbox().setPosition(position);
	}

	/*
	 * Getters & Setters
	 */
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

	public Vector2 getPosOrigin() {
		return posOrigin;
	}

	public void setPosOrigin(Vector2 posOrigin) {
		this.posOrigin = posOrigin;
	}
	
	
	
}