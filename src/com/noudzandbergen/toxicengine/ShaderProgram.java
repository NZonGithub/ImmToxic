package com.noudzandbergen.toxicengine;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL46C.*;

public abstract class ShaderProgram {

    private final Shader VS, FS;
    private final int ID;

    private static ShaderProgram activeProgram;

    private static final FloatBuffer fBuffer = BufferUtils.createFloatBuffer(16);

    protected ShaderProgram(Shader vertexShader, Shader fragmentShader) throws Error {

        VS = vertexShader;
        FS = fragmentShader;

        ID = glCreateProgram();
        VS.attach(ID);
        FS.attach(ID);

        bindAttributes();

        glLinkProgram(ID);
        glValidateProgram(ID);

        getUniformLocations();
    }

    public void destroy() {

        VS.detach(ID);
        FS.detach(ID);
        glDeleteProgram(ID);

    }

    public void use() {
        if (this != activeProgram) {
            activeProgram = this;
            glUseProgram(ID);
        }
    }

    protected void load(int location, int value) {
        glProgramUniform1i(ID, location, value);
    }

    protected void load(int location, float value) {
        glProgramUniform1f(ID, location, value);
    }
    protected void load(int location, Vector2f value) {
        glProgramUniform2f(ID, location, value.x, value.y);
    }
    protected void load(int location, Vector3f value) {
        glProgramUniform3f(ID, location, value.x, value.y, value.z);
    }

    protected void load(int location, Matrix4f value) {
        glProgramUniformMatrix4fv(ID, location, false, value.get(fBuffer));
    }
    protected void loadMatTransposed(int location, Matrix4f value) {
        glProgramUniformMatrix4fv(ID, location, true, value.get(fBuffer));
    }

    protected abstract void bindAttributes();
    protected abstract void getUniformLocations();

    protected void bindAttribute(String name, int location) {
        glBindAttribLocation(ID, location, name);
    }

    protected int getUniformLocation(String name) {
        return glGetUniformLocation(ID, name);
    }

}
