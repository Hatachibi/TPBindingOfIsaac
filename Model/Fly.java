package Model;

import Shaders.Vector2;
import Vue.Render;
import Vue.Texture;

public class Fly extends Ennemi{

	public Fly(int width, int heigth, Vector2 position, String url) {
		super(width, heigth, position, url);
	}
	
	public void drawFly() {
		Texture.fly.bind();
		Render.getInstance().drawPicture((float)position.getX(), (float)position.getY(), 25, 25, 1, 1, new float[] {});
		Texture.fly.unbind();
	}

}
