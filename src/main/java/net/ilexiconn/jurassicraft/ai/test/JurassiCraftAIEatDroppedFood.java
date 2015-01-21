package net.ilexiconn.jurassicraft.ai.test;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.item.EntityItem;

public class JurassiCraftAIEatDroppedFood extends EntityAIBase
{
	private EntityJurassiCraftSmart creature;
	private EntityItem droppedFood;
	private double timeTryingToEat;
	private double searchDistance;

	public JurassiCraftAIEatDroppedFood(EntityJurassiCraftSmart entity, double distance)
	{
		this.creature = entity;
		this.searchDistance = distance;
		this.timeTryingToEat = 0;
	}

	@Override
	public boolean shouldExecute() {
		if (this.creature.getAttackTarget() != null || this.creature.isSitting() || this.creature.isFlying() || this.creature.isFleeing() || this.creature.isAttacking() || this.creature.isDefending() || this.creature.isEating() || this.creature.isDrinking()) {
			return false;
		}
		else if (this.creature.getRNG().nextInt(25) == 0)
		{
			List nearEntityList = this.creature.worldObj.getEntitiesWithinAABBExcludingEntity(this.creature, this.creature.boundingBox.expand(this.searchDistance, this.searchDistance / 2.0D, this.searchDistance));
			if (!nearEntityList.isEmpty())
			{
				for (int i = nearEntityList.size() - 1; i > -1; i--)
				{
					Entity item = (Entity) nearEntityList.get(i);
					if (item instanceof EntityItem)
					{
						this.droppedFood = (EntityItem) item;
						if (this.creature.getCreature().isFavoriteFood(this.droppedFood.getEntityItem().getItem()))
						{
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	@Override
	public void startExecuting()
	{
		this.creature.setDefending(false);
		this.creature.setAttacking(false);
		this.creature.setBreeding(false);
		this.creature.setPlaying(false);
		this.creature.setSocializing(false);
		this.creature.setEating(false);
		this.creature.setDrinking(false);
		this.creature.setSitting(false, null);
		this.creature.getNavigator().tryMoveToXYZ(droppedFood.posX, droppedFood.posY, droppedFood.posZ, this.creature.getCreatureSpeed());
		this.timeTryingToEat = 0;
		super.startExecuting();
	}

	@Override
	public void updateTask() {
		double distance = Math.sqrt(Math.pow((double) (this.creature.posX - this.droppedFood.posX), 2.0D) + Math.pow((double) (this.creature.posY - this.droppedFood.posY), 2.0D) + Math.pow((double) (this.creature.posZ - this.droppedFood.posZ), 2.0D));
		if (distance < 1.2D) {
			this.droppedFood.setDead();
			this.creature.setEating(true);
		} else {
			this.timeTryingToEat++;
			if (this.creature.getNavigator().noPath())
				this.creature.getNavigator().tryMoveToXYZ(this.droppedFood.posX, this.droppedFood.posY, this.droppedFood.posZ, this.creature.getCreatureSpeed());
		}
	}

	@Override
	public boolean continueExecuting() {
		return this.timeTryingToEat < 125 && this.droppedFood.isEntityAlive() && this.creature.isEntityAlive() && !this.creature.isSitting() && this.creature.riddenByEntity == null && !this.creature.isAttacking() && !this.creature.isDefending();
	}

	@Override
	public void resetTask() {
		this.creature.getNavigator().clearPathEntity();
		this.droppedFood = (EntityItem) null;
		this.timeTryingToEat = 0;
	}
}
