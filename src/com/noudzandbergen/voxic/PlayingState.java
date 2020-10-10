package com.noudzandbergen.voxic;

import com.noudzandbergen.toxicengine.Engine;
import com.noudzandbergen.toxicengine.State;
import com.noudzandbergen.toxicengine.renderer.GLIndexedMesh;
import com.noudzandbergen.voxic.block.SimpleBlock;
import com.noudzandbergen.voxic.block.container.Mesher;
import com.noudzandbergen.voxic.shaders.ChunkShader;
import com.noudzandbergen.voxic.world.fixed.FixedWorld;
import org.joml.Matrix4f;

import static org.lwjgl.opengl.GL11C.*;
import static org.lwjgl.opengl.GL11C.GL_DEPTH_BUFFER_BIT;

public class PlayingState implements State {

    private Engine engine;
    private FixedWorld world;
    private final SimpleBlock block = new SimpleBlock();
    private GLIndexedMesh worldModel;
    private ChunkShader shader;

    public PlayingState(Engine engine) {
        this.engine = engine;
    }

    private FixedWorld createSimpleWorld() {
//        FixedWorld world = new FixedWorld(64, 64, 64);
        FixedWorld world = new FixedWorld(16, 16, 16);

        for (int y = 0; y < world.sizeZ; y++) {
            for (int x = 0; x < world.sizeX; x++) {
                for (int z = 0; z < world.sizeZ; z++) {
                    world.setBlock(x, y, z, block);
//                    if (((x ^ z) & 1) == 0) world.setBlock(x, y, z, block);
//                    if (((x ^ z) & 1) == 0) world.setBlock(x, y, z, block);
                }
            }
        }

        return world;
    }

    private GLIndexedMesh loadModelForWorld(FixedWorld world) {

        Mesher mesher = world.createMesh();

        return GLIndexedMesh.loadFrom(
                mesher.getPositions(),
                mesher.getTextureCoordinates(),
                mesher.getIndices()
        );
    }

    @Override
    public void init() {
        glClearColor(.1f, .1f, .2f, 0);
        glEnable(GL_DEPTH_TEST);

        world = createSimpleWorld();
        worldModel = loadModelForWorld(world);

        System.out.println(world.volume);

//        for (int i = 0; i < 10; i++) {
//            world.createMesh();
//        }
//
//        int count = 1000;
//
//        long t1 = System.nanoTime();
//
//        for (int i = 0; i < count; i++) {
//            world.createMesh();
//        }
//
//        long t2 = System.nanoTime();
//
//        float seconds = (t2 - t1)*1e-9f;

//        System.out.printf("Chunks meshing rate: %f CPS%nMilliseconds per chunk: %f ms", count/seconds, seconds/count*1000);

        shader = new ChunkShader();
        Matrix4f modelMatrix = new Matrix4f().identity().translate(-world.sizeX/2f, -world.sizeY/2f, -world.sizeZ/2f);
        Matrix4f projectionMatrix = new Matrix4f().perspective((float) (Math.toRadians(80)), 16f/9f, 1e-1f, 1e5f);

        shader.setModelMatrix(modelMatrix);
        shader.setProjectionMatrix(projectionMatrix);
//        shader.
    }

    float rot = 0;
    long t1 = System.nanoTime();
    @Override
    public void render() {

        double delta = (t1 - (t1 = System.nanoTime()))*-1e-9f;
        rot += delta*0.1;

        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        Matrix4f viewMatrix = new Matrix4f()
                .identity()
//                .translate(0, 0, -2048*2)
                .translate(0, 0, -world.sizeZ*2)
                .rotateX((float) (Math.sin(rot) * 1/3 * Math.PI))
                .rotateY((float) (rot * Math.PI));

        shader.setViewMatrix(viewMatrix);

        shader.use();
        worldModel.render();
    }

    @Override
    public void destroy() {
        shader.destroy();
        worldModel.delete();
    }
}
