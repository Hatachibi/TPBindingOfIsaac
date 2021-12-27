package Controler;

import java.util.LinkedList;
import java.util.List;

import Model.Ennemi;
import Model.Jeu;

public class listeEnnemi {

	/*
	 * Liste Chainée d'ennemi
	 */
	private LinkedList<Ennemi> liste;
	
	/*
	 * Constructeur
	 */
	public listeEnnemi() {
		this.liste = new LinkedList<Ennemi>();
	}
	
	/**
	 * @return Update tous les ennemis
	 */
	public void updateEnnemis() {
		LinkedList<Ennemi> copieListe = (LinkedList<Ennemi>) liste.clone();
		for(Ennemi e: liste) {  //On parcours un par un les ennemis
			if(e.doRemove(e)) {  //On les enlèves si besoin
				copieListe.remove(e);
			} else {
				e.boucleCooldownEnnemi();
				e.IAEnnemi(Jeu.room.getPlayer());
			}
		}
		liste = copieListe;
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

	public void setListe(LinkedList<Ennemi> liste) {
		this.liste = liste;
	}
	
}
