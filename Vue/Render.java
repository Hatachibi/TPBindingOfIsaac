
package Vue;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

import java.awt.image.BufferedImage;
import java.net.MalformedURLException;
import java.net.URL;

import org.lwjgl.opengl.GL;
import Controler.Input;
import Model.Balle;

public class Render {
	
	public final static Render INSTANCE = new Render();
	public final static int TAILLE_CARRE = 65;
	
	private Render() {};
	
	public static Render getInstance() {
		return INSTANCE;
	}
	
	public void init(long window) {
		glfwMakeContextCurrent(window);
    	GL.createCapabilities();
    	glEnable(GL_ALPHA_TEST);  	
    	glLoadIdentity();
    	glOrtho(0, Fenetre.WidthFenetre, 0, Fenetre.HeigthFenetre, -1, 1);
	}

	public void drawTrait(float x1, float y1, float x2, float y2) {
	//	glColor4f(0f, 1f, 0f, 1f);
		glBegin(GL_LINES);
		glVertex2f(x1, y1);
		glVertex2f(x2, y2);
		glEnd();
	}
	
	public void drawPoint(float x, float y) {
		glColor4f(0f, 1f, 0f, 1f);
		glBegin(GL_POINTS);
		glVertex2f(x, y);
		glEnd();
	}
	
	public void drawPoint(float x, float y, int size) {
		glColor4f(1f, 1f, 1f, 1f);
		glPointSize(size);
		glBegin(GL_POINTS);
		glVertex2f(x, y);
		glEnd();
	}
	
	public void drawSquare(float x1, float y1, float x2, float y2, float x3, float y3, float x4, float y4, float[] color) {
		glColor4f(color[0], color[1], color[2], color[3]);
		glBegin(GL_QUADS);
		glVertex2f(x1, y1);
		glVertex2f(x2, y2);
		glVertex2f(x3, y3);
		glVertex2f(x4, y4);
		glEnd();
	}
	
	public void drawSquare(float x1, float y1, float x2, float y2, float x3, float y3, float x4, float y4) {
		//	glColor4f(0f, 1f, 0f, 1f);
			glBegin(GL_QUADS);
			glVertex2f(x1, y1);
			glVertex2f(x2, y2);
			glVertex2f(x3, y3);
			glVertex2f(x4, y4);
			glEnd();
		}
	
	public void drawTriangle(float x1, float y1, float x2, float y2, float x3, float y3) {
		glColor4f(0f, 1f, 0f, 1f);
		glBegin(GL_TRIANGLES);
		glVertex2f(x1, y1);
		glVertex2f(x2, y2);
		glVertex2f(x3, y3);
		glEnd();
	}
	
	public void drawCroix(float x, float y) {
		this.drawTrait(x, y+20, x, y-20);
		this.drawTrait(x+20, y, x-20, y);
	}
		
	public void mapTest() {
		int[] map = {
				1, 1, 1, 1, 1, 1, 1, 1, 1,
				1, 0, 0, 0, 0, 0, 0, 0, 1,
				1, 0, 0, 0, 0, 0, 0, 0, 1,
				1, 0, 0, 0, 0, 0, 0, 0, 1,
				1, 0, 0, 0, 0, 0, 0, 0, 1,
				1, 0, 0, 0, 0, 0, 0, 0, 1,
				1, 0, 0, 0, 0, 0, 0, 0, 1,
				1, 0, 0, 0, 0, 0, 0, 0, 1,
				1, 1, 1, 1, 1, 1, 1, 1, 1
		};
		for(int y=0; y<9; y++) {
			for(int x=0; x<9; x++) {
				if(map[(y*9)+x]==1) {
					glColor3f(1, 1, 0);
				} else {
					glColor3f(1, 0, 1);
				}
				int xo = x*TAILLE_CARRE;
				int yo = y*TAILLE_CARRE;
				this.drawSquare(xo, yo, xo, yo+TAILLE_CARRE, xo+TAILLE_CARRE, yo+TAILLE_CARRE, xo+TAILLE_CARRE, yo);
			}
		}
		
	}
	
	public void render() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		this.mapTest();
		Input.getInstance().getPlayerMove().drawPlayer();
		
	}
	
}