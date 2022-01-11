package com.projetpo.bindingofisaac.module.Model.Ennemis;

import java.util.LinkedList;

import com.projetpo.bindingofisaac.module.Controler.ListeBalle;
import com.projetpo.bindingofisaac.module.Model.Animation;
import com.projetpo.bindingofisaac.module.Model.Balle;
import com.projetpo.bindingofisaac.module.Model.Ennemi;
import com.projetpo.bindingofisaac.module.Model.Jeu;
import com.projetpo.bindingofisaac.module.Model.Personnage;
import com.projetpo.bindingofisaac.module.Shaders.Vector2;
import com.projetpo.bindingofisaac.module.Vue.Fenetre;

public class Pooter extends Ennemi{
	
	/*
	 * Coordonnées d'origine
	 */
	private Vector2 PositionOrigine;
	
	/*
	 * Liste de Balle du Pooter
	 */
	private ListeBalle munitions;
	
	/*
	 * Position aléatoire
	 */
	private Vector2 randomPosition;
	
	/*
	 * Constructeur
	 */
	public Pooter(int width, int heigth, Vector2 position, String url, double speed) {
		super(width, heigth, position, speed, url);
		this.PositionOrigine = position;
		this.munitions = new ListeBalle();
		this.randomPosition = position;
		this.setDegat(1);
		this.munitions.setDegats(2);
		this.setLife(8);
		this.munitions.setRange(4);
		this.munitions.setEnnemiBalle(true);
	}

	@Override
	public void drawEnnemi() {
		LinkedList<String> liste = new LinkedList<String>();
		liste.add(url);
		liste.add("src/main/resources/animation2Pooter.png");
		liste.add("src/main/resources/animation3Pooter.png");
		liste.add("src/main/resources/animation4Pooter.png");
		liste.add("src/main/resources/animation5Pooter.png");
		liste.add("src/main/resources/animation6Pooter.png");
		Animation anim = new Animation(this, 60, liste, position, new Vector2(this.getWidth(), this.getHeigth()));
		anim.animUrl();
	//	this.drawEntite();
		this.munitions.drawBalle();
	}

	public boolean isMur() {
		return (Jeu.gameWorld.getMapEnCours().getcarte().getCollisionMap()[(int)(position.getX()/65)][(int)(position.getY()/65)]);
	}
	
	/**
	 * @return IA de l'ennemi
	 */
	@Override
	public void IAEnnemi(Personnage p) {
		munitions.update();
		if(Fenetre.tick == 30) {
			if(Math.random() > 0.5) {
				randomPosition.setX(Math.random() - 0.5);
				randomPosition.setY(Math.random() - 0.5);
				this.url = "src/main/resources/animation6Pooter.png";
			} else {
				randomPosition = PositionOrigine;
				Vector2 v = new Vector2(p.getPosition().getX() - position.getX(), p.getPosition().getY() - position.getY());
				Vector2 v2 = new Vector2(v.getX()/v.euclidianNorm(), v.getY()/v.euclidianNorm());
				munitions.addBalle(new Balle(25, 25, position.getX(), position.getY(), v2, "src/main/resources/enemybullets.png", 3));
				this.url = "src/main/resources/animation5Pooter.png";
			}
		}
		if(position.getX() > 65 && position.getX() < Fenetre.WidthFenetre - 65-width && position.getY() > 65 && position.getY() < Fenetre.HeigthFenetre - 65-heigth && !isMur()) {
			setDirection(new Vector2(position.getX()*randomPosition.getX(), position.getY()*randomPosition.getY()));
			this.move();
		} else {
			setDirection(new Vector2(p.getPosition().getX() - position.getX(), p.getPosition().getY() - position.getY()));
		}
	} 

}
