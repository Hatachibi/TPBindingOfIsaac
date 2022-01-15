package com.projetpo.bindingofisaac.module.Model;

import com.projetpo.bindingofisaac.module.Vue.Render;
import com.projetpo.bindingofisaac.module.Vue.Texture;

public class BarreDeVie {

	/**
	 * Entier repr�sentant le max de vie 
     */
    private int viePleine;

    /**
     * Entier repr�sentant la vie en cours
     */
    private int vieEnCours;
    
    /**
     * Constructeur
     */
    public BarreDeVie(int tailleVie) {
    	this.setViePleine(tailleVie);
    	this.setVieEnCours(tailleVie);
    }
    
    /**
     * @return Dessine la Barre de Vie du joueur
     */
    public void drawBarDeVie() {
    	float x = 25;
    	float y = 540;   
    	for(int i=0; i<vieEnCours; i+=2) {
    		if(i==vieEnCours-1) {
    			Texture.halfHeart.bind();
    			Render.getInstance().drawPicture(x, y, 25, 25);
    		} else {
    			Texture.heart.bind();
    			Render.getInstance().drawPicture(x, y, 25, 25);
    		}
    		x += 25;
    	}
    	Texture.halfHeart.unbind();
    	Texture.heart.unbind();
    	int max = viePleine-vieEnCours;
    	if(max%2==1) max = viePleine-vieEnCours-1;
    	for(int i=0; i<max; i+=2) {
    			Texture.emptyHeart.bind();    	
    			Render.getInstance().drawPicture(x, y, 25, 25);
        	x += 25;
    	}
    	Texture.emptyHeart.unbind();
    }
    
	/*
	 * Getters & Setters
	 */
    public int getViePleine() {
		return viePleine;
	}

	public void setViePleine(int viePleine) {
		this.viePleine = viePleine;
	}

	public int getVieEnCours() {
		return vieEnCours;
	}

	public void setVieEnCours(int vieEnCours) {
		this.vieEnCours = vieEnCours;
	}

}