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
    public static final DeferredItem<BlockItem> KAUTSCHUK_LOG = ITEMS.registerSimpleBlockItem("kautschuk_log", ModBlocks.KAUTSCHUK_LOG);
    public static final DeferredItem<BlockItem> KAUTSCHUK_LEAVES = ITEMS.registerSimpleBlockItem("kautschuk_leaves", ModBlocks.KAUTSCHUK_LEAVES);
    public static final DeferredItem<BlockItem> KAUTSCHUK_SAPLING = ITEMS.registerSimpleBlockItem("kautschuk_sapling", ModBlocks.KAUTSCHUK_SAPLING);

    private ModItems() {
    }
}
