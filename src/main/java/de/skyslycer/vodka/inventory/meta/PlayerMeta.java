package de.skyslycer.vodka.inventory.meta;

import de.skyslycer.vodka.inventory.Page;
import de.skyslycer.vodka.inventory.VodkaInventory;

public class PlayerMeta {

    public VodkaInventory inventory;
    public Page page;

    /**
     * Creates a new {@link PlayerMeta} to store player information.
     *
     * @param inventory The current {@link VodkaInventory} the player has opened
     * @param page The current page the player is at
     */
    public PlayerMeta(VodkaInventory inventory, Page page) {
        this.inventory = inventory;
        this.page = page;
    }

}
