package Scene;

import Panel.TestingPanel;
import Panel.TestingPanel_0;

public class TestingScene extends GlobalScene
{
	public TestingScene(int w, int h)
	{
		super(w, h);
		this.addPanel(new TestingPanel(200, 0, 200, (int)height));
		this.addPanel(new TestingPanel_0(0, 200, (int)width, 200));
	}
}
