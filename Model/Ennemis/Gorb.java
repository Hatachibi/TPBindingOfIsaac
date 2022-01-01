package com.projetpo.bindingofisaac.module.Model.Ennemis;

import com.projetpo.bindingofisaac.module.Controler.ListeBalle;
import com.projetpo.bindingofisaac.module.Model.Balle;
import com.projetpo.bindingofisaac.module.Model.Ennemi;
import com.projetpo.bindingofisaac.module.Model.ObjetsInventaire;
import com.projetpo.bindingofisaac.module.Model.Personnage;
import com.projetpo.bindingofisaac.module.Shaders.Vector2;

public class Gorb extends Ennemi{
	
	/*
	 * Cooldown entre chaque phase
	 */
	private int tickCoolDown;
	
	/*
	 * Liste de balle du Boss
	 */
	private ListeBalle munitions;
	
	/*
	 * Vitesse de tir
	 */
	private int acceleration;
	
	/*
	 * Vitesse des balles
	 */
	private double speedB;
	
	/*
	 * Constructeur
	 */
	public Gorb(int width, int heigth, Vector2 position, double speed, String url) {
		super(width, heigth, position, speed, url);
		this.tickCoolDown = 0;
		this.speedB = 1;
		this.munitions = new ListeBalle();
		this.munitions.setEnnemiBalle(true);
		this.munitions.setRange(5);
		this.munitions.setDegats(2);
		this.setLife(40);
		this.setAcceleration(1);
		for(int i=1; i<13; i++) {
			this.getLoot().add(new ObjetsInventaire(i, 10, 10, position, ""));
		}
	}	

	@Override
	public void drawEnnemi() {
		this.drawEntite();
	//	Render.getInstance().drawSquare((float)position.getX(),(float) position.getY(), width, heigth);
		munitions.drawBalle();
	}

	@Override
	public void IAEnnemi(Personnage p) {
		if(this.getLife() > 30) {
			if(tickCoolDown == 30/acceleration) {
				Attaque(1, 2, 3, 4);
			} if(tickCoolDown == 60/acceleration) {
				this.setSpeedB(10);
			}
		} else if(this.getLife() > 20) {
			if(tickCoolDown == 30) {
				Attaque(1, 2, 3, 4);
			} if(tickCoolDown == 60/acceleration) {
				this.setSpeedB(10);
			} if(tickCoolDown == 90/acceleration) {
				this.setSpeedB(1);
				Attaque(5, 6, 7, 8);
			} if(tickCoolDown == 120/acceleration) {
				this.setSpeedB(10);
			}
		} else {
			if(this.getLife() < 10) {
				this.setAcceleration(2);
			}
			if(tickCoolDown == 30) {
				Attaque(1, 2, 3, 4);
			} if(tickCoolDown == 60/acceleration) {
				this.setSpeedB(10);
				} if(tickCoolDown == 90/acceleration) {
					this.setSpeedB(1);
				Attaque(5, 6, 7, 8);
			} if(tickCoolDown == 120/acceleration) {
				this.setSpeedB(10);
			} if(tickCoolDown == 150/acceleration) {
				this.setSpeedB(1);
				Attaque(1, 2, 3, 4);
			} if(tickCoolDown == 180/acceleration) {
				this.setSpeedB(10);
			}
		}
		tickCoolDown ++;
		if(tickCoolDown == 210/acceleration) {
			tickCoolDown = 0;
			this.setSpeedB(1);
		}
		setDirection(new Vector2(p.getPosition().getX() - position.getX(), p.getPosition().getY() - position.getY()));
		this.move();
	}
	
	/**
	 * @Note Attaque  
	 */
	public void Attaque(int a, int b, int c, int d) {
		munitions.addBalle(new Balle(10, 10, position.getX(), position.getY(), new Vector2(0, 1), "", speedB));
		munitions.addBalle(new Balle(10, 10, position.getX(), position.getY(), new Vector2(0, -1), "", speedB));
		munitions.addBalle(new Balle(10, 10, position.getX(), position.getY(), new Vector2(1, 0), "", speedB));
		munitions.addBalle(new Balle(10, 10, position.getX(), position.getY(), new Vector2(-1, 0), "", speedB));
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

	public ListeBalle getMunitions() {
		return munitions;
	}

	public void setMunitions(ListeBalle munitions) {
		this.munitions = munitions;
	}

	public int getAcceleration() {
		return acceleration;
	}

	public void setAcceleration(int acceleration) {
		this.acceleration = acceleration;
	}

	public double getSpeedB() {
		return speedB;
	}

	public void setSpeedB(double speedB) {
		this.speedB = speedB;
	}

}
