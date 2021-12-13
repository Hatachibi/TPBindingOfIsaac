
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
	
	public void drawSquare(float x, float y, float width, float heigth, float[] color) {
		glColor4f(color[0], color[1], color[2], color[3]);
		glBegin(GL_QUADS);
		glVertex2f(x, y);
		glVertex2f(x+width, y);
		glVertex2f(x+width, y+heigth);
		glVertex2f(x, y+heigth);
		glEnd();
		glColor4f(1f, 1f, 1f, 1f);
	}
	
	public void drawTriangle(float x1, float y1, float x2, float y2, float x3, float y3) {
		glColor4f(0f, 1f, 0f, 1f);
		glBegin(GL_TRIANGLES);
		glVertex2f(x1, y1);
		glVertex2f(x2, y2);
		glVertex2f(x3, y3);
		glEnd();
	}
	
	public void drawPicture(float x, float y, int w, int h, int xo, int yo, float[] color) {
		glEnable(GL_TEXTURE_2D);
		glBegin(GL_QUADS);
//		glColor4f(color[0], color[1], color[2], color[3]);
		glTexCoord2f(0, 0); glVertex2f(x, y);
		glTexCoord2f(1, 0); glVertex2f(x + w, y);
		glTexCoord2f(1, 1); glVertex2f(x + w, y + h);
		glTexCoord2f(0, 1); glVertex2f(x, y + h);
		glEnd();
	}
	
	public void drawCroix(float x, float y) {
		this.drawTrait(x, y+20, x, y-20);
		this.drawTrait(x+20, y, x-20, y);
	}

}