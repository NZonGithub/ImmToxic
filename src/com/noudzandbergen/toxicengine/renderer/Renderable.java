package com.noudzandbergen.toxicengine.renderer;

import com.noudzandbergen.toxicengine.ShaderProgram;
import org.lwjgl.opengl.GL46C;

import java.util.ArrayList;
import java.util.List;

//public abstract class Renderable {
//
//    {
//        GL46C.glMultiDrawElementsBaseVertex();
//    }
//
//}



public interface Renderable {

    ShaderProgram getProgram();
    Model getModel();

    boolean isVisible();
    void render();

}