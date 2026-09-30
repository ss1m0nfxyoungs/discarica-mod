package com.discarica;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod(DiscaricaMod.MODID)
public class DiscaricaMod {
    public static final String MODID = "discarica";

    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    public static final DeferredRegister<CreativeModeTab> TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, MODID);

    // Blocco Pattume
    public static final RegistryObject<Block> PATTUME_BLOCK = BLOCKS.register("pattume",
            () -> new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_BROWN)
                    .strength(1.0F)
                    .sound(SoundType.GRAVEL)));

    public static final RegistryObject<Item> PATTUME_ITEM = ITEMS.register("pattume",
            () -> new BlockItem(PATTUME_BLOCK.get(), new Item.Properties()));

    // Ricetta: 9 blocchi qualsiasi (griglia 3x3) -> 9 Blocchi Pattume
    public static final RegistryObject<RecipeSerializer<PattumeRecipe>> PATTUME_SERIALIZER =
            RECIPE_SERIALIZERS.register("pattume_crafting",
                    () -> new SimpleCraftingRecipeSerializer<>(PattumeRecipe::new));

    // Scheda nel menu creativo
    public static final RegistryObject<CreativeModeTab> TAB = TABS.register("discarica_tab",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.discarica"))
                    .icon(() -> PATTUME_ITEM.get().getDefaultInstance())
                    .displayItems((parameters, output) -> output.accept(PATTUME_ITEM.get()))
                    .build());

    public DiscaricaMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        RECIPE_SERIALIZERS.register(modEventBus);
        TABS.register(modEventBus);
    }
}
