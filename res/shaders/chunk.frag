#version 460 core

in vec2 textureCoordFrag;

out vec4 fragColor;

void main() {
    fragColor = vec4(textureCoordFrag.xy, 0, 1);
//    fragColor = vec4(1, 1, 1, 1);
}