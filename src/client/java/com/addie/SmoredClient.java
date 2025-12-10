package com.addie;

import com.addie.core.SmoredBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;


public class SmoredClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(SmoredBlocks.MARSHMALLOW_JAR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(SmoredBlocks.MARSHMALLOW_JAR_BURNT, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(SmoredBlocks.MARSHMALLOW_JAR_PERFECTLY_ROASTED, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(SmoredBlocks.MARSHMALLOW_JAR_RAW, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(SmoredBlocks.MARSHMALLOW_JAR_SLIGHTLY_ROASTED, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(SmoredBlocks.COPPER_CAMPFIRE, RenderLayer.getCutout());
    }
}
