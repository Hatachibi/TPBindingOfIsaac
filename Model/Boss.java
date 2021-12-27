package Model;

import Controler.ListeBalle;
import Shaders.Vector2;
import Vue.Fenetre;
import Vue.Render;
import Vue.Texture;

public class Boss extends Ennemi{

		private ListeBalle munitions;
		private int tickCoolDown;

		public Boss(int width, int heigth, Vector2 position, String url, double speed, int life) {
			super(width, heigth, position, speed, url, life);
			this.munitions = new ListeBalle();
			this.munitions.setEnnemiBalle(true);
			this.tickCoolDown = 0;
		}
		
		public void drawEnnemi() {
			Texture.fly.bind();
			Render.getInstance().drawPicture((float)position.getX(), (float)position.getY(), 25, 25, 1, 1, new float[] {});
			Texture.fly.unbind();
			munitions.drawBalle();
		}

		public void IAEnnemi(Personnage p) {
			if(Math.random() > 0.5) {
				this.tickCoolDown ++;
				Fly.IAFly(p, this);
			} if(tickCoolDown > 120) {
				tickCoolDown = 0;
			}
		}
	
}
