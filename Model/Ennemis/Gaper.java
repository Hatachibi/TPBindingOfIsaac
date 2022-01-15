package com.projetpo.bindingofisaac.module.Model.Ennemis;

import com.projetpo.bindingofisaac.module.Controler.ListeBalle;
import com.projetpo.bindingofisaac.module.Model.Ennemi;
import com.projetpo.bindingofisaac.module.Model.ObjetsInventaire;
import com.projetpo.bindingofisaac.module.Model.Personnage;
import com.projetpo.bindingofisaac.module.Shaders.Vector2;

public class Gaper extends Ennemi{
	
	/*
	 * Liste de balle du Boss
	 */
	private ListeBalle munitions;
		
	/*
	 * Vitesse des balles
	 */
	private double speedB;
	
	/*
	 * Constructeur
	 */
	public Gaper(int width, int heigth, Vector2 position, double speed, String url) {
		super(width, heigth, position, speed, url);
		this.tick = 0;
		this.speedB = 1;
		this.munitions = new ListeBalle();
		this.munitions.setEnnemiBalle(true);
		this.munitions.setRange(5);
		this.munitions.setDegats(2);
		this.setLife(20);
		this.setDegat(2);
	}	

	@Override
	public void drawEnnemi() {
		this.drawEntite();
		munitions.drawBalle();
	}

	@Override
	public void IAEnnemi(Personnage p) {
		munitions.update();
		this.goToPlayer(p);
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

	public double getSpeedB() {
		return speedB;
	}

	public void setSpeedB(double speedB) {
		this.speedB = speedB;
	}

}
