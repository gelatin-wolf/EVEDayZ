package io.github.geletinwolf.items;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Collections;
import java.util.List;

public class ItemVaccine extends ItemBase{
    public ItemVaccine(){}

    @Override
    public Material getType() {
        return Material.BONE;
    }

    @Override
    public short getDurability() {
        return 0;
    }

    @Override
    public String getName() {
        return "백신";
    }

    @Override
    public List<String> getDescription() {
        return Collections.singletonList("우 클릭 시 2차 감염진행을 멈춥니다.");
    }

    @Override
    public Runnable getFunctionality(PlayerInteractEvent event) {
        return () -> {
            Player eun = event.getPlayer();
            eun.sendMessage("감염이 치료됐습니다.");
        };
    }
}