package testmod;

import ladylib.capability.AutoCapability;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

import java.util.List;
import java.util.function.Predicate;

@AutoCapability
public class CapabilityMana {

    @CapabilityInject(CapabilityMana.class)
    public static Capability<CapabilityMana> INSTANCE;

    @AutoCapability.AttachCapabilityCheckHandler(TestMod.MODID + ":mana")
    public static Predicate<Entity> isEntityAffected() {
        return o -> o instanceof EntityPlayer;
    }

    private int mana;

    public void addMana(int mana) {
        this.mana += mana;
    }

    public int getMana() {
        return this.mana;
    }

}
