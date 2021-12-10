package Controler;

import java.util.LinkedList;

import Model.Balle;
import Vue.Fenetre;
import Vue.Render;

public class ListeBalle {
	
	
	private LinkedList<Balle> liste;
	
	public ListeBalle() {
		this.liste = new LinkedList<Balle>();
	}
	
	public void drawBalle() {
		LinkedList<Balle> copieListe = (LinkedList<Balle>) liste.clone();
		for(Balle b: liste) {
			double speed = 1;
			if(b.getDirection() == 1)
			{
				b.drawBalle();
				b.setX((float) (b.getX() - speed));
				if(b.getX() < 65)copieListe.remove(copieListe.indexOf(b));
			}
			if(b.getDirection() == 2)
			{
				b.drawBalle();
				b.setX((float) (b.getX() + speed));
				if(b.getX() > Fenetre.WidthFenetre - 65)copieListe.remove(copieListe.indexOf(b));
			}
			if(b.getDirection() == 3)
			{
				b.drawBalle();
				b.setY((float) (b.getY() + speed));
				if(b.getY() > Fenetre.HeigthFenetre - 65)copieListe.remove(copieListe.indexOf(b));
			}
			if(b.getDirection() == 4)
			{
				b.drawBalle();
				b.setY((float) (b.getY() - speed));
				if(b.getX() < 65)copieListe.remove(copieListe.indexOf(b));
			}
		}
		liste = copieListe;
	}
	
	public void addBalle(Balle b) {
		this.liste.add(b);
	}

	public LinkedList<Balle> getListe() {
		return liste;
	}
	
	
}
