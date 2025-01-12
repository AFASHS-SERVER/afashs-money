package com.github.jaeukkang12.money;

import com.github.jaeukkang12.lib.config.Config;
import com.github.jaeukkang12.money.api.EconomyAPI;
import com.github.jaeukkang12.money.command.MoneyCommand;
import com.github.jaeukkang12.money.command.MoneyTab;
import com.github.jaeukkang12.money.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Money extends JavaPlugin {

    private static JavaPlugin plugin; // INSTANCE

    public static Config moneyData; // PLAYER MONEY DATA
    public static Config config; // CONFIG

    private static Economy economy;

    @Override
    public void onEnable() {
        if (Bukkit.getPluginManager().getPlugin("afashs-lib") == null) {
            Bukkit.getLogger().warning("[" + this.getName() + "]" + "afashs-lib 플러그인이 감지되지 않았습니다! 플러그인을 종료합니다.");
            Bukkit.getPluginManager().disablePlugin(this);
        }

        // INSTANCE
        plugin = this;

        // CONFIG
        config = new Config("config", plugin);
        config.setPrefix("prefix");
        config.loadDefaultConfig();

        // PLAYER MONEY DATA
        moneyData = new Config("data", plugin);
        moneyData.loadDefaultConfig();

        // ECONOMY
        economy = new Economy();

        // COMMAND
        Bukkit.getPluginCommand("돈").setExecutor(new MoneyCommand());
        Bukkit.getPluginCommand("돈").setTabCompleter(new MoneyTab());
    }

    public static JavaPlugin getPlugin() {
        return plugin;
    }

    public static Economy getEconomy() {
        return economy;
    }
}
