package Panel;

import Button.Button_menu_exit;
import Button.Button_menu_newGame;
import Button.Button_menu_play;
import Button.Button_menu_settings;
import Scene.GlobalScene;

public class Panel_menu extends GlobalPanel
{
	Button_menu_play continueBtn;
	public Panel_menu(double x, double y, double w, double h, GlobalScene scene_parent)
	{
		super(x, y, w, h, scene_parent);

		continueBtn = new Button_menu_play(0.1, 1d/9d, 0.8, 1d/9d, this);
		
		continueBtn.ButtonActive = false;
		
		this.addChild(continueBtn);
		this.addChild(new Button_menu_newGame(0.1, 3d/9d, 0.8, 1d/9d, this));
		this.addChild(new Button_menu_settings(0.1, 5d/9d, 0.8, 1d/9d, this));
		this.addChild(new Button_menu_exit(0.1, 7d/9d, 0.8, 1d/9d, this));
	}
}
