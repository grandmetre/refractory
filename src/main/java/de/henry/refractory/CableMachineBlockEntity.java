package de.henry.refractory;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;

public final class CableMachineBlockEntity extends BlockEntity implements MenuProvider {
    public static final int RUBBER_SLOT = 0;
    public static final int COPPER_SLOT = 1;

    private final ItemStackHandler items = new ItemStackHandler(2) {
        @Override
        public boolean isItemValid(int slot, ItemStack stack) {
            return slot == RUBBER_SLOT ? stack.is(ModItems.RUBBER.get()) : stack.is(Items.COPPER_INGOT);
        }

        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };

    public CableMachineBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.CABLE_MACHINE.get(), pos, state);
    }

    public ItemStackHandler getItems() {
        return items;
    }

    public boolean canCraft() {
        return items.getStackInSlot(RUBBER_SLOT).is(ModItems.RUBBER.get())
                && items.getStackInSlot(COPPER_SLOT).is(Items.COPPER_INGOT);
    }

    public void consumeIngredients() {
        items.extractItem(RUBBER_SLOT, 1, false);
        items.extractItem(COPPER_SLOT, 1, false);
        setChanged();
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("container.refractory.cable_machine");
    }

    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory inventory, Player player) {
        return new CableMachineMenu(containerId, inventory, this);
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.put("Inventory", items.serializeNBT(registries));
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        if (tag.contains("Inventory")) items.deserializeNBT(registries, tag.getCompound("Inventory"));
    }
}
