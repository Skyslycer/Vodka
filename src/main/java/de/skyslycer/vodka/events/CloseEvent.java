package de.skyslycer.vodka.events;

import de.skyslycer.vodka.inventory.VodkaInventory;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

public class CloseEvent {

    public InventoryCloseEvent bukkitEvent;
    public Player player;
    public VodkaInventory inventory;

    public CloseEvent(InventoryCloseEvent event, VodkaInventory inventory, Inventory bukkitInventory) {
        this.inventory = inventory;
        this.bukkitEvent = event;
        this.player = (Player) event.getPlayer();
    }

}
