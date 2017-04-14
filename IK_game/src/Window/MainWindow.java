package Window;

import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.JFrame;

import Game.GameInfo;
import Resources.Settings;

public class MainWindow extends JFrame
{
	public MainWindow()
	{
		if(Settings.fullScreen)
		{
			this.setBounds(0, 0, (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(), (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight());
			this.setUndecorated(true);
		}
		else
		{
			this.setBounds(100, 100, Settings.widthScreen, Settings.heightScreen);
		}
		
		this.setTitle(GameInfo.getGameFullestName());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
