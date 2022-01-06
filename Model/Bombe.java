package com.projetpo.bindingofisaac.module.Model;

import com.projetpo.bindingofisaac.module.Shaders.Vector2;

public class Bombe extends ObjetsInventaire {

	/*
	 * Tick pour calculs
	 */
	private int tickCoolDown;
	
	
	public Bombe(int id, int width, int heigth, Vector2 position, String url) {
		super(id, width, heigth, position, url);
		this.tickCoolDown = 0;
	}
	
	public void draw() {
		
	}

}
