package com.projetpo.bindingofisaac.module.Model.Ennemis.Boss;

import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.projetpo.bindingofisaac.module.Controler.ListeBalle;
import com.projetpo.bindingofisaac.module.Model.Balle;
import com.projetpo.bindingofisaac.module.Model.Ennemi;
import com.projetpo.bindingofisaac.module.Model.Jeu;
import com.projetpo.bindingofisaac.module.Model.ObjetsInventaire;
import com.projetpo.bindingofisaac.module.Model.Personnage;
import com.projetpo.bindingofisaac.module.Model.Ennemis.Fly;
import com.projetpo.bindingofisaac.module.Model.Ennemis.Spider;
import com.projetpo.bindingofisaac.module.Ressource.RoomInfos;
import com.projetpo.bindingofisaac.module.Shaders.Vector2;
import com.projetpo.bindingofisaac.module.Vue.Fenetre;
import com.projetpo.bindingofisaac.module.Vue.Render;
import com.projetpo.bindingofisaac.module.Vue.Texture;

public class Boss extends Ennemi{

		/*
		 * Liste de balle du Boss
		 */
		private ListeBalle munitions;
				
		/*
		 * Boolean permettant de savoir la phase du boss 
		 */
		private boolean firstPhase;
		
		boolean playOnce = true;

		/*
		 * Constructeur
		 */
		public Boss(int width, int heigth, Vector2 position, String url, double speed) {
			super(width, heigth, position, speed, url);
			this.munitions = new ListeBalle();
			this.munitions.setEnnemiBalle(true);
			this.tick = 0;
			this.setDegat(3);
			this.setLife(50);
			this.firstPhase = true;
			this.munitions.setRange(4);
			this.munitions.setDegats(1); // Degat des projectiles
		}
		
		/**
		 * @return Dessine l'ennemi
		 */
		@Override
		public void drawEnnemi() {
			if(firstPhase) {
				playOnce = true;
				Texture.boss1.bind();
				Render.getInstance().drawPicture((float)position.getX(), (float)position.getY(), 75, 75);
				Texture.boss1.unbind();
			} else {
				if(playOnce){
					try {
						Jeu.music("/com/projetpo/bindingofisaac/module/libMusic/boss_phase.wav", false);
					} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
						e.printStackTrace();
					}
					playOnce = false;
				}
					
				Texture.boss2.bind();
				Render.getInstance().drawPicture((float)position.getX(), (float)position.getY(), 75, 75);
				Texture.boss2.unbind();
			}
	//		Render.getInstance().drawSquare((float)position.getX(),(float) position.getY(), width, heigth);
			munitions.drawBalle();
			Texture.bdvBoss.bind();
			Render.getInstance().drawPicture(300, 100, Texture.bdvBoss.getWidth()*2, Texture.bdvBoss.getHeight()*2);
			Texture.bdvBoss.unbind();
			Render.getInstance().drawSquare(340, 105, (float) (this.getLife()*215/50), (float) ((float)Texture.bdvBoss.getHeight()), new float[] {1f, 0f, 0f, 1f});
		}

		/**
		 * @return IA de l'ennemi avec des phases random
		 */
		@Override
		public void IAEnnemi(Personnage p) {
			munitions.update();
			if(firstPhase) { //Phase 1
				this.tick ++;
				this.goToRandom(Fenetre.getInstance().getFPS()/2, Fenetre.getInstance().getFPS()/2);
			} else { //Phase 2
				this.goToPlayer(p);
				if(tick%60 == 0) {
					Vector2 v = new Vector2(p.getPosition().getX() - getPosition().getX(), p.getPosition().getY() - getPosition().getY());
					Vector2 v2 = new Vector2(v.getX()/v.euclidianNorm(), v.getY()/v.euclidianNorm());
					munitions.addBalle(new Balle(35, 35, getPosition().getX(), getPosition().getY(), v2, "src/main/resources/enemybullets.png", 10));
				}
				firstPhase = false;
				this.tick ++;
			}
			if(tick > 120) { //Reset de Phase
				tick = 0;
				if(Math.random() > 0.5) {
					firstPhase = true;
				} else {
					firstPhase = false;
				}
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

		public boolean isFirstPhase() {
			return firstPhase;
		}

		public void setFirstPhase(boolean firstPhase) {
			this.firstPhase = firstPhase;
		}

		public boolean isPlayOnce() {
			return playOnce;
		}

		public void setPlayOnce(boolean playOnce) {
			this.playOnce = playOnce;
		}
	
}
