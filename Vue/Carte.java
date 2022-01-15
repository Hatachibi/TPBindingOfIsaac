package com.projetpo.bindingofisaac.module.Vue;

import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glEnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import com.projetpo.bindingofisaac.module.Model.GameWorld;
import com.projetpo.bindingofisaac.module.Model.Jeu;
import com.projetpo.bindingofisaac.module.Model.MapObject;
import com.projetpo.bindingofisaac.module.Model.ObjetsInventaire;
import com.projetpo.bindingofisaac.module.Ressource.RoomInfos;
import com.projetpo.bindingofisaac.module.Shaders.Vector2;

public class Carte {	
	
	/*
	 * Map avec tous les emplacements des objets, ennemis, ...
	 */
	private MapObject[][] mapobject;
	
	/*
	 * Boolean qui indique si une pièce à déjà été visité
	 */
	private boolean isVisited;
	
	/**
	 * Définie si la carte continent un boss ou non.
	 */
	private boolean isBossRoom;
	
	private boolean isShopRoom;
	
	/*
	 * Liste qui contient tous les objets
	 */
	private ArrayList<ObjetsInventaire> objet;
	
	/*
	 * Carte des ennemis dans une room
	 */
	private HashMap<Vector2, Integer> ennemiMap;
	
	/*
	 * Constructeur
	 */
	public Carte() {
		this.mapobject = new MapObject[RoomInfos.NB_HEIGHT_TILES][RoomInfos.NB_WIDTH_TILES];
		for(int i = 0; i<mapobject.length; i++)
		{
			for(int j = 0; j<mapobject[i].length; j++)
			{
				mapobject[i][j] = new MapObject();
			}
		}
		this.isVisited = false;
		this.isBossRoom = false;
		this.setObjet(new ArrayList<ObjetsInventaire>());
		this.setEnnemiMap(new HashMap<Vector2, Integer>());
	}
	
	public Carte(int categorieCarte) {
		this.mapobject = new MapObject[RoomInfos.NB_HEIGHT_TILES][RoomInfos.NB_WIDTH_TILES];
		for(int i = 0; i<mapobject.length; i++)
		{
			for(int j = 0; j<mapobject[i].length; j++)
			{
				mapobject[i][j] = new MapObject();
			}
		}
		this.isVisited = false;
		if(categorieCarte == 1)
		{
			this.isBossRoom = true;
			this.isShopRoom = false;
		}
		else if(categorieCarte == 2)
		{
			this.isBossRoom = false;
			this.isShopRoom = true;
		}
		else
		{
			this.isBossRoom = false;
			this.isShopRoom = false;
		}
		
		this.setObjet(new ArrayList<ObjetsInventaire>());
		this.setEnnemiMap(new HashMap<Vector2, Integer>());
	}
	
