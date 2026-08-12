package com.rodrigodev.warframemobility.network;

import com.rodrigodev.warframemobility.Warframemobility;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

public record SlideStatePayload(boolean sliding) implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<SlideStatePayload> TYPE =
        new CustomPacketPayload.Type<>(Warframemobility.id("slide_state"));

    public static final StreamCodec<RegistryFriendlyByteBuf, SlideStatePayload> CODEC =
        StreamCodec.composite(
            ByteBufCodecs.BOOL, SlideStatePayload::sliding,
            SlideStatePayload::new
        );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
