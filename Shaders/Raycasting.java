package com.projetpo.bindingofisaac.module.Shaders;

import com.projetpo.bindingofisaac.module.Model.Personnage;
import com.projetpo.bindingofisaac.module.Vue.Render;

/**
 * On va définir cette classe
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
     * Décrit la taille du mur en fonction de la grille.
     * Plus est grand, plus le mur apparaîtra grand.
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
    
    public static void drawRays3D(Personnage personnage, boolean[][] map) {
    /*	double mdx=0, mdy=0, sx=0, sy=0;
    	double pa = personnage.getA();
    	int heigth = Fenetre.HeigthFenetre;
    	int width = Fenetre.WidthFenetre;
    		for(float i=0; i<PRECISION_TIRE_TRAIT; i+=OFFSET){   
    			mdx = personnage.getPosition().getX() +  Math.cos(pa)*i;       //50 pê modifier
    			mdy = personnage.getPosition().getY() +  Math.sin(pa)*i;
    			OFFSET = dist(sx, sy, personnage.getPosition().getX(), personnage.getPosition().getY())/1000;
    			if(mdx < width && mdx > 0 && mdy < heigth && mdy > 0 && map[(int)(mdx/TAILLE_GRILLE_X)][(int)(mdy/TAILLE_GRILLE_Y)]) {   //A régler avec les collisions
    				sx=mdx;
    				sy=mdy;
    				break;
    			} 	
    		}
    		float dist = (float) ((dist(sx, sy, personnage.getPosition().getX(), personnage.getPosition().getY()))); 
    //		Render.getInstance().drawTrait((float)personnage.getPosition().getX(), (float) personnage.getPosition().getY(),(float) mdx, (float)mdy);
    		personnage.setDistance(dist); */
    		personnage.getHitbox().collisionPlayer(personnage);
    }
}