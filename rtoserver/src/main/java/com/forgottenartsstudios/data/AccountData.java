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

    private int activeChar;

    public Player[] chars = new Player[3 + 1];

    public String getID() { return ID; }
    public String getPW() { return PW; }
    public int getCID() { return CID; }
    public boolean isKeepAlive() { return keepAlive; }
    public int getKeepCount() { return keepCount; }
    public int getActiveChar() { return activeChar; }

    public void setID(String id) { ID = id; }
    public void setPW(String pw) { PW = pw; }
    public void setCID(int cid) { CID = cid; }
    public void setKeepAlive(boolean keepAlive) { this.keepAlive = keepAlive; }
    public void setKeepCount(int keepCount) { this.keepCount = keepCount; }

    public void setActiveChar(int activeChar) { this.activeChar = activeChar; }
}
