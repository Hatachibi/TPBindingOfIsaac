package com.projetpo.bindingofisaac.module.Model.Ennemis;

import com.projetpo.bindingofisaac.module.Controler.ListeBalle;
import com.projetpo.bindingofisaac.module.Model.Balle;
import com.projetpo.bindingofisaac.module.Model.Ennemi;
import com.projetpo.bindingofisaac.module.Model.Personnage;
import com.projetpo.bindingofisaac.module.Shaders.Vector2;

public class ParabiteBalle extends Ennemi {
	
	/*
	 * Cooldown entre chaque phase
	 */
	private int tickCoolDown;
	
	/*
	 * Liste de balle du Boss
	 */
	private ListeBalle munitions;
	
	/*
	 * Random au niveau du CoolDown
	 */
	private int random;
	
	/*
	 * 4 munitions 
	 */
	private Balle[] four = new Balle[4];
	
	/*
	 * Constructeur
	 */
	public ParabiteBalle(int width, int heigth, Vector2 position, double speed, String url) {
		super(width, heigth, position, speed, url);
		this.setLife(25);
		this.munitions = new ListeBalle();
		this.munitions.setEnnemiBalle(true);
		this.munitions.setRange(3);
		this.random = (int) (Math.random()*60);
	}
	
	@Override
	public void drawEnnemi() {
		if(tickCoolDown > random + 20) {
			this.drawEntite();
		}
		this.munitions.drawBalle();
	}

	@Override
	public void IAEnnemi(Personnage p) {
		if(tickCoolDown == random) {
			setDirection(new Vector2(p.getPosition().getX(), p.getPosition().getY()));
		} else if(tickCoolDown == random + 20) {
			System.out.println("check");
			setPosition(this.getDirection());
		} else if (tickCoolDown == random + 30) {
			this.four[0] = new Balle(this.width, this.heigth, this.position.getX(), this.position.getY(), new Vector2(1, 0), "src/main/resources/enemybullets.png", 1);
			this.four[1] = new Balle(this.width, this.heigth, this.position.getX(), this.position.getY(), new Vector2(0, 1), "src/main/resources/enemybullets.png", 1);
			this.four[2] = new Balle(this.width, this.heigth, this.position.getX(), this.position.getY(), new Vector2(-1, 0), "src/main/resources/enemybullets.png", 1);
			this.four[3] = new Balle(this.width, this.heigth, this.position.getX(), this.position.getY(), new Vector2(0, -1), "src/main/resources/enemybullets.png", 1);
			munitions.addBalle(four[0]);
			munitions.addBalle(four[1]);
			munitions.addBalle(four[2]);
			munitions.addBalle(four[3]);
		} else if(tickCoolDown == 180) {
			tickCoolDown = 0;
		} if(tickCoolDown < random + 19) {
			setPosition(new Vector2(0, 0));
		}
		if(!munitions.getListe().isEmpty()) {
			this.four[0].setDirection(new Vector2(1, (double)(tickCoolDown%60)/60));
			this.four[1].setDirection(new Vector2(-(double)(tickCoolDown%60)/60, 1));
			this.four[2].setDirection(new Vector2(-1, -(double)(tickCoolDown%60)/60));
			this.four[3].setDirection(new Vector2((double)(tickCoolDown%60)/60, -1));
		}
		tickCoolDown ++;
	}

}
