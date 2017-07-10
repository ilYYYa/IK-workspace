package Button;

import java.util.ArrayList;

import Obj.DrawbleObject;

public class GButton_Switch extends GlobalButton
{
	public ArrayList<String> tabs = new ArrayList<String>();
	public int chose = 0;

	public GButton_Switch(double x, double y, double w, double h, DrawbleObject parent)
	{
		super(x, y, w, h, parent);
	}

	public void addTab(String tab)
	{
		tabs.add(tab);
	}
	
	public void removeTab(int index)
	{
		tabs.remove(index);
	}
	
	@Override
	public String getText()
	{
		if(tabs.size() == 0) return "WARN: No tabs!";
		return super.getText() + ": " + tabs.get(chose);
	}
	
	@Override
	public void onMouseRelease(int x, int y, int btn)
	{
		super.onMouseRelease(x,y,btn);
		chose++;
		if(chose >= tabs.size()) chose = 0;
	}
}
