package com.example.moviedb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private String API_TOKEN = "7172a8267c2ed5d17ca028017ddcb467";

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.editTextSearchMovie);
    }


    public void searchMovie(View view){
        String text = editText.getText().toString();
        Log.i("JLMZ51 : button search", text);
    }
}