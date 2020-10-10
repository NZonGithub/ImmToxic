package com.noudzandbergen.toxicengine.programs;

import com.noudzandbergen.toxicengine.Shader;
import com.noudzandbergen.toxicengine.ShaderProgram;
import com.noudzandbergen.toxicengine.program.Transformable;
import org.joml.Matrix4f;

public abstract class TransformableShaderProgram extends ShaderProgram implements Transformable {

    private int worldLocation, viewLocation, modelLocation;

    protected TransformableShaderProgram(Shader vertexShader, Shader fragmentShader) throws Error {
        super(vertexShader, fragmentShader);
    }

    @Override
    protected void getUniformLocations() {
        worldLocation = getUniformLocation("projectionMatrix");
        viewLocation = getUniformLocation("viewMatrix");
        modelLocation = getUniformLocation("modelMatrix");
    }

    public void setProjectionMatrix(Matrix4f worldMatrix) {
        load(worldLocation, worldMatrix);
    }

    public void setViewMatrix(Matrix4f viewMatrix) {
        load(viewLocation, viewMatrix);
    }

    public void setModelMatrix(Matrix4f modelMatrix) {
        load(modelLocation, modelMatrix);
    }

//    void setModelMatrix(Vector3f position, Vector3f rotation, Vector3f scale) {
//        load(modelLocation, new Matrix4f()
//                .identity()
//                .translate(position)
//                .rotateXYZ(rotation)
//                .scale(scale));
//    }

//    void setModelMatrix(Vector3f position, Quaternionf rotation, Vector3f scale) {
//        load(modelLocation, new Matrix4f()
//                .identity()
//                .translate(position)
//                .rotate(rotation)
//                .scale(scale));
//    }

}
