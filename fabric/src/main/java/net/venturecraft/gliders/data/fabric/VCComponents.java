package net.venturecraft.gliders.data.fabric;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistryV3;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import dev.onyxstudios.cca.api.v3.entity.RespawnCopyStrategy;
import net.venturecraft.gliders.VCGliders;

public class VCComponents implements EntityComponentInitializer {

    public static final ComponentKey<GliderDataImpl> PLAYER_DATA = ComponentRegistryV3.INSTANCE.getOrCreate(VCGliders.id("player_data"), GliderDataImpl.class);

    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.registerForPlayers(PLAYER_DATA, GliderDataImpl::new, RespawnCopyStrategy.CHARACTER);
    }
}