package Controler;

import java.util.LinkedList;

import Model.Balle;
import Vue.Fenetre;
import Vue.Render;

public class ListeBalle {
	
	
	private final static ListeBalle INSTANCE = new ListeBalle();
	private LinkedList<Balle> liste;
	
	public ListeBalle() {
		this.liste = new LinkedList<Balle>();
	}
	
	public void drawBalle() {
		LinkedList<Balle> copieListe = (LinkedList<Balle>) liste.clone();
		for(Balle b: liste) {
		
			if(b.getDirection() == 1)
			{
				b.drawBalle();
				b.setX((float) (b.getX() - 1));
				if(b.getX() < 65)copieListe.remove(copieListe.indexOf(b));
			}
			if(b.getDirection() == 2)
			{
				b.drawBalle();
				b.setX((float) (b.getX() + 1));
				if(b.getX() > Fenetre.WidthFenetre - 65)copieListe.remove(copieListe.indexOf(b));
			}
			if(b.getDirection() == 3)
			{
				b.drawBalle();
				b.setY((float) (b.getY() + 1));
				if(b.getY() > Fenetre.HeigthFenetre - 65)copieListe.remove(copieListe.indexOf(b));
			}
			if(b.getDirection() == 4)
			{
				b.drawBalle();
				b.setY((float) (b.getY() - 1));
				if(b.getX() < 65)copieListe.remove(copieListe.indexOf(b));
			}
		}
		liste = copieListe;
	}
	
	public void addBalle(Balle b) {
		this.liste.add(b);
	}

	public static ListeBalle getInstance() {
		return INSTANCE;
	}

	public LinkedList<Balle> getListe() {
		return liste;
	}
	
	
}
