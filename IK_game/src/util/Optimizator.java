package util;

import java.util.ArrayList;

public class Optimizator
{
	public static ArrayList<OptLine> lines = new ArrayList<OptLine>();
	public static boolean isPause = false;
	
	public static void addLine()
	{
		if(isPause) return;
		
		if(lines.size() >= 64) lines.remove(0);
		lines.add(new OptLine());
	}
	
	public static void addPointToLatestLine(String name)
	{
		if(isPause) return;
		
		addPointTo(lines.size() - 1, name);
	}
	
	public static void addPointTo(int index, String name)
	{
		if(isPause) return;
		
		lines.get(index).addPoint(name);
	}
}
