package com.esspl.hemendra.mydatabaseapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.esspl.hemendra.mydaogenerator.Person;
import com.esspl.hemendra.mydaogenerator.dao.DaoSession;
import com.esspl.hemendra.mydaogenerator.dao.PersonDao;

import java.util.List;

import de.greenrobot.dao.query.Query;
import de.greenrobot.dao.query.QueryBuilder;
import de.greenrobot.dao.query.WhereCondition;

/**
 * Created by BAPI1 on 03-01-2016.
 */
public class EditContactActivity extends AppCompatActivity {


    private final String TAG = "EditContactActivity";
    private String fnam;
    private String lnam;
    private String mnam;
    private String email;
    private String phone;
    private String address;

    private TextView fname;
    private TextView lname;
    private TextView mname;
    private TextView emailText;
    private TextView phoneText;
    private TextView addressText;

    private Button updateData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_contact_layout);

        Bundle bundle = getIntent().getExtras();
        final int personId = bundle.getInt("personId",0);
        Log.d(TAG, "onCreate: personID:"+personId);
        if (personId!=0){
            DaoSession daoSession = DBInitializer.getNewDaoSession(EditContactActivity.this);
            PersonDao personDao = daoSession.getPersonDao();
            List<Person> persons =  personDao.queryBuilder().where(PersonDao.Properties.Id.eq(personId)).list();
            for (Person person:persons){
                if ((person.getId()!=null?person.getId():-1) == personId){
                    fnam = person.getFname()!=null ? person.getFname().toString():"";
                    mnam = person.getLname()!=null ? person.getLname().toString():"";
                    lnam = person.getMname()!=null ? person.getMname().toString():"";
                    email = person.getEmail() != null ? person.getEmail().toString():"";
                    phone = person.getPhone()!=null  ? person.getPhone().toString():"";
                    address = person.getAddress()!=null  ? person.getAddress().toString():"";
                }
            }
            fname = (TextView) findViewById(R.id.fname);
            mname = (TextView) findViewById(R.id.mname);
            lname = (TextView) findViewById(R.id.lname);
            emailText = (TextView) findViewById(R.id.email);
            phoneText = (TextView) findViewById(R.id.phone);
            addressText = (TextView) findViewById(R.id.address);
            updateData = (Button) findViewById(R.id.updateData);

            fname.setText(fnam);
            mname.setText(mnam);
            lname.setText(lnam);
            emailText.setText(email);
            phoneText.setText(phone);
            addressText.setText(address);

            updateData.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DaoSession daoSession = DBInitializer.getNewDaoSession(EditContactActivity.this);
                    insertPersonData(daoSession, personId);
                    DBInitializer.closeDbSesion();
                }
            });


        }
    }

    private void insertPersonData(DaoSession daoSession, int personId) {
        Person person = new Person();
        person.setFname(fname.getText().toString());
        person.setMname(mname.getText().toString());
        person.setLname(lname.getText().toString());
        person.setEmail(emailText.getText().toString());
        person.setPhone(Long.valueOf(phoneText.getText().toString()));
        person.setAddress(addressText.getText().toString());
        person.setId(Long.valueOf(personId));

        PersonDao personDao = daoSession.getPersonDao();
        long x=personDao.insertOrReplace(person);
        Log.d("MainActivity", "X="+x);

        if (x>1) Toast.makeText(EditContactActivity.this, "Data Update Successfully....", Toast.LENGTH_SHORT).show();

    }
}
