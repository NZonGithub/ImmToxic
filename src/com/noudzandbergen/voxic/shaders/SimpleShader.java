package com.noudzandbergen.voxic.shaders;

import com.noudzandbergen.toxicengine.Shader;
import com.noudzandbergen.toxicengine.ShaderProgram;

import java.io.File;

public class SimpleShader extends ShaderProgram {

	private static final File vertexShader = new File("res/shaders/simple.vert");
	private static final File fragmentShader = new File("res/shaders/simple.frag");

	public SimpleShader() throws Error {
		super(
				Shader.fromFile(vertexShader, Shader.Type.VERTEX),
				Shader.fromFile(fragmentShader, Shader.Type.FRAGMENT)
		);
	}

	@Override
	protected void bindAttributes() {
		bindAttribute("position", 0);
	}

	@Override
	protected void getUniformLocations() {

	}
}
