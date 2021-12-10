package Model;

import Ressource.MapPath;
import Vue.Map;

public class Room {

	private Personnage player;
	private Map mapEnCours;
	
	public Room(Personnage player) {
		this.player = player;
		this.mapEnCours = MapPath.start;
	}

	public Personnage getPlayer() {
		return player;
	}

	public void setPlayer(Personnage player) {
		this.player = player;
	}

	public Map getMapEnCours() {
		return mapEnCours;
	}

	public void setMapEnCours(Map mapEnCours) {
		this.mapEnCours = mapEnCours;
	}
	
}
