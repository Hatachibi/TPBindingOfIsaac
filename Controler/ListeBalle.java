package Controler;

import java.util.LinkedList;

import Model.Balle;
import Shaders.Raycasting;
import Vue.Fenetre;
import Vue.Render;

public class ListeBalle {
	
	
	private LinkedList<Balle> liste;
	private boolean isNotShot;
	private int coolDown;
	private double dernierAjout;
	
	public ListeBalle() {
		this.liste = new LinkedList<Balle>();
		this.isNotShot = true;
		this.dernierAjout = 0;
		this.setCoolDown(0);
	}
	
	/**
	 * 1 = gauche
	 * 2 = droite
	 * 3 = haut
	 * 4 = bas
	 */
	
	/**
	 * M�thode qui invoque la m�thode drawBalle() de la classe balle.
	 * Modifie la position de la balle en fonction de sa direction.
	 * V�rifie si elle n'attend pas un mur.
	 */
	public void drawBalle() {
		LinkedList<Balle> copieListe = (LinkedList<Balle>) liste.clone();
		for(Balle b: liste) {
			double speed = 10;
			if(b.getDirection() == Raycasting.PI)
			{
				b.drawBalle();
				b.setX((float) (b.getX() - speed));
				if(b.getX() < 65)copieListe.remove(copieListe.indexOf(b));
			}
			if(b.getDirection() == 0)
			{
				b.drawBalle();
				b.setX((float) (b.getX() + speed));
				if(b.getX() > Fenetre.WidthFenetre - 65)copieListe.remove(copieListe.indexOf(b));
			}
			if(b.getDirection() == Raycasting.P2)
			{
				b.drawBalle();
				b.setY((float) (b.getY() + speed));
				if(b.getY() > Fenetre.HeigthFenetre - 65)copieListe.remove(copieListe.indexOf(b));
			}
			if(b.getDirection() == Raycasting.P3)
			{
				b.drawBalle();
				b.setY((float) (b.getY() - speed));
				if(b.getX() < 65)copieListe.remove(copieListe.indexOf(b));
			}
		}
		liste = copieListe;
	}
	
	/**
	 * M�thode qui ajoute une balle � la liste des balles si le joueur est autoris� � tirer.
	 * @param b
	 * De classe Balle ou h�riti�re, correspond � la balle � ajouter � la liste.
	 */
	public void addBalle(Balle b) {
	/*	double time = (double) System.nanoTime()/10000000;
		if(time - dernierAjout >= 25) {
			System.out.println(time - dernierAjout); */
		if(this.isNotShot) {
			this.liste.add(b);
		}
		//	dernierAjout = time;
		//}
			this.isNotShot = false;
	}

	public LinkedList<Balle> getListe() {
		return liste;
	}

	public boolean isNotShot() {
		return isNotShot;
	}

	public void setShot(boolean isNotShot) {
		this.isNotShot = isNotShot;
	}

	public int getCoolDown() {
		return coolDown;
	}

	public void setCoolDown(int coolDown) {
		this.coolDown = coolDown;
	}

}
