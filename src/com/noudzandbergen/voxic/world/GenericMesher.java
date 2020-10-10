package com.noudzandbergen.voxic.world;

import com.noudzandbergen.voxic.block.container.BlockContainer;
import com.noudzandbergen.voxic.block.container.Mesher;
import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class GenericMesher implements Mesher, Runnable {

    private final BlockContainer blockContainer;

    private int x, y, z;
    private final int x1, y1, z1, x2, y2, z2;

    private int localX, localY, localZ;

    public GenericMesher(BlockContainer world, int x1, int y1, int z1, int x2, int y2, int z2) {
        this.blockContainer = world;
        this.x1 = x1;
        this.y1 = y1;
        this.z1 = z1;
        this.x2 = x2;
        this.y2 = y2;
        this.z2 = z2;
    }

    @Override
    public void run() {
        positions = BufferUtils.createFloatBuffer(INITIAL_POSITIONS_CAPACITY);
        indices = BufferUtils.createIntBuffer(INITIAL_INDICES_CAPACITY);
        textureCoordinates = BufferUtils.createFloatBuffer(INITIAL_TEXTURE_COORDINATES_CAPACITY);

        localY = 0;
        for (y = y1; y < y2; y++) {
            localY++;
            localX = 0;
            for (x = x1; x < x2; x++) {
                localX++;
                localZ = 0;
                for (z = z1; z < z2; z++) {
                    localZ++;
                    blockContainer.getBlock(x, y, z).writeTo(this);
                }
            }
        }

        positions.flip();
        textureCoordinates.flip();
        indices.flip();
    }

    private int vertexCount = 0;

    private FloatBuffer positions;
    private FloatBuffer textureCoordinates;
    private IntBuffer indices;

    private static final int EXPECTED_VERTEX_COUNT = 1024;
//    private static final int EXPECTED_INDEX_COUNT = 16;
//    private static final int EXPECTED_VERTEX_COUNT = 32;
    private static final int INITIAL_POSITIONS_CAPACITY = EXPECTED_VERTEX_COUNT*3;
    private static final int INITIAL_TEXTURE_COORDINATES_CAPACITY = EXPECTED_VERTEX_COUNT*2;
    private static final int INITIAL_INDICES_CAPACITY = EXPECTED_VERTEX_COUNT*2;

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public int getZ() {
        return z;
    }

    @Override
    public BlockContainer getBlocks() {
        return blockContainer;
    }

    @Override
    public FloatBuffer getPositions() {
        return positions;
    }

    @Override
    public FloatBuffer getTextureCoordinates() {
        return textureCoordinates;
    }

    @Override
    public IntBuffer getIndices() {
        return indices;
    }

    @Override
    public int addVertex(float x, float y, float z, float textureX, float textureY, boolean isTranslucent) {
        if (isTranslucent) return 0;

        if (positions.capacity() - positions.position() < 3) growPositionBuffer();
        if (textureCoordinates.capacity() - textureCoordinates.position() < 2) growTextureCoordinateBuffer();

        positions.put(x + this.localX);
        positions.put(y + this.localY);
        positions.put(z + this.localZ);
        textureCoordinates.put(textureX);
        textureCoordinates.put(textureY);

        return vertexCount++;
    }

    @Override
    public void addFace(int vertexIndex1, int vertexIndex2, int vertexIndex3, boolean isTranslucent) {
        if (isTranslucent) return;

        if (indices.capacity() - indices.position() < 3) growIndexBuffer();

        indices.put(vertexIndex1);
        indices.put(vertexIndex2);
        indices.put(vertexIndex3);
    }

    private void growPositionBuffer() {
        FloatBuffer old = positions.flip();
        positions = BufferUtils.createFloatBuffer(old.capacity()*2);
        positions.put(old);
    }

    private void growTextureCoordinateBuffer() {
        FloatBuffer old = textureCoordinates.flip();
        textureCoordinates = BufferUtils.createFloatBuffer(old.capacity()*2);
        textureCoordinates.put(old);
    }

    private void growIndexBuffer() {
        IntBuffer old = indices.flip();
        indices = BufferUtils.createIntBuffer(old.capacity()*2);
        indices.put(old);
    }
}
