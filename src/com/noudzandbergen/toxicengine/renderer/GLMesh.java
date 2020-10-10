package com.noudzandbergen.toxicengine.renderer;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL45C.*;

public class GLMesh implements Model {

	protected final int VAO_ID;
	private final int POSITIONS_VBO_ID;
	private final int TEXTURE_COORDINATES_VBO_ID;
	protected int vertexCount;

	protected GLMesh(int vaoID, int positionsVboID, int textureCoordinatesVboID, int vertexCount) {
		VAO_ID = vaoID;
		POSITIONS_VBO_ID = positionsVboID;
		TEXTURE_COORDINATES_VBO_ID = textureCoordinatesVboID;

		this.vertexCount = vertexCount;
	}

	public void render() {

	}

	protected int getVertexCount() {
		return vertexCount;
	}

	public void delete() {
		glDeleteVertexArrays(VAO_ID);
		if (POSITIONS_VBO_ID != 0) glDeleteBuffers(POSITIONS_VBO_ID);
		if (TEXTURE_COORDINATES_VBO_ID != 0) glDeleteBuffers(TEXTURE_COORDINATES_VBO_ID);
	}

	protected static int loadBuffer(FloatBuffer buffer) {
		if (buffer == null) return 0;
		int vbo = glCreateBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vbo);
		glBufferData(GL_ARRAY_BUFFER, buffer, GL_DYNAMIC_DRAW);
		return vbo;
	}

	protected static int loadBufferAsVertexAttrib(FloatBuffer buffer, int index, int componentCount, int glType, boolean normalized) {
		int vboID = loadBuffer(buffer);
		if (vboID == 0) return 0;

		// Todo: experiment with not calling the following methods when the vboID is 0
		glVertexAttribPointer(index, componentCount, glType, normalized, 0, 0);
		glEnableVertexAttribArray(index);
		return vboID;
	}

	public static GLMesh loadFrom(FloatBuffer positions, FloatBuffer textureCoordinates) {

		int vaoID = glCreateVertexArrays();
		glBindVertexArray(vaoID);

		int positionsVboID = loadBufferAsVertexAttrib(positions, 0, 3, GL_FLOAT, false);
		int textureCoordinatesVboID = loadBufferAsVertexAttrib(textureCoordinates, 2, 2, GL_FLOAT, false);

		return new GLMesh(vaoID, positionsVboID, textureCoordinatesVboID, positions.limit()/3);
	}

	@Override
	public String toString() {
		return String.format("GLMesh with %d vertices", vertexCount);
	}

}