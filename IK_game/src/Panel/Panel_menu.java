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
		this.addObjectForDraw(new Button_menu_play(x,y,w-x*2,h/6,scene_parent,this));
		this.addObjectForDraw(new Button_menu_settings(x,y+h/6*2,w-x*2,h/6,scene_parent,this));
		this.addObjectForDraw(new Button_menu_exit(x,y+h/6*4,w-x*2,h/6,scene_parent,this));
	}

}
