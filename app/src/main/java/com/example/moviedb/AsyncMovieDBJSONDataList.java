package com.example.moviedb;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class AsyncMovieDBJSONDataList extends AsyncTask<String, Void, JSONObject> {

    private MyAdapter adapter;

    public AsyncMovieDBJSONDataList(MyAdapter adapter){
        this.adapter = adapter;
    }


    @Override
    protected JSONObject doInBackground(String... strings) {
        URL url = null;
        HttpURLConnection urlConnection = null;
        String result = null;
        try {
            url = new URL(strings[0]);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        InputStream in = null;
        try {
            in = new BufferedInputStream(urlConnection.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        //result = readStream(in);
        try {
            result = convertInputStreamToString(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Log.i("e","JLMZ51 : "+result);
        urlConnection.disconnect();
        JSONObject json = null;
        try {
            json = new JSONObject(result);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }

    protected void onPostExecute(JSONObject jsonObj){
        Log.e("JLMZ51","Test IF jsonObject is NULLLLLLL ???????");
        if(jsonObj != null){
            Log.i("JLMZ51", "JSON data (in on PostExecute) : "+jsonObj);
            try{
                Log.e("JLMZ51", "In Le try frérot");
                JSONArray array = jsonObj.getJSONArray("results");
                for(int i =0; i<array.length();i++){
                    String title = (String) array.getJSONObject(i).get("original_title");
                    Log.i("JLMZ51", " Adding to adapter title : "+ title);
                    adapter.add(title);
                }
                adapter.notifyDataSetChanged();
                Log.e("JLMZ51", "count : "+adapter.getCount());
                Log.e("JLMZ51", "Maybe le try succed");

            } catch (JSONException e) {
                Log.e("JLMZ51","TRY FAILEEEEDD !");
                e.printStackTrace();
            }
        }else
        {
            Log.e("JLMZ51","The JSON Data are fucking nullllllllllllllllllll !!!!!!");
        }
    }

    private String convertInputStreamToString(InputStream is) throws IOException {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[16777216];
        int length;
        while ((length = is.read(buffer)) != -1) {
            result.write(buffer, 0, length);
        }

        // Java 1.1
        return result.toString(StandardCharsets.UTF_8.name());

        // Java 10
        // return result.toString(StandardCharsets.UTF_8);

    }
}
