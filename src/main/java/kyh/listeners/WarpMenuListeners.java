package kyh.listeners;

import kyh.kyhplugin2;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class WarpMenuListeners implements Listener {

    private final kyhplugin2 plugin;

    public WarpMenuListeners(kyhplugin2 plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onMenuClick(InventoryClickEvent e) {

        if (e.getView().getTitle().equalsIgnoreCase(ChatColor.BLUE + "이동 메뉴")) {

            if (e.getCurrentItem() == null) {
                return;
            }

            // make it so that players cannot move items
            e.setCancelled(true);

            if (e.getSlot() == 4) {
                Player p = (Player) e.getWhoClicked();

                Location spawnLoc = plugin.getConfig().getLocation("spawn");
                if (spawnLoc != null) {
                    p.teleport(spawnLoc);
                    p.sendMessage(ChatColor.GOLD + "스폰 지점으로 이동했습니다.");
                } else {
                    p.sendMessage(ChatColor.RED + "스폰 지점이 존재하지 않습니다. /setspawn 을 사용하여 스폰 지점을 설정해주세요.");
                }

            }

        }
    }

}
