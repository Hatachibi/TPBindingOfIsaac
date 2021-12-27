package Model;

import Shaders.Vector2;

public class Hitbox {
	
	/* 2------4
	 * |      |
	 * |      |
	 * 1------3
	 */
	
	/*
	 * position 1 sur le schéma
	 */
	private Vector2 position;
	
	/*
	 * Largeur de la Hitbox
	 */
	private int width;
	
	/*
	 * Longueur de la Hitbox
	 */
	private int heigth;
	
	/*
	 * Boolean qui indique si il y a une collision en Haut
	 */
	private boolean isZCollision;
	
	/*
	 * Boolean qui indique si il y a une collision à Gauche
	 */
	private boolean isQCollision;
	
	/*
	 * Boolean qui indique si il y a une collision en Bas
	 */
	private boolean isSCollision;
	
	/*
	 * Boolean qui indique si il y a une collision à Droite
	 */
	private boolean isDCollision;
	
	/*
	 * Constructeur
	 */
    public Hitbox(Vector2 position, int width, int heigth) {
    	this.position = position;
    	this.width = width;
    	this.heigth = heigth;
    } 
    
    
    /**
     * @return Calcul les collisions avec le joueur pour les murs
     */
    public void collisionPlayer(Personnage p) {
    	Jeu.Isaac.updateHitbox();
    	if(this.collisionMur(p.getHitbox().getPositionX().getX() + p.getSpeed(), p.getHitbox().getPositionX().getY()) || this.collisionMur(p.getHitbox().getPositionXY().getX() + p.getSpeed(), p.getHitbox().getPositionXY().getY())) {
    		this.isDCollision = true;
    	} else {
    		this.isDCollision = false;
    	}
    	if(this.collisionMur(p.getHitbox().getPosition().getX() - p.getSpeed(), p.getHitbox().getPosition().getY()) || this.collisionMur(p.getHitbox().getPositionY().getX() - p.getSpeed(), p.getHitbox().getPositionY().getY())) {
    		this.isQCollision = true;
    	} else {
    		this.isQCollision = false;
    	}
    	if(this.collisionMur(p.getHitbox().getPositionY().getX(), p.getHitbox().getPositionY().getY() + p.getSpeed()) || this.collisionMur(p.getHitbox().getPositionXY().getX(), p.getHitbox().getPositionXY().getY() + p.getSpeed())) {
    		this.isZCollision = true;
    	}  else {
    		this.isZCollision = false;
    	}
    	if(this.collisionMur(p.getHitbox().getPosition().getX(), p.getHitbox().getPosition().getY() - p.getSpeed()) || this.collisionMur(p.getHitbox().getPositionX().getX(), p.getHitbox().getPositionX().getY() - p.getSpeed())) {
    		this.isSCollision = true;
    	}  else {
    		this.isSCollision = false;
    	}
    }
        
    /**
     * @param pos est la position 1, x la 3, y la 2 et xy la 4 (voir schéma ligne 10)
     * @return si 2 Hitbox sont en contact
     */
    public boolean collisionHitbox(Vector2 pos, Vector2 x, Vector2 y, Vector2 xy) {
    	return (this.between(pos) || this.between(x) || this.between(y) || this.between(xy));
    }
    
    /**
     * @param x la coordonnée en x du joueur
     * @param y la coordonnée en y du joueur
     * @return si le joueur est en collision avec un mur
     */
    public boolean collisionMur(double x, double y) {
    	int i = (int) x/65;
    	int j = (int) y/65;
    	if(i<9 && i>=0 && j<9 && j>=0)
    	{
    		return (Jeu.room.getMapEnCours().getCollisionMap()[i][j]);
    	}
    	return true;
    }
    
