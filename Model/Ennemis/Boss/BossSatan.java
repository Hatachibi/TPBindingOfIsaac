package com.projetpo.bindingofisaac.module.Model.Ennemis.Boss;

import java.io.IOException;
import java.util.Random;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.projetpo.bindingofisaac.module.Controler.ListeBalle;
import com.projetpo.bindingofisaac.module.Model.Balle;
import com.projetpo.bindingofisaac.module.Model.Ennemi;
import com.projetpo.bindingofisaac.module.Model.Personnage;
import com.projetpo.bindingofisaac.module.Shaders.Vector2;
import com.projetpo.bindingofisaac.module.Vue.Fenetre;
import com.projetpo.bindingofisaac.module.Vue.Jeu;
import com.projetpo.bindingofisaac.module.Vue.Render;
import com.projetpo.bindingofisaac.module.Vue.Texture;

public class BossSatan extends Ennemi{
	
	private ListeBalle munitions;
	
	private Vector2 ancienSpikes;
	
	private boolean firstTime;
	
	private int lignePhase2;

	public BossSatan(int width, int heigth, Vector2 position, double speed, String url) {
		super(width, heigth, position, speed, url);
		this.munitions = new ListeBalle();
		this.munitions.setEnnemiBalle(true);
		this.munitions.setRange(10);
		this.munitions.setDegats(1);
		this.setLife(75);
		this.ancienSpikes = new Vector2(1, 1);
		this.firstTime = true;
		this.lignePhase2 = 1;
	}

	@Override
	public void drawEnnemi() {
		this.munitions.drawBalle();
		Texture.megasatan.bind();
		Render.getInstance().drawPicture((float)position.getX() - Texture.megasatan.getWidth()/2 + 40, (float)position.getY() - 10, Texture.megasatan.getWidth(), Texture.megasatan.getHeight());
		Texture.megasatan.unbind();
		Texture.bdvBoss.bind();
		Render.getInstance().drawPicture(300, 100, Texture.bdvBoss.getWidth()*2, Texture.bdvBoss.getHeight()*2);
		Texture.bdvBoss.unbind();
		Render.getInstance().drawSquare(340, 105, (float) (this.getLife()*215/75), (float) ((float)Texture.bdvBoss.getHeight()), new float[] {1f, 0f, 0f, 1f});
	
	}

	@Override
	public void IAEnnemi(Personnage p) {
		this.munitions.update();
		this.goToRandom(Fenetre.getInstance().getFPS()/2, Fenetre.getInstance().getFPS()/2);
		if(this.getLife() < 50 && tick%Fenetre.getInstance().getFPS() == 0) {
			if(firstTime) {
				firstTime = false;
				try {
					Jeu.music("/com/projetpo/bindingofisaac/module/libMusic/megasatan.wav", false);
				} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
					e.printStackTrace();
				}
			}
			this.munitions.addBalle(new Balle(20, 20, getPosition().getX(), getPosition().getY(), new Vector2(1, 0), "src/main/resources/enemybullets.png", 10));
			this.munitions.addBalle(new Balle(20, 20, getPosition().getX(), getPosition().getY(), new Vector2(-1, 0), "src/main/resources/enemybullets.png",10));
			this.munitions.addBalle(new Balle(20, 20, getPosition().getX(), getPosition().getY(), new Vector2(0, 1), "src/main/resources/enemybullets.png", 10));
			this.munitions.addBalle(new Balle(20, 20, getPosition().getX(), getPosition().getY(), new Vector2(0, -1), "src/main/resources/enemybullets.png", 10));
			for(int i=1; i<12; i++) {
				if(lignePhase2 != 1) {
					Jeu.gameWorld.getMapEnCours().getcarte().setRenderMap(i, this.lignePhase2-1, 0);
				} else {
					Jeu.gameWorld.getMapEnCours().getcarte().setRenderMap(i, 7, 0);
				}
				Jeu.gameWorld.getMapEnCours().getcarte().setRenderMap(i, this.lignePhase2, 11);
			}
			this.lignePhase2 ++;
			if(this.lignePhase2 == 8) {
				this.lignePhase2 = 1;
			}
		} else {
			if(this.tick == 0) {
				int newX = new Random().nextInt(10)+1;
				int newY = new Random().nextInt(6)+1;
				Jeu.gameWorld.getMapEnCours().getcarte().setRenderMap(newX, newY, 11);
				Jeu.gameWorld.getMapEnCours().getcarte().setRenderMap((int)this.ancienSpikes.getX(),(int)this.ancienSpikes.getY(), 0);
				this.ancienSpikes = new Vector2(newX, newY);
			}
		}
		this.tick ++;
		if(this.tick == 180) {
			this.tick = 0;
		}
		this.move();
	}

}