	/**
	 * Change de Map quand le joueur passe dans une porte
	 */
	public void changeMap() {
		int x = (int) ((Jeu.gameWorld.getPlayer().getPosition().getX())/65);
		int y = (int) ((Jeu.gameWorld.getPlayer().getPosition().getY())/65);
		if(getRenderMap()[x][y] < 0 || getRenderMap()[x][y+1] == 20 ||  getRenderMap()[x][y-1] == 21 || getRenderMap()[x+1][y] == 22 || getRenderMap()[x-1][y] == 23) {
			if(x==(RoomInfos.NB_HEIGHT_TILES-1)/2 && y==RoomInfos.NB_WIDTH_TILES-1) {
				Jeu.gameWorld.getPlayer().getPosition().setY(65);
				Jeu.gameWorld.setMapEnCours(Jeu.gameWorld.getEtage()[(int) Jeu.gameWorld.getEtageCoos().getX()][(int) (Jeu.gameWorld.getEtageCoos().getY() + 1)]);
				Jeu.gameWorld.getEtageCoos().setY(Jeu.gameWorld.getEtageCoos().getY() + 1);
			}
			else if(x==(RoomInfos.NB_HEIGHT_TILES-1)/2 && y==RoomInfos.NB_WIDTH_TILES-2 && this.getRenderMap()[x][y+1] == 20 && Jeu.gameWorld.getPlayer().getKey() > 0 && Jeu.gameWorld.getMapEnCours().getListeEnnemi().isEmpty())
			{
				this.setRenderMap(x, y+1, -1);
				this.generateCollisionMap();
				Jeu.gameWorld.getPlayer().setKey(Jeu.gameWorld.getPlayer().getKey()-1);
			}
			
			if(x==(RoomInfos.NB_HEIGHT_TILES-1)/2 && y==0) {
				Jeu.gameWorld.getPlayer().getPosition().setY(520);
				Jeu.gameWorld.setMapEnCours(Jeu.gameWorld.getEtage()[(int) Jeu.gameWorld.getEtageCoos().getX()][(int) (Jeu.gameWorld.getEtageCoos().getY() - 1)]);
				Jeu.gameWorld.getEtageCoos().setY(Jeu.gameWorld.getEtageCoos().getY() - 1);
			}
			else if(x==(RoomInfos.NB_HEIGHT_TILES-1)/2 && y==1 && this.getRenderMap()[x][y-1] == 21 && Jeu.gameWorld.getPlayer().getKey() > 0 && Jeu.gameWorld.getMapEnCours().getListeEnnemi().isEmpty())
			{
				this.setRenderMap(x, y-1, -2);
				this.generateCollisionMap();
				Jeu.gameWorld.getPlayer().setKey(Jeu.gameWorld.getPlayer().getKey()-1);
			}
				
			if(x==RoomInfos.NB_HEIGHT_TILES-1 && y==(RoomInfos.NB_WIDTH_TILES - 1)/2) {
				Jeu.gameWorld.getPlayer().getPosition().setX(65);
				Jeu.gameWorld.setMapEnCours(Jeu.gameWorld.getEtage()[(int) Jeu.gameWorld.getEtageCoos().getX() - 1][(int) (Jeu.gameWorld.getEtageCoos().getY())]);
				Jeu.gameWorld.getEtageCoos().setX(Jeu.gameWorld.getEtageCoos().getX() - 1);
			}
			else if(x==RoomInfos.NB_HEIGHT_TILES-2 && y==(RoomInfos.NB_WIDTH_TILES - 1)/2 && this.getRenderMap()[x+1][y] == 22 && Jeu.gameWorld.getPlayer().getKey() > 0 && Jeu.gameWorld.getMapEnCours().getListeEnnemi().isEmpty())
			{
				this.setRenderMap(x+1, y, -3);
				this.generateCollisionMap();
				Jeu.gameWorld.getPlayer().setKey(Jeu.gameWorld.getPlayer().getKey()-1);
			}
				
			if(x==0 && y==(RoomInfos.NB_WIDTH_TILES-1)/2) {
				Jeu.gameWorld.getPlayer().getPosition().setX(520);
				Jeu.gameWorld.setMapEnCours(Jeu.gameWorld.getEtage()[(int) Jeu.gameWorld.getEtageCoos().getX() + 1][(int) (Jeu.gameWorld.getEtageCoos().getY())]);
				Jeu.gameWorld.getEtageCoos().setX(Jeu.gameWorld.getEtageCoos().getX() + 1);
			}
			else if(x==1 && y==(RoomInfos.NB_WIDTH_TILES-1)/2 && this.getRenderMap()[x-1][y] == 23 && Jeu.gameWorld.getPlayer().getKey() > 0 && Jeu.gameWorld.getMapEnCours().getListeEnnemi().isEmpty())
			{
				this.setRenderMap(x-1, y, -4);
				this.generateCollisionMap();
				Jeu.gameWorld.getPlayer().setKey(Jeu.gameWorld.getPlayer().getKey()-1);
			}
		}
	}
	
	/**
	 * Change de Map quand le joueur passe dans une porte
	 */
	public void changeFloor() {
		int x = (int) ((Jeu.gameWorld.getPlayer().getPosition().getX())/65);
		int y = (int) ((Jeu.gameWorld.getPlayer().getPosition().getY())/65);
		if(getRenderMap()[x][y] == -9) {
			if(x==(RoomInfos.NB_HEIGHT_TILES-1)/2 && y==(RoomInfos.NB_WIDTH_TILES-1)/2) {
				Jeu.gameWorld.setFloor(Jeu.gameWorld.getFloor()+1);
				Jeu.gameWorld.initRoom(GameWorld.DEFAULT_NB_ROOMS+Jeu.gameWorld.getFloor(), GameWorld.DEFAULT_NB_MAX_ROCKS+Jeu.gameWorld.getFloor(),  GameWorld.DEFAULT_NB_MAX_SPIKES+Jeu.gameWorld.getFloor(), GameWorld.DEFAULT_NB_MAX_ENNEMIS+Jeu.gameWorld.getFloor());
				Jeu.gameWorld.setMapEnCours(Jeu.gameWorld.getEtage()[4][4]);
				Jeu.gameWorld.setEtageCoos(new Vector2(4, 4));
			}
		}	
	}

