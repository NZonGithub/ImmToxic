package com.noudzandbergen.voxic.world;

public class ChunkManager {

    private static final int INITIAL_CAPACITY_LSHIFT = 8;

    private Chunk[] chunks = new Chunk[1<<INITIAL_CAPACITY_LSHIFT];
    private int capacityLShift = INITIAL_CAPACITY_LSHIFT;

    private int chunkCount;

    private void growChunkList() {
        Chunk[] old = chunks;
        chunks = new Chunk[1<<++capacityLShift];
    }

    private void shrinkChunkList() {

    }

    private Chunk getChunk(int x, int y, int z) {

    }

    private void setChunk(int x, int y, int z, Chunk chunk) {

    }

    private static int hashPosition(int x, int y, int z) {
        return (x * 31 | y) * 31
    }

    public static int scaleToChunkPo(int component) {
        return component >> Chunk.SIZE_POWER;
    }

    private static final class PositionedChunk {

        private int x, y, z;

    }


}
