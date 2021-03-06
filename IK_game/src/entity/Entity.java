package entity;

import Resources.Saver;
import Resources.TextureLoader;
import Window.MainWindow;
import block.Block;
import world.BlockPos;
import world.World;

public class Entity
{
	/** ������� � ������� ����� ����� ������ � ���� */
	public double posX = 0;
	/** ������� � ������� ����� ����� ������ � ����	 */
	public double posY = 0;
	
	/** �������� ������ �� �, � ������� ������ ����� ��������� */
	public double motionX = 0;
	/** �������� ������ �� �, � ������� ������ ����� ��������� */
	public double motionY = 0;
	/** �������� ������ �� � � ������ ������ */
	public double currentMotionX = 0;
	/** �������� ������ �� � � ������ ������ */
	public double currentMotionY = 0;

	/** ������������ �������� ������������ ������ */
	public double motion = 1D;
	/** ��������� ������ */
	public double moveSpeed = 0.15D;

	/** ������������� ������ ������ */
	public int id = 0;
	/** ���������� ������������� ������� ������ */
	public int uid = 0;
	/** ��������� ������������� ������ ������ */
	public String unlocalizedName = "";

	/** ������ ������ � ������ */
	public double width = 1D;
	/** ������ ������ � ������ */
	public double height = 1D;
	
	/** ������ �������� ������ � ������ */
	public double hitboxWidth = 1D;
	/** ������ �������� ������ � ������ */
	public double hitboxHeight = 1D;

	/** ������ ����������� ������ */
	public LookingVect lookingTo = LookingVect.S;

	/** �������� �� ������ ������� */
	public boolean isDead = false;

	/** ����� ����� ������ */
	public long lifeTime = 0;
	
	/** ������������ ����� ��������������� ������ */
	public int UntouchableMaxCD = 30;
	/** ���������� ����� ��������������� ������. 0 - ������ ����� ���� �������� */
	public int UntouchableCD = 0;

	/**  */
	public boolean collisedWithBlocks = true;

	/** �������� �� ������ ���������, ���� ��, �� �������� ������ ����������� */
	public boolean ghost = false;

	/** ��� */
	public World world;
	
	public Entity(World world, String unlocalizedname, int id, int uid)
	{
		this.world = world;
		this.unlocalizedName = unlocalizedname;
		this.id = id;
		this.uid = uid;
	}

	/** �������� �� ��������������� � ������ */
	public boolean collisionWithEntity(Entity e)
	{
		if(e.posX > this.posX + this.width) return false;
		if(e.posX + e.width < this.posX) return false;
		if(e.posY > this.posY + this.height) return false;
		if(e.posY + e.height < this.posY) return false;
		
		return true;
	}

	/** �������� �� ��������������� � ������ ���������� */
	public boolean collisionWithEntityByHitbox(Entity e)
	{
		if(e.posX + e.width/2 - e.hitboxWidth/2 > this.posX + this.width/2 + this.hitboxWidth/2) return false;
		if(e.posX + e.width/2 + e.hitboxWidth/2 < this.posX + this.width/2 - this.hitboxWidth/2) return false;
		if(e.posY + e.height - e.hitboxHeight > this.posY + this.height) return false;
		if(e.posY + e.height < this.posY + this.height - this.hitboxHeight) return false;
		
		return true;
	}

	/** �������������, �������� �� ������ ���������. ���� ��������, �� �������� ������ ������������ ����� */
	public void setGhostly(boolean ghost)
	{
		this.ghost = ghost;
	}

	/** �������� �� ������ ���������. ���� ��������, �� �������� ������ ������������ ����� */
	public boolean isGhost()
	{
		return this.ghost;
	}

	/** ������������� �������� ��������� ������ */
	public void setMoveSpeed(double speed)
	{
		this.moveSpeed = speed;
	}

	/** ��������� ������ */
	public double getMoveSpeed()
	{
		return this.moveSpeed;
	}

	/** ������������ ������ ������ � ������ */
	public void setWidth(double width)
	{
		this.width = width;
	}

	/** ������ ������ � ������ */
	public double getWidth()
	{
		return this.width;
	}

	/** ������������� ������ ������ */
	public void setHeight(double height)
	{
		this.height = height;
	}

	/** ������ ������ � ������ */
	public double getHeight()
	{
		return this.height;
	}
	
	/** ������������ ������ �������� ������ � ������ */
	public void setHitboxWidth(double width)
	{
		this.hitboxWidth = width;
	}

	/** ������ �������� ������ � ������ */
	public double getHitboxWidth()
	{
		return this.hitboxWidth;
	}

	/** ������������� ������ �������� ������ */
	public void setHitboxHeight(double height)
	{
		this.hitboxHeight = height;
	}

