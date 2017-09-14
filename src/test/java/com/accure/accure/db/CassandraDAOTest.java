///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.accure.accure.db;
//
//import com.accure.db.in.DAO;
//import junit.framework.TestCase;
//
///**
// *
// * @author sansari
// */
//public class CassandraDAOTest extends TestCase {
//    
//    String url = "localhost";
//    String port = "9160";
//    String schema = "accure";
//    DAO dao = null;
//    
//    public CassandraDAOTest(String testName) {
//        super(testName);
//    }
//    
//    @Override
//    protected void setUp() throws Exception {
//        super.setUp();
//        //dao = DAOFactory.getDAO("cassandra", url, port, schema);
//    }
//    
//    @Override
//    protected void tearDown() throws Exception {
//        super.tearDown();
//    }
//
//    /**
//     * Test of insert method, of class CassandraDAO.
//     */
//    public void testInsert() throws Exception {
////        String columnFamily = "person";
////        String rowKey = "ansaris1";
////        Map<String, String> cols = new HashMap<String, String>();
////        cols.put("email", "sansari1");
////        cols.put("phone", "95381773421");
////        cols.put("name", "Shamshad");
////        
////        
////        System.out.println("starting the test method insert");
////        boolean status = dao.insert(columnFamily, rowKey, cols);
////        if(!status) fail("The insert single row failed."); 
//    }
//
//    /**
//     * Test of fetch method, of class CassandraDAO.
//     */
//    public void testFetch() throws Exception {
//        System.out.println("fetch");
////        String table = "users";
////        String primaryKey = "accure";
////        String[] columns = {"company", "url"};
////        CassandraDAO instance = null;
////        HashMap expResult = null;
////        HashMap result = instance.fetch(table, primaryKey, columns);
////        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//      //  fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of fetchMultipleRows method, of class CassandraDAO.
//     */
//    public void testFetchMultipleRows() throws Exception {
//        System.out.println("fetchMultipleRows");
////        String table = "";
////        String[] primaryKeys = null;
////        CassandraDAO instance = null;
////        ArrayList expResult = null;
////        ArrayList result = instance.fetchMultipleRows(table, primaryKeys);
////        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//      //  fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of fetchRowsByConditions method, of class CassandraDAO.
//     */
//    public void testFetchRowsByConditions() throws Exception {
//        System.out.println("fetchRowsByConditions");
////        HashMap<String, Map<String, String>> columns1 = new HashMap<String, Map<String, String>>();
////        Map<String, String> attrib = new HashMap<String, String>();
////        attrib.put("equal", "Shamshad");
////        columns1.put("name", attrib);
////        attrib = new HashMap<String, String>();
////        attrib.put("equals", "1234567890");
////        columns1.put("phone", attrib);
////        attrib = new HashMap<String, String>();
////        attrib.put("equal", "sansari@accure.com");
////        columns1.put("email", attrib);
////        columns1.put("prediction_result", null);
////        ArrayList<HashMap<String, String>> result = dao.fetchRowsByConditions("person", columns1);
////        System.out.println(result);
//    }
//}
