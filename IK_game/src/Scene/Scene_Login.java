package Scene;

import Panel.Panel_login;
import Window.MainWindow;

public class Scene_Login extends GlobalScene
{
	public Scene_Login()
	{
		this.addPanel(new Panel_login(0.3,0.3,0.4,0.4,this));
	}
}
