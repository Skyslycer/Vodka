package de.skyslycer.vodka;

import de.skyslycer.vodka.events.ClickEvent;
import de.skyslycer.vodka.events.CloseEvent;
import de.skyslycer.vodka.inventory.VodkaInventory;
import de.skyslycer.vodka.inventory.meta.PlayerMeta;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * A basic handler method to handle inventory events and the inventories.
 */
public class VodkaHandler implements Listener {

    public Map<Player, PlayerMeta> playerMapping = new HashMap<>();
    public Set<VodkaInventory> inventories = new HashSet<>();

    private final Plugin plugin;

    /**
     * Create a new {@link VodkaHandler} with the plugin as the parameter.
     *
     * @param plugin The plugin that is using this
     */
    public VodkaHandler(Plugin plugin) {
        this.plugin = plugin;
    }

    /**
     * Adds an inventory to the inventory list.
     *
     * @param inventory The {@link VodkaInventory} to add
     */
    public void addInventory(VodkaInventory inventory) {
        inventories.add(inventory);
    }

    /**
     * Removes an inventory from the inventory list.
     *
     * @param inventory The {@link VodkaInventory} to remove
     */
    public void removeInventory(VodkaInventory inventory) {
        inventories.remove(inventory);
    }

    /**
     * Opens the first page of the inventory. (0)
     *
     * @param player    The {@link Player} to open to player to.
     * @param inventory The {@link VodkaInventory} to open
     * @return If the inventory got opened
     */
    public boolean openFirst(Player player, VodkaInventory inventory) {
        return open(player, inventory, 0);
    }

    /**
     * Open the next page with the currently stored {@link PlayerMeta}.
     *
     * @param player    The player to open the inventory to
     * @param inventory The inventory to open the next page from
     * @return If the inventory got opened
     */
    public boolean openNext(Player player, VodkaInventory inventory) {
        var page =
                playerMapping.containsKey(player) && playerMapping.get(player).inventory == inventory ?
                        playerMapping.get(player).page.number + 1 : 0;

        return open(player, inventory, page);
    }

    /**
     * Open an inventory with the given page to the given player.
     *
     * @param player     The {@link Player} to open the inventory to
     * @param inventory  The {@link VodkaInventory} to get the page from
     * @param pageNumber The page to get from the inventory
     * @return If the inventory got opened
     */
    public boolean open(Player player, VodkaInventory inventory, int pageNumber) {
        var page = inventory.open(player, pageNumber, plugin);

        if (page.isEmpty()) {
            return false;
        }

        playerMapping.put(player, new PlayerMeta(inventory, page.get()));
        inventories.add(inventory);

        return true;
    }

    /**
     * Opens the first page of the inventory. (0)
     *
     * @param player    The {@link Player} to open to player to.
     * @param inventory The {@link VodkaInventory} to open
     * @return If the inventory got opened
     * @see VodkaHandler#openFirst(Player, VodkaInventory) The method this is referring to
     */
    public boolean sip(Player player, VodkaInventory inventory) {
        return openFirst(player, inventory);
    }

    /**
     * The method that gets called when the {@link InventoryClickEvent} is fired.
     *
     * @param event The event instance
     */
    @EventHandler
    public void onClick(InventoryClickEvent event) {
        var player = (Player) event.getWhoClicked();

        var meta = checkIfExists(player);

        if (meta == null) {
            return;
        }

        if (meta.inventory.onClick == null) {
            return;
        }

        meta.inventory.onClick.accept(
                new ClickEvent(
                        event,
                        meta.inventory,
                        meta.page,
                        event.getInventory()
                )
        );
    }

    /**
     * The method that gets called when the {@link InventoryCloseEvent} is fired.
     *
     * @param event The event instance
     */
    @EventHandler
    public void onClose(InventoryCloseEvent event) {
        var player = (Player) event.getPlayer();

        var meta = checkIfExists(player);

        if (meta == null) {
            return;
        }

        if (!meta.inventory.closable) {
            Bukkit.getScheduler().runTaskLater(plugin, () -> meta.inventory.open(player, meta.page.number, plugin), 2);
            return;
        }

        playerMapping.remove(player);

        if (meta.inventory.onClose == null) {
            return;
        }

        meta.inventory.onClose.accept(
                new CloseEvent(
                        meta.inventory,
                        meta.page,
                        event,
                        event.getInventory()
                )
        );
    }

    private PlayerMeta checkIfExists(Player player) {
        if (!playerMapping.containsKey(player)) {
            return null;
        }

        return playerMapping.get(player);
    }

}
