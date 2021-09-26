package de.skyslycer.vodka.inventory;

public enum InventorySize {

    ONE_ROW(9),
    TWO_ROWS(18),
    THREE_ROWS(27),
    FOUR_ROWS(36),
    FIVE_ROWS(45),
    SIX_ROWS(54);

    public final int slots;

    InventorySize(int slots) {
        this.slots = slots;
    }

}
