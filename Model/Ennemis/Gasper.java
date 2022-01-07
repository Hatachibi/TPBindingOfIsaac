package com.projetpo.bindingofisaac.module.Model.Ennemis;

import com.projetpo.bindingofisaac.module.Controler.ListeBalle;
import com.projetpo.bindingofisaac.module.Model.Balle;
import com.projetpo.bindingofisaac.module.Model.Ennemi;
import com.projetpo.bindingofisaac.module.Model.ObjetsInventaire;
import com.projetpo.bindingofisaac.module.Model.Personnage;
import com.projetpo.bindingofisaac.module.Shaders.Vector2;

public class Gasper extends Ennemi{
	
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
	public Gasper(int width, int heigth, Vector2 position, double speed, String url) {
		super(width, heigth, position, speed, url);
		this.tickCoolDown = 0;
		this.speedB = 1;
		this.munitions = new ListeBalle();
		this.munitions.setEnnemiBalle(true);
		this.munitions.setRange(5);
		this.munitions.setDegats(2);
		this.setLife(40);
		this.setDegat(2);
		this.setAcceleration(1);
		for(int i=1; i<13; i++) {
			this.getLoot().add(new ObjetsInventaire(i, 30, 30, position, ""));
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
		munitions.update();
		setDirection(new Vector2(p.getPosition().getX() - position.getX(), p.getPosition().getY() - position.getY()));
		this.move();
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
