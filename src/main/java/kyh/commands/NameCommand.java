package kyh.commands;

import kyh.kyhplugin2;
import kyh.util.UT;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class NameCommand implements CommandExecutor {

    private final kyhplugin2 plugin;

    public NameCommand(kyhplugin2 plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        boolean isPlayer = sender instanceof Player;

        if (isPlayer) {
            Player p = ((Player) sender).getPlayer();
            String newName = (args.length == 0) ? p.getName() : args[0];

            if (newName.length() > 20) {
                p.sendMessage(ChatColor.RED + "이름의 최대 길이는 20입니다.");
                System.out.println(p.getName() + "님이 이름을 20자 이상으로 설정하려고 시도했습니다.");
                return false;
            }

            if (args.length >= 2) {
                p.sendMessage(ChatColor.RED + "플레이어는 자신의 이름만 변경할 수 있습니다.");
                System.out.println(p.getName() + "님이 " + args[1] + "의 이름을 " + newName + "(으)로 변경하려고 시도했습니다.");
                return false;
            }

            String isInit = (p.getName().equals(newName)) ? "(기본값) " : "";
            boolean isNewName = isInit.equals("");
            String listName = (isNewName) ? (newName + "(" + p.getName() + ")") : p.getName();

            p.setDisplayName(newName);
            p.setPlayerListName(listName);

            p.sendMessage(ChatColor.GOLD + "표시되는 이름이 '" + isInit + newName + "'(으)로 변경됐습니다.");
            System.out.println(p.getName() + "의 이름이 " + isInit + newName + "로 변경됐습니다.");

            plugin.getConfig().set("customName." + p.getUniqueId(), newName);
            plugin.saveConfig();

        } else {
            if (args.length == 2) {
                if (args[0].length() > 20) {
                    System.out.println("이름의 최대 길이는 20입니다.");
                    return false;
                }
                Player target = UT.getPlayerStartWith(args[1]);;
                if (target != null) {
                    target.setDisplayName(args[0]);
                    System.out.println(target.getName() + "의 이름이 " + args[0] + "로 변경됐습니다.");

                    plugin.getConfig().set("customName." + target.getUniqueId(), args[0]);
                    plugin.saveConfig();
                } else {
                    System.out.println("대상이 존재하지 않습니다.");
                }
            } else {
                if (args.length == 1) {
                    System.out.println("콘솔에서는 대상을 꼭 입력해야 합니다.");
                } else {
                    System.out.println("명령어 형식이 잘못됐습니다. /name [표시될 이름] [대상]");
                }
            }
        }

        return true;
    }
}
