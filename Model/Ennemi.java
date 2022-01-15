package com.projetpo.bindingofisaac.module.Model;

import java.util.ArrayList;

import com.projetpo.bindingofisaac.module.Shaders.Vector2;
import com.projetpo.bindingofisaac.module.Vue.Fenetre;

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
	
	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public int getTick() {
		return tick;
	}

	public void setTick(int tick) {
		this.tick = tick;
	}

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
	 * Direction aléatoire
	 */
	private Vector2 random;
	
	/*
	 * Loot de l'ennemi
	 */
	private ArrayList<ObjetsInventaire> loot;
	
	protected int tick;
	
	/*
	 * Constructeur
	 */
	public Ennemi(int width, int heigth, Vector2 position, double speed, String url) {
		super(width, heigth, position, url);
		this.speed = speed;
		this.tick = 0 ;
		this.direction = new Vector2(0, 0);
		this.random = new Vector2(0, 0);
		this.loot = new  ArrayList<ObjetsInventaire>();
		this.getLoot().add(new ObjetsInventaire(-3, 30, 30, position, ""));
		this.getLoot().add(new ObjetsInventaire(1, 30, 30, position, ""));
		this.getLoot().add(new ObjetsInventaire(10, 30, 30, position, ""));
		this.getLoot().add(new ObjetsInventaire(11, 30, 30, position, ""));
		this.getLoot().add(new ObjetsInventaire(12, 30, 30, position, ""));
		this.getLoot().add(new ObjetsInventaire(13, 30, 30, position, ""));
	}
	
	/*
	 * Dessine l'ennemi
	 */
	public abstract void drawEnnemi();
	
	public void goToPlayer(Personnage p) {
		setDirection(new Vector2(p.getPosition().getX() - position.getX(), p.getPosition().getY() - position.getY()));
		this.move();
	}
	
	public void goToRandom(int firstTick, int secondTick) {
		if(Fenetre.tick == firstTick) {
			random.setX(Math.random() - 0.5);
			random.setY(Math.random() - 0.5);
		}
		if(Fenetre.tick >= secondTick) {
			if(position.getX() < 65) {
				setDirection(new Vector2(1, this.random.getY()));
			} 
			else if(position.getX() > Fenetre.WidthFenetre - 65-width) {
				setDirection(new Vector2(-1, this.random.getY()));
			}
			else if(position.getY() < 65) {
				setDirection(new Vector2(this.random.getX(), 1));
			}
			else if(position.getY() > Fenetre.HeigthFenetre - 65-heigth) {
				setDirection(new Vector2(this.random.getX(), -1));
			}
			else {
				setDirection(random);
			}
			random = getDirection();
			this.move();
		}
	}
	
	/*
	 * IA de l'ennemi
	 */
	public abstract void IAEnnemi(Personnage p);
	
	/**
	 * @return si l'ennemi n'a plus de vie
	 */
	public boolean doRemove(Ennemi e) {
		return e.getLife()<=0;
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
			if(getCooldownDegat() == Fenetre.getInstance().getFPS()/2) {
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
