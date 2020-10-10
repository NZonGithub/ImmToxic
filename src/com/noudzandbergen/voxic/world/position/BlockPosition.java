package com.noudzandbergen.voxic.world.position;

import com.noudzandbergen.voxic.block.Block;
import com.noudzandbergen.voxic.block.container.BlockContainer;

public class BlockPosition {

    public int x, y, z;
    public BlockContainer container;

    public BlockPosition() {

    }

    public BlockPosition(BlockContainer container, int x, int y, int z) {
        this.container = container;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Block getBlock() {
        return container.getBlock(x, y, z);
    }
}
