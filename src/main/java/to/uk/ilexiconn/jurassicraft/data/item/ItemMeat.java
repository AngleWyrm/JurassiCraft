package to.uk.ilexiconn.jurassicraft.data.item;

import net.minecraft.item.ItemFood;
import net.minecraft.potion.Potion;
import to.uk.ilexiconn.jurassicraft.ModCreativeTabs;
import to.uk.ilexiconn.jurassicraft.Util;

public class ItemMeat extends ItemFood
{
    public ItemMeat(String name)
    {
        super(4, 0.1f, true);
        setPotionEffect(Potion.hunger.id, 30, 0, 0.8f);
        setUnlocalizedName(name + "_Meat");
        setTextureName(Util.getModId() + name + "_Meat");
        setCreativeTab(ModCreativeTabs.fossilTab);
    }
}
