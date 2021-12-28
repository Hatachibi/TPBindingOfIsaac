package Model;

import Shaders.Vector2;

public class Piece extends ObjetsInventaire{
	
	/*
	 * Valeur de la piece
	 */
	private int value;

	/*
	 * Constructeur
	 */
	public Piece(int width, int heigth, Vector2 position, String url, int value) {
		super(width, heigth, position, url);
		this.setValue(value);
	}
	
	/**
	 * @return S'il il y a une collision entre le joueur et une piece
	 */
	@Override
	public boolean collisionJoueur(Personnage p) {
		return Hitbox.rectangleCollision(position, new Vector2(width, heigth), p.getPosition(), new Vector2(p.getWidth(), p.getHeigth()));
	}
	
	/**
	 * @return Update la piece
	 */
	@Override
	public void update() {
		if(this.collisionJoueur(Jeu.room.getPlayer())) {
			Jeu.room.getPlayer().setCoin(Jeu.room.getPlayer().getCoin() + value);
			this.setTouch(true);
		}
	}
	
	/*
	 * Getters & Setters
	 */
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

}
