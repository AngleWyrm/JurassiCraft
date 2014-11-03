package to.uk.ilexiconn.jurassicraft.item;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import to.uk.ilexiconn.jurassicraft.ModCreativeTabs;
import to.uk.ilexiconn.jurassicraft.Util;
import to.uk.ilexiconn.jurassicraft.entity.mammal.EntityPregnantCow;
import to.uk.ilexiconn.jurassicraft.entity.mammal.EntityPregnantHorse;
import to.uk.ilexiconn.jurassicraft.entity.mammal.EntityPregnantPig;
import to.uk.ilexiconn.jurassicraft.entity.mammal.EntityPregnantSheep;

public class ItemMammalSyringe extends Item
{
    public String mammalName;
    public static final HashSet<String> creaturesFromCow = new HashSet<String>(Arrays.asList("Mammoth", "Arsinoitherium", "Basilosaurus", "Uintatherium", "Paraceratherium", "Deinotherium", "Leptictidium"));
    public static final HashSet<String> creaturesFromPig = new HashSet<String>(Arrays.asList("Mammoth", "Arsinoitherium", "Basilosaurus", "Uintatherium", "Paraceratherium", "Deinotherium", "Leptictidium"));
    public static final HashSet<String> creaturesFromHorse = new HashSet<String>(Arrays.asList("Mammoth", "Arsinoitherium", "Basilosaurus", "Uintatherium", "Paraceratherium", "Deinotherium", "Leptictidium"));
    public static final HashSet<String> creaturesFromSheep = new HashSet<String>(Arrays.asList("Mammoth", "Arsinoitherium", "Basilosaurus", "Uintatherium", "Paraceratherium", "Deinotherium", "Leptictidium"));

    public ItemMammalSyringe(String mammal)
    {
        super();
        this.setUnlocalizedName(mammal + "_Syringe");
        this.setTextureName(Util.getModId() + mammal + "_Syringe");
        this.setCreativeTab(ModCreativeTabs.items);
        this.mammalName = mammal;
    }

    public String getEggDNASequence(ItemStack syringe)
    {
        if (syringe.hasTagCompound())
        {
            if (syringe.getTagCompound().hasKey("SyringeDNA"))
            {
                return syringe.getTagCompound().getString("SyringeDNA");
            }
        }
        System.out.println("ERROR! Syringe sequence was not determined yet!");
        return JurassiCraftDNAHandler.createDefaultDNA();
    }

    public int getEggQuality(ItemStack syringe)
    {
        if (syringe.hasTagCompound())
        {
            if (syringe.getTagCompound().hasKey("SyringeQuality"))
            {
                return syringe.getTagCompound().getInteger("SyringeQuality");
            }
        }
        System.out.println("ERROR! Syringe quality was not determined yet!");
        return 75;
    }

    @Override
    public void addInformation(ItemStack syringe, EntityPlayer player, List list, boolean flag)
    {
        if (syringe.hasTagCompound())
        {
            if (syringe.getTagCompound().hasKey("SyringeDNA"))
            {
                list.add(EnumChatFormatting.GREEN + "DNA: " + syringe.getTagCompound().getString("SyringeDNA"));
            }
            if (syringe.getTagCompound().hasKey("SyringeQuality"))
            {
                list.add(EnumChatFormatting.GREEN + "Quality: " + syringe.getTagCompound().getInteger("SyringeQuality") + "%");
            }
        }
    }

    @Override
    public ItemStack onItemRightClick(ItemStack syringe, World world, EntityPlayer player)
    {
        if (player.capabilities.isCreativeMode && player.isSneaking())
        {
            NBTTagCompound compound = new NBTTagCompound();
            if (syringe.hasTagCompound())
            {
            	if (syringe.getTagCompound().hasKey("SyringeQuality"))
                {
            		int oldQuality = syringe.getTagCompound().getInteger("SyringeQuality");
            		syringe.getTagCompound().removeTag("SyringeQuality");
                    switch (oldQuality)
                    {
                        case 25:
                            compound.setInteger("SyringeQuality", 50);
                            break;
                        case 50:
                            compound.setInteger("SyringeQuality", 75);
                            break;
                        case 75:
                            compound.setInteger("SyringeQuality", 100);
                            break;
                        case 100:
                            compound.setInteger("SyringeQuality", 25);
                            break;
                        default:
                            break;
                    }
                } else {
                    compound.setInteger("SyringeQuality", 25);
                }
            	if (syringe.getTagCompound().hasKey("SyringeDNA"))
                {
            		syringe.getTagCompound().removeTag("SyringeDNA");
                    compound.setString("SyringeDNA", JurassiCraftDNAHandler.createDefaultDNA());
                } else {
                    compound.setString("SyringeDNA", JurassiCraftDNAHandler.createDefaultDNA());
                }
            }
            else
            {
                compound.setInteger("SyringeQuality", 25);
                compound.setString("SyringeDNA", JurassiCraftDNAHandler.createDefaultDNA());
            }
            syringe.setTagCompound(compound);
            if (world.isRemote)
            {
                player.addChatMessage(new ChatComponentText("Cheater! New quality changed to " + syringe.getTagCompound().getInteger("SyringeQuality") + "%"));
            }
        }
        return syringe;
    }

