package testmod;

import ladylib.capability.AutoCapability;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

@AutoCapability
public class CapabilityBackpack {

    @CapabilityInject(CapabilityBackpack.class)
    public static Capability<CapabilityBackpack> INSTANCE;

    @AutoCapability.AttachCapabilityCheckHandler(TestMod.MODID + ":backpack")
    public static Predicate<Entity> isEntityAffected() {
        return o -> o instanceof EntityPlayer;
    }

    private List<ItemStack> playerMagicBackpack = new ArrayList<>();

    public void addItemStack(ItemStack stack) {
        playerMagicBackpack.add(stack.copy());
    }

    public void dropContent(EntityPlayer player) {
        for (Iterator<ItemStack> iterator = playerMagicBackpack.iterator(); iterator.hasNext(); ) {
            ItemStack stack = iterator.next();
            player.dropItem(stack, false);
            iterator.remove();
        }
    }
}
