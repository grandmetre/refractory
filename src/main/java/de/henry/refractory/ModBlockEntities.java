package de.henry.refractory;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, Refractory.MOD_ID);

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<KabelmaschineBlockEntity>> KABELMASCHINE =
            BLOCK_ENTITIES.register("kabelmaschine", () -> BlockEntityType.Builder.of(
                    KabelmaschineBlockEntity::new, ModBlocks.KABELMASCHINE.get()).build(null));

    private ModBlockEntities() {
    }
}
