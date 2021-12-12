package Model;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;

import Controler.Input;
import Ressource.MapPath;
import Vue.Map;
import Vue.Render;

public class Room {

	private Personnage player;
	private Map mapEnCours;
	
	public Room(Personnage player) {
		this.player = player;
		this.mapEnCours = MapPath.mapStart();
	}
	
	public void updateRoom() {
		Input.getInstance().deplacement();
		Input.getInstance().tire();
		Jeu.room.getMapEnCours().changeMap();
	}
	
	public void drawRoom() {	
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);	
		mapEnCours.drawMap();
		Input.getInstance().getPlayerMove().drawPlayer();
		Input.getInstance().drawBalle();
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
