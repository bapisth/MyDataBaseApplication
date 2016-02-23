package com.esspl.hemendra.mydatabaseapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.esspl.hemendra.mydaogenerator.dao.DaoMaster;
import com.esspl.hemendra.mydaogenerator.dao.DaoSession;

/**
 * Created by BAPI1 on 14-12-2015.
 */
public class DBInitializer {
    private static DaoMaster.DevOpenHelper helper;
    private static SQLiteDatabase database;
    private static DaoMaster daoMaster;
    private static DaoSession daoSession;
    public static DaoSession getNewDaoSession(Context context){
        helper = new DaoMaster.DevOpenHelper(context, "person-db", null);
        if (database == null)
            database = helper.getWritableDatabase();
        else{
            if (!database.isOpen())
                database = helper.getWritableDatabase();
        }

        daoMaster = new DaoMaster(database);
        daoSession = daoMaster.newSession();
        return daoSession;
    }
    public  static void closeDbSesion(){
        database.close();
    }
}
