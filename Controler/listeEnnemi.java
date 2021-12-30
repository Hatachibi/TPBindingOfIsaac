package com.projetpo.bindingofisaac.module.Controler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.projetpo.bindingofisaac.module.Model.Boss;
import com.projetpo.bindingofisaac.module.Model.Ennemi;
import com.projetpo.bindingofisaac.module.Model.Jeu;

public class listeEnnemi {

	/*
	 * Liste Chainée d'ennemi
	 */
	private ArrayList<Ennemi> liste;
	
	/*
	 * Constructeur
	 */
	public listeEnnemi() {
		this.liste = new ArrayList<Ennemi>();
	}
	
	/*
	 * Distribue le loot de l'ennemi
	 */
	public void randomLoot(Ennemi e) {
		if(!e.getLoot().isEmpty() && Math.random() > 0.1) {
			int randomItems = (int)(Math.random()*(e.getLoot().size()));
			e.getLoot().get(randomItems).setPosition(e.getPosition());
			Jeu.room.getMapEnCours().getObjet().add(e.getLoot().get(randomItems));
		}
	}
	
	/**
	 * @return Update tous les ennemis
	 */
	public void updateEnnemis() {
		ArrayList<Ennemi> copieListe = (ArrayList<Ennemi>) liste.clone();
		for(Ennemi e: liste) {  //On parcours un par un les ennemis
			if(e.doRemove(e)) {  //On les enlèves si besoin
				copieListe.remove(e);
				this.randomLoot(e);
				playDeathSound(e);
			} else {
				e.boucleCooldownEnnemi();
				e.IAEnnemi(Jeu.room.getPlayer());
			}
		}
		liste = copieListe;
	}

	private void playDeathSound(Ennemi e) {
		if(e instanceof Boss) {
			try {
				Jeu.music("/libMusic/boss_death.wav", false);
			} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e1) {
				e1.printStackTrace();
			}
		}
	}

	/**
	 * @return Draw tous les ennemis
	 */
	public void drawEnnemis() {
		for(Ennemi e: liste) {
			if(!e.doRemove(e)) {
				e.drawEnnemi();
			}
		}
	}
	
	/**
	 * @return Ajoute un ennemi
	 */
	public void addEnnemi(Ennemi e) {
		this.liste.add(e);
	}
	
	/**
	 * @return Retourne si la liste d'ennemi est vide
	 */
	public boolean isEmpty() {
		return this.liste.size() == 0;
	}

	/*
	 * Getters & Setters
	 */
	public List<Ennemi> getListe() {
		return liste;
	}

	public void setListe(ArrayList<Ennemi> liste) {
		this.liste = liste;
	}
	
}