    @Override
    public boolean itemInteractionForEntity(ItemStack syringe, EntityPlayer player, EntityLivingBase creature)
    {

        if (!player.worldObj.isRemote && !player.capabilities.isCreativeMode)
        {
        	if (creature instanceof EntityAnimal && ((EntityAnimal) creature).getGrowingAge() == 0)
            {
        		if (!this.setBaby(creature, syringe)) 
        		{
        			return false;
        		} 
        		else 
        		{
        	        syringe.stackSize--;
        	        if (syringe.stackSize <= 0) 
        	        {
        	        	syringe = (ItemStack) null;
        	        }
        	        return true;
        		}
            }
        } 
        else if (!player.worldObj.isRemote && player.capabilities.isCreativeMode && !player.isSneaking())
        {
        	if (creature instanceof EntityAnimal && ((EntityAnimal) creature).getGrowingAge() == 0)
            {
        		if (!this.setBaby(creature, syringe)) 
        		{
        			return false;
        		} 
        		else 
        		{
        	        syringe.stackSize--;
        	        if (syringe.stackSize <= 0) 
        	        {
        	        	syringe = (ItemStack) null;
        	        }
        	        return true;
        		}
            }
        }
        return false;
    }

	private boolean setBaby(EntityLivingBase creature, ItemStack syringe) {
    	if (creature instanceof EntityCow)
        {
        	if (!this.creaturesFromCow.contains(this.mammalName)) {
    			return false;
        	} else {
            	EntityPregnantCow cow = EntityPregnantCow.get(((EntityCow) creature));
            	if (cow != null && cow.getMammalName().equals(StatCollector.translateToLocal("container.pad.pregnancy.noEmbryo"))) {
                	cow.setMammalName(this.mammalName);
                	cow.setDnaQuality(this.getEggQuality(syringe));
                	cow.setDnaSequence(this.getEggDNASequence(syringe));
        			cow.setPregnancySpeed(2048);
        			return true;
            	}
        	}
        }
    	else if (creature instanceof EntityPig)
        {
        	if (!this.creaturesFromPig.contains(this.mammalName)) {
    			return false;
        	} else {
            	EntityPregnantPig pig = EntityPregnantPig.get(((EntityPig) creature));
                if (pig != null && pig.getMammalName().equals(StatCollector.translateToLocal("container.pad.pregnancy.noEmbryo"))) {
                	pig.setMammalName(this.mammalName);
                	pig.setDnaQuality(this.getEggQuality(syringe));
                	pig.setDnaSequence(this.getEggDNASequence(syringe));
                	pig.setPregnancySpeed(2048);
        			return true;
            	}
        	}
        }
        else if (creature instanceof EntityHorse)
        {
        	if (!this.creaturesFromHorse.contains(this.mammalName)) {
    			return false;
        	} else {
            	EntityPregnantHorse horse = EntityPregnantHorse.get(((EntityHorse) creature));
                if (horse != null && horse.getMammalName().equals(StatCollector.translateToLocal("container.pad.pregnancy.noEmbryo"))) {
                	horse.setMammalName(this.mammalName);
                	horse.setDnaQuality(this.getEggQuality(syringe));
                	horse.setDnaSequence(this.getEggDNASequence(syringe));
                	horse.setPregnancySpeed(2048);
        			return true;
            	}
        	}
        }
        else if (creature instanceof EntitySheep)
        {
        	if (!this.creaturesFromSheep.contains(this.mammalName)) {
    			return false;
        	} else {
            	EntityPregnantSheep sheep = EntityPregnantSheep.get(((EntitySheep) creature));
                if (sheep != null && sheep.getMammalName().equals(StatCollector.translateToLocal("container.pad.pregnancy.noEmbryo"))) {
                	sheep.setMammalName(this.mammalName);
                	sheep.setDnaQuality(this.getEggQuality(syringe));
                	sheep.setDnaSequence(this.getEggDNASequence(syringe));
                	sheep.setPregnancySpeed(2048);
        			return true;
            	}
        	}
        }
		return false;
	}
}
