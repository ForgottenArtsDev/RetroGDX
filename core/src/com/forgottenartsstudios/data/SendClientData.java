package com.forgottenartsstudios.data;

import com.forgottenartsstudios.helpers.Variables;
import com.forgottenartsstudios.networking.packets.AppointPartyLeader;
import com.forgottenartsstudios.networking.packets.CheckPlayerDataNull;
import com.forgottenartsstudios.networking.packets.ChooseChar;
import com.forgottenartsstudios.networking.packets.CreateChar;
import com.forgottenartsstudios.networking.packets.DisbandParty;
import com.forgottenartsstudios.networking.packets.KeepAliveCheck;
import com.forgottenartsstudios.networking.packets.KickPartyMember;
import com.forgottenartsstudios.networking.packets.LeaveParty;
import com.forgottenartsstudios.networking.packets.Login;
import com.forgottenartsstudios.networking.packets.MovePlayer;
import com.forgottenartsstudios.networking.packets.NewAccount;
import com.forgottenartsstudios.networking.packets.PartyDecision;
import com.forgottenartsstudios.networking.packets.SendBuyItem;
import com.forgottenartsstudios.networking.packets.SendCastSpell;
import com.forgottenartsstudios.networking.packets.SendDropItem;
import com.forgottenartsstudios.networking.packets.SendMessage;
import com.forgottenartsstudios.networking.packets.SendPartyInvite;
import com.forgottenartsstudios.networking.packets.SendPickUpItem;
import com.forgottenartsstudios.networking.packets.SendSearch;
import com.forgottenartsstudios.networking.packets.SendTryAttack;
import com.forgottenartsstudios.networking.packets.SendUseItem;
import com.forgottenartsstudios.networking.packets.SendUsePoint;
import com.forgottenartsstudios.networking.packets.SetHotKey;
import com.forgottenartsstudios.networking.packets.TrashItem;
import com.forgottenartsstudios.networking.packets.UpdatePartyDropType;

import static com.forgottenartsstudios.rtonline.RTOnline.client;

/**
 * Created by forgo on 10/6/2017.
 */

