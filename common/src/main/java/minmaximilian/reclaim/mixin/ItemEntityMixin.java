package minmaximilian.reclaim.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.llamalad7.mixinextras.injector.WrapWithCondition;

import minmaximilian.reclaim.ReclaimCommonEvents;
import minmaximilian.reclaim.item.ReclaimItems;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.item.ItemEntity;

@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin {
    @WrapWithCondition(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/item/ItemEntity;discard()V"))
    public boolean reclaim$aboutToDespawn(ItemEntity itemEntity) {
        int pickupDelay = ((PickupDelayMixin) itemEntity).getPickupDelay();
        return ReclaimCommonEvents.onItemDespawn(itemEntity, pickupDelay);
    }

    @Inject(method = "hurt", at = @At("HEAD"), cancellable = true)
    private void reclaim$blockDamage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        ItemEntity self = (ItemEntity) (Object) this;
        if (self.getItem().getItem() == ReclaimItems.HEPHAESTUS_BAG.get()) {
            cir.setReturnValue(false);
        }
    }
}
