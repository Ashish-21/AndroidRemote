package com.example.ashish.vlcremote;

import android.os.StrictMode;
import android.util.Log;

import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Ashish on 3/21/2018.
 */

class SendData {
    String ipAddress;
    Socket client;
    PrintWriter out;
    String Data;
    int SDK_INT = android.os.Build.VERSION.SDK_INT;
    public void connecting(String ip)
    {
        ipAddress=ip;
        Log.i("IP",ip);
        Thread myThread=new Thread(new Runnable() {
            @Override
            public void run() {
                if (SDK_INT > 8)
                {
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                            .permitAll().build();
                    StrictMode.setThreadPolicy(policy);
                    //your codes here
                }
                try {
                    client = new Socket(ipAddress,9099);
                }
                catch (Exception e)
                {
                    String msg=e.toString();
                    Log.i("Error",msg);
                }
            }
        });
        myThread.start();
    }
    public void  sendRequest(String data)
    {
        Data=data;
        Thread Thread1=new Thread(new myThread());
        Thread1.start();
    }
    class myThread implements Runnable
    {
        @Override
        public void run() {
            try
            {
                out=new PrintWriter(client.getOutputStream(),true);
                out.println(Data);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
