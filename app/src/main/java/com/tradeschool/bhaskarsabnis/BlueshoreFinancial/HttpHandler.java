package com.tradeschool.bhaskarsabnis.BlueshoreFinancial;

/**
 * Created by bhaskarsabnis on 08/01/19.
 */

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class HttpHandler extends AsyncTask<String, String, String> {

    private static final String TAG = HttpHandler.class.getSimpleName();
    String response = null;
    public static String str;
    JSONObject myResponse;
    @Override
    protected String doInBackground(String... strings) {

        try {
            URL url = new URL(strings[0]);
            Log.i("URL : ",strings[0]);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            // read the response
            InputStream in = new BufferedInputStream(conn.getInputStream());
            response = convertStreamToString(in);
            Log.i("URLL : ",strings[0]);
            // myResponse = new JSONObject(response.toString());
        } catch (MalformedURLException e) {
            Log.e(TAG, "MalformedURLException: " + e.getMessage());
        } catch (ProtocolException e) {
            Log.e(TAG, "ProtocolException: " + e.getMessage());
        } catch (IOException e) {
            Log.e(TAG, "IOException: " + e.getMessage());
        } catch (Exception e) {
            Log.e(TAG, "Exception: " + e.getMessage());
        }
        Log.i("Response :",response);
        return response;
    }

    @Override
    protected void onPostExecute(String result){
        try {
            myResponse = new JSONObject(result);
            JSONArray jArray = myResponse.getJSONArray("data");
            str = jArray.getJSONObject(0).getString("WL_ID");
            Log.i("Output ",jArray.getJSONObject(0).getString("WL_ID"));
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    private String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                Log.i("LINE",line);
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Log.i("MyRes",sb.toString());
        return sb.toString();
    }
}