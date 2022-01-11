package com.projetpo.bindingofisaac.module.Model.Ennemis;

import com.projetpo.bindingofisaac.module.Model.Ennemi;
import com.projetpo.bindingofisaac.module.Model.Personnage;
import com.projetpo.bindingofisaac.module.Shaders.Vector2;
import com.projetpo.bindingofisaac.module.Vue.Fenetre;

public class Sprinter extends Ennemi {
	
	/*
	 * Cooldown entre chaque phase
	 */
	private int tickCoolDown;
	
	/*
	 * Direction qui suit le joueur
	 */
	private Vector2 directionSauv;

	/*
	 * Constructeur
	 */
	public Sprinter(int width, int heigth, Vector2 position, double speed, String url) {
		super(width, heigth, position, speed, url);
		this.tickCoolDown = 0;
		this.setLife(5);
		this.setDegat(1);
		this.setDirectionSauv(new Vector2(0, 0));
	}

	@Override
	public void drawEnnemi() {
		this.drawEntite();
	//	Render.getInstance().drawSquare((float)position.getX(),(float) position.getY(), width, heigth);
	}

	@Override
	public void IAEnnemi(Personnage p) {
		if(tickCoolDown < 90) {
			tickCoolDown ++;
		} else if(tickCoolDown == 90) {
			directionSauv = new Vector2(p.getPosition().getX() - position.getX(), p.getPosition().getY() - position.getY());
			tickCoolDown ++;
		} else if(tickCoolDown < 110) {
			if(position.getX() > 65 && position.getX() < Fenetre.WidthFenetre-65-width && position.getY() > 65 && position.getY() < Fenetre.WidthFenetre-65-heigth) {
				setDirection(directionSauv);
				move();
			} else if (position.getX() < 65){
				position.setX(66);
			} else if (position.getX() > Fenetre.WidthFenetre - 65 -width){
				position.setX(Fenetre.WidthFenetre-width);
			} else if (position.getY() < 65){
				position.setY(66);
			} else if (position.getY() > Fenetre.HeigthFenetre - 65 - heigth){
				position.setY(Fenetre.HeigthFenetre - heigth);
			}

			tickCoolDown ++;
		} else {
			tickCoolDown = 0;
		}
	}
	
	
	/*
	 * Getters & Setters
	 */
	public int getTickCoolDown() {
		return tickCoolDown;
	}

	public void setTickCoolDown(int tickCoolDown) {
		this.tickCoolDown = tickCoolDown;
	}

	public Vector2 getDirectionSauv() {
		return directionSauv;
	}

	public void setDirectionSauv(Vector2 directionSauv) {
		this.directionSauv = directionSauv;
	}

}
