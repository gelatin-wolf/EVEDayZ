package io.github.geletinwolf;

import io.github.geletinwolf.commands.CommandGetItem;
import io.github.geletinwolf.items.ItemBase;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new Listener(),this);
        this.getCommand("get").setExecutor(new CommandGetItem(this));
        ItemBase.registerItems();
    }

}
