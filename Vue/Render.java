
package com.projetpo.bindingofisaac.module.Vue;

import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_LINES;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_POINTS;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glColor4f;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glOrtho;
import static org.lwjgl.opengl.GL11.glPointSize;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex2f;

import org.lwjgl.opengl.GL;
//import org.newdawn.slick.TrueTypeFont;
import org.lwjgl.opengl.GL11;

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
		GL11.glLineWidth(200);
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
	
	public void drawText(float x, float y, String text) {
		for(int i=0; i<text.length(); i++) {
			if(text.charAt(i) == '0') {
				Texture.txtZero.bind();
			}
			if(text.charAt(i) == '1') {
				Texture.txtUn.bind();
			}
			if(text.charAt(i) == '2') {
				Texture.txtDeux.bind();
			}
			if(text.charAt(i) == '3') {
				Texture.txtTrois.bind();
			}
			if(text.charAt(i) == '4') {
				Texture.txtQuatre.bind();
			}
			if(text.charAt(i) == '5') {
				Texture.txtCinq.bind();
			}
			if(text.charAt(i) == '6') {
				Texture.txtSix.bind();
			}
			if(text.charAt(i) == '7') {
				Texture.txtSept.bind();
			}
			if(text.charAt(i) == '8') {
				Texture.txtHuit.bind();
			}
			if(text.charAt(i) == '9') {
				Texture.txtNeuf.bind();
			}
			if(text.charAt(i) == 'x') {
				Texture.txtX.bind();
			}
			if(text.charAt(i) == '.') {
				Texture.txtDot.bind();
			}
			if(text.charAt(i) == '$') {
				Texture.txtCoin.bind();
			}
			this.drawPicture(x+i*15, y, Texture.txtUn.getWidth()*2, Texture.txtUn.getHeight()*2);
		}
		Texture.txtZero.unbind();
		Texture.txtUn.unbind();
		Texture.txtDeux.unbind();
		Texture.txtTrois.unbind();
		Texture.txtQuatre.unbind();
		Texture.txtCinq.unbind();
		Texture.txtSix.unbind();
		Texture.txtSept.unbind();
		Texture.txtHuit.unbind();
		Texture.txtNeuf.unbind();
		Texture.txtDot.unbind();
		Texture.txtCoin.unbind();
	} 
	
}