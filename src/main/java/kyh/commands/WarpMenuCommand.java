package kyh.commands;

import kyh.kyhplugin2;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class WarpMenuCommand implements CommandExecutor {
    private final kyhplugin2 plugin;

    public WarpMenuCommand(kyhplugin2 plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {

            Player p = ((Player) sender).getPlayer();

            Inventory inv = Bukkit.createInventory(p, 18, ChatColor.BLUE + "이동 메뉴");

            ItemStack item = new ItemStack(Material.APPLE, 1);
            ItemMeta itemMeta = item.getItemMeta();
            itemMeta.setDisplayName(ChatColor.GREEN + "스폰 지역");

            ArrayList<String> lore = new ArrayList<>();
            lore.add(ChatColor.GRAY + "클릭하면 스폰 지역으로");
            lore.add(ChatColor.GRAY + "이동할 수 있습니다.");
            itemMeta.setLore(lore);
            itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

            itemMeta.addEnchant(Enchantment.LUCK, 1, true);
            item.setItemMeta(itemMeta);

            inv.setItem(4, item);

            p.openInventory(inv);

        }

        return false;
    }
}
