package Model;

import Shaders.Vector2;

public class Soin extends ObjetsInventaire {

	private Integer santeEnPlus;
	
	public Soin(int width, int heigth, Vector2 position, String url) {
		super(width, heigth, position, url);
		// TODO Auto-generated constructor stub
	}

    public Integer getSanteEnPlus() {
		return santeEnPlus;
	}

	public void setSanteEnPlus(Integer santeEnPlus) {
		this.santeEnPlus = santeEnPlus;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean collisionJoueur(Personnage p) {
		// TODO Auto-generated method stub
		return false;
	}

}