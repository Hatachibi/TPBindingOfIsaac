package Model;

import Controler.ListeBalle;
import Shaders.Raycasting;
import Shaders.Vector2;
import Vue.Render;
import Vue.Texture;

public class Personnage extends Entite{

	/*
	 * Liste de balle du joueur
	 */
	private ListeBalle munitions;
	
	/*
	 * Degat inflig� par le joueur.
	 */
	private int degat;
	
	/*
	 * Multiplicateur de degat
	 */
	private double multiplicator;
	
	/*
	 * Port�e des balles du joueur
	 */
	private int range;
	
	/*
	 * Vitesse du joueur
	 */
	private double speed;
	
	/*
	 * Taille du joueur
	 */
	private Vector2 size;
	
	/*
	 * Direction de deplacement du joueur
	 */
	private Vector2 direction;
	
	/*
	 * Bar de Vie du joueur
	 */
	private BarreDeVie life;
	
	/*
	 * Argent du joueur
	 */
	private int coin;
	
	/*
	 * Inventaire du joueur
	 */
	private Inventaire inv;
	
	/*
	 * Compteur permettant la mise en place d'un cooldown
	 */
	private int cooldownDegat;
	
	/*
	 * Boolean qui indique si le joueur est touch� (permet de mettre un cooldown)
	 */
	private boolean isTouch;
	
	/*
	 * Angle du joueur (pour les pi�ces en Raycastings)
	 */
	private double a;
	
	/*
	 * Distance du joueur au mur (pour les pi�ces en Raycasting)
	 */
	private float distance;
	
	/*
	 * Boolean qui indique si le joueur est invisible 
	 */
	private boolean isInvincible;
	
	/*
	 * Constructeur
	 */
    public Personnage(int degat, int width, int heigth, Vector2 position, Vector2 size, String url) {
    	super(width, heigth, position, url);
    	this.degat = 2;
    	this.multiplicator = 1.0;
    	this.life = new BarreDeVie(6);
    	this.munitions = new ListeBalle();
		this.size = size;
		this.speed = 5.85;
		this.setTouch(false);
		this.cooldownDegat = 0;
		this.direction = new Vector2();
		this.isInvincible = false;
    }
    
    /**
     * @return le nombre de degat qu'inflige le joueur quand il attaque
     */
    public double attaque() {
    	return degat*multiplicator;
    }
    
    /**
     * @param degats subit par le joueur
     * @return Enl�ve la vie correspondante aux nombres de degats au joueur sauf s'il est invisible
     */
    public void subitDegats(double degats) {
    	if(!isInvincible) {
    		life.setVieEnCours((int)(life.getVieEnCours() - degats));
    	}	
    }
    
    /**
     * @return si le joueur est en vie
     */
    public boolean isAlive() {
    	return life.getVieEnCours() > 0;
    }
    
    /**
     * @return Update les caract�ristiques du joueur
     */
    public void updateGameObject()
	{
		move();
		updateLife();
	}
    
    /**
     * @return Update la vie du joueur
     */
    public void updateLife() {
    	for(int i=0; i<Jeu.room.getListeEnnemi().getListe().size(); i++) {
    		if(Jeu.room.getListeEnnemi().getListe().get(i).collisionEnnemi(this) && this.isTouch == false) {
        		this.subitDegats(Jeu.room.getListeEnnemi().getListe().get(i).getDegat());
        		this.setTouch(true);
        	}
    	}	
    }
    
    /**
     * @return Update la Hitbox du joueur
     */
    public void updateHitbox() {
    	this.getHitbox().setPosition(position);
    }

    /**
     * @return Deplacement du joueur
     */
	private void move()
	{
		Vector2 normalizedDirection = getNormalizedDirection();
		Vector2 positionAfterMoving = getPosition().addVector(normalizedDirection);
		setPosition(positionAfterMoving);
		direction = new Vector2();
	}
	
	/**
	 * @return Ajoute 1 en Y pour la direction
	 */
	public void goUpNext()
	{
		getDirection().addY(1);
	}

	/**
	 * @return Enl�ve 1 en Y pour la direction
	 */
	public void goDownNext()
	{
		getDirection().addY(-1);
	}

