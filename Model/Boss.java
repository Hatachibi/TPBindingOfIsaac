package Model;

import Controler.ListeBalle;
import Shaders.Vector2;
import Vue.Render;
import Vue.Texture;

public class Boss extends Ennemi{

		/*
		 * Liste de balle du Boss
		 */
		private ListeBalle munitions;
		
		/*
		 * Couple (x, y) de coordonn�es al�atoire pour le d�placement
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

		/*
		 * Constructeur
		 */
		public Boss(int width, int heigth, Vector2 position, String url, double speed, int life) {
			super(width, heigth, position, speed, url, life);
			this.munitions = new ListeBalle();
			this.munitions.setEnnemiBalle(true);
			this.tickCoolDown = 0;
			this.random = new Vector2(0, 0);
			this.setDegat(3);
			this.firstPhase = true;
		}
		
		/**
		 * @return Dessine l'ennemi
		 */
		@Override
		public void drawEnnemi() {
			if(firstPhase) {
				Texture.boss1.bind();
				Render.getInstance().drawPicture((float)position.getX(), (float)position.getY(), 75, 75, 1, 1, new float[] {});
				Texture.boss1.unbind();
			} else {
				Texture.boss2.bind();
				Render.getInstance().drawPicture((float)position.getX(), (float)position.getY(), 75, 75, 1, 1, new float[] {});
				Texture.boss2.unbind();
			}
			munitions.drawBalle();
		}

		/**
		 * @return IA de l'ennemi avec des phases random
		 */
		@Override
		public void IAEnnemi(Personnage p) {
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
	
}
