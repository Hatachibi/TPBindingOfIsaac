package Model;

import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import Shaders.Vector2;
import Vue.Fenetre;
import Vue.Render;
import Vue.Texture;

public class Spider extends Ennemi{
	
	/*
	 * Couple (x, y) de coordonnées aléatoire pour le déplacement
	 */
	private Vector2 random;

	/*
	 * Constructeur
	 */
	public Spider(int width, int heigth, Vector2 position, String url, double speed) {
		super(width, heigth, position, speed, url);
		this.random = new Vector2(0, 0);
		this.setDegat(1);
		this.setLife(5);
		for(int i=1; i<13; i++) {
			this.getLoot().add(new ObjetsInventaire(i, 10, 10, position, ""));
		}
	}
	
	/**
	 * @return Dessine l'ennemi
	 */
	@Override
	public void drawEnnemi() {
		Texture entiteTexture = Texture.loadTexture(url);
		entiteTexture.bind();
		Render.getInstance().drawPicture((float)getPosition().getX(),(float) getPosition().getY(), entiteTexture.getWidth()*2, entiteTexture.getHeight()*2);
		entiteTexture.unbind();
	}
	
	public boolean isMur() {
		return (Jeu.room.getMapEnCours().getCollisionMap()[(int)(position.getX()/65)][(int)(position.getY()/65)]);
	}
	
	public static boolean isMurStatic(Boss b) {
		return (Jeu.room.getMapEnCours().getCollisionMap()[(int)(b.getPosition().getX()/65)][(int)(b.getPosition().getY()/65)]);
	}

	/**
	 * @return IA de l'ennemi
	 */
	@Override
	public void IAEnnemi(Personnage p) {
		if(Fenetre.tick == 30) {
			random.setX(Math.random() - 0.5);
			random.setY(Math.random() - 0.5);
		}
		if(Fenetre.tick > 30) {
			if(position.getX() > 65 && position.getX() < 520-width && position.getY() > 65 && position.getY() < 520-heigth && !isMur()) {
				setDirection(new Vector2(position.getX()*random.getX(), position.getY()*random.getY()));
				this.move();
			} else {
				setDirection(new Vector2(p.getPosition().getX() - position.getX(), p.getPosition().getY() - position.getY()));
				this.move();
			} 
			
		}
	} 
	
	/**
	 * @param p un personnage 
	 * @param b une balle
	 * @Note Même fonction que l'IA sauf qu'elle est en static
	 */
	public static void IASpider(Personnage p, Boss b) {
		if(Fenetre.tick == 30) {
			b.getRandom().setX(Math.random() - 0.5);
			b.getRandom().setY(Math.random() - 0.5);
		}
		if(Fenetre.tick > 30) {
			if(b.getPosition().getX() > 65 && b.getPosition().getX() < 520-b.width && b.getPosition().getY() > 65 && b.getPosition().getY() < 520-b.heigth && !Spider.isMurStatic(b)) {
				b.setDirection((new Vector2(b.getPosition().getX()*b.getRandom().getX(), b.getPosition().getY()*b.getRandom().getY())));
				b.move();
			} else {
				b.setDirection(new Vector2(p.getPosition().getX() - b.getPosition().getX(), p.getPosition().getY() - b.getPosition().getY()));
				b.move();
			} 
			
		}
	} 

}
