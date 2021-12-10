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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoris);

        ListView lv = (ListView) findViewById(R.id.listFavoris);
        List<String> array = new ArrayList<String>();

        MovieDbHelper dbHelper = new MovieDbHelper(getApplicationContext());
        SQLiteDatabase dbr = dbHelper.getReadableDatabase();

        Cursor cursor = dbr.rawQuery("SELECT * FROM "+ MovieContract.MovieEntry.TABLE_NAME,null);
        List itemsIds = new ArrayList<>();
        List<Integer> titres = new ArrayList<Integer>();
        while(cursor.moveToNext()){
            long itemId = cursor.getLong(
                    cursor.getColumnIndexOrThrow("_idINTEGER")
            );
            itemsIds.add(itemId);
            int idFilm = cursor.getInt(
                    cursor.getColumnIndexOrThrow(MovieContract.MovieEntry.COLUMN_NAME_ID_FILM)
            );
            titres.add(idFilm);
        }
        cursor.close();

        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<Integer>(
          this,
          android.R.layout.simple_list_item_1,
          titres
        );
        lv.setAdapter(arrayAdapter);
    }
}