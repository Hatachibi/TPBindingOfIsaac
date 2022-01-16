package com.projetpo.bindingofisaac.module.Model;

import com.projetpo.bindingofisaac.module.Vue.Render;
import com.projetpo.bindingofisaac.module.Vue.Texture;

public class BarreDeVie {

	/**
	 * Entier représentant le max de vie 
     */
    private int viePleine;

    /**
     * Entier représentant la vie en cours
     */
    private int vieEnCours;
    
    private int blueHeart;
    
    private int blackHeart;
    
    /**
     * Constructeur
     */
    public BarreDeVie(int tailleVie) {
    	this.setViePleine(tailleVie);
    	this.setVieEnCours(tailleVie);
    	this.blueHeart = 0;
    	this.setBlackHeart(2);
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
    	for(int i=0; i<this.viePleine - this.vieEnCours - 1; i++) {
    		if(i%2 ==  0) {
    			Texture.emptyHeart.bind();    	
    			Render.getInstance().drawPicture(x, y, 25, 25);
    			x += 25;
    		}
    	}
    	Texture.emptyHeart.unbind();
    	for(int i=0; i<blackHeart; i+=2) {
    		Texture.blackHeart.bind();
    		Render.getInstance().drawPicture(x, y, 25, 25);
    		x += 25;
    	}
    	Texture.blackHeart.unbind();
    	for(int i=0; i<blueHeart; i+=2) {
    		if(i==blueHeart-1) {
    			Texture.halfBlueHeart.bind();
    			Render.getInstance().drawPicture(x, y, 25, 25);
    		} else {
    			Texture.blueHeart.bind();
    			Render.getInstance().drawPicture(x, y, 25, 25);
    		}
    		x += 25;
    	}
    	Texture.halfBlueHeart.unbind();
    	Texture.blueHeart.unbind();
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
	public int getBlueHeart() {
		return blueHeart;
	}

	public void setBlueHeart(int blueHeart) {
		this.blueHeart = blueHeart;
	}

	public int getBlackHeart() {
		return blackHeart;
	}

	public void setBlackHeart(int blackHeart) {
		this.blackHeart = blackHeart;
	}

}