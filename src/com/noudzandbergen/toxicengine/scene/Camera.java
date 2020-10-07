package com.noudzandbergen.toxicengine.scene;

import org.joml.Matrix4f;
import org.joml.Vector3f;

public class Camera {

//    Location data
    private final Vector3f position;
    private final Vector3f rotation;

//    Camera settings
    private float aspectRatio = 16f/9f;
    private float fov = (float) (Math.PI*.5f);
    private float near = 0.1f;
    private float far = 1000f;

//    Cached
    private final Matrix4f viewMatrix = new Matrix4f();

    public Camera(Vector3f position, Vector3f rotation) {
        this.position = position;
        this.rotation = rotation;
    }

    public void setPosition() {

    }

    private void updateViewMatrix() {
        viewMatrix.perspective(fov, aspectRatio, near, far);
    }

    public Matrix4f getViewMatrix() {
        return this.viewMatrix;
    }

    public Matrix4f getWorldMatrix() {
        return this.viewMatrix;
    }

}
