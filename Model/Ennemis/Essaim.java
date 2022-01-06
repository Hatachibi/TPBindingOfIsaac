package com.projetpo.bindingofisaac.module.Model.Ennemis;

import com.projetpo.bindingofisaac.module.Model.Ennemi;
import com.projetpo.bindingofisaac.module.Model.Personnage;
import com.projetpo.bindingofisaac.module.Shaders.Vector2;

public class Essaim extends Ennemi {

	public Essaim(int width, int heigth, Vector2 position, double speed, String url) {
		super(width, heigth, position, speed, url);
	}

	@Override
	public void drawEnnemi() {
		
	}

	@Override
	public void IAEnnemi(Personnage p) {
		
	}

}
