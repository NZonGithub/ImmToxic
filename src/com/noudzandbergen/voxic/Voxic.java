package com.noudzandbergen.voxic;

import com.noudzandbergen.toxicengine.Engine;

public class Voxic {

    public final Engine engine;
    
    private static final VoxicCoreMod core = new VoxicCoreMod();

    public Voxic() {
        engine = new Engine();
    }

    public void start() {
        engine.init();
        engine.debug();
        engine.pushState(new PlayingState(engine));
        engine.start();
    }

    public static void main(String[] args) {
        new Voxic().start();
    }
}
