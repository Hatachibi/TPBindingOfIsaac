package com.projetpo.bindingofisaac.module.Model;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;

import com.projetpo.bindingofisaac.module.Controler.Input;
import com.projetpo.bindingofisaac.module.Controler.listeEnnemi;
import com.projetpo.bindingofisaac.module.Model.Ennemis.Boss;
import com.projetpo.bindingofisaac.module.Model.Ennemis.Fly;
import com.projetpo.bindingofisaac.module.Model.Ennemis.Gasper;
import com.projetpo.bindingofisaac.module.Model.Ennemis.Parabite;
import com.projetpo.bindingofisaac.module.Model.Ennemis.ParabiteBalle;
import com.projetpo.bindingofisaac.module.Model.Ennemis.Pooter;
import com.projetpo.bindingofisaac.module.Model.Ennemis.Spider;
import com.projetpo.bindingofisaac.module.Model.Ennemis.Sprinter;
import com.projetpo.bindingofisaac.module.Shaders.Vector2;
import com.projetpo.bindingofisaac.module.Vue.Carte;
import com.projetpo.bindingofisaac.module.Vue.Render;
import com.projetpo.bindingofisaac.module.Vue.Texture;

public class Room {
	
	/*
	 * Joueur dans la pièce
	 */
	private Personnage player;
	
	/*
	 * Liste ennemie dans la pièce
	 */
	private listeEnnemi listeEnnemi;
	
	/*
	 * Pièce en cours
	 */
	private Carte carte;
	
	private boolean isBossRoom;
	
	/*
	 * Constructeur
	 */
	public Room(Personnage player, Carte carte) {
		this.player = player;
		this.carte = carte;
		this.listeEnnemi = new listeEnnemi();
		this.addEnnemis();
		this.isBossRoom = carte.isBossRoom();
	}
	
	/**
	 * @return Ajoute des ennemis à une pièces
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
				case 7: getListeEnnemi().addEnnemi(new ParabiteBalle(25, 25, v, 12, "src/main/resources/parabite.png"));
				case 8: getListeEnnemi().addEnnemi(new Parabite(25, 25, v, 12, "src/main/resources/parabite.png"));
			}
		}
	}
	
	/**
	 * @return Dévérouille les portes quand tous les ennemis sont morts
	 */
	public void unlockedDoors() {
		if(carte.getRenderMap()[4][8] == 16) {
			if(isBossRoom)
			carte.setRenderMap(4, 8, -5);
			carte.generateCollisionMap();
		}
		if(carte.getRenderMap()[4][0] == 17) {
			carte.setRenderMap(4, 0, -6);
			carte.generateCollisionMap();
		}
		if(carte.getRenderMap()[8][4] == 18) {
			carte.setRenderMap(8, 4, -7);
			carte.generateCollisionMap();
		}
		if(carte.getRenderMap()[0][4] == 19) {
			carte.setRenderMap(0, 4, -8);
			carte.generateCollisionMap();
		}
		if(carte.getRenderMap()[4][8] == 12) {
			carte.setRenderMap(4, 8, -1);
			carte.generateCollisionMap();
		}
		if(carte.getRenderMap()[4][0] == 13) {
			carte.setRenderMap(4, 0, -2);
			carte.generateCollisionMap();
		}
		if(carte.getRenderMap()[8][4] == 14) {
			carte.setRenderMap(8, 4, -3);
			carte.generateCollisionMap();
		}
		if(carte.getRenderMap()[0][4] == 15) {
			carte.setRenderMap(0, 4, -4);
			carte.generateCollisionMap();
		}
	}
	
	/**
	 * Update tous les éléments de la salle
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
				unlockedDoors();
				carte.setVisited(true);
			}
		} 
	}
	
	/**
	 * Dessine tous les éléments de la salle 
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
		this.getPlayer().getLife().drawBarDeVie();
		listeEnnemi.drawEnnemis();
		drawItems();
		carte.drawObject();
	}
	
	/**
	 * Dessine les icones des items sur le côté
	 */
	public void drawItems() {
		ObjetsInventaire piece = new ObjetsInventaire(10, 10, 10, new Vector2(15, 510), "");
		piece.drawEntite();
		Render.getInstance().drawText(55, 515, player.getCoin()+"");
	//	Render.getInstance().drawText(15, 490, "x"+player.getMultiplicator());
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
	
	public boolean isBossRoom()
	{
		return isBossRoom;
	}
	
}
