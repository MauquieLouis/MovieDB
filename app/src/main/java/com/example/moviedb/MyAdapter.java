package com.example.moviedb;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Vector;

public class MyAdapter extends BaseAdapter {

    private Context context;

    public MyAdapter(Context context){
        this.context = context;
    }

    Vector<String> vector = new Vector<String>();
    Vector<String> urls_images = new Vector<String>();

    public void add(String url){
        vector.add(url);
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
        tv.setText(vector.get(i).toString());
        LinearLayout linearLayout = (LinearLayout) v.findViewById(R.id.linearLayout);
        Log.e("JLMZ51", "END adapter de ses moooooooorts");
        linearLayout.setOnClickListener(clickInLinearLayout(vector.get(i)));
        return v;
    }

    private View.OnClickListener clickInLinearLayout(String title){
        return new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Log.e("JLMZ51","Click on titre : "+title);
                Intent i = new Intent(context,DetailActivity.class);
                i.putExtra("title",title);
                v.getContext().startActivity(i);

            }
        };
    }

}
