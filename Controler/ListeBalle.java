package Controler;

import java.util.LinkedList;

import Model.Balle;
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
	
	public void drawBalle() {
		LinkedList<Balle> copieListe = (LinkedList<Balle>) liste.clone();
		for(Balle b: liste) {
			if(b.getDirection() == 1)
			{
				b.drawBalle();
				b.getPosition().setX((float) (b.getPosition().getX() - b.getSpeed()));
		//		if(b.getX() < 65)copieListe.remove(copieListe.indexOf(b));
			}
			if(b.getDirection() == 2)
			{
				b.drawBalle();
				b.getPosition().setX((float) (b.getPosition().getX() + b.getSpeed()));
	//			if(b.getX() > Fenetre.WidthFenetre - 65)copieListe.remove(copieListe.indexOf(b));
			}
			if(b.getDirection() == 3)
			{
				b.drawBalle();
				b.getPosition().setY((float) (b.getPosition().getY() + b.getSpeed()));
	//			if(b.getY() > Fenetre.HeigthFenetre - 65)copieListe.remove(copieListe.indexOf(b));
			}
			if(b.getDirection() == 4)
			{
				b.drawBalle();
				b.getPosition().setY((float) (b.getPosition().getY() - b.getSpeed()));
		//		if(b.getX() < 65)copieListe.remove(copieListe.indexOf(b));
			}
			b.updateHitbox();
			if(doRemove(b)) {
				copieListe.remove(b);
			}
		}
		liste = copieListe;
	}
	
	public void addBalle(Balle b) {
		if(this.isNotShot) {
			this.liste.add(b);
		}
			this.isNotShot = false;
	}
	
	public boolean doRemove(Balle b) {
		return b.getHitbox().collisionMurEntite(b);
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