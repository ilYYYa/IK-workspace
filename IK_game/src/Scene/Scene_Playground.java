package Scene;

import Panel.Panel_playground;

public class Scene_Playground extends GlobalScene
{
	public Scene_Playground()
	{
		this.addPanel(new Panel_playground(0, 0, 1, 1, this));
	}
}
