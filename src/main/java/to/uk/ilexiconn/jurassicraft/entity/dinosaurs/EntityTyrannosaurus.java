package to.uk.ilexiconn.jurassicraft.entity.dinosaurs;

import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import thehippomaster.AnimationAPI.AnimationAPI;
import to.uk.ilexiconn.jurassicraft.ModItems;
import to.uk.ilexiconn.jurassicraft.Util;
import to.uk.ilexiconn.jurassicraft.ai.JurassiCraftEntityAIEatDroppedFood;
import to.uk.ilexiconn.jurassicraft.ai.JurassiCraftEntityAIFollowFood;
import to.uk.ilexiconn.jurassicraft.ai.JurassiCraftEntityAITargetIfHasAgeAndNonTamed;
import to.uk.ilexiconn.jurassicraft.client.animation.AITyrannosaurusRoar;
import to.uk.ilexiconn.jurassicraft.entity.EntityJurassiCraftLandAggressive;
import to.uk.ilexiconn.jurassicraft.entity.IDinosaur;
import to.uk.ilexiconn.jurassicraft.utility.ControlledParam;

public class EntityTyrannosaurus extends EntityJurassiCraftLandAggressive implements IDinosaur
{

    private int stepCount = 0;
    private float shakeCount = 0;
    public ControlledParam roarCount = new ControlledParam(0F, 0F, 0.5F, 0F);
    public ControlledParam roarTiltDegree = new ControlledParam(0F, 0F, 1F, 0F);

    public EntityTyrannosaurus(World world)
    {
        super(world, (byte) Util.classToId(EntityTyrannosaurus.class));
        this.getNavigator().setAvoidsWater(true);
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIWander(this, this.getCreatureSpeed()));
        this.tasks.addTask(4, this.aiSit);
        this.tasks.addTask(2, new AITyrannosaurusRoar(this));
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, this.getCreatureSpeed()));
        this.tasks.addTask(6, new JurassiCraftEntityAIFollowFood(this, 1.2D * this.getCreatureSpeed()));
        this.tasks.addTask(6, new JurassiCraftEntityAIEatDroppedFood(this, 16.0D));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(7, new EntityAILookIdle(this));
        targetTasks.addTask(3, new JurassiCraftEntityAITargetIfHasAgeAndNonTamed(this, EntityStegosaur.class, 0, 0.5F));
        targetTasks.addTask(3, new JurassiCraftEntityAITargetIfHasAgeAndNonTamed(this, EntityTriceratops.class, 0, 0.6F));
        targetTasks.addTask(3, new JurassiCraftEntityAITargetIfHasAgeAndNonTamed(this, EntityGallimimus.class, 0, 0.3F));
        targetTasks.addTask(3, new JurassiCraftEntityAITargetIfHasAgeAndNonTamed(this, EntityOviraptor.class, 0, 0.2F));
        targetTasks.addTask(3, new JurassiCraftEntityAITargetIfHasAgeAndNonTamed(this, EntityBrachiosaur.class, 0, 0.6F));

        this.setCreatureExperiencePoints(6000);
    }

    public String getLivingSound()
    {
        // if(isRoaring == false){
        // isRoaring = true;
        int I = rand.nextInt(3) + 1;
        if (I == 1)
        {
            this.playSound("jurassicraft:trex1", 5.0F, this.getSoundPitch());
            if (animID == 0 && this.getAttackTarget() == null)
            {
                AnimationAPI.sendAnimPacket(this, 1);
            }
            /*
			 * else if(this.getAttackTarget() != null) {
			 * roarCount.thereAndBack(0F, 0.1F, 1, 40);
			 * System.out.println(roarCount.change); }
			 */
            return null;
        }
        else if (I == 2)
        {
            this.playSound("jurassicraft:trex2", 5.0F, this.getSoundPitch());
            return null;
        }
        else
        {
            return null;
        }
        // }
        // return null;
    }

    public String getHurtSound()
    {
        this.playSound("jurassicraft:trex3", 5.0F, this.getSoundPitch());
        return null;
    }

    public String getDeathSound()
    {
        this.playSound("jurassicraft:trex3", 5.0F, this.getSoundPitch());
        return null;
    }

    public void onUpdate()
    {
        super.onUpdate();
        roarCount.update();
        roarTiltDegree.update();
        if (animID == 1 && animTick == 22)
            this.roarTiltDegree.thereAndBack(0F, 0.1F, 1F, 20);
    }

    @Override
    public Item getDropItem()
    {
        return Util.getMeat(this.getCreatureID());
    }

	@Override
	protected void dropFewItems(boolean recentlyBeenHit, int enchantBonus) 
	{
		float developmentFraction = this.getGrowthStage() / 120.0F;
		int count = Math.round(1 + (4.0F * developmentFraction) + this.rand.nextInt(2 + (int) (4.0F * developmentFraction)) + this.rand.nextInt(1 + enchantBonus));
		if (this.isBurning()) 
		{
			this.dropItem(ModItems.dinoSteak, count);
		} 
		else 
		{
			this.dropItem(Util.getMeat(this.getCreatureID()), count);
		}
	}
}
