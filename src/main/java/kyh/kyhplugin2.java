package kyh;

import kyh.completions.FeedCompletion;
import kyh.completions.HomeCompletion;
import kyh.completions.MoneyCompletion;
import kyh.completions.NoneCompletion;
import kyh.commands.*;
import kyh.listeners.SpawnListeners;
import kyh.listeners.WarpMenuListeners;
import org.bukkit.plugin.java.JavaPlugin;

public final class kyhplugin2 extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("kyh 플러그인 정상 작동");

        // config.yml
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        getCommand("setspawn").setExecutor(new SetSpawnCommand(this));
        getCommand("spawn").setExecutor(new SpawnCommand(this));
        getCommand("feed").setExecutor(new FeedCommand(this));
        getCommand("name").setExecutor(new NameCommand(this));
        getCommand("sethome").setExecutor(new SetHomeCommand(this));
        getCommand("home").setExecutor(new HomeCommand(this));
        getCommand("warp").setExecutor(new WarpMenuCommand(this));
        getCommand("money").setExecutor(new MoneyCommand(this));
        getCommand("npc").setExecutor(new MoneyCommand(this));

        getCommand("setspawn").setTabCompleter(new NoneCompletion(this));
        getCommand("spawn").setTabCompleter(new NoneCompletion(this));
        getCommand("feed").setTabCompleter(new FeedCompletion(this));
        getCommand("sethome").setTabCompleter(new HomeCompletion(this));
        getCommand("home").setTabCompleter(new HomeCompletion(this));
        getCommand("warp").setTabCompleter(new NoneCompletion(this));
        getCommand("money").setTabCompleter(new MoneyCompletion(this));

        getServer().getPluginManager().registerEvents(new SpawnListeners(this), this);
        getServer().getPluginManager().registerEvents(new WarpMenuListeners(this), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
