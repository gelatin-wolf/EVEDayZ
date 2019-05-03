package io.github.geletinwolf;

import io.github.geletinwolf.items.ItemBandage;
import io.github.geletinwolf.items.ItemBase;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Listener implements org.bukkit.event.Listener {

    @EventHandler
    public void onRightClick(PlayerInteractEvent event) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        if (event.getAction() == Action.RIGHT_CLICK_AIR) {
            for (String s : ItemBase.items.keySet()){
                Object instance = ItemBase.items.get(s).getDeclaredConstructor().newInstance();
                if (event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(ItemBase.items.get(s).getMethod("getName").invoke(instance).toString())){
                    ItemStack is = event.getPlayer().getItemInHand();
                    if (is.getAmount() == 1) {
                        event.getPlayer().setItemInHand(null);
                    } else {
                        is.setAmount(is.getAmount() -1);
                        event.getPlayer().setItemInHand(is);
                    }
                    for( Method m : ItemBase.items.get(s).getMethods() ) {
                        if (m.getName().equalsIgnoreCase("getFunctionality")) {
                            ((Runnable) m.invoke(instance, event)).run();
                        }
                    }
                }
            }
        }
    }
}
