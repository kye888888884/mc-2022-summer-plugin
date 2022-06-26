package kyh.commands;

import kyh.kyhplugin2;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor {

    private final kyhplugin2 plugin;

    public SpawnCommand(kyhplugin2 plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            Location loc = plugin.getConfig().getLocation("spawn");

            if (loc != null) {
                player.teleport(loc);
                player.sendMessage(ChatColor.GOLD + "스폰 지점으로 이동했습니다.");
            } else {
                player.sendMessage(ChatColor.RED + "스폰 지점이 존재하지 않습니다. /setspawn 을 사용하여 스폰 지점을 설정해주세요.");
            }

        } else {
            System.out.println("콘솔에서는 사용할 수 없는 명령어입니다.");
        }


        return true;
    }
}
