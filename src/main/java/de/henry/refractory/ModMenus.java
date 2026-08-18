package de.henry.refractory;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModMenus {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(Registries.MENU, Refractory.MOD_ID);

    public static final DeferredHolder<MenuType<?>, MenuType<KabelmaschineMenu>> KABELMASCHINE =
            MENUS.register("kabelmaschine", () -> IMenuTypeExtension.create(KabelmaschineMenu::new));

    private ModMenus() {
    }
}
