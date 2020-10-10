package com.noudzandbergen.voxic.world.fixed;

import com.noudzandbergen.voxic.block.Block;
import com.noudzandbergen.voxic.block.container.Mesher;
import com.noudzandbergen.voxic.world.World;
import com.noudzandbergen.voxic.world.GenericMesher;

public class FixedWorld implements World {

    private final int[] blocks;
    public final int sizeX, sizeY, sizeZ;
    public final int volume;

    public FixedWorld(int sizeX, int sizeY, int sizeZ) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.sizeZ = sizeZ;
        volume = sizeX*sizeY*sizeZ;
        blocks = new int[volume];
    }

    public Mesher createMesh() {
        GenericMesher mesher = new GenericMesher(this, 0, 0, 0, sizeX, sizeY, sizeZ);
        mesher.run();
        return mesher;
    }

    @Override
    public int getBlockID(int x, int y, int z) {
        if (
                x < 0 || x >= sizeX ||
                y < 0 || y >= sizeY ||
                z < 0 || z >= sizeZ
        ) return 0;
        return blocks[(y*sizeX+x)*sizeZ+z];
    }

    public void setBlock(int x, int y, int z, int block) {
        if (
                x < 0 || x >= sizeX ||
                y < 0 || y >= sizeY ||
                z < 0 || z >= sizeZ
        ) return;
        blocks[(y*sizeX+x)*sizeZ+z] = block;
    }

    public void setBlock(int x, int y, int z, Block block) {
        setBlock(x, y, z, block.ID);
    }
}
