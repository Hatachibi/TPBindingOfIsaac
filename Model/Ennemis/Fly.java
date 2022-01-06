package com.projetpo.bindingofisaac.module.Model.Ennemis;

import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.projetpo.bindingofisaac.module.Controler.ListeBalle;
import com.projetpo.bindingofisaac.module.Model.Balle;
import com.projetpo.bindingofisaac.module.Model.Ennemi;
import com.projetpo.bindingofisaac.module.Model.Jeu;
import com.projetpo.bindingofisaac.module.Model.ObjetsInventaire;
import com.projetpo.bindingofisaac.module.Model.Personnage;
import com.projetpo.bindingofisaac.module.Shaders.Vector2;
import com.projetpo.bindingofisaac.module.Vue.Fenetre;

public class Fly extends Ennemi{
	
	/*
	 * Liste de balle de l'ennemi
	 */
	private ListeBalle munitions;
	
	/*
	 * Constructeur
	 */
	public Fly(int width, int heigth, Vector2 position, String url, double speed) {
		super(width, heigth, position, speed, url);
		this.munitions = new ListeBalle();
		this.munitions.setEnnemiBalle(true);
		this.setDegat(1);
		this.setLife(3);
		this.munitions.setRange(3);
		this.munitions.setDegats(1);  // Degat des projectiles
	}
	
	/**
	 * @return Dessine l'ennemi
	 */
	@Override
	public void drawEnnemi() {
	/*	Texture.fly.bind();
		Render.getInstance().drawPicture((float)position.getX(), (float)position.getY(), 25, 25, 1, 1, new float[] {});
		Texture.fly.unbind(); */
		this.drawEntite();
	//	Render.getInstance().drawSquare((float)position.getX(),(float) position.getY(), width, heigth);
		munitions.drawBalle();
	}

	/**
	 * @return IA de l'ennemi
	 */
	@Override
	public void IAEnnemi(Personnage p) {
		munitions.update();
		if(Fenetre.tick == 0%60 && Math.random() > 0.8) {
			Vector2 v = new Vector2(p.getPosition().getX() - position.getX(), p.getPosition().getY() - position.getY());
			Vector2 v2 = new Vector2(v.getX()/v.euclidianNorm(), v.getY()/v.euclidianNorm());
			munitions.addBalle(new Balle(25, 25, position.getX(), position.getY(), v2, "src/main/resources/enemybullets.png", 3));
	/*		try {
				Jeu.music("/libMusic/boss_shoot.wav", false);
			} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
				e.printStackTrace();
			} */
		}
		
	/*	if(Fenetre.tick%15 > 0 && Fenetre.tick%15 < 7.5) {
			 url = "src/main/resources/flyRed.png";
		} else {
			 url = "src/main/resources/fly.png";
		} */
		if(Fenetre.tick%50 > 25 && Math.random() > 0.5) {
			 url = "src/main/resources/flyRed.png";
		} else {
			 url = "src/main/resources/fly.png";
		}
		setDirection(new Vector2(p.getPosition().getX() - position.getX(), p.getPosition().getY() - position.getY()));
		this.move();
	}
	
	/**
	 * @param p un personnage 
	 * @param b une balle
	 * @Note M�me fonction que l'IA suaf qu'elle est en static
	 */
	public static void IAFly(Personnage p, Boss b) {
		if(Fenetre.tick == 0%60) {
			Vector2 v = new Vector2(p.getPosition().getX() - b.getPosition().getX(), p.getPosition().getY() - b.getPosition().getY());
			Vector2 v2 = new Vector2(v.getX()/v.euclidianNorm(), v.getY()/v.euclidianNorm());
			b.getMunitions().addBalle(new Balle(25, 25, b.getPosition().getX(), b.getPosition().getY(), v2, "src/main/resources/enemybullets.png", 10));
		/*	try {
				Jeu.music("/libMusic/boss_shoot.wav", false);
			} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
				e.printStackTrace();
			} */
		}
		b.setDirection(new Vector2(p.getPosition().getX() - b.getPosition().getX(), p.getPosition().getY() - b.getPosition().getY()));
		b.move();
	}

}
