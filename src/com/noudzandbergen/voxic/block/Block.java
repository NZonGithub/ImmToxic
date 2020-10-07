package com.noudzandbergen.voxic.block;

import com.noudzandbergen.voxic.world.ChunkMesher;
import com.noudzandbergen.voxic.world.World;

import java.util.ArrayList;

public abstract class Block {

    private static final ArrayList<Block> blocks = new ArrayList<>();
    public final int ID;

    protected Block() {
        ID = blocks.size();
        blocks.add(this);
    }

    abstract void writeTo(ChunkMesher mesher);

    public static Block getBlock(int id) {
        return blocks.get(id);
    }

    abstract boolean isFaceClear(Face face, int x, int y, int z, World world);

    public enum Face {
        TOP,
        BOTTOM,
        NORTH,
        EAST,
        SOUTH,
        WEST;
    }

}
