package de.skyslycer.vodka.inventory;

public enum InventorySize {

    ONE_ROW(9),
    TWO_ROWS(18),
    THREE_ROWS(27),
    FOUR_ROWS(36),
    FIVE_ROWS(47),
    SIX_ROWS(54);

    public final int slot;
    public final int bukkitSlot;

    InventorySize(int slot) {
        this.slot = slot;
        this.bukkitSlot = slot - 1;
    }

}
