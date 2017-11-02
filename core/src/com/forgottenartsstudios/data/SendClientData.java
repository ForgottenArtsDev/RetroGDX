package com.forgottenartsstudios.data;

import com.forgottenartsstudios.helpers.Variables;
import com.forgottenartsstudios.networking.packets.ChooseChar;
import com.forgottenartsstudios.networking.packets.CreateChar;
import com.forgottenartsstudios.networking.packets.KeepAliveCheck;
import com.forgottenartsstudios.networking.packets.Login;
import com.forgottenartsstudios.networking.packets.MovePlayer;
import com.forgottenartsstudios.networking.packets.NewAccount;
import com.forgottenartsstudios.networking.packets.SendBuyItem;
import com.forgottenartsstudios.networking.packets.SendDropItem;
import com.forgottenartsstudios.networking.packets.SendMessage;
import com.forgottenartsstudios.networking.packets.SendPickUpItem;
import com.forgottenartsstudios.networking.packets.SendSearch;
import com.forgottenartsstudios.networking.packets.SendTryAttack;
import com.forgottenartsstudios.networking.packets.SendUseItem;
import com.forgottenartsstudios.networking.packets.SendUsePoint;
import com.forgottenartsstudios.networking.packets.WarpCheck;

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
        mPlayer.Map = Variables.players[Variables.MyIndex].getMap();
        mPlayer.X = Variables.players[Variables.MyIndex].getX();
        mPlayer.Y = Variables.players[Variables.MyIndex].getY();
        mPlayer.Dir = dir;
        client.sendTCP(mPlayer);
    }
    public static void SendKeepAliveCheck() {
        KeepAliveCheck keepAliveCheck = new KeepAliveCheck();

        keepAliveCheck.index = Variables.MyIndex;
        keepAliveCheck.keepAlive = true;

        client.sendTCP(keepAliveCheck);
    }
    public static void SendWarpCheck() {
        WarpCheck wCheck = new WarpCheck();
        wCheck.index = Variables.MyIndex;

        client.sendTCP(wCheck);
    }
    public static void SendSearch(int X, int Y, int Type, int npcNum) {
        SendSearch sndSearch = new SendSearch();
        sndSearch.searchType = Type;
        sndSearch.index = Variables.MyIndex;
        sndSearch.npcIndex = npcNum;
        sndSearch.x = X;
        sndSearch.y = Y;
        sndSearch.mapNum = Variables.players[Variables.MyIndex].getMap();

        client.sendTCP(sndSearch);
    }
    public static void SendBuyItem() {
        SendBuyItem sBuyItem = new SendBuyItem();

        sBuyItem.index = Variables.MyIndex;
        sBuyItem.shopNum = Variables.ShopNum;
        sBuyItem.shopSlot = Variables.selectedShopSlot;

        Variables.buyItem = true;
        client.sendTCP(sBuyItem);
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

        sendDropItem.index = Variables.MyIndex;
        sendDropItem.invSlot = invSlot;
        sendDropItem.x = Variables.players[Variables.MyIndex].getX();
        sendDropItem.y = Variables.players[Variables.MyIndex].getY();

        client.sendTCP(sendDropItem);
    }
    public static void SendPickUpItem() {
        SendPickUpItem sendPickUpItem = new SendPickUpItem();

        sendPickUpItem.index = Variables.MyIndex;

        client.sendTCP(sendPickUpItem);
    }
    public static void SendUsePoint(int statNum) {
        SendUsePoint sendUsePoint = new SendUsePoint();

        sendUsePoint.index = Variables.MyIndex;
        sendUsePoint.statNum = statNum;

        Variables.usePoint = true;
        Variables.usePointTimer = 1;

        client.sendTCP(sendUsePoint);
    }
    public static void SendMessage() {
        SendMessage sendMessage = new SendMessage();

        sendMessage.index = Variables.MyIndex;
        if (Variables.chatInput.substring(0, 1).equals("'")) {
            sendMessage.type = Variables.MESSAGE_TYPE_GLOBAL;
        } else if (Variables.chatInput.substring(0, 1).equals("@")) {
            System.out.println("Whisper");
            sendMessage.type = Variables.MESSAGE_TYPE_WHISPER;
        } else {
            sendMessage.type = Variables.MESSAGE_TYPE_MAP;
        }
        sendMessage.msg = Variables.chatInput;

        Variables.chatInput = "";

        client.sendTCP(sendMessage);
    }
}
