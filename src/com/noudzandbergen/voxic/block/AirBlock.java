package com.noudzandbergen.voxic.block;

import com.noudzandbergen.voxic.world.ChunkMesher;
import com.noudzandbergen.voxic.world.World;

public class AirBlock extends Block {

    public static final AirBlock AIR_BLOCK = new AirBlock();

    private AirBlock() {

    }

    @Override
    void writeTo(ChunkMesher mesher) {

    }

    @Override
    boolean isFaceClear(Face face, int x, int y, int z, World world) {
        return true;
    }
}
