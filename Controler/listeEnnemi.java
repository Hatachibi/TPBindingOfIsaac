package com.projetpo.bindingofisaac.module.Controler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.projetpo.bindingofisaac.module.Model.Ennemi;
import com.projetpo.bindingofisaac.module.Model.Ennemis.Fly;
import com.projetpo.bindingofisaac.module.Model.Ennemis.Spider;
import com.projetpo.bindingofisaac.module.Model.Ennemis.Sprinter;
import com.projetpo.bindingofisaac.module.Model.Ennemis.Boss.Boss;
import com.projetpo.bindingofisaac.module.Model.Ennemis.Boss.BossCollectionneur;
import com.projetpo.bindingofisaac.module.Model.Ennemis.Boss.BossFinal;
import com.projetpo.bindingofisaac.module.Model.Ennemis.Boss.BossSatan;
import com.projetpo.bindingofisaac.module.Model.Ennemis.Boss.BossShoot;
import com.projetpo.bindingofisaac.module.Model.Ennemis.Boss.BossWave;
import com.projetpo.bindingofisaac.module.Shaders.Vector2;
import com.projetpo.bindingofisaac.module.Vue.Jeu;

public class ListeEnnemi {

	/*
	 * Liste Chainée d'ennemi
	 */
	private ArrayList<Ennemi> liste;
	
	/*
	 * Constructeur
	 */
	public ListeEnnemi() {
		this.liste = new ArrayList<Ennemi>();
	}
	
	/*
	 * Distribue le loot de l'ennemi
	 */
	public void randomLoot(Ennemi e) {
		if(!e.getLoot().isEmpty()) {
			int randomItems = (int)(Math.random()*(e.getLoot().size()));
			e.getLoot().get(randomItems).setPosition(e.getPosition());
			Jeu.gameWorld.getMapEnCours().getcarte().getObjet().add(e.getLoot().get(randomItems));
		}
		if(e instanceof Boss || e instanceof BossCollectionneur || e instanceof BossFinal || e instanceof BossSatan || e instanceof BossShoot || e instanceof BossWave) {
			int randomItems = (int)(Math.random()*(e.getLoot().size()));
			e.getLoot().get(randomItems).setPosition(new Vector2(new Random().nextInt(11*65)+65, new Random().nextInt(6*65)+65));
			Jeu.gameWorld.getMapEnCours().getcarte().getObjet().add(e.getLoot().get(randomItems));
		}
	}
	
	/**
	 * @return Update tous les ennemis
	 */
	public void updateEnnemis() {
		ArrayList<Ennemi> copieListe = (ArrayList<Ennemi>) liste.clone();
		for(Ennemi e: liste) {  //On parcours un par un les ennemis
			if(e.doRemove(e)) {  //On les enlèves si besoin
				if(e instanceof BossSatan) {
					for(int i=1; i<12; i++) {
						for(int j=1; j<8; j++) {
							Jeu.gameWorld.getMapEnCours().getcarte().setRenderMap(i, j, 0);
						}
					}
				}
				copieListe.remove(e);
				this.randomLoot(e);
				playDeathSound(e);
			} else {
				e.boucleCooldownEnnemi();
				e.IAEnnemi(Jeu.gameWorld.getPlayer());
			}
			if(e instanceof BossCollectionneur) {
				switch(((BossCollectionneur) e).getlaunchEnnemi()) {
				case 1: copieListe.add(new Fly(25, 25, e.getPosition(),"src/main/resources/fly.png", 5.85/8));break;
				case 2: copieListe.add(new Spider(25, 25,  e.getPosition(),"src/main/resources/Spider.png", 11.7));break;
				case 3: copieListe.add(new Sprinter(25, 25,  e.getPosition(), 5.85*3, "src/main/resources/Dart_Fly.png"));break;}
			}
		}
		liste = copieListe;
	}

	private void playDeathSound(Ennemi e) {
		if(e instanceof Boss) {
			try {
				Jeu.music("/com/projetpo/bindingofisaac/module/libMusic/boss_death.wav", false);
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
