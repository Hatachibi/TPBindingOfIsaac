package Model;

import java.util.LinkedList;
import java.util.List;

public class listeEnnemi {

	private List<Ennemi> liste;
	
	public listeEnnemi() {
		this.liste = new LinkedList<Ennemi>();
	}
	
	public void updateEnnemis() {
		for(Ennemi e: liste) {
			if(e.doRemove(e)) {
				liste.remove(e);
			} else {
				e.boucleCooldownEnnemi();
				e.IAEnnemi(Jeu.room.getPlayer());
			}
		}
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

	public void setListe(List<Ennemi> liste) {
		this.liste = liste;
	}
	
	public boolean isEmpty() {
		return this.liste.size() == 0;
	}
	
}
