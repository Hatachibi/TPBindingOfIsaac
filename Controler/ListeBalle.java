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
		for(Balle b: liste) {
			b.drawBalle();
			b.setX((float) (b.getX() + 1));
			if(b.getX() > Fenetre.WidthFenetre - 65)liste.remove(liste.indexOf(b));
		}
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
