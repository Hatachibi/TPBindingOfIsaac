package com.projetpo.bindingofisaac.module.Model;

public class MapObject {
	
	/*
	 * Boolean qui indique s'il il y a une collision
	 */
	private boolean collisionMap;
	
	/*
	 * Entier qui indique la nature du fichier image � afficher
	 */
	private int renderMap;
	
	private int overlayMap;
		
	/*
	 * Constructeur
	 */
	public MapObject() {
		this.collisionMap = false;
		this.renderMap = 0;
	}

	/*
	 * Getters & Setters
	 */
	public boolean getCollisionMap() {
		return collisionMap;
	}

	public void setCollisionMap(boolean collisionMap) {
		this.collisionMap = collisionMap;
	}

	public int getRenderMap() {
		return renderMap;
	}

	public void setRenderMap(int renderMap) {
		this.renderMap = renderMap;
	}

	public int getOverlayMap() {
		return overlayMap;
	}

	public void setOverlayMap(int overlayMap) {
		this.overlayMap = overlayMap;
	}
	
}
