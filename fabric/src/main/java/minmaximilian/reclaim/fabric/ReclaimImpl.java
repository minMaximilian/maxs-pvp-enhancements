package minmaximilian.reclaim.fabric;

import fuzs.forgeconfigapiport.fabric.api.neoforge.v4.NeoForgeConfigRegistry;
import fuzs.forgeconfigapiport.fabric.api.neoforge.v4.NeoForgeModConfigEvents;
import minmaximilian.reclaim.Reclaim;
import minmaximilian.reclaim.config.ReclaimConfig;
import net.fabricmc.api.ModInitializer;

public class ReclaimImpl implements ModInitializer {

    @Override
    public void onInitialize() {
        Reclaim.init();

        ReclaimConfig.registerConfigs(
            (t, c) -> NeoForgeConfigRegistry.INSTANCE.register(Reclaim.MOD_ID, t, c));

        NeoForgeModConfigEvents.loading(Reclaim.MOD_ID).register(ReclaimConfig::onLoad);
        NeoForgeModConfigEvents.reloading(Reclaim.MOD_ID).register(ReclaimConfig::onReload);

        ReclaimCommonEventsImpl.register();
    }
}
