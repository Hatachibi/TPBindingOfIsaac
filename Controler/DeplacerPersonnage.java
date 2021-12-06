package Controler;

import java.util.*;

import org.lwjgl.opengl.GL11;

import Shaders.Raycasting;
import Vue.Render;

import static org.lwjgl.glfw.GLFW.*;

/**
 * 
 */
public class DeplacerPersonnage {

	private float x;
	private float y;
	private float a;
	private float xmax;
	private float ymax;
	
    /**
     * Default constructor
     */
    public DeplacerPersonnage(float x, float y) {
    	this.x = x;
    	this.y = y;
    	this.a = 0;
    	this.xmax = 5;
    	this.ymax = 5;
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
				1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
				1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1,
				1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1,
				1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1,
				1, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 1,
				1, 0, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 0, 1,
				1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1,
				1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
		});
    }

}