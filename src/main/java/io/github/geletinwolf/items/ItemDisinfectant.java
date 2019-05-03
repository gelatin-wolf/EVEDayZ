package io.github.geletinwolf.items;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Arrays;
import java.util.List;

public class ItemDisinfectant extends ItemBase{
    public ItemDisinfectant(){}

    @Override
    public Material getType() {
        return Material.SLIME_BALL;
    }

    @Override
    public String getName() {
        return "소독약";
    }

    @Override
    public List<String> getDescription() {
        return Arrays.asList(new String[]{
                "우 클릭 시 1차 감염 진행을 멈춥니다."
        });
    }

    @Override
    public Runnable getFunctionality(PlayerInteractEvent event) {
        return new Runnable() {
            @Override
            public void run() {
                Player eun = event.getPlayer();
                eun.sendMessage("감염을 치료했습니다.");
            }
        };
    }
}