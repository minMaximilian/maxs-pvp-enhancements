package minmaximilian.reclaim.neoforge;

import net.neoforged.fml.ModList;

public class IndexPlatformImpl {

    public static boolean isModLoaded(String id) {
        return ModList.get().isLoaded(id);
    }
}
