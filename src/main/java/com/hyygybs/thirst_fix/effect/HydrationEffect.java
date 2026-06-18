package com.hyygybs.thirst_fix.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import cn.mlus.thirst.foundation.common.capability.ModAttachment;

public class HydrationEffect extends MobEffect {
    public HydrationEffect() {
        super(MobEffectCategory.BENEFICIAL, 9564927);
    }

    @Override
    public boolean applyEffectTick(LivingEntity entity, int amplifier) {
        if (entity instanceof Player player) {
            player.getExistingData(ModAttachment.PLAYER_THIRST)
                    .ifPresent(thirst -> {
                        if (!player.level().isClientSide) {
                            thirst.drink(amplifier + 1, 1);
                        }
                    });
        }
        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }
}
