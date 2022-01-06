package com.projetpo.bindingofisaac.module.Model;

import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

import com.projetpo.bindingofisaac.module.Shaders.Vector2;
import com.projetpo.bindingofisaac.module.Vue.Render;

public class Laser extends Entite{
	
	/*
	 * Degat du laser
	 */
	private int degats;
	
	/*
	 * 
	 */
	private boolean isEnnemiBalle;
	
	/*
	 * 
	 */
	private Vector2 posOrigin;
	
	public Laser(int width, int heigth, Vector2 position, String url) {
		super(width, heigth, position, url);
		this.posOrigin = position;
	}
	
	public void draw() {
	//	Render.getInstance().drawSquare((float)hitbox.getPosition().getX(), (float)hitbox.getPosition().getY(), (float)hitbox.getPositionX().getX(),(float) hitbox.getPositionX().getY(), (float)hitbox.getPositionXY().getX(),(float) hitbox.getPositionXY().getY(),(float) hitbox.getPositionY().getX(),(float) hitbox.getPositionY().getY());
		GL11.glColor4f(1, 0, 0, 1);
		this.drawRealEntite();
		GL11.glColor4f(1, 1, 1, 1);
	}
	
	public void update() {
		if(!isEnnemiBalle) { //Si la liste de balle appartient au joueur on fait des dégâts aux ennemis
			for(int i=0; i<Jeu.gameWorld.getMapEnCours().getListeEnnemi().getListe().size(); i++) {
				if(Jeu.gameWorld.getMapEnCours().getListeEnnemi().getListe().get(i).collisionBalle(this)) {
					Jeu.gameWorld.getMapEnCours().getListeEnnemi().getListe().get(i).setTouch(true);
					Jeu.gameWorld.getMapEnCours().getListeEnnemi().getListe().get(i).setLife(Jeu.gameWorld.getMapEnCours().getListeEnnemi().getListe().get(i).getLife()-Jeu.gameWorld.getMapEnCours().getPlayer().attaque());
				};
			}
		} else {  //Sinon on fait des dégâts au joueur
			for(int i=0; i<Jeu.gameWorld.getMapEnCours().getListeEnnemi().getListe().size(); i++) {
				if(Jeu.gameWorld.getMapEnCours().getPlayer().collisionBalle(this)) {
					Jeu.gameWorld.getMapEnCours().getPlayer().setTouch(true);
					Jeu.gameWorld.getMapEnCours().getPlayer().subitDegats(degats);
				};
			}
		}
	}
	
	/**
	 * @return Update la position de la balle 
	 */
	public void updateHitbox() {
		this.getHitbox().setPosition(position);
	}

	
	/*
	 * Getters & Setters
	 */
	public int getDegats() {
		return degats;
	}

	public void setDegats(int degats) {
		this.degats = degats;
	}

	public boolean isEnnemiBalle() {
		return isEnnemiBalle;
	}

	public void setEnnemiBalle(boolean isEnnemiBalle) {
		this.isEnnemiBalle = isEnnemiBalle;
	}

	public Vector2 getPosOrigin() {
		return posOrigin;
	}

	public void setPosOrigin(Vector2 posOrigin) {
		this.posOrigin = posOrigin;
	}
	
}
