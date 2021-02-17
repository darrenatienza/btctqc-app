package com.example.btctqc_app.adapters;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class ViewWrapper<V extends View> extends RecyclerView.ViewHolder   {

    private V view;

    public ViewWrapper(V itemView) {
        super(itemView);
        view = itemView;
    }

    public V getView() {
        return view;
    }

}
