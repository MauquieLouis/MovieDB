package com.example.moviedb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;

import org.w3c.dom.Text;


public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private String API_TOKEN = "7172a8267c2ed5d17ca028017ddcb467";
    private MyAdapter adapter;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.editTextSearchMovie);
        adapter = new MyAdapter(this.getApplicationContext());
        lv = (ListView) findViewById(R.id.list);
        lv.setAdapter(adapter);
    }


    public void searchMovie(View view){
        adapter.clear();
        String text = editText.getText().toString();
        Log.i("JLMZ51 : button search", text);
        String url = new String("https://api.themoviedb.org/3/search/movie?api_key=" + API_TOKEN + "&language=fr&query=" + text + "&page=1");
        //fonction get list films
        AsyncMovieDBJSONDataList task2 = new AsyncMovieDBJSONDataList(adapter);
        task2.execute(url);


    }

    public void accesFavoris(View view){
        Intent i = new Intent(MainActivity.this, FavorisActivity.class);
        startActivity(i);
    }
}