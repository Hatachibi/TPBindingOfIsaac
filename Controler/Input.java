package Controler;

import static org.lwjgl.glfw.GLFW.*;

import org.lwjgl.glfw.*;

import Model.Balle;
import Shaders.Raycasting;
import Vue.Render;

public class Input
{
	private static final Input INSTANCE = new Input();
	
	private DeplacerPersonnage playerMove = new DeplacerPersonnage(100, 100, 1, 1);
	
	private boolean keys[];
	private boolean oldKeys[];
	private boolean buttons[];
	private boolean oldButtons[];
	private double mouseX;
	private double mouseY;
	private double scrollX;
	private double scrollY;
	private int textChar; 
	
	private GLFWKeyCallback keyboard;
	private GLFWMouseButtonCallback button;
	private GLFWCursorPosCallback mouseMotion;
	private GLFWScrollCallback scroll;
	private GLFWCharCallback text;
	
	private boolean updateKeys;
	private boolean updateButtons;
	private boolean updateScroll;
	private boolean updateText;
	
	private float speed = 3;
	
	private float x = playerMove.getX();
	private float y = playerMove.getY();
	private float a = playerMove.getA();
	
    public final static float PI = (float) 3.141592;
    public final static float P2 = (float) (PI/2);
    public final static Float RADIAN = 0.0174533f;
	
	
	public DeplacerPersonnage getPlayerMove() {
		return playerMove;
	}
	
	private Input()
	{
		keys = new boolean[GLFW_KEY_LAST];
		oldKeys = new boolean[GLFW_KEY_LAST];
		buttons = new boolean[GLFW_MOUSE_BUTTON_LAST];
		oldButtons = new boolean[GLFW_MOUSE_BUTTON_LAST];
		
		keyboard = new GLFWKeyCallback()
		{
			public void invoke(long window, int key, int scancode, int action, int mods)
			{
				if(action != GLFW_RELEASE)
				{
					getAWSDkeys(key);
					getAction(key);
					keys[key] = true;
				}
				else
				{
					keys[key] = false;
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
	
	public void getAWSDkeys(int action)
	{
		
		switch(action)
		{
		case GLFW.GLFW_KEY_A:
			a = PI;
			if(!playerMove.getHit().isQCollision()) x -= speed;
			this.playerMove.update(x, y, a);
			this.playerMove.drawPlayer();
			break;
		case GLFW.GLFW_KEY_D:
			a = 0;
	    	if(!playerMove.getHit().isDCollision()) x += speed;
			this.playerMove.update(x, y, a);
			this.playerMove.drawPlayer();
			break;
		case GLFW.GLFW_KEY_W:
			a = PI/2;
			if(!playerMove.getHit().isZCollision()) y+=speed;
			this.playerMove.update(x, y, a);
			this.playerMove.drawPlayer();
			break;
		case GLFW.GLFW_KEY_S:
			a = 3*(PI/2);
			if(!playerMove.getHit().isSCollision()) y-= speed;
			this.playerMove.update(x, y, a);
			this.playerMove.drawPlayer();
			break;
		case GLFW.GLFW_KEY_RIGHT:
			this.getAction(action);
		}
	}
	
	public void getAction(int action) {
		if(action == GLFW.GLFW_KEY_RIGHT) {
			System.out.println("test");
			Balle balle = new Balle(1, 1, playerMove.getX(), playerMove.getY());
			balle.setShoot(true);
			balle.drawBalle();
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
