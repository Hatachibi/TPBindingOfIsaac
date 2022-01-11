package com.projetpo.bindingofisaac.module.Model.Ennemis;

import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.projetpo.bindingofisaac.module.Controler.ListeBalle;
import com.projetpo.bindingofisaac.module.Model.Ennemi;
import com.projetpo.bindingofisaac.module.Model.Jeu;
import com.projetpo.bindingofisaac.module.Model.ObjetsInventaire;
import com.projetpo.bindingofisaac.module.Model.Personnage;
import com.projetpo.bindingofisaac.module.Ressource.RoomInfos;
import com.projetpo.bindingofisaac.module.Shaders.Vector2;
import com.projetpo.bindingofisaac.module.Vue.Render;
import com.projetpo.bindingofisaac.module.Vue.Texture;

public class Boss extends Ennemi{

		/*
		 * Liste de balle du Boss
		 */
		private ListeBalle munitions;
		
		/*
		 * Couple (x, y) de coordonnées aléatoire pour le déplacement
		 */
		private Vector2 random;
		
		/*
		 * Boolean permettant de savoir la phase du boss 
		 */
		private boolean firstPhase;
		
		/*
		 * Cooldown entre chaque phase
		 */
		private int tickCoolDown;
		
		boolean playOnce = true;
		
		private boolean addFly;

		/*
		 * Constructeur
		 */
		public Boss(int width, int heigth, Vector2 position, String url, double speed) {
			super(width, heigth, position, speed, url);
			this.munitions = new ListeBalle();
			this.munitions.setEnnemiBalle(true);
			this.tickCoolDown = 0;
			this.random = new Vector2(0, 0);
			this.setDegat(3);
			this.addFly = false;
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
				Render.getInstance().drawPicture((float)position.getX(), (float)position.getY(), 75, 75, 1, 1, new float[] {});
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
				Render.getInstance().drawPicture((float)position.getX(), (float)position.getY(), 75, 75, 1, 1, new float[] {});
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
			if(tickCoolDown == 0) {
				this.addFly = true;
			}
			if(tickCoolDown == 1) {
				this.addFly = false;
			}
			if((Math.random() > 0.5 || tickCoolDown > 0) && firstPhase) { //Phase 1
				this.tickCoolDown ++;
				Spider.IASpider(p, this);
			} else if(Math.random() > 0.5 || !firstPhase) { //Phase 2
				Fly.IAFly(p, this);
				firstPhase = false;
				this.tickCoolDown ++;
			}
			if(tickCoolDown > 120) { //Reset de Phase
				tickCoolDown = 0;
				firstPhase = true;
			}
		}

		/*
		 * Getters & Setters
		 */
		public Vector2 getRandom() {
			return random;
		}

		public void setRandom(Vector2 random) {
			this.random = random;
		}

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

		public int getTickCoolDown() {
			return tickCoolDown;
		}

		public void setTickCoolDown(int tickCoolDown) {
			this.tickCoolDown = tickCoolDown;
		}

		public boolean isPlayOnce() {
			return playOnce;
		}

		public void setPlayOnce(boolean playOnce) {
			this.playOnce = playOnce;
		}

		public boolean isAddFly() {
			return addFly;
		}

		public void setAddFly(boolean addFly) {
			this.addFly = addFly;
		}
		
		
	
}
