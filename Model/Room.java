package com.projetpo.bindingofisaac.module.Model;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;

import java.util.LinkedList;

import com.projetpo.bindingofisaac.module.Controler.Input;
import com.projetpo.bindingofisaac.module.Controler.listeEnnemi;
import com.projetpo.bindingofisaac.module.Model.Ennemis.Boss;
import com.projetpo.bindingofisaac.module.Model.Ennemis.BossShoot;
import com.projetpo.bindingofisaac.module.Model.Ennemis.BossWave;
import com.projetpo.bindingofisaac.module.Model.Ennemis.Essaim;
import com.projetpo.bindingofisaac.module.Model.Ennemis.Fly;
import com.projetpo.bindingofisaac.module.Model.Ennemis.Gasper;
import com.projetpo.bindingofisaac.module.Model.Ennemis.Parabite;
import com.projetpo.bindingofisaac.module.Model.Ennemis.ParabiteBalle;
import com.projetpo.bindingofisaac.module.Model.Ennemis.Pooter;
import com.projetpo.bindingofisaac.module.Model.Ennemis.Spider;
import com.projetpo.bindingofisaac.module.Model.Ennemis.Sprinter;
import com.projetpo.bindingofisaac.module.Ressource.RoomInfos;
import com.projetpo.bindingofisaac.module.Shaders.Vector2;
import com.projetpo.bindingofisaac.module.Vue.Carte;
import com.projetpo.bindingofisaac.module.Vue.Fenetre;
import com.projetpo.bindingofisaac.module.Vue.Render;
import com.projetpo.bindingofisaac.module.Vue.Texture;

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
	private Carte carte;
	
	private boolean isBossRoom;
	
	private boolean isShopRoom;
	
	/*
	 * Liste des bombes � afficher
	 */
	private LinkedList<Bombe> bombList;
	
	/*
	 * Constructeur
	 */
	public Room(Personnage player, Carte carte) {
		this.player = player;
		this.carte = carte;
		this.listeEnnemi = new listeEnnemi();
		this.bombList = new LinkedList<Bombe>();
		this.addEnnemis();
		this.isBossRoom = carte.isBossRoom();
		this.isShopRoom = carte.isShopRoom();
	}
	
	/**
	 * @return Ajoute des ennemis � une pi�ces
	 */
	public void addEnnemis() {
		for(Vector2 v: carte.getEnnemiMap().keySet()) {
			switch(carte.getEnnemiMap().get(v)){
				case 1: getListeEnnemi().addEnnemi(new Fly(25, 25, v,"src/main/resources/fly.png", player.getSpeed()/8));break;
				case 2: getListeEnnemi().addEnnemi(new Spider(25, 25,  v,"src/main/resources/Spider.png", 11.7));break;
				case 3: getListeEnnemi().addEnnemi(new Boss(75, 75,  v,"", 2));break;
				case 4: getListeEnnemi().addEnnemi(new Sprinter(25, 25,  v,player.getSpeed()*3, "src/main/resources/Dart_Fly.png"));break;
				case 5: getListeEnnemi().addEnnemi(new Gasper(25, 25,  v,player.getSpeed()/8, "src/main/resources/Gaper.png"));break;
				case 6: getListeEnnemi().addEnnemi(new Pooter(25, 25, v, "src/main/resources/pooter.png", player.getSpeed()/8));break;
				case 7: getListeEnnemi().addEnnemi(new ParabiteBalle(25, 25, v, 12, "src/main/resources/parabite.png"));break;
				case 8: getListeEnnemi().addEnnemi(new Parabite(25, 25, v, 12, "src/main/resources/parabite.png"));break;
				case 9: getListeEnnemi().addEnnemi(new Essaim(25, 25, v, 12, "src/main/resources/parabite.png"));break;
				case 10: getListeEnnemi().addEnnemi(new BossShoot(25, 25, v, 1, "src/main/resources/parabite.png"));break;
				case 11: getListeEnnemi().addEnnemi(new BossWave(25, 25, v, 1, "src/main/resources/fly.png"));break;
			}
		}
	}
	
	/**
	 * @return D�v�rouille les portes quand tous les ennemis sont morts
	 */
	public void unlockedDoors() {
		if(carte.getRenderMap()[(RoomInfos.NB_HEIGHT_TILES-1)/2][RoomInfos.NB_WIDTH_TILES-1] == 16) {
			carte.setRenderMap((RoomInfos.NB_HEIGHT_TILES-1)/2, RoomInfos.NB_WIDTH_TILES-1, -5);
			carte.generateCollisionMap();
		}
		if(carte.getRenderMap()[(RoomInfos.NB_HEIGHT_TILES-1)/2][0] == 17) {
			carte.setRenderMap((RoomInfos.NB_HEIGHT_TILES-1)/2, 0, -6);
			carte.generateCollisionMap();
		}
		if(carte.getRenderMap()[(RoomInfos.NB_HEIGHT_TILES-1)][(RoomInfos.NB_WIDTH_TILES-1)/2] == 18) {
			carte.setRenderMap((RoomInfos.NB_HEIGHT_TILES-1), (RoomInfos.NB_WIDTH_TILES-1)/2, -7);
			carte.generateCollisionMap();
		}
		if(carte.getRenderMap()[0][(RoomInfos.NB_WIDTH_TILES-1)/2] == 19) {
			carte.setRenderMap(0, (RoomInfos.NB_WIDTH_TILES-1)/2, -8);
			carte.generateCollisionMap();
		}
		if(carte.getRenderMap()[(RoomInfos.NB_HEIGHT_TILES-1)/2][RoomInfos.NB_WIDTH_TILES-1] == 12) {
			carte.setRenderMap((RoomInfos.NB_HEIGHT_TILES-1)/2, RoomInfos.NB_WIDTH_TILES-1, -1);
			carte.generateCollisionMap();
		}
		if(carte.getRenderMap()[(RoomInfos.NB_HEIGHT_TILES-1)/2][0] == 13) {
			carte.setRenderMap((RoomInfos.NB_HEIGHT_TILES-1)/2, 0, -2);
			carte.generateCollisionMap();
		}
		if(carte.getRenderMap()[(RoomInfos.NB_HEIGHT_TILES-1)][(RoomInfos.NB_WIDTH_TILES-1)/2] == 14) {
			carte.setRenderMap((RoomInfos.NB_HEIGHT_TILES-1), (RoomInfos.NB_WIDTH_TILES-1)/2, -3);
			carte.generateCollisionMap();
		}
		if(carte.getRenderMap()[0][(RoomInfos.NB_WIDTH_TILES-1)/2] == 15) {
			carte.setRenderMap(0, (RoomInfos.NB_WIDTH_TILES-1)/2, -4);
			carte.generateCollisionMap();
		}
	/*	if(carte.isBossRoom()) {
			Jeu.gameWorld.setEtage(GenerateFloor.generateFloor(10, 6, 5));
			Jeu.gameWorld.setEtageCoos(new Vector2(4, 4));
			Jeu.gameWorld.setMapEnCours(Jeu.gameWorld.getEtage()[4][4]);
			this.setcarte(Jeu.gameWorld.getMapEnCours().getcarte());
		} */
	}
	
	/**
	 * Update tous les �l�ments de la salle
	 */
	public void updateRoom() {
		if(this.getPlayer().isAlive()) {
			Input.getInstance().deplacement();
			Input.getInstance().tire();
			getcarte().changeMap();
			getPlayer().boucleCooldownJoueur();
			getPlayer().updateHitbox();
			listeEnnemi.updateEnnemis();
			carte.updateObject();
			if(listeEnnemi.isEmpty()) {
				if(isBossRoom) {
					carte.setRenderMap((RoomInfos.NB_HEIGHT_TILES-1)/2, (RoomInfos.NB_WIDTH_TILES-1)/2, -9);
					carte.changeFloor();
				} 
				unlockedDoors();
				carte.setVisited(true);
			}
		} 
	}
	
	/**
	 * Dessine tous les �l�ments de la salle 
	 */
	public void drawRoom() {	
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		carte.drawMap();
		if(Jeu.gameWorld.getEtageCoos().equals(new Vector2(4, 4))) {
			Texture.spawnDraw.bind();
			Render.getInstance().drawPicture(100, 250, (int)(Texture.spawnDraw.getWidth()*1.3), (int)(Texture.spawnDraw.getHeight()*1.3));
			Texture.spawnDraw.unbind();	
		}
		Input.getInstance().drawBalle();
		Input.getInstance().getPlayerMove().drawPlayer();
		listeEnnemi.drawEnnemis();
		carte.drawObject();
		Texture.shaderRoom.bind();
		Render.getInstance().drawPicture(0, 0, Fenetre.WidthFenetre, Fenetre.HeigthFenetre);
		Texture.shaderRoom.unbind(); 
		//GL11.glColor4f(1f, 1f, 1f, 1f);
		drawItems();
		this.getPlayer().getLife().drawBarDeVie();
		drawPlayerItems();
	}
	
	/**
	 * Dessine les icones des items sur le c�t�
	 */
	public void drawItems() {
		ObjetsInventaire piece = new ObjetsInventaire(10, 10, 10, new Vector2(15, 510), "");
		piece.drawEntite();
		Render.getInstance().drawText(55, 515, player.getCoin()+"");
	//	Render.getInstance().drawText(15, 490, "x"+player.getMultiplicator());
		Bombe b = new Bombe(-3, 20, 20, new Vector2(12, 470),"src/main/resources/Bomb.png");
		b.drawEntite();
		Render.getInstance().drawText(55, 480, player.getInv().getNbBombe()+"");
	}
	
	public void drawPlayerItems() {
		for(Bombe b: bombList) {
			b.draw();
		}
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

	public Carte getcarte() {
		return carte;
	}

	public void setcarte(Carte carte) {
		this.carte = carte;
	}
	
	public listeEnnemi getListeEnnemi() {
		return listeEnnemi;
	}

	public void setListeEnnemi(listeEnnemi listeEnnemi) {
		this.listeEnnemi = listeEnnemi;
	}

	public LinkedList<Bombe> getBombList() {
		return bombList;
	}

	public void setBombList(LinkedList<Bombe> bombList) {
		this.bombList = bombList;
	}
	
	public boolean isBossRoom()
	{
		return isBossRoom;
	}

	public boolean isShopRoom() {
		return isShopRoom;
	}
	
}
