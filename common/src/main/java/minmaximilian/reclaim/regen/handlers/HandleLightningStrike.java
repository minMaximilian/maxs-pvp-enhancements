package minmaximilian.reclaim.regen.handlers;

import minmaximilian.reclaim.item.HephaestusBag;
import minmaximilian.reclaim.item.ReclaimItems;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.component.CustomData;

public class HandleLightningStrike {

    public static boolean handleLightningStrike(Entity entity, LightningBolt lightning) {
        if (entity instanceof ItemEntity item
            && item.getItem().getItem() == ReclaimItems.HEPHAESTUS_BAG.get()) {
            item.getItem().update(DataComponents.CUSTOM_DATA, CustomData.EMPTY, data ->
                data.update(tag -> tag.putBoolean(HephaestusBag.NBT_KEY_CHARGED, true)));
            item.clearFire();
            return true;
        }
        return false;
    }
}
