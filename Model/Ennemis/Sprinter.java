package com.projetpo.bindingofisaac.module.Model.Ennemis;

import com.projetpo.bindingofisaac.module.Model.Ennemi;
import com.projetpo.bindingofisaac.module.Model.Personnage;
import com.projetpo.bindingofisaac.module.Shaders.Vector2;
import com.projetpo.bindingofisaac.module.Vue.Fenetre;

public class Sprinter extends Ennemi {
	
	/*
	 * Direction qui suit le joueur
	 */
	private Vector2 directionSauv;

	/*
	 * Constructeur
	 */
	public Sprinter(int width, int heigth, Vector2 position, double speed, String url) {
		super(width, heigth, position, speed, url);
		this.tick = 0;
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
		if(tick < Fenetre.getInstance().getFPS()*1.5) {
			tick ++;
		} else if(tick == Fenetre.getInstance().getFPS()*1.5) {
			directionSauv = new Vector2(p.getPosition().getX() - position.getX(), p.getPosition().getY() - position.getY());
			tick ++;
		} else if(tick < Fenetre.getInstance().getFPS()*1.5 + Fenetre.getInstance().getFPS()/3) {
				if(position.getX() < 65) {
					setDirection(new Vector2(1, this.directionSauv.getY()));
				} 
				else if(position.getX() > Fenetre.WidthFenetre - 65-width) {
					setDirection(new Vector2(-1, this.directionSauv.getY()));
				}
				else if(position.getY() < 65) {
					setDirection(new Vector2(this.directionSauv.getX(), 1));
				}
				else if(position.getY() > Fenetre.HeigthFenetre - 65-heigth) {
					setDirection(new Vector2(this.directionSauv.getX(), -1));
				} else {
					setDirection(directionSauv);
				}
				move();

			tick ++;
		} else {
			tick = 0;
		}
	}
	
	
	/*
	 * Getters & Setters
	 */
	public Vector2 getDirectionSauv() {
		return directionSauv;
	}

	public void setDirectionSauv(Vector2 directionSauv) {
		this.directionSauv = directionSauv;
	}

}