	/**
	 * @return La map des collisisons
	 */
	public boolean[][] getCollisionMap() {
		boolean[][] map = new boolean[mapobject.length][mapobject[0].length];
		for(int i=0; i<mapobject.length; i++) {
			for(int j=0; j<mapobject[i].length; j++) {
				map[i][j] = mapobject[i][j].getCollisionMap();
			}
		}
		return map;
	}
	
	/**
	 * @return La map des Rendus visuels 
	 */
	public int[][] getRenderMap() {
		int[][] map = new int[mapobject.length][mapobject[0].length];
		for(int i=0; i<mapobject.length; i++) {
			for(int j=0; j<mapobject[i].length; j++) {
				map[i][j] = mapobject[i][j].getRenderMap();
			}
		}
		return map;
	}
	
	/**
	 * @param i
	 * @param j
	 * @param k le numéro de la case à changé
	 * @return change la case (i, j) avec la valeur k
	 */
	public void setRenderMap(int i, int j, int k) {
		
		if(i>=mapobject.length)
		{
			i = mapobject.length-1;
		}
		if(j>=mapobject[i].length)
		{
			j = mapobject[i].length-1;
		}
		mapobject[i][j].setRenderMap(k);
	}
	
	/*
	 * Update les objets
	 */
	public void updateObject() {
		for(int k=0; k<getObjet().size(); k++) {
			getObjet().get(k).update();
				if(getObjet().get(k).isTouch()) {
					getObjet().remove(k);
			}
		}
	}
		
	/**
	 * @return Initialise la Map
	 */
	public void initMapObject() {
		for(int i=0; i<mapobject.length; i++) {
			for(int j=0; j<mapobject[i].length; j++) {
				mapobject[i][j] = new MapObject();
			}
		}
	}
	
	/**
	 * @return Génère les collisions selon les tiles
	 */
	public void generateCollisionMap() {
		for(int i=0; i<mapobject.length; i++) {
			for(int j=0; j<mapobject[i].length; j++) {
				if(mapobject[i][j].getRenderMap()<0 || mapobject[i][j].getRenderMap() == 0 || mapobject[i][j].getRenderMap() == 11)
				{
					mapobject[i][j].setCollisionMap(false);
				}
				else
				{
					mapobject[i][j].setCollisionMap(true);
				}
			}
		}
	}
	
	/**
	 * @param i Coordonnée en x de l'ennemi
	 * @param j Coordonnée en y de l'ennemi
	 * @param k Type d'ennemi
	 */
	public void addEnnemi(double i, double j, int k) {
		ennemiMap.put(new Vector2(i, j), k); 
	}
	
	/**
	 * @return Génère des obstacles random
	 */
	public void generateRandomObstacle(int nb) {
		for(int i=0; i<nb; i++) {
			int ran1 = 2+(int)(Math.random() * 5);
			int ran2 = 2+(int)(Math.random() * 5);
			mapobject[ran1][ran2].setRenderMap(9);
		}  

	}
	
	/*4, 5, 5, 5, 5, 5, 5, 5, 3,
	6, 0, 0, 0, 0, 0, 0, 0, 8,
	6, 0, 0, 0, 0, 0, 0, 0, 8,
	6, 0, 0, 0, 9, 0, 0, 0, 8,
	6, 0, 0, 0, 9, 0, 0, 0, 8,
	6, 0, 0, 0, 9, 0, 0, 0, 8,
	6, 0, 0, 0, 0, 0, 0, 0, 8,
	6, 0, 0, 0, 0, 0, 0, 0, 8,
	1, 7, 7, 7, 7, 7, 7, 7, 2 */
	/**
	 * @return Génère la map des visuels
	 */
	public void generateRenderMap() {
		for(int i=0; i<mapobject.length; i++) {
			for(int j=0; j<mapobject[i].length; j++) {
				mapobject[i][j].setOverlayMap(new Random().nextInt(20));
				if(i==0 && j==0) mapobject[i][j].setRenderMap(4);
				else if(i==0 && j==mapobject.length-1) mapobject[i][j].setRenderMap(1);
				else if(i==mapobject.length-1 && j==mapobject.length-1) mapobject[i][j].setRenderMap(2);
				else if(i==mapobject.length-1 && j==0) mapobject[i][j].setRenderMap(3);
				else if(j==0) mapobject[i][j].setRenderMap(5);
				else if(j==mapobject.length-1) mapobject[i][j].setRenderMap(7);
				else if(i==mapobject.length-1) mapobject[i][j].setRenderMap(8);
				else if(i==0) mapobject[i][j].setRenderMap(6);
				else mapobject[i][j].setRenderMap(0);
			}
		}
	}
	
