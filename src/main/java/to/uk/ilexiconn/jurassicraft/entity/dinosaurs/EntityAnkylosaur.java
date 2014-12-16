package to.uk.ilexiconn.jurassicraft.entity.dinosaurs;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import to.uk.ilexiconn.jurassicraft.ModItems;
import to.uk.ilexiconn.jurassicraft.Util;
import to.uk.ilexiconn.jurassicraft.ai.JurassiCraftEntityAIEatDroppedFood;
import to.uk.ilexiconn.jurassicraft.ai.JurassiCraftEntityAIFollowFood;
import to.uk.ilexiconn.jurassicraft.ai.JurassiCraftEntityAIWander;
import to.uk.ilexiconn.jurassicraft.entity.EntityJurassiCraftLandProtective;
import to.uk.ilexiconn.jurassicraft.entity.IDinosaur;

public class EntityAnkylosaur extends EntityJurassiCraftLandProtective
		implements IDinosaur {

	public EntityAnkylosaur(World world) {
		super(world, (byte) Util.classToId(EntityAnkylosaur.class), 1);
		this.getNavigator().setAvoidsWater(true);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(2, this.aiSit);
		this.tasks.addTask(4, new JurassiCraftEntityAIFollowFood(this,
				1.1D * this.getCreatureSpeed()));
		this.tasks.addTask(4, new JurassiCraftEntityAIEatDroppedFood(this,
				16.0D));
		this.tasks.addTask(5, new JurassiCraftEntityAIWander(this, 0.7D * this.getCreatureSpeed()));
		this.tasks.addTask(6, new EntityAIWatchClosest(this,
				EntityPlayer.class, 6.0F));
		this.tasks.addTask(6, new EntityAILookIdle(this));
		this.setCreatureExperiencePoints(5000);
	}
	
	@Override
	public double getMountedYOffset() {
		return (double) this.getYBouningBox() * 1.05D;
	}

	@Override
	public int getTalkInterval() {
		return 350;
	}

	@Override
	public String getLivingSound() {
		if (this.rand.nextInt(2) == 0) {
			return Util.getCreatureFromId(this.getCreatureID()).livingSound1;
		} else {
			return Util.getCreatureFromId(this.getCreatureID()).livingSound2;
		}
	}

	@Override
	public String getHurtSound() {
		return Util.getCreatureFromId(this.getCreatureID()).hurtSound;
	}

	@Override
	public String getDeathSound() {
		return Util.getCreatureFromId(this.getCreatureID()).deathSound;
	}

	@Override
	public Item getDropItem() {
		return Util.getMeat(this.getCreatureID());
	}

	@Override
	protected void dropFewItems(boolean recentlyBeenHit, int enchantBonus) {
		float developmentFraction = this.getGrowthStage() / 120.0F;
		int count = Math.round(1 + (3.0F * developmentFraction)
				+ this.rand.nextInt(2 + (int) (3.0F * developmentFraction))
				+ this.rand.nextInt(1 + enchantBonus));
		if (this.isBurning()) {
			this.dropItem(ModItems.dinoSteak, count);
		} else {
			this.dropItem(Util.getMeat(this.getCreatureID()), count);
		}
	}
	
}