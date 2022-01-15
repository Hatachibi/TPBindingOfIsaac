package com.projetpo.bindingofisaac.module.Model.Ennemis;

import com.projetpo.bindingofisaac.module.Model.Ennemi;
import com.projetpo.bindingofisaac.module.Model.Hitbox;
import com.projetpo.bindingofisaac.module.Model.Jeu;
import com.projetpo.bindingofisaac.module.Model.ObjetsInventaire;
import com.projetpo.bindingofisaac.module.Model.Personnage;
import com.projetpo.bindingofisaac.module.Model.Ennemis.Boss.Boss;
import com.projetpo.bindingofisaac.module.Shaders.Vector2;
import com.projetpo.bindingofisaac.module.Vue.Fenetre;
import com.projetpo.bindingofisaac.module.Vue.Render;
import com.projetpo.bindingofisaac.module.Vue.Texture;

public class Spider extends Ennemi{
	
	/*
	 * Constructeur
	 */
	public Spider(int width, int heigth, Vector2 position, String url, double speed) {
		super(width, heigth, position, speed, url);
		this.setDegat(1);
		this.setLife(5);
	}
	
	/**
	 * @return Dessine l'ennemi
	 */
	@Override
	public void drawEnnemi() {
		this.drawEntite();
	}
	
	/**
	 * @return IA de l'ennemi
	 */
	@Override
	public void IAEnnemi(Personnage p) {
		this.goToRandom(Fenetre.getInstance().getFPS()/2, Fenetre.getInstance().getFPS() - 10);
	} 

}
