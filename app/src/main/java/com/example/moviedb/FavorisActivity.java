package com.example.moviedb;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;



public class FavorisActivity extends AppCompatActivity {

    private FavorisAdapter adapter;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoris);

        //ListView lv = (ListView) findViewById(R.id.listFavoris);
        //List<String> array = new ArrayList<String>();

        adapter = new FavorisAdapter(this.getApplicationContext());
        adapter.readDatabase();
        lv = (ListView) findViewById(R.id.listFavoris);
        lv.setAdapter(adapter);

        MovieDbHelper dbHelper = new MovieDbHelper(getApplicationContext());
        SQLiteDatabase dbr = dbHelper.getReadableDatabase();

        /*Cursor cursor = dbr.rawQuery("SELECT * FROM "+ MovieContract.MovieEntry.TABLE_NAME,null);
        List itemsIds = new ArrayList<>();
        List<Integer> filmsId = new ArrayList<Integer>();
        List<String> filmsTitle = new ArrayList<String>();
        List<String> filmsImg = new ArrayList<String>();
        List<String> filmsImgBck = new ArrayList<String>();
        while(cursor.moveToNext()){
            long itemId = cursor.getLong(
                    cursor.getColumnIndexOrThrow(MovieContract.MovieEntry._ID)
            );
            itemsIds.add(itemId);
            int idFilm = cursor.getInt(
                    cursor.getColumnIndexOrThrow(MovieContract.MovieEntry.COLUMN_NAME_ID_FILM)
            );
            String titleFilm = cursor.getString(
                    cursor.getColumnIndexOrThrow(MovieContract.MovieEntry.COLUMN_NAME_TITRE_FILM)
            );
            String url_img = cursor.getString(
                    cursor.getColumnIndexOrThrow(MovieContract.MovieEntry.COLUMN_NAME_IMG_FILM)
            );
            String url_imgbck = cursor.getString(
                    cursor.getColumnIndexOrThrow(MovieContract.MovieEntry.COLUMN_NAME_IMGBCK_FILM)
            );
            filmsId.add(idFilm);
            filmsTitle.add(titleFilm);
            filmsImg.add(url_img);
            filmsImgBck.add(url_imgbck);
        }
        cursor.close();

        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<Integer>(
          this,
            android.R.layout.simple_list_item_1,
            filmsId
        );
        lv.setAdapter(arrayAdapter);*/
    }
}