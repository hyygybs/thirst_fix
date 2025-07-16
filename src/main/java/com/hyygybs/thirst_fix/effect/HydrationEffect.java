package com.hyygybs.thirst_fix.effect;

import dev.ghen.thirst.foundation.common.capability.ModCapabilities;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class HydrationEffect extends MobEffect {
    public HydrationEffect() {
        super(MobEffectCategory.BENEFICIAL, 9564927);
    }
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (entity instanceof Player player) {
            player.getCapability(ModCapabilities.PLAYER_THIRST, null).ifPresent((cap) -> {
                if (!player.level().isClientSide) {
                cap.drink(player, amplifier + 1, (int) 1.0F);
                }
            });
        }
    }
public boolean isDurationEffectTick(int duration, int amplifier) {
    int interval = 10;
    return duration % interval == 0;
    }
}
