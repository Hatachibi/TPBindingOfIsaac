package com.projetpo.bindingofisaac.module.Vue;

import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.projetpo.bindingofisaac.module.Model.Personnage;
import com.projetpo.bindingofisaac.module.Model.Room;
import com.projetpo.bindingofisaac.module.Shaders.Vector2;

public class GameWorld {
	
	private Room [][] etage;
	private Vector2 etageCoos;
	private Room mapEnCours;
	private Personnage player;
	private boolean first;
	private int floor = 0;
	public static final int DEFAULT_NB_ROOMS = 6;
	public static final int DEFAULT_NB_MAX_ROCKS = 2;
	public static final int DEFAULT_NB_MAX_SPIKES = 2;
	public static final int DEFAULT_NB_MAX_ENNEMIS = 2;
	
	public GameWorld() {
		this.etage = new Room[9][9];
		this.setEtageCoos(new Vector2(4, 4));
		this.setPersonnage(new Personnage(10, 25, 25, new Vector2(100, 100), new Vector2(1, 1), "libImg/Isaac.png")); // Personnage par défaut
		this.first = true;
	}
	
	public void initRoom(int nbRooms, int nbMaxRocks, int nbMaxSpikes, int nbMaxEnnemis)
	{
		this.etage = GenerateFloor.generateFloor(nbRooms, nbMaxRocks, nbMaxSpikes, nbMaxEnnemis);
		this.setMapEnCours(etage[4][4]);
	}
	
	public void updateWorld() {
		etage[(int) etageCoos.getX()][(int) etageCoos.getY()].updateRoom();
	}
	
	public void drawWorld() {
		if(player.isAlive()) {
			etage[(int) etageCoos.getX()][(int) etageCoos.getY()].drawRoom();
			this.drawMiniMap();
		} else {
			if(first){
				playDeathEffect((int)(Math.random()*3));
			}
			first = false; 
			Fenetre.getInstance().setState(3);
		}
	}
	
	public void deathScreen() {
		Texture.gameOver.bind();
		Render.getInstance().drawPicture(0, 0, Fenetre.WidthFenetre, Fenetre.HeigthFenetre);
		Texture.gameOver.unbind();
	}
	
	public void victoryScreen() {
		Texture.win.bind();
		Render.getInstance().drawPicture(0, 0, Fenetre.WidthFenetre, Fenetre.HeigthFenetre);
		Texture.win.unbind();
	}
	
	private void playDeathEffect(int sound) {
		System.out.println(sound);
		switch(sound) {
			case 0: try {
				Jeu.music("/com/projetpo/bindingofisaac/module/libMusic/Isaac_dies_1.wav", false);
			} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
				e.printStackTrace();
			}
			break;
			case 1:try {
				Jeu.music("/com/projetpo/bindingofisaac/module/libMusic/Isaac_dies_2.wav", false);
			} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
				e.printStackTrace();
			}	
			break;
			case 2:try {
				Jeu.music("/com/projetpo/bindingofisaac/module/libMusic/Isaac_dies_3.wav", false);
			} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
				e.printStackTrace();
			}
			break;
		}
	}
	
	/**
	 * Dessine la miniMap
	 */
	public void drawMiniMap() {
		int coef = 2;
		Render.getInstance().drawSquare((float)(Fenetre.WidthFenetre- 58.5*coef), 465, (float)(58.5*coef - 5*coef),(float) (58.5*coef - 5.85*coef), new float[]{1f, 1f, 1f, 0.5f});
		for(int i=0; i<9; i++) {
			for(int j=0; j<9; j++) {
				if(etage[i][j] != null && etage[i][j].getcarte().isVisited()) {
					Render.getInstance().drawSquare((float)(Fenetre.WidthFenetre - 2*5.85*coef - 5.85*coef*i), (float)(465 + 5.85*coef*j), (float)5.85*coef, (float)5.85*coef, new float[]{1f, 1f, 1f, 0.5f});
				}
				if(etage[i][j] != null && etage[i][j].getcarte().isBossRoom()) {
					Render.getInstance().drawSquare((float)(Fenetre.WidthFenetre - 2*5.85*coef - 5.85*coef*i), (float)(465 + 5.85*coef*j), (float)5.85*coef, (float)5.85*coef, new float[]{1f, 0f, 1f, 0.5f});
				}
			}
		}
		Render.getInstance().drawSquare((float)(Fenetre.WidthFenetre - 2*5.85*coef -  5.85*coef*etageCoos.getX()), (float)(465 + 5.85*coef*etageCoos.getY()), (float)5.85*coef, (float)5.85*coef, new float[]{1f, 0f, 0f, 0.5f});
	} 

	/*
	 * Getters & Setters
	 */
	public Vector2 getEtageCoos() {
		return etageCoos;
	}

	public void setEtageCoos(Vector2 etageCoos) {
		this.etageCoos = etageCoos;
	}

	public Room [][] getEtage() {
		return etage;
	}

	public void setEtage(Room [][] etage) {
		this.etage = etage;
	}

	public Room getMapEnCours() {
		return mapEnCours;
	}

	public void setMapEnCours(Room room) {
		this.mapEnCours = room;
	}

	public Personnage getPlayer() {
		return player;
	}
	
	public void setPersonnage(Personnage player) {
		this.player = player;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}
	
}
