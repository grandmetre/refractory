package de.henry.refractory;

import java.util.List;
import java.util.Optional;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Set;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.storage.loot.LootParams;
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

    public static final DeferredBlock<KautschukLogBlock> KAUTSCHUK_LOG = BLOCKS.register(
            "kautschuk_log", KautschukLogBlock::new);
    public static final DeferredBlock<LeavesBlock> KAUTSCHUK_LEAVES = BLOCKS.register(
            "kautschuk_leaves",
            () -> new LeavesBlock(
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)
                            .strength(0.2F)
                            .isViewBlocking((state, level, pos) -> false)));
    public static final DeferredBlock<SaplingBlock> KAUTSCHUK_SAPLING = BLOCKS.register(
            "kautschuk_sapling",
            () -> new SaplingBlock(
                    KAUTSCHUK_TREE_GROWER,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)
                            .strength(0.0F)
                            .noCollission()));
    public static final DeferredBlock<KautschukSchaleBlock> KAUTSCHUK_SCHALE = BLOCKS.register(
            "kautschuk_schale",
            () -> new KautschukSchaleBlock(BlockBehaviour.Properties.of()
                    .strength(0.5F)
                    .noOcclusion()
                    .randomTicks()));

    public static class KautschukLogBlock extends RotatedPillarBlock {
        public KautschukLogBlock() {
            super(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG).strength(2.0F, 3.0F));
        }

        @Override
        public List<ItemStack> getDrops(BlockState state, LootParams.Builder builder) {
            return List.of(new ItemStack(ModItems.KAUTSCHUK_LOG.get()));
        }
    }

    /** A bowl attached to a rubber log which slowly collects rubber sap. */
    public static class KautschukSchaleBlock extends HorizontalDirectionalBlock {
        public static final MapCodec<KautschukSchaleBlock> CODEC = simpleCodec(KautschukSchaleBlock::new);
        public static final BooleanProperty FULL = BlockStateProperties.POWERED;

        public KautschukSchaleBlock(BlockBehaviour.Properties properties) {
            super(properties);
            registerDefaultState(stateDefinition.any()
                    .setValue(FACING, Direction.NORTH)
                    .setValue(FULL, false));
        }

        @Override
        protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
            return CODEC;
        }

        @Override
        public BlockState getStateForPlacement(BlockPlaceContext context) {
            Direction facing = context.getClickedFace();
            if (!facing.getAxis().isHorizontal()) {
                return null;
            }

            BlockPos attachedLog = context.getClickedPos().relative(facing.getOpposite());
            if (!context.getLevel().getBlockState(attachedLog).is(ModBlocks.KAUTSCHUK_LOG.get())
                    || hasBowlOnTree(context.getLevel(), attachedLog)) {
                return null;
            }
            return defaultBlockState().setValue(FACING, facing);
        }

        @Override
        protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
            BlockPos attachedLog = pos.relative(state.getValue(FACING).getOpposite());
            return level.getBlockState(attachedLog).is(ModBlocks.KAUTSCHUK_LOG.get());
        }

        @Override
        protected BlockState updateShape(
                BlockState state,
                Direction direction,
                BlockState neighborState,
                LevelAccessor level,
                BlockPos pos,
                BlockPos neighborPos) {
            if (!canSurvive(state, level, pos)) {
                return Blocks.AIR.defaultBlockState();
            }
            return super.updateShape(state, direction, neighborState, level, pos, neighborPos);
        }

        @Override
        protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
            // A random tick occurs roughly every 68 seconds at the default gamerule.
            // This gives an average filling time of about five minutes.
            if (!state.getValue(FULL) && random.nextInt(4) == 0) {
                level.setBlock(pos, state.setValue(FULL, true), Block.UPDATE_CLIENTS);
            }
        }

        @Override
        protected InteractionResult useWithoutItem(
                BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
            if (!state.getValue(FULL)) {
                return InteractionResult.PASS;
            }
            if (!level.isClientSide) {
                player.getInventory().placeItemBackInInventory(new ItemStack(ModItems.KAUTSCHUK.get()));
                level.setBlock(pos, state.setValue(FULL, false), Block.UPDATE_CLIENTS);
            }
            return InteractionResult.SUCCESS;
        }

        @Override
        protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
            builder.add(FACING, FULL);
        }

        private static boolean hasBowlOnTree(BlockGetter level, BlockPos startLog) {
            Set<BlockPos> visited = new HashSet<>();
            ArrayDeque<BlockPos> pending = new ArrayDeque<>();
            pending.add(startLog);

            // The limit prevents a malformed, player-built mega-structure from causing a long search.
            while (!pending.isEmpty() && visited.size() < 512) {
                BlockPos logPos = pending.removeFirst();
                if (!visited.add(logPos)) {
                    continue;
                }

                for (Direction direction : Direction.values()) {
                    BlockPos adjacent = logPos.relative(direction);
                    BlockState adjacentState = level.getBlockState(adjacent);
                    if (adjacentState.is(ModBlocks.KAUTSCHUK_SCHALE.get())
                            && adjacent.relative(adjacentState.getValue(FACING).getOpposite()).equals(logPos)) {
                        return true;
                    }
                    if (adjacentState.is(ModBlocks.KAUTSCHUK_LOG.get()) && !visited.contains(adjacent)) {
                        pending.addLast(adjacent);
                    }
                }
            }
            return false;
        }
    }

    private ModBlocks() {
    }
}
