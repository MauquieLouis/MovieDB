package com.example.moviedb;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;



public class FavorisAdapter extends BaseAdapter {

    private Context context;
    private SQLiteDatabase db;
    private SQLiteDatabase dbr;
    private int count;

    public FavorisAdapter(Context context){
        this.context = context;
    }

    Vector<String> vector = new Vector<String>();
    Vector<String> urls_images = new Vector<String>();
    Vector<String> urls_imagesBackground = new Vector<String>();
    Vector<Integer> ids = new Vector<Integer>();

    /*public void add(String title, String url, String imgBack, int idFilm){
        vector.add(title);
        urls_images.add(url);
        urls_imagesBackground.add(imgBack);
        ids.add(idFilm);
    }*/
    public void readDatabase(){
        MovieDbHelper dbHelper = new MovieDbHelper(context);
        db = dbHelper.getWritableDatabase();
        dbr = dbHelper.getReadableDatabase();
        Cursor cursor = dbr.rawQuery("SELECT * FROM "+ MovieContract.MovieEntry.TABLE_NAME,null);
        count = cursor.getCount();
        cursor.close();
        Cursor cursor2 = dbr.rawQuery("SELECT * FROM "+ MovieContract.MovieEntry.TABLE_NAME,null);
        List itemsIds = new ArrayList<>();
        while(cursor2.moveToNext()){
            long itemId = cursor2.getLong(
                    cursor2.getColumnIndexOrThrow(MovieContract.MovieEntry._ID)
            );
            itemsIds.add(itemId);
            ids.add(cursor2.getInt(
                    cursor2.getColumnIndexOrThrow(MovieContract.MovieEntry.COLUMN_NAME_ID_FILM)
            ));
            vector.add(cursor2.getString(
                    cursor2.getColumnIndexOrThrow(MovieContract.MovieEntry.COLUMN_NAME_TITRE_FILM)
            ));
            urls_images.add(cursor2.getString(
                    cursor2.getColumnIndexOrThrow(MovieContract.MovieEntry.COLUMN_NAME_IMG_FILM)
            ));
            urls_imagesBackground.add(cursor2.getString(
                    cursor2.getColumnIndexOrThrow(MovieContract.MovieEntry.COLUMN_NAME_IMGBCK_FILM)
            ));
        }
        Log.e("JLMZ51","image : "+urls_images.get(0));
        Log.e("JLMZ51","imageBck : "+urls_imagesBackground.get(0));
        Log.e("JLMZ51","Titre : "+vector.get(0));
        cursor2.close();
    }

    @Override
    public int getCount() {
        return this.vector.size();
    }
    @Override
    public Object getItem(int position) {
        return this.vector.get(position);
    }
    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Log.e("JLMZ51","A faire Favorissssssss");
        @SuppressLint("ViewHolder") View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.textviewlayout, viewGroup,false);
        TextView tv = (TextView) v.findViewById(R.id.textViewInfo);
        ImageView img = (ImageView) v.findViewById(R.id.imageView);
        RequestQueue queue = MySingleton.getInstance(viewGroup.getContext()).getRequestQueue();
        Response.Listener<Bitmap> rep_Listener = response -> {
            Log.e("JLMZ51", "I got a Bitmap !");
            img.setImageBitmap(response);
        };
        ImageRequest request = new ImageRequest(
                urls_images.get(i),
                rep_Listener,
                600,
                600,null,Bitmap.Config.RGB_565,null);
        queue.add(request);
        tv.setText(vector.get(i));
        LinearLayout linearLayout = (LinearLayout) v.findViewById(R.id.linearLayout);
        Log.e("JLMZ51", "END adapter de ses moooooooorts");
        linearLayout.setOnClickListener(clickInLinearLayout(vector.get(i), urls_imagesBackground.get(i),ids.get(i), urls_images.get(i)));

        return v;
    }

    private View.OnClickListener clickInLinearLayout(String title, String url_imageBck, int id_film, String url_image){
        return new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Log.e("JLMZ51","Click on titre : "+title);
                Intent i = new Intent(context,DetailActivity.class);
                i.putExtra("title",title);
                i.putExtra("url_imgbck",url_imageBck);
                i.putExtra("id", id_film);
                i.putExtra("url_img", url_image);
                v.getContext().startActivity(i);

            }
        };
    }
}
