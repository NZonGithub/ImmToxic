package com.noudzandbergen.voxic;

import com.noudzandbergen.toxicengine.Engine;

public class Voxic {

    public final Engine engine;

//    public static final Block AIR;

    public Voxic() {
        engine = new Engine();
    }

    public void start() {
        engine.start();
        engine.pushState(new PlayingState(engine));
    }

    public static void main(String[] args) {
        new Voxic().start();
    }
}
