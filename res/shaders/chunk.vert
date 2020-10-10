#version 460 core

in vec3 position;
in vec2 textureCoord;

out vec2 textureCoordFrag;

uniform mat4 projectionMatrix;
uniform mat4 viewMatrix;
uniform mat4 modelMatrix;

void main() {
    gl_Position = projectionMatrix * viewMatrix * modelMatrix * vec4(position, 1.0f);
    textureCoordFrag = textureCoord;
}