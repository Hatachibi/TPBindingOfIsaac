package com.projetpo.bindingofisaac.module.Model.Ennemis.Boss;

import com.projetpo.bindingofisaac.module.Controler.ListeBalle;
import com.projetpo.bindingofisaac.module.Model.Balle;
import com.projetpo.bindingofisaac.module.Model.Ennemi;
import com.projetpo.bindingofisaac.module.Model.Personnage;
import com.projetpo.bindingofisaac.module.Shaders.Vector2;
import com.projetpo.bindingofisaac.module.Vue.Fenetre;
import com.projetpo.bindingofisaac.module.Vue.Render;
import com.projetpo.bindingofisaac.module.Vue.Texture;

public class BossWave extends Ennemi {
	
	private ListeBalle munitions;
	
	public BossWave(int width, int heigth, Vector2 position, double speed, String url) {
		super(width, heigth, position, speed, url);
		this.tick = 0;
		this.munitions = new ListeBalle();
		this.munitions.setEnnemiBalle(true);
		this.munitions.setDegats(1);
		this.munitions.setRange(20);
		this.setLife(50);
		this.setDegat(2);
	}

	@Override
	public void drawEnnemi() {
		this.munitions.drawBalle();
		this.drawEntite();
		Texture.bdvBoss.bind();
		Render.getInstance().drawPicture(300, 20, Texture.bdvBoss.getWidth()*2, Texture.bdvBoss.getHeight()*2);
		Texture.bdvBoss.unbind();
		Render.getInstance().drawSquare(340, 25, (float) (this.getLife()*215/50), (float) ((float)Texture.bdvBoss.getHeight()), new float[] {1f, 0f, 0f, 1f});
	
	}

	@Override
	public void IAEnnemi(Personnage p) {
		this.munitions.update();
		setDirection(new Vector2(p.getPosition().getX() - position.getX(), p.getPosition().getY() - position.getY()));
		move();
		tick ++;
		if(tick < 90) {
			this.setUrl("src/main/resources/bossWave.png");
		} else {
			this.setUrl("src/main/resources/bossWave2.png");
		}
		if(this.getLife() > 25) {
			if(tick == Fenetre.getInstance().getFPS()*2) {
				tick = 0;
			}
			if(tick == 0) {
				for(int i=0; i<20; i++) {
					this.munitions.addBalle(new Balle(20, 20, 80 + i*130,Fenetre.HeigthFenetre -  90, new Vector2(0, -1), "src/main/resources/enemybullets.png", 4));
				}
			}
			if(tick == Fenetre.getInstance().getFPS()*2 - 1) {
				for(int i=0; i<10; i++) {
					this.munitions.addBalle(new Balle(20, 20, 80, 80 + i*130, new Vector2(1, 0), "src/main/resources/enemybullets.png", 4));
				}
			}
		} else {
			if(tick == Fenetre.getInstance().getFPS()*3) {
				tick = 0;
			}
			if(tick%Fenetre.getInstance().getFPS() == 0) {
				int random = (int) (Math.random()*5);
				switch(random) {
				case 0:
				for(int i=0; i<20; i++) {
					this.munitions.addBalle(new Balle(20, 20, 80 + i*130,Fenetre.HeigthFenetre -  90, new Vector2(0, -1), "src/main/resources/enemybullets.png", 5));
				} 
				break;
				case 1:
				for(int i=0; i<10; i++) {
					this.munitions.addBalle(new Balle(20, 20, 80, 80 + i*130, new Vector2(1, 0), "src/main/resources/enemybullets.png", 5));
				}
				break;
				case 2:
				for(int i=0; i<20; i++) {
					this.munitions.addBalle(new Balle(20, 20, 80 + i*130,90, new Vector2(0, 1), "src/main/resources/enemybullets.png", 5));
				}
				case 3:
				for(int i=0; i<10; i++) {
					this.munitions.addBalle(new Balle(20, 20, Fenetre.WidthFenetre - 90, 80 + i*130, new Vector2(-1, 0), "src/main/resources/enemybullets.png", 5));
				}
				}
			}
		}
	}
	
}
