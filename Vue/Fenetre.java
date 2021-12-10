package Vue;

import static org.lwjgl.glfw.GLFW.*;

import java.util.*;

import org.lwjgl.glfw.GLFWVidMode;

import Controler.DeplacerPersonnage;
import Controler.Input;


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
    	
    	double frameCap = 1.0/120.0; //On CAP � 60 FPS
    	double timer = getTime();
    	double unprocessed = 0;
    	
    	while(!Fenetre.getInstance().isClosed()) {
    		double timerLoop = getTime();
    		double passed = timerLoop - timer;
    		unprocessed += passed;
    		boolean canRender = false;
    		timer = timerLoop;
    		
    		while(unprocessed >= frameCap) {
    			unprocessed -= frameCap;
        		glfwPollEvents();
        		Input.getInstance().handleEvents();
        		canRender = true;
    		}
    		
    		if(canRender) {
    			Render.getInstance().render();
        		glfwSwapBuffers(window);
    		}	
    	}
    	
    	glfwTerminate();
    }

}