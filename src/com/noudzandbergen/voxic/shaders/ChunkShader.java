package com.noudzandbergen.voxic.shaders;

import com.noudzandbergen.toxicengine.Shader;
import com.noudzandbergen.toxicengine.programs.TransformableShaderProgram;

import java.io.File;

public class ChunkShader extends TransformableShaderProgram {

	public ChunkShader() throws Error {
		super(
				Shader.fromFile(new File("res/shaders/chunk.vert"), Shader.Type.VERTEX),
				Shader.fromFile(new File("res/shaders/chunk.frag"), Shader.Type.FRAGMENT)
		);
	}

	@Override
	protected void bindAttributes() {
		bindAttribute("position", 0);
		bindAttribute("textureCoord", 2);
	}

}
