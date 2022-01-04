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
    
    private double x = Jeu.gameWorld.getPlayer().getPosition().getY();
	private double y = Jeu.gameWorld.getPlayer().getPosition().getX();
	private double a = Jeu.gameWorld.getPlayer().getA();
	
	private final int[] listeInput = {GLFW.GLFW_KEY_A, GLFW.GLFW_KEY_D, GLFW.GLFW_KEY_W, GLFW.GLFW_KEY_S, GLFW.GLFW_KEY_I, GLFW.GLFW_KEY_L, GLFW.GLFW_KEY_K, GLFW.GLFW_KEY_P,GLFW.GLFW_KEY_O,
			GLFW.GLFW_KEY_ENTER, GLFW.GLFW_KEY_SPACE, GLFW.GLFW_KEY_UP, GLFW.GLFW_KEY_DOWN, GLFW.GLFW_KEY_RIGHT, GLFW.GLFW_KEY_LEFT};
	
	public void drawBalle() 
	{
		Jeu.gameWorld.getPlayer().getMunitions().drawBalle();
	}

	public Personnage getPlayerMove() {
		return Jeu.gameWorld.getPlayer();
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
		Jeu.gameWorld.getPlayer().updateGameObject();
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
		if(!Jeu.gameWorld.getPlayer().getHitbox().isZCollision()) {
			Jeu.gameWorld.getPlayer().goUpNext();
		}
		Jeu.gameWorld.getPlayer().setA(PI/2);
		Jeu.gameWorld.getPlayer().drawPlayer();
	}
	
	public void moveDown()
	{
		if(!Jeu.gameWorld.getPlayer().getHitbox().isSCollision()) {
			Jeu.gameWorld.getPlayer().goDownNext();
		}
		Jeu.gameWorld.getPlayer().setA(3*PI/2);
		Jeu.gameWorld.getPlayer().drawPlayer();
	}
	
	public void moveRight()
	{
		a = 0;
		if(!Jeu.gameWorld.getPlayer().getHitbox().isDCollision()) {
			Jeu.gameWorld.getPlayer().goRightNext();
		}
		Jeu.gameWorld.getPlayer().setA(0);
		Jeu.gameWorld.getPlayer().drawPlayer();
	}
	
	public void moveLeft()
	{
		a = PI;
		if(!Jeu.gameWorld.getPlayer().getHitbox().isQCollision()) {
			Jeu.gameWorld.getPlayer().goLeftNext();
		}
		Jeu.gameWorld.getPlayer().setA(PI);
		Jeu.gameWorld.getPlayer().drawPlayer();
	}
	
	public void poseBomb() {
		ObjetsInventaire bomb = new ObjetsInventaire(-1, 100, 100, Jeu.gameWorld.getPlayer().getPosition(), "");
	}
	
	public void shootUp()
	{
		Jeu.gameWorld.getPlayer().getMunitions().addBalle(new Balle(25, 25, Jeu.gameWorld.getPlayer().getPosition().getX(), Jeu.gameWorld.getPlayer().getPosition().getY(), new Vector2(0, 1), "src/main/resources/tear.png", 10));
		Jeu.gameWorld.getPlayer().setFace(1);
		Jeu.gameWorld.getPlayer().setShot(true);
	}
	
	public void shootDown()
	{
		Jeu.gameWorld.getPlayer().getMunitions().addBalle(new Balle(25, 25, Jeu.gameWorld.getPlayer().getPosition().getX(), Jeu.gameWorld.getPlayer().getPosition().getY(), new Vector2(0, -1), "src/main/resources/tear.png", 10));
		Jeu.gameWorld.getPlayer().setFace(2);
		Jeu.gameWorld.getPlayer().setShot(true);
	}
	
	public void shootRight()
	{
		Jeu.gameWorld.getPlayer().getMunitions().addBalle(new Balle(25, 25, Jeu.gameWorld.getPlayer().getPosition().getX(), Jeu.gameWorld.getPlayer().getPosition().getY(), new Vector2(1, 0), "src/main/resources/tear.png", 10));
		Jeu.gameWorld.getPlayer().setFace(3);
		Jeu.gameWorld.getPlayer().setShot(true);
	}
	
	public void shootLeft()
	{
		Jeu.gameWorld.getPlayer().getMunitions().addBalle(new Balle(25, 25, Jeu.gameWorld.getPlayer().getPosition().getX(), Jeu.gameWorld.getPlayer().getPosition().getY(), new Vector2(-1, 0), "src/main/resources/tear.png", 10));
		Jeu.gameWorld.getPlayer().setFace(4);
		Jeu.gameWorld.getPlayer().setShot(true);
	}
	
	public boolean choosePersoTemp(long window) {
		if(glfwGetKey(window, GLFW_KEY_RIGHT) == GLFW.GLFW_PRESS)
		{
			glfwSetInputMode(window, GLFW_STICKY_KEYS, GLFW_TRUE);
			return true;
		}
		if(glfwGetKey(window, GLFW_KEY_LEFT) == GLFW.GLFW_PRESS)
		{
			glfwSetInputMode(window, GLFW_STICKY_KEYS, GLFW_TRUE);
			return true;
		}
		return false;
	}
	
	public boolean valid(long window) {
		if(glfwGetKey(window, GLFW.GLFW_KEY_ENTER) == GLFW.GLFW_PRESS)
		{
			glfwSetInputMode(window, GLFW_STICKY_KEYS, GLFW_TRUE);
			return true;
		}
		return false;
	}
	
	public void getAWSDkeys(long window)
	{
		if(glfwGetKey(window, GLFW_KEY_W) == GLFW.GLFW_PRESS)
		{
			moveUp();
			glfwSetInputMode(window, GLFW_STICKY_KEYS, GLFW_TRUE);
			Jeu.gameWorld.getPlayer().setMoving(true);
		}
		if(glfwGetKey(window, GLFW_KEY_S) == GLFW.GLFW_PRESS)
		{
			moveDown();
			glfwSetInputMode(window, GLFW_STICKY_KEYS, GLFW_TRUE);
			Jeu.gameWorld.getPlayer().setMoving(true);
		}
		if(glfwGetKey(window, GLFW_KEY_D) == GLFW.GLFW_PRESS)
		{
			moveRight();
			glfwSetInputMode(window, GLFW_STICKY_KEYS, GLFW_TRUE);
			Jeu.gameWorld.getPlayer().setMoving(true);
		}
		if(glfwGetKey(window, GLFW_KEY_A) == GLFW.GLFW_PRESS)
		{
			moveLeft();
			glfwSetInputMode(window, GLFW_STICKY_KEYS, GLFW_TRUE);
			Jeu.gameWorld.getPlayer().setMoving(true);
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