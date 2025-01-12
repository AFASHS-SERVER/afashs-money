package com.github.jaeukkang12.money.command;

import com.github.jaeukkang12.lib.util.NumberUtil;
import com.github.jaeukkang12.money.economy.Economy;
import com.github.jaeukkang12.money.exceptions.NegativeMoneyException;
import com.github.jaeukkang12.money.exceptions.NotEnoughMoneyException;
import com.github.jaeukkang12.money.exceptions.SamePlayerException;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static com.github.jaeukkang12.money.Money.config;
import static com.github.jaeukkang12.money.Money.getEconomy;

public class MoneyCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command label, String s, String[] args) {

        if (!(sender instanceof Player player)) return true;

        Economy economy = getEconomy();

        // CHECK
        if (args.length == 0 || args[0].equals("확인")) {
            if (!player.hasPermission("afashs.money.help")) {
                player.sendMessage(config.getMessage("errorMessages.noPermission"));
                return true;
            }

            player.sendMessage(config.getMessage("messages.money.check")
                    .replace("{player}", player.getName())
                    .replace("{money}", economy.getStringMoney(player)));
            return true;
        }

        // HELP
        if (args[0].equals("도움말")) {
            if (!player.hasPermission("afashs.money.help")) {
                player.sendMessage(config.getMessage("errorMessages.noPermission"));
                return true;
            }

            config.getMessages("messages.money.help").forEach(player::sendMessage);
            return true;
        }

