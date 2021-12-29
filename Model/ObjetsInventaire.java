package Model;

import Shaders.Vector2;

public class ObjetsInventaire extends Entite{
	
	/**
     * 
     */
    private String nom;

    /**
     * PASSIF
     * 
     * 1 - Semi-Heart
     * 2 - Heart
     * 3 - Blood Of The Martyr
     * 4 - Jesus juice
     * 5 - Lunch
     * 6 - Cricket's Head
     * 7 - Magic Mushroom
     * 8 - Pentagram
     * 9 - Stigmata
     * 10 - Penny    // Coin
     * 11 - Nickel   // Coin
     * 12 - Dime     // Coin
     * 
     * ACTIF
     * 
     * -1 - Mr Boom
     * -2 - Sonar
     */
    protected Integer id;
    
    /*
     * Boolean qui indique si l'objet est rammassé
     */
    private boolean isTouch;
	
	/*
	 * Constructeur
	 */
    public ObjetsInventaire(int id, int width, int heigth, Vector2 position, String url) {
		super(width, heigth, position, url);
		this.id = id;
		switch(id) {
			case 1: this.url = "libImg/Half_Red_Heart.png"; break;
			case 2: this.url = "libImg/hp_up.png"; break;
			case 3: this.url = "libImg/Blood_of_the_Martyr.png"; break;
			case 4: this.url = "libImg/Jesus_Juice.png"; break;
			case 5: this.url = "libImg/Lunch.png"; break;
			case 6: this.url = "libImg/Cricket's_Head.png"; break;
			case 7: this.url = "libImg/Magic_Mushroom.png"; break;
			case 8: this.url = "libImg/Pentagram.png"; break;
			case 9: this.url = "libImg/Stigmata.png"; break;
			case 10: this.url = "libImg/Penny.png"; break;
			case 11: this.url = "libImg/Nickel.png"; break;
			case 12: this.url = "libImg/Dime.png"; break;
			case -1: this.url = "libImg/Mr_Boom.png"; break;
		}
	}
    
    public void updateTest() {
    	if(this.collisionJoueur(Jeu.room.getPlayer())) {
    		BarreDeVie bdv = Jeu.room.getPlayer().getLife();
    		Personnage joueur = Jeu.room.getPlayer(); 
    		switch(id){
    			case 1:
    				if(bdv.getVieEnCours() < bdv.getViePleine()) {
    					bdv.setVieEnCours(bdv.getVieEnCours() + 1);
    				}
    			break;
    			case 2:
    				bdv.setViePleine(bdv.getViePleine() + 2);
    				bdv.setVieEnCours(bdv.getViePleine());
    			break;
    			case 3:
    				joueur.setDegat(joueur.getDegat() + 1);
    			break;
    			case 4:
    				joueur.setDegat(joueur.getDegat() + 0.5);
    				joueur.getMunitions().setRange(joueur.getRange() + 0.5);
    			break;
    			case 5:
    				bdv.setViePleine(bdv.getViePleine() + 1);
    			break;
    			case 6:
    				joueur.setDegat(joueur.getDegat() + 0.5);
    				joueur.setMultiplicator(1.5);
    			break;
    			case 7:
    				bdv.setVieEnCours(bdv.getViePleine());
    				joueur.setDegat(joueur.getDegat() + 1);
    				joueur.getMunitions().setRange(joueur.getRange() + 1);
    				joueur.setSpeed(joueur.getSpeed() + 1);
    				joueur.setMultiplicator(1.5);
    			break;
    			case 8:
    				joueur.setDegat(joueur.getDegat() + 1);
    			break;
    			case 9:
    				joueur.setDegat(joueur.getDegat() + 0.3);
    				bdv.setViePleine(bdv.getViePleine() + 2);
    			break;
    			case 10:
    				joueur.setCoin(joueur.getCoin() + 1);
    			break;
    			case 11:
    				joueur.setCoin(joueur.getCoin() + 5);
    			break;
    			case 12:
    				joueur.setCoin(joueur.getCoin() + 10);
    			break;
    				
    		}
    		this.setTouch(true);
    	}
    }
	
//	public void update();
	
    public boolean collisionJoueur(Personnage p) {
		return Hitbox.rectangleCollision(position, new Vector2(width, heigth), p.getPosition(), new Vector2(p.getWidth(), p.getHeigth()));
	}

	/*
	 * Getters & Setters 
	 */
	public boolean isTouch() {
		return isTouch;
	}

	public void setTouch(boolean isTouch) {
		this.isTouch = isTouch;
	}
    
}