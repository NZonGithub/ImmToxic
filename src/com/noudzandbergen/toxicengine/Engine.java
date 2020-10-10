package com.noudzandbergen.toxicengine;

import org.lwjgl.Version;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL46C;

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
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 4);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 6);
        glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GLFW_TRUE);
        glfwWindowHint(GLFW_SAMPLES, 4);

    }

    public void pushState(State state) {
        state.init();
        states.push(state);
    }

    public void popState() {
        states.pop().destroy();
    }

    public void init() {
        if (++instances == 1) initGLFW();

        window = glfwCreateWindow(1280, 720, "Gamedev is toxic", NULL, NULL);

        if (window == NULL)
            throw new RuntimeException("Failed to create the GLFW window");

        glfwMakeContextCurrent(window);
        glfwSwapInterval(1);

        GL.createCapabilities();

    }

    public void debug() {
        GL46C.glDebugMessageCallback((source, type, id, severity, length, message, userParam) -> {
            System.out.println("GL Debug Message:");
            System.out.println(message);
        }, 69);
    }

    private void loop() {
        glfwShowWindow(window);

        while (!states.isEmpty() && !glfwWindowShouldClose(window)) {
            glfwPollEvents();

            states.peek().render();

            glfwSwapBuffers(window);
        }

        for (State state : states) state.destroy();
    }

    @SuppressWarnings("ConstantConditions")
    public void start() {
        loop();
        glfwDestroyWindow(window);
        if (--instances == 0) {
            glfwTerminate();
            glfwSetErrorCallback(null).free();
        }
    }
}