	/**
	 * @return Génère une porte en Haut
	 */
	public void generateUpDoor() {
		if(isBossRoom)
			mapobject[(RoomInfos.NB_HEIGHT_TILES-1)/2][RoomInfos.NB_WIDTH_TILES-1].setRenderMap(16);
		else
			mapobject[(RoomInfos.NB_HEIGHT_TILES-1)/2][RoomInfos.NB_WIDTH_TILES-1].setRenderMap(12);
	}
	
	/**
	 * @return Génère une porte en Bas
	 */
	public void generateDownDoor() {
		if(isBossRoom)
			mapobject[(RoomInfos.NB_HEIGHT_TILES-1)/2][0].setRenderMap(17);
		else
			mapobject[(RoomInfos.NB_HEIGHT_TILES-1)/2][0].setRenderMap(13);
	}
	
	/**
	 * @return Génère une porte à Droite
	 */
	public void generateLeftDoor() {
		if(isBossRoom)
		{
			if(RoomInfos.NB_HEIGHT_TILES-1>=mapobject.length)
			{
				mapobject[RoomInfos.NB_WIDTH_TILES-1][(RoomInfos.NB_WIDTH_TILES-1)/2].setRenderMap(18);
			}
			else if(RoomInfos.NB_WIDTH_TILES-1>=mapobject[RoomInfos.NB_HEIGHT_TILES-1].length)
			{
				mapobject[RoomInfos.NB_HEIGHT_TILES-1][(RoomInfos.NB_HEIGHT_TILES-1)/2].setRenderMap(18);
			}
			else
			{
				mapobject[RoomInfos.NB_HEIGHT_TILES-1][(RoomInfos.NB_WIDTH_TILES-1)/2].setRenderMap(18);
			}
		}
		else 
		{
			if(RoomInfos.NB_HEIGHT_TILES-1>=mapobject.length)
			{
				mapobject[RoomInfos.NB_WIDTH_TILES-1][(RoomInfos.NB_WIDTH_TILES-1)/2].setRenderMap(14);
			}
			else if(RoomInfos.NB_WIDTH_TILES-1>=mapobject[RoomInfos.NB_HEIGHT_TILES-1].length)
			{
				mapobject[RoomInfos.NB_HEIGHT_TILES-1][(RoomInfos.NB_HEIGHT_TILES-1)/2].setRenderMap(14);
			}
			else
			{
				mapobject[RoomInfos.NB_HEIGHT_TILES-1][(RoomInfos.NB_WIDTH_TILES-1)/2].setRenderMap(14);
			}
			
		}
			
	}
	
	/**
	 * @return Génère une porte à Gauche
	 */
	public void generateRightDoor() {
		if(isBossRoom)
			mapobject[0][(RoomInfos.NB_WIDTH_TILES-1)/2].setRenderMap(19);
		else
			mapobject[0][(RoomInfos.NB_WIDTH_TILES-1)/2].setRenderMap(15);
	}
	
	/**
	 * @return Génère une porte en Haut
	 */
	public void generateUpBossDoor() {
		mapobject[(RoomInfos.NB_HEIGHT_TILES-1)/2][RoomInfos.NB_WIDTH_TILES-1].setRenderMap(16);
	}
	
	/**
	 * @return Génère une porte en Bas
	 */
	public void generateDownBossDoor() {
		mapobject[(RoomInfos.NB_HEIGHT_TILES-1)/2][0].setRenderMap(17);
	}
	
	/**
	 * @return Génère une porte à Droite
	 */
	public void generateLeftBossDoor() {
		mapobject[RoomInfos.NB_HEIGHT_TILES-1][(RoomInfos.NB_WIDTH_TILES-1)/2].setRenderMap(18);
	}
	
	/**
	 * @return Génère une porte à Gauche
	 */
	public void generateRightBossDoor() {
		mapobject[0][(RoomInfos.NB_WIDTH_TILES-1)/2].setRenderMap(19);
	}
	
	
	
	
	
	
	
	/**
	 * @return Génère une porte en Haut
	 */
	public void generateUpLockedDoor() {
		mapobject[(RoomInfos.NB_HEIGHT_TILES-1)/2][RoomInfos.NB_WIDTH_TILES-1].setRenderMap(20);
	}
	
	/**
	 * @return Génère une porte en Bas
	 */
	public void generateDownLockedDoor() {
		mapobject[(RoomInfos.NB_HEIGHT_TILES-1)/2][0].setRenderMap(21);
	}
	
	/**
	 * @return Génère une porte à Droite
	 */
	public void generateLeftLockedDoor() {
		mapobject[RoomInfos.NB_HEIGHT_TILES-1][(RoomInfos.NB_WIDTH_TILES-1)/2].setRenderMap(22);
	}
	
