package com.projetpo.bindingofisaac.module.Model.Ennemis;

import com.projetpo.bindingofisaac.module.Model.Ennemi;
import com.projetpo.bindingofisaac.module.Model.Jeu;
import com.projetpo.bindingofisaac.module.Model.Personnage;
import com.projetpo.bindingofisaac.module.Shaders.Vector2;

public class Parabite extends Ennemi {
	
	/*
	 * Cooldown entre chaque phase
	 */
	private int tickCoolDown;
	
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
		this.tickCoolDown = 0;
		this.random = (int) (Math.random()*60);
	}

	@Override
	public void drawEnnemi() {
		if(tickCoolDown > random + 20) {
			this.drawEntite();
		}
	}

	@Override
	public void IAEnnemi(Personnage p) {
		if(tickCoolDown == random) {
			setDirection(new Vector2(p.getPosition().getX(), p.getPosition().getY()));
		} else if(tickCoolDown == random + 20) {
			setPosition(this.getDirection());
		} else if(tickCoolDown == 180) {
			tickCoolDown = 0;
		} if(tickCoolDown < random + 19) {
			setPosition(new Vector2(0, 0));
		}
		tickCoolDown ++;
	}

}
