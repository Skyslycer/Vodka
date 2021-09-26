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
    public VodkaInventory inventory;

    public VodkaItem clickedItem;

    public ClickEvent(InventoryClickEvent event, VodkaInventory inventory, Page page, Inventory bukkitInventory) {
        this.inventory = inventory;
        this.bukkitEvent = event;
        this.player = (Player) event.getWhoClicked();

        page.getItem(bukkitEvent.getSlot()).ifPresent(item -> {
            this.clickedItem = item;

            if (item instanceof CustomItem customItem) {
                if (!customItem.movable) {
                    this.bukkitEvent.setCancelled(true);
                }
            }
        });
    }

}
