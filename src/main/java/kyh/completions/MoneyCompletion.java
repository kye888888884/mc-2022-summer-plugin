package kyh.completions;

import kyh.kyhplugin2;
import kyh.util.UT;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MoneyCompletion implements TabCompleter {

    private final kyhplugin2 plugin;

    public MoneyCompletion(kyhplugin2 plugin) {
        this.plugin = plugin;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {

        String[] argsList = {"see", "set", "add", "list", "send"};

        List<String> compList = new ArrayList<>();
        List<String> playerList = new ArrayList<>();
        for (Player p: Bukkit.getServer().getOnlinePlayers()) {
            playerList.add(p.getName());
        }

        Player p = (Player) sender;

        int len = args.length;

        if (len == 1) {
            compList = UT.getListStartsWith(Arrays.asList(argsList), args[0]);
        }

        return compList;
    }
}
