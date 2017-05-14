package Panel;

import java.awt.Color;

import Scene.GlobalScene;
import Window.MainWindow;

public class Panel_settings extends GlobalPanel
{

	public Panel_settings(GlobalScene scene_parent)
	{
		super(0, 0, 1, 1, scene_parent);
		
		this.addChild(new Panel_settings_tabs(0.1, 0.1, 0.15, 0.8, this));
	}
	
	@Override
	public void draw(MainWindow g)
	{
		g.setColor(Color.gray);
		g.fillRect(0, 0, (int)realWidth(), (int)realHeight());
	}

}
