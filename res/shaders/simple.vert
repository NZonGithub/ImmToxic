#version 460 core

in vec3 position;

void main() {
    //    fragColor = vec4(textureCoordsFrag.xy, 0, 1);
    gl_Position = vec4(position, 1);
}