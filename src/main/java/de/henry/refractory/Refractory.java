package de.henry.refractory;

import com.mojang.logging.LogUtils;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import org.slf4j.Logger;

/** Entry point for Refractory. */
@Mod(Refractory.MOD_ID)
public final class Refractory {
    public static final String MOD_ID = "refractory";
    public static final Logger LOGGER = LogUtils.getLogger();

    public Refractory(IEventBus modEventBus) {
        ModBlocks.BLOCKS.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
        ModBlockEntities.BLOCK_ENTITIES.register(modEventBus);
        ModMenus.MENUS.register(modEventBus);
        ModCreativeTabs.CREATIVE_MODE_TABS.register(modEventBus);
        modEventBus.addListener(Refractory::addItemsToIngredientsTab);
        LOGGER.info("{} was loaded.", MOD_ID);
    }

    private static void addItemsToIngredientsTab(BuildCreativeModeTabContentsEvent event) {
        if (CreativeModeTabs.INGREDIENTS.equals(event.getTabKey())) {
            event.accept(ModItems.KAUTSCHUK);
            event.accept(ModItems.GUMMI);
            event.accept(ModItems.KABEL);
            event.accept(ModItems.KURBEL);
            event.accept(ModItems.KABELMASCHINE);
            event.accept(ModItems.KAUTSCHUK_LOG);
            event.accept(ModItems.KAUTSCHUK_LEAVES);
            event.accept(ModItems.KAUTSCHUK_SAPLING);
            event.accept(ModItems.KAUTSCHUK_SCHALE);
        }
    }
}
