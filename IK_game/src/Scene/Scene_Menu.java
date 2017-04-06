package Scene;

import Panel.Panel_menu;

public class Scene_Menu extends GlobalScene
{

	public Scene_Menu(double w, double h)
	{
		super(w, h);
		this.addPanel(new Panel_menu(100,100,w-200,h-200));
	}

}
