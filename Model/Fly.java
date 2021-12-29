package Model;

import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import Controler.ListeBalle;
import Shaders.Vector2;
import Vue.Fenetre;
import Vue.Render;
import Vue.Texture;

public class Fly extends Ennemi{
	
	/*
	 * Liste de balle de l'ennemi
	 */
	private ListeBalle munitions;
	
	/*
	 * Constructeur
	 */
	public Fly(int width, int heigth, Vector2 position, String url, double speed, int life) {
		super(width, heigth, position, speed, url, life);
		this.munitions = new ListeBalle();
		this.munitions.setEnnemiBalle(true);
		this.setDegat(2);
		this.munitions.setRange(3);
		this.munitions.setSpeed(8);
		this.munitions.setDegats(1);  // Degat des projectiles
	}
	
	/**
	 * @return Dessine l'ennemi
	 */
	@Override
	public void drawEnnemi() {
		Texture.fly.bind();
		Render.getInstance().drawPicture((float)position.getX(), (float)position.getY(), 25, 25, 1, 1, new float[] {});
		Texture.fly.unbind();
		munitions.drawBalle();
	}

	/**
	 * @return IA de l'ennemi
	 */
	@Override
	public void IAEnnemi(Personnage p) {
		if(Fenetre.tick == 0%60) {
			munitions.addBalle(new Balle(25, 25, position.getX(), position.getY(), 1, ""));
			munitions.addBalle(new Balle(25, 25, position.getX(), position.getY(), 2, ""));
			munitions.addBalle(new Balle(25, 25, position.getX(), position.getY(), 3, ""));
			munitions.addBalle(new Balle(25, 25, position.getX(), position.getY(), 4, ""));
			try {
				Jeu.music("/libMusic/boss_shoot.wav", false);
			} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
				e.printStackTrace();
			}
		} /*if(Fenetre.tick == 30%60) {
			munitions.addBalle(new Balle(25, 25, position.getX(), position.getY(), 5, ""));
			munitions.addBalle(new Balle(25, 25, position.getX(), position.getY(), 6, ""));
			munitions.addBalle(new Balle(25, 25, position.getX(), position.getY(), 7, ""));
			munitions.addBalle(new Balle(25, 25, position.getX(), position.getY(), 8, ""));
		} */
		setDirection(new Vector2(p.getPosition().getX() - position.getX(), p.getPosition().getY() - position.getY()));
		this.move();
	}
	
	/**
	 * @param p un personnage 
	 * @param b une balle
	 * @Note Même fonction que l'IA suaf qu'elle est en static
	 */
	public static void IAFly(Personnage p, Boss b) {
		if(Fenetre.tick == 0%60) {
			b.getMunitions().addBalle(new Balle(25, 25, b.getPosition().getX(), b.getPosition().getY(), 1, ""));
			b.getMunitions().addBalle(new Balle(25, 25, b.getPosition().getX(), b.getPosition().getY(), 2, ""));
			b.getMunitions().addBalle(new Balle(25, 25, b.getPosition().getX(), b.getPosition().getY(), 3, ""));
			b.getMunitions().addBalle(new Balle(25, 25, b.getPosition().getX(), b.getPosition().getY(), 4, ""));
			try {
				Jeu.music("/libMusic/boss_shoot.wav", false);
			} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
				e.printStackTrace();
			}
		}/* if(Fenetre.tick == 30%60) {
			b.getMunitions().addBalle(new Balle(25, 25, b.getPosition().getX(), b.getPosition().getY(), 5, ""));
			b.getMunitions().addBalle(new Balle(25, 25, b.getPosition().getX(), b.getPosition().getY(), 6, ""));
			b.getMunitions().addBalle(new Balle(25, 25, b.getPosition().getX(), b.getPosition().getY(), 7, ""));
			b.getMunitions().addBalle(new Balle(25, 25, b.getPosition().getX(), b.getPosition().getY(), 8, ""));
			try {
				Jeu.music("/libMusic/boss_shoot.wav", false);
			} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
				e.printStackTrace();
			}
		} */
		b.setDirection(new Vector2(p.getPosition().getX() - b.getPosition().getX(), p.getPosition().getY() - b.getPosition().getY()));
		b.move();
	}

}
