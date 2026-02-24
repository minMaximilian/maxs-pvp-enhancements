package minmaximilian.reclaim.item;

import static minmaximilian.reclaim.Reclaim.MOD_ID;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class ReclaimItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(MOD_ID, Registries.ITEM);

    public static final RegistrySupplier<HephaestusBag> HEPHAESTUS_BAG = ITEMS.register("hephaestus_bag",
        () -> new HephaestusBag(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC)));

    public static void register() {
        ITEMS.register();
    }
}
