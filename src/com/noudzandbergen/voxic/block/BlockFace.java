package com.noudzandbergen.voxic.block;

import com.noudzandbergen.voxic.world.position.BlockPosition;

public class BlockFace {

    public Face face;
    public BlockPosition blockPosition;

    public BlockFace(Face face, BlockPosition blockPosition) {
        this.face = face;
        this.blockPosition = blockPosition;
    }
}
