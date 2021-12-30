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

import com.projetpo.bindingofisaac.module.Model.Jeu;

public class Fenetre {
	
	public final static Fenetre INSTANCE = new Fenetre();  
	public final static Integer HeigthFenetre = 585;
	public final static Integer	WidthFenetre = 585;
	
	public static int tick;

	private long window;

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
    			Jeu.room.updateRoom();
    			tick++;
    			canRender = true;
    			glfwPollEvents();
        		if(frameTime >= 1.0) {
        			frameTime = 0;
        			tick=0;
        			System.out.println("FPS: " + frames);
        			System.out.println("Argent d'Isaac: " + Jeu.room.getPlayer().getCoin());
        			frames = 0;
        		} 
        		
    		}
    		
    		if(canRender) {
    			Jeu.room.drawRoom();
        		glfwSwapBuffers(window);
        		frames++;
    		}
    	}
    	
    	glfwTerminate();
    }

}