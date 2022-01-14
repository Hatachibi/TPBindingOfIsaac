package com.projetpo.bindingofisaac.module.Model.Ennemis;

import com.projetpo.bindingofisaac.module.Model.Ennemi;
import com.projetpo.bindingofisaac.module.Model.Jeu;
import com.projetpo.bindingofisaac.module.Model.Personnage;
import com.projetpo.bindingofisaac.module.Shaders.Vector2;
import com.projetpo.bindingofisaac.module.Vue.Fenetre;

public class Parabite extends Ennemi {
	
	/*
	 * Random au niveau du CoolDown
	 */
	private int random;
	
	/*
	 * Constructeur
	 */
	public Parabite(int width, int heigth, Vector2 position, double speed, String url) {
		super(width, heigth, position, speed, url);
		this.setLife(15);
		this.setDegat(1);
		this.tick = 0;
		this.random = (int) (Math.random()*Fenetre.getInstance().getFPS());
	}

	@Override
	public void drawEnnemi() {
		if(tick > random + Fenetre.getInstance().getFPS()/3) {
			this.drawEntite();
		}
	}

	@Override
	public void IAEnnemi(Personnage p) {
		if(tick == random) {
			setDirection(new Vector2(p.getPosition().getX(), p.getPosition().getY()));
		} else if(tick == random + Fenetre.getInstance().getFPS()/3) {
			setPosition(this.getDirection());
		} else if(tick == Fenetre.getInstance().getFPS()*3) {
			tick = 0;
		} if(tick < random + -1 - Fenetre.getInstance().getFPS()/3) {
			setPosition(new Vector2(0, 0));
		}
		tick ++;
	}

}
