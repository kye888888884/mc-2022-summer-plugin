package kyh.completions;

import kyh.kyhplugin2;
import kyh.util.UT;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class FeedCompletion implements TabCompleter {

    private final kyhplugin2 plugin;

    public FeedCompletion(kyhplugin2 plugin) {
        this.plugin = plugin;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {

        List<String> compList = new ArrayList<>();
        List<String> playerList = new ArrayList<>();

        if (args.length == 1) {

            for (Player p: Bukkit.getServer().getOnlinePlayers()) {
                playerList.add(p.getName());
            }

            compList = UT.getListStartsWith(playerList, args[0]);

        } else if (args.length == 2) {
            compList.add("0~20");
        }

        return compList;
    }
}
