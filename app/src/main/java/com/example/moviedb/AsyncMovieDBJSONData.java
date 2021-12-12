package com.example.moviedb;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

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

public class AsyncMovieDBJSONData extends AsyncTask<String, Void, JSONObject> {

    private DetailActivity activity;
    public AsyncMovieDBJSONData(DetailActivity DetailActivity){
        this.activity = DetailActivity;
    }

    @Override
    protected  JSONObject doInBackground(String... strings){
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

    @Override
    protected void onPostExecute(JSONObject jsonObject) {

        TextView tvDate = (TextView) activity.findViewById(R.id.textViewDate);
        TextView tvDescFilm = (TextView) activity.findViewById(R.id.textViewDescriptionFilm);
        TextView tvNote = (TextView) activity.findViewById(R.id.textViewNote);
        try {
            String date = (String) jsonObject.get("release_date");
            tvDate.setText(date);
        }catch (JSONException e){

        }
        try {
            Double vote = (Double) jsonObject.get("vote_average");
            tvNote.setText(vote+"/10");
        }catch (JSONException e){
        }
        try {
            String overview = (String) jsonObject.get("overview");
            tvDescFilm.setText(overview);
        }catch (JSONException e){

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
