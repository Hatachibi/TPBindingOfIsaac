package com.projetpo.bindingofisaac.module.Model;

public class Inventaire {
	
	/*
	 * Nombre de bombe du joueur
	 */
	private int nbBombe;
	
	/**
	 * Nombre de clef du joueur
	 */
	private int nbClef;

	/*
	 * Constructeur
	 */
    public Inventaire() {
    	this.nbBombe = 0;
    	this.nbClef = 0;
    }

    /*
     * Getters & Setters
     */

	public int getNbBombe() {
		return nbBombe;
	}

	public void setNbBombe(int nbBombe) {
		this.nbBombe = nbBombe;
	}

	public int getNbClef() {
		return nbClef;
	}

	public void setNbClef(int nbClef) {
		this.nbClef = nbClef;
	}

}