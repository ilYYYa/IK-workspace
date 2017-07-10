package util;

import java.util.ArrayList;

import entity.Entity;
import entity.LivingEntity;
import world.BlockPos;

public class Navigator
{
	private LivingEntity entity;
	
	private Entity targetEntity = null;
	
	private ArrayList<double[]> path = new ArrayList<double[]>();
	
	private int movingTo = 0;
	
	private double deviation = 0.5;
	
	public Navigator(LivingEntity e)
	{
		entity = e;
	}
	
	public boolean noPath()
	{
		Update();
		
		return path.size() == 0 && targetEntity == null;
	}
	
	public double[] getCurrentPoint()
	{
		Update();
		
		if(path.size() > 0 && movingTo == 1 && pointReached()) path.remove(0);
		if(movingTo == 2 && entityReached())
		{
			targetEntity = null;
			return null;
		}
		if(noPath()) return null;
		
		if(path.size() > 0 && movingTo == 1) return path.get(0);
		else if(movingTo == 2) return new double[]{targetEntity.getX(), targetEntity.getY()};
		else return new double[]{entity.getX(), entity.getY()};
	}
	
	public boolean pointReached()
	{
		if(path.size() == 0) return true;
		if(movingTo == 1 && path.size() != 0 && Math.abs(entity.getX() - path.get(0)[0]) <= deviation && Math.abs(entity.getY() - path.get(0)[1]) <= deviation) return true;
		
		return false;
	}
	
	public boolean entityReached()
	{
		if(targetEntity == null) return true;
		if(entity.collisionWithEntityByHitbox(targetEntity)) return true;
		
		return false;
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
		targetEntity = e;
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

	public void Update()
	{
		if(path.size() > 0 && movingTo == 1 && pointReached()) path.remove(0);
		if(movingTo == 2 && entityReached()) targetEntity = null;
	}
}
