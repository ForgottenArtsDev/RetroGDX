package com.forgottenartsstudios.networking.packets;

import com.forgottenartsstudios.data.Spell_Struct;

/**
 * Created by forgo on 1/3/2018.
 */

public class SendSpells extends Packet {
    public int index;
    public int spellNum;
    public Spell_Struct spell;
}
