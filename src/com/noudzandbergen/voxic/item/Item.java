package com.noudzandbergen.voxic.item;

import java.util.ArrayList;

public class Item {

    private static final ArrayList<Item> items = new ArrayList<>();
    public final int ID;

    protected Item() {
        ID = items.size();
        items.add(this);
    }

    public static Item getItem(int id) {
        return items.get(id);
    }

}
