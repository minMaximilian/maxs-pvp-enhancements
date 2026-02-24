package minmaximilian.reclaim.registry;

import static minmaximilian.reclaim.Reclaim.MOD_ID;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import minmaximilian.reclaim.block.ReclaimBlocks;
import minmaximilian.reclaim.item.ReclaimItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ReclaimCreativeModeTab {

    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(MOD_ID, Registries.CREATIVE_MODE_TAB);

    public static final RegistrySupplier<CreativeModeTab> MAIN_TAB = TABS.register("main",
        () -> CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0)
            .title(Component.literal("Reclaim"))
            .icon(() -> new ItemStack(ReclaimItems.HEPHAESTUS_BAG.get()))
            .displayItems((params, output) -> {
                output.accept(ReclaimItems.HEPHAESTUS_BAG.get());
                output.accept(ReclaimBlocks.WALL_PLASTER.get());
            })
            .build());

    public static void register() {
        TABS.register();
    }
}