        // SEND
        if (args[0].equals("보내기")) {
            if (!player.hasPermission("afashs.money.send")) {
                player.sendMessage(config.getMessage("errorMessages.noPermission"));
                return true;
            }

            if (args.length == 1) {
                player.sendMessage(config.getMessage("errorMessages.nonTarget"));
                return true;
            }

            if (args.length == 2) {
                player.sendMessage(config.getMessage("errorMessages.nonAmount"));
                return true;
            }

            if (args.length > 3) {
                player.sendMessage(config.getMessage("errorMessages.wrongCommand"));
                return true;
            }

            Player target = Bukkit.getPlayer(args[1]);
            if (target == null) {
                player.sendMessage(config.getMessage("errorMessages.invalidPlayer"));
                return true;
            }

            int amount;
            try {
                amount = Integer.parseInt(args[2]);
            } catch (NumberFormatException e) {
                player.sendMessage(config.getMessage("errorMessages.invalidAmount"));
                return true;
            }

            try {
                economy.sendMoney(player, target,amount);

                // PLAYER
                player.sendMessage(config.getMessage("messages.money.send")
                        .replace("{target}", target.getName())
                        .replace("{amount}", NumberUtil.format(amount)));
                player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BELL, SoundCategory.MASTER, 1f, 1f);

                // TARGET
                target.sendMessage(config.getMessage("messages.money.receive")
                        .replace("{player}", player.getName())
                        .replace("{amount}", NumberUtil.format(amount)));
                target.playSound(target, Sound.BLOCK_NOTE_BLOCK_BELL, SoundCategory.MASTER, 1f, 1f);

                return true;

            } catch (NotEnoughMoneyException e) {
                player.sendMessage(config.getMessage("errorMessages.notEnoughMoney"));
                return true;
            }  catch (NegativeMoneyException e) {
                player.sendMessage(config.getMessage("errorMessages.cannotNegative"));
                return true;
            } catch (SamePlayerException e) {
                player.sendMessage(config.getMessage("errorMessages.samePlayer"));
                return true;
            }
        }

        // ADD
        if (args[0].equals("추가")) {
            if (!player.hasPermission("afashs.money.add")) {
                player.sendMessage(config.getMessage("errorMessages.noPermission"));
                return true;
            }

            if (args.length == 1) {
                player.sendMessage(config.getMessage("errorMessages.nonTarget"));
                return true;
            }

            if (args.length == 2) {
                player.sendMessage(config.getMessage("errorMessages.nonAmount"));
                return true;
            }

            if (args.length > 3) {
                player.sendMessage(config.getMessage("errorMessages.wrongCommand"));
                return true;
            }

            Player target = Bukkit.getPlayer(args[1]);
            if (target == null) {
                player.sendMessage(config.getMessage("errorMessages.invalidPlayer"));
                return true;
            }

            int amount;
            try {
                amount = Integer.parseInt(args[2]);
            } catch (NumberFormatException e) {
                player.sendMessage(config.getMessage("errorMessages.invalidAmount"));
                return true;
            }

            economy.addMoney(target, amount);

            // PLAYER
            player.sendMessage(config.getMessage("messages.money.add")
                    .replace("{target}", target.getName())
                    .replace("{amount}", NumberUtil.format(amount)));
            player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BELL, SoundCategory.MASTER, 1f, 1f);

            // TARGET
            target.sendMessage(config.getMessage("messages.money.added")
                    .replace("{player}", player.getName())
                    .replace("{amount}", NumberUtil.format(amount)));
            target.playSound(target, Sound.BLOCK_NOTE_BLOCK_BELL, SoundCategory.MASTER, 1f, 1f);
            return true;
        }

        // REMOVE
        if (args[0].equals("제거")) {
            if (!player.hasPermission("afashs.money.remove")) {
                player.sendMessage(config.getMessage("errorMessages.noPermission"));
                return true;
            }

            if (args.length == 1) {
                player.sendMessage(config.getMessage("errorMessages.nonTarget"));
                return true;
            }

            if (args.length == 2) {
                player.sendMessage(config.getMessage("errorMessages.nonAmount"));
                return true;
            }

            if (args.length > 3) {
                player.sendMessage(config.getMessage("errorMessages.wrongCommand"));
                return true;
            }

            Player target = Bukkit.getPlayer(args[1]);
            if (target == null) {
                player.sendMessage(config.getMessage("errorMessages.invalidPlayer"));
                return true;
            }

            int amount;
            try {
                amount = Integer.parseInt(args[2]);
            } catch (NumberFormatException e) {
                player.sendMessage(config.getMessage("errorMessages.invalidAmount"));
                return true;
            }

            economy.removeMoney(target, amount);

            // PLAYER
            player.sendMessage(config.getMessage("messages.money.remove")
                    .replace("{target}", target.getName())
                    .replace("{amount}", NumberUtil.format(amount)));
            player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BELL, SoundCategory.MASTER, 1f, 1f);

            // TARGET
            target.sendMessage(config.getMessage("messages.money.removed")
                    .replace("{player}", player.getName())
                    .replace("{amount}", NumberUtil.format(amount)));
            target.playSound(target, Sound.BLOCK_NOTE_BLOCK_BELL, SoundCategory.MASTER, 1f, 1f);
            return true;
        }

        // SET
        if (args[0].equals("설정")) {
            if (!player.hasPermission("afashs.money.set")) {
                player.sendMessage(config.getMessage("errorMessages.noPermission"));
                return true;
            }

            if (args.length == 1) {
                player.sendMessage(config.getMessage("errorMessages.nonTarget"));
                return true;
            }

            if (args.length == 2) {
                player.sendMessage(config.getMessage("errorMessages.nonAmount"));
                return true;
            }

            if (args.length > 3) {
                player.sendMessage(config.getMessage("errorMessages.wrongCommand"));
                return true;
            }

            Player target = Bukkit.getPlayer(args[1]);
            if (target == null) {
                player.sendMessage(config.getMessage("errorMessages.invalidPlayer"));
                return true;
            }

            int amount;
            try {
                amount = Integer.parseInt(args[2]);
            } catch (NumberFormatException e) {
                player.sendMessage(config.getMessage("errorMessages.invalidAmount"));
                return true;
            }

            economy.setMoney(target, amount);

            // PLAYER
            player.sendMessage(config.getMessage("messages.money.set")
                    .replace("{target}", target.getName())
                    .replace("{amount}", NumberUtil.format(amount)));
            player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BELL, SoundCategory.MASTER, 1f, 1f);

            // TARGET
            target.sendMessage(config.getMessage("messages.money.isSet")
                    .replace("{player}", player.getName())
                    .replace("{amount}", NumberUtil.format(amount)));
            target.playSound(target, Sound.BLOCK_NOTE_BLOCK_BELL, SoundCategory.MASTER, 1f, 1f);
            return true;
        }
        return false;
    }
}
