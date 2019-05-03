package io.github.geletinwolf.items;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Arrays;
import java.util.List;

public class ItemMorphine extends ItemBase{
    public ItemMorphine(){}

    @Override
    public Material getType() {
        return Material.BLAZE_ROD;
    }

    @Override
    public String getName() {
        return "모르핀";
    }

    @Override
    public List<String> getDescription() {
        return Arrays.asList(new String[]{
                "우 클릭 시 다리 혹은 팔의 부상을 무시하고", "잠깐 동안 정상 활동을 할 수 있게 해줍니다.", "시간이 지나면 모르핀 효과가 떨어집니다."
        });
    }

    @Override
    public Runnable getFunctionality(PlayerInteractEvent event) {
        return new Runnable() {
            @Override
            public void run() {
                Player eun = event.getPlayer();
                eun.sendMessage("모르핀을 사용하셨습니다.");
            }
        };
    }
}