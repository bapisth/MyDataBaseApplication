package com.esspl.hemendra.mydaogenerator;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

/**
 * Created by BAPI1 on 13-12-2015.
 */
public class GenerateMyDao {
    public static void main(String[] args) throws Exception{
        Schema schema = new Schema(1,"com.esspl.hemendra.mydaogenerator");
        schema.setDefaultJavaPackageTest("com.esspl.hemendra.mydaogenerator.test");
        schema.setDefaultJavaPackageDao("com.esspl.hemendra.mydaogenerator.dao");
        createPersonTable(schema);
        createTestTable(schema);

        new DaoGenerator().generateAll(schema, "app/src/main/java");
    }

    private static void createTestTable(Schema schema) {
        Entity test = schema.addEntity("Test");
        test.addLongProperty("c1");
        test.addLongProperty("c2");
        test.addLongProperty("c3");
        test.addLongProperty("c4");
    }

    private static void createPersonTable(Schema schema) {
        Entity person = schema.addEntity("Person");
        person.addIdProperty();
        person.addStringProperty("fname");
        person.addStringProperty("mname");
        person.addStringProperty("lname");
        person.addStringProperty("email");
        person.addStringProperty("address");
        person.addLongProperty("phone");
    }
}
