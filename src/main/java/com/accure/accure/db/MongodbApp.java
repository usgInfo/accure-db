package com.accure.accure.db;

import com.mongodb.DBCursor;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.bson.types.ObjectId;

public class MongodbApp {

    private static void displayMapList(ArrayList<HashMap<String, String>> maplist) {
        HashMap<String, String> rowMap = null;
        Iterator<HashMap<String, String>> it = maplist.iterator();
        int count = maplist.size();
        System.out.println(count);
        while (it.hasNext()) {
            rowMap = it.next();
            Set<String> keys = rowMap.keySet();

            for (String key : keys) {

                System.out.print(" " + key + " : " + rowMap.get(key) + " , ");
            }
            System.out.println("\n");
        }
    }

    private static void displayMap(Map resMap) {
        Set<String> keys = resMap.keySet();
        int count = resMap.size();
        System.out.println(count);
        for (String key : keys) {
            System.out.print(" " + key + ": " + resMap.get(key) + " , ");
        }
        System.out.println("\n");
    }

    private static void displayCursor(DBCursor cursor) {
        cursor.count();
        System.out.println("results cursor count --> " + cursor.count());
        while (cursor.hasNext()) {
            System.out.println(cursor.next());
        }
    }

    private static void displaylist(String[] list) {

        int count = list.length;
        System.out.println("results list count --> " + count);
        for (int i = 0; i < count; i++) {
            System.out.println(list[i]);
        }
        System.out.println("\n");
    }

    public static void main(String[] a) throws Exception {

        //MongodbDAO dao = new MongodbDAO("192.168.204.1", "27017", "irheum");
        //MongodbDAO dao = MongodbDAO.getInstance("192.168.204.1", "27017", "ico");
        //System.out.println("connected to mongo db::::: " + dao.getDbName());

//        //check insert/////////////////////
//        String strCollection = "users";
//        String rowKey = "kalyank";
//        Map<String, String> cols = new HashMap<String, String>();
//        cols.put("email", "kalyan@123.com");
//        cols.put("phone", "9844090469");
//        cols.put("name", "kalyan kumar");
//        cols.put("age", "46");
//        cols.put("orgid", "9");
//        cols.put("roles", "100,10");
//
//        System.out.println("starting the test method insert");
//        boolean status = dao.insert(strCollection, rowKey, cols);
//        System.out.println("insert status " + status);
//
//        System.out.println("completed the test method insert");
//        //fetch tests//////////////////////
//        String[] columns = {"email", "name"};
//        String[] columns1 = {"email", "name", "age"};
//
//        displayMap(dao.fetch("users", "name", "kalyan kumar", null));
//        System.out.println("completed fetch all fields test method ");
//
//        displayMap(dao.fetch("users", "name", "kalyan kumar", columns1));
//        System.out.println("completed fetch test by field values method ");
//
//        displayMap(dao.fetch("users", "age", "46", columns));
//        System.out.println("completed fetch test by age id method ");
//
//        displayMap(dao.fetch("users", "5426bd52c5900db30f2d5be3", columns));
//        System.out.println("completed fetch test by pk id method ");
//        //fetchAllRowsByConditions test///////////////////
//        HashMap<String, String> hm = new HashMap<String, String>();
//        // add key-value pair to hashmap
//        hm.put("name", "kalyan kumar");
//        hm.put("age", "46");
//
//        displayMapList(dao.fetchAllRowsByConditions("users", hm));
//        System.out.println("completed fetchAllRowsByConditions all display multi row test method-------- ");
        //fetchRowsByConditions test/////////////////////
//        System.out.println("calling fetchRowsByConditions all display multi row test method ");
//        HashMap<String, Map<String, String>> columns2 = new HashMap<String, Map<String, String>>();
//        Map<String, String> attrib = new HashMap<String, String>();
//        attrib.put("equal", "kalyan kumar");
//        columns2.put("name", attrib);
//        attrib = new HashMap<String, String>();
//        attrib.put("gte", "46");
//        columns2.put("age", attrib);
//        attrib = new HashMap<String, String>();
//        attrib.put("equal", "9844090469");
//        columns2.put("phone", attrib);
//        //displayMapList(dao.fetchRowsByConditions("users", columns2));
//        System.out.println("completed fetchRowsByConditions all display multi row test method ");
//        String fileid = dao.storeBinaryFile("F:/Projects/Accure/IRhume/Docs/reportsdata/DEV/1416576265997summary.pdf", "Prescription", "1234", "PatientReports");
//        dao.getDbFile(fileid, "PatientReports");
//        InputStream ins = dao.getDbFileInputStream(fileid,"PatientReports");
//        dao.getToFileSystem(fileid, "PatientReports" , "F:\\Projects\\Accure\\IRhume\\Docs\\reportsdata\\DEV\\temp\\");
//        System.out.println(dao.listAllFiles("1234", "PatientReports"));
        
        //String distinctlist = dao.fetchDistinctList("labtest", "seq");
        //System.out.println(distinctlist);
    }

    public static void testuser(MongodbDAO dao) throws Exception {
        String table = "users";
        String pkKey = "";
        Map<String, String> cols = new HashMap<String, String>();
        cols.put("email", "xyz@123.com");
        cols.put("phone", "99-9844-090");
        cols.put("name", "James");
        cols.put("age", "52");

        System.out.println("starting the test method insert");
        boolean status = dao.insert(table, pkKey, cols);

        System.out.println("completed new record insert");

        Map resMap = dao.fetch("users", "name", "James", null);
        String pkid = (String) resMap.get("_id");
        System.out.println("id=:" + pkid);

        displayMap(dao.fetch("users", pkid, null));

        System.out.println("completed fetch test by pk id method ");

        ArrayList testlist = new ArrayList();
        testlist.add("admin");
        testlist.add("clinical");
        testlist.add("front desk");
        testlist.add("guest");

        dao.createlist("users", pkid, "roles", testlist);

    }

    public static void testroles(MongodbDAO dao) throws Exception {
        String table = "roles";
        String pkKey = "";
        String key = "rolename";
        String value = "patient";
        String listname = "screens";
        Map<String, String> cols = new HashMap<String, String>();
        cols.put(key, value);

        System.out.println("starting the test method insert");
        boolean status = dao.insert(table, pkKey, cols);

        System.out.println("completed new record insert");

        Map resMap = dao.fetch(table, key, value, null);
        String pkid = (String) resMap.get("_id");
        System.out.println("id=:" + pkid);

        displayMap(dao.fetch(table, pkid, null));

        System.out.println("completed fetch test by pk id method ");

        ArrayList testlist = new ArrayList();
        testlist.add("login");
        //testlist.add("orgmgmt");
        //testlist.add("usermgmt");
        //testlist.add("guest");
        //testlist.add("clinical");
        //testlist.add("profile");
        testlist.add("patientselfdata");
        //testlist.add("patientreg");
        dao.createlist(table, pkid, listname, testlist);
    }
}
