package Window;

import java.awt.Toolkit;
import java.io.IOException;

import javax.swing.JFrame;

import Resources.SettingLoader;
import Resources.Settings;

public class MainWindow extends JFrame
{
	DoubleBuffer buff;
	
	public MainWindow()
	{
		try
		{
			SettingLoader.initLoad();
		}
		catch (IOException e1)
		{
			SettingLoader.initSave();
		}
		
		if(Settings.fullScreen)
		{
			this.setBounds(0, 0, (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(), (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight());
			this.setUndecorated(true);
		}
		else
		{
			this.setBounds(100, 100, Settings.widthScreen, Settings.heightScreen);
		}
		
		this.setTitle("Title");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		buff = new DoubleBuffer(this);
		this.addKeyListener(buff.listeners);
		
		this.add(buff);
		
		this.setVisible(true);
		
		while(true)
		{
			repaint();
			
			try
			{
				Thread.sleep(16); // 16 --> 1000/60 --> 60fps
			}
			catch (InterruptedException e){}
		}
	}
}
