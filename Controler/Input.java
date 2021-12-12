package Controler;

import static org.lwjgl.glfw.GLFW.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.lwjgl.glfw.*;

import Model.Balle;
import Model.Jeu;
import Model.Personnage;
import Shaders.Raycasting;
import Vue.Fenetre;
import Vue.Render;

public class Input
{
	private static final Input INSTANCE = new Input();
	
	public final static float PI = (float) 3.141592;
    public final static float P2 = (float) (PI/2);
    
    private double x = Jeu.Isaac.getPosition().getY();
	private double y = Jeu.Isaac.getPosition().getX();
	private double a = Jeu.Isaac.getA();
	
	private final int[] listeInput = {GLFW.GLFW_KEY_A, GLFW.GLFW_KEY_D, GLFW.GLFW_KEY_W, GLFW.GLFW_KEY_S,
			GLFW.GLFW_KEY_UP, GLFW.GLFW_KEY_DOWN, GLFW.GLFW_KEY_RIGHT, GLFW.GLFW_KEY_LEFT};
	
	private double speed = 5.85;

	public void drawBalle() 
	{
		Jeu.Isaac.getMunitions().drawBalle();
	}
	
	public Personnage getPlayerMove() {
		return Jeu.Isaac;
	}
	
	private GLFWKeyCallback keyboard;
	
	//private HashMap<Integer, Boolean> mappageTouches;
	
	private Input()
	{
		/*mappageTouches = new HashMap<Integer, Boolean>();
		
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
			
		};*/
	}
	
	public void deplacement()
	{
		for(Integer key:listeInput)
		{
			getDeplacement(Fenetre.getInstance().getWindow(), key);
		}
		Jeu.Isaac.updateGameObject();
	}
	
	public void tire()
	{
		for(Integer key:listeInput)
		{
			getTire(Fenetre.getInstance().getWindow(), key);
		}
	}
	
	public void getDeplacement(long window, int key)
	{
		if(glfwGetKey(window, key) == GLFW_PRESS)
		{
	//		System.out.println("Pressed");
			getAWSDkeys(window);
		}
		if(glfwGetKey(window, key) == GLFW_RELEASE)
		{
			glfwSetInputMode(window, GLFW_STICKY_KEYS, GLFW_FALSE);
	//		System.out.println("Released");
		}
	}
	
	public void getTire(long window, int key)
	{
		if(glfwGetKey(window, key) == GLFW_PRESS)
		{
	//		System.out.println("Pressed");
			getShotsKeys(window);
		}
		if(glfwGetKey(window, key) == GLFW_RELEASE)
		{
			glfwSetInputMode(window, GLFW_STICKY_KEYS, GLFW_FALSE);
	//		System.out.println("Released");
		}
	}
	
	public void moveUp()
	{
		if(!Jeu.Isaac.getHitbox().isZCollision()) {
			Jeu.Isaac.goUpNext();
		}
		Jeu.Isaac.setA(PI/2);
		Jeu.Isaac.drawPlayer();
	}
	
	public void moveDown()
	{
		if(!Jeu.Isaac.getHitbox().isSCollision()) {
			Jeu.Isaac.goDownNext();
		}
		Jeu.Isaac.setA(3*PI/2);
		Jeu.Isaac.drawPlayer();
	}
	
	public void moveRight()
	{
		a = 0;
		if(!Jeu.Isaac.getHitbox().isDCollision()) {
			Jeu.Isaac.goRightNext();
		}
		Jeu.Isaac.setA(0);
		Jeu.Isaac.drawPlayer();
	}
	
	public void moveLeft()
	{
		a = PI;
		if(!Jeu.Isaac.getHitbox().isQCollision()) {
			Jeu.Isaac.goLeftNext();
		}
		Jeu.Isaac.setA(PI);
		Jeu.Isaac.drawPlayer();
	}
	
	public void shootUp()
	{
		Jeu.Isaac.getMunitions().addBalle(new Balle(1, 1, Jeu.Isaac.getPosition().getX(), Jeu.Isaac.getPosition().getY(), 3));
	}
	
	public void shootDown()
	{
		Jeu.Isaac.getMunitions().addBalle(new Balle(1, 1, Jeu.Isaac.getPosition().getX(), Jeu.Isaac.getPosition().getY(), 4));	}
	
	public void shootRight()
	{
		Jeu.Isaac.getMunitions().addBalle(new Balle(1, 1, Jeu.Isaac.getPosition().getX(), Jeu.Isaac.getPosition().getY(), 2));	}
	
	public void shootLeft()
	{
		Jeu.Isaac.getMunitions().addBalle(new Balle(1, 1, Jeu.Isaac.getPosition().getX(), Jeu.Isaac.getPosition().getY(), 1));
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
		/*for(Integer key:listeInput)
		{
			keyPolling(window, key);
		}*/	
		//glfwSetKeyCallback(window, keyboard);
		/*keyPolling(window, key);
		glfwPollEvents();*/
	}
	
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