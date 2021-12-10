package com.example.moviedb;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class AsyncBitmapDownloader extends AsyncTask<String, Void, Bitmap> {

    private AppCompatActivity myActiviy;

    public AsyncBitmapDownloader(AppCompatActivity myActiviy_){
        myActiviy = myActiviy_;
    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        URL url = null;
        HttpURLConnection urlConnection = null;
        Bitmap bm = null;
        try {
            url = new URL(strings[0]);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            urlConnection = (HttpURLConnection) url.openConnection(); // Open
        } catch (IOException e) {
            e.printStackTrace();
        }
        InputStream in = null; // Stream
        try {
            in = new BufferedInputStream(urlConnection.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        bm = BitmapFactory.decodeStream(in);
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        urlConnection.disconnect();
        return bm;
    }

    protected void onPostExecute(Bitmap bitmap){
        ImageView image = (ImageView) myActiviy.findViewById(R.id.imageViewBackground);
        image.setImageBitmap(bitmap);
    }
}
