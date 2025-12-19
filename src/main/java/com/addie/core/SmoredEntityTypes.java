package com.addie.core;

import com.addie.core.entity.SeatEntity;
import dev.amble.lib.container.AssignedName;
import dev.amble.lib.container.impl.EntityContainer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;

public class SmoredEntityTypes implements EntityContainer {

    @AssignedName("seat")
    public static final EntityType<SeatEntity> SEAT = FabricEntityTypeBuilder
            .create(SpawnGroup.MISC, SeatEntity::new).dimensions(EntityDimensions.changing(1f, 0.5f))
            .disableSaving()
            .disableSummon()
            .build();
}

