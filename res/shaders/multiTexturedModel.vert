#version 460 core

in vec3 position;
// TODO: Change into vec4, because texture arrays don't support variable sizes.
in vec2 textureCoords;

out vec4 positionFrag;
out vec2 textureCoordsFrag;

uniform mat4 projectionMatrix;
uniform mat4 viewMatrix;
uniform mat4 modelMatrix;

void main() {
    positionFrag = modelMatrix * projectionMatrix * viewMatrix * vec4(position.xyz, 0);
    textureCoordsFrag = textureCoords;
}