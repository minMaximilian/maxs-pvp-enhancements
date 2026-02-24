package minmaximilian.reclaim.neoforge;

import minmaximilian.reclaim.Reclaim;
import minmaximilian.reclaim.ReclaimClient;
import minmaximilian.reclaim.config.ReclaimConfig;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.common.NeoForge;

@Mod(Reclaim.MOD_ID)
public class ReclaimImpl {

    public ReclaimImpl(IEventBus modEventBus, ModContainer modContainer) {
        IEventBus forgeEventBus = NeoForge.EVENT_BUS;

        ReclaimConfig.registerConfigs(modContainer::registerConfig);

        modEventBus.addListener(this::onLoadConfig);
        modEventBus.addListener(this::onReloadConfig);

        ReclaimCommonEventsImpl.register(forgeEventBus);

        Reclaim.init();

        if (FMLEnvironment.dist == Dist.CLIENT) {
            ReclaimClientImpl.prepareClient(modEventBus, forgeEventBus);
        }
    }

    private void onLoadConfig(ModConfigEvent.Loading event) {
        ReclaimConfig.onLoad(event.getConfig());
    }

    private void onReloadConfig(ModConfigEvent.Reloading event) {
        ReclaimConfig.onReload(event.getConfig());
    }
}
