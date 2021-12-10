package Controler;

import static org.lwjgl.glfw.GLFW.*;

import java.util.TreeSet;

import org.lwjgl.glfw.*;

import Model.Balle;
import Model.Jeu;
import Shaders.Raycasting;
import Vue.Render;

public class Input
{
	private static final Input INSTANCE = new Input();
		
	private boolean keys[];
	private boolean oldKeys[];
	private boolean buttons[];
	private boolean oldButtons[];
	private double mouseX;
	private double mouseY;
	private double scrollX;
	private double scrollY;
	private int textChar; 
	private TreeSet<Integer> currentKeysDown; 
	
	private GLFWKeyCallback keyboard;
	private GLFWKeyCallback keyCallBack;
	            
	private GLFWMouseButtonCallback button;
	private GLFWCursorPosCallback mouseMotion;
	private GLFWScrollCallback scroll;
	private GLFWCharCallback text;
	
	private boolean updateKeys;
	private boolean updateButtons;
	private boolean updateScroll;
	private boolean updateText;
	
	private float speed = 3;
	
	private double x = Jeu.Isaac.getDeplacement().getHit().getEntity().getX();
	private double y = Jeu.Isaac.getDeplacement().getHit().getEntity().getY();
	private double a = Jeu.Isaac.getDeplacement().getA();
	
    public final static float PI = (float) 3.141592;
    public final static float P2 = (float) (PI/2);
    public final static Float RADIAN = 0.0174533f;
	
	public void drawBalle() {
		Jeu.Isaac.getMunitions().drawBalle();
	}
    
	public DeplacerPersonnage getPlayerMove() {
		return Jeu.Isaac.getDeplacement();
	}
	
	private Input()
	{
		keys = new boolean[GLFW_KEY_LAST];
		oldKeys = new boolean[GLFW_KEY_LAST];
		buttons = new boolean[GLFW_MOUSE_BUTTON_LAST];
		oldButtons = new boolean[GLFW_MOUSE_BUTTON_LAST];
		//currentKeysDown = new TreeSet<Integer>();
		keyboard = new GLFWKeyCallback()
		{
			public void invoke(long window, int key, int scancode, int action, int mods)
			{
				System.out.println(key);
				if(action == GLFW.GLFW_RELEASE)
				{
					System.out.println("released");
					oldKeys[key] = true;
					keys[key] = false;
				}
				else if(action == GLFW.GLFW_PRESS)
				{
					System.out.println("Pressed");
					//getAWSDkeys(key);
					//getShotsKeys(key);
					oldKeys[key] = false;
					keys[key] = true;
					getAWSDkeys();
					getShotsKeys(key);
					
					
				}
				else
				{
					System.out.println("hold");
					//getAWSDkeys(key);
					getAWSDkeys();
					//getShotsKeys();
					getShotsKeys(key);
				}
				updateKeys = true;
			}
		};
		
		
		button = new GLFWMouseButtonCallback()
		{
			public void invoke(long window, int button, int action, int mods)
			{
				buttons[button] = (action != GLFW_RELEASE);
				updateButtons = true;
			}
		};

		mouseMotion = new GLFWCursorPosCallback()
		{
			public void invoke(long window, double xpos, double ypos)
			{
				mouseX = xpos;
				mouseY = ypos;
			}
		};
		
		scroll = new GLFWScrollCallback()
		{
			public void invoke(long window, double offsetx, double offsety)
			{
				scrollX += offsetx;
				scrollY += offsety;
				updateScroll = true;
			}			
		};
		
		text = new GLFWCharCallback()
		{
			public void invoke(long window, int text)
			{
				textChar = text;
				updateText = true;
			}
			
		};
	}
	
	public static Input getInstance()
	{
		return INSTANCE;
	}
	
	public void init(long window)
	{
		glfwSetKeyCallback(window, keyboard);
		glfwSetMouseButtonCallback(window, button);
		glfwSetCursorPosCallback(window, mouseMotion);
		glfwSetScrollCallback(window, scroll);
		glfwSetCharCallback(window, text);
	}
	
	public void handleEvents()
	{
		if(updateKeys)
		{
			for(int i = 0; i < keys.length; i++)
			{
				oldKeys[i] = keys[i];
			}
			updateKeys = false;
		}
		
		if(updateButtons)
		{
			
			for(int i = 0; i < buttons.length; i++)
			{
				oldButtons[i] = buttons[i];
			}
			updateButtons = false;
		}
		
		if(updateScroll)
		{
			scrollX = 0;
			scrollY = 0;
			updateScroll = false;
		}
		
		if(updateText)
		{
			textChar = 	0;
			updateText = false;
		}
	}
	
	/*private void playerHitbox()
	{
		int xo = 0;
		if(Math.cos(a)<0)
		{
			xo =- 20;
		}
		else
		{
			xo=20;
		}
		int yo = 0;
		if(-Math.sin(a)<0)
		{
			yo =- 20;
		}
		else
		{
			yo=20;
		}
		int ipx = (int) (x/64f);
		int ipx_add_xo = (int) ((x+xo)/64f);
		int ipx_sub_xo = (int) ((x-xo)/64f);
		int ipy = (int) (x/64f);
		int ipy_add_yo = (int) ((y+yo)/64f);
		int ipy_sub_yo = (int) ((y-yo)/64f);
	}*/
	
	public void getAWSDkeys()
	{
		int[] listeInput = {GLFW.GLFW_KEY_A, GLFW.GLFW_KEY_D, GLFW.GLFW_KEY_W, GLFW.GLFW_KEY_S};
		for(int i : listeInput)
		{
			if(keys[i])
			{
				switch(i)
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
		
	}
	
	public void getShotsKeys(int action)
	{
		switch(action)
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
		
	public boolean isKeyDown(int key)
	{
		return keys[key];
	}

	public boolean isKeyPressed(int key)
	{
		return keys[key] && !oldKeys[key];
	}
	
	public boolean isKeyReleased(int key)
	{
		return !keys[key] && oldKeys[key];
	}
	
	public boolean isButtonDown(int button)
	{
		return buttons[button];
	}
	
	public boolean IsButtonPressed(int button)
	{
		return buttons[button] && !oldButtons[button];
	}
	
	public boolean isButtonReleased(int button)
	{
		return !buttons[button] && oldButtons[button];
	}

	public double getScrollX()
	{
		return scrollX;
	}

	public double getScrollY()
	{
		return scrollY;
	}
	
	public double getMouseX()
	{
		return mouseX;
	}
	
	public double getMouseY()
	{
		return mouseY;
	}
	
	public char getChar()
	{
		return (char)textChar;
	}
	
	public void free()
	{
		keyboard.free();
		button.free();
		mouseMotion.free();
		scroll.free();
		text.free();	
	}
}