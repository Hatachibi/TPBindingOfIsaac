package com.projetpo.bindingofisaac.module.Model.Ennemis;

import java.io.IOException;
import java.util.LinkedList;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.projetpo.bindingofisaac.module.Model.Bombe;
import com.projetpo.bindingofisaac.module.Model.Ennemi;
import com.projetpo.bindingofisaac.module.Model.Personnage;
import com.projetpo.bindingofisaac.module.Shaders.Vector2;
import com.projetpo.bindingofisaac.module.Vue.Fenetre;
import com.projetpo.bindingofisaac.module.Vue.Jeu;

public class Bomberman extends Ennemi {
	
	private LinkedList<Bombe> bombList;
	
	public Bomberman(int width, int heigth, Vector2 position, double speed, String url) {
		super(width, heigth, position, speed, url);
		this.bombList = new LinkedList<Bombe>();
		this.tick = 0;
		this.setLife(10);
		this.setDegat(1);
	}

	@Override
	public void drawEnnemi() {
		this.drawEntite();
		for(Bombe b: bombList) {
			b.draw();
		}
	}

	@Override
	public void IAEnnemi(Personnage p) {
		if(!bombList.isEmpty()) {
			LinkedList<Bombe> copieListe = (LinkedList<Bombe>) bombList.clone();
			for(Bombe b: bombList) {
				if(b.isDoRemove()) {
					copieListe.remove(b);
				}
				b.update();
			}
			bombList = copieListe;
		}
		if(tick == 0) {
			Bombe b = new Bombe(-3, width, heigth, new Vector2(65 + Math.random()*(Fenetre.WidthFenetre - 130 - width), 65 + Math.random()*(Fenetre.HeigthFenetre - 130 - heigth)), "src/main/resources/bomb.png");
			b.setBombEnnemi(true);
			b.setRange(1);
			b.setDegat(1);
			this.bombList.add(b);
		}
		tick ++;
		if(tick == 2*Fenetre.getInstance().getFPS()) {
			try {
				Jeu.music("/com/projetpo/bindingofisaac/module/libMusic/wololo.wav", false);
			} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
				e.printStackTrace();
			}
			tick = 0;
		}
	}

}
