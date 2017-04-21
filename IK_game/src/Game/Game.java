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
	
	public Game()
	{
		theGame = this;
		
		createSaver();
		
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
			
			if(System.currentTimeMillis()/1000 % 10 == 0) this.SaveSettings();
			
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
	}
	
	public void SaveSettings()
	{
		gameSettingSaver.initSave();
	}
}
