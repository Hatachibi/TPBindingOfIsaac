package Model;

import java.util.LinkedList;
import java.util.List;

public class listeEnnemi {

	private LinkedList<Ennemi> liste;
	
	public listeEnnemi() {
		this.liste = new LinkedList<Ennemi>();
	}
	
	public void updateEnnemis() {
		LinkedList<Ennemi> copieListe = (LinkedList<Ennemi>) liste.clone();
		for(Ennemi e: liste) {
			if(e.doRemove(e)) {
				copieListe.remove(e);
			} else {
				e.boucleCooldownEnnemi();
				e.IAEnnemi(Jeu.room.getPlayer());
			}
		}
		liste = copieListe;
	}

	public void drawEnnemis() {
		for(Ennemi e: liste) {
			if(!e.doRemove(e)) {
				e.drawEnnemi();
			}
		}
	}
	
	public void addEnnemi(Ennemi e) {
		this.liste.add(e);
	}

	public List<Ennemi> getListe() {
		return liste;
	}

	public void setListe(LinkedList<Ennemi> liste) {
		this.liste = liste;
	}
	
	public boolean isEmpty() {
		return this.liste.size() == 0;
	}
	
}
