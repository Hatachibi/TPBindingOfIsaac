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
    	
    //	GLFWVidMode videoMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
    	
  
 //   	glfwSetWindowPos(window, (videoMode.width() - Fenetre.WidthFenetre) / 2, (videoMode.height() - Fenetre.HeigthFenetre) /2);
  //  	glfwShowWindow(window);
    }
    
    public boolean isClosed() {
    	return glfwWindowShouldClose(window);
    }
    
    public void run() {
    	
    	while(!Fenetre.getInstance().isClosed()) {
    		Input.getInstance().handleEvents();
    		Render.getInstance().render();
    		glfwPollEvents();
    		glfwSwapBuffers(window);
    	}
    	
    	glfwTerminate();
    }

}