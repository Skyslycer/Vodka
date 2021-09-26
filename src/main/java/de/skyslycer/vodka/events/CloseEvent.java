package de.skyslycer.vodka.events;

import de.skyslycer.vodka.inventory.Page;
import de.skyslycer.vodka.inventory.VodkaInventory;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

public class CloseEvent {

    public VodkaInventory inventory;
    public Page page;

    public InventoryCloseEvent bukkitEvent;
    public Inventory bukkitInventory;
    public Player player;

    public CloseEvent(VodkaInventory inventory, Page page, InventoryCloseEvent event, Inventory bukkitInventory) {
        this.inventory = inventory;
        this.page = page;
        this.bukkitEvent = event;
        this.bukkitInventory = bukkitInventory;
        this.player = (Player) event.getPlayer();
    }

}
