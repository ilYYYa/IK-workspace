package Panel;

import Button.Button_settings_video_fullScreen;
import Obj.DrawbleObject;

public class Panel_settings_video extends GlobalPanel
{
	
	public Panel_settings_video(double x, double y, double w, double h, DrawbleObject parent)
	{
		super(x, y, w, h, parent);
		
		this.addChild(new Button_settings_video_fullScreen(0, 0, 1, 0.2, this));
	}

}