	/**
	 * @return Génère une porte à Gauche
	 */
	public void generateRightLockedDoor() {
		mapobject[0][(RoomInfos.NB_WIDTH_TILES-1)/2].setRenderMap(23);
	}
	
	
	/**
	 * @param up
	 * @param down
	 * @param left
	 * @param right
	 * @return Génrère une Map avec les portes selon les boolean up, down, left et right
	 */
	public void generateMap(boolean up, boolean down, boolean left, boolean right) {
		initMapObject();
		generateRenderMap();
		if(up)generateUpDoor();
		if(down)generateDownDoor();
		if(left)generateLeftDoor();
		if(right)generateRightDoor();
	}
	
	/*
	 * Dessine les Objets
	 */
	public void drawObject() {
		for(int k=0; k<getObjet().size(); k++) {
			getObjet().get(k).drawEntite();
			if(Jeu.gameWorld.getMapEnCours().isShopRoom()) {
				Render.getInstance().drawText((float) 130*(k+1), (float) 265, this.getObjet().get(k).getPrice() + "$");
			}
		}
	}
	
	public void addOverlay(int random, int i, int j) {
		switch(random) {
		case 1:
			Texture.overlay1.bind();
			Render.getInstance().drawPicture(i*RoomInfos.TAILLE_CARRE, j*RoomInfos.TAILLE_CARRE, RoomInfos.TAILLE_CARRE, RoomInfos.TAILLE_CARRE);
			Texture.overlay1.unbind();	
			break;
		case 2:
			Texture.overlay2.bind();
			Render.getInstance().drawPicture(i*RoomInfos.TAILLE_CARRE, j*RoomInfos.TAILLE_CARRE, RoomInfos.TAILLE_CARRE, RoomInfos.TAILLE_CARRE );
			Texture.overlay2.unbind();
		break;
		case 3:
			Texture.overlay3.bind();
			Render.getInstance().drawPicture(i*RoomInfos.TAILLE_CARRE, j*RoomInfos.TAILLE_CARRE, RoomInfos.TAILLE_CARRE, RoomInfos.TAILLE_CARRE );
			Texture.overlay3.unbind();
		break;
		case 4:
			Texture.overlay4.bind();
			Render.getInstance().drawPicture(i*RoomInfos.TAILLE_CARRE, j*RoomInfos.TAILLE_CARRE, RoomInfos.TAILLE_CARRE, RoomInfos.TAILLE_CARRE );
			Texture.overlay4.unbind();
		break;
		case 5:
			Texture.overlay5.bind();
			Render.getInstance().drawPicture(i*RoomInfos.TAILLE_CARRE, j*RoomInfos.TAILLE_CARRE, RoomInfos.TAILLE_CARRE, RoomInfos.TAILLE_CARRE );
			Texture.overlay5.unbind();
			break;
		}
	}
	
