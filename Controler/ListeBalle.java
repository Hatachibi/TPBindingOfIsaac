package Controler;

import java.util.LinkedList;
import Model.Balle;
import Model.Jeu;

public class ListeBalle {
	
	/*
	 * Liste Chain�e de Balle
	 */
	private LinkedList<Balle> liste;
	
	/*
	 * Boolean qui permet de savoir si une balle � �t� tir�e (sert pour le Cooldown du joueur)
	 */
	private boolean isNotShot;
	
	/*
	 * Cooldown qui �vite de spammer les balles
	 */
	private int coolDown;
	
	/*
	 * Boolean qui sert � savoir s'il s'agit de balle ennemi
	 */
	private boolean isEnnemiBalle;
	
	/*
	 * Constructeur
	 */
	public ListeBalle() {
		this.liste = new LinkedList<Balle>();
		this.isNotShot = true;
		this.setCoolDown(0);
		this.setEnnemiBalle(false);
	}
	
	/**
	 * @return Dessine l'ensemble des balles de la liste
	 */
	public void drawBalle() {
		LinkedList<Balle> copieListe = (LinkedList<Balle>) liste.clone();  //On fait une copie de la liste pour �viter d'enlever ou d'ajouter une balle pendant durant l'execution 
		for(Balle b: liste) {
			if(b.getDirection() == 1)
			{
				b.drawBalle();
				b.getPosition().setX((float) (b.getPosition().getX() - b.getSpeed()));
			}
			if(b.getDirection() == 2)
			{
				b.drawBalle();
				b.getPosition().setX((float) (b.getPosition().getX() + b.getSpeed()));
			}
			if(b.getDirection() == 3)
			{
				b.drawBalle();
				b.getPosition().setY((float) (b.getPosition().getY() + b.getSpeed()));
			}
			if(b.getDirection() == 4)
			{
				b.drawBalle();
				b.getPosition().setY((float) (b.getPosition().getY() - b.getSpeed()));
			}
			if(b.getDirection() == 5)
			{
				b.drawBalle();
				b.getPosition().setX((float) (b.getPosition().getX() + b.getSpeed()));
				b.getPosition().setY((float) (b.getPosition().getY() + b.getSpeed()));
			}
			if(b.getDirection() == 6)
			{
				b.drawBalle();
				b.getPosition().setX((float) (b.getPosition().getX() - b.getSpeed()));
				b.getPosition().setY((float) (b.getPosition().getY() - b.getSpeed()));
			}
			if(b.getDirection() == 7)
			{
				b.drawBalle();
				b.getPosition().setX((float) (b.getPosition().getX() - b.getSpeed()));
				b.getPosition().setY((float) (b.getPosition().getY() + b.getSpeed()));
			}
			if(b.getDirection() == 8)
			{
				b.drawBalle();
				b.getPosition().setX((float) (b.getPosition().getX() + b.getSpeed()));
				b.getPosition().setY((float) (b.getPosition().getY() - b.getSpeed()));
			}
			b.updateHitbox();
			if(doRemove(b)) {
				copieListe.remove(b);
			}
			if(!isEnnemiBalle) { //Si la liste de balle appartient au joueur on fait des d�g�ts aux ennemis
				for(int i=0; i<Jeu.room.getListeEnnemi().getListe().size(); i++) {
					if(Jeu.room.getListeEnnemi().getListe().get(i).collisionBalle(b)) {
						copieListe.remove(b);
						Jeu.room.getListeEnnemi().getListe().get(i).setTouch(true);
						Jeu.room.getListeEnnemi().getListe().get(i).setLife(Jeu.room.getListeEnnemi().getListe().get(i).getLife()-Jeu.room.getPlayer().attaque());
					};
				}
			} else {  //Sinon on fait des d�g�ts au joueur
				for(int i=0; i<Jeu.room.getListeEnnemi().getListe().size(); i++) {
					if(Jeu.room.getPlayer().collisionBalle(b)) {
						copieListe.remove(b);
						Jeu.room.getPlayer().setTouch(true);
						Jeu.room.getPlayer().subitDegats(Jeu.room.getListeEnnemi().getListe().get(i).getDegat());
					};
				}
			}
		}
		liste = copieListe;
	}
	
	/**
	 * @param b une balle
	 * Utilise la varaible isNotShot si il s'agit du joueur pour cr�er un cooldown
	 * @return Ajoute une balle � la liste
	 */
	public void addBalle(Balle b) {
		if(this.isNotShot || isEnnemiBalle) {
			this.liste.add(b);
		}
		this.isNotShot = false;
	}
	
	/**
	 * @param b une balle
	 * @return renvoie si on doit enlever une balle de la liste � cause d'une collision
	 */
	public boolean doRemove(Balle b) {
		return b.getHitbox().collisionMurEntite(b);
	}

	/*
	 * Getters & Setters
	 */
	public LinkedList<Balle> getListe() {
		return liste;
	}

	public boolean isNotShot() {
		return isNotShot;
	}

	public void setShot(boolean isNotShot) {
		this.isNotShot = isNotShot;
	}

	public int getCoolDown() {
		return coolDown;
	}

	public void setCoolDown(int coolDown) {
		this.coolDown = coolDown;
	}

	public boolean isEnnemiBalle() {
		return isEnnemiBalle;
	}

	public void setEnnemiBalle(boolean isEnnemiBalle) {
		this.isEnnemiBalle = isEnnemiBalle;
	}

}