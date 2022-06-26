package kyh.completions;

import kyh.kyhplugin2;
import kyh.util.UT;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class HomeCompletion implements TabCompleter {

    private final kyhplugin2 plugin;

    public HomeCompletion(kyhplugin2 plugin) {
        this.plugin = plugin;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {

        List<String> compList = new ArrayList<>();

        Player p = (Player) sender;

        if (args.length == 1) {

            List<String> homeList = new ArrayList<>(plugin.getConfig().getConfigurationSection("home." + p.getUniqueId()).getKeys(false));

            compList = UT.getListStartsWith(homeList, args[0]);

            if (command.getName().equals("sethome")){
                compList.add("<새로운 집 이름>");
            }

        }

        return compList;
    }
}
