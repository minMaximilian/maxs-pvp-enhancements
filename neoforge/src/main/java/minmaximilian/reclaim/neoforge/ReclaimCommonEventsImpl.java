package minmaximilian.reclaim.neoforge;

import minmaximilian.reclaim.ReclaimCommonEvents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.item.ItemEntity;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.event.tick.LevelTickEvent;
import net.neoforged.neoforge.event.entity.EntityStruckByLightningEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.event.level.ChunkEvent;
import net.neoforged.neoforge.event.level.ExplosionEvent;
import net.neoforged.neoforge.event.level.LevelEvent;
import net.neoforged.bus.api.IEventBus;

public class ReclaimCommonEventsImpl {

    public static void register(IEventBus forgeEventBus) {
        forgeEventBus.addListener(ReclaimCommonEventsImpl::onServerStarting);
        forgeEventBus.addListener(ReclaimCommonEventsImpl::onExplosion);
        forgeEventBus.addListener(ReclaimCommonEventsImpl::onWorldTick);
        forgeEventBus.addListener(ReclaimCommonEventsImpl::onChunkLoad);
        forgeEventBus.addListener(ReclaimCommonEventsImpl::onChunkUnload);
        forgeEventBus.addListener(ReclaimCommonEventsImpl::onRegisterCommands);
        forgeEventBus.addListener(ReclaimCommonEventsImpl::onLightningStrike);
        forgeEventBus.addListener(ReclaimCommonEventsImpl::onBlockPlace);
    }

    public static void onServerStarting(LevelEvent.Load event) {
        ReclaimCommonEvents.onServerStarting(event.getLevel());
    }

    public static void onExplosion(ExplosionEvent.Detonate explosionEvent) {
        ReclaimCommonEvents.onExplosion(explosionEvent.getLevel(), explosionEvent.getAffectedBlocks(),
            explosionEvent.getExplosion());
    }

    private static void onBlockPlace(BlockEvent.EntityPlaceEvent blockEvent) {
        ReclaimCommonEvents.onBlockPlace(blockEvent.getLevel(), blockEvent.getEntity(),
            blockEvent.getPlacedBlock(), blockEvent.getPos());
    }

    public static void onWorldTick(LevelTickEvent.Pre event) {
        if (event.getLevel() instanceof ServerLevel serverLevel) {
            ReclaimCommonEvents.onLevelTick(serverLevel);
        }
    }

    public static void onChunkLoad(ChunkEvent.Load event) {
        ReclaimCommonEvents.onChunkLoad(event.getLevel(), event.getChunk());
    }

    public static void onChunkUnload(ChunkEvent.Unload event) {
        ReclaimCommonEvents.onChunkUnload(event.getLevel(), event.getChunk());
    }

    public static void onRegisterCommands(RegisterCommandsEvent event) {
        ReclaimCommonEvents.onLoadCommands(event.getDispatcher());
    }

    public static void onLightningStrike(EntityStruckByLightningEvent event) {
        ReclaimCommonEvents.onLightningStrike(event.getEntity(), event.getLightning());
    }
}
