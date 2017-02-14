package com.stoykov.maryan.networking_i;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Maryan on 12.2.2017.
 */

public class DownloadRepoTask extends AsyncTask<String, String, String> {

    @Override
    protected String doInBackground(String... strings) {

        try {
            return downloadData(strings[0]);
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }

    }


    @Override
    protected void onPostExecute(String result){

    }

    private String downloadData(String urlString) throws IOException {
        InputStream inputStream=null;
        try {
            URL url=new URL(urlString);
            HttpURLConnection httpURLConnection=(HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();
            inputStream=httpURLConnection.getInputStream();
            return convertToString(inputStream);
        } finally {
            if (inputStream!=null){
                inputStream.close();
            }
        }
      //return null;
    }

    private String convertToString(InputStream inputStream) throws IOException{
        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder total=new StringBuilder();
        String line;
        while ((line=bufferedReader.readLine())!=null){
            total.append(line);
        }
        return new String(total);
    }
}
