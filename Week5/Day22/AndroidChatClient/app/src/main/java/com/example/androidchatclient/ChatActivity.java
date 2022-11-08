package com.example.androidchatclient;

import static com.example.androidchatclient.MyWebSocket.messages;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ChatActivity extends AppCompatActivity {
    public static ListView lv_;
    public static ArrayAdapter chatLstAdapter_;
    public String userName_;
    public String roomName_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            userName_ = extras.get("userName").toString();
            TextView rm = findViewById(R.id.rmNm2);
            roomName_ = extras.get("roomName").toString();
            rm.setText(roomName_);
        }
        lv_ = findViewById(R.id.chatLV);
        chatLstAdapter_ = new ArrayAdapter(this, android.R.layout.simple_list_item_1, messages);
        lv_.setAdapter(chatLstAdapter_);
        lv_.post(new Runnable() {
            @Override
            public void run() {
                chatLstAdapter_.notifyDataSetChanged();
                lv_.smoothScrollToPosition(chatLstAdapter_.getCount());
            }
        });
    }

    public void sendMsg(View view) {
        TextView txtMsg = findViewById(R.id.msg);
        if (MyWebSocket.connected) {
            MainActivity.ws_.sendText(userName_ + " " + txtMsg.getText());
//            Intent intent = new Intent(this, ChatActivity.class);
//            startActivity(intent);
        }
        else {
            //new Runnable()
        }
    }
}