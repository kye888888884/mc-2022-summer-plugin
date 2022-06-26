package kyh.listeners;

import kyh.kyhplugin2;
import kyh.util.UT;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class SpawnListeners implements Listener {

    private final kyhplugin2 plugin;

    public SpawnListeners(kyhplugin2 plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {

        Player p = e.getPlayer();

        // go to spawn point
        if (!e.getPlayer().hasPlayedBefore()) {
            Location loc = plugin.getConfig().getLocation("spawn");

            if (loc != null) {
                p.teleport(loc);
            }

        }

        // set custom name
        String customName = plugin.getConfig().getString("customName." + p.getUniqueId());
        if (customName != null) {
            if (!p.getName().equals(customName)) {
                p.setDisplayName(customName);
                p.setPlayerListName(UT.getNameWithDisplay(p));
                System.out.println(p.getName() + "님의 커스텀 이름이 적용되었습니다.");
            }
        }

    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent e) {
        // when a player dies, respawn them at the spawn point
        Location loc = plugin.getConfig().getLocation("spawn");
        if (loc != null) {
            e.setRespawnLocation(loc);
        }
    }

}
