package com.codemo.www.rideme;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.codemo.www.rideme.Fragments.HistoryFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by aminda on 9/18/2018.
 */

public class FetchHistory extends AsyncTask<String,Void,String []> {

    private HistoryFragment main;
    private ProgressDialog dialog;

    public FetchHistory(HistoryFragment main){
        this.main =main;
    }


    @Override
    protected String[] doInBackground(String... params) {

        try {

            String nic = params[0];

            URL url = new URL(main.getContext().getResources().getString(R.string.IP_ADDRESS )+ main.getContext().getResources().getString(R.string.HISTORY));
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            String post_data= URLEncoder.encode("nic","UTF-8")+"="+URLEncoder.encode(nic,"UTF-8");
            bufferedWriter.write(post_data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
            String result = "";
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                result += line;
            }

            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();

            return new String[]{result, params[0]};
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            return new String[]{main.getContext().getResources().getString(R.string.NO_INTERNET)};
        }

        return null;
    }


    @Override
    protected void onPreExecute() {
        dialog = new ProgressDialog(main.getContext());
        this.dialog.setMessage("Please Wait !");
        this.dialog.show();

    }

    @Override
    protected void onPostExecute(String[] result) {
        super.onPostExecute(result);
        if (dialog.isShowing()) {
            dialog.dismiss();
        }

        try {
            JSONArray obj = new JSONArray(result[0]);
            ArrayList<Booking> bList = new ArrayList<>();
            for (int i = 0; i< obj.length(); i++) {
                JSONObject jo = (JSONObject) obj.get(i);
                String pack  = (String) jo.get("pack");
                String date = (String) jo.get("date");
                String rent = (String) jo.get("rent");
                String hours = (String) jo.get("hours");
                String type = (String) jo.get("type");
                Booking b = new Booking(date, pack, rent, type, hours);
                bList.add(b);
            }

            if(obj.length() > 0){
                main.updateHistory(bList);
            }else{
                Toast.makeText(main.getContext(), "No user history!", Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ClassCastException e) {
            e.printStackTrace();
        }



    }

}