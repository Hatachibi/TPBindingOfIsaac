
package Vue;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

import java.awt.Font;
import java.awt.image.BufferedImage;
import java.net.MalformedURLException;
import java.net.URL;

import org.lwjgl.opengl.GL;
//import org.newdawn.slick.TrueTypeFont;

import Controler.Input;
import Model.Balle;

public class Render {
	
	public final static Render INSTANCE = new Render();
	public final static int TAILLE_CARRE = 65;
	
	/*
	 * Constructeur
	 */
	private Render() {};
	
	public static Render getInstance() {
		return INSTANCE;
	}
	
/*	public void drawStrings(int x, int y, String text) {
		glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
 
        TrueTypeFont font = new TrueTypeFont(new Font("MS Sans Serif", Font.PLAIN, 30), true);
		font.drawString(x, y, text);
	} */
	
	/**
	 * @return Initialise le visuel pour la fenêtre
	 */
	public void init(long window) {
		glfwMakeContextCurrent(window);
    	GL.createCapabilities(); 	
    	glEnable(GL_BLEND);
    	glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
    	glLoadIdentity();
    	glOrtho(0, Fenetre.WidthFenetre, 0, Fenetre.HeigthFenetre, -1, 1);
	}

	/**
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @return Dessine un trait entre 2 points
	 */
	public void drawTrait(float x1, float y1, float x2, float y2) {
	//	glColor4f(0f, 1f, 0f, 1f);
		glBegin(GL_LINES);
		glVertex2f(x1, y1);
		glVertex2f(x2, y2);
		glEnd();
	}
	
	/**
	 * @param x
	 * @param y
	 * @return Dessine un point
	 */
	public void drawPoint(float x, float y) {
		glColor4f(0f, 1f, 0f, 1f);
		glBegin(GL_POINTS);
		glVertex2f(x, y);
		glEnd();
	}
	
	/**
	 * @param x
	 * @param y
	 * @param size
	 * @return Dessine un point d'une certaine taille
	 */
	public void drawPoint(float x, float y, int size) {
		glColor4f(1f, 1f, 1f, 1f);
		glPointSize(size);
		glBegin(GL_POINTS);
		glVertex2f(x, y);
		glEnd();
	}
	
	/**
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @param x3
	 * @param y3
	 * @param x4
	 * @param y4
	 * @param color
	 * @return Dessine un carre avec 4 points en précisant la couleur
	 */
	public void drawSquare(float x1, float y1, float x2, float y2, float x3, float y3, float x4, float y4, float[] color) {
		glColor4f(color[0], color[1], color[2], color[3]);
		glBegin(GL_QUADS);
		glVertex2f(x1, y1);
		glVertex2f(x2, y2);
		glVertex2f(x3, y3);
		glVertex2f(x4, y4);
		glEnd();
	}
	
	/**
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @param x3
	 * @param y3
	 * @param x4
	 * @param y4
	 * @return Dessine un carre avec 4 points
	 */
	public void drawSquare(float x1, float y1, float x2, float y2, float x3, float y3, float x4, float y4) {
		//	glColor4f(0f, 1f, 0f, 1f);
		glBegin(GL_QUADS);
		glVertex2f(x1, y1);
		glVertex2f(x2, y2);
		glVertex2f(x3, y3);
		glVertex2f(x4, y4);
		glEnd();
	}
	
	/**
	 * @param x
	 * @param y
	 * @param width
	 * @param heigth
	 * @param color
	 * @return Dessine un carre avec 1 point de sa largeur et longueur et avec une couleur
	 */
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
	
	/**
	 * @param x
	 * @param y
	 * @param width
	 * @param heigth
	 * @param color
	 * @return Dessine un carre avec 1 point de sa largeur et longueur
	 */
	public void drawSquare(float x, float y, float width, float heigth) {
		glBegin(GL_QUADS);
		glVertex2f(x, y);
		glVertex2f(x+width, y);
		glVertex2f(x+width, y+heigth);
		glVertex2f(x, y+heigth);
		glEnd();
		glColor4f(1f, 1f, 1f, 1f);
	}
	
	/**
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @param x3
	 * @param y3
	 * @return Dessine un triangle avec 3 points
	 */
	public void drawTriangle(float x1, float y1, float x2, float y2, float x3, float y3) {
		glColor4f(0f, 1f, 0f, 1f);
		glBegin(GL_TRIANGLES);
		glVertex2f(x1, y1);
		glVertex2f(x2, y2);
		glVertex2f(x3, y3);
		glEnd();
	}
	
	/**
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param xo
	 * @param yo
	 * @param color
	 * @return Dessine une image avec un filtre couleur
	 */
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
	
	/**
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @return Dessine une image sans filtre couleur
	 */
	public void drawPicture(float x, float y, int w, int h) {
		glEnable(GL_TEXTURE_2D);
		glBegin(GL_QUADS);
//		glColor4f(color[0], color[1], color[2], color[3]);
		glTexCoord2f(0, 0); glVertex2f(x, y);
		glTexCoord2f(1, 0); glVertex2f(x + w, y);
		glTexCoord2f(1, 1); glVertex2f(x + w, y + h);
		glTexCoord2f(0, 1); glVertex2f(x, y + h);
		glEnd();
	}
	
/*	public void drawText(float x, float y, String text) {
		Font awtFont = new Font("Times New Roman", Font.BOLD, 12);
		TrueTypeFont font = new TrueTypeFont();
	} */
	
	/**
	 * @param x
	 * @param y
	 * @return Dessine une croix (style FPS)
	 */
	public void drawCroix(float x, float y) {
		this.drawTrait(x, y+20, x, y-20);
		this.drawTrait(x+20, y, x-20, y);
	}

}