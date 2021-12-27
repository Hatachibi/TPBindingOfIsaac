package Model;

import Vue.Fenetre;
import Controler.Input;
import Shaders.Vector2;

public class Jeu {
	
	public static Personnage Isaac = new Personnage(10, 25, 25, new Vector2(100, 100), new Vector2(1, 1), "libImg/Isaac.png");
	public static final Room room = new Room(Isaac);
	
    public static void main(String[] args) 
    {    	
    	Fenetre.getInstance().init();
    	Fenetre.getInstance().create();
    	Input.getInstance().init(Fenetre.getInstance().getWindow());
    	Fenetre.getInstance().run();
    }

}