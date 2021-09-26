package de.skyslycer.vodka.item.items;

import de.skyslycer.vodka.events.ClickEvent;
import de.skyslycer.vodka.item.ItemType;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;

public class CustomItem implements VodkaItem {

    public int slot;
    public boolean movable = false;

    public ItemStack item;

    public Consumer<ClickEvent> onClick;

    public CustomItem(int slot, ItemStack item, Consumer<ClickEvent> onClick) {
        this.slot = slot;
        this.item = item;
        this.onClick = onClick;
    }

    @Override
    public ItemType type() {
        return ItemType.FUNCTIONAL;
    }

}
