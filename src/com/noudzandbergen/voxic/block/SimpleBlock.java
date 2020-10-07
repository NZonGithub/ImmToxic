package com.noudzandbergen.voxic.block;

import com.noudzandbergen.voxic.world.ChunkMesher;
import com.noudzandbergen.voxic.world.World;

public class SimpleBlock extends Block {

    @Override
    void writeTo(ChunkMesher mesher) {

    }

    @Override
    boolean isFaceClear(Face face, int x, int y, int z, World world) {
        return true;
    }
}