	/**
	 * @return Enl�ve 1 en X pour la direction
	 */
	public void goLeftNext()
	{
		getDirection().addX(-1);
	}

	/**
	 * @return Ajoute 1 en X pour la direction
	 */
	public void goRightNext()
	{
		getDirection().addX(1);
	}
	
	/**
	 * @return Le vecteur direction normalis�e
	 */
	public Vector2 getNormalizedDirection()
	{
		Vector2 normalizedVector = new Vector2(direction);
		normalizedVector.euclidianNormalize(speed);
		return normalizedVector;
	}
	
	/**
	 * @return Dessine le joueur
	 */
    public void drawPlayer() {
    	Texture.Isaac.bind();
    	Render.getInstance().drawPicture((float) ((float)this.getPosition().getX() - 12.5),(float)this.getPosition().getY() - 5, 50, 50, 200, 200, new float[] {255, 255, 255, 255});
    	Texture.Isaac.unbind();
    	//drawEntite();
    	Raycasting.drawRays3D(this, Jeu.room.getMapEnCours().getCollisionMap());  
    }
    
    /**
     * @return Calcul tous les cooldown en cours
     */
    public void boucleCooldownJoueur()
    {
    	if(!this.getMunitions().isNotShot()) { //Cooldown balle
    		this.getMunitions().setCoolDown(this.getMunitions().getCoolDown()+1);
			if(this.getMunitions().getCoolDown() == 30) {
				this.getMunitions().setCoolDown(0);
				this.getMunitions().setShot(true);
			};
		}
    	if(isTouch()) { //Cooldown degat
			setCooldownDegat((Jeu.Isaac.getCooldownDegat()+1));
			if(getCooldownDegat() == 30) {
				setCooldownDegat(0);
				setTouch(false);
			};
		}
    }
    
    /**
     * @param b une balle
     * @return si une balle ennemi est en collision avec le joueur
     */
    public boolean collisionBalle(Balle b) {
		return (Hitbox.rectangleCollision(b.position, new Vector2(b.getHitbox().getWidth(), b.getHitbox().getHeigth()), position, new Vector2(hitbox.getWidth(), hitbox.getHeigth())) && !isTouch); 
	}
    
    /*
     * Getters & Setters
     */
	public ListeBalle getMunitions() {
		return munitions;
	}

	public void setMunitions(ListeBalle munitions) {
		this.munitions = munitions;
	}

	public int getDegat() {
		return degat;
	}

	public void setDegat(int degat) {
		this.degat = degat;
	}
	

	public double getMultiplicator() {
		return multiplicator;
	}

	public void setMultiplicator(double multiplicator) {
		this.multiplicator = multiplicator;
	}

	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}

	public BarreDeVie getLife() {
		return life;
	}

	public void setLife(BarreDeVie life) {
		this.life = life;
	}

	public Inventaire getInv() {
		return inv;
	}

	public void setInv(Inventaire inv) {
		this.inv = inv;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public double getA() {
		return a;
	}

	public void setA(double a) {
		this.a = a;
	}

	public float getDistance() {
		return distance;
	}

	public void setDistance(float distance) {
		this.distance = distance;
	}

	public Vector2 getPosition() {
		return position;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}

	public Vector2 getSize() {
		return size;
	}

	public void setSize(Vector2 size) {
		this.size = size;
	}

	public Vector2 getDirection() {
		return direction;
	}

	public void setDirection(Vector2 direction) {
		this.direction = direction;
	}

	public Hitbox getHitbox() {
		return hitbox;
	}

	public void setHitbox(Hitbox hitbox) {
		this.hitbox = hitbox;
	}

	public boolean isTouch() {
		return isTouch;
	}

	public void setTouch(boolean isTouch) {
		this.isTouch = isTouch;
	}

	public int getCooldownDegat() {
		return cooldownDegat;
	}

	public void setCooldownDegat(int cooldownDegat) {
		this.cooldownDegat = cooldownDegat;
	}

	public boolean isInvincible() {
		return isInvincible;
	}

	public void setInvincible(boolean isInvincible) {
		this.isInvincible = isInvincible;
	}

	public int getCoin() {
		return coin;
	}

	public void setCoin(int coin) {
		this.coin = coin;
	}	
	
}