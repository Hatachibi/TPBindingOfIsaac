package Model;

import Shaders.Vector2;

public class Heart extends ObjetsInventaire {
	
	public Heart(int width, int heigth, Vector2 position, String url) {
		super(width, heigth, position, url);
	}

	@Override
	public void update() {
		
	}

	@Override
	public boolean collisionJoueur(Personnage p) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
