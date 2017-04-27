package Game;
import Resources.Saver;
import Scene.GlobalScene;
import Window.DoubleBuffer;
import Window.MainWindow;
import block.Block_Air;
import block.Blocks;
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
	
	public double fps = 0;
	public long logicTick = 0;
	public long renderTick = 0;
	public long avrRenderTick = 0;
	public long avrLogicTick = 0;
	
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
			long l1 = System.currentTimeMillis();
			theDoubleBuffer.logic();
			long l2 = System.currentTimeMillis();
			if(theMainWindow != null) theMainWindow.repaint();
			long l3 = System.currentTimeMillis();
			
			if(avrRenderTick == 0)avrRenderTick = (l2-l1);
			else avrRenderTick = (avrRenderTick+(l2-l1))/2;
			
			if(avrLogicTick == 0)avrLogicTick = (l3-l2);
			else avrLogicTick = (avrLogicTick+(l3-l2))/2;
			
			if(System.currentTimeMillis()/1000 % 2 == 0)
			{
				this.renderTick = this.avrRenderTick;
				this.avrRenderTick = 0;
				this.logicTick = this.avrLogicTick;
				this.avrLogicTick = 0;
			}
			
			if(System.currentTimeMillis()/1000 % 10 == 0 && lastSaved != System.currentTimeMillis()) this.SaveSettings();
			
			try {Thread.sleep(16);} // 16 --> 1000/60 --> 60fps
			catch (InterruptedException e){}
		}
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























