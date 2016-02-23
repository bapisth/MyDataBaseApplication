package com.esspl.hemendra.util;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.esspl.hemendra.mydaogenerator.Person;
import com.esspl.hemendra.mydatabaseapplication.R;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by BAPI1 on 14-12-2015.
 */
public class PersonListAdapter extends BaseAdapter {

    private Context context;
    private List<Person> personList;
    private static LayoutInflater inflater;
    private Person person;
    private static final String TAG = "PersonListAdapter";

    public PersonListAdapter(Context context, List<Person> personList) {
        this.context = context;
        this.personList = personList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }




    @Override
    public int getCount() {
        return personList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d(TAG, "Data Size"+personList.size());
        ViewHolder holder = null;
        View view =convertView;
        if(convertView == null){
            Log.d(TAG, "getView: convertView is null..........................");
            view = inflater.inflate(R.layout.person_list_layout,null);
            holder  = new ViewHolder();
            holder.fname = (TextView) view.findViewById(R.id.lfname);
            Log.d(TAG, "getView: holder.fname"+holder.fname);
            holder.mname = (TextView) view.findViewById(R.id.lmname);
            holder.lname = (TextView) view.findViewById(R.id.llname);
            holder.email = (TextView) view.findViewById(R.id.lemail);
            holder.phone = (TextView) view.findViewById(R.id.lphone);
            holder.address = (TextView) view.findViewById(R.id.laddress);

            view.setTag(holder);
        }else
            holder = (ViewHolder) view.getTag();

        if (personList.size() >0){
            person = personList.get(position);
            if(holder.fname!=null){
                Log.d(TAG, "getView: Holder is not null");
            }else
                Log.d(TAG, "getView: Holder is null");
            if (person!=null){
                Log.d(TAG, "getView: "+person.getFname());
            holder.fname.setText(String.valueOf(person.getFname()));
            holder.mname.setText(person.getMname().toString());
            holder.lname.setText(person.getLname().toString());
            holder.email.setText(person.getEmail().toString());
            holder.phone.setText(person.getPhone().toString());
            holder.address.setText(person.getAddress().toString());
            }else
                Log.d(TAG, "Something went wrong!!");
        }


        return view;
    }

    public class ViewHolder{
        public TextView fname;
        public TextView mname;
        public TextView lname;
        public TextView email;
        public TextView phone;
        public TextView address;

    }
}
