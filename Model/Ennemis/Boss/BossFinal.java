package com.projetpo.bindingofisaac.module.Model.Ennemis.Boss;

import java.util.LinkedList;

import com.projetpo.bindingofisaac.module.Controler.ListeBalle;
import com.projetpo.bindingofisaac.module.Model.Balle;
import com.projetpo.bindingofisaac.module.Model.Ennemi;
import com.projetpo.bindingofisaac.module.Model.Personnage;
import com.projetpo.bindingofisaac.module.Model.Ennemis.Fly;
import com.projetpo.bindingofisaac.module.Shaders.Vector2;
import com.projetpo.bindingofisaac.module.Vue.Fenetre;
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
		this.munitions.setRange(3);
		this.flyList = new LinkedList<Fly>();
		this.setLife(1);
		this.setDegat(2);
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
			Render.getInstance().drawTrait((float)position.getX(), (float)position.getY(), (float)f.getPosition().getX(), (float)f.getPosition().getY());
			f.drawEntite();
		}
		Texture.halo.bind();
		Render.getInstance().drawPicture((float)position.getX() - Texture.halo.getWidth()/4, (float)position.getY()  - Texture.halo.getHeight()/4, Texture.halo.getWidth()*2, Texture.halo.getHeight()*2);
		Texture.halo.unbind();
		this.drawEntite();
		this.munitions.drawBalle();
	}

	@Override
	public void IAEnnemi(Personnage p) {
		this.munitions.update();
		this.setTouch(true);
		LinkedList<Fly> copieListe = (LinkedList<Fly>) flyList.clone();
		for(Fly f: flyList) {
			if(Fenetre.tick%60 == 0) {
				this.munitions.addBalle(new Balle(20, 20, f.getPosition().getX(), f.getPosition().getY(), new Vector2(Math.random() - 0.5, Math.random() - 0.5), "src/main/resources/enemybullets.png", 11));
			}
			if(Fenetre.tick%50 > 25 && Math.random() > 0.5) {
				rayon = (int) (Math.random()*20)+100;
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
				copieListe.remove(f);
			}
			f.setA(f.getA()+0.1);
		}
		flyList = copieListe;
		if(flyList.isEmpty()) {
			this.setLife(0);
		}
		setDirection(new Vector2(p.getPosition().getX() - getPosition().getX(), p.getPosition().getY() - getPosition().getY()));
		this.move();
	}

}
