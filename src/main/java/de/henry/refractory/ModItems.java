package de.henry.refractory;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.BlockItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

/** Registers all items provided by the mod. */
public final class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Refractory.MOD_ID);

    public static final DeferredItem<Item> RUBBER_SAP = ITEMS.registerSimpleItem("rubber_sap");
    public static final DeferredItem<Item> RUBBER = ITEMS.registerSimpleItem("rubber");
    public static final DeferredItem<Item> CABLE = ITEMS.registerSimpleItem("cable");
    public static final DeferredItem<Item> CRANK = ITEMS.registerSimpleItem("crank", new Item.Properties().stacksTo(1));
    public static final DeferredItem<BlockItem> RUBBER_LOG = ITEMS.registerSimpleBlockItem("rubber_log", ModBlocks.RUBBER_LOG);
    public static final DeferredItem<BlockItem> RUBBER_LEAVES = ITEMS.registerSimpleBlockItem("rubber_leaves", ModBlocks.RUBBER_LEAVES);
    public static final DeferredItem<BlockItem> RUBBER_SAPLING = ITEMS.registerSimpleBlockItem("rubber_sapling", ModBlocks.RUBBER_SAPLING);
    public static final DeferredItem<BlockItem> RUBBER_BOWL = ITEMS.registerSimpleBlockItem("rubber_bowl", ModBlocks.RUBBER_BOWL);
    public static final DeferredItem<BlockItem> CABLE_MACHINE = ITEMS.registerSimpleBlockItem("cable_machine", ModBlocks.CABLE_MACHINE);

    private ModItems() {
    }
}
