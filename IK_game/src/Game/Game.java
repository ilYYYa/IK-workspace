package Game;
import java.util.ArrayList;

import Resources.Saver;
import Scene.GlobalScene;
import Window.DoubleBuffer;
import Window.MainWindow;
import block.Block_Air;
import block.Blocks;
import util.Optimizator;
import world.World;

public class Game
{
	public static Game theGame;
	
	public Saver gameSettingSaver;
	
	public MainWindow theMainWindow;
	public DoubleBuffer theDoubleBuffer;
	
	public World currentOpenedWorld = null;
	
	public boolean gameRunning = false;
	
	public long lastSaved = System.currentTimeMillis()/1000;
	
	public static final int GAME_FPS = 60;
	
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
		createDoubleBuffer();
		setupDoubleBuffer();
		setWindowVisible(true);
		
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
	}
	
	public void GameRunning()
	{
		while(gameRunning)
		{
			Optimizator.addLine();
			Optimizator.addPointToLatestLine("i1B");
			
			if(globalTick != 0)fps = 1_000_000_000D / (double)globalTick;
			else fps = 1000D;
			
			long l1 = System.nanoTime();
			Optimizator.addPointToLatestLine("i2B");
			theDoubleBuffer.logic();
			Optimizator.addPointToLatestLine("i2E");
			long l2 = System.nanoTime();
			if(theMainWindow != null) theMainWindow.repaint();
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

			while(System.currentTimeMillis() < lastTickUpdate + 1000/GAME_FPS);
			
			lastTickUpdate = System.currentTimeMillis();
			
			this.globalTick = System.nanoTime() - l1;
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
	
	public void createDoubleBuffer()
	{
		theDoubleBuffer = new DoubleBuffer(theMainWindow);
	}
	
	public void setupDoubleBuffer()
	{
		theMainWindow.addKeyListener(theDoubleBuffer.listeners);
		theMainWindow.add(theDoubleBuffer);
	}
	
	public void setWindowVisible(boolean v)
	{
		theMainWindow.setVisible(v);
	}
	
	public void createSaver()
	{
		gameSettingSaver = new Saver("gameSettings.save");
		gameSettingSaver.initLoad();
	}
	
	public void createDefaultSettings()
	{
		if(!gameSettingSaver.existBoolean("FullScreen")) gameSettingSaver.addBoolean(true, "FullScreen");
		
		if(!gameSettingSaver.existDouble("SoundScale")) gameSettingSaver.addDouble(1.0, "SoundScale");
		if(!gameSettingSaver.existDouble("MusicScale")) gameSettingSaver.addDouble(1.0, "MusicScale");
		if(!gameSettingSaver.existDouble("MasterSoundScale")) gameSettingSaver.addDouble(1.0, "MasterSoundScale");

		if(!gameSettingSaver.existString("DifficultyLevel")) gameSettingSaver.addString("Normal", "DifficultyLevel");

		if(!gameSettingSaver.existInt("Key_NorthMotion")) gameSettingSaver.addInt(87, "Key_NorthMotion"); //Default W key
		if(!gameSettingSaver.existInt("Key_SouthMotion")) gameSettingSaver.addInt(83, "Key_SouthMotion"); //Default S key
		if(!gameSettingSaver.existInt("Key_WestMotion")) gameSettingSaver.addInt(65, "Key_WestMotion"); //Default A key
		if(!gameSettingSaver.existInt("Key_EastMotion")) gameSettingSaver.addInt(68, "Key_EastMotion"); //Default D key
		if(!gameSettingSaver.existInt("Key_Run")) gameSettingSaver.addInt(16, "Key_Run"); //Default Shift key
	}
	
	public void SaveSettings()
	{
		lastSaved = System.currentTimeMillis()/1000;
		gameSettingSaver.initSave();
	}

	public void setScene(GlobalScene scene)
	{
		this.theDoubleBuffer.setScene(scene);
	}
}























