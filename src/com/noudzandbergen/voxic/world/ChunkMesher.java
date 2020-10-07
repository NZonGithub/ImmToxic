package com.noudzandbergen.voxic.world;

import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class ChunkMesher {

    private Chunk chunk;
    private World world;

    private FloatBuffer positions = BufferUtils.createFloatBuffer(Chunk.VOLUME);
    private IntBuffer indices = BufferUtils.createIntBuffer(Chunk.AREA);

    private int currentVertex = 0;

    private void growPositions() {
        FloatBuffer old = positions;
        positions = BufferUtils.createFloatBuffer(old.capacity()*2);
        positions.put(old);
    }

    private void growIndices() {
        IntBuffer old = indices;
        indices = BufferUtils.createIntBuffer(old.capacity()*2);
        indices.put(old);
    }

    public int putVertex(float x, float y, float z) {
        if (positions.remaining() < 3) growPositions();
        positions.put(x);
        positions.put(y);
        positions.put(z);
        return currentVertex++;
    }

    public void putFace(int i1, int i2, int i3) {
        if (indices.remaining() < 3) growIndices();
        indices.put(i1);
        indices.put(i2);
        indices.put(i3);
    }

}
