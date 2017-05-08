package util;

import java.util.ArrayList;

public class OptLine
{
	public ArrayList<OptPoint> points = new ArrayList<OptPoint>();
	public long length()
	{
		return points.get(points.size() - 1).microSec - points.get(0).microSec;
	}
	
	public void addPoint(String name)
	{
		OptPoint point = new OptPoint((long)(System.nanoTime()/1000), name);
		points.add(point);
	}

}