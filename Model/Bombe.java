package com.projetpo.bindingofisaac.module.Model;

import com.projetpo.bindingofisaac.module.Shaders.Vector2;
import com.projetpo.bindingofisaac.module.Vue.Fenetre;
import com.projetpo.bindingofisaac.module.Vue.Jeu;

public class Bombe extends ObjetsInventaire {

	/*
	 * Tick pour calculs
	 */
	private int tickCoolDown;
	
	/*
	 * Degat de l'explosion
	 */
	private int degat;
	
	/*
	 * Range de la Bombe
	 */
	private int range;
	
	/*
	 * Boolean qui indique si la bombe a explosé ou non
	 */
	private boolean doRemove;
	
	/*
	 * Boolean qui indique si la bombe vient d'être posé
	 */
	private boolean isPoseRecently;
	
	/*
	 * Boolean qui indique s'il d'agit d'une bombe ennemi
	 */
	private boolean isBombEnnemi;
		
	
	public Bombe(int id, int width, int heigth, Vector2 position, String url) {
		super(id, width, heigth, position, url);
		this.tickCoolDown = 0;
		this.degat = 4;
		this.range = 2;
		this.setDoRemove(false);
		this.isPoseRecently = true;
	}
	
	public void draw() {
		this.drawEntite();
	}
	
	public void update() {
		if(tickCoolDown == Fenetre.getInstance().getFPS()*3) {
			explosion();
			setDoRemove(true);
		} else {
			tickCoolDown ++;
		}
		if(tickCoolDown == Fenetre.getInstance().getFPS()) {
			isPoseRecently = false;
		}
		if(tickCoolDown%Fenetre.getInstance().getFPS()/2 < Fenetre.getInstance().getFPS()/4) {
			this.url = "src/main/resources/BombRed.png";
		} else {
			this.url = "src/main/resources/Bomb.png";
		}
	}
	
	public void explosion() {
		int x = (int) position.getX()/65;
		int y = (int) position.getY()/65;
		width = width + (range*65);
		heigth = heigth + (range*65);
		for(int i=x-range; i<x+range; i++) {
			for(int j=y-range; j<y+range; j++) {
				if(i>=0 && i<9 && j>=0 && j<9) {
					if(Jeu.gameWorld.getMapEnCours().getcarte().getRenderMap()[i][j] == 9) {
						Jeu.gameWorld.getMapEnCours().getcarte().setRenderMap(i, j, 0);
						Jeu.gameWorld.getMapEnCours().getcarte().generateCollisionMap();
						int random = (int) Math.random()*6;
						switch(random) {
							case 0: Jeu.gameWorld.getMapEnCours().getcarte().getObjet().add(new ObjetsInventaire(10, 30, 30, position, "")); break;
							case 1: Jeu.gameWorld.getMapEnCours().getcarte().getObjet().add(new ObjetsInventaire(11, 30, 30, position, "")); break;
							case 2: Jeu.gameWorld.getMapEnCours().getcarte().getObjet().add(new ObjetsInventaire(12, 30, 30, position, "")); break;
							case 3: Jeu.gameWorld.getMapEnCours().getcarte().getObjet().add(new ObjetsInventaire(-3, 30, 30, position, "")); break;
						}
					}
				}
			}
		}
		if(this.collisionJoueur(Jeu.gameWorld.getPlayer())) {
			Jeu.gameWorld.getPlayer().subitDegats(degat);
		}
		if(!isBombEnnemi) {
			for(Ennemi e: Jeu.gameWorld.getMapEnCours().getListeEnnemi().getListe()) {
				if(this.collisionEnnemi(e)) {
					e.setLife(e.getLife() - degat);
				}
			}
		}
	}

	/*
	 * Getters & Setters
	 */
	public boolean isDoRemove() {
		return doRemove;
	}

	public int getTickCoolDown() {
		return tickCoolDown;
	}

	public void setTickCoolDown(int tickCoolDown) {
		this.tickCoolDown = tickCoolDown;
	}

	public int getDegat() {
		return degat;
	}

	public void setDegat(int degat) {
		this.degat = degat;
	}

	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}

	public boolean isPoseRecently() {
		return isPoseRecently;
	}

	public void setPoseRecently(boolean isPoseRecently) {
		this.isPoseRecently = isPoseRecently;
	}

	public void setDoRemove(boolean doRemove) {
		this.doRemove = doRemove;
	}

	public boolean isBombEnnemi() {
		return isBombEnnemi;
	}

	public void setBombEnnemi(boolean isBombEnnemi) {
		this.isBombEnnemi = isBombEnnemi;
	}

}
