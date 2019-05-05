package io.github.geletinwolf.items;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Arrays;
import java.util.List;

public class ItemPainkiller extends ItemBase{
    public ItemPainkiller(){}

    @Override
    public Material getType() {
        return Material.SUGAR;
    }

    @Override
    public short getDurability() {
        return 0;
    }

    @Override
    public String getName() {
        return "진통제";
    }

    @Override
    public List<String> getDescription() {
        return Arrays.asList("우클릭을 하면 HP20만큼가 일시적으로 회복이됩니다.", "시간이 흐르면 HP15만큼 떨어집니다.");
    }

    @Override
    public Runnable getFunctionality(PlayerInteractEvent event) {
        return () -> {
            Player eun = event.getPlayer();
            eun.sendMessage("HP20이 회복됬습니다.");
        };
    }
}
