package com.projetpo.bindingofisaac.module.Controler;

import static org.lwjgl.glfw.GLFW.GLFW_FALSE;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_A;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_D;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_DOWN;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_I;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_K;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_L;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_LEFT;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_O;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_P;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_RIGHT;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_S;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_SPACE;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_UP;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_W;
import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;
import static org.lwjgl.glfw.GLFW.GLFW_STICKY_KEYS;
import static org.lwjgl.glfw.GLFW.GLFW_TRUE;
import static org.lwjgl.glfw.GLFW.glfwGetKey;
import static org.lwjgl.glfw.GLFW.glfwSetInputMode;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallback;

import com.projetpo.bindingofisaac.module.Model.Balle;
import com.projetpo.bindingofisaac.module.Model.Jeu;
import com.projetpo.bindingofisaac.module.Model.ObjetsInventaire;
import com.projetpo.bindingofisaac.module.Model.Personnage;
import com.projetpo.bindingofisaac.module.Shaders.Vector2;
import com.projetpo.bindingofisaac.module.Vue.Fenetre;

public class Input
{
	private static final Input INSTANCE = new Input();
	
	public final static float PI = (float) 3.141592;
    public final static float P2 = (float) (PI/2);
    
    private double x = Jeu.Isaac.getPosition().getY();
	private double y = Jeu.Isaac.getPosition().getX();
	private double a = Jeu.Isaac.getA();
	
	private final int[] listeInput = {GLFW.GLFW_KEY_A, GLFW.GLFW_KEY_D, GLFW.GLFW_KEY_W, GLFW.GLFW_KEY_S, GLFW.GLFW_KEY_I, GLFW.GLFW_KEY_L, GLFW.GLFW_KEY_K, GLFW.GLFW_KEY_P,GLFW.GLFW_KEY_O,
			GLFW.GLFW_KEY_SPACE, GLFW.GLFW_KEY_UP, GLFW.GLFW_KEY_DOWN, GLFW.GLFW_KEY_RIGHT, GLFW.GLFW_KEY_LEFT};
	
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
			checkDeplacement(Fenetre.getInstance().getWindow(), key);
		}
		Jeu.Isaac.updateGameObject();
	}
	
	public void tire()
	{
		for(Integer key:listeInput)
		{
			checkTire(Fenetre.getInstance().getWindow(), key);
		}
	}
	
	/**
	 * M�thode qui v�rifie si une touche a �t� press�e.
	 * Si oui renvoie vers la m�thode getAWSDkeys(window).
	 * Si non, d�finie l'entr�e r�manente � faux.
	 * @param window
	 * La fen�tre active.
	 * @param key
	 * La touche d�tect�e � v�rifier.
	 */
	public void checkDeplacement(long window, int key)
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
	
	public void checkTire(long window, int key)
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
	
	public void poseBomb() {
		ObjetsInventaire bomb = new ObjetsInventaire(-1, 100, 100, Jeu.gameWorld.getPlayer().getPosition(), "");
	}
	
	public void shootUp()
	{
		Jeu.Isaac.getMunitions().addBalle(new Balle(25, 25, Jeu.Isaac.getPosition().getX(), Jeu.Isaac.getPosition().getY(), new Vector2(0, 1), "", 10));
		Jeu.gameWorld.getPlayer().setFace(1);
	}
	
	public void shootDown()
	{
		Jeu.Isaac.getMunitions().addBalle(new Balle(25, 25, Jeu.Isaac.getPosition().getX(), Jeu.Isaac.getPosition().getY(), new Vector2(0, -1), "", 10));
		Jeu.gameWorld.getPlayer().setFace(2);
	}
	
	public void shootRight()
	{
		Jeu.Isaac.getMunitions().addBalle(new Balle(25, 25, Jeu.Isaac.getPosition().getX(), Jeu.Isaac.getPosition().getY(), new Vector2(1, 0), "", 10));
		Jeu.gameWorld.getPlayer().setFace(3);
	}
	
	public void shootLeft()
	{
		Jeu.Isaac.getMunitions().addBalle(new Balle(25, 25, Jeu.Isaac.getPosition().getX(), Jeu.Isaac.getPosition().getY(), new Vector2(-1, 0), "", 10));
		Jeu.gameWorld.getPlayer().setFace(4);
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
		if(glfwGetKey(window, GLFW_KEY_L) == GLFW.GLFW_PRESS)
		{
			Jeu.gameWorld.getPlayer().setSpeed(20);
			glfwSetInputMode(window, GLFW_STICKY_KEYS, GLFW_TRUE);
		}
		if(glfwGetKey(window, GLFW_KEY_I) == GLFW.GLFW_PRESS)
		{
			Jeu.gameWorld.getPlayer().setInvincible(true);
			glfwSetInputMode(window, GLFW_STICKY_KEYS, GLFW_TRUE);
		}
		if(glfwGetKey(window, GLFW_KEY_K) == GLFW.GLFW_PRESS)
		{
			Jeu.gameWorld.getMapEnCours().setListeEnnemi(new listeEnnemi());
			glfwSetInputMode(window, GLFW_STICKY_KEYS, GLFW_TRUE);
		}
		if(glfwGetKey(window, GLFW_KEY_P) == GLFW.GLFW_PRESS)
		{
			Jeu.gameWorld.getPlayer().setDegat(Integer.MAX_VALUE);
			glfwSetInputMode(window, GLFW_STICKY_KEYS, GLFW_TRUE);
		}
		if(glfwGetKey(window, GLFW_KEY_O) == GLFW.GLFW_PRESS)
		{
			Jeu.gameWorld.getPlayer().setCoin(Jeu.gameWorld.getPlayer().getCoin() + 10); //TODO pas fini
			glfwSetInputMode(window, GLFW_STICKY_KEYS, GLFW_TRUE);
		}
		if(glfwGetKey(window, GLFW_KEY_SPACE) == GLFW.GLFW_PRESS)
		{
			poseBomb();
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
	
	public void init(long window)
	{
		
	}

	public static Input getInstance() {
		return INSTANCE;
	}
}