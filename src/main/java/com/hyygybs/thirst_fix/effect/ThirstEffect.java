package com.hyygybs.thirst_fix.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import cn.mlus.thirst.foundation.common.capability.ModAttachment;

public class ThirstEffect extends MobEffect {
    public ThirstEffect() {super(MobEffectCategory.HARMFUL,10870382);}

    @Override
    public boolean applyEffectTick(LivingEntity entity, int amplifier) {
        if (entity instanceof Player player) {
            player.getExistingData(ModAttachment.PLAYER_THIRST)
                    .ifPresent(thirst -> {
                        if (!player.level().isClientSide) {
                            float exhaustion = (float) (2 + amplifier * 0.1);
                            thirst.addExhaustion(player, exhaustion);
                        }
                    });
        }
        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        int time = 80 >> amplifier;
        if (time > 0) {
            return duration % time == 0;
        } else {
            return true;
        }
    }
}
