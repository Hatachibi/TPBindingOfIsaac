package com.projetpo.bindingofisaac.module.Model;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import com.projetpo.bindingofisaac.module.Controler.Input;
import com.projetpo.bindingofisaac.module.Controler.listeEnnemi;
import com.projetpo.bindingofisaac.module.Model.Ennemis.Boss;
import com.projetpo.bindingofisaac.module.Model.Ennemis.Fly;
import com.projetpo.bindingofisaac.module.Model.Ennemis.Gorb;
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
	
	/*
	 * Constructeur
	 */
	public Room(Personnage player, Carte carte) {
		this.player = player;
		this.carte = carte;
		this.listeEnnemi = new listeEnnemi();
		this.addEnnemis();
	}
	
	/**
	 * @return Ajoute des ennemis à une pièces
	 */
	public void addEnnemis() {
		for(int i=0; i<carte.getMapobject().length; i++) {
			for(int j=0; j<carte.getMapobject()[i].length; j++) {
				switch(carte.getMapobject()[i][j].getEnnemiMap()){
					case 1: getListeEnnemi().addEnnemi(new Fly(25, 25, new Vector2(i*65, j*65),"src/main/resources/fly.png", player.getSpeed()/8));break;
					case 2: getListeEnnemi().addEnnemi(new Spider(25, 25, new Vector2(i*65, i*65),"src/main/resources/Spider.png", 11.7));break;
					case 3: getListeEnnemi().addEnnemi(new Boss(75, 75, new Vector2(i*65, i*65),"", 2));break;
					case 4: getListeEnnemi().addEnnemi(new Sprinter(25, 25, new Vector2(i*65, j*65),player.getSpeed()*3, "src/main/resources/Dart_Fly.png"));break;
					case 5: getListeEnnemi().addEnnemi(new Gorb(25, 25, new Vector2(i*65, j*65),player.getSpeed()/8, "src/main/resources/Gaper.png"));break;
				}
			}
		}
	}
	
	/**
	 * @return Dévérouille les portes quand tous les ennemis sont morts
	 */
	public void unlockedDoors() {
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
	 * @return Update tous les éléments de la salle
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
	 * @return Dessine tous les éléments de la salle 
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
	 * @return Dessine les icones des items sur le côté
	 */
	public void drawItems() {
		ObjetsInventaire piece = new ObjetsInventaire(10, 10, 10, new Vector2(15, 510), "");
		piece.drawEntite();
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
	
}
