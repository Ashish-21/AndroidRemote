package com.example.ashish.vlcremote;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;


public class MainActivity extends AppCompatActivity  implements  View.OnClickListener{

    EditText ip;
    String ipAddress;
    Button connect,shut,restart,disconnect,play,pause,next,up,down,previous;
    ImageView voice;
    SendData s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Thread ini=new Thread(new intialization());
        ini.start();
    }

    void initialization()
    {
        s=new SendData();
        ip=(EditText)findViewById(R.id.ip);
        connect=(Button)findViewById(R.id.connect);
        shut=(Button)findViewById(R.id.shut);
        restart=(Button)findViewById(R.id.restart);
        disconnect=(Button)findViewById(R.id.dis);

        play=(Button)findViewById(R.id.play);
        pause=(Button)findViewById(R.id.pause);
        previous=(Button)findViewById(R.id.previuos);
        next=(Button)findViewById(R.id.next);
        up=(Button)findViewById(R.id.up);
        down=(Button)findViewById(R.id.down);
        voice=(ImageView)findViewById(R.id.voice);


        connect.setOnClickListener(this);
        shut.setOnClickListener(this);
        restart.setOnClickListener(this);;
        disconnect.setOnClickListener(this);

        play.setOnClickListener(this);
        pause.setOnClickListener(this);
        previous.setOnClickListener(this);
        next.setOnClickListener(this);
        up.setOnClickListener(this);
        down.setOnClickListener(this);
        voice.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.connect:
                ipAddress=ip.getText().toString();
                Thread conThread=new Thread(new Connection());
                conThread.start();
                //s.connecting(ipAddress);
                break;
            case R.id.shut:
                s.sendRequest(shut.getText().toString());
                break;
            case R.id.voice:
                getData();
                break;
            case R.id.restart:
                s.sendRequest(restart.getText().toString());
                break;
            case R.id.dis:
                s.sendRequest(disconnect.getText().toString());
                break;
            case R.id.play:
                Log.d("error","Error");
                s.sendRequest(play.getText().toString());
                break;
            case R.id.pause:
                s.sendRequest(pause.getText().toString());
                break;
            case R.id.previuos:
                s.sendRequest(previous.getText().toString());
                break;
                case R.id.next:
                s.sendRequest(next.getText().toString());
                break;
            case R.id.down:
                s.sendRequest("down");
                break;
            case R.id.up:
                s.sendRequest("up");
                break;
            default:
                s.sendRequest("Exit");
                break;
        }
    }

    public void getData() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

        try {
            if(intent.resolveActivity(getPackageManager())!=null)
            startActivityForResult(intent, 10);
            else
            {
                Toast.makeText(getApplicationContext(),"NOt Supported",Toast.LENGTH_LONG).show();
            }

        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),"Not Supported",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 10: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    String com;
                    String search="play pause previous next up down";
                    com=result.get(0);
                    if(search.contains(com))
                    {
                        Toast.makeText(getApplicationContext(),com,Toast.LENGTH_LONG).show();
                        s.sendRequest(com.toLowerCase());
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Command Not Available",Toast.LENGTH_LONG).show();
                    }
                }
                break;
            }
        }

    }
    private class Connection implements Runnable
    {
        @Override
        public void run() {
        s.connecting(ipAddress);
        }
    }
    private class intialization implements Runnable
    {
        @Override
        public void run() {
            initialization();
        }
    }


}
