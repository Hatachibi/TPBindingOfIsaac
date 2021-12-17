package Model;

import Controler.ListeBalle;
import Shaders.Vector2;

public class Entite {
	
	protected Vector2 position;
	protected Hitbox hitbox;
	protected int width;
	protected int heigth;
	protected String url;
	
	public Entite(int width, int heigth, Vector2 position, String url) {
    	this.hitbox = new Hitbox(position, width, heigth);
    	this.position = position;
    	this.width = width;
    	this.heigth = heigth;
    	this.url = url;
    	this.hitbox = new Hitbox(position, width, heigth);
	}
	
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
