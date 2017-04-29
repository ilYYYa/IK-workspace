package entity;

import java.awt.Graphics;

import Resources.Saver;
import Resources.TextureLoader;
import block.Block;
import world.BlockPos;
import world.World;

public class Entity
{
	public double posX = 0;
	public double posY = 0;
	
	public double motionX = 0;
	public double motionY = 0;
	public double currentMotionX = 0;
	public double currentMotionY = 0;

	public double moveSpeed = 0.15D;
	
	public int id = 0;
	public int uid = 0;
	public String unlocalizedName = "";
	
	public double width = 1D;
	public double height = 1D;
	
	public LookingVect lookingTo = LookingVect.SOUTH;
	
	public boolean isDead = false;
	
	public long lifeTime = 0;
	
	public boolean collisedWithBlocks = true;
	
	public boolean ghost = false;
	
	public World world;
	
	public Entity(World world, String unlocalizedname, int id, int uid)
	{
		this.world = world;
		this.unlocalizedName = unlocalizedname;
		this.id = id;
		this.uid = uid;
	}
	
	public boolean collisionWithEntity(Entity e)
	{
		if(e.posX > this.posX + this.width) return false;
		if(e.posX + e.width < this.posX) return false;
		if(e.posY > this.posY + this.height) return false;
		if(e.posY + e.height < this.posY) return false;
		
		return true;
	}
	
	public void setGhostly(boolean ghost)
	{
		this.ghost = ghost;
	}
	
	public boolean isGhost()
	{
		return this.ghost;
	}

	public void setMoveSpeed(double speed)
	{
		this.moveSpeed = speed;
	}
	
	public double getMoveSpeed()
	{
		return this.moveSpeed;
	}
	
	public void setWidth(double width)
	{
		this.width = width;
	}
	
	public double getWidth()
	{
		return this.width;
	}
	
	public void setHeight(double height)
	{
		this.height = height;
	}
	
	public double getHeight()
	{
		return this.height;
	}
	
	public void attackFrom(damageSource damageType, double value)
	{
		
	}
	
	public void attackFromLivingEntity(LivingEntity e, damageSource damageType, double value)
	{
		
	}
	
	public void setPosition(double x, double y)
	{
		posX = x - this.getWidth()/2; posY = y - this.getHeight();
	}
	
	public BlockPos getPosition()
	{
		return new BlockPos((int)(this.getX()), (int)(this.getY()), BlockPos.blockPosLevel.MIDDLE);
	}
	
	public double getX()
	{
		return posX + this.getWidth()/2;
	}
	
	public double getY()
	{
		return posY + this.getHeight();
	}
	
	public double getDistanceSQTo(double x, double y)
	{
		return (this.getX() - x)*(this.getX() - x) + (this.getY() - y)*(this.getY() - y);
	}
	public double getDistanceTo(double x, double y)
	{
		return Math.sqrt(getDistanceSQTo(x, y));
	}
	public double getDistanceToBlockPos(BlockPos pos)
	{
		return getDistanceTo(pos.posX, pos.posY);
	}
	public double getDistanceToEntity(Entity entity)
	{
		return getDistanceTo(entity.getX(), entity.getY());
	}
	
    public boolean isEntityAlive()
    {
        return !this.isDead;
    }
    
    public void kill()
    {
    	this.isDead = true;
    }
    
    public void onEntityUpdate()
    {
    	lifeTime++;
    	
    	if(this.currentMotionX < this.motionX) this.currentMotionX += this.getMoveSpeed();
    	if(this.currentMotionX > this.motionX) this.currentMotionX -= this.getMoveSpeed();
    	if(Math.abs(this.currentMotionX - this.motionX) < this.getMoveSpeed()) this.currentMotionX = this.motionX;
    	if(this.currentMotionY < this.motionY) this.currentMotionY += this.getMoveSpeed();
    	if(this.currentMotionY > this.motionY) this.currentMotionY -= this.getMoveSpeed();
    	if(Math.abs(this.currentMotionY - this.motionY) < this.getMoveSpeed()) this.currentMotionY = this.motionY;
    	
    	if(canStayAt(this.getX() + currentMotionX/25D, this.getY())) this.setPosition(this.getX() + currentMotionX/25D, this.getY());
    	else
    		if(currentMotionX > 0) this.setPosition((int)(this.getX() + this.getWidth()/2) - this.getWidth()/2 + 0.99, this.getY());
    		else  this.setPosition((int)(this.getX() - this.getWidth()/2) + this.getWidth()/2 - 0.99, this.getY());
    	
    	if(canStayAt(this.getX(), this.getY() + currentMotionY/25D)) this.setPosition(this.getX(), this.getY() + currentMotionY/25D);
    	else
    		if(currentMotionY > 0) this.setPosition(this.getX(), (int)this.getY() + 0.99);
    		else this.setPosition(this.getX(), (int)(this.getY() - this.getHeight()) + this.getHeight() - 0.99);
    	
    	if(motionX > 0) this.lookingTo = LookingVect.EAST;
    	if(motionX < 0) this.lookingTo = LookingVect.WEST;
    	if(motionY > 0) this.lookingTo = LookingVect.SOUTH;
    	if(motionY < 0) this.lookingTo = LookingVect.NORTH;
    }
    
