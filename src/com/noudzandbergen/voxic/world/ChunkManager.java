//package com.noudzandbergen.voxic.world;
//
//import com.noudzandbergen.voxic.world.chunk.Chunk;
//
//public class ChunkManager {
//
//    private static final int INITIAL_CAPACITY_LEFT_SHIFT = 8; // Good for 256 chunks
//    private static final int MINIMAL_CAPACITY_LEFT_SHIFT = 4; // Good for 16 chunks
//
////    Basically indicates how many items / capacity should be in a hashmap for it to grow
//    private static final float CAPACITY_GROW_THRESHOLD_MULTIPLIER = .75f;
//
////    Indicates how many items / capacity should be in a hashmap for it to shrink
//    private static final float CAPACITY_SHRINK_THRESHOLD_MULTIPLIER = .2f;
//
////    Indicates how much to increase / decrease the capacity left shift with. If it's 2, the capacity will grow or shrink 4x every time it changes in size.
//    private static final int CAPACITY_LEFT_SHIFT_INCREMENT = 1;
//
//    private PositionedChunk[] chunks = new PositionedChunk[1 << INITIAL_CAPACITY_LEFT_SHIFT];
//
//    private int capacityLeftShift = INITIAL_CAPACITY_LEFT_SHIFT;
//    private int capacityMask = 1 << capacityLeftShift - 1;
//
//    private int growThreshold;
//    private int shrinkThreshold;
//
//    private int chunkCount;
//
//    private void growChunkList() {
//        PositionedChunk[] old = chunks;
//        chunks = new PositionedChunk[1<<++capacityLeftShift];
//
//        for (PositionedChunk chunk : old) {
//            put(chunk.x, chunk.y, chunk.z, chunk.chunk);
//        }
//
//        private int capacityMask = 1 << capacityLeftShift - 1;
//    }
//
//    private void shrinkChunkList() {
//        if (chunkCount > chunks.length >> 1) return;
//
//        private int capacityMask = 1 << capacityLeftShift - 1;
//    }
//
//    private void resize(int capacityLeftShift) {
//        this.capacityLeftShift = capacityLeftShift;
//
//        PositionedChunk[] old = chunks;
//
//        chunks = new PositionedChunk[1<<--capacityLeftShift];
//
//        growThreshold = (int) (chunks.length * CAPACITY_GROW_THRESHOLD_MULTIPLIER);
//        if (capacityLeftShift == MINIMAL_CAPACITY_LEFT_SHIFT) shrinkThreshold = (int) (chunks.length * CAPACITY_SHRINK_THRESHOLD_MULTIPLIER);
//
//        for (PositionedChunk chunk : old) {
//            put(chunk.x, chunk.y, chunk.z, chunk.chunk);
//        }
//
//    }
//
//    public Chunk get(int x, int y, int z) {
//
//        int hash = hashPosition(x >> Chunk.SIZE_POWER, y >> Chunk.SIZE_POWER, z >> Chunk.SIZE_POWER);
//
//        PositionedChunk other;
//        int index = hash;
//        while (true) {
//
//            other = chunks[index &= capacityMask];
//
//            if (other == null) break;
//            else if (x == other.x && y == other.y && z == other.z) {
//                chunks[index] = null;
//                break;
//            }
//
//            index++;
//        }
//    }
//
//    private void put(int x, int y, int z, Chunk chunk) {
//        if (chunk == null) remove(x, y, z);
//        else put(new PositionedChunk(chunk, x, y, z));
//    }
//
//    private void remove(int x, int y, int z) {
//
//        int hash = hashPosition(x >> Chunk.SIZE_POWER, y >> Chunk.SIZE_POWER, z >> Chunk.SIZE_POWER);
//
//
//        if (chunkCount >= growThreshold)
//            resize(capacityLeftShift + CAPACITY_LEFT_SHIFT_INCREMENT);
//
//        PositionedChunk other;
//        int index = hash;
//
//        while (true) {
//
//            other = chunks[index &= capacityMask];
//
//            if (other == null) break;
//            else if (x == other.x && y == other.y && z == other.z) {
//                chunks[index] = null;
//                break;
//            }
//
//            index++;
//        }
//
//    }
//
//    private void swapSameHash(int hash, int startIndex) {
//
//        PositionedChunk other;
//        int index = hash;
//
//        int lastSameHash;
//
//        while (true) {
//
//            other = chunks[index &= capacityMask];
//
//            if (other == null) return;
//            else if (hashPosition(other.x, other.y, other.z) == hash) {
//                lastSameHash = void;
//                chunks[index] = null;
//                index++;
//                continue;
//                break;
//            }
//
//        }
//
//    }
//
//    private void put(PositionedChunk chunk) {
//
//        int hash = hashPosition(chunk.x >> Chunk.SIZE_POWER, chunk.y >> Chunk.SIZE_POWER, chunk.z >> Chunk.SIZE_POWER);
//
//        PositionedChunk other;
//
//        int index = hash;
//        while (true) {
//
//            index &= hash;
//
//            other = chunks[index];
//
//            if (other == null) {
//                chunks[index] = chunk;
//
//                if (++chunkCount > )
//
//                break;
//            }
//            else if (other.x == chunk.x && other.y == chunk.y && other.z == chunk.z) {
//                other.chunk = chunk.chunk;
//                break;
//            }
//
//            index++;
//        }
//    }
//
//    private static int hashPosition(int x, int y, int z) {
//        return x = ((x * 31 + y) * 31 + z) ^ x >>> 16;
//    }
//
////    private int indexPosition(int x, int y, int z) {
////        return ((x = ((x * 31 + y) * 31 + z)) ^ x >>> 16) & capacityMask;
////    }
//
//    private static final class PositionedChunk {
//
//        public Chunk chunk;
//        public int x, y, z;
//
//        public PositionedChunk(Chunk chunk, int x, int y, int z) {
//            this.chunk = chunk;
//            this.x = x;
//            this.y = y;
//            this.z = z;
//        }
//    }
//
//
//}
