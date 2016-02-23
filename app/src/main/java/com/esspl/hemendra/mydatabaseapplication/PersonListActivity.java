package com.esspl.hemendra.mydatabaseapplication;

import android.app.ActionBar;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.esspl.hemendra.listener.RecyclerItemClickListener;
import com.esspl.hemendra.mydaogenerator.Person;
import com.esspl.hemendra.mydaogenerator.dao.DaoSession;
import com.esspl.hemendra.mydaogenerator.dao.PersonDao;
import com.esspl.hemendra.util.PersonListAdapter;
import com.esspl.hemendra.util.RecyclerViewAdapter;

import java.util.List;

import de.greenrobot.dao.query.DeleteQuery;
import de.greenrobot.dao.query.Query;
import de.greenrobot.dao.query.WhereCondition;

public class PersonListActivity extends AppCompatActivity {

    //private ListView personListView;
    private final String TAG = "PersonListActivity";
    private RecyclerView personListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        DaoSession daoSession = DBInitializer.getNewDaoSession(PersonListActivity.this);
        PersonDao personDao = daoSession.getPersonDao();

        final List<Person> persons = personDao.loadAll();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        personListView  = (RecyclerView) findViewById(R.id.personListView);
       /* final PersonListAdapter  personListAdapter = new PersonListAdapter(this, persons);
        personListView.setAdapter(personListAdapter);*/
        final RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(PersonListActivity.this, persons);
        personListView.setLayoutManager(linearLayoutManager);
        personListView.setAdapter(recyclerViewAdapter);

        /*personListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //String text = ((TextView)view.findViewById(R.id.address)).getText().toString();
                String text  = ((TextView)view).getText().toString();
                Toast.makeText(PersonListActivity.this, "Item Clicked at position"+text, Toast.LENGTH_SHORT).show();
            }
        });*/

        personListView.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnListItemClickListener() {

            @Override
            public void onItemClick(View view, final int position) {
                Log.d("PersonListActivity", "onItemClick: ");
                TextView textView = (TextView) view.findViewById(R.id.person_name);
                Log.d(TAG, "onItemClick: " + textView != null ? textView.getText().toString() : "Text View is Null...");
                String fname = textView != null ? textView.getText().toString() : "";
                final ImageView imageView = (ImageView) view.findViewById(R.id.delete);
                final ImageView editImage = (ImageView) view.findViewById(R.id.editContact);
                TextView pId = (TextView) view.findViewById(R.id.person_id);
                final int personId = pId != null ? Integer.parseInt(pId.getText().toString()) : 0;
                if (imageView != null) {
                    Log.d(TAG, "onItemClick: Image view not null");
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Log.d(TAG, "onClick: Onclick Listener is working........");
                            AlertDialog.Builder builder = new AlertDialog.Builder(PersonListActivity.this, R.style.AppcompatAlertDialogStyle);
                            builder.setTitle("Confirm");
                            builder.setMessage("Do you want to Delete?");
                            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Log.d(TAG, "onClick: You clicked yes.. data going to be deleted");
                                    recyclerViewAdapter.removeItem(position);
                                    deleteRecordFromDatabase(personId);
                                }
                            });
                            builder.setNegativeButton("No", null);
                            builder.show();
                            //Toast.makeText(PersonListActivity.this, "You Clicked on Delete Icon", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                //Toast.makeText(PersonListActivity.this, "The Item is clicked at position :" + fname, Toast.LENGTH_SHORT).show();

                editImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(PersonListActivity.this, EditContactActivity.class);
                        intent.putExtra("personId",personId);
                        startActivity(intent);
                    }
                });

            }

            @Override
            public void onItemLongClick(View view, int position) {
                Log.d("PersonListActivity", "onItemLongClick: ");
                Toast.makeText(PersonListActivity.this, "The Item is clicked at position :" + position, Toast.LENGTH_SHORT).show();
            }
        }, personListView));



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                Log.d("PersonListActivity", "Back Button Clicked.....");
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void deleteRecordFromDatabase(int personId){
        Log.d(TAG, "deleteRecordFromDatabase: inside method");
        DaoSession daoSession = DBInitializer.getNewDaoSession(PersonListActivity.this);
        PersonDao personDao = daoSession.getPersonDao();
        //Query query = personDao.queryBuilder().where( new WhereCondition.StringCondition("_ID="+personId)).build();

        DeleteQuery deleteQuery  = personDao.queryBuilder().where(new WhereCondition.StringCondition("_ID="+personId)).buildDelete();
        Log.d(TAG, "deleteRecordFromDatabase: delete query is:"+deleteQuery.toString());
        deleteQuery.executeDeleteWithoutDetachingEntities();
        Log.d(TAG, "deleteRecordFromDatabase: Item removed successfully from the database.....");
        Toast.makeText(PersonListActivity.this, "The Contact with id"+personId+" successfully deleted",Toast.LENGTH_SHORT).show();


        //personDao.delete();
    }
}
