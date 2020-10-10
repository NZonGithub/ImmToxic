package com.noudzandbergen.voxic.world.chunk;

import com.noudzandbergen.voxic.VoxicCoreMod;
import com.noudzandbergen.voxic.block.container.BlockContainer;

public class Chunk implements BlockContainer {

    public static final int SIZE_POWER = 5,
            SIZE = 1 << SIZE_POWER,
            SIZE_MASK = SIZE - 1,
            Z_COMPONENT_MASK = SIZE_MASK,
            X_COMPONENT_MASK = Z_COMPONENT_MASK << SIZE_POWER,
            Y_COMPONENT_MASK = X_COMPONENT_MASK << SIZE_POWER,
            AREA = SIZE * SIZE,
            VOLUME = AREA * SIZE,
            VOLUME_MASK = VOLUME - 1,
            SURFACE_AREA = AREA * 6;

//    YXZ Order
    private int[] blocks = null;
    private int count;

    public Chunk() {

    }

    public int getBlockID(int index) {
        return blocks == null ? 0 : blocks[index];
    }

    public int getBlockID(int x, int y, int z) {
        return blocks == null ? VoxicCoreMod.BLOCK_AIR.ID : getBlockID(calcIndex(x, y, z));
    }

    public void setBlock(int idx, int block) {
        populate();

        if (blocks[idx] == VoxicCoreMod.BLOCK_AIR.ID && block != VoxicCoreMod.BLOCK_AIR.ID) count++;
        else if (blocks[idx] != VoxicCoreMod.BLOCK_AIR.ID && block == VoxicCoreMod.BLOCK_AIR.ID) count--;

        blocks[idx] = block;

        if (count == 0) empty();
    }

    public void setBlock(int x, int y, int z, int block) {
        setBlock(calcIndex(x, y, z), block);
    }

    private void populate() {
        if (this.blocks == null) blocks = new int[VOLUME];
    }

    private void empty() {
        this.blocks = null;
        this.count = 0;
    }

    public static int calcIndex(int x, int y, int z) {
        return ((y & SIZE_MASK << SIZE_POWER | x & SIZE_MASK) << SIZE_POWER | z & SIZE_MASK);
    }

    public static int getX(int index) {
        return index & X_COMPONENT_MASK;
    }

    public static int getY(int index) {
        return index & Y_COMPONENT_MASK;
    }

    public static int getZ(int index) {
        return index & Z_COMPONENT_MASK;
    }

}
