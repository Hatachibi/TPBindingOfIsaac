package Model;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import Shaders.Vector2;
import Vue.Fenetre;
import Vue.Render;
import Vue.Texture;

public class Spider extends Ennemi{
	
	private Vector2 random;

	public Spider(int width, int heigth, Vector2 position, double speed, String url, int life) {
		super(width, heigth, position, speed, url, life);
		this.random = new Vector2(0, 0);
	}
	
	@Override
	public void drawEnnemi() {
		Texture entiteTexture = Texture.loadTexture(url);
		entiteTexture.bind();
		Render.getInstance().drawPicture((float)getPosition().getX(),(float) getPosition().getY(), entiteTexture.getWidth()*2, entiteTexture.getHeight()*2);
		entiteTexture.unbind();
	}

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

}
