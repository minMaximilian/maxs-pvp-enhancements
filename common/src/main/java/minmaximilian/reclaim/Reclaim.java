package minmaximilian.reclaim;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import minmaximilian.reclaim.block.ReclaimBlocks;
import minmaximilian.reclaim.item.ReclaimItems;
import minmaximilian.reclaim.regen.SavedChunkDataManager;
import minmaximilian.reclaim.registry.ReclaimCreativeModeTab;
import net.minecraft.resources.ResourceLocation;

public class Reclaim {

    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "reclaim";
    public static final SavedChunkDataManager SAVED_CHUNKS = new SavedChunkDataManager();

    public static void init() {
        ReclaimCreativeModeTab.register();
        ReclaimBlocks.register();
        ReclaimItems.register();
    }

    public static ResourceLocation asResource(String name) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, name);
    }
}
