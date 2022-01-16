package com.projetpo.bindingofisaac.module.Model.Ennemis.Boss;

import java.io.IOException;
import java.util.LinkedList;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.projetpo.bindingofisaac.module.Controler.ListeBalle;
import com.projetpo.bindingofisaac.module.Model.Balle;
import com.projetpo.bindingofisaac.module.Model.Ennemi;
import com.projetpo.bindingofisaac.module.Model.Personnage;
import com.projetpo.bindingofisaac.module.Model.Ennemis.Fly;
import com.projetpo.bindingofisaac.module.Shaders.Vector2;
import com.projetpo.bindingofisaac.module.Vue.Fenetre;
import com.projetpo.bindingofisaac.module.Vue.Jeu;
import com.projetpo.bindingofisaac.module.Vue.Render;
import com.projetpo.bindingofisaac.module.Vue.Texture;

public class BossFinal extends Ennemi {

private LinkedList<Fly> flyList;
	
	private ListeBalle munitions;

	private int rayon = 100;

	public BossFinal(int width, int heigth, Vector2 position, double speed, String url) {
		super(width, heigth, position, speed, url);
		this.tick = 0;
		this.munitions = new ListeBalle();
		this.munitions.setDegats(1);
		this.munitions.setEnnemiBalle(true);
		this.munitions.setRange(20);
		this.flyList = new LinkedList<Fly>();
		this.setLife(200);
		this.setDegat(3);
		for(int i=0; i<7; i++) {
			Fly f = new Fly(25, 25, new Vector2(position.getX(), position.getY()), "src/main/resources/bossFinalHands.png", speed);
			f.setLife(4);
			f.setDegat(1);
			flyList.add(f);
		}
	}

	@Override
	public void drawEnnemi() {
		for(Fly f: flyList) {
			f.drawEntite();
		}
		Texture.halo.bind();
		Render.getInstance().drawPicture((float)position.getX() - width, (float)position.getY()  - heigth, Texture.halo.getWidth()*2, Texture.halo.getHeight()*2);
		Texture.halo.unbind();
		Texture.bossFinal.bind();
		Render.getInstance().drawPicture((float)position.getX(), (float)position.getY()  , Texture.bossFinal.getWidth()*2, Texture.bossFinal.getHeight()*2);
		Texture.bossFinal.unbind();
		this.munitions.drawBalle();
		Texture.bdvBoss.bind();
		Render.getInstance().drawPicture(300, 100, Texture.bdvBoss.getWidth()*2, Texture.bdvBoss.getHeight()*2);
		Texture.bdvBoss.unbind();
		Render.getInstance().drawSquare(340, 105, (float) (this.getLife()*215/200), (float) ((float)Texture.bdvBoss.getHeight()), new float[] {1f, 0f, 0f, 1f});
	
	}

