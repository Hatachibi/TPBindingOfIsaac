package Model;

import Shaders.Vector2;

public class Ennemi extends Entite{
	
	private Vector2 direction;
	private double speed;
	
	public Ennemi(int width, int heigth, Vector2 position, double speed,String url) {
		super(width, heigth, position, url);
		this.speed = speed;
		
	}
		
	public boolean collisionEnnemi(Personnage p) {
		return Hitbox.rectangleCollision(p.position, new Vector2(p.getHitbox().getWidth(), p.getHitbox().getHeigth()), position, new Vector2(hitbox.getWidth(), hitbox.getHeigth()));
	}
	
	private void move()
	{
		Vector2 normalizedDirection = getNormalizedDirection();
		Vector2 positionAfterMoving = getPosition().addVector(normalizedDirection);
		setPosition(positionAfterMoving);
		direction = new Vector2();
	}
	
	public Vector2 getNormalizedDirection()
	{
		Vector2 normalizedVector = new Vector2(direction);
		normalizedVector.euclidianNormalize(speed);
		return normalizedVector;
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
	
	public void IA(Personnage p) {
		setDirection(new Vector2(p.getPosition().getX() - position.getX(), p.getPosition().getY() - position.getY()));
		move();
	}

	public Vector2 getDirection() {
		return direction;
	}

	public void setDirection(Vector2 direction) {
		this.direction = direction;
	}
	
	
	
}
