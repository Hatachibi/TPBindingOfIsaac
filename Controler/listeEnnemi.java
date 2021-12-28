package Controler;

import java.util.LinkedList;
import java.util.List;

import Model.Ennemi;
import Model.Jeu;
import Shaders.Vector2;

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
	
	/*
	 * Distribue le loot de l'ennemi
	 */
	public void randomLoot(Ennemi e) {
		for(int i=0; i<e.getLoot().size(); i++) {
			System.out.println("hello");
			e.getLoot().get(i).setPosition(e.getPosition());
			Jeu.room.getMapEnCours().getObjet().add(e.getLoot().get(i));
			System.out.println(e.getLoot().get(i).getPosition().toString());
		}
	}
	
	/**
	 * @return Update tous les ennemis
	 */
	public void updateEnnemis() {
		LinkedList<Ennemi> copieListe = (LinkedList<Ennemi>) liste.clone();
		for(Ennemi e: liste) {  //On parcours un par un les ennemis
			if(e.doRemove(e)) {  //On les enlèves si besoin
				copieListe.remove(e);
				this.randomLoot(e);
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
