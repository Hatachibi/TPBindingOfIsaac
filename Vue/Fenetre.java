package Vue;

import static org.lwjgl.glfw.GLFW.*;

import java.util.*;

import org.lwjgl.glfw.GLFWVidMode;
import Controler.Input;
import Model.Jeu;
import Model.Room;


/**
 * 
 */
public class Fenetre {
	
	public final static Fenetre INSTANCE = new Fenetre();  
	public final static Integer HeigthFenetre = 585;
	public final static Integer	WidthFenetre = 585;

	private long window;

    /**
     * Default constructor
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
    	this.window = glfwCreateWindow(WidthFenetre, HeigthFenetre, "My window", 0, 0);
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
    			canRender = true;
    			glfwPollEvents();
        		if(frameTime >= 1.0) {
        			frameTime = 0;
        			System.out.println("FPS: " + frames);  
        			frames = 0;
        		}
        		if(!Jeu.Isaac.getMunitions().isNotShot()) {
    				Jeu.Isaac.getMunitions().setCoolDown(Jeu.Isaac.getMunitions().getCoolDown()+1);
    				if(Jeu.Isaac.getMunitions().getCoolDown() == 30) {
    					Jeu.Isaac.getMunitions().setCoolDown(0);
    					Jeu.Isaac.getMunitions().setShot(true);
    				};
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