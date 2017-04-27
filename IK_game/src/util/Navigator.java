package util;

import java.util.ArrayList;

import entity.Entity;
import entity.LivingEntity;
import world.BlockPos;

public class Navigator
{
	private LivingEntity entity;
	
	private ArrayList<double[]> path = new ArrayList<double[]>();
	
	private int movingTo = 0;
	
	public Navigator(LivingEntity e)
	{
		entity = e;
	}
	
	public boolean noPath()
	{
		return path.size() == 0;
	}
	
	public double[] getCurrentPoint()
	{
		return path.get(0);
	}
	
	public void pointReached()
	{
		path.remove(0);
	}
	
	public void tryMoveToXY(double x, double y)
	{
		if(!entity.canMoveTo((int)x, (int)y)) return;
		path.add(new double[]{entity.getX(), y});
		path.add(new double[]{x, y});
		movingTo = 1;
	}
	
	public void tryMoveToEntity(Entity e)
	{
		path.add(new double[]{entity.getX(), e.getY()});
		path.add(new double[]{e.getX(), e.getY()});
		movingTo = 2;
	}

	public void removePath()
	{
		path.clear();
	}

	public boolean movingToPos()
	{
		return movingTo == 1;
	}

	public boolean movingToEntity()
	{
		return movingTo == 2;
	}
}
