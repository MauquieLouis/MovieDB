package com.example.moviedb;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Vector;

public class MyAdapter extends BaseAdapter {


    Vector<String> vector = new Vector<String>();

    public void add(String url){
        vector.add(url);
    }

    @Override
    public int getCount() {
        return this.vector.size();
    }
    @Override
    public Object getItem(int position) {
        return null;
    }
    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Log.e("JLMZ51","A faire");
        @SuppressLint("ViewHolder") View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_main, viewGroup,false);
        TextView tv = (TextView) v.findViewById(R.id.textView);
        tv.setText(vector.get(i).toString());
        Log.e("JLMZ51", "END adapter de ses moooooooorts");
        return v;
    }

}