	/** ������ �������� ������ � ������ */
	public double getHitboxHeight()
	{
		return this.hitboxHeight;
	}
	
	/** ��������� ���� ������ ��������� ������. ��������� false ���� ����� �� ������� */
	public boolean attack(Entity entity, damageSource damageType, double value)
	{
		return false;
	}

	/** ��������� ���� ������. ��������� ���� ���� ���� �� ��� ������� */
	public boolean attackFrom(damageSource damageType, double value)
	{
		return false;
	}

	/** ��������� ���� ������ ������ ������. ��������� ���� ���� ���� �� ��� ������� */
	public boolean attackFromLivingEntity(LivingEntity e, damageSource damageType, double value)
	{
		return false;
	}

	/** ������������� ������� ������. � - �������� ������, � - ������ ������� ������ */
	public void setPosition(double x, double y)
	{
		posX = x - this.getWidth()/2; posY = y - this.getHeight();
	}
	
	public BlockPos getPosition()
	{
		return new BlockPos((int)(this.getX()), (int)(this.getY()), BlockPos.blockPosLevel.MIDDLE);
	}

	/** ������� ������ �� � (�������� ������) */
	public double getX()
	{
		return posX + this.getWidth()/2;
	}

	/** ������� ������ �� � (������ ������� ������) */
	public double getY()
	{
		return posY + this.getHeight();
	}

	/** ���������� ���������� ���������� �� ����� */
	public double getDistanceSQTo(double x, double y)
	{
		return (this.getX() - x)*(this.getX() - x) + (this.getY() - y)*(this.getY() - y);
	}

	/** ���������� ���������� �� ����� */
	public double getDistanceTo(double x, double y)
	{
		return Math.sqrt(getDistanceSQTo(x, y));
	}

	/** ���������� ���������� �� ������ ����� */
	public double getDistanceToBlockPos(BlockPos pos)
	{
		return getDistanceTo(pos.posX + 0.5, pos.posY + 0.5);
	}
	
	/** ���������� ���������� �� ������ */
	public double getDistanceToEntity(Entity entity)
	{
		return getDistanceTo(entity.getX(), entity.getY());
	}

	/** �������� �� ���� ������ ����� */
    public boolean isEntityAlive()
    {
        return !this.isDead;
    }

	/** ����� ������ */
    public void kill()
    {
    	this.isDead = true;
    }
	
    /** ��������� ������������ ����� ��������������� ������*/
	public void setMaxUntouchableCD(int cd)
	{
		UntouchableMaxCD = cd;
	}

    /** �������� ���������� ����� ��������������� ������ � ������ ������. 0 - ����� ��������� */
	public int getUntouchableCD()
	{
		return UntouchableCD;
	}
	
	/** ������ ������ ������������� �� UntouchableMaxCD ����� */
	public void setEntityUntouchable()
	{
		this.UntouchableCD = this.UntouchableMaxCD;
	}

	/** ���������� ������� ������ */
    public void onEntityUpdate()
    {
    	lifeTime++;
    	
		if(UntouchableCD > 0) UntouchableCD--;
		if(UntouchableCD < 0) UntouchableCD = 0;
		if(UntouchableCD > UntouchableMaxCD) UntouchableCD = UntouchableMaxCD;
    	
    	double motionX = this.motionX;
    	double motionY = this.motionY;
    	
    	if(!this.isGhost())
    	{
    		motionX *= 1 - world.getBlock(this.getX(), this.getY(), BlockPos.blockPosLevel.BACK).getBlockMovementSlow();
    		motionX *= 1 - world.getBlock(this.getX(), this.getY(), BlockPos.blockPosLevel.MIDDLE).getBlockMovementSlow();
    		motionY *= 1 - world.getBlock(this.getX(), this.getY(), BlockPos.blockPosLevel.BACK).getBlockMovementSlow();
    		motionY *= 1 - world.getBlock(this.getX(), this.getY(), BlockPos.blockPosLevel.MIDDLE).getBlockMovementSlow();
    	}
    	
    	if(this.currentMotionX < motionX) this.currentMotionX += this.getMoveSpeed();
    	if(this.currentMotionX > motionX) this.currentMotionX -= this.getMoveSpeed();
    	if(Math.abs(this.currentMotionX - motionX) < this.getMoveSpeed()) this.currentMotionX = motionX;
    	if(this.currentMotionY < motionY) this.currentMotionY += this.getMoveSpeed();
    	if(this.currentMotionY > motionY) this.currentMotionY -= this.getMoveSpeed();
    	if(Math.abs(this.currentMotionY - motionY) < this.getMoveSpeed()) this.currentMotionY = motionY;

    	if(!this.isGhost())
    	{
    		if(canStayAt(this.getX() + currentMotionX/25D, this.getY())) this.setPosition(this.getX() + currentMotionX/25D, this.getY());
    		if(canStayAt(this.getX(), this.getY() + currentMotionY/25D)) this.setPosition(this.getX(), this.getY() + currentMotionY/25D);
    	}
    	else
    	{
    		this.setPosition(this.getX() + currentMotionX/25D, this.getY());
    		this.setPosition(this.getX(), this.getY() + currentMotionY/25D);
    	}
    	
    	updateLookingVector();
    }
    
