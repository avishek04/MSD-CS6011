package com.example.androidchatclient;

import static android.content.ContentValues.TAG;

import static com.example.androidchatclient.ChatActivity.chatLstAdapter_;
import static com.example.androidchatclient.ChatActivity.lv_;

import android.content.Intent;
import android.util.Log;
import android.widget.ListView;

import com.google.gson.JsonObject;
import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;
import com.neovisionaries.ws.client.WebSocketException;
import com.neovisionaries.ws.client.WebSocketFactory;
import com.neovisionaries.ws.client.WebSocketFrame;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MyWebSocket extends WebSocketAdapter {
    public static ArrayList<String> messages = new ArrayList<>();
    public static boolean connected = false;

    @Override
    public void onConnected(WebSocket websocket, Map<String, List<String>> headers) throws Exception {
        super.onConnected(websocket, headers);
        connected = true;
        Log.d("CC:webSocket", "Web Socket connected");
    }

//    @Override
//    public void onConnectError(WebSocket websocket, WebSocketException exception) throws Exception {
//        super.onConnectError(websocket, exception);
//    }

    @Override
    public void onTextMessage(WebSocket websocket, String text) throws Exception {
        //super.onTextMessage(websocket, text);
        Log.i("WC: MyWebSocket: ", text);
        JSONObject jsonTxt = new JSONObject(text);

        if (jsonTxt.get("type").equals("join")) {
            messages.add(jsonTxt.get("user") + " joined " + jsonTxt.get("room"));
        }
        else if (jsonTxt.get("type").equals("message")) {
            messages.add(jsonTxt.get("user") + ": " + jsonTxt.get("message"));
        }
        if (lv_ != null) {
            lv_.post(new Runnable() {
                @Override
                public void run() {
                    chatLstAdapter_.notifyDataSetChanged();
                    lv_.smoothScrollToPosition(chatLstAdapter_.getCount());
                }
            });
        }
    }
//    @Override
//    public void onDisconnected(WebSocket websocket, WebSocketFrame serverCloseFrame, WebSocketFrame clientCloseFrame, boolean closedByServer) throws Exception {
//        super.onDisconnected(websocket, serverCloseFrame, clientCloseFrame, closedByServer);
//    }
}
