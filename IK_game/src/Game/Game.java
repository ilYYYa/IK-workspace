package Game;
import Resources.Saver;
import Window.DoubleBuffer;
import Window.MainWindow;

public class Game
{
	public static Game theGame;
	
	public Saver gameSettingSaver;
	
	public MainWindow theMainWindow;
	public DoubleBuffer theDoubleBuffer;
	
	public boolean gameRunning = false;
	
	public long lastSaved = System.currentTimeMillis()/1000;
	
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
			theDoubleBuffer.logic();
			if(theMainWindow != null) theMainWindow.repaint();
			
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
}























