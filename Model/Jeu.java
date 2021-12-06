package Model;

import Vue.Fenetre;

import static org.lwjgl.opengl.GL11.*;

import Controler.Input;

import static org.lwjgl.glfw.GLFW.*;

public class Jeu {
    public static void main(String[] args) 
    {    	
    	Fenetre.getInstance().init();
    	Fenetre.getInstance().create();
    	Input.getInstance().init(Fenetre.getInstance().getWindow());
    	Fenetre.getInstance().run();
    }

}
