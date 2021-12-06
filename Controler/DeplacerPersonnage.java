package Controler;

import java.util.*;

import org.lwjgl.opengl.GL11;

import Model.Entite;
import Model.Hitbox;
import Shaders.Raycasting;
import Vue.Render;

import static org.lwjgl.glfw.GLFW.*;

public class DeplacerPersonnage {

	private float x;
	private float y;
	private float a;
	private int width;
	private int heigth;
	private float xmax;
	private float ymax;
	private float distance;
	private Hitbox hit;
   
    public DeplacerPersonnage(float x, float y, int width, int heigth) {
    	this.x = x;
    	this.y = y;
    	this.a = 0;
    	this.xmax = 5;
    	this.ymax = 5;
    	this.hit = new Hitbox(new Entite(width, heigth));
    }
    
	public float getXmax() {
		return xmax;
	}

	public float getYmax() {
		return ymax;
	}

	public float getA() {
		return a;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public void setA(float a) {
		this.a = a;
	}

	public void update(float x, float y, float a) {
    	this.x = x;
    	this.y = y;
    	this.a = a;
    }
        
    public void drawPlayer() {
    	Render.getInstance().drawPoint(x, y, 8);
    	Raycasting.drawRays3D(this, new int[]  {
    			1, 1, 1, 1, 1, 1, 1, 1, 1,
				1, 0, 0, 0, 0, 0, 0, 0, 1,
				1, 0, 0, 0, 0, 0, 0, 0, 1,
				1, 0, 0, 0, 0, 0, 0, 0, 1,
				1, 0, 0, 0, 0, 0, 0, 0, 1,
				1, 0, 0, 0, 0, 0, 0, 0, 1,
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