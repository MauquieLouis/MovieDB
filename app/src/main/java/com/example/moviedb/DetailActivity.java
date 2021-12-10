package com.example.moviedb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private TextView tv;
    private ImageView img;
    private int idFilm;
    private SQLiteDatabase db;
    private SQLiteDatabase dbr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            String title = extras.getString("title");
            String url_img = extras.getString("url_img");
            idFilm = extras.getInt("id");
            tv = (TextView) findViewById(R.id.textViewTitle);
            AsyncBitmapDownloader bmpD = new AsyncBitmapDownloader(this);
            bmpD.execute(url_img);
            tv.setText(title);
        }

        MovieDbHelper dbHelper = new MovieDbHelper(getApplicationContext());
        db = dbHelper.getWritableDatabase();
        dbr = dbHelper.getReadableDatabase();
    }

    public void addFavorite(View view){
        ContentValues values = new ContentValues();
        values.put(MovieContract.MovieEntry.COLUMN_NAME_ID_FILM, idFilm);
        long newRowId = db.insert(MovieContract.MovieEntry.TABLE_NAME, null, values);
    }

    public void accesFavoris(View view){
        Intent i = new Intent(DetailActivity.this, FavorisActivity.class);
        startActivity(i);
    }
}