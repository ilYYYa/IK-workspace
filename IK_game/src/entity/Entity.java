package entity;

import Resources.Saver;
import Resources.TextureLoader;
import Window.MainWindow;
import block.Block;
import world.BlockPos;
import world.World;

public class Entity
{
	/** Позиция Х верхней левой точки ентити в миру */
	public double posX = 0;
	/** Позиция У верхней левой точки ентити в миру	 */
	public double posY = 0;
	
	/** Скорость ентити по Х, к которой ентити будет сремиться */
	public double motionX = 0;
	/** Скорость ентити по У, к которой ентити будет сремиться */
	public double motionY = 0;
	/** Скорость ентити по Х в данный момент */
	public double currentMotionX = 0;
	/** Скорость ентити по У в данный момент */
	public double currentMotionY = 0;

	/** Максимальная скорость передвижения ентити */
	public double motion = 1D;
	/** Ускорение ентити */
	public double moveSpeed = 0.15D;

	/** Идентификатор класса ентити */
	public int id = 0;
	/** Уникальный идентификатор данного ентити */
	public int uid = 0;
	/** Текстовый идентификатор класса ентити */
	public String unlocalizedName = "";

	/** Ширина ентити в блоках */
	public double width = 1D;
	/** Высота ентити в блоках */
	public double height = 1D;
	
	/** Ширина хитбокса ентити в блоках */
	public double hitboxWidth = 1D;
	/** Высота хитбокса ентити в блоках */
	public double hitboxHeight = 1D;

	/** Вектор направления ентити */
	public LookingVect lookingTo = LookingVect.S;

	/** Является ли ентити мертвым */
	public boolean isDead = false;

	/** Время жизни ентити */
	public long lifeTime = 0;

	/**  */
	public boolean collisedWithBlocks = true;

	/** Является ли ентити призраком, если да, то проходит сквозь препятствия */
	public boolean ghost = false;

	/** Мир */
	public World world;
	
	public Entity(World world, String unlocalizedname, int id, int uid)
	{
		this.world = world;
		this.unlocalizedName = unlocalizedname;
		this.id = id;
		this.uid = uid;
	}

	/** Проверка на соприкосновение с ентити */
	public boolean collisionWithEntity(Entity e)
	{
		if(e.posX > this.posX + this.width) return false;
		if(e.posX + e.width < this.posX) return false;
		if(e.posY > this.posY + this.height) return false;
		if(e.posY + e.height < this.posY) return false;
		
		return true;
	}

	/** Проверка на соприкосновение с ентити хитбоксами */
	public boolean collisionWithEntityByHitbox(Entity e)
	{
		if(e.posX + e.width/2 - e.hitboxWidth/2 > this.posX + this.width/2 + this.hitboxWidth/2) return false;
		if(e.posX + e.width/2 + e.hitboxWidth/2 < this.posX + this.width/2 - this.hitboxWidth/2) return false;
		if(e.posY + e.height/2 - e.hitboxHeight/2 > this.posY + this.height/2 + this.hitboxHeight/2) return false;
		if(e.posY + e.height/2 + e.hitboxHeight/2 < this.posY + this.height/2 - this.hitboxHeight/2) return false;
		
		return true;
	}

	/** Устанавливает, является ли ентити призраком. Если является, то проходит сквозь непроходимые блоки */
	public void setGhostly(boolean ghost)
	{
		this.ghost = ghost;
	}

	/** Является ли ентити призраком. Если является, то проходит сквозь непроходимые блоки */
	public boolean isGhost()
	{
		return this.ghost;
	}

	/** Устанавливает значение ускорения ентити */
	public void setMoveSpeed(double speed)
	{
		this.moveSpeed = speed;
	}

	/** Ускорение ентити */
	public double getMoveSpeed()
	{
		return this.moveSpeed;
	}

	/** Устанвливает ширину ентити в блоках */
	public void setWidth(double width)
	{
		this.width = width;
	}

	/** Ширина ентити в блоках */
	public double getWidth()
	{
		return this.width;
	}

	/** Устанавливает высоту ентити */
	public void setHeight(double height)
	{
		this.height = height;
	}

	/** Высота ентити в блоках */
	public double getHeight()
	{
		return this.height;
	}
	
	/** Устанвливает ширину хитбокса ентити в блоках */
	public void setHitboxWidth(double width)
	{
		this.hitboxWidth = width;
	}

	/** Ширина хитбокса ентити в блоках */
	public double getHitboxWidth()
	{
		return this.hitboxWidth;
	}

	/** Устанавливает высоту хитбокса ентити */
	public void setHitboxHeight(double height)
	{
		this.hitboxHeight = height;
	}

	/** Высота хитбокса ентити в блоках */
	public double getHitboxHeight()
	{
		return this.hitboxHeight;
	}

	/** Атаковать этот ентити */
	public void attackFrom(damageSource damageType, double value)
	{
		
	}

	/** Атаковать этот ентити живущем ентити */
	public void attackFromLivingEntity(LivingEntity e, damageSource damageType, double value)
	{
		
	}
	
	public void attack()
	{
		
	}

	/** Устанавливает позицию ентити. Х - середина ентити, У - нижняя граница ентити */
	public void setPosition(double x, double y)
	{
		posX = x - this.getWidth()/2; posY = y - this.getHeight();
	}
	
	public BlockPos getPosition()
	{
		return new BlockPos((int)(this.getX()), (int)(this.getY()), BlockPos.blockPosLevel.MIDDLE);
	}

	/** Позиция ентити по Х (середина ентити) */
	public double getX()
	{
		return posX + this.getWidth()/2;
	}

	/** Позиция ентити по У (низняя граница ентити) */
	public double getY()
	{
		return posY + this.getHeight();
	}

	/** Возвращает квадратное расстояние до точки */
	public double getDistanceSQTo(double x, double y)
	{
		return (this.getX() - x)*(this.getX() - x) + (this.getY() - y)*(this.getY() - y);
	}

	/** Возвращает расстояние до точки */
	public double getDistanceTo(double x, double y)
	{
		return Math.sqrt(getDistanceSQTo(x, y));
	}

	/** Возвращает расстояние до центра блока */
	public double getDistanceToBlockPos(BlockPos pos)
	{
		return getDistanceTo(pos.posX + 0.5, pos.posY + 0.5);
	}
	
	/** Возвращает расстояние до ентити */
	public double getDistanceToEntity(Entity entity)
	{
		return getDistanceTo(entity.getX(), entity.getY());
	}

	/** Является ли этот ентити живым */
    public boolean isEntityAlive()
    {
        return !this.isDead;
    }

	/** Убить ентити */
    public void kill()
    {
    	this.isDead = true;
    }

	/** Логическая функция ентити */
    public void onEntityUpdate()
    {
    	lifeTime++;
    	
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

	/** Возвращает текстуру ентити */
    public String getTexture()
    {
    	return "";
    }

	/** Должен ли ентити деспауниться */
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





















