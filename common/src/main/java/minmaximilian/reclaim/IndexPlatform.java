package minmaximilian.reclaim;

import dev.architectury.injectables.annotations.ExpectPlatform;

public class IndexPlatform {

    @ExpectPlatform
    public static boolean isModLoaded(String id) {
        throw new AssertionError();
    }
}
