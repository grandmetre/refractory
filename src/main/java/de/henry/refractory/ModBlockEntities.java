package de.henry.refractory;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, Refractory.MOD_ID);

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<CableMachineBlockEntity>> CABLE_MACHINE =
            BLOCK_ENTITIES.register("cable_machine", () -> BlockEntityType.Builder.of(
                    CableMachineBlockEntity::new, ModBlocks.CABLE_MACHINE.get()).build(null));

    private ModBlockEntities() {
    }
}
