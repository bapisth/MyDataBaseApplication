package com.esspl.hemendra.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.esspl.hemendra.mydatabaseapplication.R;

/**
 * Created by BAPI1 on 05-01-2016.
 */
public class ViewHolder2 extends RecyclerView.ViewHolder {
    private ImageView img;

    public ImageView getImg() {
        return img;
    }

    public void setImg(ImageView img) {
        this.img = img;
    }

    public ViewHolder2(View itemView) {
        super(itemView);
        this.img = (ImageView) itemView.findViewById(R.id.img);
    }
}
