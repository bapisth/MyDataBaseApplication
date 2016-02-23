package com.esspl.hemendra.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.esspl.hemendra.mydatabaseapplication.R;

/**
 * Created by BAPI1 on 05-01-2016.
 */
public class ViewHolder1 extends RecyclerView.ViewHolder {
    private TextView t1;
    private TextView t2;

    public ViewHolder1(View itemView) {
        super(itemView);
        this.t1 = (TextView)itemView.findViewById(R.id.t1);
        this.t2 = (TextView)itemView.findViewById(R.id.t2);
    }

    public TextView getT1() {
        return t1;
    }

    public void setT1(TextView t1) {
        this.t1 = t1;
    }

    public TextView getT2() {
        return t2;
    }

    public void setT2(TextView t2) {
        this.t2 = t2;
    }


}
