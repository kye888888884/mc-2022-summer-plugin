package kyh.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class UT {

    public static String getNameWithDisplay(Player p) {
        if (p != null) {
            if (p.getName() != p.getDisplayName()) {
                return p.getDisplayName() + "(" + p.getName() + ")";
            } else {
                return p.getName();
            }
        } else{
            return "(ERROR: 존재하지 않는 플레이어)";
        }
    }

    public static List<String> getListStartsWith(List<String> list, String start) {

        List<String> returnList = new ArrayList<>();

        for (String s: list) {
            if (s.toLowerCase().startsWith(start)) {
                returnList.add(s);
            }
        }

        return returnList;

    }

    public static void sendMessageOnCase(ChatColor color, String msg, Boolean isPlayer, Player p) {
        if (isPlayer) {
            p.sendMessage(color + msg);
        } else {
            System.out.println(msg);
        }
    }

    public static Player getPlayerStartWith(String start) {
         for (Player p: Bukkit.getServer().getOnlinePlayers()) {
             if (p.getName().startsWith(start)) {
                 return p;
             }
         }

         return null;
    }

}
