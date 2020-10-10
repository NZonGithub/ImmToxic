package com.noudzandbergen.voxic.world;

import com.noudzandbergen.voxic.block.Block;
import com.noudzandbergen.voxic.world.chunk.Chunk;

public class ChunkWorld implements World {

    public static final int INITIAL_SIZE = 8*8*8;

//    private ChunkManager chunks = new ChunkManager();

//    Positional mapping:
//    UP = Y
//    DOWN = -Y
//    NORTH = -Z
//    SOUTH = Z
//    EAST = X
//    WEST = -X
//    Conclusion, no axes are inverse. Our world space is the same as that of openGL.

    public int getBlockID(int x, int y, int z) {
//        return manager
        throw new UnsupportedOperationException();
    }

    public Block getBlock(int x, int y, int z) {
        return Block.getBlock(getBlockID(x, y, z));
    }

//    @Override
//    public Mesher createMesh() {
//        return null;
//    }

    public Chunk getChunk(int x, int y, int z) {
//        chunks
        return null;
    }
}
