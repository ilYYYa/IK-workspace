package Panel;

import Obj.DrawbleObject;
import world.World;

public class Panel_playground extends GlobalPanel
{
	Panel_worldrenderer worldRenderer;
	public Panel_playground(double x, double y, double w, double h, DrawbleObject parent)
	{
		super(x, y, w, h, parent);
		
		worldRenderer = new Panel_worldrenderer(0, 0, 1, 1, this);
		worldRenderer.setWorld(new World("testing world"));
		
		this.addChild(worldRenderer);
	}
}
