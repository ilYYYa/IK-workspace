package Button;

import Panel.GlobalPanel;
import Scene.GlobalScene;

public class TestingButton extends GlobalButton
{
	public TestingButton(int x, int y, int w, int h, GlobalScene scene_parent, GlobalPanel panel_parent)
	{
		super(x, y, w, h, scene_parent, panel_parent);
		this.setText("TEST!!");
	}
}
