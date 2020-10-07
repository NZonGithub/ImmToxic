package com.noudzandbergen.toxicengine;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static org.lwjgl.opengl.GL46C.*;

public class Shader {

    private int uses = 0;
    private final int ID;

    private enum Type {
        VERTEX(GL_VERTEX_SHADER),
        FRAGMENT(GL_FRAGMENT_SHADER),
        TESSELATION_CONTROL(GL_TESS_CONTROL_SHADER),
        TESSELATION_EVALUATION(GL_TESS_EVALUATION_SHADER),
        COMPUTE(GL_COMPUTE_SHADER),
        GEOMETRY(GL_GEOMETRY_SHADER);

        public final int ID;

        Type(int id) {
            this.ID = id;
        }
    }

    // TODO: Replace Error class with ShaderError
    public Shader(String source, int type) throws Error {
        ID = glCreateShader(type);

        glShaderSource(ID, source);
        glCompileShader(ID);

        if (glGetShaderi(ID, GL_COMPILE_STATUS) == GL_FALSE) {
            throw new RuntimeException("Shader compilation failed. Log: " + glGetShaderInfoLog(ID, 2048));
        }
    }

    public Shader(String source, Type type) throws Error {
        this(source, type.ID);
    }

    public static Shader fromFile(File file, Type type) throws RuntimeException {
        try {

            return new Shader(
                    new String(new FileInputStream(file).readAllBytes()),
                    type
            );

        } catch (IOException e) {
            throw new RuntimeException("Shader source could not be read", e);
        }
    }

    public void attach(int shaderProgram) {
        uses++;
        glAttachShader(shaderProgram, ID);
    }

    public void detach(int shaderProgram) {
        glDetachShader(shaderProgram, ID);
        if (--uses == 0) {
            glDeleteShader(ID);
        }
    }
}
