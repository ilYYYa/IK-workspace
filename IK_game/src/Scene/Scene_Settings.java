package Scene;

import Panel.Panel_settings;

public class Scene_Settings extends GlobalScene
{
	public Scene_Settings()
	{
		super();
		
		addPanel(new Panel_settings(this));
	}
}
