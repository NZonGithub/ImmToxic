package com.noudzandbergen.toxicengine.renderer;

//import java.nio.FloatBuffer;
//import java.nio.IntBuffer;
//
//import static org.lwjgl.opengl.GL46C.*;

public interface Model {

    void render();

//    private final int vaoId;
//    private final int positionsVboId;
//    private final int textureCoordinatesVboId;
//    private final int indicesVboId;

//    public Model(FloatBuffer positions, FloatBuffer textureCoordinates, IntBuffer indices) {
//        vaoId = glCreateVertexArrays();
//        glBindVertexArray(vaoId);
//
//        glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
//        glEnableVertexAttribArray(0);
//
//        glVertexAttribPointer(1, 2, GL_FLOAT, false, 0, 0);
//        glEnableVertexAttribArray(1);
//    }

//    TODO: Allow streaming new chunk meshes when breaking blocks for example.
//    public void update(FloatBuffer positions, FloatBuffer textureCoordinates, IntBuffer indices) {
//
//    }

//    public void render() {
//
//    }
//
//    private int createBuffer(FloatBuffer buffer) {
//        int id = glCreateBuffers();
//        glBindBuffer(GL_ARRAY_BUFFER, id);
//        glBufferData(id, buffer, GL_DYNAMIC_DRAW);
//        return id;
//    }
//
//    private int createBuffer(IntBuffer buffer) {
//        int id = glCreateBuffers();
//        glBindBuffer(GL_ARRAY_BUFFER, id);
//        glBufferData(GL_ARRAY_BUFFER, buffer, GL_DYNAMIC_DRAW);
//        return id;
//    }
//
//    private void destroy() {
//        glDeleteVertexArrays(vaoId);
//        glDeleteBuffers(positionsVboId);
//        glDeleteBuffers(textureCoordinatesVboId);
//        glDeleteBuffers(indicesVboId);
//    }

}
