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
    public int getMoney(Player target) {
        if(!moneyData.isExist(target.getUniqueId() + "")) {
            init(target);
        }
        return moneyData.getInt(target.getUniqueId() + "");
    }

    public String getStringMoney(Player target) {
        return NumberUtil.format(getMoney(target));
    }

    @Override
    public void addMoney(Player target, int i) {
        moneyData.setInt(target.getUniqueId() + "", getMoney(target) + i);
    }

    @Override
    public void removeMoney(Player target, int i) {
        moneyData.setInt(target.getUniqueId() + "", getMoney(target) - i);
    }

    @Override
    public void setMoney(Player target, int i) {
        moneyData.setInt(target.getUniqueId() + "", i);
    }

    @Override
    public void sendMoney(Player sender, Player sendTo, int i) throws NotEnoughMoneyException,NegativeMoneyException,SamePlayerException {
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
