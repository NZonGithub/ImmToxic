package com.noudzandbergen.voxic.world;

import java.util.HashMap;
import java.util.Map;

public class World {

    public static final int INITIAL_SIZE = 8*8*8;

//    private Chunk[] chunks = new Chunk[INITIAL_SIZE];
    private Map<ChunkPosition, Chunk> chunks = new HashMap<>(INITIAL_SIZE);

//    Positional mapping:
//    UP = -Y
//    DOWN = Y
//    NORTH = -Z
//    SOUTH = Z
//    EAST = X
//    WEST = -
//    Conclusion, only the Y axis is inversed

    private static final class ChunkPosition {

        public int x, y, z;

        public ChunkPosition(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        @Override
        public int hashCode() {
            return (y*31+x)*31+z;
        }
    }

}
