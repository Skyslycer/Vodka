package de.skyslycer.vodka.inventory;

import de.skyslycer.vodka.item.items.CustomItem;
import de.skyslycer.vodka.item.items.EmptyItem;
import de.skyslycer.vodka.item.items.VodkaItem;
import de.skyslycer.vodka.item.ItemCollection;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

import java.util.Optional;

public class Page {

    public final int number;
    public final ItemCollection items;
    public Component title;
    public final InventorySize size;

    /**
     * Create a new page.
     *
     * @param number The page number of the page
     * @param items The items the page contains
     * @param title The title of the page
     * @param size The size of the page
     */
    public Page(int number, ItemCollection items, Component title, InventorySize size) {
        this.number = number;
        this.items = items;
        this.title = title;
        this.size = size;
    }

    /**
     * Check if an item exists.
     *
     * @param slot The slot the item should be at
     * @return If the item exists
     */
    public boolean itemExists(int slot) {
        return items.get(slot) != null;
    }

    /**
     * Get an item.
     *
     * @param slot The slot the item should be at
     * @return The optional, empty if the item doesn't exist
     */
    public Optional<VodkaItem> getItem(int slot) {
        return Optional.ofNullable(items.get(slot));
    }

    /**
     * Build the current page.
     *
     * @return The built inventory
     */
    public Inventory build() {
        var inventory = Bukkit.createInventory(null, size.slots, title);

        items.forEach((slot, item) -> {
            if (item instanceof CustomItem customItem) {
                inventory.setItem(slot, customItem.item);
            }
        });

        return inventory;
    }

    /**
     * Get a new builder.
     *
     * @return The new builder
     */
    public static PageBuilder newBuilder() {
        return new PageBuilder();
    }

    public static class PageBuilder {

        private Component title = Component.text("Drinking Vodka with the bois");
        private int pageNumber = 0;

        private InventorySize size = InventorySize.SIX_ROWS;

        private final ItemCollection items = new ItemCollection();

        /**
         * Set the title.
         *
         * @param title The new title
         * @return The {@link PageBuilder} for further interactions.
         */
        public PageBuilder title(Component title) {
            this.title = title;
            return this;
        }

        /**
         * Set the size.
         *
         * @param size The new size
         * @return The {@link PageBuilder} for further interactions.
         */
        public PageBuilder size(InventorySize size) {
            this.size = size;
            return this;
        }

        /**
         * Set an item to a slot.
         *
         * @param slot The slot of the item
         * @param item The item to set
         * @return The {@link PageBuilder} for further interactions.
         */
        public PageBuilder setItem(int slot, VodkaItem item) {
            items.put(slot, item);
            return this;
        }

        /**
         * Remove an item.
         *
         * @param slot The slot of the item to remove
         * @return The {@link PageBuilder} for further interactions.
         */
        public PageBuilder removeItem(int slot) {
            items.remove(slot);
            return this;
        }

        /**
         * Set the page
         *
         * @param page The page number
         * @return The {@link PageBuilder} for further interactions
         */
        public PageBuilder page(int page) {
            this.pageNumber = page;
            return this;
        }

        /**
         * Build the {@link Page}.
         *
         * @return The built {@link Page}
         */
        public Page build() {
            for (var i = 0; i < size.bukkitSlot; i++) {
                items.putIfAbsent(i, new EmptyItem());
            }

            return new Page(
                    pageNumber,
                    items,
                    title,
                    size
            );
        }

    }

}
