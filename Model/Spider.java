package Model;

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
	public Spider(int width, int heigth, Vector2 position, double speed, String url, int life) {
		super(width, heigth, position, speed, url, life);
		this.random = new Vector2(0, 0);
		this.setDegat(2);
		this.getLoot().add(new Piece(10, 10, position, "libImg/Penny.png", 5));
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
			if(position.getX() > 65 && position.getX() < 520-width && position.getY() > 65 && position.getY() < 520-heigth) {
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
	 * @Note Même fonction que l'IA suaf qu'elle est en static
	 */
	public static void IASpider(Personnage p, Boss b) {
		if(Fenetre.tick == 30) {
			b.getRandom().setX(Math.random() - 0.5);
			b.getRandom().setY(Math.random() - 0.5);
		}
		if(Fenetre.tick > 30) {
			if(b.getPosition().getX() > 65 && b.getPosition().getX() < 520-b.width && b.getPosition().getY() > 65 && b.getPosition().getY() < 520-b.heigth) {
				b.setDirection((new Vector2(b.getPosition().getX()*b.getRandom().getX(), b.getPosition().getY()*b.getRandom().getY())));
				b.move();
			} else {
				b.setDirection(new Vector2(p.getPosition().getX() - b.getPosition().getX(), p.getPosition().getY() - b.getPosition().getY()));
				b.move();
			} 
			
		}
	} 

}
