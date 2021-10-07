package de.skyslycer.vodka.events;

import de.skyslycer.vodka.inventory.Page;
import de.skyslycer.vodka.inventory.VodkaInventory;
import de.skyslycer.vodka.item.items.CustomItem;
import de.skyslycer.vodka.item.items.VodkaItem;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class ClickEvent {

    public InventoryClickEvent bukkitEvent;
    public Player player;
    public Inventory bukkitInventory;
    public VodkaInventory inventory;
    public Page page;
    public VodkaItem clickedItem;

    public ClickEvent(InventoryClickEvent event, Player player, Inventory bukkitInventory, VodkaInventory inventory, Page page,  VodkaItem clickedItem) {
        this.bukkitEvent = event;
        this.player = player;
        this.bukkitInventory = bukkitInventory;
        this.inventory = inventory;
        this.page = page;
        this.clickedItem = clickedItem;
    }

}
