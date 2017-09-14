///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.accure.accure.db;
//
//import com.mongodb.DBObject;
//import com.mongodb.util.JSON;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//import junit.framework.TestCase;
//
///**
// * Dt: 15-10-2014 The Tests are disabled by default. To run the tests "" prefix
// * has to be remove from all test method names, and the setup() function should
// * be initialized with valid database connection information for strUrl1 =
// * "192.168.204.1"; strPort1 = "27017";.
// *
// * @author kalyan
// */
//public class MongodbDAOTest extends TestCase {
//
//    private MongodbDAO dao = null;
//
//    private String strUrl1 = null;
//    private String strPort1 = null;
//    private String strSchema1 = null;
//    private String strTable1 = null;
//    private Map<String, String> resultmap = null;
//    private Map<String, String> resultmap1 = null;
//    private String id = "";
//    private String[] columnnames = null;
//    private char charArray[] = null;
//    private HashMap<String, Map<String, String>> refmap = null;
//
//    public MongodbDAOTest(String testName) {
//        super(testName);
//    }
//
//    @Override
//    protected void setUp() throws Exception {
////        super.setUp();
////
////        strUrl1 = "192.168.2.78"; //Jenkins build 
////        //strUrl1 = "192.168.2.76"; QA
////        strUrl1 = "localhost"; //Unit testing
////        strPort1 = "27017";
////        strSchema1 = "irheumsp1";
////        strTable1 = "users";
//        //dao = MongodbDAO.getInstance(strUrl1, strPort1, strSchema1);
////        String[] columnnames1 = {"userid", "fname", "mname", "lname", "phone1", "phone2", "email1", "email2", "address1", "address2", "city", "state", "country", "gender", "age", "ssn", "status", "validity", "loginid", "password"};
////        columnnames = columnnames1;
////        resultmap = new HashMap<String, String>();
////        resultmap.put("fname", "kishore");
////        resultmap.put("mname", "Kumar");
////        resultmap.put("lname", "Kumar");
////        resultmap.put("phone1", "1234567893");
////        resultmap.put("phone2", "9876543214");
////        resultmap.put("email1", "vkumar@accuresoftware.com");
////        resultmap.put("email2", "vinod@gmail.com");
////        resultmap.put("address1", " 1st Cross Rd); Rt Nagar Mian road); opp to food world); Bengaluru); Karnataka 560032");
////        resultmap.put("address2", " 1st Cross Rd) Rt Nagar Mian road) opp to food world) Bengaluru) Karnataka 560032");
////        resultmap.put("city", "Bangalore");
////        resultmap.put("state", "Karnataka");
////        resultmap.put("country", "India");
////        resultmap.put("gender", "male");
////        resultmap.put("age", "40");
////        resultmap.put("ssn", "1ac05");
////        resultmap.put("status", "active");
////        resultmap.put("validity", "2018");
////        resultmap.put("loginid", "vkumar");
////        resultmap.put("password", "123");
////
////        resultmap1 = new HashMap<String, String>();
////        resultmap1.put("fname", "Manoj");
////        resultmap1.put("mname", "Kumar");
////        resultmap1.put("lname", "Kumar");
////        resultmap1.put("phone1", "1234567893");
////        resultmap1.put("phone2", "9876543214");
////        resultmap1.put("email1", "mkumar@accuresoftware.com");
////        resultmap1.put("email2", "minod@gmail.com");
////        resultmap1.put("address1", " 1st Cross Rd); Rt Nagar Mian road); opp to food world); Bengaluru); Karnataka 560032");
////        resultmap1.put("address2", " 1st Cross Rd) Rt Nagar Mian road) opp to food world) Bengaluru) Karnataka 560032");
////        resultmap1.put("city", "Bangalore");
////        resultmap1.put("state", "Karnataka");
////        resultmap1.put("country", "India");
////        resultmap1.put("gender", "male");
////        resultmap1.put("age", "49");
////        resultmap1.put("ssn", "1ac09");
////        resultmap1.put("status", "active");
////        resultmap1.put("validity", "2019");
////        resultmap1.put("loginid", "mkumar");
////        resultmap1.put("password", "129");
////
////        refmap = new HashMap<String, Map<String, String>>();
////  
////        Map<String, String> attrib = new HashMap<String, String>();
////        attrib.put("dascalc", "DAS83A");
////        refmap.put("dascalc", attrib);
////
////        attrib = new HashMap<String, String>();
////        attrib.put("licnum", "F$123446");
////        refmap.put("licnum", attrib);
////
////        attrib = new HashMap<String, String>();
////        attrib.put("deanum", "56-69");
////        refmap.put("medicaidnum", attrib);
//
//    }
//
//    @Override
//    protected void tearDown() throws Exception {
//        super.tearDown();
//        //dao.close();
//    }
//
//    /**
//     * Test of getInstance method, of class MongodbDAO.
//     */
//    public void DISABLED_testGetInstance() {
//        System.out.println("getInstance");
////        String strUrl = strUrl1;
////        String strPort = strPort1;
////        String strSchema = strSchema1;
////
////        MongodbDAO expResult = MongodbDAO.getInstance(strUrl, strPort, strSchema);
////        MongodbDAO result = MongodbDAO.getInstance(strUrl, strPort, strSchema);
////        assertEquals(expResult, result);
////        dao = result;
//
//    }
//
//    /**
//     * Test of getDefaultInstance method, of class MongodbDAO.
//     */
//    public void DISABLED_testGetDefaultInstance() {
//        System.out.println("getDefaultInstance");
//        String strUrl = strUrl1;
//        String strSchema = strSchema1;
//        MongodbDAO expResult = MongodbDAO.getDefaultInstance(strUrl, strSchema);
//        MongodbDAO result = MongodbDAO.getDefaultInstance(strUrl, strSchema);
//        assertEquals(expResult, result);
//
//    }
//
//    /**
//     * Test of getDbName method, of class MongodbDAO.
//     */
//    public void DISABLED_testGetDbName() {
//        System.out.println("getDbName");
//        String strSchema = strSchema1;
//
//        MongodbDAO instance = MongodbDAO.getInstance(strUrl1, strPort1, strSchema1);
//        String expResult = strSchema;
//        String result = instance.getDbName();
//        assertEquals(expResult, result);
//
//    }
//
//    /**
//     * Test of insert method, of class MongodbDAO.
//     */
//    public void DISABLED_testInsert_3args() throws Exception {
//        System.out.println("insert");
//        String strTable = strTable1;
//        String strPrimaryKey = "_id";
//        Map<String, String> columns = null;
//        MongodbDAO instance = dao;
//        columns = resultmap;
//
//        boolean expResult = true;
//        boolean result = instance.insert(strTable, strPrimaryKey, columns);
//        id = instance.insert(strTable, strPrimaryKey, columns, "");
//
//        assertEquals(expResult, result);
//
//    }
//
//    /**
//     * Test of fetch method, of class MongodbDAO.
//     */
//    public void DISABLED_testFetch_3args() throws Exception {
//
//        System.out.println("fetch");
//
//        //System.out.println("insert");
//        String strTable = strTable1;
//        String strPKey = "_id";
//
//        MongodbDAO instance = dao;
//        //columns=resultmap;
//        String pkid = instance.insert(strTable, strPKey, resultmap, "");
//        String strPrimaryKey = pkid;
//
//        Map<String, String> expResult = resultmap;
//        Map<String, String> result = instance.fetch(strTable, strPrimaryKey, columnnames);
//        result.remove("_id");
//        assertEquals(expResult, result);
//
//    }
//
//    /**
//     * Test of fetch method, of class MongodbDAO.
//     */
//    public void DISABLED_testFetch_4args() throws Exception {
//        System.out.println("fetch");
//        String strTable = strTable1;
//        String strFieldname = "fname";
//        String strFieldvalue = "kishore";
//
//        MongodbDAO instance = dao;
//        Map<String, String> expResult = resultmap;
//        Map<String, String> result = instance.fetch(strTable, strFieldname, strFieldvalue, columnnames);
//        result.remove("_id");
//
//        assertEquals(expResult, result);
//
//    }
//
//    /**
//     * Test of fetchMultipleRows method, of class MongodbDAO.
//     */
//    public void testFetchMultipleRows() throws Exception {
////        System.out.println("fetchMultipleRows");
////        String strTable = strTable1
////        String[] primaryKeys = null;
////        MongodbDAO instance = null;
////        ArrayList<HashMap<String, String>> expResult = null;
////        ArrayList<HashMap<String, String>> result = instance.fetchMultipleRows(strTable, primaryKeys);
////        assertEquals(expResult, result);
//        // TODO review the generated DISABLED_test code and remove the default call to fail.
//        //fail("The DISABLED_test case is a prototype.");
//    }
//
//    /**
//     * Test of fetchRowsByConditions method, of class MongodbDAO.
//     */
//    public void DISABLED_testFetchRowsByConditions_String_Map() throws Exception {
//        System.out.println("fetchRowsByConditions");
//        String strTable = strTable1;
//
//        HashMap<String, Map<String, String>> columns2 = new HashMap<String, Map<String, String>>();
//        Map<String, String> attrib = new HashMap<String, String>();
//        attrib.put("equal", "kishore");
//        columns2.put("fname", attrib);
//        attrib = new HashMap<String, String>();
//        attrib.put("gte", "9");
//        columns2.put("age", attrib);
//        attrib = new HashMap<String, String>();
//        attrib.put("equal", "1234567893");
//        columns2.put("phone1", attrib);
//        Map<String, Map<String, String>> columns = columns2;
//
//        attrib = new HashMap<String, String>();
//        attrib.put("SORTKEY", "fname");
//        columns2.put("SORTINFOKEY", attrib);
//
//        attrib = new HashMap<String, String>();
//        attrib.put("SORTORDER", "-1");
//        columns2.put("SORTORDERKEY", attrib);
//
//        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
//        HashMap<String, String> resMap = new HashMap<String, String>();
//        resMap.put("fname", "kishore");
//        list.add(resMap);
//
//        HashMap<String, String> resMap1 = new HashMap<String, String>();
//        resMap.put("phone1", "1234567893");
//        list.add(resMap1);
//
//        MongodbDAO instance = dao;
//        String expResult = instance.fetchRowsByConditions(strTable, columns);
//        String result = instance.fetchRowsByConditions(strTable, columns);
////        System.out.println("expResult" + expResult.size());
//        // displayMapList(expResult);
//
//        // System.out.println("result" + result.size());
//        //displayMapList(result);
//        assertEquals(expResult, result);
//
//    }
//
//    /**
//     * Test of fetchRowsByConditions method, of class MongodbDAO.
//     */
//    public void DISABLED_testFetchRowsByConditions_3args_1() throws Exception {
//        System.out.println("fetchRowsByConditions");
//        String strTable = strTable1;
//        Map<String, Map<String, String>> columns = null;
//        int limit = 01;
//        MongodbDAO instance = dao;
//
//        HashMap<String, Map<String, String>> columns2 = new HashMap<String, Map<String, String>>();
//        Map<String, String> attrib = new HashMap<String, String>();
//        attrib.put("equal", "kishore");
//        columns2.put("fname", attrib);
//        attrib = new HashMap<String, String>();
//        attrib.put("gte", "09");
//        columns2.put("age", attrib);
//        attrib = new HashMap<String, String>();
//        attrib.put("equal", "1234567893");
//        columns2.put("phone1", attrib);
//        columns = columns2;
//
//        attrib = new HashMap<String, String>();
//        attrib.put("SORTKEY", "fname");
//        columns2.put("SORTINFOKEY", attrib);
//
//        attrib = new HashMap<String, String>();
//        attrib.put("SORTORDER", "-1");
//        columns2.put("SORTORDERKEY", attrib);
//
//        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
//        HashMap<String, String> resMap = new HashMap<String, String>();
//        resMap.put("fname", "kishore");
//        list.add(resMap);
//
//        HashMap<String, String> resMap1 = new HashMap<String, String>();
//        resMap.put("phone1", "1234567893");
//        list.add(resMap1);
//
//        String result = instance.fetchRowsByConditions(strTable, columns, limit);
//
//        // System.out.println("result-limit" + result.size());
//        //displayMapList(result);
//        //  assertEquals(result.size(), limit);
//    }
//
//    /**
//     * Test of fetchAllRowsByConditions method, of class MongodbDAO.
//     */
//    public void DISABLED_testFetchAllRowsByConditions() throws Exception {
//        System.out.println("fetchAllRowsByConditions");
//        String strTable = strTable1;
//        Map<String, String> conditions = resultmap;
//        MongodbDAO instance = dao;
//        String expResult = instance.fetchAllRowsByConditions(strTable, conditions);
//        String result = instance.fetchAllRowsByConditions(strTable, conditions);
//        assertEquals(expResult, result);
//        // TODO review the generated DISABLED_test code and remove the default call to fail.
//        //fail("The DISABLED_test case is a prototype.");
//    }
//
//    /**
//     * Test of update method, of class MongodbDAO.
//     */
//    public void DISABLED_testUpdate() throws Exception {
//        System.out.println("update");
//        String strTable = strTable1;
//        Map<String, String> columns = resultmap;
//        MongodbDAO instance = dao;
//        String strPrimaryKey = instance.insert(strTable, "", resultmap, "");
//        boolean expResult = true;
//        boolean result = instance.update(strTable, strPrimaryKey, columns.toString());
//        assertEquals(expResult, result);
//
//    }
//
//    /**
//     * Test of close method, of class MongodbDAO.
//     */
//    public void DISABLED_testClose() {
//        System.out.println("close");
//        MongodbDAO instance = dao;
//        instance.close();
//        //assertEquals(null, instance.getDbName());
//
//    }
//
//    /**
//     * Test of deletefromlist method, of class MongodbDAO.
//     */
//    public void DISABLED_testDeletefromlist() throws Exception {
//        System.out.println("deletefromlist");
//        String strTable = "";
//        String strPrimaryKey = "";
//        String strListname = "";
//        String strListvalue = "";
//        MongodbDAO instance = null;
//        boolean expResult = false;
//        //boolean result = instance.deletefromlist(strTable, strPrimaryKey, strListname, strListvalue);
//        //assertEquals(expResult, result);
//        // TODO review the generated DISABLED_test code and remove the default call to fail.
//        //fail("The DISABLED_test case is a prototype.");
//    }
//
//    /**
//     * Test of addtolist method, of class MongodbDAO.
//     */
//    public void DISABLED_testAddtolist() throws Exception {
//        System.out.println("addtolist");
//        String strTable = strTable1;
//        MongodbDAO instance = dao;
//        String strPrimaryKey = instance.insert(strTable, "", resultmap, "");
//
//        String strListname = "roles";
//        String strListvalue = "admin";
//
//        boolean expResult = true;
//        boolean result = instance.addtolist(strTable, strPrimaryKey, strListname, strListvalue);
//        assertEquals(expResult, result);
//
//    }
//
//    /**
//     * Test of getlist method, of class MongodbDAO.
//     */
//    public void DISABLED_testGetlist() throws Exception {
//        System.out.println("getlist");
//        String strTable = strTable1;
//        MongodbDAO instance = dao;
//        String strListname = "roles";
//        String strPrimaryKey = instance.insert(strTable, "", resultmap, "");
//        instance.addtolist(strTable, strPrimaryKey, strListname, "admin");
//
//        String[] expResult = {"admin"};
//        String[] result = instance.getlist(strTable, strPrimaryKey, strListname);
//        assertEquals(expResult[0], result[0]);
//
//    }
//
//    /**
//     * Test of createlist method, of class MongodbDAO.
//     */
//    public void DISABLED_testCreatelist() throws Exception {
//        System.out.println("createlist");
//        String strTable = strTable1;
//
//        String strListname = "roles";
//        ArrayList arList = null;
//        MongodbDAO instance = dao;
//        String strPrimaryKey = instance.insert(strTable, "", resultmap, "");
//        arList = new ArrayList();
//        arList.add("Clinical");
//        arList.add("Administrative");
//        arList.add("Patient");
//        arList.add("Temp");
//        arList.add("Reports");
//        arList.add("Guest");
//        arList.add("SYSTEM");
//
//        boolean expResult = true;
//        boolean result = instance.createlist(strTable, strPrimaryKey, strListname, arList);
//        assertEquals(expResult, result);
//
//    }
//
//    /**
//     * Test of insert method, of class MongodbDAO.
//     */
//    public void DISABLED_testInsert_String_Map() throws Exception {
//        System.out.println("insert");
//        String strTable = strTable1;
//        Map<String, String> columns = resultmap;
//        MongodbDAO instance = dao;
//        String expResult = "543ca78822462402d34e248e";
//        String result = instance.insert(strTable, columns);
//        assertEquals(expResult.length(), result.length());
//
//    }
//
//    /**
//     * Test of insert method, of class MongodbDAO.
//     */
//    public void DISABLED_testInsert_4args_1() throws Exception {
//        System.out.println("insert");
//        String strTable = strTable1;
//        String strPrimaryKey = "";
//        Map<String, String> columns = resultmap;
//        String dummy = "";
//        MongodbDAO instance = dao;
//        String expResult = "543ca78822462402d34e248e";
//        String result = instance.insert(strTable, strPrimaryKey, columns, dummy);
//        assertEquals(expResult.length(), result.length());
//
//    }
//
//    /**
//     * Test of insert method, of class MongodbDAO.
//     */
//    public void DISABLED_testInsert_4args_2() throws Exception {
//        System.out.println("insert");
//        String strTable = strTable1;
//        String strPrimaryKey = "fname";
//        String strPrimaryValue = "pkey";
//        Map<String, String> columns = resultmap;
//        MongodbDAO instance = dao;
//        String expResult = "543ca78822462402d34e248e";
//        String result = instance.insert(strTable, strPrimaryKey, strPrimaryValue, columns);
//        assertEquals(expResult.length(), result.length());
//
//    }
//
//    /**
//     * Test of fetchAllRowsLike method, of class MongodbDAO.
//     */
//    public void DISABLED_testFetchAllRowsLike() throws Exception {
//        System.out.println("fetchAllRowsLike");
//        String strTable = strTable1;
//        Map<String, String> conditions = new HashMap<String, String>();
//
//        //add key-value pair to hashmap
//        //String pattern = "^.*"+"kis ho"+".*$";
//        String REGEX_PATTERN = "(^" + "kis" + "|\\W" + "ku" + ")";
//
//        conditions.put("fname", REGEX_PATTERN);
//        conditions.put("age", "65");
//
//        conditions.put("SORTKEY", "fname");
//        conditions.put("SORTORDER", "-1");
//
//        String strLogic = "or";
//        MongodbDAO instance = dao;
//        ArrayList<HashMap<String, String>> expResult = null;
//        String result = instance.fetchAllRowsLike(strTable, conditions, strLogic);
//
//        // displayMapList(result);
//        //assertEquals(expResult, result);
//    }
//
//    /**
//     * Test of fetchRowsByConditions method, of class MongodbDAO.
//     */
//    public void DISABLED_testFetchRowsByConditions_3args_2() throws Exception {
//        System.out.println("fetchRowsByConditions");
//        String strTable = strTable1;
//
//        String strLogic = "or";
//        MongodbDAO instance = dao;
//
//        HashMap<String, Map<String, String>> columns2 = new HashMap<String, Map<String, String>>();
//        Map<String, String> attrib = new HashMap<String, String>();
//        attrib.put("equal", "kishore");
//        columns2.put("fname", attrib);
//        attrib = new HashMap<String, String>();
//        attrib.put("gte", "46");
//        columns2.put("age", attrib);
//        attrib = new HashMap<String, String>();
//        attrib.put("equal", "9844090469");
//        columns2.put("phone1", attrib);
//
//        attrib = new HashMap<String, String>();
//        attrib.put("SORTKEY", "fname");
//        columns2.put("SORTINFOKEY", attrib);
//
//        attrib = new HashMap<String, String>();
//        attrib.put("SORTORDER", "-1");
//        columns2.put("SORTORDERKEY", attrib);
//
//        Map<String, Map<String, String>> columns = columns2;
//
//        ArrayList<HashMap<String, String>> expResult = null;
//        String result = instance.fetchRowsByConditions(strTable, columns, strLogic);
//        //displayMapList(result);
//
//        //assertEquals(expResult, result);
//    }
//
//    /**
//     * Test of createMap method, of class MongodbDAO.
//     */
//    public void DISABLED_testCreateMap() throws Exception {
//        System.out.println("createMap");
//        String strTable = strTable1;
//        String strMapName = "preferences";
//
//        MongodbDAO instance = dao;
//        String strPrimaryKey = instance.insert(strTable, "", resultmap, "");
//
//        HashMap<String, Map<String, String>> columns2 = new HashMap<String, Map<String, String>>();
//
//        Map<String, String> attrib = new HashMap<String, String>();
//        attrib.put("dascalc", "DAS83A");
//        columns2.put("dascalc", attrib);
//
//        attrib = new HashMap<String, String>();
//        attrib.put("licnum", "F$123446");
//        columns2.put("licnum", attrib);
//
//        attrib = new HashMap<String, String>();
//        attrib.put("deanum", "56-69");
//        columns2.put("medicaidnum", attrib);
//
//        Map keyValues = columns2;
//
//        displayMap(keyValues);
//
//        boolean expResult = true;
//        boolean result = instance.createMap(strTable, strPrimaryKey, strMapName, keyValues);
//        assertEquals(expResult, result);
//
//    }
//
//    /**
//     * Test of getMap method, of class MongodbDAO.
//     */
//    public void DISABLED_testGetMap() throws Exception {
//        System.out.println("getMap");
//        String strTable = strTable1;
//        String strMapName = "preferences";
//
//        MongodbDAO instance = dao;
//        String strPrimaryKey = instance.insert(strTable, "", resultmap, "");
//        instance.createMap(strTable, strPrimaryKey, strMapName, refmap);
//
//        Map expResult = refmap;
//        Map result = instance.getMap(strTable, strPrimaryKey, strMapName);
//        assertEquals(expResult, result);
//
//    }
//
//    /**
//     * Test of deleteFromMap method, of class MongodbDAO.
//     */
//    public void DISABLED_testDeleteFromMap() throws Exception {
//        System.out.println("deleteFromMap");
//        String strTable = strTable1;
//        String strMapName = "preferences";
//        String strKeyName = "licnum";
//
//        MongodbDAO instance = dao;
//        String strPrimaryKey = instance.insert(strTable, "", resultmap, "");
//        instance.createMap(strTable, strPrimaryKey, strMapName, refmap);
//
//        boolean expResult = true;
//        boolean result = instance.deleteFromMap(strTable, strPrimaryKey, strMapName, strKeyName);
//        assertEquals(expResult, result);
//
//    }
//
//    /**
//     * Test of updateToMap method, of class MongodbDAO.
//     */
//    public void DISABLED_testUpdateToMap() throws Exception {
//        System.out.println("updateToMap");
//        String strTable = strTable1;
//        String strMapName = "preferences";
//        String strKeyName = "licnum";
//        String KeyValue = "updated value";
//
//        MongodbDAO instance = dao;
//        String strPrimaryKey = instance.insert(strTable, "", resultmap, "");
//        instance.createMap(strTable, strPrimaryKey, strMapName, refmap);
//
//        boolean expResult = true;
//        boolean result = instance.updateToMap(strTable, strPrimaryKey, strMapName, strKeyName, KeyValue);
//        assertEquals(expResult, result);
//
//    }
//
//    private static void displayMapList(ArrayList<HashMap<String, String>> maplist) {
//        HashMap<String, String> rowMap = null;
//        Iterator<HashMap<String, String>> it = maplist.iterator();
//        int count = maplist.size();
//        System.out.println(count);
//        while (it.hasNext()) {
//            rowMap = it.next();
//            Set<String> keys = rowMap.keySet();
//
//            for (String key : keys) {
//
//                System.out.print(" " + key + " : " + rowMap.get(key) + " , ");
//            }
//            System.out.println("\n");
//        }
//    }
//
//    private static void displayMap(Map resMap) {
//        Set<String> keys = resMap.keySet();
//        int count = resMap.size();
//        System.out.println(count);
//        for (String key : keys) {
//            System.out.print(" " + key + ": " + resMap.get(key) + " , ");
//        }
//        System.out.println("\n");
//    }
//
//    /**
//     * Test of fetchMultipleRows method, of class MongodbDAO.
//     */
//    public void DISABLED_testFetchMultipleRows() throws Exception {
//        System.out.println("fetchMultipleRows");
//        String strTable = "";
//        String[] primaryKeys = null;
//        MongodbDAO instance = null;
//        ArrayList<HashMap<String, String>> expResult = null;
//        //ArrayList<HashMap<String, String>> result = instance.fetchMultipleRows(strTable, primaryKeys);
//        //assertEquals(expResult, result);
//        // TODO review the generated DISABLED_test code and remove the default call to fail.
//        //fail("The DISABLED_test case is a prototype.");
//    }
//
//    /**
//     * Test of storeBinaryFile method, of class MongodbDAO.
//     */
//    public void DISABLED_testStoreBinaryFile() throws Exception {
//        System.out.println("storeBinaryFile");
//        String strFilePath = "F:\\Projects\\Testdata\\BiniaryFiles\\mongodb-win32-i386-2.6.4-signed.msi";
//        String strDocType = "PatientSummary";
//        String strOwnerID = "54267b84c5905df99f96882f";
//        //String docid=dao.storeBinaryFile("F:\\Projects\\Testdata\\BiniaryFiles\\mongodb-win32-i386-2.6.4-signed.msi","PatientSummary", "54267b84c5905df99f96882f");
//        MongodbDAO instance = dao;
//        String expResult = "54267b84c5905df99f96882f";
//        String result = instance.storeBinaryFile(strFilePath, strDocType, strOwnerID, "mybucket");
//        assertEquals(expResult.length(), result.length());
//        // TODO review the generated test code and remove the default call to fail.
//        //fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of fetchAllRowsLike method, of class MongodbDAO.
//     */
//    public void DISABLED_testFetchRowsLike() throws Exception {
//        System.out.println("fetchRowsLike");
//        String strTable = "questionbank";
//        Map<String, String> conditions = new HashMap<String, String>();
//
//        //add key-value pair to hashmap
//        //String pattern = "^.*"+"kis ho"+".*$";
//        String REGEX_PATTERN = "(^" + "kis" + "|\\W" + "ku" + ")";
//
//        conditions.put("orgid", "d");
//        //conditions.put("age", "65");
//
//        conditions.put("SORTKEY", "orgid");
//        conditions.put("SORTORDER", "-1");
//
//        String strLogic = "or";
//        MongodbDAO instance = dao;
//        ArrayList<HashMap<String, String>> expResult = null;
//        String result = instance.fetchRowsLike(strTable, conditions, strLogic, 100, 100);
//        instance.fetchRowsLike(strTable, conditions, strLogic, 0, 100);
//        instance.fetchRowsLike(strTable, conditions, strLogic, 0, 128);
//        instance.fetchRowsLike(strTable, conditions, strLogic, 0, 129);
//        instance.fetchRowsLike(strTable, conditions, strLogic, 0, 0);
//        instance.fetchRowsLike(strTable, conditions, strLogic, -1, -1);
//
//        instance.fetchRowsLike(strTable, conditions, strLogic, 1000, 100);
//        instance.fetchRowsLike(strTable, conditions, strLogic, 10, 128);
//        instance.fetchRowsLike(strTable, conditions, strLogic, 16, 10);
//        instance.fetchRowsLike(strTable, conditions, strLogic, 128, 100);
//        instance.fetchRowsLike(strTable, conditions, strLogic, 129, 50);
//
//    }
//
//    /**
//     * Test aggregate with match condition
//     */
//    public void DISABLED_testAggregateWithMatchGroupAndSort() throws Exception {
//
//        System.out.println("aggregateWithMath\n");
//        MongodbDAO instance = dao;
//        String expResult = "54267b84c5905df99f96882f";
//        // match condition
//        Map<String, String> matchMap = new HashMap<String, String>();
//        matchMap.put("orgName", "UNMC Village Pointe");
//        // sort condition, 
//        // if you are using group then you can sort by column name by _id or by count(aggregrate)
//        Map<String, Integer> sortMap = new HashMap<String, Integer>();
//        sortMap.put("_id", -1);
//        // group list
//        Set<String> groupList = new HashSet<String>();
//        groupList.add("preAuthStatus");
//        // condition
//        Map<String, Object> cond = new HashMap<String, Object>();
//        cond.put("match", matchMap);
//        cond.put("sort", sortMap);
//        cond.put("group", groupList);
//        String result = instance.aggregateWithMatchGroupAndSort("preauth", cond, "or");
//
//        System.out.println("RESULT -- " + result);
//        DBObject obj = (DBObject) JSON.parse(result);
//        System.out.println("DBObject -- " + obj.keySet().size());
//        assertEquals(obj.keySet().size(), 4);
//        // TODO review the generated test code and remove the default call to fail.
//        //fail("The test case is a prototype.");
//    }
//
//}
