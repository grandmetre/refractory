package de.henry.refractory;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

/** The creative-mode tab containing the items from this mod. */
public final class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Refractory.MOD_ID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> MAIN_TAB =
            CREATIVE_MODE_TABS.register("main", () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.refractory"))
                    .icon(() -> ModItems.RUBBER_SAP.get().getDefaultInstance())
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.RUBBER_SAP);
                        output.accept(ModItems.RUBBER);
                        output.accept(ModItems.CABLE);
                        output.accept(ModItems.CRANK);
                        output.accept(ModItems.CABLE_MACHINE);
                        output.accept(ModItems.RUBBER_LOG);
                        output.accept(ModItems.RUBBER_LEAVES);
                        output.accept(ModItems.RUBBER_SAPLING);
                    })
                    .build());

    private ModCreativeTabs() {
    }
}
