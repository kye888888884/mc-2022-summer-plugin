package kyh.commands;

import kyh.kyhplugin2;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetHomeCommand implements CommandExecutor {

    private final kyhplugin2 plugin;

    public SetHomeCommand(kyhplugin2 plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {

            Player p = ((Player) sender).getPlayer();
            String homeName = (args.length == 0) ? "기본" : args[0];

            if (args.length <= 1) {

                plugin.getConfig().set("home." + p.getUniqueId() + "." + homeName, p.getLocation());
                plugin.saveConfig();

                p.sendMessage(ChatColor.GOLD + "현재 위치를 '" + homeName + "'집으로 설정했습니다.");
                System.out.println(p.getName() + "님이 " + p.getLocation() + "위치를 '" + homeName + "'집으로 설정했습니다.");

            } else {
                p.sendMessage(ChatColor.RED + "명령어 형식이 잘못됐습니다. /" + label + " [집 이름]");
            }

        } else {
            System.out.println("콘솔에서는 사용할 수 없습니다.");
            return false;
        }

        return true;
    }
}
