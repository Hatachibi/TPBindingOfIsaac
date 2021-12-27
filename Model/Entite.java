package Model;

import Shaders.Vector2;
import Vue.Render;
import Vue.Texture;

public class Entite {
	
	/*
	 * Position de l'entite
	 */
	protected Vector2 position;
	
	/*
	 * Hitbox de l'entite
	 */
	protected Hitbox hitbox;
	
	/*
	 * Largeur de l'entite
	 */
	protected int width;
	
	/*
	 * Longueur de l'entite
	 */
	protected int heigth;
	
	/*
	 * Url du fichier png
	 */
	protected String url;
	
	/*
	 * Constructeur
	 */
	public Entite(int width, int heigth, Vector2 position, String url) {
    	this.hitbox = new Hitbox(position, width, heigth);
    	this.position = position;
    	this.width = width;
    	this.heigth = heigth;
    	this.url = url;
    	this.hitbox = new Hitbox(position, width, heigth);
	}
	
	/**
	 * @return Dessine l'entite
	 */
	public void drawEntite() {
		Texture entiteTexture = Texture.loadTexture(url);
		entiteTexture.bind();
		Render.getInstance().drawPicture((float)hitbox.getPosition().getX(),(float) hitbox.getPosition().getY(), entiteTexture.getWidth()*2, entiteTexture.getHeight()*2);
		entiteTexture.unbind();
	}
	
	/*
	 * Getters & Setters
	 */
	public Vector2 getPosition() {
		return position;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeigth() {
		return heigth;
	}

	public void setHeigth(int heigth) {
		this.heigth = heigth;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Hitbox getHitbox() {
		return hitbox;
	}

	public void setHitbox(Hitbox hitbox) {
		this.hitbox = hitbox;
	}
	
}