public class SendClientData {
    public static void SendLogin(String ID, String PW) {
        Login login = new Login();
        login.ID = ID;
        login.PW = PW;
        client.sendTCP(login);
    }
    public static void SendNewAccount(String ID, String PW) {
        NewAccount newAccount = new NewAccount();
        newAccount.ID = ID;
        newAccount.PW = PW;
        client.sendTCP(newAccount);
    }
    public static void SendCreateChar(int Index) {
        CreateChar crChar = new CreateChar();
        crChar.Name = Variables.TempName;
        crChar.Job = Variables.TempJob;
        crChar.Sprite = Variables.TempSprite;
        crChar.CharSlot = Variables.CharIndex;
        crChar.Index = Index;

        Variables.TempName = "";
        Variables.TempJob = 0;
        Variables.TempSprite = 0;

        client.sendTCP(crChar);
    }
    public static void SendChooseChar(int charSlot) {
        ChooseChar chChar = new ChooseChar();
        chChar.index = Variables.MyIndex;
        chChar.charSlot = charSlot;
        client.sendTCP(chChar);
    }
    public static void SendMovePlayer(int dir) {
        MovePlayer mPlayer = new MovePlayer();
        mPlayer.Index = Variables.MyIndex;
        mPlayer.Map = Variables.Players[Variables.MyIndex].getMap();
        mPlayer.X = Variables.Players[Variables.MyIndex].getX();
        mPlayer.Y = Variables.Players[Variables.MyIndex].getY();
        mPlayer.Dir = dir;
        client.sendTCP(mPlayer);

        int X = mPlayer.X;
        int Y = mPlayer.Y;
        int Map = Variables.Players[Variables.MyIndex].getMap();

        boolean canMove;

        switch (dir) {
            case Variables.DIR_UP:
                if (Y <= 0 || (Y - 1) < 0) {

                } else {
                    canMove = TileIsOpen(Map, X, Y - 1);
                    if (canMove) {
                        Variables.Players[Variables.MyIndex].setY(Y - 1);
                        Variables.Players[Variables.MyIndex].setOffsetY(32);
                        Variables.Players[Variables.MyIndex].setMoving(1);
                        Variables.Players[Variables.MyIndex].setDir(dir);
                    } else {
                        Variables.Players[Variables.MyIndex].setDir(dir);
                    }
                }
                break;
            case Variables.DIR_DOWN:
                if (Variables.mapRender[Map] == null) { break; }
                if (Y >= Variables.mapRender[Map].MaxY - 1 || (Y + 1) > Variables.mapRender[Map].MaxY - 1) {

                } else {
                    canMove = TileIsOpen(Map, X, Y + 1);
                    if (canMove) {
                        Variables.Players[Variables.MyIndex].setY(Y + 1);
                        Variables.Players[Variables.MyIndex].setOffsetY(32 * -1);
                        Variables.Players[Variables.MyIndex].setMoving(1);
                        Variables.Players[Variables.MyIndex].setDir(dir);
                    } else {
                        Variables.Players[Variables.MyIndex].setDir(dir);
                    }
                }
                break;

            case Variables.DIR_LEFT:
                if (X <= 0 || (X - 1) < 0) {

                } else {
                    canMove = TileIsOpen(Map, X - 1, Y);
                    if (canMove) {
                        Variables.Players[Variables.MyIndex].setX(X - 1);
                        Variables.Players[Variables.MyIndex].setOffsetX(32);
                        Variables.Players[Variables.MyIndex].setMoving(1);
                        Variables.Players[Variables.MyIndex].setDir(dir);
                    } else {
                        Variables.Players[Variables.MyIndex].setDir(dir);
                    }
                }
                break;
            case Variables.DIR_RIGHT:
                if (Variables.mapRender[Map] == null) { break; }
                if (X >= Variables.mapRender[Map].MaxX - 1 || (X + 1) > Variables.mapRender[Map].MaxX - 1) {

                } else {
                    canMove = TileIsOpen(Map, X + 1, Y);
                    if (canMove) {
                        Variables.Players[Variables.MyIndex].setX(X + 1);
                        Variables.Players[Variables.MyIndex].setOffsetX(32 * -1);
                        Variables.Players[Variables.MyIndex].setMoving(1);
                        Variables.Players[Variables.MyIndex].setDir(dir);
                    } else {
                        Variables.Players[Variables.MyIndex].setDir(dir);
                    }
                }
                break;
        }
    }
    public static void SendKeepAliveCheck() {
        KeepAliveCheck keepAliveCheck = new KeepAliveCheck();

        keepAliveCheck.index = Variables.MyIndex;
        keepAliveCheck.keepAlive = true;

        client.sendTCP(keepAliveCheck);
    }
    public static void SendSearch(int X, int Y, int Type, int targetNum) {
        SendSearch sndSearch = new SendSearch();
        sndSearch.searchType = Type;
        sndSearch.index = Variables.MyIndex;
        sndSearch.targetIndex = targetNum;
        sndSearch.x = X;
        sndSearch.y = Y;
        sndSearch.mapNum = Variables.Players[Variables.MyIndex].getMap();

        client.sendTCP(sndSearch);
    }
    public static void SendBuyItem() {
        SendBuyItem sBuyItem = new SendBuyItem();

        sBuyItem.index = Variables.MyIndex;
        sBuyItem.shopNum = Variables.ShopNum;
        sBuyItem.shopSlot = Variables.selectedShopSlot;
        sBuyItem.buyAmt = Variables.shopBuyAmt;

        Variables.buyItem = true;
        client.sendTCP(sBuyItem);

        Variables.shopBuyAmt = 1;
    }
    public static void SendUseItem() {
        SendUseItem sUseItem = new SendUseItem();

        sUseItem.index = Variables.MyIndex;
        sUseItem.invSlot = Variables.selectedInvSlot;

        Variables.useItem = true;
        client.sendTCP(sUseItem);
    }
    public static void SendAttack() {
        SendTryAttack sTAtk = new SendTryAttack();

        sTAtk.index = Variables.MyIndex;

        client.sendTCP(sTAtk);
    }
    public static void SendDropItem(int invSlot) {
        SendDropItem sendDropItem = new SendDropItem();

        if (Variables.inInventory) {
            if (invSlot > 0) {
                if (Variables.Players[Variables.MyIndex].inventory[invSlot].getItemNum() > 0) {
                    sendDropItem.index = Variables.MyIndex;
                    sendDropItem.invSlot = invSlot;
                    sendDropItem.x = Variables.Players[Variables.MyIndex].getX();
                    sendDropItem.y = Variables.Players[Variables.MyIndex].getY();
                    client.sendTCP(sendDropItem);
                }
            }
        }
    }
    public static void SendPickUpItem() {
        SendPickUpItem sendPickUpItem = new SendPickUpItem();

        sendPickUpItem.index = Variables.MyIndex;

        client.sendTCP(sendPickUpItem);
    }
    public static void SendUsePoint() {
        SendUsePoint sendUsePoint = new SendUsePoint();

        sendUsePoint.index = Variables.MyIndex;
        sendUsePoint.STR = Variables.tempStats.STR;
        sendUsePoint.DEF = Variables.tempStats.DEF;
        sendUsePoint.VIT = Variables.tempStats.VIT;
        sendUsePoint.AGI = Variables.tempStats.AGI;
        sendUsePoint.MAG = Variables.tempStats.MAG;

        sendUsePoint.Points = Variables.tempStats.Points;

        Variables.usePoint = true;
        Variables.usePointTimer = 1;

        Variables.tempStats = new TempStats();

        client.sendTCP(sendUsePoint);
    }
    public static void SendMessage() {
        SendMessage sendMessage = new SendMessage();

        sendMessage.index = Variables.MyIndex;
        if (Variables.chatInput.substring(0, 1).equals("'")) {
            sendMessage.type = Variables.MESSAGE_TYPE_GLOBAL;
        } else if (Variables.chatInput.substring(0, 1).equals("@")) {
            sendMessage.type = Variables.MESSAGE_TYPE_WHISPER;
        } else if (Variables.chatInput.substring(0, 1).equals("\"")) {
            sendMessage.type = Variables.MESSAGE_TYPE_PARTY;
        } else {
            sendMessage.type = Variables.MESSAGE_TYPE_MAP;
        }
        sendMessage.msg = Variables.chatInput;

        Variables.chatInput = "";
        if (Variables.Client_Mode == Variables.Client_Mode_Desktop) {
            Variables.inChat = false;
        }

        client.sendTCP(sendMessage);
    }
    public static void SendPartyInvite() {
        SendPartyInvite sendPartyInvite = new SendPartyInvite();

        sendPartyInvite.index = Variables.MyIndex;
        sendPartyInvite.target = Variables.target;

        client.sendTCP(sendPartyInvite);
    }
    public static void SendPartyDecision(boolean decision) {
        PartyDecision partyDecision = new PartyDecision();

        partyDecision.index = Variables.PartyLeader;
        partyDecision.target = Variables.MyIndex;
        partyDecision.decision = decision;

        client.sendTCP(partyDecision);
    }
    public static void SendDisbandParty() {
        DisbandParty dParty = new DisbandParty();

        dParty.index = Variables.MyIndex;

        client.sendTCP(dParty);
    }
    public static void SendLeaveParty() {
        LeaveParty lParty = new LeaveParty();

        lParty.index = Variables.MyIndex;

        client.sendTCP(lParty);
    }
    public static void SendAppointLeader(int newLeader) {
        AppointPartyLeader aPL = new AppointPartyLeader();

        aPL.index = Variables.MyIndex;
        aPL.newLeader = newLeader;

        client.sendTCP(aPL);
    }
    public static void SendKickMember(int kickMem) {
        KickPartyMember kPM = new KickPartyMember();

        kPM.index = Variables.MyIndex;
        kPM.kickedMember = kickMem;

        client.sendTCP(kPM);
    }
    public static void SendUpdateDropType(int dropType) {
        UpdatePartyDropType uPDT = new UpdatePartyDropType();

        uPDT.index = Variables.MyIndex;
        uPDT.dropType = dropType;

        client.sendTCP(uPDT);
    }
    public static void SendUseHotKey(int hotKey) {
        SendCastSpell sendCastSpell = new SendCastSpell();

        sendCastSpell.index = Variables.MyIndex;
        sendCastSpell.hotKey = hotKey;

        client.sendTCP(sendCastSpell);
    }
    public static void SendSetHotKey(int hotKey, int hotKeyNum) {
        SetHotKey setHotKey = new SetHotKey();

        setHotKey.index = Variables.MyIndex;
        setHotKey.hotKey = hotKey;
        setHotKey.hotKeyVal = hotKeyNum;

        client.sendTCP(setHotKey);
    }
    public static void SendTrashItem(int invSlot) {
        TrashItem trashItem = new TrashItem();

        trashItem.index = Variables.MyIndex;
        trashItem.invSlot = invSlot;

        client.sendTCP(trashItem);
    }
    public static void SendPlayerNull() {
        CheckPlayerDataNull checkPlayerDataNull = new CheckPlayerDataNull();

        checkPlayerDataNull.index = Variables.MyIndex;

        client.sendTCP(checkPlayerDataNull);
    }

