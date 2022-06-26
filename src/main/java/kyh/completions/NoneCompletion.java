package kyh.completions;

import kyh.kyhplugin2;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class NoneCompletion implements TabCompleter {

    private final kyhplugin2 plugin;

    public NoneCompletion(kyhplugin2 plugin) {
        this.plugin = plugin;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> emptyList = new ArrayList<>();

        return emptyList;
    }
}
