package com.projetpo.bindingofisaac.module.Model;

public class Inventaire {
	
	/*
	 * Tableau contenant tout les objets du joueur
	 */
	private ObjetsInventaire[] objets;
	
	/*
	 * Nombre de bombe du joueur
	 */
	private int nbBombe;

	/*
	 * Constructeur
	 */
    public Inventaire() {
    	this.setObjets(new ObjetsInventaire[5]); 
    }

    /*
     * Getters & Setters
     */
	public ObjetsInventaire[] getObjets() {
		return objets;
	}

	public void setObjets(ObjetsInventaire[] objets) {
		this.objets = objets;
	}
	
	public void setObjetInv(ObjetsInventaire objet, int i) {
		this.objets[i] = objet;
	}

	public int getNbBombe() {
		return nbBombe;
	}

	public void setNbBombe(int nbBombe) {
		this.nbBombe = nbBombe;
	}

}