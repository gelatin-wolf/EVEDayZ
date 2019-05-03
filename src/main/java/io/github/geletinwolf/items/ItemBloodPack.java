package io.github.geletinwolf.items;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Arrays;
import java.util.List;

public class ItemBloodPack extends ItemBase{
    public ItemBloodPack(){}

    @Override
    public Material getType() {
        return Material.IRON_NUGGET;
    }

    @Override
    public String getName() {
        return "혈액 팩";
    }

    @Override
    public List<String> getDescription() {
        return Arrays.asList(new String[]{
                "우 클릭을 하면 천천히 HP가 회복됩니다.", "플레이어가 혈액 팩을 수혈 중이라는 걸", "다른 플레이어도 볼 수 있습니다."
        });
    }

    @Override
    public Runnable getFunctionality(PlayerInteractEvent event) {
        return new Runnable() {
            @Override
            public void run() {
                Player eun = event.getPlayer();
                eun.sendMessage("혈액 팩 수혈을 시작합니다.");
            }
        };
    }
}