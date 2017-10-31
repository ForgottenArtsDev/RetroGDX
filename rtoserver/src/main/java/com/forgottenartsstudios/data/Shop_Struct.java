package com.forgottenartsstudios.data;

import java.io.Serializable;

/**
 * Created by Perfekt on 5/22/2017.
 */

public class Shop_Struct implements Serializable {
    public String Name;

    public String welcomeMsg;
    public String goodbyeMsg;

    public int salesTax;

    public int[] itemNum = new int[20 + 1];
    public int[] itemVal = new int[20 + 1];

    public boolean canRepair;
}
