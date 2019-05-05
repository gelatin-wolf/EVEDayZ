package io.github.geletinwolf.items;

import org.bukkit.Material;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.List;

public abstract class ItemBase {

    public static HashMap<String, Class<? extends ItemBase>> items = new HashMap<String, Class<? extends ItemBase>>();

    public static void registerItems() {
        items.put("Bandage", ItemBandage.class);
        items.put("PainKiller", ItemPainkiller.class);
        items.put("BloodPack", ItemBloodPack.class);
        items.put("Disinfectant", ItemDisinfectant.class);
        items.put("Vaccine", ItemVaccine.class);
        items.put("Morphine", ItemMorphine.class);
        items.put("Adrenaline",ItemAdrenaline.class);
    }

    public abstract Material getType();
    public abstract short getDurability();
    public abstract String getName();
    public abstract List<String> getDescription();
    public abstract Runnable getFunctionality(PlayerInteractEvent event);

    public ItemStack getItemStack(){

        ItemStack is = new ItemStack(getType());
        is.setDurability(getDurability());

        ItemMeta im = is.getItemMeta();
        im.setDisplayName(getName());
        im.setLore(getDescription());
        is.setItemMeta(im);

        return is;
    }



}
