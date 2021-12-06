package Model;

import java.util.*;

import Controler.DeplacerPersonnage;

/**
 * 
 */
public class Personnage {

	private DeplacerPersonnage deplacement;
	private Balle balle;
	private int range;
	
	
    /**
     * Default constructor
     */
    public Personnage(DeplacerPersonnage deplacement, Balle balle) {
    	this.deplacement = deplacement;
    	this.balle = balle;
    }

}