    public String getTexture()
    {
    	return "";
    }
    
    public boolean shouldDespawn()
    {
    	return false;
    }
    
    public boolean canMoveTo(BlockPos pos)
    {
    	return canMoveTo(pos.posX, pos.posY);
    }
    
    public boolean canMoveTo(int x, int y)
    {
    	BlockPos pos1 = new BlockPos(x,y, BlockPos.blockPosLevel.BACK);
    	BlockPos pos2 = new BlockPos(x,y, BlockPos.blockPosLevel.MIDDLE);
    	Block block1 = world.getBlock(pos1);
    	Block block2 = world.getBlock(pos2);
    	
    	if(block1 == null || block2 == null) return false;
    	if(!block1.isPassable()) return false;
    	if(!block2.isPassable()) return false;
    	
		return true;
	}
    
    public boolean canStayAt(double x, double y)
    {
    	double x1 = x - this.getWidth()/2;
    	double y1 = y - this.getHeight();
    	double x2 = x + this.getWidth()/2;
    	double y2 = y;
    	
    	for(double ix = x1; ix <= x2; ix+=0.1)
    	{
    		for(double iy = y1; iy <= y2; iy+=0.1)
        	{
    			if(!pointAtPassibleBlock(ix, iy)) return false;
        	}
    	}
    	
		if(!pointAtPassibleBlock(x1, y1)) return false;
		if(!pointAtPassibleBlock(x1, y2)) return false;
		if(!pointAtPassibleBlock(x2, y1)) return false;
		if(!pointAtPassibleBlock(x2, y2)) return false;
    	
    	return true;
    }
    
    public boolean pointAtPassibleBlock(double x, double y)
    {
    	Block b1 = world.getBlock(x, y, BlockPos.blockPosLevel.BACK);
    	Block b2 = world.getBlock(x, y, BlockPos.blockPosLevel.MIDDLE);
    	
    	if(b1 == null || b2 == null) return false;
    	if(!b1.isPassable()) return false;
    	if(!b2.isPassable()) return false;
    	
    	return true;
    }

	public static enum damageSource
    {
    	PHYSICAL, MAGICAL, PURE
    }

	public void writeToSaver(Saver saver, int i)
	{
		saver.addString(unlocalizedName, "EntityUnlocalizedName" + i);
		saver.addInt(id, "EntityId" + i);
		saver.addInt(uid, "EntityUid" + i);
		saver.addDouble(posX, "EntityPosX"+i);
		saver.addDouble(posY, "EntityPosY"+i);
		saver.addDouble(motionX, "EntityMotionX"+i);
		saver.addDouble(motionY, "EntityMotionY"+i);
		saver.addDouble(width, "EntityWidth"+i);
		saver.addDouble(height, "EntityHeight"+i);
		saver.addBoolean(isDead, "EntityIsDead"+i);
		saver.addLong(lifeTime, "EntityLifeTime"+i);
		byte lv = (byte)(this.lookingTo == LookingVect.NORTH ? 0 : this.lookingTo == LookingVect.SOUTH ? 1 : this.lookingTo == LookingVect.WEST ? 2 : 3);
		saver.addByte(lv, "EntityLookingTo"+i);
	}

	public void readFromSaver(Saver saver, int i)
	{
		unlocalizedName = saver.getString("EntityUnlocalizedName" + i);
		id = saver.getInt("EntityId" + i);
		uid = saver.getInt("EntityUid" + i);
		posX = saver.getDouble("EntityPosX" + i);
		posY = saver.getDouble("EntityPosY" + i);
		motionX = saver.getDouble("EntityMotionX" + i);
		motionY = saver.getDouble("EntityMotionY" + i);
		width = saver.getDouble("EntityWidth" + i);
		height = saver.getDouble("EntityHeight" + i);
		isDead = saver.getBoolean("EntityIsDead" + i);
		lifeTime = saver.getLong("EntityLifeTime" + i);
		byte lv = saver.getByte("EntityLookingTo"+i);
		this.lookingTo = lv == 0 ? LookingVect.NORTH : lv == 1 ? LookingVect.SOUTH : lv == 2 ? LookingVect.WEST : LookingVect.EAST;
	}

	public void drawEntityOnScreen(Graphics g, int sx, int sy, int sw, int sh)
	{
		g.drawImage(TextureLoader.getTextureByName(this.getTexture()), sx, sy, sw, sh, null);
	}
	
	public static enum LookingVect
	{
		NORTH, SOUTH, WEST, EAST
	}
}





















