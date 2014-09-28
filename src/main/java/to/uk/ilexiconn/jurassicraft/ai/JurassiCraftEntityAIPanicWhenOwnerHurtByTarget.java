package to.uk.ilexiconn.jurassicraft.ai;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAITarget;
import to.uk.ilexiconn.jurassicraft.entity.EntityJurassiCraftLandCoward;

public class JurassiCraftEntityAIPanicWhenOwnerHurtByTarget extends EntityAITarget
{
	EntityJurassiCraftLandCoward cowardTameable;
    EntityLivingBase whoTheOwnerIsAttacking;

    public JurassiCraftEntityAIPanicWhenOwnerHurtByTarget(EntityJurassiCraftLandCoward entityTameable)
    {
        super(entityTameable, false);
        this.cowardTameable = entityTameable;
        this.setMutexBits(1);
    }

    @Override
    public boolean shouldExecute()
    {
        if (!this.cowardTameable.isTamed())
        {
            return false;
        }
        else
        {
            if (this.cowardTameable.getOwner() != null) {
                this.whoTheOwnerIsAttacking = (EntityLivingBase) this.cowardTameable.getOwner().getAITarget();
                return whoTheOwnerIsAttacking != null;
            } else {
            	return false;
            }
        }
    }

    @Override
    public void startExecuting()
    {
        if (!this.cowardTameable.isPanicking())
        	this.cowardTameable.setFleeingTick(75 + this.cowardTameable.worldObj.rand.nextInt(100));
        super.startExecuting();
    }
}