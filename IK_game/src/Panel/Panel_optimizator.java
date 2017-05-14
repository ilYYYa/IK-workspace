package Panel;

import java.awt.Color;

import Obj.DrawbleObject;
import Window.MainWindow;
import util.OptLine;
import util.OptPoint;
import util.Optimizator;

public class Panel_optimizator extends GlobalPanel
{
	public Panel_optimizator(double x, double y, double w, double h, DrawbleObject parent)
	{
		super(x, y, w, h, parent);
	}

	@Override
	public void draw(MainWindow g)
	{
		int wd = 30;
		int xx = 0;
		for(int i = 0; i < Optimizator.lines.size()-1; i++)
		{
			OptLine l = Optimizator.lines.get(i);
			
			if(l.length() >= 1000) wd = 40; else wd = 30;
			int yy = 0;
			
			for(int j = 0; j < l.points.size()-1; j++)
			{
				OptPoint p = l.points.get(j);
				long D = l.points.get(j+1).microSec - p.microSec;
				double mp = D / 32000D;
				
				int rg = (int)(255D * mp);
				if(rg > 255) rg = 255;
				if(rg < 0) rg = 0;
				
				g.setColor(new Color(rg, 255 - rg, 0));
				
				int hg = (int)(400D * ((double)D / (double)l.length()));
				
				g.fillRect(xx, (int)this.realHeight() - 425 + yy, wd, hg);
				g.setColor(Color.BLACK);
				g.drawLine(xx, (int)this.realHeight() - 425 + yy, xx + wd, (int)this.realHeight() - 425 + yy);
				if(hg > 10)
				{
					g.setColor(Color.white);
					g.drawString(p.name, xx, (int)this.realHeight() - 415 + yy);
					if(hg > 25)
					{
						g.drawString("" + D, xx, (int)this.realHeight() - 400 + yy);
					}
				}
				
				yy += hg;
			}
			
			g.setColor(Color.white);
			g.drawString("" + l.length(), xx, (int)this.realHeight() - 7);
			
			xx += wd;
		}
		
		super.draw(g);
	}
}
























