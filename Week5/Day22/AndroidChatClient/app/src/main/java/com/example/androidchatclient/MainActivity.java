package com.example.androidchatclient;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.InputDevice;
import android.view.View;
import android.widget.TextView;

import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;
import com.neovisionaries.ws.client.WebSocketFactory;

import org.w3c.dom.Text;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    public static WebSocket ws_;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create a WebSocket factory. The timeout value remains 0.
        WebSocketFactory factory = new WebSocketFactory();

        // Create a WebSocket with a socket connection timeout value.
        try {
            ws_ = factory.createSocket("ws://10.0.2.2:8080/endpoint", 5000);
            ws_.addListener(new MyWebSocket());
            ws_.connectAsynchronously();
            Log.d("WC: MainActivity: ", "ws async connect called");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void login(View view) {
        TextView userName = findViewById(R.id.usrNm);
        TextView roomName = findViewById(R.id.rmNm);
        if (MyWebSocket.connected) {
            ws_.sendText("join " + userName.getText() + " " + roomName.getText());

            Intent intent = new Intent(this, ChatActivity.class);
            intent.putExtra("userName", userName.getText());
            intent.putExtra("roomName", roomName.getText());
            startActivity(intent);
        }
        else {
            //new Runnable()
        }
    }
}