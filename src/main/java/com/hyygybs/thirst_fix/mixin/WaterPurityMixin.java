package com.hyygybs.thirst_fix.mixin;

import com.hyygybs.thirst_fix.ThirstFix;
import dev.ghen.thirst.content.purity.WaterPurity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(WaterPurity.class)
public class WaterPurityMixin {
    @Redirect(
            method = "givePurityEffects(Lnet/minecraft/world/entity/player/Player;I)Z",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/player/Player;addEffect(Lnet/minecraft/world/effect/MobEffectInstance;)Z"
            ),
            require = 4
    )
    private static boolean redirectAddEffect(Player player, MobEffectInstance effectInstance) {
        if (effectInstance.getEffect() == MobEffects.HUNGER) {
            MobEffectInstance newEffect = new MobEffectInstance(
                    ThirstFix.THIRST.get(),
                    effectInstance.getDuration(),
                    effectInstance.getAmplifier(),
                    effectInstance.isAmbient(),
                    effectInstance.isVisible(),
                    effectInstance.showIcon()
            );
            return player.addEffect(newEffect);
        }
        return player.addEffect(effectInstance);
    }
}
