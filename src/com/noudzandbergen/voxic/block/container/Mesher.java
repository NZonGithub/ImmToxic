package com.noudzandbergen.voxic.block.container;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public interface Mesher {

//    TODO: Pack all vertex data into (unsigned) integers or longs.
//     10 bits per position components... Think about this later.
    int addVertex(float x, float y, float z, float textureX, float textureY, boolean isTranslucent);
    void addFace(int vertexIndex1, int vertexIndex2, int vertexIndex3, boolean isTranslucent);
    default void addQuad(int vertexIndex1, int vertexIndex2, int vertexIndex3, int vertexIndex4, boolean isTranslucent) {
        addFace(vertexIndex1, vertexIndex3, vertexIndex2, isTranslucent);
        addFace(vertexIndex1, vertexIndex4, vertexIndex3, isTranslucent);
    }
//    default void addQuad(Face face, float x, float y, float width, float height, float u1, float v1, float u2, float v2, boolean isTranslucent) {
//
//
//
//        switch (face) {
//            case TOP:
//        }
//
//
//        addQuad();
//
//    }



//    default void addQuad(float x1, float y1, float z1, float x2, float y2, float z2, float u1, float v1, float u2, float v2, boolean isTranslucent) {
//
//        int[] vertices = new int[4];
//
//        for ()
//
//        addFace(vertexIndex1, vertexIndex3, vertexIndex2, isTranslucent);
//        addFace(vertexIndex1, vertexIndex4, vertexIndex3, isTranslucent);
//
//    }

    int getX();
    int getY();
    int getZ();
    BlockContainer getBlocks();

    FloatBuffer getPositions();
    FloatBuffer getTextureCoordinates();
    IntBuffer getIndices();

}
