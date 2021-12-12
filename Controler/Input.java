package Controler;

import static org.lwjgl.glfw.GLFW.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.lwjgl.glfw.*;

import Model.Balle;
import Model.Jeu;
import Shaders.Raycasting;
import Vue.Fenetre;
import Vue.Render;

public class Input
{
	private static final Input INSTANCE = new Input();
	
	public final static double PI = (float) 3.141592;
    public final static double P2 = (float) (PI/2);
    
    private double x = Jeu.Isaac.getDeplacement().getHit().getEntity().getX();
	private double y = Jeu.Isaac.getDeplacement().getHit().getEntity().getY();
	private double a = Jeu.Isaac.getDeplacement().getA();
	
	private final int[] listeInput = {GLFW.GLFW_KEY_A, GLFW.GLFW_KEY_D, GLFW.GLFW_KEY_W, GLFW.GLFW_KEY_S,
			GLFW.GLFW_KEY_UP, GLFW.GLFW_KEY_DOWN, GLFW.GLFW_KEY_RIGHT, GLFW.GLFW_KEY_LEFT};
	
	public static final double SPEED = 3;//5.85;

	public void drawBalle() 
	{
		Jeu.Isaac.getMunitions().drawBalle();
	}
	
	public DeplacerPersonnage getPlayerMove() {
		return Jeu.Isaac.getDeplacement();
	}
	
	public void deplacement()
	{
		for(Integer key:listeInput)
		{
			checkDeplacement(Fenetre.getInstance().getWindow(), key);
		}
	}
	
	public void tire()
	{
		for(Integer key:listeInput)
		{
			checkTire(Fenetre.getInstance().getWindow(), key);
		}
	}
	
	/**
	 * Méthode qui vérifie si une touche a été pressée.
	 * Si oui renvoie vers la méthode getAWSDkeys(window).
	 * Si non, définie l'entrée rémanente à faux.
	 * @param window
	 * La fenêtre active.
	 * @param key
	 * La touche détectée à vérifier.
	 */
	public void checkDeplacement(long window, int key)
	{
		if(glfwGetKey(window, key) == GLFW_PRESS)
		{
			System.out.println("Pressed");
			getAWSDkeys(window);
		}
		if(glfwGetKey(window, key) == GLFW_RELEASE)
		{
			glfwSetInputMode(window, GLFW_STICKY_KEYS, GLFW_FALSE);
			System.out.println("Released");
		}
	}
	
	public void checkTire(long window, int key)
	{
		if(glfwGetKey(window, key) == GLFW_PRESS)
		{
			System.out.println("Pressed");
			getShotsKeys(window);
		}
		if(glfwGetKey(window, key) == GLFW_RELEASE)
		{
			glfwSetInputMode(window, GLFW_STICKY_KEYS, GLFW_FALSE);
			System.out.println("Released");
		}
	}
	
	/**
	 * Méthode qui fait se déplacer le personnage vers le haut.
	 */
	public void moveUp()
	{
		a = PI/2;
		if(!Jeu.Isaac.getDeplacement().getHit().isZCollision()) {
			y += SPEED;
		}
		Jeu.Isaac.getDeplacement().update(x, y, a);
		Jeu.Isaac.getDeplacement().drawPlayer();
	}
	
	/**
	 * Méthode qui fait se déplacer le personnage vers le bas.
	 */
	public void moveDown()
	{
		a = 3*(PI/2);
		if(!Jeu.Isaac.getDeplacement().getHit().isSCollision()) {
			y -= SPEED;
		}
		Jeu.Isaac.getDeplacement().update(x, y, a);
		Jeu.Isaac.getDeplacement().drawPlayer();
	}
	
	/**
	 * Méthode qui fait se déplacer le personnage vers la droite.
	 */
	public void moveRight()
	{
		a = 0;
		if(!Jeu.Isaac.getDeplacement().getHit().isDCollision()) {
			x += SPEED;
		}
		Jeu.Isaac.getDeplacement().update(x, y, a);
		Jeu.Isaac.getDeplacement().drawPlayer();
	}
	
