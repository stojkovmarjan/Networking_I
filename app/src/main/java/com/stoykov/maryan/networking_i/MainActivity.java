package com.stoykov.maryan.networking_i;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (IsNetworkConnected()){
            //ShowAlertDialog("CONNECTION","You are connected!");
            progressDialog=new ProgressDialog(this);
            progressDialog.setMessage("Please wait...");
            progressDialog.setCancelable(false);
            progressDialog.show();
                if (IsWiFiConnected()){
                    //ShowAlertDialog("CONNECTION","WiFi connection detected!");

                    startDownload();

                } else{
                   // ShowAlertDialog("CONNECTION","Mobile Data Connection detected!");
                }
        } else {
            ShowAlertDialog("CONNECTION","You are not connected!");
        }


    }

    boolean IsNetworkConnected(){// network connection
        ConnectivityManager connectivityManager=(ConnectivityManager)
                                                    getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
        return ( networkInfo !=null && networkInfo.isConnected() );
    }
    boolean IsWiFiConnected(){// type of connection (wifi/mobile)
        ConnectivityManager connectivityManager=(ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
        return ( networkInfo !=null &&(connectivityManager.TYPE_WIFI==networkInfo.getType())&& networkInfo.isConnected() );
    }

    void ShowAlertDialog(String titleMsg,String alertMsg){
        AlertDialog.Builder alertDialogBuilder=new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle(titleMsg);
        alertDialogBuilder.setMessage(alertMsg);
        alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        alertDialogBuilder.show();
    }

    void startDownload(){
    //new DownloadRepoTask(params[0]);
    new DownloadRepoTask().execute("https://api.github.com/repositories");

    }

}