    /**
     * @return Calcul les collisions entre le Mur et une entite
     */
    public boolean collisionMurEntite(Entite e) {
    	if((int) e.getHitbox().getPosition().getX()/65 < 8 && (int) e.getHitbox().getPosition().getX()/65 >= 1 && (int) e.getHitbox().getPosition().getY()/65 >= 1 && (int) e.getHitbox().getPosition().getY()/65 < 8) {
    		return (Jeu.room.getMapEnCours().getCollisionMap()[(int) e.getHitbox().getPosition().getX()/65][(int) e.getHitbox().getPosition().getY()/65]
    	    		 || Jeu.room.getMapEnCours().getCollisionMap()[(int) e.getHitbox().getPositionX().getX()/65][(int) e.getHitbox().getPositionX().getY()/65]
    	    		 || Jeu.room.getMapEnCours().getCollisionMap()[(int) e.getHitbox().getPositionY().getX()/65][(int) e.getHitbox().getPositionY().getY()/65]		
    	    		 || Jeu.room.getMapEnCours().getCollisionMap()[(int) e.getHitbox().getPositionXY().getX()/65][(int) e.getHitbox().getPositionXY().getY()/65]	);
    	    }
    	return true;
    	}
    
    /**
     * @return si un Vecteur est compris dans la Hitbox
     */
    public boolean between(Vector2 pos) {
    	return ((pos.getX() > position.getX() && pos.getX() < getPositionX().getX()) && (pos.getY() < getPositionY().getY() && pos.getY() > position.getY()));
    }
    
    /**
	 * Calculates whether two rectangles are in collision or not. Two rectangles are
	 * in collision if they share any area.
	 * 
	 * @param pos1:  position of first rectangle
	 * @param size1: size of first rectangle
	 * @param pos2:  position of second rectangle
	 * @param size2: size of second rectangle
	 * @return true if rectangles are in collision else false
	 */
    public static  boolean rectangleCollision(Vector2 pos1, Vector2 size1, Vector2 pos2, Vector2 size2)
	{
		// We authorise a small overlap before considering a collision in order to avoid
		// collision between side-to-side objects and floating point approximation
		// errors.
		double authorizedOverlap = 65 / 1000;

		boolean tooFarLeft = pos1.getX() + (size1.getX() / 2) < authorizedOverlap + pos2.getX() - (size2.getX() / 2);
		boolean tooFarBelow = pos1.getY() + (size1.getY() / 2) < authorizedOverlap + pos2.getY() - (size2.getY() / 2);
		boolean tooFarRight = pos1.getX() - (size1.getX() / 2) + authorizedOverlap > pos2.getX() + (size2.getX() / 2);
		boolean tooFarAbove = pos1.getY() - (size1.getY() / 2) + authorizedOverlap > pos2.getY() + (size2.getY() / 2);

		if (tooFarLeft || tooFarRight || tooFarAbove || tooFarBelow)
		{
			return false;
		}
		return true;
	}
    
    /**
     * @return la posision du point 1 (voir schéma ligne 10)
     */
    public Vector2 getPosition() {
  		return position;
  	}
    
    /**
     * @return la posision du point 2 (voir schéma ligne 10)
     */
    public Vector2 getPositionY() {
    	return new Vector2(position.getX(), position.getY() + heigth);
    }
    
    /**
     * @return la posision du point 3 (voir schéma ligne 10)
     */
    public Vector2 getPositionX() {
    	return new Vector2(position.getX() + width, position.getY());
    }
    
    /**
     * @return la posision du point 4 (voir schéma ligne 10)
     */
    public Vector2 getPositionXY() {
    	return new Vector2(position.getX() + width, position.getY() + heigth);
    }

    /*
     * Getters & Setters
     */
	public boolean isZCollision() {
		return isZCollision;
	}

	public void setZCollision(boolean isZCollision) {
		this.isZCollision = isZCollision;
	}

	public boolean isQCollision() {
		return isQCollision;
	}

	public void setQCollision(boolean isQCollision) {
		this.isQCollision = isQCollision;
	}

	public boolean isSCollision() {
		return isSCollision;
	}

	public void setSCollision(boolean isSCollision) {
		this.isSCollision = isSCollision;
	}

	public boolean isDCollision() {
		return isDCollision;
	}

	public void setDCollision(boolean isDCollision) {
		this.isDCollision = isDCollision;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeigth() {
		return heigth;
	}

	public void setHeigth(int heigth) {
		this.heigth = heigth;
	}
	
}