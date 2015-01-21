package net.ilexiconn.jurassicraft.entity.dinosaurs;

import net.ilexiconn.jurassicraft.ai.test.EntityJurassiCraftSmart;
import net.ilexiconn.jurassicraft.ai.test.JurassiCraftAIEatDroppedFood;
import net.ilexiconn.jurassicraft.ai.test.JurassiCraftAIEating;
import net.ilexiconn.jurassicraft.ai.test.JurassiCraftAIFlee;
import net.ilexiconn.jurassicraft.ai.test.JurassiCraftAIFollowFood;
import net.ilexiconn.jurassicraft.ai.test.JurassiCraftAISit;
import net.ilexiconn.jurassicraft.ai.test.JurassiCraftAIWander;
import net.ilexiconn.jurassicraft.client.model.modelbase.ChainBuffer;
import net.ilexiconn.jurassicraft.entity.CreatureManager;
import net.ilexiconn.jurassicraft.interfaces.IDinosaur;
import net.ilexiconn.jurassicraft.interfaces.IHerbivore;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityGallimimus extends EntityJurassiCraftSmart implements IDinosaur, IHerbivore
{
	public ChainBuffer tailBuffer = new ChainBuffer(4);
	
    public EntityGallimimus(World world)
    {
        super(world, CreatureManager.classToCreature(EntityGallimimus.class));
        this.getNavigator().setAvoidsWater(true);
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(2, new JurassiCraftAISit(this));
        this.tasks.addTask(2, new JurassiCraftAIFlee(this, 50, 1.1D * this.getCreatureSpeed()));
        this.tasks.addTask(4, new JurassiCraftAIFollowFood(this, 100, 1.1D * this.getCreatureSpeed()));
        this.tasks.addTask(4, new JurassiCraftAIEatDroppedFood(this, 16.0D));
        this.tasks.addTask(4, new JurassiCraftAIEating(this, 20));
        this.tasks.addTask(5, new EntityAIAvoidEntity(this, EntityTyrannosaurus.class, 12.0F, this.getCreatureSpeed(), 1.2D * this.getCreatureSpeed()));
        this.tasks.addTask(6, new JurassiCraftAIWander(this, 30, 0.6D * this.getCreatureSpeed()));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 10.0F));
        this.tasks.addTask(7, new EntityAILookIdle(this));
        this.setCreatureExperiencePoints(1000);
    }

    @Override
    public double getMountedYOffset()
    {
        return 0.7D * (double) this.getYBouningBox();
    }

    @Override
    public int getTalkInterval()
    {
        return 350;
    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();
        this.tailBuffer.calculateChainSwingBuffer(45.0F, 3, 3.8F, this);
    }
    
    @Override
    protected void dropFewItems(boolean recentlyBeenHit, int enchantBonus)
    {
    	float developmentFraction = this.getGrowthStage() / 120.0F;
        int count = Math.round(1 + (2.5F * developmentFraction) + this.rand.nextInt(1 + (int) (2.0F * developmentFraction)) + this.rand.nextInt(1 + enchantBonus));
    	if (!this.isBurning())
        {
            this.dropItemStackWithGenetics(new ItemStack(this.getCreature().getMeat(), count));
        }
        else
        {
            this.dropItem(this.getCreature().getSteak(), count);
        }
    }
}