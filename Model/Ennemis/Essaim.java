package com.projetpo.bindingofisaac.module.Model.Ennemis;

import com.projetpo.bindingofisaac.module.Model.Ennemi;
import com.projetpo.bindingofisaac.module.Model.Laser;
import com.projetpo.bindingofisaac.module.Model.Personnage;
import com.projetpo.bindingofisaac.module.Shaders.Vector2;

public class Essaim extends Ennemi {
	
	/*
	 * Laser de l'ennemi
	 */
	private Laser laser;
	
	/*
	 * Tick
	 */
	private int tickCoolDown;
	
	/*
	 * Constructeur
	 */
	public Essaim(int width, int heigth, Vector2 position, double speed, String url) {
		super(width, heigth, position, speed, url);
		this.tickCoolDown = 0;
		this.laser = new Laser(100, 30, new Vector2(position.getX() + width/2, position.getY() + heigth), "src/main/resources/enemybullets.png");
		this.setLife(20);
		this.laser.setDegats(1);
		this.laser.setEnnemiBalle(true);
	}

	@Override
	public void drawEnnemi() {
		this.drawEntite();
		this.laser.draw();
	}

	@Override
	public void IAEnnemi(Personnage p) {
		this.laser.updateHitbox();
		this.laser.update();
	//	if(tickCoolDown%240 > 120) {
		/*	if(p.getPosition().getX() < position.getX()) {
					if(laser.getHeigth() > laser.getWidth()) {
						int tmp = laser.getHeigth();
						laser.setHeigth(laser.getWidth());
						laser.setWidth(tmp);
						laser.getHitbox().setWidth(laser.getWidth());
						laser.getHitbox().setHeigth(laser.getHeigth());
					}
					this.laser.setUrl("src/main/resources/laserVertical.png");
					laser.setPosition(new Vector2(laser.getPosOrigin().getX() - laser.getWidth(), laser.getPosOrigin().getY()));
			}
			if(p.getPosition().getX() > position.getX()) {
					if(laser.getHeigth() > laser.getWidth()) {
						int tmp = laser.getHeigth();
						laser.setHeigth(laser.getWidth());
						laser.setWidth(tmp);
						laser.getHitbox().setWidth(laser.getWidth());
						laser.getHitbox().setHeigth(laser.getHeigth());
					}
					this.laser.setUrl("src/main/resources/laserVertical.png");
					laser.setPosition(new Vector2(laser.getPosOrigin().getX(), laser.getPosOrigin().getY()));
			} 
		} else { */
			if(p.getPosition().getY() < position.getY()) {
					if(laser.getWidth() > laser.getHeigth()) {
						int tmp = laser.getHeigth();
						laser.setHeigth(laser.getWidth());
						laser.setWidth(tmp);
						laser.getHitbox().setWidth(laser.getWidth());
						laser.getHitbox().setHeigth(laser.getHeigth());
					}
					this.laser.setUrl("src/main/resources/laserVertical.png");
					laser.setPosition(new Vector2(laser.getPosOrigin().getX(), laser.getPosOrigin().getY() -  laser.getHeigth()));
			}
			if(p.getPosition().getY() > position.getY()) {
					if(laser.getWidth() > laser.getHeigth()) {
						int tmp = laser.getHeigth();
						laser.setHeigth(laser.getWidth());
						laser.setWidth(tmp);
						laser.getHitbox().setWidth(laser.getWidth());
						laser.getHitbox().setHeigth(laser.getHeigth());
					}
					this.laser.setUrl("src/main/resources/laserVertical.png");
					laser.setPosition(new Vector2(laser.getPosOrigin().getX(), laser.getPosOrigin().getY()));
			}
	/*	}
		if(tickCoolDown == 240) {
			tickCoolDown = 0;
		}
		tickCoolDown ++; */
	} 

}
