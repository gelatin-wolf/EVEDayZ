package io.github.geletinwolf.items;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Arrays;
import java.util.List;

public class ItemAdrenaline extends ItemBase{
    public ItemAdrenaline(){}

    @Override
    public Material getType() {
        return Material.STICK;
    }

    @Override
    public String getName() {
        return "아드레날린";
    }

    @Override
    public List<String> getDescription() {
        return Arrays.asList(new String[]{
                "우 클릭 시 최대 체력 HP50이 증가합니다.", "아드레날린을 연속으로 사용할 시 죽을 수도 있습니다."
        });
    }

    @Override
    public Runnable getFunctionality(PlayerInteractEvent event) {
        return new Runnable() {
            @Override
            public void run() {
                Player eun = event.getPlayer();
                eun.sendMessage("아드레날린을 사용하셨습니다.");
            }
        };
    }
}