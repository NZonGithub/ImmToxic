//package com.noudzandbergen.voxic.world;
//
//import com.noudzandbergen.voxic.block.Block;
//import com.noudzandbergen.voxic.block.container.Mesher;
//import com.noudzandbergen.voxic.world.chunk.Chunk;
//import com.noudzandbergen.voxic.world.position.ChunkPosition;
//import org.lwjgl.BufferUtils;
//
//import java.nio.FloatBuffer;
//import java.nio.IntBuffer;
//
//public class ChunkMesher implements Mesher {
//
//    private Chunk chunk;
//    private World world;
//
//    private FloatBuffer positions = BufferUtils.createFloatBuffer(Chunk.VOLUME);
//    private IntBuffer indices = BufferUtils.createIntBuffer(Chunk.AREA);
//
//    private int currentVertex = 0;
//
//    private void growPositions() {
//        FloatBuffer old = positions;
//        positions = BufferUtils.createFloatBuffer(old.capacity()*2);
//        positions.put(old);
//    }
//
//    private void growIndices() {
//        IntBuffer old = indices;
//        indices = BufferUtils.createIntBuffer(old.capacity()*2);
//        indices.put(old);
//    }
//
//    public int putVertex(float x, float y, float z) {
//        if (positions.remaining() < 3) growPositions();
//        positions.put(x);
//        positions.put(y);
//        positions.put(z);
//        return currentVertex++;
//    }
//
//    public void putFace(int i1, int i2, int i3) {
//        if (indices.remaining() < 3) growIndices();
//        indices.put(i1);
//        indices.put(i2);
//        indices.put(i3);
//    }
//
//    private int x, y, z;
//    public void process(ChunkPosition position) {
//        this.chunk = position.world.getChunk(position.x, position.y, position.z);
//        this.world = position.world;
//        tripleLoop(position.x, position.y, position.z);
//    }
//
//    private void tripleLoop(int xOff, int yOff, int zOff) {
//
//        int x2 = xOff + Chunk.SIZE;
//        int y2 = yOff + Chunk.SIZE;
//        int z2 = zOff + Chunk.SIZE;
//        for (y = yOff; y < y2; y++)
//            for (x = xOff; x < x2; x++)
//                for (z = zOff; z < z2; z++)
//                    chunk.getBlock(x, y, z).writeTo(this);
//    }
//
//    private void singleLoop(int xOff, int yOff, int zOff) {
//
//        for (int i = 0; i < Chunk.VOLUME; i++) {
//            this.x = xOff + Chunk.getX(i);
//            this.y = yOff + Chunk.getY(i);
//            this.z = zOff + Chunk.getZ(i);
//
//            Block block = chunk.getBlock(i);
//
//            block.writeTo(this);
//        }
//
//    }
//
//    @Override
//    public int addVertex(float x, float y, float z, int textureID, float textureX, float textureY, boolean isTranslucent) {
//        return 0;
//    }
//
//    @Override
//    public void addFace(int vertexIndex1, int vertexIndex2, int vertexIndex3, boolean isTranslucent) {
//
//    }
//
//    public int getX() {
//        return x;
//    }
//
//    public int getY() {
//        return y;
//    }
//
//    public int getZ() {
//        return z;
//    }
//
////    public Chunk getChunk() {
////        return chunk;
////    }
//
//    public World getWorld() {
//        return world;
//    }
//}
