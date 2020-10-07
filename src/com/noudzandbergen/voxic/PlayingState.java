package com.noudzandbergen.voxic;

import com.noudzandbergen.toxicengine.Engine;
import com.noudzandbergen.toxicengine.State;

import static org.lwjgl.opengl.GL11C.*;
import static org.lwjgl.opengl.GL11C.GL_DEPTH_BUFFER_BIT;

public class PlayingState implements State {

    private Engine engine;

    public PlayingState(Engine engine) {
        this.engine = engine;
    }

    @Override
    public void init() {
        glClearColor(0, 0, 0, 0);
    }

    @Override
    public void render() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }

    @Override
    public void destroy() {

    }
}
