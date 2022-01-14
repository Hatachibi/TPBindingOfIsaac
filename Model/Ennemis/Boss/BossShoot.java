package com.projetpo.bindingofisaac.module.Model.Ennemis.Boss;

import java.util.ArrayList;

import com.projetpo.bindingofisaac.module.Controler.ListeBalle;
import com.projetpo.bindingofisaac.module.Model.Balle;
import com.projetpo.bindingofisaac.module.Model.Ennemi;
import com.projetpo.bindingofisaac.module.Model.Jeu;
import com.projetpo.bindingofisaac.module.Model.Personnage;
import com.projetpo.bindingofisaac.module.Shaders.Vector2;
import com.projetpo.bindingofisaac.module.Vue.Fenetre;
import com.projetpo.bindingofisaac.module.Vue.Render;
import com.projetpo.bindingofisaac.module.Vue.Texture;

public class BossShoot extends Ennemi{
	
	private ListeBalle munitions;
	
	private int phase;
	
	private int wichDirection;


	public BossShoot(int width, int heigth, Vector2 position, double speed, String url) {
		super(width, heigth, position, speed, url);
		this.setLife(75);
		this.phase = 1;
		this.munitions = new ListeBalle();
		this.munitions.setEnnemiBalle(true);
		this.munitions.setRange(20);
		this.munitions.setDegats(1);
		this.tick = 0;
		this.wichDirection = 1;
		this.position.setX((Fenetre.WidthFenetre/2) - width);
		this.position.setY((Fenetre.HeigthFenetre/2) - heigth);
	}

	
	@Override
	public void drawEnnemi() {
		this.munitions.drawBalle();
		this.drawEntite();
		Texture.bdvBoss.bind();
		Render.getInstance().drawPicture(300, 100, Texture.bdvBoss.getWidth()*2, Texture.bdvBoss.getHeight()*2);
		Texture.bdvBoss.unbind();
		Render.getInstance().drawSquare(340, 105, (float) (this.getLife()*215/75), (float) ((float)Texture.bdvBoss.getHeight()), new float[] {1f, 0f, 0f, 1f});
	}

	@Override
	public void IAEnnemi(Personnage p) {
		this.munitions.update();
		if(phase == 1) {
			this.attaquePhase1();
		} else {
			if(Math.random() > 0.5){
				for(int i=1; i<10; i++) {
					if(tick*2 == tick*3/i) {
						this.attaque(i*0.1);
					}
				}
			} else if(Math.random() > 0.5) {
				for(int i=5; i<15; i++) {
					if(tick*2 == 180/i) {
						this.attaque(i*0.1);
					}
				}
			} else {
				this.attaquePhase1();
			}
		}
		tick++;
		if(this.getLife() < 25){
			this.phase = 2;
		}
		if(tick == 180) {
			tick = 0;
		}
		if(position.equals(new Vector2(this.getPosition().getX() ,Fenetre.	HeigthFenetre - 65 - heigth))) {
			this.wichDirection = 0;
		} else if(position.equals(new Vector2(this.getPosition().getX(),65 + heigth))) {
			this.wichDirection = 1;
		}
		if(this.wichDirection == 0) {
			this.setDirection(new Vector2(0, -1));
		} else {
			this.setDirection(new Vector2(0, 1));
		}
		this.move();
	}
	
	public void attaque(double nb) {
		this.munitions.addBalle(new Balle(25, 25, position.getX() + width/2, position.getY() + heigth/2, new Vector2(nb, 1-nb),"src/main/resources/enemybullets.png", 4 + Math.random()*3));
		this.munitions.addBalle(new Balle(25, 25, position.getX() + width/2, position.getY() + heigth/2, new Vector2(nb, -(1-nb)),"src/main/resources/enemybullets.png", 4 + Math.random()*3));
		this.munitions.addBalle(new Balle(25, 25, position.getX() + width/2, position.getY() + heigth/2, new Vector2(-nb, 1-nb),"src/main/resources/enemybullets.png", 4 + Math.random()*3));
		this.munitions.addBalle(new Balle(25, 25, position.getX() + width/2, position.getY() + heigth/2, new Vector2(-nb, -(1-nb)),"src/main/resources/enemybullets.png", 4 + Math.random()*3));
	}
	
	public void attaquePhase1() {
		if(tick == 60) {
			this.attaque(0.5);
		}
		if(tick == 120) {
			this.attaque(0.5);
			this.attaque(1);
		}
		if(tick == 180) {
			this.attaque(0.25);
			this.attaque(0.75);
			this.attaque(1);
		}
	}

	
	/*
	 * Getters & Setters
	 */
	public ListeBalle getMunitions() {
		return munitions;
	}
	
	public void setMunitions(ListeBalle munitions) {
		this.munitions = munitions;
	}

	
}
