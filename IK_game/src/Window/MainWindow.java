package Window;

import static org.lwjgl.opengl.GL11.*;

import java.awt.Color;
import java.awt.Toolkit;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import Game.Game;
import Game.GameInfo;
import KeyEvents.Key;
import KeyEvents.KeyRegister;
import Resources.Texture;
import Resources.TextureLoader;
import Scene.GlobalScene;

public class MainWindow
{
	public int width = 0;
	public int height = 0;
	public boolean fullScreen = false;
	
	public float[] color = {0f,0f,0f,0f};
	public Color colorObj = new Color(0f,0f,0f,0f);
	
	public String title = GameInfo.getGameFullestName();
	
	public Display display;
	
	public MainWindow()
	{
		try
		{
			init();
		}
		catch (LWJGLException e)
		{
			System.err.println("Can't create window (openGL)");
			e.printStackTrace();
			System.exit(-1);
		}
		
		initOpenGL();
	}
	
	private void init() throws LWJGLException
	{
		fullScreen = Game.theGame.gameSettingSaver.getBoolean("FullScreen");
		if(fullScreen)
		{
			width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
			height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
			Display.setDisplayModeAndFullscreen(Display.getDesktopDisplayMode());
		}
		else
		{
			width = Game.theGame.gameSettingSaver.getInt("ScreenWidth");
			height = Game.theGame.gameSettingSaver.getInt("ScreenHeight");
			
			DisplayMode mode = new DisplayMode(width, height);
			display.setDisplayMode(mode);
		}

		display.setTitle(title);
		//display.setResizable(true);
		display.create();
		
		TextureLoader.updateTexturesOpenGL();
	}
	private void initOpenGL()
	{
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, Display.getWidth(), Display.getHeight(), 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
	    glEnable(GL_ALPHA);
	}
	
	public void keyboardUpdate()
	{
		for(int i = 0; i < Keyboard.KEYBOARD_SIZE; i++)
		{
			Key key = KeyRegister.getKeyByKeyCodeAndKeyName(i, Keyboard.getKeyName(i));
			key.pressed = keyPressed(Game.theGame.Scene, key, key.pressed);
		}
	}
	
	private boolean keyPressed(GlobalScene scene, Key key, boolean wasPressed)
	{
		if(Keyboard.isKeyDown(key.keyCode) && !wasPressed)
		{
			scene.onKeyPress(key.keyCode, key.keyName);
			return true;
		}
		if(!Keyboard.isKeyDown(key.keyCode) && wasPressed)
		{
			scene.onKeyRelease(key.keyCode, key.keyName);
			return false;
		}
		
		return wasPressed;
	}
	
	private boolean MOUSE_BTN_DOWN_0 = false;
	private boolean MOUSE_BTN_DOWN_1 = false;
	private boolean MOUSE_BTN_DOWN_2 = false;
	private boolean MOUSE_BTN_DOWN_3 = false;
	private boolean MOUSE_BTN_DOWN_4 = false;
	
	public void mouseUpdate()
	{
		if(Game.theGame.Scene == null) return;
		
		GlobalScene scene = Game.theGame.Scene;
		
		int mX = Mouse.getX();
		int mY = this.height - Mouse.getY();
		
		if(Mouse.getDX() != 0 || Mouse.getDY() != 0) scene.onMouseMove(mX, mY, Mouse.getDX(), Mouse.getDY());
		
		MOUSE_BTN_DOWN_0 = checkMouseButton(mX, mY, 0, MOUSE_BTN_DOWN_0, scene);
		MOUSE_BTN_DOWN_1 = checkMouseButton(mX, mY, 1, MOUSE_BTN_DOWN_1, scene);
		MOUSE_BTN_DOWN_2 = checkMouseButton(mX, mY, 2, MOUSE_BTN_DOWN_2, scene);
		MOUSE_BTN_DOWN_3 = checkMouseButton(mX, mY, 3, MOUSE_BTN_DOWN_3, scene);
		MOUSE_BTN_DOWN_4 = checkMouseButton(mX, mY, 4, MOUSE_BTN_DOWN_4, scene);
		
		if(Mouse.getDWheel() != 0) scene.onMouseWheelMoved(Mouse.getDWheel());
	}
	
