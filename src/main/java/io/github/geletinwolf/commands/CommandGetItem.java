package io.github.geletinwolf.commands;

import io.github.geletinwolf.Main;
import io.github.geletinwolf.items.ItemBase;
import io.github.geletinwolf.player.EVEPlayer;
import io.github.geletinwolf.player.effects.EffectType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.InvocationTargetException;

public class CommandGetItem implements CommandExecutor {

    private Main plugin;

    public CommandGetItem(Main main){ plugin = main;}

    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player){
            Player p = (Player) commandSender;
            if(strings.length == 0) return false;
            for(String s2 : ItemBase.items.keySet()) {
                if(s2.equalsIgnoreCase(strings[0])) {
                    try {
                        Object instance = ItemBase.items.get(s2).getDeclaredConstructor().newInstance();
                        ItemStack ib = (ItemStack) ItemBase.items.get(s2).getMethod("getItemStack").invoke(instance);
                        p.getInventory().addItem(ib);
                    } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                    return true;
                }
            }
            if(strings[0].equalsIgnoreCase("bleed")) {
                EVEPlayer ep = new EVEPlayer(p);
                ep.getPlayer().getPlayer().sendMessage("BLEED");
                ep.giveBuff(EffectType.BLEEDING, 10000);
            }
        }
        return false;
    }
}
