package com.github.jaeukkang12.money.api;

import com.github.jaeukkang12.money.economy.Economy;

public class EconomyAPI {

    private Economy economy;

    public EconomyAPI() {
        economy = new Economy();
    }

    public Economy getEconomyManager() {
        return economy;
    }


}
