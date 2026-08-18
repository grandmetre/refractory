package de.henry.refractory;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.items.SlotItemHandler;

public final class KabelmaschineMenu extends AbstractContainerMenu {
    private final KabelmaschineBlockEntity machine;
    private static final int MACHINE_SLOTS = 2;

    public KabelmaschineMenu(int containerId, Inventory inventory, FriendlyByteBuf data) {
        this(containerId, inventory, (KabelmaschineBlockEntity) inventory.player.level().getBlockEntity(data.readBlockPos()));
    }

    public KabelmaschineMenu(int containerId, Inventory inventory, KabelmaschineBlockEntity machine) {
        super(ModMenus.KABELMASCHINE.get(), containerId);
        this.machine = machine;
        addSlot(new SlotItemHandler(machine.getItems(), KabelmaschineBlockEntity.RUBBER_SLOT, 62, 27));
        addSlot(new SlotItemHandler(machine.getItems(), KabelmaschineBlockEntity.COPPER_SLOT, 98, 27));

        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 9; column++) {
                addSlot(new Slot(inventory, column + row * 9 + 9, 8 + column * 18, 64 + row * 18));
            }
        }
        for (int column = 0; column < 9; column++) {
            addSlot(new Slot(inventory, column, 8 + column * 18, 122));
        }
    }

    @Override
    public boolean stillValid(Player player) {
        return machine != null && stillValid(ContainerLevelAccess.create(machine.getLevel(), machine.getBlockPos()),
                player, ModBlocks.KABELMASCHINE.get());
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        Slot slot = slots.get(index);
        if (!slot.hasItem()) return ItemStack.EMPTY;
        ItemStack stack = slot.getItem();
        ItemStack original = stack.copy();
        if (index < MACHINE_SLOTS) {
            if (!moveItemStackTo(stack, MACHINE_SLOTS, slots.size(), true)) return ItemStack.EMPTY;
        } else {
            int target = stack.is(ModItems.GUMMI.get()) ? KabelmaschineBlockEntity.RUBBER_SLOT
                    : stack.is(Items.COPPER_INGOT) ? KabelmaschineBlockEntity.COPPER_SLOT : -1;
            if (target < 0 || !moveItemStackTo(stack, target, target + 1, false)) return ItemStack.EMPTY;
        }
        if (stack.isEmpty()) slot.setByPlayer(ItemStack.EMPTY); else slot.setChanged();
        return original;
    }
}
