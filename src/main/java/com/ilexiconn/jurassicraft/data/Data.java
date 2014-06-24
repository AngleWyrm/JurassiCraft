package com.ilexiconn.jurassicraft.data;

import com.ilexiconn.jurassicraft.Util;
import com.ilexiconn.jurassicraft.data.block.*;
import com.ilexiconn.jurassicraft.data.gui.GuiHandler;
import com.ilexiconn.jurassicraft.data.item.ItemAmber;
import com.ilexiconn.jurassicraft.data.item.ItemDinoBone;
import com.ilexiconn.jurassicraft.data.item.ItemFossil;
import com.ilexiconn.jurassicraft.data.tile.TileAnalyzer;
import com.ilexiconn.jurassicraft.data.tile.TileCultivate;
import com.ilexiconn.jurassicraft.data.tile.TileEgg;
import com.ilexiconn.jurassicraft.data.tile.render.CultivateRenderer;
import com.ilexiconn.jurassicraft.data.tile.render.EggRenderer;
import com.ilexiconn.jurassicraft.data.world.gen.WorldGenAmberOre;
import com.ilexiconn.jurassicraft.data.world.gen.WorldGenFossilOre;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public final class Data extends Util
{
    public void init(FMLPreInitializationEvent event)
    {
        { /** Creativetabs */
            addCreativeTab(0, new CreativeTabs("fossilTab")
            {
                public Item getTabIconItem()
                {
                    return Item.getItemFromBlock(getBlock(1));
                }
            });
        }
        { /** Blocks */
            addBlockWithTileEntity(0, new BlockCultivate(true), TileCultivate.class, true);
            addBlockWithTileEntity(1, new BlockCultivate(false), TileCultivate.class, false);
            addBlockWithTileEntity(2, new BlockAnalyzer(true), TileAnalyzer.class, true);
            addBlockWithTileEntity(3, new BlockAnalyzer(false), TileAnalyzer.class, false);
            addBlock(4, new BlockAmberOre());
            addBlock(5, new BlockFossilOre());
            addBlock(6, new GhostBlock("cultivate_top", getBlock(1), 1f, new int[]{-1}, -1, 2, 0f, -1f, 0f, 1f, 1f, 1f));
        }
        { /** Items */
            addItem(1, new ItemAmber());
            addItem(2, new ItemFossil());
            addItem(3, new ItemDinoBone());
        }
        { /** World Gens */
            addWorldGenerator(new WorldGenFossilOre(), 1);
            addWorldGenerator(new WorldGenAmberOre(), 2);
        }
        { /** Other stuff */
            addGuiHandler(new GuiHandler());
        }
        { /** Recipes */
            addShapedRecipe(new ItemStack(getBlock(1)), "GGG", "GCG", "III", 'G', Blocks.glass, 'C', new ItemStack(Items.dye, 1, 2), 'I', Items.iron_ingot);
            addShapedRecipe(new ItemStack(getBlock(3)), "IFI", "IFI", 'I', Items.iron_ingot, 'F', getItem(2));
        }
    }

    @SideOnly(Side.CLIENT)
    public void initClient(FMLPreInitializationEvent event)
    {
        { /** Block Renderers */
            addTileEntityRenderer(TileCultivate.class, new CultivateRenderer());
            addTileEntityRenderer(TileEgg.class, new EggRenderer());
        }
        { /** Item Renderers */

        }
    }
}
