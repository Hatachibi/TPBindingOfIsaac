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
import com.projetpo.bindingofisaac.module.Shaders.Vector2;

public class Fenetre {
	
	public final static Fenetre INSTANCE = new Fenetre();  
	public final static Integer HeigthFenetre = 585;
	public final static Integer	WidthFenetre = 585;
	
	public static int tick;

	private long window;
	
	private int state;
	private int playerChoice = 0;

    /**
     * Constructeur
     */
    private Fenetre() {}
    
    public static Fenetre getInstance() {
    	return INSTANCE;
    }
    
    public void init() {
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
    	Render.getInstance().drawPicture(0, 0, 585, 585);
    	Texture.bgMenu.unbind();
    	Texture.leftArrow.bind();
    	Render.getInstance().drawPicture(585/4, 585/2, Texture.leftArrow.getWidth(), Texture.leftArrow.getHeight());
    	Texture.leftArrow.unbind();
    	Texture.rightArrow.bind();
    	Render.getInstance().drawPicture(3*585/4, 585/2, Texture.rightArrow.getWidth(), Texture.rightArrow.getHeight());
    	Texture.rightArrow.unbind();
    	switch(playerChoice) {
    		case 0:
    			Texture.IsaacMenu.bind();
	       		Render.getInstance().drawPicture((float)(585/2), (float)(585/2), Texture.IsaacMenu.getWidth(), Texture.IsaacMenu.getHeight());
	       		Texture.IsaacMenu.unbind();
	       		Texture.IsaacString.bind();
	       		Render.getInstance().drawPicture(585/2, 585/4, Texture.IsaacString.getWidth(), Texture.IsaacString.getHeight());
	       		Texture.IsaacString.unbind(); break;
    		case 1:
    			Texture.MagdaleneMenu.bind();
    	    	Render.getInstance().drawPicture((float)(585/2), (float)(585/2), Texture.MagdaleneMenu.getWidth(), Texture.MagdaleneMenu.getHeight());
    	    	Texture.MagdaleneMenu.unbind();
    	    	Texture.MagdaleneString.bind();
    	    	Render.getInstance().drawPicture(585/2, 585/4, Texture.MagdaleneString.getWidth(), Texture.MagdaleneString.getHeight());
    	    	Texture.MagdaleneString.unbind(); break;
    		}
    	if(Input.getInstance().choosePersoTemp(window) && tick%10 == 0) {
    		playerChoice++;
    		if(playerChoice > 1) {
    			playerChoice = 0;
    		}
    	}
    	if(Input.getInstance().valid(window)) {
    			switch(playerChoice) {
    			case 0: Jeu.gameWorld.getPlayer().setLife(new BarreDeVie(6)); Jeu.gameWorld.getPlayer().setRange(5);  Jeu.gameWorld.getPlayer().setName("Isaac"); break;
    			case 1: Jeu.gameWorld.getPlayer().setLife(new BarreDeVie(8)); Jeu.gameWorld.getPlayer().setRange(5);  Jeu.gameWorld.getPlayer().setName("Magdalene");break;
    		}
    		Jeu.gameWorld.initRoom(true);
    		this.setState(2);
    	}
    }
    
    public void run() {
    	
    	double frameCap = 1.0/60.0; //On CAP à 60 FPS
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
        		//	System.out.println("Argent d'Isaac: " + Jeu.gameWorld.getPlayer().getCoin());
        			frames = 0;
        		} 
        		
    		}
    		
    		if(canRender) {
    			switch(state) {
    				case 1: this.menu(); break;
    				case 2: Jeu.gameWorld.drawWorld();  break;
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

}