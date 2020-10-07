package com.noudzandbergen.toxicengine;

import org.lwjgl.Version;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import java.util.Stack;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Engine {

    private long window;
    private final Stack<State> states = new Stack<>();

    private static int instances;

    private static void initGLFW() {

        System.out.println("Hello LWJGL " + Version.getVersion() + "!");

        GLFWErrorCallback.createPrint(System.err).set();

        if (!glfwInit()) throw new IllegalStateException("Unable to initialize GLFW");

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);

    }

    public void pushState(State state) {
        state.init();
        states.push(state);
    }

    public void popState() {
        states.pop().destroy();
    }

    private void init() {

        window = glfwCreateWindow(1280, 720, "Gamedev is toxic", NULL, NULL);

        if (window == NULL)
            throw new RuntimeException("Failed to create the GLFW window");

        glfwMakeContextCurrent(window);
        glfwSwapInterval(1);

    }

    private void loop() {
        glfwShowWindow(window);

        GL.createCapabilities();

        while (!states.isEmpty() && !glfwWindowShouldClose(window)) {
            glfwPollEvents();

            states.peek().render();

            glfwSwapBuffers(window);
        }

        for (State state : states) state.destroy();
    }

    @SuppressWarnings("ConstantConditions")
    public void start() {
        if (++instances == 1) initGLFW();
        init();
        loop();
        glfwDestroyWindow(window);
        if (--instances == 0) {
            glfwTerminate();
            glfwSetErrorCallback(null).free();
        }
    }
}
