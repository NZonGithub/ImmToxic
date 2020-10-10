package com.noudzandbergen.voxic;

import com.noudzandbergen.voxic.block.Block;
import com.noudzandbergen.voxic.block.Face;
import com.noudzandbergen.voxic.block.container.BlockContainer;
import com.noudzandbergen.voxic.block.container.Mesher;
import com.noudzandbergen.voxic.mod.Mod;

public class VoxicCoreMod extends Mod {

    public static final Block BLOCK_AIR = new Block() {
        @Override
        public void writeTo(Mesher mesher) {

        }

        @Override
        public boolean isFaceClear(Face face, int x, int y, int z, BlockContainer world) {
            return true;
        }
    };

}