    public void updateLookingVector()
    {
    	if(this.motionX > 0 && this.motionY > 0) this.lookingTo = LookingVect.SE;
    	if(this.motionX > 0 && this.motionY < 0) this.lookingTo = LookingVect.NE;
    	if(this.motionX > 0 && this.motionY == 0) this.lookingTo = LookingVect.E;
    	if(this.motionX < 0 && this.motionY > 0) this.lookingTo = LookingVect.SW;
    	if(this.motionX < 0 && this.motionY < 0) this.lookingTo = LookingVect.NW;
    	if(this.motionX < 0 && this.motionY == 0) this.lookingTo = LookingVect.W;
    	if(this.motionX == 0 && this.motionY > 0) this.lookingTo = LookingVect.S;
    	if(this.motionX == 0 && this.motionY < 0) this.lookingTo = LookingVect.N;
    }

	/** ���������� �������� ������ */
    public String getTexture()
    {
    	return "";
    }

	/** ������ �� ������ ������������ */
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
    	if(this.isGhost()) return true;
    	
    	BlockPos pos1 = new BlockPos(x, y, BlockPos.blockPosLevel.BACK);
    	BlockPos pos2 = new BlockPos(x, y, BlockPos.blockPosLevel.MIDDLE);
    	BlockPos pos3 = new BlockPos(x, y, BlockPos.blockPosLevel.HIGH);
    	Block block1 = world.getBlock(pos1);
    	Block block2 = world.getBlock(pos2);
    	Block block3 = world.getBlock(pos3);
    	
    	if(block1 == null || block2 == null || block3 == null) return false;
    	if(!block1.isPassable()) return false;
    	if(!block2.isPassable()) return false;
    	if(!block3.isPassable()) return false;
    	
		return true;
	}
    
    public boolean canStayAt(double x, double y)
    {
    	if(this.isGhost()) return true;
    	
    	double x1 = x - this.getHitboxWidth()/2;
    	double x2 = x + this.getHitboxWidth()/2;
    	
    	double y1 = y - 0.5;
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
    
    private boolean pointAtPassibleBlock(double x, double y)
    {
    	Block b1 = world.getBlock(x, y, BlockPos.blockPosLevel.BACK);
    	Block b2 = world.getBlock(x, y, BlockPos.blockPosLevel.MIDDLE);
    	Block b3 = world.getBlock(x, y, BlockPos.blockPosLevel.HIGH);
    	
    	if(b1 == null || b2 == null || b3 == null) return false;
    	if(!b1.isPassable()) return false;
    	if(!b2.isPassable()) return false;
    	if(!b3.isPassable()) return false;
    	
    	return true;
    }

	public boolean pointOnEntity(double x, double y)
	{
		if(x < this.posX) return false;
		if(x > this.posX + this.width) return false;
		if(y < this.posY) return false;
		if(y > this.posY + this.height) return false;
		
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
		byte lv = 0;
		switch(this.lookingTo)
		{
			case N: lv = 0; break;
			case NE: lv = 1; break;
			case E: lv = 2; break;
			case SE: lv = 3; break;
			case S: lv = 4; break;
			case SW: lv = 5; break;
			case W: lv = 6; break;
			case NW: lv = 7; break;
		}
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
		switch(lv)
		{
			case 0: this.lookingTo = LookingVect.N; break;
			case 1: this.lookingTo = LookingVect.NE; break;
			case 2: this.lookingTo = LookingVect.E; break;
			case 3: this.lookingTo = LookingVect.SE; break;
			case 4: this.lookingTo = LookingVect.S; break;
			case 5: this.lookingTo = LookingVect.SW; break;
			case 6: this.lookingTo = LookingVect.W; break;
			case 7: this.lookingTo = LookingVect.NW; break;
		}
	}

	public void drawEntityOnScreen(MainWindow g, int sx, int sy, int sw, int sh)
	{
		g.drawTexture(TextureLoader.getTextureByName(this.getTexture()), sx, sy, sw, sh);
	}
	
	public static enum LookingVect
	{
		N, NE, E, SE, S, SW, W, NW
	}
}





















