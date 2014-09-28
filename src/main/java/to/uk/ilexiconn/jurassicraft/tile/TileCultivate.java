package to.uk.ilexiconn.jurassicraft.tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemBucketMilk;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import to.uk.ilexiconn.jurassicraft.Util;
import to.uk.ilexiconn.jurassicraft.enums.JurassiCraftFoodNutrients;
import to.uk.ilexiconn.jurassicraft.item.ItemDNA;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class TileCultivate extends TileEntity implements ISidedInventory
{
    public int animationTick;
    private Random random;
    private ItemStack[] slots = new ItemStack[4];
    private short cultivateTime;
    private int cultivateSpeed;
    private byte creatureID;
    private byte waterStored;
    private short proximateValue;
    private short mineralValue;
    private short vitaminValue;
    private short lipidValue;
    private final short maxValue = 3000;
    private float creatureSize;
    private boolean shouldUpdate;
    public final Map<Short, Byte> growthRateList = new HashMap<Short, Byte>();
    public int rotation;

    public TileCultivate()
    {
        this.random = new Random();
        this.cultivateSpeed = 100;
        this.creatureID = -1;
        this.waterStored = 0;
        this.cultivateTime = 0;
        this.proximateValue = 0;
        this.mineralValue = 0;
        this.vitaminValue = 0;
        this.lipidValue = 0;
        this.creatureSize = 0.0F;
    }

    /**
     * Returns the id for the rendering.
     */
    public byte getEmbryoID()
    {
        return this.creatureID;
    }

    /**
     * Returns the current hatchery time.
     */
    public void setCultivateTime(short time)
    {
        this.cultivateTime = time;
    }

    /**
     * Returns the current hatchery time.
     */
    public int getCultivateTime()
    {
        return this.cultivateTime;
    }

    /**
     * Returns the maximum value of nutrients that can be stored.
     */
    public int getMaximumValueOfNutrients()
    {
        return this.maxValue;
    }

    public float getCreatureSize()
    {
        return this.creatureSize;
    }

    public void setCreatureSize(float time, float speed)
    {
        if (speed > 0)
        {
            this.creatureSize = time / speed;
        }
        else
        {
            this.creatureSize = 0.0F;
        }
    }

    /**
     * Returns the current water stored.
     */
    public byte getWaterStored()
    {
        return this.waterStored;
    }

    /**
     * Returns true if there is water stored.
     */
    public boolean hasWater()
    {
        return (this.waterStored > 0);
    }

    /**
     * Returns the current proximate value.
     */
    public int getProximateValue()
    {
        return this.proximateValue;
    }

    /**
     * Returns true if there is proximate stored.
     */
    public boolean hasProximate()
    {
        return (this.proximateValue > 0);
    }

    /**
     * Returns the current minerals value.
     */
    public int getMineralValue()
    {
        return this.mineralValue;
    }

    /**
     * Returns true if there is mineral stored.
     */
    public boolean hasMineral()
    {
        return (this.mineralValue > 0);
    }

    /**
     * Returns the current vitamin value.
     */
    public int getVitaminValue()
    {
        return this.vitaminValue;
    }

    /**
     * Returns true if there is vitamin stored.
     */
    public boolean hasVitamin()
    {
        return (this.vitaminValue > 0);
    }

    /**
     * Returns the current value of lipids.
     */
    public int getLipidValue()
    {
        return this.lipidValue;
    }

    /**
     * Returns true if there is lipids stored.
     */
    public boolean hasLipid()
    {
        return (this.lipidValue > 0);
    }

    /**
     * Sets the water value.
     */
    public void setWaterStored(byte water)
    {
        this.waterStored = water;
    }

    /**
     * Sets the current value of proximate.
     */
    public void setProximateValue(short proximate)
    {
        this.proximateValue = proximate;
    }

    /**
     * Sets the current value of minerals.
     */
    public void setMineralValue(short mineral)
    {
        this.mineralValue = mineral;
    }

    /**
     * Sets the current value of vitamins.
     */
    public void setVitaminValue(short vitamin)
    {
        this.vitaminValue = vitamin;
    }

    /**
     * Sets the current value of lipids.
     */
    public void setLipidValue(short lipids)
    {
        this.lipidValue = lipids;
    }

    /**
     * Returns a percentage of the water stored scaled for X value.
     */
    public int getWaterStoredProgressScaled(int i)
    {
        return (this.waterStored * i) / 3;
    }

    /**
     * Sets the cultivate process speed.
     */
    public int setCultivateSpeed(int speed)
    {
        return this.cultivateSpeed = speed;
    }

    /**
     * Returns the number of ticks required to cultivate the creature.
     */
    public int getCultivateSpeed()
    {
        return this.cultivateSpeed;
    }

    /**
     * Returns a percentage of the hatchery process scaled for X value.
     */
    public int getcultivateTimeProgressScaled(int i)
    {
        if (this.getCultivateSpeed() <= 0)
        {
            this.setCultivateSpeed(100);
        }
        return (this.getCultivateTime() * i) / this.getCultivateSpeed();
    }

    /**
     * Returns a percentage of the proximate value scaled for X value.
     */
    public int getProximateBarScaled(int i)
    {
        return (this.getProximateValue() * i) / this.getMaximumValueOfNutrients();
    }

    /**
     * Returns a percentage of the mineral value scaled for X value.
     */
    public int getMineralBarScaled(int i)
    {
        return (this.getMineralValue() * i) / this.getMaximumValueOfNutrients();
    }

    /**
     * Returns a percentage of vitamins scaled for X value.
     */
    public int getVitaminBarScaled(int i)
    {
        return (this.getVitaminValue() * i) / this.getMaximumValueOfNutrients();
    }

    /**
     * Returns a percentage of lipids scaled for X value.
     */
    public int getLipidBarScaled(int i)
    {
        return (this.getLipidValue() * i) / this.getMaximumValueOfNutrients();
    }

    /**
     * Returns true if the hatchery time is larger than 0.
     */
    public boolean isHatching()
    {
        return (this.cultivateTime > 0);
    }

    /**
     * Returns true if the machine can consume the water bucket at slot[0].
     */
    private boolean canCansumeWaterBucket()
    {
        if (this.slots[0] != (ItemStack) null)
        {
            if (this.slots[0].getItem() == Items.water_bucket)
            {
                if (this.slots[1] == (ItemStack) null)
                {
                    return true;
                }
                else if (this.slots[1].getItem() == Items.bucket)
                {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Consumes water bucket from slot[0] if there is space for more water.
     */
    private void consumeWaterBucket()
    {
        if (!this.worldObj.isRemote)
        {
            if ((this.getWaterStored() + 1) > 3)
            {
                return;
            }
            else
            {
                this.slots[0].stackSize--;
                if (this.slots[0].stackSize <= 0)
                {
                    this.slots[0] = (ItemStack) null;
                }
                this.setWaterStored((byte) (this.getWaterStored() + 1));
                if (this.slots[1] == (ItemStack) null)
                {
                    ItemStack waterBucket = new ItemStack(Items.bucket);
                    this.slots[1] = waterBucket;
                }
                else
                {
                    this.slots[1].stackSize++;
                }
            }
        }
    }

    /**
     * Returns true if the machine can consume food.
     */
    private boolean canCansumeFood()
    {
        if (this.slots[3] == (ItemStack) null)
        {
            return false;
        }
        else
        {
            if ((proximateValue < this.getMaximumValueOfNutrients()) || (mineralValue < this.getMaximumValueOfNutrients()) || (vitaminValue < this.getMaximumValueOfNutrients()) || (lipidValue < this.getMaximumValueOfNutrients()))
            {
                if (JurassiCraftFoodNutrients.FOODLIST.containsKey(this.slots[3].getItem()))
                {
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * Consumes food from slot[7] if it is valid.
     */
    private void consumeFood()
    {
        if (!worldObj.isRemote)
        {
            int id = JurassiCraftFoodNutrients.FOODLIST.get(this.slots[3].getItem());
            if (this.slots[3].getItem() instanceof ItemBucketMilk)
            {
                this.slots[3] = (ItemStack) null;
                this.slots[3] = new ItemStack(Items.bucket);
            }
            else
            {
                this.slots[3].stackSize--;
                if (this.slots[3].stackSize <= 0)
                {
                    this.slots[3] = (ItemStack) null;
                }
            }
            if (proximateValue < this.getMaximumValueOfNutrients())
            {
                proximateValue = (short) (proximateValue + (800 + random.nextInt(201)) * (JurassiCraftFoodNutrients.values()[id].getProximate()));
                if (proximateValue > this.getMaximumValueOfNutrients())
                {
                    proximateValue = (short) this.getMaximumValueOfNutrients();
                }
            }
            if (mineralValue < this.getMaximumValueOfNutrients())
            {
                mineralValue = (short) (mineralValue + (900 + random.nextInt(101)) * (JurassiCraftFoodNutrients.values()[id].getMinerals()));
                if (mineralValue > this.getMaximumValueOfNutrients())
                {
                    mineralValue = (short) this.getMaximumValueOfNutrients();
                }
            }
            if (vitaminValue < this.getMaximumValueOfNutrients())
            {
                vitaminValue = (short) (vitaminValue + (900 + random.nextInt(101)) * (JurassiCraftFoodNutrients.values()[id].getVitamins()));
                if (vitaminValue > this.getMaximumValueOfNutrients())
                {
                    vitaminValue = (short) this.getMaximumValueOfNutrients();
                }
            }
            if (lipidValue < this.getMaximumValueOfNutrients())
            {
                lipidValue = (short) (lipidValue + (980 + random.nextInt(101)) * (JurassiCraftFoodNutrients.values()[id].getLipids()));
                if (lipidValue > this.getMaximumValueOfNutrients())
                {
                    lipidValue = (short) this.getMaximumValueOfNutrients();
                }
            }
        }
    }

    /**
     * Returns true if the machine can cultivate certain creature.
     */
    private boolean canCultivate()
    {
        if (this.slots[2] != null)
        {
            if (this.slots[2].hasTagCompound())
            {
                if (this.slots[2].getTagCompound().hasKey("Quality") && this.slots[2].getTagCompound().hasKey("DNA"))
                {
                    if (this.slots[2].getTagCompound().getInteger("Quality") >= 50)
                    {
                        this.creatureID = (byte) Util.getDNAArray().lastIndexOf(this.slots[2].getItem());
                        if (this.getProximateValue() < Util.getDinoByID(this.getEmbryoID()).minProximate || this.getMineralValue() < Util.getDinoByID(this.getEmbryoID()).minMinerals || this.getVitaminValue() < Util.getDinoByID(this.getEmbryoID()).minVitamins || this.getLipidValue() < Util.getDinoByID(this.getEmbryoID()).minLipids)
                        {
                            return false;
                        }
                        else
                        {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * Creates the specific creature for the current DNA setup.
     */
    private void cultivateCreature()
    {
        if (!canCultivate())
        {
            return;
        }
        else
        {
            NBTTagCompound compound = new NBTTagCompound();
            ItemStack cultivateResult = new ItemStack(((ItemDNA) this.slots[2].getItem()).getCorrespondingEgg(), 1, 0);
            compound.setInteger("EggQuality", this.slots[2].getTagCompound().getInteger("Quality"));
            compound.setString("EggDNA", this.slots[2].getTagCompound().getString("DNA"));
            cultivateResult.setTagCompound(compound);
            this.slots[2] = (ItemStack) null;
            this.slots[2] = cultivateResult;
            this.setCultivateTime((short) 0);
            this.setWaterStored((byte) 0);
            this.proximateValue = (short) (proximateValue - Util.getDinoByID(this.getEmbryoID()).minProximate);
            this.mineralValue = (short) (mineralValue - Util.getDinoByID(this.getEmbryoID()).minMinerals);
            this.vitaminValue = (short) (vitaminValue - Util.getDinoByID(this.getEmbryoID()).minVitamins);
            this.lipidValue = (short) (lipidValue - Util.getDinoByID(this.getEmbryoID()).minLipids);
        }
    }

    /**
     * Resets a list of values to update the size of the creature for rendering.
     */
    private void recalculateGrowthRate(int id)
    {
        if (id >= 0)
        {
            int speed = Util.getDinoByID(this.getEmbryoID()).cultivateSpeed;
            for (Byte i = 0; i <= 10 - 1; i++)
            {
                if (i > 0)
                {
                    this.growthRateList.put((short) ((i * speed) / 10), i);
                }
                else
                {
                    this.growthRateList.put((short) 2, (byte) 0);
                }
            }
        }
    }

    /**
     * Resets cultivateTime and creatureID, and updates render.
     */
    private void resetBaseValues()
    {
        this.cultivateTime = 0;
        this.cultivateSpeed = 100;
        this.creatureID = -1;
        this.growthRateList.clear();
        this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
    }

    /**
     * Check for any item stacks in all slots.
     */
    public boolean hasItems()
    {
        if (this.slots[0] != null || this.slots[1] != null || this.slots[2] != null || this.slots[3] != null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Check for any item stacks in the DNA slot.
     */
    private boolean hasEmptyDNASlot()
    {
        return (this.slots[2] == (ItemStack) null) ? true : false;
    }

    @Override
    public void updateEntity()
    {
        animationTick++;
        if (animationTick == Integer.MAX_VALUE) animationTick = 0;

        if (!this.worldObj.isRemote)
        {
            if (!this.isHatching())
            {
                if (this.canCansumeWaterBucket())
                {
                    this.consumeWaterBucket();
                }
                if (canCansumeFood())
                {
                    this.consumeFood();
                }
                if (this.getWaterStored() >= 3)
                {
                    if ((this.getProximateValue() > 0) && (this.getMineralValue() > 0) && (this.getVitaminValue() > 0) && (this.getLipidValue() > 0))
                    {
                        if (this.canCultivate())
                        {
                            this.cultivateSpeed = Util.getDinoByID(this.getEmbryoID()).cultivateSpeed;
                            this.recalculateGrowthRate(this.creatureID);
                            this.setCultivateTime((short) 1);
                            this.creatureSize = 0.0F;
                            this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
                        }
                    }
                }
            }
            else
            {
                this.cultivateTime++;
                if (this.growthRateList.containsKey(cultivateTime))
                {
                    this.setCreatureSize(this.cultivateTime, this.cultivateSpeed);
                    this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
                }
                else if (shouldUpdate)
                {
                    this.setCreatureSize(this.cultivateTime, this.cultivateSpeed);
                    this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
                    this.shouldUpdate = false;
                }
                if (this.cultivateTime >= this.cultivateSpeed)
                {
                    this.cultivateCreature();
                    this.resetBaseValues();
                }
                else
                {
                    if (this.hasEmptyDNASlot())
                    {
                        this.resetBaseValues();
                    }
                }
            }
        }
    }

    public void cancelHatching(float progress)
    {
    	if (this.isHatching()) 
    	{
            this.setProximateValue((short) (this.getProximateValue() - (int) (progress * Util.getDinoByID(this.getEmbryoID()).minProximate)));
            this.setMineralValue((short) (this.getMineralValue() - (int) (progress * Util.getDinoByID(this.getEmbryoID()).minMinerals)));
            this.setVitaminValue((short) (this.getVitaminValue() - (int) (progress * Util.getDinoByID(this.getEmbryoID()).minVitamins)));
            this.setLipidValue((short) (this.getLipidValue() - (int) (progress * Util.getDinoByID(this.getEmbryoID()).minLipids)));
            if (progress >= 0.75F)
            {
                this.setWaterStored((byte) 0);
            }
            else if (progress >= 0.5F)
            {
                this.setWaterStored((byte) 1);
            }
            else
            {
                this.setWaterStored((byte) 2);
            }
            this.cultivateTime = 0;
            this.cultivateSpeed = 100;
            this.creatureID = -1;
            this.growthRateList.clear();
            this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
    	}
    }

    @Override
    public int getSizeInventory()
    {
        return this.slots.length;
    }

    @Override
    public ItemStack getStackInSlot(int i)
    {
        return this.slots[i];
    }

    @Override
    public ItemStack decrStackSize(int i, int stackSize)
    {
        if (this.slots[i] != null)
        {
            ItemStack splitedStack;
            if (this.slots[i].stackSize <= stackSize)
            {
                splitedStack = this.slots[i];
                this.slots[i] = null;
                return splitedStack;
            }
            else
            {
                splitedStack = this.slots[i].splitStack(stackSize);
                if (this.slots[i].stackSize == 0)
                {
                    this.slots[i] = null;
                }
                return splitedStack;
            }
        }
        else
        {
            return null;
        }
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int i)
    {
        if (this.slots[i] != null)
        {
            ItemStack itemStack = this.slots[i];
            this.slots[i] = null;
            return itemStack;
        }
        else
        {
            return null;
        }
    }

    @Override
    public void setInventorySlotContents(int i, ItemStack itemStack)
    {
        this.slots[i] = itemStack;
        if (itemStack != null && itemStack.stackSize > this.getInventoryStackLimit())
        {
            itemStack.stackSize = this.getInventoryStackLimit();
        }
    }

    @Override
    public String getInventoryName()
    {
        return "Hatchery";
    }

    @Override
    public boolean hasCustomInventoryName()
    {
        return true;
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player)
    {
        return ((this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : player.getDistanceSq((double) this.xCoord + 0.5D, (double) this.yCoord + 0.5D, (double) this.zCoord + 0.5D) <= 64.0D) && !this.isHatching());
    }

    @Override
    public void openInventory()
    {

    }

    @Override
    public void closeInventory()
    {

    }

    @Override
    public boolean isItemValidForSlot(int i, ItemStack itemStack)
    {
        return false;
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int i)
    {
        return new int[]{0};
    }

    @Override
    public boolean canInsertItem(int i, ItemStack itemStack, int j)
    {
        return false;
    }

    @Override
    public boolean canExtractItem(int i, ItemStack itemStack, int j)
    {
        return false;
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);
        nbt.setByte("CreatureID", creatureID);
        nbt.setByte("Water", waterStored);
        nbt.setShort("Proximate", proximateValue);
        nbt.setShort("Mineral", mineralValue);
        nbt.setShort("Vitamin", vitaminValue);
        nbt.setShort("Lipid", lipidValue);
        nbt.setShort("cultivateTime", cultivateTime);
        nbt.setFloat("CreatureSize", creatureSize);
        NBTTagList list = new NBTTagList();
        for (int i = 0; i < this.slots.length; i++)
        {
            if (this.slots[i] != null)
            {
                NBTTagCompound compound = new NBTTagCompound();
                compound.setByte("Slot", (byte) i);
                this.slots[i].writeToNBT(compound);
                list.appendTag(compound);
            }
        }
        nbt.setInteger("rotation", rotation);
        nbt.setTag("Items", list);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
        this.shouldUpdate = true;
        NBTTagList list = nbt.getTagList("Items", 10);
        this.slots = new ItemStack[this.getSizeInventory()];
        for (int i = 0; i < list.tagCount(); i++)
        {
            NBTTagCompound compound = (NBTTagCompound) list.getCompoundTagAt(i);
            byte k = compound.getByte("Slot");

            if (k >= 0 && k < this.slots.length)
            {
                this.slots[k] = ItemStack.loadItemStackFromNBT(compound);
            }
        }
        this.creatureID = nbt.getByte("CreatureID");
        this.creatureSize = nbt.getShort("CreatureSize");
        this.waterStored = nbt.getByte("Water");
        this.cultivateTime = nbt.getShort("cultivateTime");
        this.proximateValue = nbt.getShort("Proximate");
        this.mineralValue = nbt.getShort("Mineral");
        this.vitaminValue = nbt.getShort("Vitamin");
        this.lipidValue = nbt.getShort("Lipid");
        rotation = nbt.getInteger("rotation");
        if (creatureID >= 0)
        {
            this.setCultivateSpeed(Util.getDinoByID(this.getEmbryoID()).cultivateSpeed);
            this.recalculateGrowthRate(creatureID);
        }
    }

    @Override
    public Packet getDescriptionPacket()
    {
        NBTTagCompound compound = new NBTTagCompound();
        this.writeToNBT(compound);
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, this.blockMetadata, compound);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet)
    {
        NBTTagCompound compound = packet.func_148857_g();
        this.readFromNBT(compound);
    }
}