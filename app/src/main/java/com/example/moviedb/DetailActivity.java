package com.example.moviedb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            String title = extras.getString("title");
            tv = (TextView) findViewById(R.id.textViewTitle);
            tv.setText(title);
        }
    }
}