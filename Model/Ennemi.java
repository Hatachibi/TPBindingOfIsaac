package com.projetpo.bindingofisaac.module.Model;

import java.util.ArrayList;

import com.projetpo.bindingofisaac.module.Shaders.Vector2;

/*
 * Classe abstraite car plusieurs comportement pour chaque ennemi
 */
public abstract class Ennemi extends Entite{
	
	/*
	 * Direction de deplacement
	 */
	private Vector2 direction;
	
	/*
	 * Vitesse de l'ennemi
	 */
	private double speed;
	
	/*
	 * Degat de l'ennemi 
	 */
	private double degat;
	
	/*
	 * Vie de l'ennemi
	 */
	private double life;
	
	/*
	 * Cooldown quand l'ennemi se fait toucher
	 */
	private int cooldownDegat;
	
	/*
	 * Boolean qui indique si l'ennemi est touché
	 */
	private boolean isTouch;
	
	/*
	 * Loot de l'ennemi
	 */
	private ArrayList<ObjetsInventaire> loot;
	
	/*
	 * Constructeur
	 */
	public Ennemi(int width, int heigth, Vector2 position, double speed, String url) {
		super(width, heigth, position, url);
		this.speed = speed;
		this.direction = new Vector2(0, 0);
		this.loot = new  ArrayList<ObjetsInventaire>();
	}
	
	/*
	 * Dessine l'ennemi
	 */
	public abstract void drawEnnemi();
	
	/*
	 * IA de l'ennemi
	 */
	public abstract void IAEnnemi(Personnage p);
	
	/**
	 * @return si l'ennemi n'a plus de vie
	 */
	public boolean doRemove(Ennemi e) {
		return e.getLife()<0;
	}
	
	/**
	 * @param p le joueur
	 * @return si il y a collision entre l'ennemi et le personnage (joueur)
	 */
	public boolean collisionEnnemi(Personnage p) {
		return Hitbox.rectangleCollision(p.position, new Vector2(p.getHitbox().getWidth(), p.getHitbox().getHeigth()), position, new Vector2(hitbox.getWidth(), hitbox.getHeigth()));
	}
	
	/**
	 * @param b une balle (celle du joueur)
	 * @return si il y a collision entre l'ennemi et la balle
	 */
	public boolean collisionBalle(Balle b) {
		return (Hitbox.rectangleCollision(b.position, new Vector2(b.getHitbox().getWidth(), b.getHitbox().getHeigth()), position, new Vector2(hitbox.getWidth(), hitbox.getHeigth())) && !isTouch); 
	}
	
	/**
	 * @return Fonction qui effectue le cooldown
	 */
	public void boucleCooldownEnnemi() {
		if(isTouch()) {
			setCooldownDegat((getCooldownDegat()+1));
			if(getCooldownDegat() == 30) {
				setCooldownDegat(0);
				setTouch(false);
			};
		}
	}
	
	/*
	 * Fonction de deplacement
	 */
	public void move()
	{
		Vector2 normalizedDirection = getNormalizedDirection();
		Vector2 positionAfterMoving = getPosition().addVector(normalizedDirection);
		setPosition(positionAfterMoving);
		direction = new Vector2();
	}
	
	/**
	 * @return le vecteur direction normalisée
	 */
	public Vector2 getNormalizedDirection()
	{
		Vector2 normalizedVector = new Vector2(direction);
		normalizedVector.euclidianNormalize(speed);
		return normalizedVector;
	}
	
	/*
	 * Getters & Setters
	 */
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

	public double getDegat() {
		return degat;
	}

	public void setDegat(double degat) {
		this.degat = degat;
	}

	public ArrayList<ObjetsInventaire> getLoot() {
		return loot;
	}

	public void setLoot(ArrayList<ObjetsInventaire> loot) {
		this.loot = loot;
	}
	
}
