package com.example.moviedb;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.provider.ContactsContract;
import android.text.Html;
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

import org.w3c.dom.Text;

import java.util.Vector;

public class MyAdapter extends BaseAdapter {

    private Context context;

    public MyAdapter(Context context){
        this.context = context;
    }

    Vector<String> vector = new Vector<String>();
    Vector<String> urls_images = new Vector<String>();
    Vector<String> urls_imagesBackground = new Vector<String>();
    Vector<Integer> ids = new Vector<Integer>();

    public void add(String title, String url, String imgBack, int idFilm){
        vector.add(title);
        urls_images.add(url);
        urls_imagesBackground.add(imgBack);
        ids.add(idFilm);
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
        Log.e("JLMZ51","A faire");
        @SuppressLint("ViewHolder") View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.textviewlayout, viewGroup,false);
        TextView tv = (TextView) v.findViewById(R.id.textViewInfo);
        ImageView img = (ImageView) v.findViewById(R.id.imageView);
        RequestQueue queue = MySingleton.getInstance(viewGroup.getContext()).getRequestQueue();
        Response.Listener<Bitmap> rep_Listener = response -> {
            Log.e("JLMZ51", "I got a Bitmap !");
            img.setImageBitmap(response);
        };
        ImageRequest request = new ImageRequest(
                urls_images.get(i).toString(),
                rep_Listener,
                600,
                600,null,Bitmap.Config.RGB_565,null);
        queue.add(request);
        tv.setText(vector.get(i).toString());
        LinearLayout linearLayout = (LinearLayout) v.findViewById(R.id.linearLayout);
        Log.e("JLMZ51", "END adapter de ses moooooooorts");
        linearLayout.setOnClickListener(clickInLinearLayout(vector.get(i), urls_imagesBackground.get(i),ids.get(i)));

        return v;
    }

    private View.OnClickListener clickInLinearLayout(String title, String url_imageBck, int id_film){
        return new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Log.e("JLMZ51","Click on titre : "+title);
                Intent i = new Intent(context,DetailActivity.class);
                i.putExtra("title",title);
                i.putExtra("url_img",url_imageBck);
                i.putExtra("id", id_film);
                v.getContext().startActivity(i);

            }
        };
    }

}
