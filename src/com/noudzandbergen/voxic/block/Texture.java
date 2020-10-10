package com.noudzandbergen.voxic.block;

import java.util.ArrayList;

public class Texture {

    private static final ArrayList<Texture> textures = new ArrayList<>();
    public final int ID;

    protected Texture() {
        ID = textures.size();
        textures.add(this);
    }

}
