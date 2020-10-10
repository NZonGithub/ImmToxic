package com.noudzandbergen.voxic.block.container;

import com.noudzandbergen.voxic.block.Block;

public interface BlockContainer {

    int getBlockID(int x, int y, int z);
    default Block getBlock(int x, int y, int z) {
        return Block.getBlock(getBlockID(x, y, z));
    }

}
