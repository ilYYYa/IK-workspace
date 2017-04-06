package Scene;

import Panel.Panel_settings_tabs;

public class Scene_Settings extends GlobalScene
{
	public Scene_Settings(double w, double h)
	{
		super(w, h);
		this.addPanel(new Panel_settings_tabs(50, 50, 200, h-100, this));
	}
}
