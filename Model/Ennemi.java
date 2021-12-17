package Model;

import Shaders.Vector2;

public class Ennemi extends Entite{
	
	public Ennemi(int width, int heigth, Vector2 position, String url) {
		super(width, heigth, position, url);
		
	}
	
	public boolean collisionEnnemi(Personnage p) {
		return Hitbox.rectangleCollision(p.position, new Vector2(p.getHitbox().getWidth(), p.getHitbox().getHeigth()), position, new Vector2(hitbox.getWidth(), hitbox.getHeigth()));
	}
	
	public void IA(Personnage p) {
		if(p.getPosition().getX()>this.getPosition().getX())
		{
			this.getPosition().setX(this.getPosition().getX()+1);
		}
		if(p.getPosition().getX()<this.getPosition().getX())
		{
			this.getPosition().setX(this.getPosition().getX()-1);
		}
		if(p.getPosition().getY()>this.getPosition().getY())
		{
			this.getPosition().setY(this.getPosition().getY()+1);
		}
		if(p.getPosition().getY()<this.getPosition().getY())
		{
			this.getPosition().setY(this.getPosition().getY()-1);
		}
	}
	
}
