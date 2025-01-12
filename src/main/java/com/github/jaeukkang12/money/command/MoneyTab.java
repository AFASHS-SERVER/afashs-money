package com.github.jaeukkang12.money.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MoneyTab implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) return Collections.emptyList();

        if (args.length == 1) {
            List<String> list = new ArrayList<>();
            if (player.hasPermission("afashs.money.help")) list.add("도움말");
            if (player.hasPermission("afashs.money.send")) list.add("보내기");
            if (player.hasPermission("afashs.money.add")) list.add("추가");
            if (player.hasPermission("afashs.money.set")) list.add("설정");
            if (player.hasPermission("afashs.money.remove")) list.add("차감");
            return list;
        } else if (args.length == 2) return null;
        else if (args.length == 3) {
            if (List.of("보내기","추가","설정","제거").contains(args[0])) {
                return List.of("<금액>");
            }
        }
        return Collections.emptyList();
    }
}
