package com.projetpo.bindingofisaac.module.Model.Ennemis;

import com.projetpo.bindingofisaac.module.Model.Ennemi;
import com.projetpo.bindingofisaac.module.Model.Jeu;
import com.projetpo.bindingofisaac.module.Model.Personnage;
import com.projetpo.bindingofisaac.module.Shaders.Vector2;
import com.projetpo.bindingofisaac.module.Vue.Fenetre;
import com.projetpo.bindingofisaac.module.Vue.Render;
import com.projetpo.bindingofisaac.module.Vue.Texture;

public class BossCollectionneur extends Ennemi {

	private int tickCoolDown;
	
	private int launchEnnemi;
	
	private Vector2 random;
	
	private Vector2 ancienDir;
	
	private boolean reDo;
	
	private int coolDown;
	
	public BossCollectionneur(int width, int heigth, Vector2 position, double speed, String url) {
		super(width, heigth, position, speed, url);
		this.tickCoolDown = 0;
		this.launchEnnemi = 0;
		this.setLife(40);
		this.setDegat(1);
		this.ancienDir = new Vector2();
		this.random = new Vector2();
		this.reDo = false;
	}

	@Override
	public void drawEnnemi() {
		this.drawEntite();
		Texture.bdvBoss.bind();
		Render.getInstance().drawPicture(300, 100, Texture.bdvBoss.getWidth()*2, Texture.bdvBoss.getHeight()*2);
		Texture.bdvBoss.unbind();
		Render.getInstance().drawSquare(340, 105, (float) (this.getLife()*215/40), (float) ((float)Texture.bdvBoss.getHeight()), new float[] {1f, 0f, 0f, 1f});
	}
	
	public boolean isMur() {
		return (Jeu.gameWorld.getMapEnCours().getcarte().getCollisionMap()[(int)(position.getX()/65)][(int)(position.getY()/65)]);
	}

	@Override
	public void IAEnnemi(Personnage p) {
		if(tickCoolDown == 60 && Math.random() > 0.5) {
			this.launchEnnemi = (int) (1+Math.random()*4);
		}
		if(tickCoolDown == 61) {
			this.launchEnnemi = 0;
		}
		if(tickCoolDown == 180) {
			tickCoolDown = 0;
		}
		if(Fenetre.tick == 30) {
			random.setX(Math.random() - 0.5);
			random.setY(Math.random() - 0.5);
		}
		if(position.getX() > 65 && position.getX() < Fenetre.WidthFenetre - 65-width && position.getY() > 65 && position.getY() < Fenetre.HeigthFenetre - 65-heigth && !isMur()) {
			setDirection(new Vector2(position.getX()*random.getX(), position.getY()*random.getY()));
		} else {
			setDirection(new Vector2(p.getPosition().getX() - position.getX(), p.getPosition().getY() - position.getY()));
		}
		this.move();
		tickCoolDown ++;
	}

	public int getlaunchEnnemi() {
		return launchEnnemi;
	}

	public void setlaunchEnnemi(int launchEnnemi) {
		this.launchEnnemi = launchEnnemi;
	}

	
	
}
