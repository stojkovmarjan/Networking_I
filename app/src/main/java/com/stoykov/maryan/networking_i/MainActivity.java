package com.stoykov.maryan.networking_i;

import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (IsNetworkConnected()){
            ShowAlertDialog("CONNECTION","You are connected!");
        } else {
            ShowAlertDialog("CONNECTION","You are not connected!");
        }
    }

    boolean IsNetworkConnected(){
        ConnectivityManager connectivityManager=(ConnectivityManager)
                                                    getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
        return ( networkInfo !=null && networkInfo.isConnected() );
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
}