	/**
	 * @return Dessine la Map
	 */
	public void drawMap() {
		for(int i=0; i<mapobject.length; i++) {
			for(int j=0; j<mapobject[i].length; j++) {
				if(mapobject[i][j].getRenderMap()==1) {
					Texture.coinHG.bind();
				} else if(mapobject[i][j].getRenderMap()==2) {
					Texture.coinHD.bind();
				} else if(mapobject[i][j].getRenderMap()==3) {
					Texture.coinBD.bind();
				} else if(mapobject[i][j].getRenderMap()==4) {
					Texture.coinBG.bind();
				} else if(mapobject[i][j].getRenderMap()==5) {
					Texture.murBas.bind();
				} else if(mapobject[i][j].getRenderMap()==6) {
					Texture.murGauche.bind();
				} else if(mapobject[i][j].getRenderMap()==7) {
					Texture.murHaut.bind();
				} else if(mapobject[i][j].getRenderMap()==8) {
					Texture.murDroite.bind();
				} else if(mapobject[i][j].getRenderMap()==9) {
					Texture.emptyCell.bind();
					Render.getInstance().drawPicture(i*RoomInfos.TAILLE_CARRE, j*RoomInfos.TAILLE_CARRE, RoomInfos.TAILLE_CARRE, RoomInfos.TAILLE_CARRE );
					Texture.emptyCell.unbind();
					Texture.rock.bind();
				}
				else if(mapobject[i][j].getRenderMap()==-1) 
				{
					Texture.murHaut.bind();
					Render.getInstance().drawPicture(i*RoomInfos.TAILLE_CARRE, j*RoomInfos.TAILLE_CARRE, RoomInfos.TAILLE_CARRE, RoomInfos.TAILLE_CARRE );
					Texture.murHaut.unbind();
					Texture.top_openDoor.bind();
					Render.getInstance().drawPicture(i*RoomInfos.TAILLE_CARRE, j*RoomInfos.TAILLE_CARRE, RoomInfos.TAILLE_CARRE, RoomInfos.TAILLE_CARRE );
				} 
				else if(mapobject[i][j].getRenderMap()==-2) 
				{
					Texture.murBas.bind();
					Render.getInstance().drawPicture(i*RoomInfos.TAILLE_CARRE, j*RoomInfos.TAILLE_CARRE, RoomInfos.TAILLE_CARRE, RoomInfos.TAILLE_CARRE );
					Texture.murBas.unbind();
					Texture.bot_openDoor.bind();
					Render.getInstance().drawPicture(i*RoomInfos.TAILLE_CARRE, j*RoomInfos.TAILLE_CARRE, RoomInfos.TAILLE_CARRE, RoomInfos.TAILLE_CARRE );
				} 
				else if(mapobject[i][j].getRenderMap()==-3) 
				{
					Texture.murDroite.bind();
					Render.getInstance().drawPicture(i*RoomInfos.TAILLE_CARRE, j*RoomInfos.TAILLE_CARRE, RoomInfos.TAILLE_CARRE, RoomInfos.TAILLE_CARRE );
					Texture.murDroite.unbind();
					Texture.right_openDoor.bind();
					Render.getInstance().drawPicture(i*RoomInfos.TAILLE_CARRE, j*RoomInfos.TAILLE_CARRE, RoomInfos.TAILLE_CARRE, RoomInfos.TAILLE_CARRE );
				} 
				else if(mapobject[i][j].getRenderMap()==-4) 
				{
					Texture.murGauche.bind();
					Render.getInstance().drawPicture(i*RoomInfos.TAILLE_CARRE, j*RoomInfos.TAILLE_CARRE, RoomInfos.TAILLE_CARRE, RoomInfos.TAILLE_CARRE );
					Texture.murGauche.unbind();
					Texture.left_openDoor.bind();
					Render.getInstance().drawPicture(i*RoomInfos.TAILLE_CARRE, j*RoomInfos.TAILLE_CARRE, RoomInfos.TAILLE_CARRE, RoomInfos.TAILLE_CARRE );
				}
				else if(mapobject[i][j].getRenderMap()==-5) 
				{
					Texture.murHaut.bind();
					Render.getInstance().drawPicture(i*RoomInfos.TAILLE_CARRE, j*RoomInfos.TAILLE_CARRE, RoomInfos.TAILLE_CARRE, RoomInfos.TAILLE_CARRE );
					Texture.murHaut.unbind();
					Texture.top_openBossDoor.bind();
					Render.getInstance().drawPicture(i*RoomInfos.TAILLE_CARRE, j*RoomInfos.TAILLE_CARRE, RoomInfos.TAILLE_CARRE, RoomInfos.TAILLE_CARRE );
				} 
				else if(mapobject[i][j].getRenderMap()==-6) 
				{
					Texture.murBas.bind();
					Render.getInstance().drawPicture(i*RoomInfos.TAILLE_CARRE, j*RoomInfos.TAILLE_CARRE, RoomInfos.TAILLE_CARRE, RoomInfos.TAILLE_CARRE );
					Texture.murBas.unbind();
					Texture.bot_openBossDoor.bind();
					Render.getInstance().drawPicture(i*RoomInfos.TAILLE_CARRE, j*RoomInfos.TAILLE_CARRE, RoomInfos.TAILLE_CARRE, RoomInfos.TAILLE_CARRE );
				} 
				else if(mapobject[i][j].getRenderMap()==-7) 
				{
					Texture.murDroite.bind();
					Render.getInstance().drawPicture(i*RoomInfos.TAILLE_CARRE, j*RoomInfos.TAILLE_CARRE, RoomInfos.TAILLE_CARRE, RoomInfos.TAILLE_CARRE );
					Texture.murDroite.unbind();
					Texture.right_openBossDoor.bind();
					Render.getInstance().drawPicture(i*RoomInfos.TAILLE_CARRE, j*RoomInfos.TAILLE_CARRE, RoomInfos.TAILLE_CARRE, RoomInfos.TAILLE_CARRE );
				}
				else if(mapobject[i][j].getRenderMap()==-8) 
				{
					Texture.murGauche.bind();
					Render.getInstance().drawPicture(i*RoomInfos.TAILLE_CARRE, j*RoomInfos.TAILLE_CARRE, RoomInfos.TAILLE_CARRE, RoomInfos.TAILLE_CARRE );
					Texture.murGauche.unbind();
					Texture.left_openBossDoor.bind();
					Render.getInstance().drawPicture(i*RoomInfos.TAILLE_CARRE, j*RoomInfos.TAILLE_CARRE, RoomInfos.TAILLE_CARRE, RoomInfos.TAILLE_CARRE );
				} else if(mapobject[i][j].getRenderMap()==-9) {
					Texture.emptyCell.bind();
					Render.getInstance().drawPicture(i*RoomInfos.TAILLE_CARRE, j*RoomInfos.TAILLE_CARRE, RoomInfos.TAILLE_CARRE, RoomInfos.TAILLE_CARRE );
					Texture.emptyCell.unbind();
					Texture.trapdoor.bind();
				}
				else if(mapobject[i][j].getRenderMap()==11) 
				{
					Texture.emptyCell.bind();
					Render.getInstance().drawPicture(i*RoomInfos.TAILLE_CARRE, j*RoomInfos.TAILLE_CARRE, RoomInfos.TAILLE_CARRE, RoomInfos.TAILLE_CARRE );
					Texture.emptyCell.unbind();
					Texture.spikes.bind();
				} 
				else if(mapobject[i][j].getRenderMap()==12) 
				{
					Texture.murHaut.bind();
					Render.getInstance().drawPicture(i*RoomInfos.TAILLE_CARRE, j*RoomInfos.TAILLE_CARRE, RoomInfos.TAILLE_CARRE, RoomInfos.TAILLE_CARRE );
					Texture.murHaut.unbind();
					Texture.closeDoor_up.bind();
				} 
				else if(mapobject[i][j].getRenderMap()==13) 
				{
					Texture.murBas.bind();
					Render.getInstance().drawPicture(i*RoomInfos.TAILLE_CARRE, j*RoomInfos.TAILLE_CARRE, RoomInfos.TAILLE_CARRE, RoomInfos.TAILLE_CARRE );
					Texture.murBas.unbind();
					Texture.closeDoor_down.bind();
				} 
				else if(mapobject[i][j].getRenderMap()==14) 
				{
					Texture.murDroite.bind();
					Render.getInstance().drawPicture(i*RoomInfos.TAILLE_CARRE, j*RoomInfos.TAILLE_CARRE, RoomInfos.TAILLE_CARRE, RoomInfos.TAILLE_CARRE );
					Texture.murDroite.unbind();
					Texture.closeDoor_right.bind();
				} 
				else if(mapobject[i][j].getRenderMap()==15) 
				{
					Texture.murGauche.bind();
					Render.getInstance().drawPicture(i*RoomInfos.TAILLE_CARRE, j*RoomInfos.TAILLE_CARRE, RoomInfos.TAILLE_CARRE, RoomInfos.TAILLE_CARRE );
					Texture.murGauche.unbind();
					Texture.closeDoor_left.bind();
				} 
				else if(mapobject[i][j].getRenderMap()==16) 
				{
					Texture.murHaut.bind();
					Render.getInstance().drawPicture(i*RoomInfos.TAILLE_CARRE, j*RoomInfos.TAILLE_CARRE, RoomInfos.TAILLE_CARRE, RoomInfos.TAILLE_CARRE );
					Texture.murHaut.unbind();
					Texture.closeBossDoor_up.bind();
				} 
				else if(mapobject[i][j].getRenderMap()==17) 
				{
					Texture.murBas.bind();
					Render.getInstance().drawPicture(i*RoomInfos.TAILLE_CARRE, j*RoomInfos.TAILLE_CARRE, RoomInfos.TAILLE_CARRE, RoomInfos.TAILLE_CARRE );
					Texture.murBas.unbind();
					Texture.closeBossDoor_down.bind();
				} 
				else if(mapobject[i][j].getRenderMap()==18) 
				{
					Texture.murDroite.bind();
					Render.getInstance().drawPicture(i*RoomInfos.TAILLE_CARRE, j*RoomInfos.TAILLE_CARRE, RoomInfos.TAILLE_CARRE, RoomInfos.TAILLE_CARRE );
					Texture.murDroite.unbind();
					Texture.closeBossDoor_right.bind();
				} 
				else if(mapobject[i][j].getRenderMap()==19) 
				{
					Texture.murGauche.bind();
					Render.getInstance().drawPicture(i*RoomInfos.TAILLE_CARRE, j*RoomInfos.TAILLE_CARRE, RoomInfos.TAILLE_CARRE, RoomInfos.TAILLE_CARRE );
					Texture.murGauche.unbind();
					Texture.closeBossDoor_left.bind();
				}else if(mapobject[i][j].getRenderMap()==20) 
				{
					Texture.murHaut.bind();
					Render.getInstance().drawPicture(i*RoomInfos.TAILLE_CARRE, j*RoomInfos.TAILLE_CARRE, RoomInfos.TAILLE_CARRE, RoomInfos.TAILLE_CARRE);
					Texture.murHaut.unbind();
					Texture.lockedcloseDoor_up.bind();
				} 
				else if(mapobject[i][j].getRenderMap()==21) 
				{
					Texture.murBas.bind();
					Render.getInstance().drawPicture(i*RoomInfos.TAILLE_CARRE, j*RoomInfos.TAILLE_CARRE, RoomInfos.TAILLE_CARRE, RoomInfos.TAILLE_CARRE);
					Texture.murBas.unbind();
					Texture.lockedcloseDoor_down.bind();
				} 
				else if(mapobject[i][j].getRenderMap()==22) 
				{
					Texture.murDroite.bind();
					Render.getInstance().drawPicture(i*RoomInfos.TAILLE_CARRE, j*RoomInfos.TAILLE_CARRE, RoomInfos.TAILLE_CARRE, RoomInfos.TAILLE_CARRE);
					Texture.murDroite.unbind();
					Texture.lockedcloseDoor_right.bind();
				} 
				else if(mapobject[i][j].getRenderMap()==23) 
				{
					Texture.murGauche.bind();
					Render.getInstance().drawPicture(i*RoomInfos.TAILLE_CARRE, j*RoomInfos.TAILLE_CARRE, RoomInfos.TAILLE_CARRE, RoomInfos.TAILLE_CARRE);
					Texture.murGauche.unbind();
					Texture.lockedcloseDoor_left.bind();
				}
				else {
					Texture.emptyCell.bind();
				}
				glEnable(GL_TEXTURE_2D);
				Render.getInstance().drawPicture(i*RoomInfos.TAILLE_CARRE, j*RoomInfos.TAILLE_CARRE, RoomInfos.TAILLE_CARRE, RoomInfos.TAILLE_CARRE );
				Texture.coinHG.unbind();
				Texture.coinHD.unbind();
				Texture.coinBG.unbind();
				Texture.coinBD.unbind();
				Texture.murHaut.unbind();
				Texture.murBas.unbind();
				Texture.murGauche.unbind();
				Texture.murDroite.unbind();
				Texture.top_openDoor.unbind();
				Texture.bot_openDoor.unbind();
				Texture.right_openDoor.unbind();
				Texture.left_openDoor.unbind();
				Texture.top_openBossDoor.unbind();
				Texture.bot_openBossDoor.unbind();
				Texture.right_openBossDoor.unbind();
				Texture.left_openBossDoor.unbind();
				Texture.rock.unbind();
				Texture.closeDoor_up.unbind();
				Texture.closeDoor_down.unbind();
				Texture.closeDoor_right.unbind();
				Texture.closeDoor_left.unbind();
				Texture.closeBossDoor_up.unbind();
				Texture.closeBossDoor_down.unbind();
				Texture.closeBossDoor_right.unbind();
				Texture.closeBossDoor_left.unbind();
				Texture.spikes.unbind();
				Texture.emptyCell.unbind();
				Texture.trapdoor.unbind();
				if(mapobject[i][j].getRenderMap() >=1 && mapobject[i][j].getRenderMap() <= 8) {
					this.addOverlay(mapobject[i][j].getOverlayMap(), i, j);
				}
			}
		}
		
	}
	
	/*
	 * Getters & Setters
	 */
	public MapObject[][] getMapobject() {
		return mapobject;
	}

	public void setMapobject(MapObject[][] mapobject) {
		this.mapobject = mapobject;
	}

	public boolean isVisited() {
		return isVisited;
	}

	public void setVisited(boolean isVisited) {
		this.isVisited = isVisited;
	}

	public ArrayList<ObjetsInventaire> getObjet() {
		return objet;
	}

	public void setObjet(ArrayList<ObjetsInventaire> objet) {
		this.objet = objet;
	}

	public HashMap<Vector2, Integer> getEnnemiMap() {
		return ennemiMap;
	}

	public void setEnnemiMap(HashMap<Vector2, Integer> ennemiMap) {
		this.ennemiMap = ennemiMap;
	}
	
	public boolean isBossRoom()
	{
		return isBossRoom;
	}

	public boolean isShopRoom() {
		return isShopRoom;
	}
}
