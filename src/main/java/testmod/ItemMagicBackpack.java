package testmod;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class ItemMagicBackpack extends Item {
    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, @Nonnull EnumHand handIn) {
        CapabilityMana cap = playerIn.getCapability(CapabilityMana.INSTANCE, null);
        CapabilityBackpack backpack = playerIn.getCapability(CapabilityBackpack.INSTANCE, null);
        if (cap != null && backpack != null) {
            if (playerIn.isSneaking()) {
                backpack.dropContent(playerIn);
            } else {
                NonNullList<ItemStack> mainInventory = playerIn.inventory.mainInventory;
                for (int i = 0; i < mainInventory.size(); i++) {
                    ItemStack stack = mainInventory.get(i);
                    if (cap.getMana() <= 0) break;
                    if (stack.isEmpty() || playerIn.getHeldItem(handIn) == stack || stack.getItem() instanceof ItemManaOrb)
                        continue;
                    backpack.addItemStack(stack);
                    mainInventory.set(i, ItemStack.EMPTY);
                    cap.addMana(-1);
                }
            }
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
