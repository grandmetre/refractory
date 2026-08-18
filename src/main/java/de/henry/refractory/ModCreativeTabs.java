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
                    .icon(() -> ModItems.KAUTSCHUK.get().getDefaultInstance())
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.KAUTSCHUK);
                        output.accept(ModItems.GUMMI);
                        output.accept(ModItems.KABEL);
                        output.accept(ModItems.KURBEL);
                        output.accept(ModItems.KABELMASCHINE);
                        output.accept(ModItems.KAUTSCHUK_LOG);
                        output.accept(ModItems.KAUTSCHUK_LEAVES);
                        output.accept(ModItems.KAUTSCHUK_SAPLING);
                    })
                    .build());

    private ModCreativeTabs() {
    }
}
