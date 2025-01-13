package com.github.jaeukkang12.money.economy;

import com.github.jaeukkang12.lib.economy.EconomyImpl;
import com.github.jaeukkang12.lib.util.NumberUtil;
import com.github.jaeukkang12.money.exceptions.NegativeMoneyException;
import com.github.jaeukkang12.money.exceptions.NotEnoughMoneyException;
import com.github.jaeukkang12.money.exceptions.SamePlayerException;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;

import static com.github.jaeukkang12.money.Money.*;

public class Economy implements EconomyImpl {


    public Economy() {
    }

    public void init(Player target) {
        setMoney(target, config.getInt("initialMoney"));
    }

    @Override
    public double getMoney(Player target) {
        if(!moneyData.isExist(target.getUniqueId() + "")) {
            init(target);
        }
        return moneyData.getDouble(target.getUniqueId() + "");
    }

    public String getStringMoney(Player target) {
        return NumberUtil.format(getMoney(target));
    }

    @Override
    public void addMoney(Player target, double i) {
        moneyData.setDouble(target.getUniqueId() + "", getMoney(target) + i);
    }

    @Override
    public void removeMoney(Player target, double i) {
        moneyData.setDouble(target.getUniqueId() + "", getMoney(target) - i);
    }

    @Override
    public void setMoney(Player target, double i) {
        moneyData.setDouble(target.getUniqueId() + "", i);
    }

    @Override
    public void sendMoney(Player sender, Player sendTo, double i) throws NotEnoughMoneyException,NegativeMoneyException,SamePlayerException {
        if (getMoney(sender) < i) {
            throw new NotEnoughMoneyException();
        }

        if (i <= 0) {
            throw new NegativeMoneyException();
        }

        if (sender == sendTo) {
            throw new SamePlayerException();
        }

        removeMoney(sender, i);
        addMoney(sendTo, i);
    }
}
