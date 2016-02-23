package com.esspl.hemendra.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

//import com.esspl.hemendra.drawables.TextDrawable;
import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.esspl.hemendra.mydaogenerator.Person;
import com.esspl.hemendra.mydatabaseapplication.R;

import java.util.List;

/**
 * Created by BAPI1 on 02-01-2016.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private final String TAG = "RecyclerViewAdapter";
    public LayoutInflater inflater;
    public Context context;
    List<Person> personList;
    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_layout,null);
        View view = inflater.inflate(R.layout.recycler_view_layout,null);
        MyViewHolder holder = new MyViewHolder(view);
        Log.d("RecyclerViewAdapter", "onCreateViewHolder: "+holder);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.MyViewHolder holder, int position) {
        Person person = personList.get(position);

        if (person!=null){
            holder.person_name.setText(person.getFname()!=null?person.getFname():"");
            holder.person_age.setText(String.valueOf(Integer.parseInt("24")+position).toString());
            holder.person_address.setText("Bhubaneswar" + position);
            holder.person_email.setText(person.getEmail() != null ? person.getEmail() : "");
            holder.person_id.setText(person.getId().toString());

            //TextDrawable textDrawable = new TextDrawable(context.getResources(), person.getFname().substring(0,1).toUpperCase());

            com.amulyakhare.textdrawable.TextDrawable textDrawable = TextDrawable.builder()
                                                                .buildRound(person.getFname().substring(0,1).toUpperCase(),
                                                                        ColorGenerator.DEFAULT.getRandomColor());
            holder.contactLetterIcon.setImageDrawable(textDrawable);
        }
    }

    @Override
    public int getItemCount() {
        Log.d("RecyclerViewAdapter", String.valueOf("========="+personList.size()));
        return personList.size();
    }

    public RecyclerViewAdapter(Context context, List<Person> personList) {
        this.context = context;
        this.personList = personList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView person_name;
        public TextView person_age;
        public TextView person_address;
        public TextView person_email;
        public TextView person_id;
        public ImageView delete;
        public ImageView contactLetterIcon;
        public MyViewHolder(View itemView) {
            super(itemView);
            this.person_name = (TextView)itemView.findViewById(R.id.person_name);
            this.person_age = (TextView) itemView.findViewById(R.id.person_age);
            this.person_address = (TextView) itemView.findViewById(R.id.person_address);
            this.person_email = (TextView) itemView.findViewById(R.id.email);
            this.person_id = (TextView) itemView.findViewById(R.id.person_id);
            this.delete = (ImageView) itemView.findViewById(R.id.delete);
            this.contactLetterIcon = (ImageView) itemView.findViewById(R.id.contactLetterIcon);
        }
    }

    public void removeItem(int position){
        Log.d(TAG, "removeItem: "+position);
        personList.remove(position);
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }
}
