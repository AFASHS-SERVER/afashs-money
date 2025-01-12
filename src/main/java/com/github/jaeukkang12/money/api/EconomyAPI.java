package com.github.jaeukkang12.money.api;

import com.github.jaeukkang12.money.economy.Economy;

import static com.github.jaeukkang12.money.Money.getEconomy;

public class EconomyAPI {

    private Economy economy;

    public EconomyAPI() {
        economy = getEconomy();
    }

    public Economy getEconomyManager() {
        return economy;
    }


}
