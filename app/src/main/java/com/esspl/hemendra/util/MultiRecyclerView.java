package com.esspl.hemendra.util;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by BAPI1 on 05-01-2016.
 */
public class MultiRecyclerView extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Object> listdata;

    private final int TEXTS=0, IMG=1;

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    public MultiRecyclerView(Context context, List<Object> listdata) {
        this.listdata = listdata;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }
}
