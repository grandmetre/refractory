package de.henry.refractory;

import java.util.Optional;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

/** Registers the blocks used by the rubber tree. */
public final class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Refractory.MOD_ID);

    private static final ResourceKey<net.minecraft.world.level.levelgen.feature.ConfiguredFeature<?, ?>>
            KAUTSCHUK_TREE_FEATURE = ResourceKey.create(
                    Registries.CONFIGURED_FEATURE,
                    ResourceLocation.fromNamespaceAndPath(Refractory.MOD_ID, "kautschuk_tree"));

    private static final TreeGrower KAUTSCHUK_TREE_GROWER = new TreeGrower(
            Refractory.MOD_ID + ":kautschuk_tree",
            Optional.empty(),
            Optional.of(KAUTSCHUK_TREE_FEATURE),
            Optional.empty());

    public static final DeferredBlock<RotatedPillarBlock> KAUTSCHUK_LOG = BLOCKS.register(
            "kautschuk_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)));
    public static final DeferredBlock<LeavesBlock> KAUTSCHUK_LEAVES = BLOCKS.register(
            "kautschuk_leaves", () -> new LeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)));
    public static final DeferredBlock<SaplingBlock> KAUTSCHUK_SAPLING = BLOCKS.register(
            "kautschuk_sapling",
            () -> new SaplingBlock(KAUTSCHUK_TREE_GROWER, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));

    private ModBlocks() {
    }
}
