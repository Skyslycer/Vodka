package de.skyslycer.vodka.item;

import net.kyori.adventure.text.Component;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Easily create item stacks, without messing your hands. <br>
 * <i>Note that if you do use this in one of your projects, leave this notice.</i>
 * <i>Please do credit me if you do use this in one of your projects.</i>
 * @author NonameSL
 */
public class ItemBuilder {

    private final ItemStack itemStack;

    /**
     * Create a new ItemBuilder from scratch.
     *
     * @param material The material to create the ItemBuilder with
     */
    public ItemBuilder(Material material){
        this(material, 1);
    }

    /**
     * Create a new ItemBuilder over an existing item.
     *
     * @param itemStack The item to create the ItemBuilder over
     */
    public ItemBuilder(ItemStack itemStack){
        this.itemStack = itemStack;
    }

    /**
     * Create a new ItemBuilder from scratch.
     *
     * @param material The material of the item
     * @param amount The amount of the item
     */
    public ItemBuilder(Material material, int amount){
        itemStack = new ItemStack(material, amount);
    }

    /**
     * Clone the ItemBuilder into a new one.
     *
     * @return The cloned instance
     */
    @Override
    public ItemBuilder clone(){
        return new ItemBuilder(itemStack);
    }

    /**
     * Set the durability of the item.
     *
     * @param durability The durability to set it to
     */
    public ItemBuilder setDurability(int durability){
        try {
            var damageMeta = (Damageable) itemStack.getItemMeta();
            damageMeta.setDamage(durability);
        } catch (ClassCastException ignored) {

        }

        return this;
    }

    /**
     * Remove durability from the item.
     *
     * @param durability The durability to remove
     */
    public ItemBuilder removeDurability(int durability){
        try {
            var damageMeta = (Damageable) itemStack.getItemMeta();
            damageMeta.setDamage(durability);
        } catch (ClassCastException ignored) { }

        return this;
    }

    /**
     * Sets the item to unbreakable.
     */
    public ItemBuilder setUnbreakable(){
        try {
            var damageMeta = (Damageable) itemStack.getItemMeta();
            damageMeta.setUnbreakable(true);
        } catch (ClassCastException ignored) { }

        return this;
    }

    /**
     * Set the display name of the item.
     *
     * @param name The name to change it to
     */
    public ItemBuilder setName(Component name){
        var itemMeta = itemStack.getItemMeta();
        itemMeta.displayName(name);
        itemStack.setItemMeta(itemMeta);
        return this;
    }

    /**
     * Add an unsafe enchantment.
     *
     * @param enchantment The enchantment to add
     * @param level The level to put the enchantment on
     */
    public ItemBuilder addUnsafeEnchantment(Enchantment enchantment, int level){
        itemStack.addUnsafeEnchantment(enchantment, level);
        return this;
    }

    /**
     * Add an enchantment to the item.
     *
     * @param enchantment The enchantment to add
     * @param level The level
     */
    public ItemBuilder addEnchant(Enchantment enchantment, int level){
        var im = itemStack.getItemMeta();
        im.addEnchant(enchantment, level, true);
        itemStack.setItemMeta(im);
        return this;
    }

    /**
     * Add multiple enchants at once.
     *
     * @param enchantments The enchants to add
     */
    public ItemBuilder addEnchantments(Map<Enchantment, Integer> enchantments){
        itemStack.addEnchantments(enchantments);
        return this;
    }

    /**
     * Remove a certain enchant from the item.
     *
     * @param enchantment The enchantment to remove
     */
    public ItemBuilder removeEnchantment(Enchantment enchantment){
        itemStack.removeEnchantment(enchantment);
        return this;
    }

    /**
     * Set the skull owner for the item. Works on skulls only.
     * @param owner The name of the skull's owner.
     */
    public ItemBuilder setSkullOwner(String owner){
        try {
            var im = (SkullMeta) itemStack.getItemMeta();
            im.setOwner(owner);
            itemStack.setItemMeta(im);
        } catch(ClassCastException ignored) { }

        return this;
    }

    /**
     * Sets the lore.
     *
     * @param lore The lore to set it to
     */
    public ItemBuilder setLore(Component... lore){
        var itemMeta = itemStack.getItemMeta();
        itemMeta.lore(Arrays.asList(lore));
        itemStack.setItemMeta(itemMeta);

        return this;
    }

    /**
     * Sets the lore.
     *
     * @param lore The lore to set it to
     */
    public ItemBuilder setLore(List<Component> lore) {
        var itemMeta = itemStack.getItemMeta();
        itemMeta.lore(lore);
        itemStack.setItemMeta(itemMeta);
        return this;
    }

    /**
     * Remove a lore line.
     *
     * @param line The line to remove
     */
    public ItemBuilder removeLoreLine(Component line){
        var itemMeta = itemStack.getItemMeta();

        if (!itemMeta.hasLore()) return this;

        var lore = new ArrayList<>(itemMeta.lore());
        if (!lore.contains(line)) return this;

        lore.remove(line);
        itemMeta.lore(lore);

        itemStack.setItemMeta(itemMeta);

        return this;
    }

    /**
     * Remove a lore line.
     *
     * @param index The index of the lore line to remove
     */
    public ItemBuilder removeLoreLine(int index){
        var itemMeta = itemStack.getItemMeta();

        if (!itemMeta.hasLore()) return this;

        var lore = new ArrayList<>(itemMeta.lore());
        if (index < 0 || index > lore.size()) return this;

        lore.remove(index);
        itemMeta.lore(lore);

        itemStack.setItemMeta(itemMeta);

        return this;
    }

    /**
     * Add a lore line.
     *
     * @param line The lore line to add
     */
    public ItemBuilder addLoreLine(Component line){
        var itemMeta = itemStack.getItemMeta();

        if (!itemMeta.hasLore()) return this;

        var lore = new ArrayList<>(itemMeta.lore());

        lore.add(line);
        itemMeta.lore(lore);

        itemStack.setItemMeta(itemMeta);

        return this;
    }

    /**
     * Add a lore line.
     *
     * @param line The lore line to add
     * @param position The index of where to put it
     */
    public ItemBuilder addLoreLine(Component line, int position){
        var itemMeta = itemStack.getItemMeta();

        if (!itemMeta.hasLore() || position < 0) return this;

        var lore = new ArrayList<>(itemMeta.lore());

        lore.set(position, line);
        itemMeta.lore(lore);

        itemStack.setItemMeta(itemMeta);

        return this;
    }

    /**
     * Sets the armor color of a leather armor piece. Works only on leather armor pieces.
     *
     * @param color The color to set it to
     */
    public ItemBuilder setLeatherArmorColor(Color color){
        try {
            var leatherMeta = (LeatherArmorMeta) itemStack.getItemMeta();
            leatherMeta.setColor(color);
            itemStack.setItemMeta(leatherMeta);
        } catch(ClassCastException ignored) { }

        return this;
    }

    /**
     * Retrieves the item stack from the {@link ItemBuilder}.
     *
     * @return The item stack created/modified by the {@link ItemBuilder} instance
     */
    public ItemStack build(){
        return itemStack;
    }

}