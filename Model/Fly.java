package Model;

import Shaders.Vector2;
import Vue.Render;
import Vue.Texture;

public class Fly extends Ennemi{

	public Fly(int width, int heigth, Vector2 position, String url, double speed, int life) {
		super(width, heigth, position, speed, url, life);
	}
	
	public void drawEnnemi() {
		Texture.fly.bind();
		Render.getInstance().drawPicture((float)position.getX(), (float)position.getY(), 25, 25, 1, 1, new float[] {});
		Texture.fly.unbind();
	}

	public void IAEnnemi(Personnage p) {
		setDirection(new Vector2(p.getPosition().getX() - position.getX(), p.getPosition().getY() - position.getY()));
		this.move();
	}

}