	private boolean checkMouseButton(int mX, int mY, int btn, boolean wasPressed, GlobalScene scene)
	{
		if(Mouse.isButtonDown(btn) && !wasPressed)
		{
			scene.onMousePress(mX, mY, btn);
			return true;
		}
		if(!Mouse.isButtonDown(btn) && wasPressed)
		{
			scene.onMouseRelease(mX, mY, btn);
			return false;
		}
		
		return wasPressed;
	}
	
	public void render()
	{
		glClear(GL_COLOR_BUFFER_BIT);
		clear();
		Game.theGame.draw();
		display.update();
	}
	
	public void clear()
	{
		Texture rect = TextureLoader.getTextureByName("!003" + width + "&" + height + "&255&255&255&255");
		this.drawTexture(rect, 0, 0);
	}
	
	public void acceptColor()
	{
		glColor4f(color[0], color[1], color[2], color[3]);
	}
	
	public void setColorF(float r, float g, float b, float a)
	{
		if(r > 1F) r = 1F;
		if(g > 1F) g = 1F;
		if(b > 1F) b = 1F;
		if(a > 1F) a = 1F;
		if(r < 0F) r = 0F;
		if(g < 0F) g = 0F;
		if(b < 0F) b = 0F;
		if(a < 0F) a = 0F;
		
		color[0] = r;
		color[1] = g;
		color[2] = b;
		color[3] = a;
		
		colorObj = new Color(r,g,b,a);
	}
	
	public void setColor(Color c)
	{
		this.setColorF((float)c.getRed() / 255f, (float)c.getGreen() / 255f, (float)c.getBlue() / 255f, (float)c.getAlpha() / 255f);
	}

	public void setColorD(double r, double g, double b, double a)
	{
		this.setColorF((float)r, (float)g, (float)b, (float)a);
	}
	
	public void drawString(String str, int x, int y)
	{
		int xx = 0;
		
		for(int i = 0; i < str.length(); i++)
		{
			Texture text = TextureLoader.getTextureByName("!004" + str.substring(i, i+1) + "&" + colorObj.getRGB());
			this.drawTexture(text, x+xx, y - text.height);
			x+=text.width;
		}
	}
	
	public void drawText(String str, int x, int y, int width, int height)
	{
		if(str.length() == 0) return;
		int xx = 0;
		int w = width/str.length();
		
		for(int i = 0; i < str.length(); i++)
		{
			Texture text = TextureLoader.getTextureByName("!002" + str.substring(i, i+1));
			this.drawTexture(text, x+xx, y);
			x+=text.width;
		}
	}
	
	public void drawLine(int x1, int y1, int x2, int y2)
	{
		acceptColor();
		
        glBegin(GL_LINES);
			glVertex2i(x1, y1);
			glVertex2i(x2, y2);
		glEnd();
	}
	
	public void drawRect(int x, int y, int w, int h)
	{
		drawLine(x,y,x+w,y);
		drawLine(x+w,y,x+w,y+h);
		drawLine(x+w,y+h,x,y+h);
		drawLine(x,y+h,x,y);
	}
	
	public void fillRect(int x, int y, int w, int h)
	{
		Texture rect = TextureLoader.getTextureByName("!003" + w + "&" + h + "&" + (int)(color[0]*255f) + "&" + (int)(color[1]*255f) + "&" + (int)(color[2]*255f) + "&" + (int)(color[3]*255f));
		this.drawTexture(rect, x, y);
		
		/*acceptColor();
		
        glBegin(GL_QUADS);
			glVertex2f(x, y);
			glVertex2f(x, y + h);
			glVertex2f(x + w, y + h);
			glVertex2f(x + w, y);
		glEnd();*/
	}

	public void destroy()
	{
		display.destroy();
	}

	public void drawTexture(Texture texture, int x, int y, int w, int h)
	{
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		
		this.setColorF(1f, 1f, 1f, 1f);
		this.acceptColor();
		
		texture.bind();
		
        glBegin(GL_QUADS);
			glTexCoord2f(0, 0);
			glVertex2f(x, y);
			glTexCoord2f(texture.U, 0);
			glVertex2f(x, y + h);
			glTexCoord2f(texture.U, texture.V);
			glVertex2f(x + w, y + h);
			glTexCoord2f(0, texture.V);
			glVertex2f(x + w, y);
		glEnd();
		
		glDisable(GL_BLEND);
	}

	public void drawTexture(Texture texture, int x, int y)
	{
		if(texture.width == 0 || texture.height == 0) return;
		this.drawTexture(texture, x, y, (int)(texture.image.getWidth() * texture.V), (int)(texture.image.getHeight() * texture.U));
	}
}





















