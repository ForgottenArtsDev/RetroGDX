package com.forgottenartsstudios.networking.packets;

import com.forgottenartsstudios.data.AccountData;
import com.forgottenartsstudios.data.Player;

/**
 * Created by forgo on 10/6/2017.
 */

public class SendLogin extends Packet {
    public int Index;
    public AccountData accountData;
    public Player char1;
    public Player char2;
    public Player char3;
}
