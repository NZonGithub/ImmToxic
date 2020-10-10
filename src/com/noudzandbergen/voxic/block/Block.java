package com.noudzandbergen.voxic.block;

import com.noudzandbergen.voxic.block.container.BlockContainer;
import com.noudzandbergen.voxic.block.container.Mesher;

import java.util.ArrayList;

public abstract class Block {

    private static final ArrayList<Block> blocks = new ArrayList<>();
    public final int ID;

    protected Block() {
        ID = blocks.size();
        blocks.add(this);
    }

    public abstract void writeTo(Mesher mesher);

    public static Block getBlock(int id) {
        return blocks.get(id);
    }

    public abstract boolean isFaceClear(Face face, int x, int y, int z, BlockContainer world);

}
