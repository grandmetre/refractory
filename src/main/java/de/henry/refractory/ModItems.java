package de.henry.refractory;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.BlockItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

/** Registers all items provided by the mod. */
public final class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Refractory.MOD_ID);

    public static final DeferredItem<Item> KAUTSCHUK = ITEMS.registerSimpleItem("kautschuk");
    public static final DeferredItem<Item> GUMMI = ITEMS.registerSimpleItem("gummi");
    public static final DeferredItem<Item> KABEL = ITEMS.registerSimpleItem("kabel");
    public static final DeferredItem<Item> KURBEL = ITEMS.registerSimpleItem("kurbel", new Item.Properties().stacksTo(1));
    public static final DeferredItem<BlockItem> KAUTSCHUK_LOG = ITEMS.registerSimpleBlockItem("kautschuk_log", ModBlocks.KAUTSCHUK_LOG);
    public static final DeferredItem<BlockItem> KAUTSCHUK_LEAVES = ITEMS.registerSimpleBlockItem("kautschuk_leaves", ModBlocks.KAUTSCHUK_LEAVES);
    public static final DeferredItem<BlockItem> KAUTSCHUK_SAPLING = ITEMS.registerSimpleBlockItem("kautschuk_sapling", ModBlocks.KAUTSCHUK_SAPLING);
    public static final DeferredItem<BlockItem> KAUTSCHUK_SCHALE = ITEMS.registerSimpleBlockItem("kautschuk_schale", ModBlocks.KAUTSCHUK_SCHALE);
    public static final DeferredItem<BlockItem> KABELMASCHINE = ITEMS.registerSimpleBlockItem("kabelmaschine", ModBlocks.KABELMASCHINE);

    private ModItems() {
    }
}
