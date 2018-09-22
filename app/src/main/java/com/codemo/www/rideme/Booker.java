package com.codemo.www.rideme;

/**
 * Created by aminda on 9/17/2018.
 */





        import android.app.AlertDialog;
        import android.app.Dialog;
        import android.app.ProgressDialog;
        import android.os.AsyncTask;
        import android.util.Log;
        import android.widget.Toast;

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

/**
 * Created by uwin5 on 05/20/18.
 */
public class Booker extends AsyncTask<String,Void,String []> {

    private MainActivity main;
    private ProgressDialog dialog;

    public Booker(MainActivity main){
        this.main =main;
    }


    @Override
    protected String[] doInBackground(String... params) {

        try {
            String name = params[0];
            String nic = params[1];
            String type = params[2];
            String pack = params[3];
            String location = params[4];
            String hours = params[5];
            String date = params[6];
            String rent = params[7];

            URL url = new URL(main.getApplicationContext().getResources().getString(R.string.IP_ADDRESS )+ main.getApplicationContext().getResources().getString(R.string.BOOK_BIKE));
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            String post_data=
                    URLEncoder.encode("name","UTF-8")+"="+URLEncoder.encode(name,"UTF-8")+"&"+
                    URLEncoder.encode("nic","UTF-8")+"="+URLEncoder.encode(nic,"UTF-8")+"&"+
                    URLEncoder.encode("type","UTF-8")+"="+URLEncoder.encode(type,"UTF-8")+"&"+
                    URLEncoder.encode("pack","UTF-8")+"="+URLEncoder.encode(pack,"UTF-8")+"&"+
                    URLEncoder.encode("location","UTF-8")+"="+URLEncoder.encode(location,"UTF-8")+"&"+
                    URLEncoder.encode("hours","UTF-8")+"="+URLEncoder.encode(hours,"UTF-8")+"&"+
                    URLEncoder.encode("date","UTF-8")+"="+URLEncoder.encode(date,"UTF-8")+"&"+
                    URLEncoder.encode("rent","UTF-8")+"="+URLEncoder.encode(rent,"UTF-8");
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
            return new String[]{main.getApplicationContext().getResources().getString(R.string.NO_INTERNET)};
        }

        return null;
    }


    @Override
    protected void onPreExecute() {
        dialog = new ProgressDialog(main);
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
            Log.d("result.................",result[0]);
            String obj = new String(result[0]);

            if(obj.equals("full")){
                Toast.makeText(main.getApplicationContext().getApplicationContext(),"No bicycles available !",Toast.LENGTH_LONG).show();
            }
            else if(obj.equals("network error" )){
                Toast.makeText(main.getApplicationContext().getApplicationContext(),"Network Error !",Toast.LENGTH_LONG).show();
            }
            else if(obj.length() <= 3){
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(main);
                alertDialogBuilder.setTitle("Booking Successful!");
                alertDialogBuilder.setMessage("Bicycle No is: "+obj).setCancelable(true);
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }else{
                Toast.makeText(main.getApplicationContext().getApplicationContext(),"Booking is not Successful!",Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }



    }

}

