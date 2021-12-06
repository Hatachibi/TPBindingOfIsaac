package Shaders;

import java.util.*;



import Controler.DeplacerPersonnage;
import Vue.Fenetre;
import Vue.Render;

/**
 * On va définir cette classe
 */
public class Raycasting {

    /**
     * Default constructor
     */
    public Raycasting() {
    }
    
    public static boolean isZCollision = false;
    public static boolean isQCollision = false;
    public static boolean isSCollision = false;
    public static boolean isDCollision = false;

    /**
     * 
     */
    public final static float PI = (float) 3.141592;

    /**
     * 
     */
    public final static float P2 = PI/2;

    /**
     * 
     */
    public final static float P3 = P2*3;
    
    public final static Float RADIAN = 0.0174533f;
    
    public final static Integer ANGLE_DE_VUE = 2;
    
    public final static Integer VITESSE_ROTATION = 50;
    
    public final static Integer PRECISION_TIRE_TRAIT = 1000;
    
    public static float OFFSET = 0.04f;
    
    public final static Integer TAILLE_GRILLE_X = 48;
    
    public final static Integer TAILLE_GRILLE_Y = 48;
      
    private float pa;
    
    /**
     * Décrit la taille du mur en fonction de la grille.
     * Plus est grand, plus le mur apparaîtra grand.
     */
    public final static Integer TAILLE_MUR_3D = 4800;
    
    public static float dist(float ax, float ay, float bx, float by) {
    	return (float) (Math.sqrt((bx-ax)*(bx-ax) + (by-ay)*(by-ay)));
    }
    
    
    public static void cercleTrigo(float a) {
    	if(a <= 0)
        {
            a = (float) (0 + PI);
        }
        else if(a >= 2*PI)
        {
            a = 0;
        }
    }
    
    public static void drawRays3D(DeplacerPersonnage player, int[] map) {
    	float mdx=0, mdy=0, sx=0, sy=0;
    	float pa = player.getA();
    	int heigth = Fenetre.HeigthFenetre;
    	int width = Fenetre.WidthFenetre;
    		for(float i=0; i<PRECISION_TIRE_TRAIT; i+=OFFSET){   
    			mdx = player.getX() + (float) Math.cos(pa)*i;       //50 pê modifier
    			mdy = player.getY() + (float) Math.sin(pa)*i;
    			OFFSET = dist(sx, sy, player.getX(), player.getY())/1000;
    			if(mdx < width && mdx > 0 && mdy < heigth && mdy > 0 && map[(int)(mdx/TAILLE_GRILLE_X)+14*(int)(mdy/TAILLE_GRILLE_Y)]==1) {   //A régler avec les collisions
    				sx=mdx;
    				sy=mdy;
    				break;
    			} 	
    		}
    		float dist = (float) ((dist(sx, sy, player.getX(), player.getY()))); 
    		Render.getInstance().drawTrait(player.getX(), player.getY(), mdx, mdy);
    		Render.getInstance().drawTrait(player.getX()-player.getXmax(), player.getY(), mdx-player.getXmax(), mdy);
    		Render.getInstance().drawTrait(player.getX()+player.getXmax(), player.getY(), mdx+player.getXmax(), mdy);
    		Render.getInstance().drawTrait(player.getX(), player.getY()-player.getYmax(), mdx, mdy-player.getYmax());
    		Render.getInstance().drawTrait(player.getX(), player.getY()+player.getYmax(), mdx, mdy+player.getYmax()); 
    		if(dist <= 7) {
    			if(pa == PI) isQCollision = true;
    			if(pa == PI/2) isZCollision = true;
    			if(pa == 3*(PI/2)) isSCollision = true;
    			if(pa == 0) isDCollision = true;
    		} else {
    			if(pa == PI) isQCollision = false;
    			if(pa == PI/2) isZCollision = false;
    			if(pa == 3*(PI/2)) isSCollision = false;
    			if(pa == 0) isDCollision = false;
    		}
    		if(player.getX() > width) {
    			player.setX(5);
    			isQCollision = false;
    			isDCollision = false;
    		}
    		if(player.getX() < 0) {
    			player.setX(width - 5);
    			isQCollision = false;
    			isDCollision = false;
    		}
    		System.out.println(dist);
    }
}