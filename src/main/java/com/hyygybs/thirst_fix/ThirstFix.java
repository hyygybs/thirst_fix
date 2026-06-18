package com.hyygybs.thirst_fix;

import com.hyygybs.thirst_fix.effect.HydrationEffect;
import com.hyygybs.thirst_fix.effect.ThirstEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

@Mod(ThirstFix.MODID)
public class ThirstFix {
    public static final String MODID = "thirst_fix";

    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(Registries.MOB_EFFECT, MODID);
    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(Registries.POTION, MODID);

    public static final DeferredHolder<MobEffect, ThirstEffect> THIRST = EFFECTS.register("thirst", ThirstEffect::new);
    public static final DeferredHolder<Potion, Potion> THIRST_POTION = POTIONS.register("thirst", () -> new Potion("thirst", new MobEffectInstance(THIRST, 3600, 0, false, true, true)));
    public static final DeferredHolder<Potion, Potion> THIRST_POTION_LONG = POTIONS.register("thirst_long", () -> new Potion("thirst_long", new MobEffectInstance(THIRST, 9600, 0, false, true, true)));
    public static final DeferredHolder<MobEffect, HydrationEffect> HYDRATION = EFFECTS.register("hydration", HydrationEffect::new);
    public static final DeferredHolder<Potion, Potion> HYDRATION_FILL_POTION = POTIONS.register("hydration", () -> new Potion("hydration", new MobEffectInstance(HYDRATION, 3600, 0, false, true, true)));
    public static final DeferredHolder<Potion, Potion> HYDRATION_FILL_POTION_LONG = POTIONS.register("hydration_long", () -> new Potion("hydration_long", new MobEffectInstance(HYDRATION, 9600, 0, false, true, true)));

    public ThirstFix(IEventBus modEventBus, ModContainer modContainer) {
        EFFECTS.register(modEventBus);
        POTIONS.register(modEventBus);
    }
}
