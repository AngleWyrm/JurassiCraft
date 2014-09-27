package to.uk.ilexiconn.jurassicraft.ai;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import to.uk.ilexiconn.jurassicraft.Util;
import to.uk.ilexiconn.jurassicraft.entity.EntityJurassiCraftCreature;

public class JurassiCraftEntityAIFollowFood extends EntityAIBase
{
    private EntityJurassiCraftCreature temptedEntity;
    private double speed;
    private double targetX;
    private double targetY;
    private double targetZ;
    private double rotationPitchOfThePlayer;
    private double rotationYawOfThePlayer;
    private EntityPlayer temptingPlayer;
    private int delayTemptCounter;
    private boolean isRunning;
    private boolean scaredByPlayerMovement;
    private boolean avoidWater;

    public JurassiCraftEntityAIFollowFood(EntityJurassiCraftCreature creature, double velocity, boolean beAfraidOfMovement)
    {
        this.temptedEntity = creature;
        this.speed = velocity;
        this.scaredByPlayerMovement = beAfraidOfMovement;
        this.setMutexBits(3);
    }

    public boolean shouldExecute()
    {
        if (this.delayTemptCounter > 0)
        {
            this.delayTemptCounter--;
            return false;
        }
        else
        {
            this.temptingPlayer = this.temptedEntity.worldObj.getClosestPlayerToEntity(this.temptedEntity, 10.0D);

            if (this.temptingPlayer == null)
            {
                return false;
            }
            else
            {
                ItemStack itemstack = this.temptingPlayer.getCurrentEquippedItem();
                return itemstack == null ? false : (Util.isFavoriteFood(this.temptedEntity.getCreatureID(), itemstack.getItem()) && this.temptedEntity.getAttackTarget() == null);
            }
        }
    }

    public boolean continueExecuting()
    {
        if (this.scaredByPlayerMovement)
        {
            if (this.temptedEntity.getDistanceSqToEntity(this.temptingPlayer) < 36.0D)
            {
                if (this.temptingPlayer.getDistanceSq(this.targetX, this.targetY, this.targetZ) > 0.010000000000000002D)
                {
                    return false;
                }
                if (Math.abs((double) this.temptingPlayer.rotationPitch - this.rotationPitchOfThePlayer) > 5.0D || Math.abs((double) this.temptingPlayer.rotationYaw - this.rotationYawOfThePlayer) > 5.0D)
                {
                    return false;
                }
            }
            else
            {
                this.targetX = this.temptingPlayer.posX;
                this.targetY = this.temptingPlayer.posY;
                this.targetZ = this.temptingPlayer.posZ;
            }
            this.rotationPitchOfThePlayer = (double) this.temptingPlayer.rotationPitch;
            this.rotationYawOfThePlayer = (double) this.temptingPlayer.rotationYaw;
        }
        return this.shouldExecute();
    }

    public void startExecuting()
    {
        this.targetX = this.temptingPlayer.posX;
        this.targetY = this.temptingPlayer.posY;
        this.targetZ = this.temptingPlayer.posZ;
        this.isRunning = true;
        this.avoidWater = this.temptedEntity.getNavigator().getAvoidsWater();
        this.temptedEntity.getNavigator().setAvoidsWater(false);
    }

    public void resetTask()
    {
        this.temptingPlayer = null;
        this.temptedEntity.getNavigator().clearPathEntity();
        this.delayTemptCounter = 100;
        this.isRunning = false;
        this.temptedEntity.getNavigator().setAvoidsWater(this.avoidWater);
    }

    public void updateTask()
    {
        this.temptedEntity.getLookHelper().setLookPositionWithEntity(this.temptingPlayer, 30.0F, (float) this.temptedEntity.getVerticalFaceSpeed());
        if (this.temptedEntity.getDistanceSqToEntity(this.temptingPlayer) < 6.25D)
        {
            this.temptedEntity.getNavigator().clearPathEntity();
        }
        else
        {
            this.temptedEntity.getNavigator().tryMoveToEntityLiving(this.temptingPlayer, this.speed);
        }
    }

    public boolean isRunning()
    {
        return this.isRunning;
    }
}