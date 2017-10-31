package com.forgottenartsstudios.networking.packets;

/**
 * Created by forgo on 5/27/2017.
 */

public class SendShop extends Packet {
    public int shopNum;
    public String name;
    public String welcomeMsg;
    public String goodbyeMsg;
    public int salesTax;

    public int[] itemNums = new int[20 + 1];
    public int[] itemVals = new int[20 + 1];
}
