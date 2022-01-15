package com.projetpo.bindingofisaac.module.Vue;

import static org.lwjgl.glfw.GLFW.GLFW_FALSE;
import static org.lwjgl.glfw.GLFW.GLFW_VISIBLE;
import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.glfw.GLFW.glfwGetPrimaryMonitor;
import static org.lwjgl.glfw.GLFW.glfwGetVideoMode;
import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSetWindowPos;
import static org.lwjgl.glfw.GLFW.glfwShowWindow;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.glfw.GLFW.glfwTerminate;
import static org.lwjgl.glfw.GLFW.glfwWindowHint;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;

import org.lwjgl.glfw.GLFWVidMode;

import com.projetpo.bindingofisaac.module.Controler.Input;
import com.projetpo.bindingofisaac.module.Model.BarreDeVie;
import com.projetpo.bindingofisaac.module.Model.Jeu;
import com.projetpo.bindingofisaac.module.Model.Personnage;
import com.projetpo.bindingofisaac.module.Ressource.RoomInfos;
import com.projetpo.bindingofisaac.module.Shaders.Vector2;

public class Fenetre {
	
	public final static Fenetre INSTANCE = new Fenetre();  
	public final static Integer HeigthFenetre = RoomInfos.NB_WIDTH_TILES*65;
	public final static Integer	WidthFenetre = RoomInfos.NB_HEIGHT_TILES*65;
	
	public int FPS;
	
	public static int tick;

	private long window;
	
	public int state;
	private int playerChoice = 0;

    /**
     * Constructeur
     */
    private Fenetre() {}
    
    public static Fenetre getInstance() {
    	return INSTANCE;
    }
    
    public void init() {
    	setFPS(60);
    	if(!glfwInit())
    	{
    		throw new IllegalStateException("Failed to initialize GLFW");
    	}
    	this.window = glfwCreateWindow(WidthFenetre, HeigthFenetre, "Harry Potter et la Chambre des secrets", 0, 0);
    	glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
    	Render.getInstance().init(window);
    }
    
    public long getWindow()
    {
    	return window;
    }
    
    public void create() {
    	
    	
    	if(window == 0)
    	{
    		throw new IllegalStateException("Failed to initialize window");

    	}
    	
    	GLFWVidMode videoMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
    	
  
    	glfwSetWindowPos(window, (videoMode.width() - Fenetre.WidthFenetre) / 2, (videoMode.height() - Fenetre.HeigthFenetre) /2);
    	glfwShowWindow(window);
    }
    
    public boolean isClosed() {
    	return glfwWindowShouldClose(window);
    }
    
    public static double getTime() {
    	return (double)System.nanoTime() / (double)1000000000L;
    }
    
