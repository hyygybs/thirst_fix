package com.hyygybs.thirst_fix.effect;

import dev.ghen.thirst.foundation.common.capability.ModCapabilities;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class ThirstEffect extends MobEffect {
    public ThirstEffect() {super(MobEffectCategory.HARMFUL,10870382);}
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (entity instanceof Player player) {
            player.getCapability(ModCapabilities.PLAYER_THIRST, null).ifPresent((cap) -> {
                if (!player.level().isClientSide()) {
                    cap.addExhaustion(player, (float)(2 + amplifier * 0.1));
                }
            });
        }
    }
    public boolean isDurationEffectTick(int duration, int amplifier) {
        int time = 80 >> amplifier;
        if (time > 0) {
            return duration % time == 0;
        } else {
            return true;
        }
    }
}