	@Override
	public void IAEnnemi(Personnage p) {
		this.munitions.update();
		LinkedList<Fly> copieListe = (LinkedList<Fly>) flyList.clone();
		if(Fenetre.tick%Fenetre.getInstance().getFPS() == 0 && !flyList.isEmpty()) {
			if(Math.random() > 0.5) {
				this.munitions.addBalle(new Balle(20, 20, getPosition().getX(), getPosition().getY(), new Vector2(1, 0), "src/main/resources/enemybullets.png", 11));
				this.munitions.addBalle(new Balle(20, 20, getPosition().getX(), getPosition().getY(), new Vector2(-1, 0), "src/main/resources/enemybullets.png", 11));
				this.munitions.addBalle(new Balle(20, 20, getPosition().getX(), getPosition().getY(), new Vector2(0, 1), "src/main/resources/enemybullets.png", 11));
				this.munitions.addBalle(new Balle(20, 20, getPosition().getX(), getPosition().getY(), new Vector2(0, -1), "src/main/resources/enemybullets.png", 11));
			} else {
				this.munitions.addBalle(new Balle(20, 20, getPosition().getX(), getPosition().getY(), new Vector2(0.5, 0.5), "src/main/resources/enemybullets.png", 11));
				this.munitions.addBalle(new Balle(20, 20, getPosition().getX(), getPosition().getY(), new Vector2(-0.5, 0.5), "src/main/resources/enemybullets.png", 11));
				this.munitions.addBalle(new Balle(20, 20, getPosition().getX(), getPosition().getY(), new Vector2(-0.5, -0.5), "src/main/resources/enemybullets.png", 11));
				this.munitions.addBalle(new Balle(20, 20, getPosition().getX(), getPosition().getY(), new Vector2(0.5, -0.5), "src/main/resources/enemybullets.png", 11));
			}
			
		}
		for(Fly f: flyList) {
			if(Fenetre.tick%50 > 25 && Math.random() > 0.5) {
				rayon = (int) (Math.random()*20)+200;
				f.setUrl("src/main/resources/bossFinalHands2.png");
			} else {
				f.setUrl("src/main/resources/bossFinalHands.png");
			}
			f.setPosition(position.addVector(new Vector2(Math.cos(f.getA()  + copieListe.indexOf(f)*2*3.14/copieListe.size())*f.getSpeed()*rayon, Math.sin(f.getA()  + copieListe.indexOf(f)*2*3.14/copieListe.size())*f.getSpeed()*
					rayon)));
			for(Balle b: p.getMunitions().getListe()) {
				if(f.collisionBalle(b)) {
					f.setLife(f.getLife() - p.getDegat());
					f.setTouch(true);
					p.getMunitions().getListe().remove(b);
				}
			}
			if(f.collisionEnnemi(p) && !p.isTouch()) {
				p.subitDegats(f.getDegat());
				p.setTouch(true);
			}
			f.boucleCooldownEnnemi();
			if(f.getLife() < 0) {
				if(flyList.size() == 1) {
					try {
						Jeu.music("/com/projetpo/bindingofisaac/module/libMusic/bossFinalRun.wav", false);
					} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
						e.printStackTrace();
					}
				}
				copieListe.remove(f);
			}
			f.setA(f.getA()+0.1);
		}
		flyList = copieListe;
		if(flyList.isEmpty()) {
			if(Fenetre.tick%Fenetre.getInstance().getFPS() == 0) {
				this.munitions.addBalle(new Balle(20, 20, getPosition().getX(), getPosition().getY(), new Vector2(1, 0), "src/main/resources/enemybullets.png", 20));
				this.munitions.addBalle(new Balle(20, 20, getPosition().getX(), getPosition().getY(), new Vector2(-1, 0), "src/main/resources/enemybullets.png",20));
				this.munitions.addBalle(new Balle(20, 20, getPosition().getX(), getPosition().getY(), new Vector2(0, 1), "src/main/resources/enemybullets.png", 20));
				this.munitions.addBalle(new Balle(20, 20, getPosition().getX(), getPosition().getY(), new Vector2(0, -1), "src/main/resources/enemybullets.png", 20));
				this.munitions.addBalle(new Balle(20, 20, getPosition().getX(), getPosition().getY(), new Vector2(0.5, 0.5), "src/main/resources/enemybullets.png", 20));
				this.munitions.addBalle(new Balle(20, 20, getPosition().getX(), getPosition().getY(), new Vector2(-0.5, 0.5), "src/main/resources/enemybullets.png", 20));
				this.munitions.addBalle(new Balle(20, 20, getPosition().getX(), getPosition().getY(), new Vector2(-0.5, -0.5), "src/main/resources/enemybullets.png", 20));
				this.munitions.addBalle(new Balle(20, 20, getPosition().getX(), getPosition().getY(), new Vector2(0.5, -0.5), "src/main/resources/enemybullets.png", 20));
			}
			this.setSpeed(2);
		}
		setDirection(new Vector2(p.getPosition().getX() - getPosition().getX(), p.getPosition().getY() - getPosition().getY()));
		this.move();
	}

}
