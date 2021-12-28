package Model;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import Controler.Input;
import Controler.listeEnnemi;
import Ressource.MapPath;
import Shaders.Vector2;
import Vue.Fenetre;
import Vue.Map;
import Vue.Render;
import Vue.Texture;

public class Room {
	
	/*
	 * Joueur dans la pi�ce
	 */
	private Personnage player;
	
	/*
	 * Liste ennemie dans la pi�ce
	 */
	private listeEnnemi listeEnnemi;
	
	/*
	 * Pi�ce en cours
	 */
	private Map mapEnCours;
	
	/*
	 * Carte d'un �tage
	 */
	private Map[][] etage;
	
	/*
	 * Coordon�es (x, y) de la pi�ce dans l'�tage
	 */
	private Vector2 etageCoos;
	
	/*
	 * Constructeur
	 */
	public Room(Personnage player) {
		this.player = player;
		this.mapEnCours = MapPath.mapStart();
		this.listeEnnemi = new listeEnnemi();
		this.setEtage(initEtage());
		this.setEtageCoos(new Vector2(4, 4));
		this.addEnnemis();
	}
	
	/**
	 * @return L'�tage initialis� avec les pi�ces g�n�r�es
	 */
	public Map[][] initEtage(){
		Map[][] map = new Map[9][9];
		for(int i=0; i<map.length; i++) {
			for(int j=0; j<map[i].length; j++) {
				map[i][j] = new Map();
				map[i][j].generateMap(j != map.length - 1, j != 0, i != 0, i != map.length - 1);
				map[i][j].generateRandomObstacle((int) (Math.random()*3));
				map[i][j].generateCollisionMap();
			}
		}
		map[4][4] = MapPath.mapStart();
		map[4][3] = MapPath.flyMap();
		map[4][2] = MapPath.spiderMap();
		map[4][1] = MapPath.bossMap();
		return map;
	}
	
	/**
	 * @return Ajoute des ennemis � une pi�ces
	 */
	public void addEnnemis() {
		for(int i=0; i<mapEnCours.getMapobject().length; i++) {
			for(int j=0; j<mapEnCours.getMapobject()[i].length; j++) {
				switch(mapEnCours.getMapobject()[i][j].getEnnemiMap()){
					case 1: getListeEnnemi().addEnnemi(new Fly(25, 25, new Vector2(i*65, j*65),"libImg/Fly.png", 2, 3));break;
					case 2: getListeEnnemi().addEnnemi(new Spider(25, 25, new Vector2(i*65, i*65), 5, "libImg/Spider.png", 3));break;
					case 3: getListeEnnemi().addEnnemi(new Boss(75, 75, new Vector2(i*65, i*65),"libImg/Spider.png", 2, 20));break;
				}
			}
		}
	}
	
	/**
	 * @return D�v�rouille les portes quand tous les ennemis sont morts
	 */
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
	
	/**
	 * @return Update tous les �l�ments de la salle
	 */
	public void updateRoom() {
		Input.getInstance().deplacement();
		Input.getInstance().tire();
		getMapEnCours().changeMap();
		getPlayer().boucleCooldownJoueur();
		getPlayer().updateHitbox();
		listeEnnemi.updateEnnemis();
		mapEnCours.updateObject();
		if(listeEnnemi.isEmpty()) {
			unlockedDoors();
			mapEnCours.setVisited(true);
		}
	}
	
	/**
	 * @return Dessine tous les �l�ments de la salle 
	 */
	public void drawRoom() {	
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		if(this.getPlayer().isAlive()) {
			mapEnCours.drawMap();
			this.drawMiniMap();
			this.getPlayer().getLife().drawBarDeVie();
			Input.getInstance().getPlayerMove().drawPlayer();
			Input.getInstance().drawBalle();
			listeEnnemi.drawEnnemis();
			this.drawItems();
	//		mapEnCours.drawObject();
		} else {
			Texture.gameOver.bind();
			Render.getInstance().drawPicture(0, 0, 585, 585, 1, 1, new float[]{});
			Texture.gameOver.unbind();
		}
	}
	
	/*
	 * Dessine tous les items en possession du joueur
	 */
	public void drawItems() {
		Piece p = new Piece(10, 10, new Vector2(5, 510), "libImg/Penny.png", 0);
		p.drawEntite();
	}
	
	/**
	 * @return Dessine la miniMap
	 */
	public void drawMiniMap() {
		int coef = 2;
		Render.getInstance().drawSquare((float)(Fenetre.WidthFenetre + 5*coef - 58.5*coef), 5, (float)58.5*coef,(float) 58.5*coef, new float[]{255f, 255f, 255f, 255f});
		for(int i=0; i<9; i++) {
			for(int j=0; j<9; j++) {
				if(etage[i][j].isVisited()) {
					Render.getInstance().drawSquare((float)(Fenetre.WidthFenetre - 5.85*coef - 5.85*coef*i), (float)(5 + 5.85*coef*j), (float)5.85*coef, (float)5.85*coef, new float[]{1f, 0f, 1f, 0f});
				}
			}
		}
		Render.getInstance().drawSquare((float)(Fenetre.WidthFenetre - 5.85*coef - 5.85*coef*etageCoos.getX()), (float)(5 + 5.85*coef*etageCoos.getY()), (float)5.85*coef, (float)5.85*coef, new float[]{1f, 0f, 0f, 0f});
	}

	/*
	 * Getters & Setters
	 */
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
