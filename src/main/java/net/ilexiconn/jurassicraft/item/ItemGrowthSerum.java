package net.ilexiconn.jurassicraft.item;

import net.ilexiconn.jurassicraft.JurassiCraft;
import net.ilexiconn.jurassicraft.ModCreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;

import java.util.List;

public class ItemGrowthSerum extends Item
{
    public ItemGrowthSerum()
    {
        super();
        setUnlocalizedName("growthSerum");
        setTextureName(JurassiCraft.getModId() + "growth_Serum");
        setCreativeTab(ModCreativeTabs.items);
    }

    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean flag)
    {
        list.add(EnumChatFormatting.GRAY + StatCollector.translateToLocal("item.growthSerum.info"));
    }
}
