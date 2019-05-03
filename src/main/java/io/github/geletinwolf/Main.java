package io.github.geletinwolf;

import io.github.geletinwolf.commands.CommandGetItem;
import io.github.geletinwolf.events.EventListener;
import io.github.geletinwolf.items.ItemBase;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

public class Main extends JavaPlugin {

    private static BukkitScheduler scheduler;
    private static Main plugin;

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new EventListener(),this);
        this.getCommand("get").setExecutor(new CommandGetItem(this));
        ItemBase.registerItems();
        scheduler = this.getServer().getScheduler();
        plugin = this;
    }

    public static BukkitScheduler getScheduler() {
        return scheduler;
    }

    public static Main getInstance() {
        return plugin;
    }

}
