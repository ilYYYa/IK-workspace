package Game;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import Obj.DrawbleObject;
import Resources.Saver;
import Scene.GlobalScene;
import Scene.Scene_Logo;
import Window.MainWindow;
import block.Block_Air;
import block.Blocks;
import util.Optimizator;
import world.World;

public class Game
{
	public static Game theGame;
	public GlobalScene Scene = new Scene_Logo();
	
	public Saver gameSettingSaver;
	
	public MainWindow theMainWindow;
	
	public World currentOpenedWorld = null;
	
	public boolean gameRunning = false;
	
	public long lastSaved = System.currentTimeMillis()/1000;
	
	public static int GAME_FPS = 60;
	
	public double fps = 3000;
	public double avrFps = 0;
	public ArrayList<Double> fpss = new ArrayList<Double>();
	
	public long globalTick = 0;
	public long avrGlobalTick = 0;
	public long maxGlobalTick = 0;
	public ArrayList<Long> globalTicks = new ArrayList<Long>();
	
	public long logicTick = 0;
	public long avrLogicTick = 0;
	public long maxLogicTick = 0;
	public ArrayList<Long> logicTicks = new ArrayList<Long>();
	
	public long renderTick = 0;
	public long avrRenderTick = 0;
	public long maxRenderTick = 0;
	public ArrayList<Long> renderTicks = new ArrayList<Long>();

	private long lastTickUpdate = 0;
	
	public static Block_Air air = (Block_Air) Blocks.AIR;
	
	public Game()
	{
		theGame = this;
		
		createSaver();
		createDefaultSettings();
		
		createWnindow();
		
		startGameRunning();
	}
	
	public void startGameRunning()
	{
		gameRunning = true;
		GameRunning();
	}

	public void stopGameRunning()
	{
		gameRunning = false;
		this.SaveSettings();
		if(this.currentOpenedWorld != null) this.currentOpenedWorld.getWorldSaver().initSave();
	}
	
	public void GameRunning()
	{
		while(gameRunning)
		{
			Optimizator.addLine();
			Optimizator.addPointToLatestLine("i1B");
			
			if(globalTick != 0)fps = 1_000_000_000D / (double)globalTick;
			else fps = 1000D;

			if(theMainWindow != null) theMainWindow.mouseUpdate();
			if(theMainWindow != null) theMainWindow.keyboardUpdate();
			long l1 = System.nanoTime();
			Optimizator.addPointToLatestLine("i2B");
			this.logic();
			Optimizator.addPointToLatestLine("i2E");
			long l2 = System.nanoTime();
			if(theMainWindow != null) theMainWindow.render();
			long l3 = System.nanoTime();

			fpss.add(fps);
			avrFps = this.getAVRD(fpss);
			
			globalTicks.add(globalTick);
			avrGlobalTick = (long) this.getAVRL(globalTicks);
			
			logicTick = l2-l1;
			logicTicks.add(logicTick);
			avrLogicTick = (long) this.getAVRL(logicTicks);
			
			renderTick = l3-l2;
			renderTicks.add(renderTick);
			avrRenderTick = (long) this.getAVRL(renderTicks);
			
			if(maxGlobalTick < globalTick) maxGlobalTick = globalTick;
			if(maxLogicTick < l2-l1) maxLogicTick = l2-l1;
			if(maxRenderTick < l3-l2) maxRenderTick = l3-l2;
			
			if(System.currentTimeMillis()/1000 % 10 == 0 && lastSaved != System.currentTimeMillis()/1000)
			{
				Optimizator.addPointToLatestLine("S1B");
				this.SaveSettings();
				Optimizator.addPointToLatestLine("S1E");
				fpss.clear();
				globalTicks.clear();
				logicTicks.clear();
				renderTicks.clear();
				
				lastSaved = System.currentTimeMillis()/1000;
			}

			if(theMainWindow != null) theMainWindow.display.sync(this.GAME_FPS);
			
			lastTickUpdate = System.currentTimeMillis();
			
			this.globalTick = System.nanoTime() - l1;
			
			if(theMainWindow != null && theMainWindow.display.isCloseRequested())
			{
				this.destroyWindow();
				this.stopGameRunning();
			}
		}
	}
	
