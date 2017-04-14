package Game;
import java.io.IOException;

import Resources.SettingLoader;
import Window.DoubleBuffer;
import Window.MainWindow;

public class Game
{
	public static Game theGame;
	public MainWindow theMainWindow;
	public DoubleBuffer theDoubleBuffer;
	
	public boolean gameRunning = false;
	
	public Game()
	{
		theGame = this;
		
		InitializeSettings();
		
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
			if(theMainWindow != null) theMainWindow.repaint();
			
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
	
	public void InitializeSettings()
	{
		try
		{
			SettingLoader.initLoad();
		}
		catch (IOException e1)
		{
			SettingLoader.initSave();
		}
	}
	
	public void SaveSettings()
	{
		SettingLoader.initSave();
	}
}
