package Shaders;

import java.util.*;



import Controler.DeplacerPersonnage;
import Vue.Fenetre;
import Vue.Render;

/**
 * On va d�finir cette classe
 */
public class Raycasting {

    /**
     * Default constructor
     */
    public Raycasting() {
    }
    

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
    
    public final static Integer TAILLE_GRILLE_X = Render.TAILLE_CARRE;
    
    public final static Integer TAILLE_GRILLE_Y = Render.TAILLE_CARRE;
     
  
    /**
     * D�crit la taille du mur en fonction de la grille.
     * Plus est grand, plus le mur appara�tra grand.
     */
    public final static Integer TAILLE_MUR_3D = 4800;
    
    public static float dist(double ax, double ay, double bx, double by) {
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
    	double mdx=0, mdy=0, sx=0, sy=0;
    	double pa = player.getA();
    	int heigth = Fenetre.HeigthFenetre;
    	int width = Fenetre.WidthFenetre;
    		for(float i=0; i<PRECISION_TIRE_TRAIT; i+=OFFSET){   
    			mdx = player.getHit().getEntity().getX() +  Math.cos(pa)*i;       //50 p� modifier
    			mdy = player.getHit().getEntity().getY() +  Math.sin(pa)*i;
    			OFFSET = dist(sx, sy, player.getHit().getEntity().getX(), player.getHit().getEntity().getY())/1000;
    			if(mdx < width && mdx > 0 && mdy < heigth && mdy > 0 && map[(int)(mdx/TAILLE_GRILLE_X)+9*(int)(mdy/TAILLE_GRILLE_Y)]==1) {   //A r�gler avec les collisions
    				sx=mdx;
    				sy=mdy;
    				break;
    			} 	
    		}
    		float dist = (float) ((dist(sx, sy, player.getHit().getEntity().getX(), player.getHit().getEntity().getY()))); 
    	/*	Render.getInstance().drawTrait((float)player.getHit().getEntity().getX(), (float) player.getHit().getEntity().getY(),(float) mdx, (float)mdy);
    		Render.getInstance().drawTrait(player.getX()-player.getXmax(), player.getY(), mdx-player.getXmax(), mdy);
    		Render.getInstance().drawTrait(player.getX()+player.getXmax(), player.getY(), mdx+player.getXmax(), mdy);
    		Render.getInstance().drawTrait(player.getX(), player.getY()-player.getYmax(), mdx, mdy-player.getYmax());
    		Render.getInstance().drawTrait(player.getX(), player.getY()+player.getYmax(), mdx, mdy+player.getYmax());  */
    		player.setDistance(dist);
    		player.getHit().collisionPlayer(player);
    }
}