package testmod;

import ladylib.LLibContainer;
import ladylib.LadyLib;
import ladylib.misc.TemplateUtil;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = TestMod.MODID)
public class TestMod {

    public static final String MODID = "test_mod";

    @LadyLib.LLInstance
    public static LLibContainer lib;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        lib.makeCreativeTab(() -> new ItemStack(ModItems.TEST));
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        TemplateUtil.generateStubModels(MODID, "../src/main/resources");
    }

}
