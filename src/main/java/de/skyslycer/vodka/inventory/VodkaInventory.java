package de.skyslycer.vodka.inventory;

import de.skyslycer.vodka.events.ClickEvent;
import de.skyslycer.vodka.events.CloseEvent;
import de.skyslycer.vodka.inventory.meta.PlayerMeta;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.Optional;
import java.util.function.Consumer;

public class VodkaInventory {

    public boolean closable;

    public Consumer<ClickEvent> onClick;
    public Consumer<CloseEvent> onClose;

    public HashMap<Integer, Page> pages;

    /**
     * Create a new inventory.
     *
     * @param onClick The onClick consumer
     * @param onClose The onClose consumer
     * @param closable If the inventory is closable.
     * @param pages The pages of the inventory.
     */
    public VodkaInventory(Consumer<ClickEvent> onClick, Consumer<CloseEvent> onClose, boolean closable, HashMap<Integer, Page> pages) {
        this.onClick = onClick;
        this.onClose = onClose;
        this.closable = closable;
        this.pages = pages;
    }

    /**
     * Open a page from the inventory to a player.
     *
     * @param player The {@link Player} to open the page to
     * @param pageNumber The number of the page
     * @param plugin The plugin using this API
     * @return An empty optional if the page doesn't exist, if it does exist, the optional contains the page
     */
    public Optional<Page> open(Player player, int pageNumber, Plugin plugin) {
        if (pages.isEmpty()) {
            return Optional.empty();
        }

        var page = pages.get(pageNumber);

        if (page == null) {
            return Optional.empty();
        }

        player.getOpenInventory().close();

        Bukkit.getScheduler().runTaskLater(plugin, () -> player.openInventory(page.build()), 2);

        return Optional.of(page);
    }

    /**
     * Get a new builder.
     *
     * @return The new builder
     */
    public static VodkaInventoryBuilder getBuilder() {
        return new VodkaInventoryBuilder();
    }

    static class VodkaInventoryBuilder {

        private boolean closable = true;

        private Consumer<ClickEvent> onClick;
        private Consumer<CloseEvent> onClose;

        private final HashMap<Integer, Page> pages = new HashMap<>();

        /**
         * Prevent usage of the constructor.
         */
        private VodkaInventoryBuilder() {

        }

        /**
         * Set if the inventory is closable.
         *
         * @param closable The new boolean
         * @return The {@link VodkaInventoryBuilder} for further interactions.
         */
        public VodkaInventoryBuilder closable(boolean closable) {
            this.closable = closable;
            return this;
        }

        /**
         * Set the onClick {@link Consumer<ClickEvent>}.
         *
         * @param onClick The new {@link Consumer<ClickEvent>}
         * @return The {@link VodkaInventoryBuilder} for further interactions.
         */
        public VodkaInventoryBuilder onClick(Consumer<ClickEvent> onClick) {
            this.onClick = onClick;
            return this;
        }

        /**
         * Set the onClose {@link Consumer<CloseEvent>}.
         *
         * @param onClose The new {@link Consumer<CloseEvent>}
         * @return The {@link VodkaInventoryBuilder} for further interactions.
         */
        public VodkaInventoryBuilder onClose(Consumer<CloseEvent> onClose) {
            this.onClose = onClose;
            return this;
        }

        /**
         * Set a page.
         *
         * @param pageNumber The slot of the page to set
         * @param page The page to add
         * @return The {@link VodkaInventoryBuilder} for further interactions.
         */
        public VodkaInventoryBuilder setPage(int pageNumber, Page page) {
            this.pages.put(pageNumber, page);
            return this;
        }

        /**
         * Remove a page.
         *
         * @param slot The slot of the page to remove
         * @return The {@link VodkaInventoryBuilder} for further interactions.
         */
        public VodkaInventoryBuilder removePage(int slot) {
            this.pages.remove(slot);
            return this;
        }

        /**
         * Build the {@link VodkaInventory}.
         *
         * @return The built {@link VodkaInventory}
         */
        public VodkaInventory build() {
            return new VodkaInventory(
                    onClick,
                    onClose,
                    closable,
                    pages
            );
        }

    }

}
