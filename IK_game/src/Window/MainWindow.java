package Window;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.JFrame;

import Scene.TestingScene;

public class MainWindow extends JFrame
{
	DoubleBuffer buff;
	
	public MainWindow()
	{
		this.setBounds(0, 0, (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(), (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight());
		this.setTitle("Title");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setUndecorated(true);
		
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
