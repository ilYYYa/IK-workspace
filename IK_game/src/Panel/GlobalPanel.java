package Panel;

import Obj.DrawbleObject;
import Window.MainWindow;

public class GlobalPanel extends DrawbleObject
{
	public GlobalPanel(double x, double y, double w, double h, DrawbleObject parent)
	{
		super(parent);
		posX = x;
		posY = y;
		width = w;
		height = h;
	}

	public void logic(){}
	public void draw(MainWindow g){}
}
