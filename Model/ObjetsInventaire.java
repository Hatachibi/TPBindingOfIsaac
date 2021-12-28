package Model;

import Shaders.Vector2;

public abstract class ObjetsInventaire extends Entite{

	/**
     * 
     */
    private String nom;

    /**
     * 
     */
    protected Integer id;

    /**
     * 
     */
    private Integer quantite;
    
    /*
     * Boolean qui indique si l'objet est rammassé
     */
    private boolean isTouch;
	
	/*
	 * Constructeur
	 */
    public ObjetsInventaire(int width, int heigth, Vector2 position, String url) {
		super(width, heigth, position, url);
	}
	
	public abstract void update();
	
	public abstract boolean collisionJoueur(Personnage p);

	/*
	 * Getters & Setters 
	 */
	public boolean isTouch() {
		return isTouch;
	}

	public void setTouch(boolean isTouch) {
		this.isTouch = isTouch;
	}
    
}