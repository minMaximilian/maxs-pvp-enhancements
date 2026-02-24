package minmaximilian.reclaim.multiloader.neoforge;

import minmaximilian.reclaim.multiloader.Env;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.loading.FMLEnvironment;

public class EnvImpl {

    public static Env getCurrent() {
        return FMLEnvironment.dist == Dist.CLIENT ? Env.CLIENT : Env.SERVER;
    }
}
