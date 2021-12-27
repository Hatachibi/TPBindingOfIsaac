package Model;

import Controler.ListeBalle;
import Shaders.Vector2;
import Vue.Fenetre;
import Vue.Render;
import Vue.Texture;

public class Fly extends Ennemi{
	
	private ListeBalle munitions;

	public Fly(int width, int heigth, Vector2 position, String url, double speed, int life) {
		super(width, heigth, position, speed, url, life);
		this.munitions = new ListeBalle();
		this.munitions.setEnnemiBalle(true);
	}
	
	public void drawEnnemi() {
		Texture.fly.bind();
		Render.getInstance().drawPicture((float)position.getX(), (float)position.getY(), 25, 25, 1, 1, new float[] {});
		Texture.fly.unbind();
		munitions.drawBalle();
	}

	public void IAEnnemi(Personnage p) {
		if(Fenetre.tick == 0%60) {
			munitions.addBalle(new Balle(25, 25, position.getX(), position.getY(), 1, ""));
			munitions.addBalle(new Balle(25, 25, position.getX(), position.getY(), 2, ""));
			munitions.addBalle(new Balle(25, 25, position.getX(), position.getY(), 3, ""));
			munitions.addBalle(new Balle(25, 25, position.getX(), position.getY(), 4, ""));
		} if(Fenetre.tick == 30%60) {
			munitions.addBalle(new Balle(25, 25, position.getX(), position.getY(), 5, ""));
			munitions.addBalle(new Balle(25, 25, position.getX(), position.getY(), 6, ""));
			munitions.addBalle(new Balle(25, 25, position.getX(), position.getY(), 7, ""));
			munitions.addBalle(new Balle(25, 25, position.getX(), position.getY(), 8, ""));
		}
		setDirection(new Vector2(p.getPosition().getX() - position.getX(), p.getPosition().getY() - position.getY()));
		this.move();
	}
	
	public static void IAFly(Personnage p, Ennemi e) {
		e.setDirection(new Vector2(p.getPosition().getX() - e.getPosition().getX(), p.getPosition().getY() - e.getPosition().getY()));
		e.move();
	}

}
