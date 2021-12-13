package Model;

import java.util.*;

import Controler.ListeBalle;
import Shaders.Raycasting;
import Shaders.Vector2;
import Vue.Render;
import Vue.Texture;

public class Personnage extends Entite{

	private ListeBalle munitions;
	private int degat;
	private double multiplicator;
	private int range;
	private double speed;
//	private Hitbox hitbox;
//	private Vector2 position;
	private Vector2 size;
	private Vector2 direction;
	private BarreDeVie life;
	private Inventaire inv;
	private double a;
	private float distance;
	
    public Personnage(int degat, int width, int heigth, Vector2 position, Vector2 size, String url) {
    	super(width, heigth, position, url);
    	this.degat = 2;
    	this.multiplicator = 1.0;
    	this.life = new BarreDeVie(10);
  //  	this.hitbox = new Hitbox(position, width, heigth);
    	this.munitions = new ListeBalle();
  //  	this.position = position;
		this.size = size;
		this.speed = 5.85;
		this.direction = new Vector2();
    }
    
    public double attaque() {
    	return degat*multiplicator;
    }
    
    public void subitDegats(double degats) {
    	life.setVieEnCours((int)(life.getVieEnCours() - degats));
    }
    
    public boolean isAlive() {
    	return life.getVieEnCours() >= 0;
    }
    
    public void updateGameObject()
	{
		move();
	}
    
    public void updateHitbox() {
    	this.getHitbox().setPosition(position);
    }

	private void move()
	{
		Vector2 normalizedDirection = getNormalizedDirection();
		Vector2 positionAfterMoving = getPosition().addVector(normalizedDirection);
		setPosition(positionAfterMoving);
		direction = new Vector2();
	}
	
	public void goUpNext()
	{
		getDirection().addY(1);
	}

	public void goDownNext()
	{
		getDirection().addY(-1);
	}

	public void goLeftNext()
	{
		getDirection().addX(-1);
	}

	public void goRightNext()
	{
		getDirection().addX(1);
	}
	
	public Vector2 getNormalizedDirection()
	{
		Vector2 normalizedVector = new Vector2(direction);
		normalizedVector.euclidianNormalize(speed);
		return normalizedVector;
	}
    
    public void drawPlayer() {
    	Texture.Isaac.bind();
    	Render.getInstance().drawPicture((float) ((float)this.getPosition().getX() - 12.5),(float)this.getPosition().getY() - 5, 50, 50, 200, 200, new float[] {255, 255, 255, 255});
    	Texture.Isaac.unbind();
    	Render.getInstance().drawSquare((float)hitbox.getPosition().getX(), (float)hitbox.getPosition().getY(), (float)hitbox.getPositionX().getX(), (float)hitbox.getPositionX().getY(), (float)hitbox.getPositionXY().getX(), (float)hitbox.getPositionXY().getY(), (float)hitbox.getPositionY().getX(), (float)hitbox.getPositionY().getY()); //Obliger de cast en float car sinon on ne peut pas draw les rectangles
    	Raycasting.drawRays3D(this, Jeu.room.getMapEnCours().getCollisionMap()); 
    }
    
    public void boucleCooldownJoueur()
    {
    	if(!this.getMunitions().isNotShot()) {
    		this.getMunitions().setCoolDown(this.getMunitions().getCoolDown()+1);
			if(this.getMunitions().getCoolDown() == 30) {
				this.getMunitions().setCoolDown(0);
				this.getMunitions().setShot(true);
			};
		}
    }

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
	
	
}