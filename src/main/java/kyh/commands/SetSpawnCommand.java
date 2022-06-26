package kyh.commands;

import kyh.kyhplugin2;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetSpawnCommand implements CommandExecutor {

    private final kyhplugin2 plugin;

    public SetSpawnCommand(kyhplugin2 plugin) {
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player p = (Player) sender;

            if (p.hasPermission("kyh.setspawn")) {
                Location loc = p.getLocation();

                // Save the location object directly
                plugin.getConfig().set("spawn", loc);

                plugin.saveConfig();

                p.sendMessage(ChatColor.GOLD + "스폰 지점이 설정되었습니다!");
            } else {
                p.sendMessage(ChatColor.RED + "이 명령어를 사용할 권한(kyh.setspawn)이 없습니다.");
            }

        } else {
            System.out.println("콘솔에서는 사용할 수 없는 명령어입니다.");
        }

        return true;
    }
}
