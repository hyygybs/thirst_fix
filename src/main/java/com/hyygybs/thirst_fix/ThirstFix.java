package com.hyygybs.thirst_fix;

import com.hyygybs.thirst_fix.effect.HydrationEffect;
import com.hyygybs.thirst_fix.effect.ThirstEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod(ThirstFix.MOD_ID)
public class ThirstFix {
    public static final String MOD_ID = "thirst_fix";

    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, "thirst_fix");
    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTIONS, "thirst_fix");
    public static final RegistryObject<MobEffect> THIRST = EFFECTS.register("thirst", ThirstEffect::new);
    public static final RegistryObject<Potion> THIRST_POTION = POTIONS.register("thirst", () -> new Potion("thirst", new MobEffectInstance(THIRST.get(), 3600, 0)));
    public static final RegistryObject<Potion> THIRST_POTION_LONG = POTIONS.register("thirst_long", () -> new Potion("thirst_long", new MobEffectInstance(THIRST.get(), 9600, 0)));
    public static final RegistryObject<MobEffect> HYDRATION = EFFECTS.register("hydration", HydrationEffect::new);
    public static final RegistryObject<Potion> HYDRATION_POTION = POTIONS.register("hydration", () -> new Potion("hydration", new MobEffectInstance(HYDRATION.get(), 3600, 0)));
    public static final RegistryObject<Potion> HYDRATION_POTION_LONG = POTIONS.register("hydration_long", () -> new Potion("hydration_long", new MobEffectInstance(HYDRATION.get(), 9600, 0)));

    public ThirstFix() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ThirstFix.register(bus); // 确保调用注册方法
    }
    public static void register(IEventBus eventBus) {
        EFFECTS.register(eventBus);
        POTIONS.register(eventBus);
    }
}
