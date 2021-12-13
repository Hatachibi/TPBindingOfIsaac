package Model;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;

import Controler.Input;
import Ressource.MapPath;
import Shaders.Vector2;
import Vue.Map;
import Vue.Render;
import Vue.Texture;

public class Room {

	private Personnage player;
	private Map mapEnCours;
	private Map[][] etage;
	private Vector2 etageCoos;
	
	public Room(Personnage player) {
		this.player = player;
		this.mapEnCours = MapPath.mapStart();
		this.setEtage(initEtage());
		this.setEtageCoos(new Vector2(4, 4));
	}
	
	public Map[][] initEtage(){
		Map[][] map = new Map[9][9];
		for(int i=0; i<map.length; i++) {
			for(int j=0; j<map[i].length; j++) {
				map[i][j] = new Map();
				map[i][j].generateMap(j != map.length - 1, j != 0, i != 0, i != map.length - 1);
				map[i][j].generateCollisionMap();
			}
		}
		map[4][4] = MapPath.mapStart();
	//	map[4][3] = MapPath.mapShop();
		return map;
	}
	
	public void updateRoom() {
		Input.getInstance().deplacement();
		Input.getInstance().tire();
		Jeu.room.getMapEnCours().changeMap();
		Jeu.room.getPlayer().boucleCooldownJoueur();
		Jeu.room.getPlayer().updateHitbox();
	}
	
	public void drawRoom() {	
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		if(this.getPlayer().isAlive()) {
			mapEnCours.drawMap();
			this.getPlayer().getLife().drawBarDeVie();
			Input.getInstance().getPlayerMove().drawPlayer();
			Input.getInstance().drawBalle();
		} else {
			Texture.gameOver.bind();
			Render.getInstance().drawPicture(0, 0, 585, 585, 1, 1, new float[]{});
			Texture.gameOver.unbind();
		}
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

	public Map[][] getEtage() {
		return etage;
	}

	public void setEtage(Map[][] etage) {
		this.etage = etage;
	}

	public Vector2 getEtageCoos() {
		return etageCoos;
	}

	public void setEtageCoos(Vector2 etageCoos) {
		this.etageCoos = etageCoos;
	}
	
}