    public static boolean TileIsOpen(int mapNum, int X, int Y) {
        // CHECK FOR PLAYERS ON MAP //
        //if (Static.PlayersOnMap[mapNum])
        //{
        for (int LoopI = 1; LoopI <= Variables.MaxPlayers; LoopI++) {
            if (Variables.Players[LoopI] != null) {
                if (Variables.Players[LoopI].getMap() == mapNum) {
                    if (Variables.Players[LoopI].getX() == X) {
                        if (Variables.Players[LoopI].getY() == Y) {
                            return false;
                        }
                    }
                }
            }
        }
        //}

        // CHECK FOR NPCS ON MAP //
        for (int LoopI = 1; LoopI <= Variables.MaxMapNPCs; LoopI++) {
            if (Variables.MapNPCs[LoopI].getNum() > 0) {
                if (Variables.MapNPCs[LoopI].getX() == X) {
                    if (Variables.MapNPCs[LoopI].getY() == Y) {
                        if (!Variables.MapNPCs[LoopI].isDead()) {
                            return false;
                        }
                    }
                }
            }
        }

        // CHECK TILE TYPE //
        if (Variables.mapRender[mapNum].Tile[X][Y].Type != Variables.TILE_TYPE_WALKABLE) {
            if (Variables.mapRender[mapNum].Tile[X][Y].Type != Variables.TILE_TYPE_NPCSPAWN) {
                if (Variables.mapRender[mapNum].Tile[X][Y].Type != Variables.TILE_TYPE_ITEM) {
                    if (Variables.mapRender[mapNum].Tile[X][Y].Type != Variables.TILE_TYPE_WARP) {
                        if (Variables.mapRender[mapNum].Tile[X][Y].Type != Variables.TILE_TYPE_NPCAVOID) {
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }
}
