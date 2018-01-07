package com.forgottenartsstudios.networking.packets;

import com.forgottenartsstudios.data.Spell_Inv_Struct;

/**
 * Created by forgo on 12/23/2017.
 */

public class SendSpellInv extends Packet {
    public int index;
    public Spell_Inv_Struct[] splData = new Spell_Inv_Struct[60 + 1];
}
