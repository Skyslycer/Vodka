package de.skyslycer.vodka.item.items;

import de.skyslycer.vodka.item.ItemType;

public class EmptyItem implements VodkaItem {

    @Override
    public ItemType type() {
        return ItemType.EMPTY;
    }

}
