package com.example.moviedb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private TextView tv;
    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            String title = extras.getString("title");
            String url_img = extras.getString("url_img");
            tv = (TextView) findViewById(R.id.textViewTitle);
            AsyncBitmapDownloader bmpD = new AsyncBitmapDownloader(this);
            bmpD.execute(url_img);
            tv.setText(title);
        }
    }
}