	/**
	 * Méthode qui fait se déplacer le personnage vers la gauche.
	 */
	public void moveLeft()
	{
		a = PI;
		if(!Jeu.Isaac.getDeplacement().getHit().isQCollision()) {
			x -= SPEED;
		}
		Jeu.Isaac.getDeplacement().update(x, y, a);
		Jeu.Isaac.getDeplacement().drawPlayer();
	}
	
	public void shootUp()
	{
		Jeu.Isaac.getMunitions().addBalle(new Balle(1, 1, Jeu.Isaac.getDeplacement().getHit().getEntity().getX(), Jeu.Isaac.getDeplacement().getHit().getEntity().getY(), PI/2));
	}
	
	public void shootDown()
	{
		Jeu.Isaac.getMunitions().addBalle(new Balle(1, 1, Jeu.Isaac.getDeplacement().getHit().getEntity().getX(), Jeu.Isaac.getDeplacement().getHit().getEntity().getY(), 3*(PI/2)));
	}
	
	public void shootRight()
	{
		Jeu.Isaac.getMunitions().addBalle(new Balle(1, 1, Jeu.Isaac.getDeplacement().getHit().getEntity().getX(), Jeu.Isaac.getDeplacement().getHit().getEntity().getY(), 0));
	}
	
	public void shootLeft()
	{
		Jeu.Isaac.getMunitions().addBalle(new Balle(1, 1, Jeu.Isaac.getDeplacement().getHit().getEntity().getX(), Jeu.Isaac.getDeplacement().getHit().getEntity().getY(), PI));

	}
	
	public void getAWSDkeys(long window)
	{
		if(glfwGetKey(window, GLFW_KEY_W) == GLFW.GLFW_PRESS)
		{
			moveUp();
			glfwSetInputMode(window, GLFW_STICKY_KEYS, GLFW_TRUE);
		}
		if(glfwGetKey(window, GLFW_KEY_S) == GLFW.GLFW_PRESS)
		{
			moveDown();
			glfwSetInputMode(window, GLFW_STICKY_KEYS, GLFW_TRUE);
		}
		if(glfwGetKey(window, GLFW_KEY_D) == GLFW.GLFW_PRESS)
		{
			moveRight();
			glfwSetInputMode(window, GLFW_STICKY_KEYS, GLFW_TRUE);
		}
		if(glfwGetKey(window, GLFW_KEY_A) == GLFW.GLFW_PRESS)
		{
			moveLeft();
			glfwSetInputMode(window, GLFW_STICKY_KEYS, GLFW_TRUE);
		}
	}
	
	
	public void getShotsKeys(long window)
	{
		if(glfwGetKey(window, GLFW_KEY_UP) == GLFW.GLFW_PRESS)
		{
			shootUp();
			glfwSetInputMode(window, GLFW_STICKY_KEYS, GLFW_TRUE);
		}
		if(glfwGetKey(window, GLFW_KEY_DOWN) == GLFW.GLFW_PRESS)
		{
			shootDown();
			glfwSetInputMode(window, GLFW_STICKY_KEYS, GLFW_TRUE);
		}
		if(glfwGetKey(window, GLFW_KEY_RIGHT) == GLFW.GLFW_PRESS)
		{
			shootRight();
			glfwSetInputMode(window, GLFW_STICKY_KEYS, GLFW_TRUE);
		}
		if(glfwGetKey(window, GLFW_KEY_LEFT) == GLFW.GLFW_PRESS)
		{
			shootLeft();
			glfwSetInputMode(window, GLFW_STICKY_KEYS, GLFW_TRUE);
		}
	}
	
	
	public static Input getInstance()
	{
		return INSTANCE;
	}
	
	public void init(long window)
	{
		
	}
	
	//private GLFWKeyCallback keyboard;

