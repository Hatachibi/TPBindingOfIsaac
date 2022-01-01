package com.projetpo.bindingofisaac.module.Model;

import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.projetpo.bindingofisaac.module.Ressource.MapPath;
import com.projetpo.bindingofisaac.module.Shaders.Vector2;
import com.projetpo.bindingofisaac.module.Vue.Fenetre;
import com.projetpo.bindingofisaac.module.Vue.Carte;
import com.projetpo.bindingofisaac.module.Vue.Render;
import com.projetpo.bindingofisaac.module.Vue.Texture;

public class GameWorld {
	
	private Room [][] etage;
	private Vector2 etageCoos;
	private Room mapEnCours;
	private Personnage player;
	
	public GameWorld(Personnage player) {
		this.etage = new Room[9][9];
		this.player = player;
		this.setEtageCoos(new Vector2(4, 4));
		initRoom();
	}

	private void initRoom() {
		for(int i=0; i<etage.length; i++) {
			for(int j=0; j<etage[i].length; j++){
				Carte m = new Carte();
				m.generateMap(j != etage.length - 1, j != 0, i != 0, i != etage.length - 1);
				m.generateRandomObstacle((int) (Math.random()*3));
				m.getMapobject()[(int)(2+Math.random()*6)][(int)(2+Math.random()*6)].setEnnemiMap((int)(1+Math.random()*5));
				m.generateCollisionMap();
				etage[i][j] = new Room(Jeu.Isaac, m);
			}
		}
		etage[4][5] = new Room(player, MapPath.mapShop());
		etage[4][4] = new Room(player, MapPath.mapStart());
		etage[4][1] = new Room(player, MapPath.bossMap());
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
		/*	if(first){
				playDeathEffect((int)(Math.random()*3));
			}
			first = false; */
			Texture.gameOver.bind();
			Render.getInstance().drawPicture(0, 0, 585, 585, 1, 1, new float[]{});
			Texture.gameOver.unbind();
		}
	}
	
	private void playDeathEffect(int sound) {
		System.out.println(sound);
		switch(sound) {
			case 0: try {
				Jeu.music("/libMusic/Isaac_dies_1.wav", false);
			} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
				e.printStackTrace();
			}
			break;
			case 1:try {
				Jeu.music("/libMusic/Isaac_dies_2.wav", false);
			} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
				e.printStackTrace();
			}	
			break;
			case 2:try {
				Jeu.music("/libMusic/Isaac_dies_3.wav", false);
			} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
				e.printStackTrace();
			}
			break;
		}
	}
	
	/**
	 * @return Dessine la miniMap
	 */
	public void drawMiniMap() {
		int coef = 2;
		Render.getInstance().drawSquare((float)(Fenetre.WidthFenetre- 58.5*coef), 465, (float)(58.5*coef - 5*coef),(float) (58.5*coef - 5.85*coef), new float[]{1f, 1f, 1f, 0.5f});
		for(int i=0; i<9; i++) {
			for(int j=0; j<9; j++) {
				if(etage[i][j].getcarte().isVisited()) {
					Render.getInstance().drawSquare((float)(Fenetre.WidthFenetre - 2*5.85*coef - 5.85*coef*i), (float)(465 + 5.85*coef*j), (float)5.85*coef, (float)5.85*coef, new float[]{1f, 1f, 1f, 0.5f});
				}
			}
		}
		Render.getInstance().drawSquare((float)(Fenetre.WidthFenetre - 2*5.85*coef -  5.85*coef*etageCoos.getX()), (float)(465 + 5.85*coef*etageCoos.getY()), (float)5.85*coef, (float)5.85*coef, new float[]{1f, 0f, 0f, 0.5f});
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
