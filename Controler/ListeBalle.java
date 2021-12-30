package com.projetpo.bindingofisaac.module.Controler;

import java.util.LinkedList;

import com.projetpo.bindingofisaac.module.Model.Balle;
import com.projetpo.bindingofisaac.module.Model.Jeu;

public class ListeBalle {
	
	/*
	 * Liste Chainée de Balle
	 */
	private LinkedList<Balle> liste;
	
	/*
	 * Boolean qui permet de savoir si une balle à été tirée (sert pour le Cooldown du joueur)
	 */
	private boolean isNotShot;
	
	/*
	 * Vitesse de la balle
	 */
	private int speed;
	
	/*
	 * Degat de la balle
	 */
	private double degats;
	
	/*
	 * Cooldown qui évite de spammer les balles
	 */
	private int coolDown;
	
	/*
	 * Boolean qui sert à savoir s'il s'agit de balle ennemi
	 */
	private boolean isEnnemiBalle;
	
	/*
	 * Range des balles
	 */
	private double range;
	
	/*
	 * Constructeur
	 */
	public ListeBalle() {
		this.liste = new LinkedList<Balle>();
		this.isNotShot = true;
		this.setCoolDown(0);
		this.setEnnemiBalle(false);
	}
	
	/**
	 * @return Dessine l'ensemble des balles de la liste
	 */
	public void drawBalle() {
		LinkedList<Balle> copieListe = (LinkedList<Balle>) liste.clone();  //On fait une copie de la liste pour éviter d'enlever ou d'ajouter une balle pendant durant l'execution 
		for(Balle b: liste) {
			if(b.getDirection() == 1)
			{
				b.drawBalle();
				b.getPosition().setX((float) (b.getPosition().getX() - speed));
				if(!isEnnemiBalle) Jeu.room.getPlayer().setFace(4);
			}
			if(b.getDirection() == 2)
			{
				b.drawBalle();
				b.getPosition().setX((float) (b.getPosition().getX() + speed));
				if(!isEnnemiBalle) Jeu.room.getPlayer().setFace(3);
			}
			if(b.getDirection() == 3)
			{
				b.drawBalle();
				b.getPosition().setY((float) (b.getPosition().getY() + speed));
				if(!isEnnemiBalle) Jeu.room.getPlayer().setFace(1);
			}
			if(b.getDirection() == 4)
			{
				b.drawBalle();
				b.getPosition().setY((float) (b.getPosition().getY() - speed));
				if(!isEnnemiBalle) Jeu.room.getPlayer().setFace(2);
			}
			if(b.getDirection() == 5)
			{
				b.drawBalle();
				b.getPosition().setX((float) (b.getPosition().getX() + speed));
				b.getPosition().setY((float) (b.getPosition().getY() + speed));
			}
			if(b.getDirection() == 6)
			{
				b.drawBalle();
				b.getPosition().setX((float) (b.getPosition().getX() - speed));
				b.getPosition().setY((float) (b.getPosition().getY() - speed));
			}
			if(b.getDirection() == 7)
			{
				b.drawBalle();
				b.getPosition().setX((float) (b.getPosition().getX() - speed));
				b.getPosition().setY((float) (b.getPosition().getY() + speed));
			}
			if(b.getDirection() == 8)
			{
				b.drawBalle();
				b.getPosition().setX((float) (b.getPosition().getX() + speed));
				b.getPosition().setY((float) (b.getPosition().getY() - speed));
			}
			b.updateHitbox();
			if(doRemove(b)) {
				copieListe.remove(b);
			}
			if(!isEnnemiBalle) { //Si la liste de balle appartient au joueur on fait des dégâts aux ennemis
				for(int i=0; i<Jeu.room.getListeEnnemi().getListe().size(); i++) {
					if(Jeu.room.getListeEnnemi().getListe().get(i).collisionBalle(b)) {
						copieListe.remove(b);
						Jeu.room.getListeEnnemi().getListe().get(i).setTouch(true);
						Jeu.room.getListeEnnemi().getListe().get(i).setLife(Jeu.room.getListeEnnemi().getListe().get(i).getLife()-Jeu.room.getPlayer().attaque());
					};
				}
			} else {  //Sinon on fait des dégâts au joueur
				for(int i=0; i<Jeu.room.getListeEnnemi().getListe().size(); i++) {
					if(Jeu.room.getPlayer().collisionBalle(b)) {
						copieListe.remove(b);
						Jeu.room.getPlayer().setTouch(true);
						Jeu.room.getPlayer().subitDegats(Jeu.room.getListeEnnemi().getListe().get(i).getDegat());
					};
				}
			}
		}
		liste = copieListe;
	}
	
	/**
	 * @param b une balle
	 * Utilise la varaible isNotShot si il s'agit du joueur pour créer un cooldown
	 * @return Ajoute une balle à la liste
	 */
	public void addBalle(Balle b) {
		if(this.isNotShot || isEnnemiBalle) {
			this.liste.add(b);
		}
		this.isNotShot = false;
	}
	
	/**
	 * @param b une balle
	 * @return renvoie si on doit enlever une balle de la liste à cause d'une collision
	 */
	public boolean doRemove(Balle b) {
		return (b.getHitbox().collisionMurEntite(b) || outOfRange(b));
	}

	/**
	 * @param b une balle
	 * @return un boolean si la balle a dépassé sa range
	 */
	private boolean outOfRange(Balle b) {
		if(b.getDirection() == 1) {
			return (b.getPosition().getX() < (b.getPosOrigin().getX() - range*65));
		}
		if(b.getDirection() == 2) {
			return (b.getPosition().getX() > (b.getPosOrigin().getX() + range*65));
		}
		if(b.getDirection() == 3) {
			return (b.getPosition().getY() > (b.getPosOrigin().getY() + range*65));
		}
		if(b.getDirection() == 4) {
			return (b.getPosition().getY() < (b.getPosOrigin().getY() - range*65));
		}
		return false;
	}

	/*
	 * Getters & Setters
	 */
	public LinkedList<Balle> getListe() {
		return liste;
	}

	public boolean isNotShot() {
		return isNotShot;
	}

	public void setShot(boolean isNotShot) {
		this.isNotShot = isNotShot;
	}

	public int getCoolDown() {
		return coolDown;
	}

	public void setCoolDown(int coolDown) {
		this.coolDown = coolDown;
	}

	public boolean isEnnemiBalle() {
		return isEnnemiBalle;
	}

	public void setEnnemiBalle(boolean isEnnemiBalle) {
		this.isEnnemiBalle = isEnnemiBalle;
	}

	public double getRange() {
		return range;
	}

	public void setRange(double range) {
		this.range = range;
	}

	public void setListe(LinkedList<Balle> liste) {
		this.liste = liste;
	}

	public void setNotShot(boolean isNotShot) {
		this.isNotShot = isNotShot;
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
	
}