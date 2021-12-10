package Model;

import java.util.*;

import Controler.DeplacerPersonnage;
import Controler.ListeBalle;

public class Personnage {

	private DeplacerPersonnage deplacement;
	private ListeBalle munitions;
	private int degat;
	private double multiplicator;
	private int range;
	private BarreDeVie life;
	private Inventaire inv;
	private Hitbox hit;
	private Entite entity;
	private String url;
	
    public Personnage(int degat, double x, double y, int width, int heigth) {
    	this.degat = 2;
    	this.entity = new Entite(width, heigth, x, y);
    	this.multiplicator = 1.0;
    	this.life = new BarreDeVie(10);
    	this.deplacement = new DeplacerPersonnage(new Entite(width, heigth, x, y));
    	this.munitions = new ListeBalle();
    }
    
    public double attaque() {
    	return degat*multiplicator;
    }
    
    public void subitDegats(double degats) {
    	life.setVieEnCours((int)(life.getVieEnCours() - degats));
    }
    
    public boolean isAlive() {
    	return life.getVieEnCours() <= 0;
    }

	public DeplacerPersonnage getDeplacement() {
		return deplacement;
	}

	public void setDeplacement(DeplacerPersonnage deplacement) {
		this.deplacement = deplacement;
	}

	public ListeBalle getMunitions() {
		return munitions;
	}

	public void setMunitions(ListeBalle munitions) {
		this.munitions = munitions;
	}

	public int getDegat() {
		return degat;
	}

	public void setDegat(int degat) {
		this.degat = degat;
	}

	public double getMultiplicator() {
		return multiplicator;
	}

	public void setMultiplicator(double multiplicator) {
		this.multiplicator = multiplicator;
	}

	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}

	public BarreDeVie getLife() {
		return life;
	}

	public void setLife(BarreDeVie life) {
		this.life = life;
	}

	public Inventaire getInv() {
		return inv;
	}

	public void setInv(Inventaire inv) {
		this.inv = inv;
	}

	public Hitbox getHit() {
		return hit;
	}

	public void setHit(Hitbox hit) {
		this.hit = hit;
	}

	public Entite getEntity() {
		return entity;
	}

	public void setEntity(Entite entity) {
		this.entity = entity;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
    

}