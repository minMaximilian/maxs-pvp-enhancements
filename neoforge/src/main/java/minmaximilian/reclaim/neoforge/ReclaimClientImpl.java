package minmaximilian.reclaim.neoforge;

import minmaximilian.reclaim.ReclaimClient;
import net.neoforged.bus.api.IEventBus;

public class ReclaimClientImpl {

    public static void prepareClient(IEventBus modEventBus, IEventBus forgeEventBus) {
        ReclaimClient.init();
    }
}
