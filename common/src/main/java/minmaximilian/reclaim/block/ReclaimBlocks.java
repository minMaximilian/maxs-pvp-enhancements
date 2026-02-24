package minmaximilian.reclaim.block;

import static minmaximilian.reclaim.Reclaim.MOD_ID;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import minmaximilian.reclaim.item.ReclaimItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class ReclaimBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(MOD_ID, Registries.BLOCK);

    public static final RegistrySupplier<WallPlaster> WALL_PLASTER = BLOCKS.register("wall_plaster",
        () -> new WallPlaster(BlockBehaviour.Properties.ofFullCopy(Blocks.SAND).sound(SoundType.GRAVEL)));

    // Register block items in the item registry
    public static final RegistrySupplier<BlockItem> WALL_PLASTER_ITEM = ReclaimItems.ITEMS.register("wall_plaster",
        () -> new BlockItem(WALL_PLASTER.get(), new Item.Properties()));

    public static void register() {
        BLOCKS.register();
    }
}
