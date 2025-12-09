package com.addie;

import com.addie.core.SmoredBlocks;
import com.addie.core.SmoredItemGroups;
import com.addie.core.SmoredItems;
import dev.amble.lib.container.RegistryContainer;
import net.fabricmc.api.ModInitializer;

import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Smored implements ModInitializer {
	public static final String MOD_ID = "smored";

    public static Identifier id(String path) {
        return new Identifier(MOD_ID, path);
    }
	

	@Override
	public void onInitialize() {
        RegistryContainer.register(SmoredItems.class, MOD_ID);
        RegistryContainer.register(SmoredBlocks.class, MOD_ID);
        RegistryContainer.register(SmoredItemGroups.class, MOD_ID);
	}
}