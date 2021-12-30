package com.projetpo.bindingofisaac.module.Model;

import com.projetpo.bindingofisaac.module.Shaders.Vector2;

public class GameWorld {
	
	private Room [][] etage;
	private Vector2 etageCoos;
	private Room mapEnCours;
	private Personnage player;
	
	public GameWorld(Personnage player) {
		this.etage = new Room[9][9];
		this.player = player;
		this.mapEnCours = new Room(player);
		this.setEtageCoos(new Vector2(4, 4));
		initRoom();
	}

	private void initRoom() {
		for(int i=0; i<etage.length; i++) {
			for(int j=0; j<etage[i].length; j++){
				etage[i][j] = new Room(Jeu.Isaac);
			}
		}
		etage[4][4] = Jeu.room;
	}
	
	public void updateWorld() {
		etage[(int) etageCoos.getX()][(int) etageCoos.getY()].updateRoom();
	}
	
	public void drawWorld() {
		etage[(int) etageCoos.getX()][(int) etageCoos.getY()].drawRoom();
	}

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
	
}
