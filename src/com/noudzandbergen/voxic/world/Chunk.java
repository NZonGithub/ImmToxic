package com.noudzandbergen.voxic.world;

public class Chunk {

    public static final int SIZE_POWER = 5,
            SIZE = 1 << SIZE_POWER,
            SIZE_MASK = SIZE - 1,
            AREA = SIZE * SIZE,
            VOLUME = AREA * SIZE,
            VOLUME_MASK = VOLUME - 1,
            SURFACE_AREA = AREA * 6;

    private int[] blocks = null;
    private int count;

    public Chunk() {

    }

    public int getBlock(int x, int y, int z) {
        return blocks == null ? 0 : blocks[calcIndex(x, y, z)];
    }

    public void setBlock(int idx, int block) {
        populate();

        if (blocks[idx] == 0 && block != 0) count++;
        else if (blocks[idx] != 0 && block == 0) count--;

        blocks[idx] = block;

        if (count == 0) clear();
    }

    public void setBlock(int x, int y, int z, int block) {
        setBlock(calcIndex(x, y, z), block);
    }

    private void populate() {
        if (this.blocks == null) blocks = new int[VOLUME];
    }

    private void clear() {
        this.blocks = null;
        this.count = 0;
    }

    public static int calcIndex(int x, int y, int z) {
        return ((y << SIZE_POWER | x) << SIZE_POWER | z) & VOLUME_MASK;
    }

}
