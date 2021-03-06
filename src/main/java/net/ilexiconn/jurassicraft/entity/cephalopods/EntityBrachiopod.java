package net.ilexiconn.jurassicraft.entity.cephalopods;

import net.ilexiconn.jurassicraft.ModItems;
import net.ilexiconn.jurassicraft.entity.EntityWaterCreature;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityBrachiopod extends EntityWaterCreature
{
    public int openMouthDummyTimer;

    public EntityBrachiopod(World world)
    {
        super(world);
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(10.0);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.0);
    }

    protected void entityInit()
    {
        super.entityInit();
        this.dataWatcher.addObject(20, (Object) 0);
        this.dataWatcher.addObject(21, (Object) 1);
    }

    public int getOpenMouth()
    {
        return this.dataWatcher.getWatchableObjectInt(20);
    }

    public void setOpenMouth(final int par1)
    {
        this.dataWatcher.updateObject(20, (Object) par1);
    }

    public float getOpenMouth(final float var1)
    {
        return this.getOpenMouth() / var1;
    }

    public void writeEntityToNBT(final NBTTagCompound par1NBTTagCompound)
    {
        super.writeEntityToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setInteger("openMouth", this.getOpenMouth());
        par1NBTTagCompound.setInteger("pearl", (int) (this.hasPearl() ? 1 : 0));
    }

    public void readEntityFromNBT(final NBTTagCompound par1NBTTagCompound)
    {
        super.readEntityFromNBT(par1NBTTagCompound);
        this.setOpenMouth(par1NBTTagCompound.getInteger("openMouth"));
        this.setHasPearl(par1NBTTagCompound.getInteger("pearl") == 1);
    }

    public void resetMouth(final int plusOpenMouthTimer)
    {
        this.openMouthDummyTimer += this.rand.nextInt(250) + plusOpenMouthTimer;
        this.setOpenMouth(0);
    }

    public boolean hasPearl()
    {
        return this.dataWatcher.getWatchableObjectInt(21) == 1;
    }

    public void setHasPearl(final boolean par1)
    {
        if (par1)
        {
            this.dataWatcher.updateObject(21, 1);
            return;
        }
        this.dataWatcher.updateObject(21, 0);
    }

    public void onUpdate()
    {
        super.onUpdate();
        if (!this.worldObj.isRemote)
        {
            if (this.openMouthDummyTimer != -1)
            {
                --this.openMouthDummyTimer;
            }
            if (this.openMouthDummyTimer == 0 && this.getOpenMouth() == 0)
            {
                this.setOpenMouth(this.getOpenMouth() - 1);
                this.openMouthDummyTimer = -1;
            }
            if (this.getOpenMouth() != 0 && this.getOpenMouth() > -65)
            {
                this.setOpenMouth(this.getOpenMouth() - 1);
            }
        }
    }

    public boolean interact(final EntityPlayer player)
    {
        final ItemStack item = player.getCurrentEquippedItem();
        if (item != null && item.getItem() == ModItems.net && this.hasPearl())
        {
            if (this.getOpenMouth() == -65 && this.worldObj.rand.nextInt(3) == 0)
            {
                this.playSound("jurassicraft:brachiopod.slam", 0.1f, this.getSoundPitch());
                this.setHasPearl(false);
                if (!this.worldObj.isRemote)
                {
                    // this.entityDropItem(new ItemStack(ModItems.multiItems, 1, 8), 0.0f);
                }
                if (!player.capabilities.isCreativeMode)
                {
                    item.damageItem(1, player);
                }
                this.resetMouth(500);
            }
            else if (this.getOpenMouth() != 0)
            {
                this.playSound("jurassicraft:brachiopod.slam", 0.1f, this.getSoundPitch());
                // player.attackEntityFrom(CarboniferousApi.brachiopodDamage, 1.0f);
                this.resetMouth(75);
            }
        }
        else if (this.getOpenMouth() != 0)
        {
            this.playSound("jurassicraft:brachiopod.slam", 0.1f, this.getSoundPitch());
            // player.attackEntityFrom(CarboniferousApi.brachiopodDamage, 2.0f);
            this.resetMouth(80);
        }
        return true;
    }
}
