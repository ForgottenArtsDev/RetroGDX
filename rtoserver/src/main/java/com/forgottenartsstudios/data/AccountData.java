package com.forgottenartsstudios.data;

import com.forgottenartsstudios.helpers.ServerVars;
import com.sun.org.apache.xpath.internal.operations.Variable;

import java.io.Serializable;

/**
 * Created by forgo on 10/6/2017.
 */

public class AccountData implements Serializable {
    private String ID;
    private String PW;

    private int CID;

    private boolean keepAlive;
    private int keepCount;

    public Player[] chars = new Player[3 + 1];

    public String getID() { return ID; }
    public String getPW() { return PW; }
    public int getCID() { return CID; }
    //public Player getChar1() { return char1; }
    //public Player getChar2() { return char2; }
    //public Player getChar3() { return char3; }
    public boolean isKeepAlive() { return keepAlive; }
    public int getKeepCount() { return keepCount; }

    public void setID(String id) { ID = id; }
    public void setPW(String pw) { PW = pw; }
    public void setCID(int cid) { CID = cid; }
    //public void setChar1(Player Char1) { char1 = Char1; }
    //public void setChar2(Player Char2) { char2 = Char2; }
    //public void setChar3(Player Char3) { char3 = Char3; }
    public void setKeepAlive(boolean keepAlive) { this.keepAlive = keepAlive; }
    public void setKeepCount(int keepCount) { this.keepCount = keepCount; }
}
