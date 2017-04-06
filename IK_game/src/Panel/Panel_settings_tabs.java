package Panel;

import Button.Button_settings_audio;
import Button.Button_settings_game;
import Button.Button_settings_video;
import Scene.GlobalScene;

public class Panel_settings_tabs extends GlobalPanel
{
	public static Panel_settings_video psv = null;
	
	public Panel_settings_tabs(double x, double y, double w, double h, GlobalScene scene_parent)
	{
		super(x, y, w, h, scene_parent);
		this.addObjectForDraw(new Button_settings_game(10, 0, w - 20, h/5, scene_parent, this));
		this.addObjectForDraw(new Button_settings_video(10, h/5*2, w - 20, h/5, scene_parent, this));
		this.addObjectForDraw(new Button_settings_audio(10, h/5*4, w - 20, h/5, scene_parent, this));
		
		psv = new Panel_settings_video(w+x+50, y, scene_parent.width-w-x, h, scene_parent);
	}
}
