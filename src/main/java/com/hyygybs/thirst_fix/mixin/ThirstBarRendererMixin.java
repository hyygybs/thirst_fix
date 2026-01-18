package com.hyygybs.thirst_fix.mixin;

import com.hyygybs.thirst_fix.ThirstFix;
import dev.ghen.thirst.foundation.gui.ThirstBarRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@OnlyIn(Dist.CLIENT)
@Mixin(ThirstBarRenderer.class)
public class ThirstBarRendererMixin {

    @Unique
    private static final ResourceLocation THIRST_EFFECT_ICONS =
            new ResourceLocation("thirst_fix", "textures/gui/icons.png");

    @Unique
    private static boolean hasCustomThirstEffect() {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player != null) {
            return mc.player.hasEffect(ThirstFix.THIRST.get());
        }
        return false;
    }

    @ModifyArg(
            method = "render",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphics;blit(Lnet/minecraft/resources/ResourceLocation;IIFFIIII)V",
                    ordinal = 0
            ),
            index = 0
    )
    private static ResourceLocation modifyFirstBlitTexture(ResourceLocation original) {
        if (hasCustomThirstEffect()) {
            return THIRST_EFFECT_ICONS;
        }
        return original;
    }

    @ModifyArg(
            method = "render",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphics;blit(Lnet/minecraft/resources/ResourceLocation;IIFFIIII)V",
                    ordinal = 1
            ),
            index = 0
    )
    private static ResourceLocation modifySecondBlitTexture(ResourceLocation original) {
        if (hasCustomThirstEffect()) {
            return THIRST_EFFECT_ICONS;
        }
        return original;
    }

    @ModifyArg(
            method = "render",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphics;blit(Lnet/minecraft/resources/ResourceLocation;IIFFIIII)V",
                    ordinal = 2
            ),
            index = 0
    )
    private static ResourceLocation modifyThirdBlitTexture(ResourceLocation original) {
        if (hasCustomThirstEffect()) {
            return THIRST_EFFECT_ICONS;
        }
        return original;
    }
}
