package com.addie.core;

import com.addie.core.block.*;
import dev.amble.lib.block.ABlockSettings;
import dev.amble.lib.container.impl.BlockContainer;
import dev.amble.lib.datagen.util.AxeMineable;
import dev.amble.lib.datagen.util.NoBlockDrop;
import dev.amble.lib.datagen.util.NoEnglish;
import dev.amble.lib.item.AItemSettings;
import net.minecraft.block.*;
import net.minecraft.block.enums.Instrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.sound.BlockSoundGroup;

import static net.minecraft.block.Blocks.createLightLevelFromLitBlockState;

public class SmoredBlocks extends BlockContainer {


    @NoEnglish
    @NoBlockDrop
    public static final Block MARSHMALLOW_JAR = new MarshmallowJarBlock(ABlockSettings.create()
            .itemSettings(new AItemSettings().group(SmoredItemGroups.MAIN)).requiresTool()
            .strength(0.5F, 1.0F).pistonBehavior(PistonBehavior.NORMAL).sounds(BlockSoundGroup.GLASS));

    @NoEnglish
    @NoBlockDrop
    public static final Block MARSHMALLOW_JAR_RAW = new MarshmallowJarRawBlock(ABlockSettings.create()
            .itemSettings(new AItemSettings()).requiresTool()
            .strength(0.5F, 1.0F).pistonBehavior(PistonBehavior.NORMAL).sounds(BlockSoundGroup.GLASS));

    @NoEnglish
    @NoBlockDrop
    public static final Block MARSHMALLOW_JAR_LIGHTLY_ROASTED = new MarshmallowJarLightlyRoastedBlock(ABlockSettings.create()
            .itemSettings(new AItemSettings()).requiresTool()
            .strength(0.5F, 1.0F).pistonBehavior(PistonBehavior.NORMAL).sounds(BlockSoundGroup.GLASS));

    @NoEnglish
    @NoBlockDrop
    public static final Block MARSHMALLOW_JAR_PERFECTLY_ROASTED = new MarshmallowJarPerfectlyRoastedBlock(ABlockSettings.create()
            .itemSettings(new AItemSettings()).requiresTool()
            .strength(0.5F, 1.0F).pistonBehavior(PistonBehavior.NORMAL).sounds(BlockSoundGroup.GLASS));

    @NoEnglish
    @NoBlockDrop
    public static final Block MARSHMALLOW_JAR_BURNT = new MarshmallowJarBurntBlock(ABlockSettings.create()
            .itemSettings(new AItemSettings()).requiresTool()
            .strength(0.5F, 1.0F).pistonBehavior(PistonBehavior.NORMAL).sounds(BlockSoundGroup.GLASS));

    @NoEnglish
    @NoBlockDrop
    public static final Block COPPER_CAMPFIRE = new CampfireBlock(true,
            1, AbstractBlock.Settings.create().mapColor(MapColor.SPRUCE_BROWN)
            .instrument(Instrument.BASS).strength(2.0F).sounds(BlockSoundGroup.WOOD)
            .luminance(createLightLevelFromLitBlockState(15)).nonOpaque().burnable());

    @NoEnglish
    @AxeMineable
    public static final Block OAK_LOG_SEAT = new LogSeatBlock(ABlockSettings.create()
            .itemSettings(new AItemSettings().group(SmoredItemGroups.MAIN)).requiresTool()
            .strength(0.5F, 1.0F).pistonBehavior(PistonBehavior.NORMAL).sounds(BlockSoundGroup.WOOD));

    @NoEnglish
    @AxeMineable
    public static final Block SPRUCE_LOG_SEAT = new LogSeatBlock(ABlockSettings.create()
            .itemSettings(new AItemSettings().group(SmoredItemGroups.MAIN)).requiresTool()
            .strength(0.5F, 1.0F).pistonBehavior(PistonBehavior.NORMAL).sounds(BlockSoundGroup.WOOD));

    @NoEnglish
    @AxeMineable
    public static final Block BIRCH_LOG_SEAT = new LogSeatBlock(ABlockSettings.create()
            .itemSettings(new AItemSettings().group(SmoredItemGroups.MAIN)).requiresTool()
            .strength(0.5F, 1.0F).pistonBehavior(PistonBehavior.NORMAL).sounds(BlockSoundGroup.WOOD));

    @NoEnglish
    @AxeMineable
    public static final Block JUNGLE_LOG_SEAT = new LogSeatBlock(ABlockSettings.create()
            .itemSettings(new AItemSettings().group(SmoredItemGroups.MAIN)).requiresTool()
            .strength(0.5F, 1.0F).pistonBehavior(PistonBehavior.NORMAL).sounds(BlockSoundGroup.WOOD));

    @NoEnglish
    @AxeMineable
    public static final Block ACACIA_LOG_SEAT = new LogSeatBlock(ABlockSettings.create()
            .itemSettings(new AItemSettings().group(SmoredItemGroups.MAIN)).requiresTool()
            .strength(0.5F, 1.0F).pistonBehavior(PistonBehavior.NORMAL).sounds(BlockSoundGroup.WOOD));

    @NoEnglish
    @AxeMineable
    public static final Block DARK_OAK_LOG_SEAT = new LogSeatBlock(ABlockSettings.create()
            .itemSettings(new AItemSettings().group(SmoredItemGroups.MAIN)).requiresTool()
            .strength(0.5F, 1.0F).pistonBehavior(PistonBehavior.NORMAL).sounds(BlockSoundGroup.WOOD));

    @NoEnglish
    @AxeMineable
    public static final Block MANGROVE_LOG_SEAT = new LogSeatBlock(ABlockSettings.create()
            .itemSettings(new AItemSettings().group(SmoredItemGroups.MAIN)).requiresTool()
            .strength(0.5F, 1.0F).pistonBehavior(PistonBehavior.NORMAL).sounds(BlockSoundGroup.WOOD));

    @NoEnglish
    @AxeMineable
    public static final Block CHERRY_LOG_SEAT = new LogSeatBlock(ABlockSettings.create()
            .itemSettings(new AItemSettings().group(SmoredItemGroups.MAIN)).requiresTool()
            .strength(0.5F, 1.0F).pistonBehavior(PistonBehavior.NORMAL).sounds(BlockSoundGroup.WOOD));

    @NoEnglish
    @AxeMineable
    public static final Block CRIMSON_LOG_SEAT = new LogSeatBlock(ABlockSettings.create()
            .itemSettings(new AItemSettings().group(SmoredItemGroups.MAIN)).requiresTool()
            .strength(0.5F, 1.0F).pistonBehavior(PistonBehavior.NORMAL).sounds(BlockSoundGroup.WOOD));

    @NoEnglish
    @AxeMineable
    public static final Block WARPED_LOG_SEAT = new LogSeatBlock(ABlockSettings.create()
            .itemSettings(new AItemSettings().group(SmoredItemGroups.MAIN)).requiresTool()
            .strength(0.5F, 1.0F).pistonBehavior(PistonBehavior.NORMAL).sounds(BlockSoundGroup.WOOD));

}
