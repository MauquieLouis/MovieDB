package com.example.moviedb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    private TextView tv;
    private ImageView img;
    private int idFilm;
    private String title;
    private String url_imgP;
    private String url_imgbck;
    private SQLiteDatabase db;
    private SQLiteDatabase dbr;
    private boolean favorite;
    private Button favoriteButton;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        context = getApplicationContext();
        favoriteButton = (Button) findViewById(R.id.buttonfavorite);

        int id_film = 0;
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            title = extras.getString("title");
            url_imgbck = extras.getString("url_imgbck");
            url_imgP = extras.getString("url_img");
            id_film = extras.getInt("id");
            idFilm = extras.getInt("id");
            tv = (TextView) findViewById(R.id.textViewTitle);
            Log.e("JLMZ51","le fameux lien : "+url_imgbck);
            if(url_imgbck != null){
                AsyncBitmapDownloader bmpD = new AsyncBitmapDownloader(this);
                bmpD.execute(url_imgbck);
            }
            tv.setText(title);
        }

        MovieDbHelper dbHelper = new MovieDbHelper(getApplicationContext());
        db = dbHelper.getWritableDatabase();
        dbr = dbHelper.getReadableDatabase();
        //Si l'identifiant du film n'est pas null
        if(id_film != 0){
            Log.e("JLMZ51 Detail","ID du film bien != 0");
            String url = new String("https://api.themoviedb.org/3/movie/"+id_film+"?api_key=7172a8267c2ed5d17ca028017ddcb467&language=fr");
            AsyncMovieDBJSONData task2 = new AsyncMovieDBJSONData(DetailActivity.this);
            task2.execute(url);
        }


        Cursor cursor = dbr.rawQuery("SELECT * FROM "+ MovieContract.MovieEntry.TABLE_NAME +" WHERE "+MovieContract.MovieEntry.COLUMN_NAME_ID_FILM+" = "+id_film ,null);
        if(cursor.getCount() <= 0){
            Log.e("JLMZ51","LE FIIIIIIIIIIIIIIILM n'EsT pAs DaNs LeS FaVoRiS !:!:!");
            //Afficher le bouton ajouter aux favoris
            favorite = false;
            favoriteButton.setText("Ajouter aux favoris");
        }else{
            Log.e("JLMZ51","LE FIIIIIIIIIIIIIIILM EsT DeJa DaNs LeS FaVoRiS !:!:!");
            //Afficher le bouton supprimer des favoris
            favorite = true;
            favoriteButton.setText("Enlever des favoris");
        }
        cursor.close();
        Log.e("JLMZ51","Film id : "+id_film+" Deuxieme version id : "+idFilm);
    }

    public void addFavorite(View view){
        ContentValues values = new ContentValues();
        values.put(MovieContract.MovieEntry.COLUMN_NAME_ID_FILM, idFilm);
        values.put(MovieContract.MovieEntry.COLUMN_NAME_TITRE_FILM, title);
        values.put(MovieContract.MovieEntry.COLUMN_NAME_IMG_FILM,url_imgP);
        values.put(MovieContract.MovieEntry.COLUMN_NAME_IMGBCK_FILM,url_imgbck);
        long newRowId = db.insert(MovieContract.MovieEntry.TABLE_NAME, null, values);
    }
    public void toggleFavorite(View view){
        if(favorite){
            //Enlver des favoris + changer le texte du button
            favorite = false;
            String selection= MovieContract.MovieEntry.COLUMN_NAME_ID_FILM+" = ?";
            String[] selectionArgs = {""+idFilm};
            Log.e("JLMZ51", selectionArgs[0]);
            int deletedRows = db.delete(MovieContract.MovieEntry.TABLE_NAME, selection,selectionArgs);
            favoriteButton.setText("Ajouter aux favoris");
            CharSequence text = "Film retiré des favoris !";
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }else{
            //Ajouter aux favoris + changer texte button.
            favorite = true;
            ContentValues values = new ContentValues();
            values.put(MovieContract.MovieEntry.COLUMN_NAME_ID_FILM, idFilm);
            values.put(MovieContract.MovieEntry.COLUMN_NAME_TITRE_FILM, title);
            values.put(MovieContract.MovieEntry.COLUMN_NAME_IMG_FILM,url_imgP);
            values.put(MovieContract.MovieEntry.COLUMN_NAME_IMGBCK_FILM,url_imgbck);
            long newRowId = db.insert(MovieContract.MovieEntry.TABLE_NAME, null, values);
            favoriteButton.setText("Enlever des favoris");
            CharSequence text = "Film ajouté aux favoris !";
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }

    }

    public void accesFavoris(View view){

        Intent i = new Intent(DetailActivity.this, FavorisActivity.class);
        startActivity(i);
    }
}