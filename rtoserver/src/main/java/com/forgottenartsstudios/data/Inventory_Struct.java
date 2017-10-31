package com.forgottenartsstudios.data;

import java.io.Serializable;

/**
 * Created by forgo on 10/14/2017.
 */

public class Inventory_Struct implements Serializable {
    private int itemNum;
    private int itemVal;

    public int getItemNum() { return itemNum; }
    public int getItemVal() { return itemVal; }

    public void setItemNum(int itemNum) { this.itemNum = itemNum; }
    public void setItemVal(int itemVal) { this.itemVal = itemVal; }
}
