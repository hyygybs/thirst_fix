package com.hyygybs.thirst_fix.mixin;

import com.hyygybs.thirst_fix.ThirstFix;
import dev.ghen.thirst.content.purity.WaterPurity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(WaterPurity.class)
public abstract class WaterPurityMixin {
    @ModifyArg(
            method = "givePurityEffects(Lnet/minecraft/world/entity/player/Player;I)Z",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/player/Player;addEffect(Lnet/minecraft/world/effect/MobEffectInstance;)Z"
            ),
            index = 0
    )
    private static MobEffectInstance replaceHungerWithThirst(MobEffectInstance original) {
        if (original.getEffect() == MobEffects.HUNGER) {
            return new MobEffectInstance(
                    ThirstFix.THIRST.get(),
                    original.getDuration(),
                    original.getAmplifier(),
                    original.isAmbient(),
                    original.isVisible(),
                    original.showIcon()
            );
        }
        return original;
    }
}
