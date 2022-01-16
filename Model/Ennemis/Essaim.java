package com.projetpo.bindingofisaac.module.Model.Ennemis;

import java.util.LinkedList;
import com.projetpo.bindingofisaac.module.Model.Balle;
import com.projetpo.bindingofisaac.module.Model.Ennemi;
import com.projetpo.bindingofisaac.module.Model.Personnage;
import com.projetpo.bindingofisaac.module.Shaders.Vector2;
import com.projetpo.bindingofisaac.module.Vue.Fenetre;

public class Essaim extends Ennemi {
	
	private LinkedList<Fly> flyList;
	
	private int rayon = 100;

	public Essaim(int width, int heigth, Vector2 position, double speed, String url) {
		super(width, heigth, position, speed, url);
		this.tick = 0;
		this.flyList = new LinkedList<Fly>();
		this.setLife(1);
		this.setDegat(2);
		for(int i=0; i<7; i++) {
			Fly f = new Fly(25, 25, new Vector2(position.getX(), position.getY()), url, speed);
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
	}

	@Override
	public void IAEnnemi(Personnage p) {
		this.setTouch(true);
		LinkedList<Fly> copieListe = (LinkedList<Fly>) flyList.clone();
		for(Fly f: flyList) {
			if(Fenetre.tick%50 > 25 && Math.random() > 0.5) {
				rayon = (int) (Math.random()*100);
				f.setUrl("src/main/resources/flyCircle2.png");
			} else {
				f.setUrl("src/main/resources/flyCircle.png");
			}
			f.setPosition(position.addVector(new Vector2(Math.cos(f.getA()  + copieListe.indexOf(f)*2*3.14/copieListe.size())*f.getSpeed()*rayon, Math.sin(f.getA()  + copieListe.indexOf(f)*2*3.14/copieListe.size())*f.getSpeed()*
					rayon)));
			LinkedList<Balle> copieListeBalle = p.getMunitions().getListe();
			for(Balle b: copieListeBalle) {
				if(f.collisionBalle(b)) {
					f.setLife(f.getLife() - p.getDegat());
					f.setTouch(true);
					p.getMunitions().getListe().remove(b);
				}
			}
			p.getMunitions().setListe(copieListeBalle);
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
