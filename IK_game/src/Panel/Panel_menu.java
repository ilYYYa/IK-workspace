package Panel;

import Button.Button_menu_exit;
import Button.Button_menu_play;
import Button.Button_menu_settings;
import Scene.GlobalScene;

public class Panel_menu extends GlobalPanel
{

	public Panel_menu(double x, double y, double w, double h, GlobalScene scene_parent)
	{
		super(x, y, w, h, scene_parent);
		
		this.addChild(new Button_menu_play(0.1, 1d/7d, 0.8, 1d/7d, this));
		this.addChild(new Button_menu_settings(0.1, 3d/7d, 0.8, 1d/7d, this));
		this.addChild(new Button_menu_exit(0.1, 5d/7d, 0.8, 1d/7d, this));
	}
}
