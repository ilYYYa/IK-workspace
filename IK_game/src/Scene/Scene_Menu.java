package Scene;

import Panel.Panel_menu;

public class Scene_Menu extends GlobalScene
{
	public Scene_Menu()
	{
		super();
		
		this.addPanel(new Panel_menu(0.1, 0.1, 0.8, 0.8, this));
	}
}
