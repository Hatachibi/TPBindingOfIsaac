package com.projetpo.bindingofisaac.module.Model;

import java.util.LinkedList;
import com.projetpo.bindingofisaac.module.Shaders.Vector2;
import com.projetpo.bindingofisaac.module.Vue.Fenetre;
import com.projetpo.bindingofisaac.module.Vue.Render;
import com.projetpo.bindingofisaac.module.Vue.Texture;

public class Animation {

	/*
	 * Liste des animations
	 */
	private LinkedList<Texture> liste;
	
	/*
	 * Nombre de fps de l'animation
	 */
	private int fps;
	
	/*
	 * Position de l'image
	 */
	private Vector2 position;
	
	/*
	 * Entite qui sera draw selon son url
	 */
	private Entite entite;
	
	/*
	 * Liste de String contenant les Urls
	 */
	private LinkedList<String> urlListe;
	
	/*
	 * Taille de l'image
	 */
	private Vector2 size;
	
	/*
	 * Constructeur 1
	 */
	public Animation(LinkedList<Texture> liste, int fps, Vector2 position, Vector2 size) {
		this.setListe(liste);
		this.setFps(fps);
		this.position = position;
		this.size = size;
	}
	
	/*
	 * Constructeur 2
	 */
	public Animation(Entite entite, int fps, LinkedList<String> urlListe, Vector2 position, Vector2 size) {
		this.entite = entite;
		this.urlListe = urlListe;
		this.setFps(fps);
		this.position = position;
		this.size = size;
	}

	/**
	 * @Note Fonction d'animation
	 */
	public void anim() {
		int i = liste.size();
		int indice = 1;
		int min = (indice-1) * fps / i;
		for(Texture t: liste) {
			if(Fenetre.tick%fps >= min && Fenetre.tick%fps < indice*fps/i) {
				t.bind();
				Render.getInstance().drawPicture((float)position.getX(), (float)position.getY(), (int)size.getX(), (int)size.getY());
				t.unbind();
			}
			indice ++;
			min = (indice-1) * fps / i;
			i -= 1;
		}
	}
	
	/**
	 * @Note Anim + change l'url
	 */
	public void animUrl() {
		int i = urlListe.size();
		int indice = 1;
		int min = (indice-1) * fps / i;
		for(String s: urlListe) {
			if(Fenetre.tick%fps >= min && Fenetre.tick%fps < indice*fps/i) {
				entite.setUrl(s);
				entite.drawEntite();
			}
			indice ++;
			min = (indice-1) * fps / i;
			i -= 1;
		}
		entite.setUrl(urlListe.get(0));
	}
	
	/*
	 * Getters & Setters
	 */
	public LinkedList<Texture> getListe() {
		return liste;
	}

	public void setListe(LinkedList<Texture> liste) {
		this.liste = liste;
	}

	public int getFps() {
		return fps;
	}

	public void setFps(int fps) {
		this.fps = fps;
	}

	public Vector2 getSize() {
		return size;
	}

	public void setSize(Vector2 size) {
		this.size = size;
	}
	
}
