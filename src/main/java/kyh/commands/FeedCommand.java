package kyh.commands;

import kyh.kyhplugin2;
import kyh.util.UT;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.lang.reflect.UndeclaredThrowableException;

public class FeedCommand implements CommandExecutor {

    private final kyhplugin2 plugin;

    public FeedCommand(kyhplugin2 plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        boolean isPlayer = sender instanceof Player;

        // checking sender is player
        Player p;
        if (isPlayer) {
            p = ((Player) sender).getPlayer();
            if (!p.hasPermission("kyh.feed")) {
                p.sendMessage(ChatColor.RED + "이 명령어를 사용할 권한(kyh.feed)이 없습니다.");
                return true;
            }
        } else {
            p = null;
        }

        // setting target
        Player target = null;
        if (args.length == 0) { // feed me
            if (isPlayer) {
                target = p;
            } else {
                System.out.println("콘솔에서는 대상을 꼭 입력해주세요. /feed [대상] [값(max:20)]");
                return true;
            }
        } else if (args.length <= 2 + 1) { // +1 is to consider <Enter>
            target = UT.getPlayerStartWith(args[0]);;
        } else {
            if (isPlayer) {
                p.sendMessage(ChatColor.RED + "명령어 형식이 잘못되었습니다. /feed [대상] [값(max:20)]");
            } else {
                System.out.println("명령어 형식이 잘못되었습니다. /feed [대상] [값(max:20)]");
            }
            return true;
        }

        // satiety level
        int level = (args.length != 2) ? 20 : Integer.parseInt(args[1]);

        // setting satiety
        if (target != null) {

            String targetName = UT.getNameWithDisplay(target);

            target.setFoodLevel(level);
            String msg = "배부름이 " + level + "(으)로 설정됐습니다.";
            target.sendMessage(ChatColor.GOLD + msg);

            if (isPlayer) {
                System.out.println(targetName + "의 " + msg);

                if (target != p) {
                    p.sendMessage(ChatColor.GOLD + targetName + msg);
                }
            } else {
                System.out.println(targetName + "의 " + msg);
            }

        } else {
            if (isPlayer) {
                p.sendMessage(ChatColor.RED + "대상이 존재하지 않습니다.");
            } else {
                System.out.println("대상이 존재하지 않습니다.");
            }
        }

        return true;

    }
}
