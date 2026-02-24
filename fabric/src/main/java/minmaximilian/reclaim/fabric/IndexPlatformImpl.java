package minmaximilian.reclaim.fabric;

import net.fabricmc.loader.api.FabricLoader;

public class IndexPlatformImpl {

    public static boolean isModLoaded(String id) {
        return FabricLoader.getInstance().isModLoaded(id);
    }
}
