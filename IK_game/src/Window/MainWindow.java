package Window;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import Game.Game;
import Game.GameInfo;

public class MainWindow extends JFrame
{
	public MainWindow()
	{
		if(!Game.theGame.gameSettingSaver.existBoolean("FullScreen")) Game.theGame.gameSettingSaver.addBoolean(true, "FullScreen");
		
		if(Game.theGame.gameSettingSaver.getBoolean("FullScreen"))
		{
			this.setBounds(0, 0, (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(), (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight());
			this.setUndecorated(true);
		}
		else
		{
			this.setBounds(100, 100, 1280, 720);
		}
		
		this.setMinimumSize(new Dimension(400, 400));
		
		this.setTitle(GameInfo.getGameFullestName());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
