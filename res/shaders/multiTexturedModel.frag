#version 460 core

in vec4 positionFrag;
in vec2 textureCoordsFrag;

out vec4 fragColor;

void main() {
    fragColor = vec4(textureCoordsFrag.xy, 0, 1);
}