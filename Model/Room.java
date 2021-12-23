package Model;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;

import java.util.LinkedList;

import Controler.Input;
import Ressource.MapPath;
import Shaders.Vector2;
import Vue.Fenetre;
import Vue.Map;
import Vue.Render;
import Vue.Texture;

public class Room {

	private Personnage player;
	private listeEnnemi listeEnnemi;
	private Map mapEnCours;
	private Map[][] etage;
	private Vector2 etageCoos;
	
	public Room(Personnage player) {
		this.player = player;
		this.mapEnCours = MapPath.mapStart();
		this.listeEnnemi = new listeEnnemi();
		this.setEtage(initEtage());
		this.setEtageCoos(new Vector2(4, 4));
		this.addEnnemis();
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
	
	public void addEnnemis() {
		for(int i=0; i<mapEnCours.getMapobject().length; i++) {
			for(int j=0; j<mapEnCours.getMapobject()[i].length; j++) {
				switch(mapEnCours.getMapobject()[i][j].getEnnemiMap()){
					case 1: getListeEnnemi().addEnnemi(new Fly(25, 25, new Vector2(i*65, j*65),"", 2, 3));break;
				}
			}
		}
	}
	
	public void unlockedDoors() {
		if(mapEnCours.getRenderMap()[4][8] == 12) {
			mapEnCours.setRenderMap(4, 8, -1);
			mapEnCours.generateCollisionMap();
		}
		if(mapEnCours.getRenderMap()[4][0] == 13) {
			mapEnCours.setRenderMap(4, 0, -2);
			mapEnCours.generateCollisionMap();
		}
		if(mapEnCours.getRenderMap()[8][4] == 14) {
			mapEnCours.setRenderMap(8, 4, -3);
			mapEnCours.generateCollisionMap();
		}
		if(mapEnCours.getRenderMap()[0][4] == 15) {
			mapEnCours.setRenderMap(0, 4, -4);
			mapEnCours.generateCollisionMap();
		}
	}
	
	public void updateRoom() {
		Input.getInstance().deplacement();
		Input.getInstance().tire();
		getMapEnCours().changeMap();
		getPlayer().boucleCooldownJoueur();
		getPlayer().updateHitbox();
		listeEnnemi.updateEnnemis();
		if(listeEnnemi.isEmpty()) {
			unlockedDoors();
		}
	}
	
	public void drawRoom() {	
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		if(this.getPlayer().isAlive()) {
			mapEnCours.drawMap();
			this.drawMiniMap();
			this.getPlayer().getLife().drawBarDeVie();
			Input.getInstance().getPlayerMove().drawPlayer();
			Input.getInstance().drawBalle();
			listeEnnemi.drawEnnemis();
		} else {
			Texture.gameOver.bind();
			Render.getInstance().drawPicture(0, 0, 585, 585, 1, 1, new float[]{});
			Texture.gameOver.unbind();
		}
	}
	
	public void drawMiniMap() {
		int coef = 2;
		Render.getInstance().drawSquare((float)(Fenetre.WidthFenetre + 5*coef - 58.5*coef), 5, (float)58.5*coef,(float) 58.5*coef, new float[]{255f, 255f, 255f, 255f});
		Render.getInstance().drawSquare((float)(Fenetre.WidthFenetre - 5.85*coef - 5.85*coef*etageCoos.getX()), (float)(5 + 5.85*coef*etageCoos.getY()), (float)5.85*coef, (float)5.85*coef, new float[]{1f, 0f, 0f, 0f});
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
	
	public listeEnnemi getListeEnnemi() {
		return listeEnnemi;
	}

	public void setListeEnnemi(listeEnnemi listeEnnemi) {
		this.listeEnnemi = listeEnnemi;
	}
	
}
