package com.projetpo.bindingofisaac.module.Controler;

import java.util.LinkedList;

import com.projetpo.bindingofisaac.module.Model.Balle;
import com.projetpo.bindingofisaac.module.Model.Jeu;
import com.projetpo.bindingofisaac.module.Shaders.Vector2;
import com.projetpo.bindingofisaac.module.Vue.Render;
import com.projetpo.bindingofisaac.module.Vue.Texture;

public class ListeBalle {
	
	/*
	 * Liste Chain�e de Balle
	 */
	private LinkedList<Balle> liste;
	
	/*
	 * Boolean qui permet de savoir si une balle � �t� tir�e (sert pour le Cooldown du joueur)
	 */
	private boolean isNotShot;
	
	/*
	 * Degat de la balle
	 */
	private double degats;
	
	/*
	 * Cooldown qui �vite de spammer les balles
	 */
	private int coolDown;
	
	/*
	 * Boolean qui sert � savoir s'il s'agit de balle ennemi
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
	 * @Note Dessine l'ensemble des balles de la liste
	 */
	public void drawBalle() {
		LinkedList<Balle> copieListe = (LinkedList<Balle>) liste.clone();  //On fait une copie de la liste pour �viter d'enlever ou d'ajouter une balle pendant durant l'execution 
		for(Balle b: liste) {
			b.getPosition().setX(b.getPosition().getX() + b.getDirection().getX()*b.getSpeed());
			b.getPosition().setY(b.getPosition().getY() + b.getDirection().getY()*b.getSpeed());
			if(isEnnemiBalle()) {
				b.drawRealEntite();
			} else {
				b.drawBalle();
			}
			b.updateHitbox();
		}
		liste = copieListe;
	} 
	
	public void update() {
		LinkedList<Balle> copieListe = (LinkedList<Balle>) liste.clone();
		for(Balle b: liste) {
			if(doRemove(b)) {
				copieListe.remove(b);
			}
			if(!isEnnemiBalle) { //Si la liste de balle appartient au joueur on fait des d�g�ts aux ennemis
				for(int i=0; i<Jeu.gameWorld.getMapEnCours().getListeEnnemi().getListe().size(); i++) {
					if(Jeu.gameWorld.getMapEnCours().getListeEnnemi().getListe().get(i).collisionBalle(b)) {
						copieListe.remove(b);
						Jeu.gameWorld.getMapEnCours().getListeEnnemi().getListe().get(i).setTouch(true);
						Jeu.gameWorld.getMapEnCours().getListeEnnemi().getListe().get(i).setLife(Jeu.gameWorld.getMapEnCours().getListeEnnemi().getListe().get(i).getLife()-Jeu.gameWorld.getMapEnCours().getPlayer().attaque());
					};
				}
			} else {  //Sinon on fait des d�g�ts au joueur
				for(int i=0; i<Jeu.gameWorld.getMapEnCours().getListeEnnemi().getListe().size(); i++) {
					if(Jeu.gameWorld.getMapEnCours().getPlayer().collisionBalle(b)) {
						copieListe.remove(b);
						Jeu.gameWorld.getMapEnCours().getPlayer().setTouch(true);
						Jeu.gameWorld.getMapEnCours().getPlayer().subitDegats(degats);
					};
				}
			}
		}
		liste = copieListe;
	}
	
	/**
	 * @param b une balle
	 * Utilise la varaible isNotShot si il s'agit du joueur pour cr�er un cooldown
	 * @return Ajoute une balle � la liste
	 */
	public void addBalle(Balle b) {
		if(this.isNotShot || isEnnemiBalle) {
			this.liste.add(b);
		}
		this.isNotShot = false;
	}
	
	/**
	 * @param b une balle
	 * @return renvoie si on doit enlever une balle de la liste � cause d'une collision
	 */
	public boolean doRemove(Balle b) {
		return (b.getHitbox().collisionMurEntite(b) || outOfRange(b));
	}

	/**
	 * @param b une balle
	 * @return un boolean si la balle a d�pass� sa range
	 */
	private boolean outOfRange(Balle b) {
		if(b.getDirection().equals(new Vector2(-1, 0))) {
			return (b.getPosition().getX() < (b.getPosOrigin().getX() - range*65));
		}
		if(b.getDirection().equals(new Vector2(1, 0))) {
			return (b.getPosition().getX() > (b.getPosOrigin().getX() + range*65));
		}
		if(b.getDirection().equals(new Vector2(0, 1))) {
			return (b.getPosition().getY() > (b.getPosOrigin().getY() + range*65));
		}
		if(b.getDirection().equals(new Vector2(0, -1))) {
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

	public double getDegats() {
		return degats;
	}

	public void setDegats(double degats) {
		this.degats = degats;
	}
	
}