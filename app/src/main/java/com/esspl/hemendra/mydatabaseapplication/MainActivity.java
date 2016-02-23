package com.esspl.hemendra.mydatabaseapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.esspl.hemendra.mydaogenerator.Person;
import com.esspl.hemendra.mydaogenerator.dao.DaoSession;
import com.esspl.hemendra.mydaogenerator.dao.PersonDao;

public class MainActivity extends AppCompatActivity {

    private EditText fname;
    private EditText mname;
    private EditText lname;
    private EditText email;
    private EditText phone;
    private EditText address;
    private Button save;
    private Button savedData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Initializing the views
        fname = (EditText) findViewById(R.id.fname);
        mname = (EditText) findViewById(R.id.mname);
        lname = (EditText) findViewById(R.id.lname);
        email = (EditText) findViewById(R.id.email);
        phone = (EditText) findViewById(R.id.phone);
        address = (EditText) findViewById(R.id.address);
        save = (Button) findViewById(R.id.save);
        savedData = (Button) findViewById(R.id.savedData);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Code to put the data into the database
                DaoSession daoSession = DBInitializer.getNewDaoSession(MainActivity.this);
                insertPersonData(daoSession);
                DBInitializer.closeDbSesion();
            }
        });

        savedData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent = new Intent(MainActivity.this, PersonListActivity.class);
                startActivity(intent);
            }
        });


    }

    private void insertPersonData(DaoSession daoSession) {
        Person person = new Person();
        person.setFname(fname.getText().toString());
        person.setMname(mname.getText().toString());
        person.setLname(lname.getText().toString());
        person.setEmail(email.getText().toString());
        person.setPhone(Long.valueOf(phone.getText().toString()));
        person.setAddress(address.getText().toString());

        PersonDao personDao = daoSession.getPersonDao();
        long x=personDao.insertOrReplace(person);
        Log.d("MainActivity", "X="+x);
        if(x>0){
            Toast.makeText(MainActivity.this, "The data has been inserted successfully", Toast.LENGTH_SHORT).show();
            fname.setText("");
            mname.setText("");
            lname.setText("");
            phone.setText("");
            email.setText("");
            address.setText("");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