	/*public void init(long window)
	{
		for(Integer key:listeInput)
		{
			keyPolling(window, key);
		}*/	
		//glfwSetKeyCallback(window, keyboard);
		/*keyPolling(window, key);
		glfwPollEvents();
	}*/
	
	
	//private HashMap<Integer, Boolean> mappageTouches;
	
		/*private Input()
		{
			mappageTouches = new HashMap<Integer, Boolean>();
			
			for(int key:listeInput)
			{
				mappageTouches.put(key, false);
			}*/

			/*keyboard = new GLFWKeyCallback()
			{

				@Override
				public void invoke(long window, int key, int scancode, int action, int mods) 
				{
					if(glfwGetKey(window, key) == GLFW_PRESS)
					{
						System.out.println("Pressed");
						getAWSDkeys(window);
						getShotsKeys(window);
					}
					if(glfwGetKey(window, key) == GLFW_RELEASE)
					{
						glfwSetInputMode(window, GLFW_STICKY_KEYS, GLFW_FALSE);
						System.out.println("Released");
					}
				}
				
			};
		}*/
	
	/*public void getAWSDkeys()
	{
		for(Integer key:listeInput)
		{
			if(mappageTouches.get(key))
			{
				switch(key)
				{
					case GLFW.GLFW_KEY_A:
						a = PI;
						if(!Jeu.Isaac.getDeplacement().getHit().isQCollision()) x -= speed;
						Jeu.Isaac.getDeplacement().update(x, y, a);
						Jeu.Isaac.getDeplacement().drawPlayer();
						break;
					case GLFW.GLFW_KEY_D:
						a = 0;
						if(!Jeu.Isaac.getDeplacement().getHit().isDCollision()) x += speed;
						Jeu.Isaac.getDeplacement().update(x, y, a);
						Jeu.Isaac.getDeplacement().drawPlayer();
						break;
					case GLFW.GLFW_KEY_W:
						a = PI/2;
						if(!Jeu.Isaac.getDeplacement().getHit().isZCollision()) y += speed;
						Jeu.Isaac.getDeplacement().update(x, y, a);
						Jeu.Isaac.getDeplacement().drawPlayer();
						break;
					case GLFW.GLFW_KEY_S:
						a = 3*(PI/2);
						if(!Jeu.Isaac.getDeplacement().getHit().isSCollision()) y -= speed;
						Jeu.Isaac.getDeplacement().update(x, y, a);
						Jeu.Isaac.getDeplacement().drawPlayer();
						break;
				}
			}
		}

	}*/
	
	/*public void getShotsKeys()
	{
		for(Integer key:listeInput)
		{
			if(mappageTouches.get(key))
			{
				switch(key)
				{
					case GLFW.GLFW_KEY_UP:
						Jeu.Isaac.getMunitions().addBalle(new Balle(1, 1, Jeu.Isaac.getDeplacement().getHit().getEntity().getX(), Jeu.Isaac.getDeplacement().getHit().getEntity().getY(), 3));
						break;
					case GLFW.GLFW_KEY_DOWN:
						Jeu.Isaac.getMunitions().addBalle(new Balle(1, 1, Jeu.Isaac.getDeplacement().getHit().getEntity().getX(), Jeu.Isaac.getDeplacement().getHit().getEntity().getY(), 4));
						break;
					case GLFW.GLFW_KEY_RIGHT:
						Jeu.Isaac.getMunitions().addBalle(new Balle(1, 1, Jeu.Isaac.getDeplacement().getHit().getEntity().getX(), Jeu.Isaac.getDeplacement().getHit().getEntity().getY(), 2));
						break;
					case GLFW.GLFW_KEY_LEFT:
						Jeu.Isaac.getMunitions().addBalle(new Balle(1, 1, Jeu.Isaac.getDeplacement().getHit().getEntity().getX(), Jeu.Isaac.getDeplacement().getHit().getEntity().getY(), 1));
						break;
				}
			}
		}
		
	}*/
	
}