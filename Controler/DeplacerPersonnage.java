package Controler;

import java.util.*;

import org.lwjgl.opengl.GL11;

import Model.Entite;
import Model.Hitbox;
import Model.Jeu;
import Shaders.Raycasting;
import Vue.Render;

import static org.lwjgl.glfw.GLFW.*;

public class DeplacerPersonnage {

	private double a;
	private float xmax;
	private float ymax;
	private float distance;
	private Hitbox hit;
   
    public DeplacerPersonnage(Entite e) {
    	this.a = 0;
    	this.xmax = 5;
    	this.ymax = 5;
    	this.hit = new Hitbox(e);
    }
    
	public float getXmax() {
		return xmax;
	}

	public float getYmax() {
		return ymax;
	}

	public double getA() {
		return a;
	}

	public void setA(double a) {
		this.a = a;
	}

	public void update(double x, double y, double a) {
    	hit.getEntity().setX(x);
    	hit.getEntity().setY(y);
    	this.a = a;
    }
        
    public void drawPlayer() {
    	Render.getInstance().drawPoint((float) hit.getEntity().getX(),(float) hit.getEntity().getY(), 8); //Obliger de cast en float car sinon on ne peut pas draw les ractangles
    	Raycasting.drawRays3D(this, new int[]  {
    			1, 1, 1, 1, 1, 1, 1, 1, 1,
				1, 0, 0, 0, 0, 0, 0, 0, 1,
				1, 0, 0, 0, 0, 0, 0, 0, 1,
				1, 0, 0, 0, 1, 0, 0, 0, 1,
				1, 0, 0, 0, 1, 0, 0, 0, 1,
				1, 0, 0, 0, 1, 0, 0, 0, 1,
				1, 0, 0, 0, 0, 0, 0, 0, 1,
				1, 0, 0, 0, 0, 0, 0, 0, 1,
				1, 1, 1, 1, 1, 1, 1, 1, 1
		});
    }

	public float getDistance() {
		return distance;
	}

	public void setDistance(float distance) {
		this.distance = distance;
	}

	public Hitbox getHit() {
		return hit;
	}

	public void setHit(Hitbox hit) {
		this.hit = hit;
	}

}