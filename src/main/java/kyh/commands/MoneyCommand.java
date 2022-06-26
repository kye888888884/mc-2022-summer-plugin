package kyh.commands;

import kyh.kyhplugin2;
import kyh.util.UT;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class MoneyCommand implements CommandExecutor {

    private final kyhplugin2 plugin;

    public MoneyCommand(kyhplugin2 plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Boolean isPlayer = sender instanceof Player;
        Player p = null;
        Player t = null;
        if (isPlayer) {
            p = (Player) sender;
        }
        String msg;
        int money;
        int preMoney;

        int len = args.length;
        if (len == 0) {
            UT.sendMessageOnCase(ChatColor.RED, "명령어 형식이 잘못됐습니다.", isPlayer, p);
            return false;
        }
        else {
            switch(args[0]) {
                case "see":
                    if (len == 1) {
                        if (isPlayer) {
                            money = getMoney(p);
                            msg = UT.getNameWithDisplay(p) + "님이 현재 가지고 있는 돈은 " + money + "원입니다.";
                            p.sendMessage(ChatColor.GOLD + msg);
                        } else {
                            System.out.println("대상을 입력해주세요. /money see [대상]");
                        }
                    } else if (len <= 2 + 1) {
                        t = Bukkit.getPlayer(args[1]);
                        if (t == null) {
                            UT.sendMessageOnCase(ChatColor.RED, "대상이 존재하지 않습니다.", isPlayer, p);
                            return true;
                        } else {
                            if (!(t.equals(p) || p.hasPermission("kyh.money.see"))) {
                                UT.sendMessageOnCase(ChatColor.RED, "다른 플레이어의 돈을 볼 권한(kyh.money.see)이 없습니다.", isPlayer, p);
                                return true;
                            }
                            money = getMoney(t);
                            msg = UT.getNameWithDisplay(p) + "님이 현재 가지고 있는 돈은 " + money + "원입니다.";
                            UT.sendMessageOnCase(ChatColor.GOLD, msg, isPlayer, p);
                        }
                    } else {
                        UT.sendMessageOnCase(ChatColor.RED, "명령어 형식이 잘못됐습니다.", isPlayer, p);
                        return false;
                    }
                break;

                case "set":
                case "add":
                    // check permission
                    if (!p.hasPermission("kyh.money.see")) {
                        UT.sendMessageOnCase(ChatColor.RED, "다른 플레이어의 돈을 수정할 권한(kyh.money.set)이 없습니다.", isPlayer, p);
                        return true;
                    }
                    // syntax error
                    if (len == 1) {
                        UT.sendMessageOnCase(ChatColor.RED, "설정할 금액과 대상을 입력해주세요. /money " + args[0] + " [금액] [대상]", isPlayer, p);
                    } else if (len <= 3 + 1) { // +1 is <Enter>
                        if (len >= 2 + 1) { // check target is exists
                            t = UT.getPlayerStartWith(args[2]);
                            if (t == null) {
                                UT.sendMessageOnCase(ChatColor.RED, "대상이 존재하지 않습니다.", isPlayer, p);
                                return true;
                            }
                        } else {
                            if (!isPlayer) {
                                System.out.println("설정할 금액과 대상을 입력해주세요. /money " + args[0] + " [금액] [대상]");
                            } else {
                                t = p; // Target is sender
                            }
                        }
                        preMoney = getMoney(p);
                        money = Integer.parseInt(args[1]);
                        String addMsg = "";
                        int addMoney = 0;
                        if (args[0].equals("add")) {
                            addMoney = preMoney + money;
                            if (money >= 0) {
                                addMsg = "원이 추가되어 ";
                            } else {
                                addMsg = "원이 차감되어 ";
                            }
                        }
                        if (args[0].equals("set")) {
                            plugin.getConfig().set("money." + t.getUniqueId(), money);
                            msg = UT.getNameWithDisplay(t) + "님의 돈이 " + preMoney + "원에서 " + money + "원으로 설정됐습니다.";
                        } else {
                            plugin.getConfig().set("money." + t.getUniqueId(), addMoney);
                            msg = UT.getNameWithDisplay(t) + "님의 돈이 " + preMoney + "원에서 " + money + addMsg + addMoney + "원으로 설정됐습니다.";
                        }
                        t.sendMessage(ChatColor.GOLD + msg);
                        plugin.saveConfig();
                    } else {
                        UT.sendMessageOnCase(ChatColor.RED, "명령어 형식이 잘못됐습니다.", isPlayer, p);
                        return false;
                    }
                break;

                case "list":
                    if (len <= 1 + 1) {
                        if (!p.hasPermission("key.money.list")) {
                            UT.sendMessageOnCase(ChatColor.RED, "돈을 목록을 볼 권한(kyh.money.list)이 없습니다.", isPlayer, p);
                            return true;
                        }

                        List<String> moneyList = new ArrayList<>();

                        for (Player playerInServer: Bukkit.getServer().getOnlinePlayers()) {
                            String playerMoney = playerInServer.getName() + ": "
                                + plugin.getConfig().getInt("money." + playerInServer.getUniqueId()) + "원";
                            moneyList.add(playerMoney);
                        }

                        for (String s: moneyList) {
                            UT.sendMessageOnCase(ChatColor.GOLD, s, isPlayer, p);
                        }

                    } else {
                        UT.sendMessageOnCase(ChatColor.RED, "명령어 형식이 잘못됐습니다.", isPlayer, p);
                        return false;
                    }
                break;

                case "send":
                    if (len <= 1 + 1) {
                        if (!p.hasPermission("key.money.list")) {
                            UT.sendMessageOnCase(ChatColor.RED, "돈을 보낼 권한(kyh.money.send)이 없습니다.", isPlayer, p);
                            return true;
                        }

                        List<String> moneyList = new ArrayList<>();

                        for (Player playerInServer: Bukkit.getServer().getOnlinePlayers()) {
                            String playerMoney = playerInServer.getName() + ": "
                                    + plugin.getConfig().getInt("money." + playerInServer.getUniqueId()) + "원";
                            moneyList.add(playerMoney);
                        }

                        for (String s: moneyList) {
                            UT.sendMessageOnCase(ChatColor.GOLD, s, isPlayer, p);
                        }

                    } else {
                        UT.sendMessageOnCase(ChatColor.RED, "명령어 형식이 잘못됐습니다.", isPlayer, p);
                        return false;
                    }
                break;

                default:
                    UT.sendMessageOnCase(ChatColor.RED, "명령어 형식이 잘못됐습니다.", isPlayer, p);
                    return false;
            }
        }

        return true;
    }

    private void setMoney(int m, Player target) {
        plugin.getConfig().set("money." + target.getUniqueId(), m);
    }

    private void addMoney(int m, Player target) {
        int newMoney = getMoney(target) + m;
        setMoney(newMoney, target);
    }

    private void sendMoney(int m, Player targetFrom, Player targetTo) {

        System.out.println(targetFrom.getName() + "님에게서 " + targetTo + "님에게의 " + m + "원 송금은 돈이 충분하지 않아 실패하였습니다.");
    }

    private int getMoney(Player target) {
        return plugin.getConfig().getInt("money." + target.getUniqueId());
    }
}
