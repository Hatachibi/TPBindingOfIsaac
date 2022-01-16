package com.projetpo.bindingofisaac.module.Model.Ennemis.Boss;

import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.projetpo.bindingofisaac.module.Model.Ennemi;
import com.projetpo.bindingofisaac.module.Model.Personnage;
import com.projetpo.bindingofisaac.module.Shaders.Vector2;
import com.projetpo.bindingofisaac.module.Vue.Fenetre;
import com.projetpo.bindingofisaac.module.Vue.Jeu;
import com.projetpo.bindingofisaac.module.Vue.Render;
import com.projetpo.bindingofisaac.module.Vue.Texture;

public class BossCollectionneur extends Ennemi {
	
	private int launchEnnemi;
	
	private Vector2 random;
	
	public BossCollectionneur(int width, int heigth, Vector2 position, double speed, String url) {
		super(width, heigth, position, speed, url);
		this.tick = 0;
		this.launchEnnemi = 0;
		this.setLife(75);
		this.setDegat(1);
		this.random = new Vector2(1, 1);
	}

	@Override
	public void drawEnnemi() {
		this.drawEntite();
		Texture.bdvBoss.bind();
		Render.getInstance().drawPicture(300, 20, Texture.bdvBoss.getWidth()*2, Texture.bdvBoss.getHeight()*2);
		Texture.bdvBoss.unbind();
		Render.getInstance().drawSquare(340, 25, (float) (this.getLife()*215/75), (float) ((float)Texture.bdvBoss.getHeight()), new float[] {1f, 0f, 0f, 1f});
	}
	
	public boolean isMur() {
		return (Jeu.gameWorld.getMapEnCours().getcarte().getCollisionMap()[(int)(position.getX()/65)][(int)(position.getY()/65)]);
	}

	@Override
	public void IAEnnemi(Personnage p) {
		if(tick == 1) {
			this.launchEnnemi = 0;
		}
		if(tick == 180) {
			tick = 0;
			if(Math.random() > 0.5) {
				try {
					Jeu.music("/com/projetpo/bindingofisaac/module/libMusic/boss_scream.wav", false);
				} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
					e.printStackTrace();
				} 
				this.launchEnnemi = (int) (1+Math.random()*4);
			} 
		} 
		if(tick < 120) {
			if(position.getX() < 65) {
				setDirection(new Vector2(1, this.random.getY()));
			} 
			else if(position.getX() > Fenetre.WidthFenetre - 65-width) {
				setDirection(new Vector2(-1, this.random.getY()));
			}
			else if(position.getY() < 65) {
				setDirection(new Vector2(this.random.getX(), 1));
			}
			else if(position.getY() > Fenetre.HeigthFenetre - 65-heigth) {
				setDirection(new Vector2(this.random.getX(), -1));
			}
			else {
				setDirection(random);
			}
			random = getDirection();
			this.move();
		}
		tick ++;
	}

	public int getlaunchEnnemi() {
		return launchEnnemi;
	}

	public void setlaunchEnnemi(int launchEnnemi) {
		this.launchEnnemi = launchEnnemi;
	}

	
	
}
