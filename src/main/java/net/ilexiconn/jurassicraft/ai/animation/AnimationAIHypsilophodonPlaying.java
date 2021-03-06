package net.ilexiconn.jurassicraft.ai.animation;

import net.ilexiconn.jurassicraft.ai.AIAnimation;
import net.ilexiconn.jurassicraft.entity.dinosaurs.EntityHypsilophodon;
import net.ilexiconn.jurassicraft.enums.JurassiCraftAnimationIDs;

public class AnimationAIHypsilophodonPlaying extends AIAnimation
{
    private EntityHypsilophodon hypsilophodon;
    private double destX;
    private double destZ;
    private double friendSpeedX;
    private double friendSpeedZ;
    private double friendPrevPosX;
    private double friendPrevPosZ;

    public AnimationAIHypsilophodonPlaying(EntityHypsilophodon hypsilophodon)
    {
        super(hypsilophodon);
        this.hypsilophodon = hypsilophodon;
    }

    @Override
    public int getAnimationId()
    {
        return JurassiCraftAnimationIDs.PLAYING.animID();
    }

    @Override
    public boolean isAutomatic()
    {
        return true;
    }

    @Override
    public int getDuration()
    {
        return 70;
    }

    @Override
    public void startExecuting()
    {
        super.startExecuting();
    }

    @Override
    public void updateTask()
    {
        if (this.hypsilophodon.getCreatureToAttack() != null && this.hypsilophodon.getCreatureToAttack() instanceof EntityHypsilophodon)
        {
            EntityHypsilophodon friend = (EntityHypsilophodon) this.hypsilophodon.getCreatureToAttack();

            if (this.hypsilophodon.getAnimationTick() < 5)
            {
                this.hypsilophodon.getLookHelper().setLookPositionWithEntity(friend, 30F, 30F);
            }

            if (this.hypsilophodon.getAnimationTick() == 6)
            {
                this.friendPrevPosX = friend.posX;
                this.friendPrevPosZ = friend.posZ;
            }

            if (hypsilophodon.getAnimationTick() == 7)
            {
                this.friendSpeedX = friend.posX - this.friendPrevPosX;
                this.friendSpeedZ = friend.posZ - this.friendPrevPosZ;
                this.destX = friend.posX + this.friendSpeedX * 12.0D;
                this.destZ = friend.posZ + this.friendSpeedZ * 12.0D;
                double d = Math.sqrt((this.destX - this.hypsilophodon.posX) * (this.destX - this.hypsilophodon.posX) + (this.destZ - this.hypsilophodon.posZ) * (this.destZ - this.hypsilophodon.posZ));
                double a = Math.atan2((this.destZ - this.hypsilophodon.posZ), (this.destX - this.hypsilophodon.posX));

                this.hypsilophodon.motionX = (d / 6.0D) * Math.cos(a);
                this.hypsilophodon.motionZ = (d / 6.0D) * Math.sin(a);
                this.hypsilophodon.motionY = 0.3D;
            }
        }
    }

    @Override
    public void resetTask()
    {
        super.resetTask();
        this.hypsilophodon.setCreatureToAttack(null);
    }
}
