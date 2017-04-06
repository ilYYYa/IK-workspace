package Panel;

import Button.*;

public class Panel_menu extends GlobalPanel
{

	public Panel_menu(double x, double y, double w, double h)
	{
		super(x, y, w, h);
		this.addObjectForDraw(new Button_menu_play(x,y,w-x*2,h/6));
		this.addObjectForDraw(new Button_menu_settings(x,y+h/6*2,w-x*2,h/6));
		this.addObjectForDraw(new Button_menu_exit(x,y+h/6*4,w-x*2,h/6));
	}

}
