package com.example.tp_game;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class CustomAdapter extends BaseAdapter {
    private int[] imagesPhoto;
    private Context context;
    private LayoutInflater layoutInflater;

    public CustomAdapter(int[] imagesPhoto, Context context) {
        this.imagesPhoto = imagesPhoto;
        this.context = context;
        this.layoutInflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return imagesPhoto.length;
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
        if(view == null) {
            view = layoutInflater.inflate(R.layout.row_items, viewGroup, false);
        }
        ImageView imageView = view.findViewById(R.id.imageView1);
        imageView.setImageResource(imagesPhoto[i]);
        return view;
    }
}