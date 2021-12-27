package Model;

import Shaders.Vector2;
import Vue.Fenetre;
import Vue.Render;
import Vue.Texture;

public class Spider extends Ennemi{
	
	/*
	 * Couple (x, y) de coordonn�es al�atoire pour le d�placement
	 */
	private Vector2 random;

	/*
	 * Constructeur
	 */
	public Spider(int width, int heigth, Vector2 position, double speed, String url, int life) {
		super(width, heigth, position, speed, url, life);
		this.random = new Vector2(0, 0);
		this.setDegat(2);
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
			if(position.getX() > 65 && position.getX() < 520 && position.getY() > 65 && position.getY() < 520) {
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
	 * @Note M�me fonction que l'IA suaf qu'elle est en static
	 */
	public static void IASpider(Personnage p, Boss b) {
		if(Fenetre.tick == 30) {
			b.getRandom().setX(Math.random() - 0.5);
			b.getRandom().setY(Math.random() - 0.5);
		}
		if(Fenetre.tick > 30) {
			if(b.getPosition().getX() > 65 && b.getPosition().getX() < 520 && b.getPosition().getY() > 65 && b.getPosition().getY() < 520) {
				b.setDirection((new Vector2(b.getPosition().getX()*b.getRandom().getX(), b.getPosition().getY()*b.getRandom().getY())));
				b.move();
			} else {
				b.setDirection(new Vector2(p.getPosition().getX() - b.getPosition().getX(), p.getPosition().getY() - b.getPosition().getY()));
				b.move();
			} 
			
		}
	} 

}
