package com.noudzandbergen.toxicengine.renderer;

import org.lwjgl.opengl.GL45C;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL11C.GL_FLOAT;
import static org.lwjgl.opengl.GL15C.*;
import static org.lwjgl.opengl.GL15C.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL30C.glBindVertexArray;
import static org.lwjgl.opengl.GL45C.glCreateVertexArrays;

public class GLIndexedMesh extends GLMesh {

    public final int INDICES_VBO_ID;

    public GLIndexedMesh(int vaoID, int positionsVboID, int textureCoordinatesVboID, int indicesVboID, int vertexCount) {
        super(vaoID, positionsVboID, textureCoordinatesVboID, vertexCount);
        INDICES_VBO_ID = indicesVboID;
    }

    @Override
    public void render() {
        GL45C.glBindVertexArray(VAO_ID);
        GL45C.glDrawElements(GL45C.GL_TRIANGLES, getVertexCount(), GL45C.GL_UNSIGNED_INT, 0);
    }

    protected static int loadIndices(IntBuffer buffer) {
        int vboID = glGenBuffers();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, vboID);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, buffer, GL_STATIC_DRAW);
        return vboID;
    }

    public static GLIndexedMesh loadFrom(FloatBuffer positions, FloatBuffer textureCoordinates, IntBuffer indices) {

        int vaoID = glCreateVertexArrays();
        glBindVertexArray(vaoID);

        int positionsVboID = loadBufferAsVertexAttrib(positions, 0, 3, GL_FLOAT, false);
        int textureCoordinatesVboID = loadBufferAsVertexAttrib(textureCoordinates, 2, 2, GL_FLOAT, false);
        int indicesVboID = loadIndices(indices);

        return new GLIndexedMesh(vaoID, positionsVboID, textureCoordinatesVboID, indicesVboID, indices.limit());
    }
}
