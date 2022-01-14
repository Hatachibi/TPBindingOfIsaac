package com.projetpo.bindingofisaac.module.Model.Ennemis;

import java.util.LinkedList;

import com.projetpo.bindingofisaac.module.Model.Bombe;
import com.projetpo.bindingofisaac.module.Model.Ennemi;
import com.projetpo.bindingofisaac.module.Model.Personnage;
import com.projetpo.bindingofisaac.module.Shaders.Vector2;
import com.projetpo.bindingofisaac.module.Vue.Fenetre;

public class Bomberman extends Ennemi {
	
	private LinkedList<Bombe> bombList;
	
	public Bomberman(int width, int heigth, Vector2 position, double speed, String url) {
		super(width, heigth, position, speed, url);
		this.bombList = new LinkedList<Bombe>();
		this.tick = 0;
		this.setLife(10);
	}

	@Override
	public void drawEnnemi() {
		this.drawEntite();
		for(Bombe b: bombList) {
			b.draw();
		}
	}

	@Override
	public void IAEnnemi(Personnage p) {
		if(!bombList.isEmpty()) {
			LinkedList<Bombe> copieListe = (LinkedList<Bombe>) bombList.clone();
			for(Bombe b: bombList) {
				if(b.isDoRemove()) {
					copieListe.remove(b);
				}
				b.update();
			}
			bombList = copieListe;
		}
		if(tick == 0) {
			Bombe b = new Bombe(-3, width, heigth, new Vector2(65 + Math.random()*(Fenetre.WidthFenetre - 130 - width), 65 + Math.random()*(Fenetre.HeigthFenetre - 130 - heigth)), "src/main/resources/bomb.png");
			b.setBombEnnemi(true);
			b.setRange(1);
			b.setDegat(1);
			this.bombList.add(b);
		}
		tick ++;
		if(tick == Fenetre.getInstance().getFPS()) {
			tick = 0;
		}
	}

}