    public void menu() {
    	Texture.bgMenu.bind();
    	Render.getInstance().drawPicture(0, 0, WidthFenetre, HeigthFenetre);
    	Texture.bgMenu.unbind();
    	Texture.leftArrow.bind();
    	Render.getInstance().drawPicture(HeigthFenetre/4, WidthFenetre/2, Texture.leftArrow.getWidth(), Texture.leftArrow.getHeight());
    	Texture.leftArrow.unbind();
    	Texture.rightArrow.bind();
    	Render.getInstance().drawPicture(3*HeigthFenetre/4, WidthFenetre/2, Texture.rightArrow.getWidth(), Texture.rightArrow.getHeight());
    	Texture.rightArrow.unbind();
    	switch(playerChoice) {
    		case 0:
    			Texture.IsaacMenu.bind();
	       		Render.getInstance().drawPicture((float)(HeigthFenetre/2), (float)(WidthFenetre/2), Texture.IsaacMenu.getWidth(), Texture.IsaacMenu.getHeight());
	       		Texture.IsaacMenu.unbind();
	       		Texture.IsaacString.bind();
	       		Render.getInstance().drawPicture(HeigthFenetre/2, WidthFenetre/4, Texture.IsaacString.getWidth(), Texture.IsaacString.getHeight());
	       		Texture.IsaacString.unbind(); break;
    		case 1:
    			Texture.MagdaleneMenu.bind();
    	    	Render.getInstance().drawPicture((float)(HeigthFenetre/2), (float)(WidthFenetre/2), Texture.MagdaleneMenu.getWidth(), Texture.MagdaleneMenu.getHeight());
    	    	Texture.MagdaleneMenu.unbind();
    	    	Texture.MagdaleneString.bind();
    	    	Render.getInstance().drawPicture(HeigthFenetre/2, WidthFenetre/4, Texture.MagdaleneString.getWidth(), Texture.MagdaleneString.getHeight());
    	    	Texture.MagdaleneString.unbind(); break;
    		}
    	if(Input.getInstance().choosePersoTemp(window) && tick%10 == 0) {
    		playerChoice++;
    		if(playerChoice > 1) {
    			playerChoice = 0;
    		}
    	}
    	Render.getInstance().drawText(500, 50, FPS+"");
    	if(Input.getInstance().valid(window)) {
    			switch(playerChoice) {
    			case 0: Jeu.gameWorld.getPlayer().setLife(new BarreDeVie(6)); Jeu.gameWorld.getPlayer().setRange(5);  Jeu.gameWorld.getPlayer().setName("Isaac"); break;
    			case 1: Jeu.gameWorld.getPlayer().setLife(new BarreDeVie(8)); Jeu.gameWorld.getPlayer().setRange(5);  Jeu.gameWorld.getPlayer().setName("Magdalene");break;
    		}
    		Jeu.gameWorld.initRoom(Jeu.gameWorld.DEFAULT_NB_ROOMS, Jeu.gameWorld.DEFAULT_NB_MAX_ROCKS, Jeu.gameWorld.DEFAULT_NB_MAX_SPIKES, Jeu.gameWorld.DEFAULT_NB_MAX_ENNEMIS);
    		this.setState(2);
    	}
    }
    
    public void run() {
    	
    	double frameCap = 1.0/FPS; //On CAP à 60 FPS
    	double timer = getTime();
    	double unprocessed = 0;
    	
    	double frameTime = 0;
    	int frames = 0;
    	
    	while(!Fenetre.getInstance().isClosed()) {
    		double timerLoop = getTime();
    		double passed = timerLoop - timer;
    		unprocessed += passed;
    		frameTime+=passed;
    		boolean canRender = false;
    		timer = timerLoop;
    		
    		while(unprocessed >= frameCap) {
    			frameCap = 1.0/FPS;
    			unprocessed -= frameCap;
    			switch(state) {
    				case 2: Jeu.gameWorld.updateWorld(); break;
    			}
    			tick++;
    			canRender = true;
    			glfwPollEvents();
        		if(frameTime >= 1.0) {
        			frameTime = 0;
        			tick=0;
        			System.out.println("FPS: " + frames);
        			frames = 0;
        		} 
        		
    		}
    		
    		if(canRender) {
    			switch(state) {
    				case 1: this.menu(); break;
    				case 2: Jeu.gameWorld.drawWorld();  break;
    				case 3: Jeu.gameWorld.deathScreen(); break;
    				case 4: Jeu.gameWorld.victoryScreen(); break;
    			}
        		glfwSwapBuffers(window);
        		frames++;
    		}
    	}
    	
    	glfwTerminate();
    }

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getTick() {
		return tick;
	}

	public int getPlayerChoice() {
		return playerChoice;
	}

	public void setPlayerChoice(int playerChoice) {
		this.playerChoice = playerChoice;
	}

	public static Integer getHeigthfenetre() {
		return HeigthFenetre;
	}

	public static Integer getWidthfenetre() {
		return WidthFenetre;
	}

	public void setWindow(long window) {
		this.window = window;
	}

	public int getFPS() {
		return FPS;
	}

	public void setFPS(int fPS) {
		FPS = fPS;
	}

}