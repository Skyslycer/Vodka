package de.skyslycer.vodka;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class Vodka {

    private final Plugin plugin;
    private VodkaHandler handler;

    private Vodka(Plugin plugin) {
        this.plugin = plugin;
    }

    /**
     * Init the {@link VodkaHandler}. <b>ONLY call this in your {@code JavaPlugin#onEnable()} or later!<b/>
     *
     * @return The newly created {@link VodkaHandler}
     */
    public VodkaHandler init() {
        this.handler = new VodkaHandler(plugin);

        Bukkit.getPluginManager().registerEvents(handler, plugin);

        return handler;
    }

    /**
     * Get the handler.
     *
     * @return The cached {@link VodkaHandler}
     */
    public VodkaHandler getHandler() {
        return this.handler;
    }

    /**
     * Create a new {@link Vodka} instance for you to use.
     *
     * @param plugin The plugin that is accessing Vodka.
     * @return The new {@link Vodka} instance
     */
    public static Vodka newBottle(Plugin plugin) {
        return new Vodka(plugin);
    }

}
