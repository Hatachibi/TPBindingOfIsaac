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
	 * Couple (x, y) de coordonnées aléatoire pour le déplacement
	 */
	private Vector2 random;

	/*
	 * Constructeur
	 */
	public Spider(int width, int heigth, Vector2 position, String url, double speed) {
		super(width, heigth, position, speed, url);
		this.random = new Vector2(0, 0);
		this.setDegat(1);
		this.setLife(5);
	}
	
	/**
	 * @return Dessine l'ennemi
	 */
	@Override
	public void drawEnnemi() {
	/*	Texture entiteTexture = Texture.loadTexture(url);
		entiteTexture.bind();
		Render.getInstance().drawPicture((float)getPosition().getX(),(float) getPosition().getY(), entiteTexture.getWidth()*2, entiteTexture.getHeight()*2);
		entiteTexture.unbind(); */
		this.drawEntite();
	//	Render.getInstance().drawSquare((float)position.getX(),(float) position.getY(), width, heigth);
	}
	
	public boolean isMur() {
		return (Jeu.gameWorld.getMapEnCours().getcarte().getCollisionMap()[(int)((position.getX() + width/2)/65)][(int)((position.getY() + heigth/2)/65)]);
	}
	
	public static boolean isMurStatic(Boss b) {
		return (Jeu.gameWorld.getMapEnCours().getcarte().getCollisionMap()[(int)(b.getPosition().getX()/65)][(int)(b.getPosition().getY()/65)]);
	}

	/**
	 * @return IA de l'ennemi
	 */
	@Override
	public void IAEnnemi(Personnage p) {
		if(Fenetre.tick == Fenetre.getInstance().getFPS()/2) {
			random.setX(Math.random() - 0.5);
			random.setY(Math.random() - 0.5);
		}
		if(Fenetre.tick > Fenetre.getInstance().getFPS() - 10) {
			if(position.getX() < 65) {
				setDirection(new Vector2(1, this.random.getY()));
			} 
			else if(position.getX() > Fenetre.WidthFenetre - 65-width) {
				setDirection(new Vector2(-1, this.random.getY()));
			}
			else if(position.getY() < 65) {
				setDirection(new Vector2(this.random.getX(), 1));
			}
			else if(position.getY() > Fenetre.HeigthFenetre - 65-heigth) {
				setDirection(new Vector2(this.random.getX(), -1));
			}
			else {
				setDirection(random);
			}
			random = getDirection();
			this.move();
			
		}
	} 
	
	/**
	 * @param p un personnage 
	 * @param b une balle
	 * @Note Même fonction que l'IA sauf qu'elle est en static
	 */
	public static void IASpider(Personnage p, Boss b) {
		if(Fenetre.tick == Fenetre.getInstance().getFPS()/2) {
			b.getRandom().setX(Math.random() - 0.5);
			b.getRandom().setY(Math.random() - 0.5);
		}
		if(Fenetre.tick > Fenetre.getInstance().getFPS()/2) {
			if(b.getPosition().getX() < 65) {
				b.setDirection(new Vector2(1, b.getRandom().getY()));
			} 
			else if(b.getPosition().getX() > Fenetre.WidthFenetre - 65-b.getWidth()) {
				b.setDirection(new Vector2(-1, b.getRandom().getY()));
			}
			else if(b.getPosition().getY() < 65) {
				b.setDirection(new Vector2(b.getRandom().getX(), 1));
			}
			else if(b.getPosition().getY() > Fenetre.HeigthFenetre - 65-b.getHeigth()) {
				b.setDirection(new Vector2(b.getRandom().getX(), -1));
			}
			else {
				b.setDirection(b.getRandom());
			}
			b.setRandom(b.getDirection());
			b.move();
		}
	} 

}