	public double getAVRD(ArrayList<Double> al)
	{
		double sum = 0;
		for(int i = 0; i < al.size(); i++)
		{
			sum += al.get(i);
		}
		return sum / (double) al.size();
	}
	
	public double getAVRL(ArrayList<Long> al)
	{
		long sum = 0;
		for(int i = 0; i < al.size(); i++)
		{
			sum += al.get(i);
		}
		return (double)sum / (double) al.size();
	}
	
	public void createWnindow()
	{
		theMainWindow = new MainWindow();
	}
	
	public void createSaver()
	{
		gameSettingSaver = new Saver("gameSettings.save");
		gameSettingSaver.initLoad();
	}
	
	public void createDefaultSettings()
	{
		if(!gameSettingSaver.existBoolean("FullScreen")) gameSettingSaver.addBoolean(true, "FullScreen");
		if(!gameSettingSaver.existInt("ScreenWidth")) gameSettingSaver.addInt(1280, "ScreenWidth");
		if(!gameSettingSaver.existInt("ScreenHeight")) gameSettingSaver.addInt(720, "ScreenHeight");
		
		if(!gameSettingSaver.existDouble("SoundScale")) gameSettingSaver.addDouble(1.0, "SoundScale");
		if(!gameSettingSaver.existDouble("MusicScale")) gameSettingSaver.addDouble(1.0, "MusicScale");
		if(!gameSettingSaver.existDouble("MasterSoundScale")) gameSettingSaver.addDouble(1.0, "MasterSoundScale");

		if(!gameSettingSaver.existString("DifficultyLevel")) gameSettingSaver.addString("Normal", "DifficultyLevel");

		if(!gameSettingSaver.existInt("Key_NorthMotion")) gameSettingSaver.addInt(Keyboard.KEY_W, "Key_NorthMotion"); //Default W key
		if(!gameSettingSaver.existInt("Key_SouthMotion")) gameSettingSaver.addInt(Keyboard.KEY_S, "Key_SouthMotion"); //Default S key
		if(!gameSettingSaver.existInt("Key_WestMotion")) gameSettingSaver.addInt(Keyboard.KEY_A, "Key_WestMotion"); //Default A key
		if(!gameSettingSaver.existInt("Key_EastMotion")) gameSettingSaver.addInt(Keyboard.KEY_D, "Key_EastMotion"); //Default D key
		if(!gameSettingSaver.existInt("Key_Run")) gameSettingSaver.addInt(Keyboard.KEY_LSHIFT, "Key_Run"); //Default Shift key
		if(!gameSettingSaver.existInt("Key_Space")) gameSettingSaver.addInt(Keyboard.KEY_SPACE, "Key_Space"); //Default Space key
	}
	
	public void SaveSettings()
	{
		lastSaved = System.currentTimeMillis()/1000;
		gameSettingSaver.initSave();
	}

	public void setScene(GlobalScene scene)
	{
		this.Scene = scene;
	}

	public void logic()
	{
		if(Scene != null) logicObjectsIn(Scene);
	}
	
	private void logicObjectsIn(DrawbleObject obj)
	{
		obj.logic();
		for(int i = 0; i < obj.childs.length; i++)
		{
			logicObjectsIn(obj.childs[i]);
		}
	}
	
	private void drawObjectsIn(DrawbleObject obj)
	{
		obj.draw(theMainWindow);
		for(int i = 0; i < obj.childs.length; i++)
		{
			drawObjectsIn(obj.childs[i]);
		}
	}
	
	public void draw()
	{
		if(Scene != null) drawObjectsIn(Scene);
	}

	public void destroyWindow()
	{
		theMainWindow.destroy();
	}
}























