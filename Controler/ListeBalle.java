package com.projetpo.bindingofisaac.module.Controler;

import java.io.IOException;
import java.util.LinkedList;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.projetpo.bindingofisaac.module.Model.Balle;
import com.projetpo.bindingofisaac.module.Shaders.Vector2;
import com.projetpo.bindingofisaac.module.Vue.Jeu;

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
	 * @Note Dessine l'ensemble des balles de la liste
	 */
	public void drawBalle() {
		LinkedList<Balle> copieListe = (LinkedList<Balle>) liste.clone();  //On fait une copie de la liste pour éviter d'enlever ou d'ajouter une balle pendant durant l'execution 
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
				b.animRemove();
				copieListe.remove(b);
			}
			if(!isEnnemiBalle) { //Si la liste de balle appartient au joueur on fait des dégâts aux ennemis
				for(int i=0; i<Jeu.gameWorld.getMapEnCours().getListeEnnemi().getListe().size(); i++) {
					if(Jeu.gameWorld.getMapEnCours().getListeEnnemi().getListe().get(i).collisionBalle(b)) {
						copieListe.remove(b);
						Jeu.gameWorld.getMapEnCours().getListeEnnemi().getListe().get(i).setTouch(true);
						Jeu.gameWorld.getMapEnCours().getListeEnnemi().getListe().get(i).setLife(Jeu.gameWorld.getMapEnCours().getListeEnnemi().getListe().get(i).getLife()-Jeu.gameWorld.getMapEnCours().getPlayer().attaque());
					};
				}
			} else {  //Sinon on fait des dégâts au joueur
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
	 * Utilise la varaible isNotShot si il s'agit du joueur pour créer un cooldown
	 * @return Ajoute une balle à la liste
	 */
	public void addBalle(Balle b) {
		if(this.isNotShot || isEnnemiBalle) {
			if(!isEnnemiBalle) {
				this.playTearEffect((int)(Math.random()*3));
			}
			this.liste.add(b);
		}
		this.isNotShot = false;
	}
	
	private void playTearEffect(int sound) {
		switch(sound) {
			case 0: try {
				Jeu.music("/com/projetpo/bindingofisaac/module/libMusic/tear_fire_1.wav", false);
			} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
				e.printStackTrace();
			}
			break;
			case 1:try {
				Jeu.music("/com/projetpo/bindingofisaac/module/libMusic/tear_fire_2.wav", false);
			} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
				e.printStackTrace();
			}	
			break;
			case 2:try {
				Jeu.music("/com/projetpo/bindingofisaac/module/libMusic/tear_fire_3.wav", false);
			} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
				e.printStackTrace();
			}
			break;
		}
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