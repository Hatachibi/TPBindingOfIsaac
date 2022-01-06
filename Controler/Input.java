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
import com.projetpo.bindingofisaac.module.Model.Bombe;
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
    
    public Personnage player = Jeu.gameWorld.getPlayer();
    
    private double x = player.getPosition().getY();
	private double y = player.getPosition().getX();
	private double a = player.getA();
	
	private final int[] listeInput = {GLFW.GLFW_KEY_A, GLFW.GLFW_KEY_D, GLFW.GLFW_KEY_W, GLFW.GLFW_KEY_S, GLFW.GLFW_KEY_I, GLFW.GLFW_KEY_L, GLFW.GLFW_KEY_K, GLFW.GLFW_KEY_P,GLFW.GLFW_KEY_O,
			GLFW.GLFW_KEY_E,GLFW.GLFW_KEY_ENTER, GLFW.GLFW_KEY_SPACE, GLFW.GLFW_KEY_UP, GLFW.GLFW_KEY_DOWN, GLFW.GLFW_KEY_RIGHT, GLFW.GLFW_KEY_LEFT};
	
	public void drawBalle() 
	{
		player.getMunitions().drawBalle();
	}

	public Personnage getPlayerMove() {
		return player;
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
		player.updateGameObject();
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
		if(!player.getHitbox().isZCollision()) {
			player.goUpNext();
		}
		player.setA(PI/2);
		player.drawPlayer();
	}
	
	public void moveDown()
	{
		if(!player.getHitbox().isSCollision()) {
			player.goDownNext();
		}
		player.setA(3*PI/2);
		player.drawPlayer();
	}
	
	public void moveRight()
	{
		a = 0;
		if(!player.getHitbox().isDCollision()) {
			player.goRightNext();
		}
		player.setA(0);
		player.drawPlayer();
	}
	
	public void moveLeft()
	{
		a = PI;
		if(!player.getHitbox().isQCollision()) {
			player.goLeftNext();
		}
		player.setA(PI);
		player.drawPlayer();
	}
	
	public void poseBombe() {
		if((!Jeu.gameWorld.getMapEnCours().getBombList().isEmpty() && !Jeu.gameWorld.getMapEnCours().getBombList().getLast().isPoseRecently()) || Jeu.gameWorld.getMapEnCours().getBombList().isEmpty()) {
			if(player.getInv().getNbBombe() - 1 >= 0) {
				Bombe bombe = new Bombe(-3, 20, 20 ,player.getPosition(),"src/main/resources/Bomb.png");
				Jeu.gameWorld.getMapEnCours().getBombList().add(bombe);
				player.getInv().setNbBombe(player.getInv().getNbBombe()-1);
			}
		}
	}
	
	public void shootUp()
	{
		player.getMunitions().addBalle(new Balle(25, 25, player.getPosition().getX(), player.getPosition().getY(), new Vector2(0, 1), "src/main/resources/tear.png", 10));
		player.setFace(1);
		player.setShot(true);
	}
	
	public void shootDown()
	{
		player.getMunitions().addBalle(new Balle(25, 25, player.getPosition().getX(), player.getPosition().getY(), new Vector2(0, -1), "src/main/resources/tear.png", 10));
		player.setFace(2);
		player.setShot(true);
	}
	
	public void shootRight()
	{
		player.getMunitions().addBalle(new Balle(25, 25, player.getPosition().getX(), player.getPosition().getY(), new Vector2(1, 0), "src/main/resources/tear.png", 10));
		player.setFace(3);
		player.setShot(true);
	}
	
	public void shootLeft()
	{
		player.getMunitions().addBalle(new Balle(25, 25, player.getPosition().getX(), player.getPosition().getY(), new Vector2(-1, 0), "src/main/resources/tear.png", 10));
		player.setFace(4);
		player.setShot(true);
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
			player.setMoving(true);
		}
		if(glfwGetKey(window, GLFW_KEY_S) == GLFW.GLFW_PRESS)
		{
			moveDown();
			glfwSetInputMode(window, GLFW_STICKY_KEYS, GLFW_TRUE);
			player.setMoving(true);
		}
		if(glfwGetKey(window, GLFW_KEY_D) == GLFW.GLFW_PRESS)
		{
			moveRight();
			glfwSetInputMode(window, GLFW_STICKY_KEYS, GLFW_TRUE);
			player.setMoving(true);
		}
		if(glfwGetKey(window, GLFW_KEY_A) == GLFW.GLFW_PRESS)
		{
			moveLeft();
			glfwSetInputMode(window, GLFW_STICKY_KEYS, GLFW_TRUE);
			player.setMoving(true);
		}
		if(glfwGetKey(window, GLFW_KEY_L) == GLFW.GLFW_PRESS)
		{
			player.setSpeed(20);
			glfwSetInputMode(window, GLFW_STICKY_KEYS, GLFW_TRUE);
		}
		if(glfwGetKey(window, GLFW_KEY_I) == GLFW.GLFW_PRESS)
		{
			player.setInvincible(true);
			glfwSetInputMode(window, GLFW_STICKY_KEYS, GLFW_TRUE);
		}
		if(glfwGetKey(window, GLFW_KEY_K) == GLFW.GLFW_PRESS)
		{
			Jeu.gameWorld.getMapEnCours().setListeEnnemi(new listeEnnemi());
			glfwSetInputMode(window, GLFW_STICKY_KEYS, GLFW_TRUE);
		}
		if(glfwGetKey(window, GLFW_KEY_P) == GLFW.GLFW_PRESS)
		{
			player.setDegat(Integer.MAX_VALUE);
			glfwSetInputMode(window, GLFW_STICKY_KEYS, GLFW_TRUE);
		}
		if(glfwGetKey(window, GLFW_KEY_O) == GLFW.GLFW_PRESS)
		{
			player.setCoin(player.getCoin() + 10); //TODO pas fini
			glfwSetInputMode(window, GLFW_STICKY_KEYS, GLFW_TRUE);
		}
		if(glfwGetKey(window, GLFW.GLFW_KEY_E) == GLFW.GLFW_PRESS)
		{
			poseBombe();
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